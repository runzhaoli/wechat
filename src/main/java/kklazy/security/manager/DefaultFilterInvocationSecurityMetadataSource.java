/**
 * 
 */
package kklazy.security.manager;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;
import org.springframework.stereotype.Service;

import kklazy.common.constants.MappingConstants;
import kklazy.persistence.constants.DefaultMappingConstants;
import kklazy.security.controller.KaptchaController;
import kklazy.security.controller.RoleController;
import kklazy.security.controller.SecurityController;
import kklazy.security.controller.TemplateController;
import kklazy.security.model.Resource;
import kklazy.security.model.Role;
import kklazy.security.service.ResourceService;

/**
 * @author Kui
 */
@Service("securityMetadataSource")
public class DefaultFilterInvocationSecurityMetadataSource implements FilterInvocationSecurityMetadataSource {

	@Autowired
	private ResourceService resourceService;

	private static final List<String> irregularity = new ArrayList<String>();
	private static final Map<String, Collection<ConfigAttribute>> MAP = new HashMap<String, Collection<ConfigAttribute>>();

	static {
		irregularity.add("/" + MappingConstants.NameSpaces.SECURITY_NAMESPACE + "/" + SecurityController.LOGIN);
		irregularity.add("/" + MappingConstants.NameSpaces.KAPTCHA_NAMESPACE  + "/" + KaptchaController.VERIFY);
		irregularity.add("/" + MappingConstants.NameSpaces.TEMPLATE_NAMESPACE + "/" + TemplateController.BASE);
		irregularity.add("/" + MappingConstants.NameSpaces.TEMPLATE_NAMESPACE + "/" + TemplateController.FRAMEWORK);
		irregularity.add("/" + MappingConstants.NameSpaces.TEMPLATE_NAMESPACE + "/" + TemplateController.MOBILE);
		irregularity.add("/" + MappingConstants.NameSpaces.TEMPLATE_NAMESPACE + "/" + TemplateController.WEUI);
	}

	/**
	 * @see org.springframework.security.access.SecurityMetadataSource#getAttributes(java.lang.Object)
	 */
	@Override
	public Collection<ConfigAttribute> getAttributes(Object object) throws IllegalArgumentException {

		FilterInvocation invocation = (FilterInvocation) object;

		Logger.getLogger(getClass()).debug(new StringBuffer("Method: getAttributes(); ").append(invocation.getClass().getName() + ": " + invocation.getFullRequestUrl()).toString());

		String url = invocation.getRequest().getServletPath();

		for (String u : irregularity) {
			if (u.equals(url)) {
				// 返回 null，跳过验证
				return null;
			}
		}

		synchronized (MAP) {

			url = filterOperation(url);

			Logger.getLogger(getClass()).debug("url: " + url);

			Collection<ConfigAttribute> attributes = MAP.get(url);
			if (attributes != null) {
				return attributes;
			}

			Resource resource = resourceService.findByUrl(url);
			if (resource != null) {
				attributes = new ArrayList<ConfigAttribute>();
				for (Role role : resource.getRoles()) {
					attributes.add(new SecurityConfig(role.getCode()));
				}
			}

			if (attributes == null || attributes.isEmpty()) {
				attributes = new ArrayList<ConfigAttribute>();
				attributes.add(new SecurityConfig("ROLE_ACCESSDENIED"));
			}

			MAP.put(url, attributes);

			return attributes;
		}

	}
	
	/**
	 * 过滤(修改、逻辑删除、物理删除、查看详情)操作所带的uuid
	 * 
	 * @param url
	 * @return
	 */
	public static String filterOperation(String url) {

		if (StringUtils.isBlank(url)) {
			return url;
		}

		if (url.indexOf(DefaultMappingConstants.CREATE + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		if (url.indexOf(DefaultMappingConstants.MODIFY + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		if (url.indexOf(DefaultMappingConstants.DELETE + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		if (url.indexOf(DefaultMappingConstants.REMOVE + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		if (url.indexOf(DefaultMappingConstants.DETAIL + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		if (url.indexOf(RoleController.AUTHORIZE + "/") != -1) {
			url = url.substring(0, url.lastIndexOf("/"));
		}

		return url;
	}

	/**
	 * 
	 * 
	 * @author Kui
	 */
	public void clean() {
		MAP.clear();
	}

	/**
	 * @see org.springframework.security.access.SecurityMetadataSource#getAllConfigAttributes()
	 */
	@Override
	public Collection<ConfigAttribute> getAllConfigAttributes() {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("SecurityMetadataSource_getAllConfigAttributes");
		List<ConfigAttribute> attributes = new ArrayList<ConfigAttribute>();
		return attributes;
	}

	/**
	 * @see org.springframework.security.access.SecurityMetadataSource#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("SecurityMetadataSource_supports");
		return true;
	}

}
