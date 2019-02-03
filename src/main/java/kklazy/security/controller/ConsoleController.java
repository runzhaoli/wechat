package kklazy.security.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kklazy.common.constants.ContextConstants;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.utils.DateUtils;
import kklazy.persistence.utils.FileUtils;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.security.model.Dictionary;
import kklazy.security.model.Employee;
import kklazy.security.model.Resource;
import kklazy.security.service.DictionaryService;
import kklazy.security.service.EmployeeService;
import kklazy.security.support.resource.callback.ResourceCallBack;
import kklazy.security.support.resource.provider.Provider;

/**
 * 框架首页
 * 
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.CONSOLE_NAMESPACE)
public class ConsoleController extends BasePageController<DefaultModel, String> {

	public static final String CENTER = "/center";
	public static final String INDEX = "/index";
	public static final String UPLOAD = "/upload";

	@Autowired
	private Provider provider;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public PageService<DefaultModel, String> pageservice() {
		return null;
	}

	@Override
	public String path() {
		return "/webpages/console";
	}

	/**
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = ConsoleController.CENTER)
	public String center(ModelMap modelMap) {
		try {
			Employee e = employeeService.findBy(SecurityApplicationContext.getPassportDetails().getEmployee().getId());
			List<Resource> resources = new ArrayList<Resource>();
			final Set<Resource> rs = e.gatherResources();
			final Set<String> rids = e.gatherResourceIds();
			final List<Dictionary> dictionaries = dictionaryService.findByGroup(ContextConstants.GROUP.RESOURCE_TYPE);
			for (Resource r : rs) {
				if (r.getSort() == null) {
					continue;
				}
				if (r.getParent() != null) {
					continue;
				}
//				boolean status = false;
//				for (Dictionary dictionary : dictionaries) {
//					if (ContextConstants.RESOURCE_TYPE.TYPE_BUTTON.equals(dictionary.getCode()) && dictionary.getValue().equals(r.getType())) {
//						status = true ;
//						break ;
//					}
//				}
//				if (status) { continue ; }
				Resource container = new Resource(r);
				provider.merge(container, r.getSublevel(), new ResourceCallBack() {
					/**
					 * @see kklazy.security.support.resource.callback.ResourceCallBack#doInAssemble(java.lang.Object)
					 */
					@Override
					public Object doInAssemble(Object entity) {
						if (StringUtils.isBlank(((Resource) entity).getType())) {
							return null;
						}
						if (((Resource) entity).getSort() == null) {
							return null;
						}
						for (Dictionary dictionary : dictionaries) {
							if (ContextConstants.RESOURCE_TYPE.TYPE_BUTTON.equals(dictionary.getCode()) && dictionary.getValue().equals(((Resource) entity).getType())) {
								return null;
							}
						}
						return rids.contains(((Resource) entity).getId()) ? new Resource((Resource) entity) : null;
					}
				});
				
				resources.add(container);
			}
			Collections.sort(resources, new Comparator<Resource>() {
				/**
				 * @param up
				 * @param down
				 * @return
				 * 
				 * @author Kui
				 */
				@Override
				public int compare(Resource up, Resource down) {
					if (up.getSort() == null || down.getSort() == null) {
						return -1;
					}
					return up.getSort().compareTo(down.getSort());
				}
			});
			modelMap.put("resources", resources);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
			e.printStackTrace();
		}
		return path() + ConsoleController.CENTER;
	}

	/**
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = ConsoleController.INDEX)
	public String index(ModelMap modelMap) {
		try {
			modelMap.put("details", SecurityApplicationContext.getPassportDetails());
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
		}
		return path() + ConsoleController.INDEX;
	}

	/**
	 * @param uploadfile
	 * @param request
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = ConsoleController.UPLOAD)
	@ResponseBody
	public Map<String, Object> upload(@RequestParam(value = "uploadfile", required = false) MultipartFile uploadfile, HttpServletRequest request, ModelMap modelMap) {

		Map<String, Object> json = new HashMap<String, Object>();
		String filename = uploadfile.getOriginalFilename();

		Logger.getLogger(getClass()).info("UPLOAD FILE: [" + filename + "]...");

		String path = request.getSession().getServletContext().getRealPath("/");
		String base = System.getProperty("catalina.base");
		String folder = "/attached/" + DateUtils.format("yyyyMMdd") + "/";
		String suffix = FileUtils.suffix(filename);

		try {
			/**
			 * 获取当前容器地址不为空,则将文件放在容器webapps目录下,并将文件访问地址以URL方式存储
			 */
			if (StringUtils.isNotBlank(base)) {
				base += "/webapps/";
				String retval = FileUtils.save(base, folder, System.currentTimeMillis() + "." + suffix, uploadfile.getInputStream());
				StringBuffer url = request.getRequestURL();
				String context = url.delete(url.length() - request.getRequestURI().length(), url.length()).toString();
				json.put("local_path", base + retval);
				json.put("remote_path", context + retval);
			} else {
				String retval = FileUtils.save(path, folder, System.currentTimeMillis() + "." + suffix, uploadfile.getInputStream());
				StringBuilder url = new StringBuilder().append(request.getScheme()).append("://").append(request.getHeader("host")).append(request.getContextPath());
				String context = url.toString();
				json.put("local_path", (path + retval).replace("\\", "/"));
				json.put("remote_path", context.toString() + retval);
			}
			Logger.getLogger(getClass()).info("local path: [" + json.get("local_path") + "]...");
			Logger.getLogger(getClass()).info("remote path: [" + json.get("remote_path") + "]...");

			json.put("result", true);
			json.put("message", "success");
		} catch (IOException e) {
			e.printStackTrace();
			json.put("result", false);
			json.put("message", "图片上传失败~请重试!");
		}
		return json;
	}

}
