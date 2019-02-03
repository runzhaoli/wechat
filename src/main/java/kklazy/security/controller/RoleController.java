package kklazy.security.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.security.manager.DefaultFilterInvocationSecurityMetadataSource;
import kklazy.security.model.Resource;
import kklazy.security.model.Role;
import kklazy.security.service.ResourceService;
import kklazy.security.service.RoleService;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.ROLE_NAMESPACE)
public class RoleController extends BasePageController<Role, String> {

	public static final String AUTHORIZE = "authorize";

	public static final String RESOURCE_AUTHORIZE = "/resourceauthorize";

	public static final String RESOURCE_AUTHORIZE_COMMIT = "/resourceauthorizecommit";

	public static final String RESOURCES = "resources";

	@Autowired
	private RoleService roleService;

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private DefaultFilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public PageService<Role, String> pageservice() {
		return roleService;
	}

	@Override
	public String path() {
		return "/webpages/security/role";
	}

	@Override
	protected void detailhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {

		modelMap.put(RESOURCES, resourceService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

				criteria.add(Restrictions.isNull("parent"));

				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}

		}, new Sort(new Sort.Order(Direction.ASC, "sort"))));

		super.detailhandler(request, response, modelMap, id);
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Role entity) {
		Role temp = service().findBy(entity.getId());

		if (temp != null) {
			entity.setEmployees(temp.getEmployees());
			entity.setResources(temp.getResources());

			entity.setModifier(SecurityApplicationContext.getName());
		} else {
			entity.setCreator(SecurityApplicationContext.getName());
		}

		return super.commithandler(request, response, modelMap, entity);
	}

	/**
	 * 打开角色授权页面
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = RoleController.RESOURCE_AUTHORIZE + "/{id}")
	public String authorize(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @PathVariable String id) throws Exception {

		modelMap.put(ENTITY, service().findBy(id));

		modelMap.put(RESOURCES, resourceService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

				criteria.add(Restrictions.isNull("parent"));

				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}

		}, new Sort(new Sort.Order(Direction.ASC, "sort"))));

		return path() + RoleController.RESOURCE_AUTHORIZE;
	}

	/**
	 * 给指定角色授予多个资源权限
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param entity
	 * @param resourceid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = RoleController.RESOURCE_AUTHORIZE_COMMIT)
	public CommonResponse authorizecommit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Role entity, String[] resourceid) throws Exception {
		CommonResponse retval = CommonResponse.FAILURE();

		if (entity == null) {
			return retval;
		}

		try {

			entity = roleService.findBy(entity.getId());

			List<Resource> resources = new ArrayList<Resource>();
			if (resourceid != null && resourceid.length > 0) {
				for (String r : resourceid) {
					if (StringUtils.isNotBlank(r)) {
						resources.add(resourceService.findBy(r));
					}
				}
			}

			entity.setResources(resources);
			roleService.merge(entity);

			securityMetadataSource.clean();

			retval = CommonResponse.SUCCESS();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return retval;

	}

}
