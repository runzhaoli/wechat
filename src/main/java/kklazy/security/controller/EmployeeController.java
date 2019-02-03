package kklazy.security.controller;

import java.util.HashSet;
import java.util.Set;

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
import kklazy.persistence.utils.MD5Utils;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.Employee;
import kklazy.security.model.Organization;
import kklazy.security.model.Role;
import kklazy.security.service.EmployeeService;
import kklazy.security.service.OrganizationService;
import kklazy.security.service.RoleService;
import kklazy.security.service.UserService;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.EMPLOYEE_NAMESPACE)
public class EmployeeController extends BasePageController<Employee, String> {

	public static final String ROLE_AUTHORIZE = "/roleauthorize";

	public static final String ROLE_AUTHORIZE_COMMIT = "/roleauthorizecommit";

	public static final String ORGANIZATION_AUTHORIZE = "/organizationauthorize";

	public static final String ORGANIZATION_AUTHORIZE_COMMIT = "/organizationauthorizecommit";

	public static final String ROLES = "roles";

	public static final String RESOURCES = "resources";

	public static final String ORGANIZATIONS = "organizations";

	@Autowired
	private UserService userService;

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private RoleService roleService;

	@Autowired
	private OrganizationService organizationService;

	@Override
	public PageService<Employee, String> pageservice() {
		return employeeService;
	}

	@Override
	public String path() {
		return "/webpages/security/employee";
	}

	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {

		modelMap.put(ORGANIZATIONS, organizationService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

				criteria.add(Restrictions.isNull("parent"));
				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}

		}, new Sort(new Sort.Order(Direction.ASC, "sort")) ));

		super.createhandler(request, response, modelMap);
	}

	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {

		modelMap.put(ORGANIZATIONS, organizationService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

				criteria.add(Restrictions.isNull("parent"));
				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}
			
		}, new Sort(new Sort.Order(Direction.ASC, "sort")) ));

		super.modifyhandler(request, response, modelMap, id);
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Employee entity) {
		Employee temp = service().findBy(entity.getId());

		if (temp != null) {
			entity.setRoles(temp.getRoles());
			entity.setData(temp.getData());
		}

		if (StringUtils.isBlank(entity.getId())) {
			entity.getUser().setEmployee(entity);
			entity.getUser().setPassword(MD5Utils.md5Hex(entity.getUser().getPassword() + "{" + entity.getUser().getUsername()  + "}"));
			userService.merge(entity.getUser());
		} else {
			return super.commithandler(request, response, modelMap, entity);
		}

		return CommonResponse.SUCCESS();

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
	@RequestMapping(value = EmployeeController.ROLE_AUTHORIZE + "/{id}")
	public String roleauthorize(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @PathVariable String id) throws Exception {

		modelMap.put(ENTITY, service().findBy(id));

		modelMap.put(ROLES, roleService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

//				criteria.add(Restrictions.isNull("parent"));

				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}

		}, new Sort(new Sort.Order(Direction.ASC, "sort")) ));

		return path() + EmployeeController.ROLE_AUTHORIZE;
	}

	/**
	 * 给指定用户授予多个角色权限
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param entity
	 * @param roleid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = EmployeeController.ROLE_AUTHORIZE_COMMIT)
	public CommonResponse roleauthorizecommit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Employee entity, String[] roleid) throws Exception {
		CommonResponse retval = CommonResponse.FAILURE();

		if (entity == null) {
			return retval;
		}

		try {

			entity = service().findBy(entity.getId());

			Set<Role> roles = new HashSet<Role>();
			if (roleid != null && roleid.length > 0) {
				for (String id : roleid) {
					if (StringUtils.isNotBlank(id)) {
						roles.add(roleService.findBy(id));	
					}
				}
			}

			entity.setRoles(roles);
			service().merge(entity);

			retval = CommonResponse.SUCCESS();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

		return retval;

	}

	/**
	 * 打开数据(机构)授权页面
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = EmployeeController.ORGANIZATION_AUTHORIZE + "/{id}")
	public String dataauthorize(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, @PathVariable String id) throws Exception {

		modelMap.put(ENTITY, service().findBy(id));

		modelMap.put(ORGANIZATIONS, organizationService.findBy(new AssembleCriteriaParamsCallBack() {

			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {

				criteria.add(Restrictions.isNull("parent"));

				criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
				criteria.add(Restrictions.eq("delete", Boolean.FALSE));

				return criteria;
			}

		}, new Sort(new Sort.Order(Direction.ASC, "sort")) ));

		return path() + EmployeeController.ORGANIZATION_AUTHORIZE;
	}

	/**
	 * 给指定用户授予多个数据(机构)权限
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param entity
	 * @param organizationid
	 * @return
	 * @throws Exception
	 */
	@ResponseBody
	@RequestMapping(value = EmployeeController.ORGANIZATION_AUTHORIZE_COMMIT)
	public CommonResponse dataauthorizecommit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Employee entity, String[] organizationid) throws Exception {
		CommonResponse retval = CommonResponse.FAILURE();

		if (entity == null) {
			return retval;
		}

		try {

			entity = service().findBy(entity.getId());

			Set<Organization> organizations = new HashSet<Organization>();
			if (organizationid != null && organizationid.length > 0) {
				for (String id : organizationid) {
					if (StringUtils.isNotBlank(id)) {
						organizations.add(organizationService.findBy(id));	
					}
				}
			}

			entity.setData(organizations);
			service().merge(entity);

			retval = CommonResponse.SUCCESS();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);

		}

		return retval;

	}

}
