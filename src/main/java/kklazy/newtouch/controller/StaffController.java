package kklazy.newtouch.controller;

import java.io.IOException;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.MatchMode;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.newtouch.model.Staff;
import kklazy.newtouch.service.StaffService;
import kklazy.newtouch.service.SynchronizeHrcDataService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.callback.AssembleDownloadExcelCallBack;
import kklazy.persistence.callback.AssembleUploadExcelCallBack;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.persistence.utils.FileUtils;
import kklazy.persistence.utils.StringUtils;
import kklazy.persistence.utils.export.ExcelUtils;
import kklazy.security.context.SecurityApplicationContext;
import ognl.OgnlException;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.STAFF_NAMESPACE)
public class StaffController extends BasePageController<Staff, String> {

	@Autowired
	private StaffService staffService;
	@Autowired
	private SynchronizeHrcDataService synchronizeHrcDataService;

	@Override
	public PageService<Staff, String> pageservice() {
		return staffService;
	}

	@Override
	public String path() {
		return "/webpages/newtouch/staff";
	}

	/* (non-Javadoc)
	 * @see kklazy.common.controller.BasePageController#searchhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultPageQueryModel, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Staff entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page<Staff> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getEmpno())) {
						criteria.add(Restrictions.eq("empno", entity.getEmpno()));
					}
					if (StringUtils.isNotBlank(entity.getName())) {
						criteria.add(Restrictions.like("name", entity.getName(), MatchMode.ANYWHERE));
					}
					if (StringUtils.isNotBlank(entity.getDepartment())) {
						entity.setDepartment(entity.getDepartment().toUpperCase());
						criteria.add(
							Restrictions.or(
								Restrictions.like("businessdepartment", entity.getDepartment(), MatchMode.ANYWHERE),
								Restrictions.like("department", entity.getDepartment(), MatchMode.ANYWHERE))
						);
					}
					if (StringUtils.isNotBlank(entity.getStatus())) {
						criteria.add(Restrictions.eq("status", entity.getStatus()));
					}
					if (entity instanceof DefaultModel) {
						criteria.add(Restrictions.eq("enabled", ((DefaultModel) entity).getEnabled()));
						criteria.add(Restrictions.eq("delete", ((DefaultModel) entity).getDelete()));
					}
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.DESC, "type"), new Sort.Order(Direction.ASC, "indate"))
				)
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
			modelMap.put("max", staffService.findMaxStatus());
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceltemplatehandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected AssembleDownloadExcelCallBack<Staff> exceltemplatehandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Staff entity) {
		return new AssembleDownloadExcelCallBack<Staff>() {
			@Override
			public List<Staff> assembleData(List<Staff> Data) {
				return Data;
			}
			@Override
			public String assembleSuffix() {
				return "xls";
			}
			@Override
			public Map<String, String> assembleTitle(Map<String, String> title) {
				title.put("empno", "工号[必填项]");
				title.put("identity", "身份证[只显示四位]");
				title.put("name", "姓名");
				title.put("businessdepartment", "事业部");
				title.put("department", "部门");
				title.put("email", "邮箱");
				title.put("indate", "进公司日期");
				title.put("area", "区域");
				title.put("status", "状态(离职填'0')");
				title.put("birthday", "生日");
				title.put("level", "职级");
				return title;
			}
		};
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceluploadhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, org.springframework.web.multipart.MultipartFile, kklazy.persistence.callback.AssembleUploadExcelCallBack)
	 */
	@Override
	protected CommonResponse exceluploadhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, MultipartFile file, AssembleUploadExcelCallBack callback) {
		CommonResponse retval = CommonResponse.FAILURE();
		String filename = file.getOriginalFilename();
		String suffix = FileUtils.suffix(filename);
		try {
			if (StringUtils.isBlank(suffix) || !StringUtils.inCollection(suffix, "xls", "xlsx")) {
				throw new IOException("suffix[ " + suffix + " ] error!");
			}
			List<Staff> entities = ExcelUtils.readExcel(callback.assembleIndex(), file.getInputStream(), suffix, clazz, callback.assembleColumn(new LinkedHashMap<Integer, String>()), null);
			staffService.importStaffs(entities);
			retval = CommonResponse.SUCCESS();
			retval.setMessage("文件上传成功,页面即将重新加载...");
			Logger.getLogger(getClass()).info("staff upload end.");
		} catch (IOException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件格式不正确哟,确认是Excel吗？~");
		} catch (IllegalArgumentException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件不对啦,真的是Excel吗?不要随便改个后缀来冒充哟~");
		} catch (OgnlException e) {
			e.printStackTrace();
			retval.setMessage("上传的文件中存在错误的数据~错误信息:" + e);
		} catch (Exception e) {
			e.printStackTrace();
			retval.setMessage("上传文件出现了难得一见的系统错误~错误信息:" + e);
		}
		return retval;
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultExcelController#exceluploadhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected AssembleUploadExcelCallBack exceluploadhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Logger.getLogger(getClass()).info("staff upload start.");
		return new AssembleUploadExcelCallBack() {
			@Override
			public Map<Integer, String> assembleColumn(Map<Integer, String> column) {
				column.put(0, "empno");
				column.put(1, "identity");
				column.put(2, "name");
				column.put(3, "businessdepartment");
				column.put(4, "department");
				column.put(5, "email");
				column.put(6, "indate");
				column.put(7, "area");
				column.put(8, "status");
				column.put(9, "birthday");
				column.put(10, "level");
				return column;
			}
		};
	}

	/**
	 * @param map
	 * @return
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.INIT)
	@ResponseBody
	public CommonResponse init(HttpServletRequest request, HttpServletResponse response, ModelMap map) {
		CommonResponse retval = CommonResponse.FAILURE();
		Logger.getLogger(getClass()).info("sync staff start.");
		try {
//			Iterable<Staff> staffs = staffService.findAll();
//			for (Staff staff : staffs) {
//				staff.setStatus(!"0".equals(staff.getStatus()) ? "1" : "0");
//				staff.setType(StringUtils.isBlank(staff.getType()) ? "0" : staff.getType());
//				staffService.merge(staff);
//			}
			synchronizeHrcDataService.syncEmployeesAndAccounts();
			retval = CommonResponse.SUCCESS();
			Logger.getLogger(getClass()).info("sync staff end.");
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

	/**
	 * @param operation
	 * @param map
	 * @return
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.OPERATION)
	@ResponseBody
	public CommonResponse operation(HttpServletRequest request, HttpServletResponse response, String empno, ModelMap map) {
		CommonResponse retval = CommonResponse.FAILURE();
		try {
			if (!"kk".equals(SecurityApplicationContext.getPassportDetails().getUsername())) {
				retval.setMessage("操作失败,你没有权限!");
				return retval;
			} else {
				staffService.operationGuest(empno);
				retval = CommonResponse.SUCCESS();
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return retval;
	}

}
