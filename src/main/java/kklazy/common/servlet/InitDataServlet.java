package kklazy.common.servlet;

import java.util.List;
import java.util.Set;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;

import org.apache.log4j.Logger;

import kklazy.common.data.InitializationData;
import kklazy.security.model.Dictionary;
import kklazy.security.model.Employee;
import kklazy.security.model.Organization;
import kklazy.security.model.Resource;
import kklazy.security.model.Role;
import kklazy.security.model.User;
import kklazy.security.service.DictionaryService;
import kklazy.security.service.OrganizationService;
import kklazy.security.service.ResourceService;
import kklazy.security.service.RoleService;
import kklazy.security.service.UserService;
import kklazy.utils.ApplicationContextUtils;
import kklazy.weixin.service.WechatAccountService;

public class InitDataServlet extends HttpServlet {

	/**
	 * 
	 */
	private DictionaryService dictionaryService;
	
	private UserService userService;
	private OrganizationService organizationService;
	private ResourceService resourceService;
	private RoleService roleService;
	private WechatAccountService wechatAccountService;
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -3957104641634269460L;
	
	/**
	 * @throws ServletException
	 */
	public void initDictionary() {

		if (dictionaryService == null) {
			dictionaryService = (DictionaryService) ApplicationContextUtils.getBean("dictionaryService");
		}
		
		Iterable<Dictionary> dictionaries = dictionaryService.findAll();

		for (Dictionary dictionary : dictionaries) {
			if (dictionary != null) {
				return ;
			}
		}

		@SuppressWarnings("unchecked")
		List<Dictionary> temps = (List<Dictionary>) InitializationData.data.get(InitializationData.DICTIONARY);
		
		for (Dictionary dictionary : temps) {
			dictionaryService.persist(dictionary);
		}
	}
	
	@Override
	public void init() throws ServletException {
		
		try {
			initUser();
		} catch (Exception e) {
			e.printStackTrace();
		}

		try {
			initDictionary();
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		Logger.getLogger(getClass()).info("data initialization is complate...");
		
		super.init();
	}
	
	/**
	 * @throws Exception
	 */
	public void initUser() {

		try {
			if (userService == null) {
				userService = (UserService) ApplicationContextUtils.getBean("userService");
			}
			
			Iterable<User> users = userService.findAll();
			
			for (User user : users) {
				if (user != null) {
					return ;
				}
			}
			
			User user = (User) InitializationData.data.get(InitializationData.USER);
	
			if (organizationService == null) {
				organizationService = (OrganizationService) ApplicationContextUtils.getBean("organizationService");
			}
			if (resourceService == null) {
				resourceService = (ResourceService) ApplicationContextUtils.getBean("resourceService");
			}
			if (roleService == null) {
				roleService = (RoleService) ApplicationContextUtils.getBean("roleService");
			}
			if (wechatAccountService == null) {
				wechatAccountService = (WechatAccountService) ApplicationContextUtils.getBean("wechatAccountService");
			}
		
			Employee     employee     = user.getEmployee();
			Organization organization = employee.getOrganization();
			Set<Role>    roles        = employee.getRoles();
			
			organizationService.persist(organization);
			employee.setOrganization(organization);
		
			for (Role role : roles) {
				List<Resource> resources = role.getResources();
				if (resources != null && !resources.isEmpty()) {
					for (Resource resource : resources) {
						resourceService.merge(resource);
						
						List<Resource> sublevels = resource.getSublevel();
						if (sublevels != null && !sublevels.isEmpty()) {
							for (Resource sublevel : sublevels) {
								sublevel.setParent(resource);
								resourceService.persist(sublevel);
							}
						}
					}
				}
				roleService.persist(role);
			}
	
			userService.persist(user);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
	}

}
