/**
 * 
 */
package kklazy.security.manager;

import java.util.Collection;
import java.util.Iterator;

import kklazy.persistence.utils.StringUtils;

import org.apache.log4j.Logger;
import org.springframework.security.access.AccessDecisionManager;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.access.ConfigAttribute;
import org.springframework.security.access.SecurityConfig;
import org.springframework.security.authentication.InsufficientAuthenticationException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.web.FilterInvocation;
import org.springframework.stereotype.Service;

/**
 * @author Kui
 */
@Service("accessDecisionManager")
public class DefaultAccessDecisionManager implements AccessDecisionManager {

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#decide(org.springframework.security.core.Authentication, java.lang.Object, java.util.Collection)
	 */
	@Override
	public void decide(Authentication authentication, Object object, Collection<ConfigAttribute> configAttributes) throws AccessDeniedException, InsufficientAuthenticationException {

		FilterInvocation invocation = (FilterInvocation) object;

		StringBuffer buffer = new StringBuffer();
		buffer.append("Username: " + authentication.getName() + "; ");
		buffer.append("RequestUrl: " + invocation.getRequestUrl() + "; ");
		
		Logger.getLogger(getClass()).info(buffer.toString());
		// 
		if (configAttributes == null || configAttributes.isEmpty()) {
			throw new AccessDeniedException("Access Denied !!!");
		}
		
		Iterator<ConfigAttribute> iterator = configAttributes.iterator();
		while (iterator.hasNext()) {
			
			ConfigAttribute attribute = (ConfigAttribute) iterator.next();
			String role = ((SecurityConfig) attribute).getAttribute();
			
			for (GrantedAuthority authority: authentication.getAuthorities()) {
				if (StringUtils.trim(role).equals(StringUtils.trim(authority.getAuthority()))) {
					
					return ;
				}
			}
		}

		throw new AccessDeniedException("Access Denied !!!");
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(org.springframework.security.access.ConfigAttribute)
	 */
	@Override
	public boolean supports(ConfigAttribute attribute) {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("DefaultAccessDecisionManager_supports_1");
		return true;
	}

	/* (non-Javadoc)
	 * @see org.springframework.security.access.AccessDecisionManager#supports(java.lang.Class)
	 */
	@Override
	public boolean supports(Class<?> clazz) {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("DefaultAccessDecisionManager_supports_2");
		return true;
	}

}
