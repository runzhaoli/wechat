package kklazy.common.controller;

import java.io.Serializable;
import java.io.UnsupportedEncodingException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;

import kklazy.common.constants.ContextConstants;
import kklazy.persistence.controller.DefaultExcelController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.security.manager.DefaultFilterInvocationSecurityMetadataSource;
import kklazy.security.model.Dictionary;
import kklazy.security.service.ResourceService;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author kk
 *
 * @param <T>
 * @param <Pk>
 */
public abstract class BasePageController<T extends DefaultModel, Pk extends Serializable> extends DefaultExcelController<T, Pk> {

	public static final String RESOURCE = "resource";
	public static final String NAMESPACE = "namespace";
	
	public static final String PASSPORT_DETAILS = "passport";

	@Autowired
	private ResourceService resourceService;

	/**
	 * @return
	 */
	public SecurityContext getSecurityContext() {
		return SecurityContextHolder.getContext();
	}

	/**
	 * @param query
	 * @return
	 */
	protected DefaultPageQueryModel assembleQuery(DefaultPageQueryModel query) {

		if (query == null) {
			query = new DefaultPageQueryModel();
		}

		if (query.getPageindex() == 0) {
			Dictionary pagesize = DictionaryUtils.getDictionaryByCode(ContextConstants.GROUP.PAGE_SIZE, ContextConstants.PAGE_SIZE.A);
			if (pagesize != null && query.getPagesize() < Integer.parseInt(pagesize.getValue())) {
				query.setPagesize(Integer.parseInt(pagesize.getValue()));
			}
		}

		return query;
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, T entity) {
		super.searchhandler(request, response, modelMap, assembleQuery(query), entity);
	}

	/**
	 * @param request
	 * @param response
	 * @throws UnsupportedEncodingException
	 */
	@ModelAttribute
	public void prepare(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws UnsupportedEncodingException {

		request.setCharacterEncoding("UTF-8");
		response.setCharacterEncoding("UTF-8");

		modelMap.put(PASSPORT_DETAILS, SecurityApplicationContext.getPassportDetails());

		putConfig(request, response, modelMap);
	}

	/**
	 * 获取当前菜单信息以及NAMESPACE信息
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 */
	public void putConfig(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		if (request.getServletPath().indexOf("commit") != -1) {
			return;
		}
		if (request.getServletPath().indexOf("base") != -1) {
			return;
		}

		try {
			String url = DefaultFilterInvocationSecurityMetadataSource.filterOperation(request.getServletPath());
			modelMap.put(RESOURCE, resourceService.findByUrl(url));
			if (url.lastIndexOf("/") != 0) {
				modelMap.put(NAMESPACE, url.substring(0, url.lastIndexOf("/") + 1));
			}
		} catch (IndexOutOfBoundsException e) {
			logger.debug(e.getLocalizedMessage(), e);
		}

	}


}
