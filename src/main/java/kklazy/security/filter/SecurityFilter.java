/**
 * 
 */
package kklazy.security.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.access.SecurityMetadataSource;
import org.springframework.security.access.intercept.AbstractSecurityInterceptor;
import org.springframework.security.access.intercept.InterceptorStatusToken;
import org.springframework.security.web.FilterInvocation;
import org.springframework.security.web.access.intercept.FilterInvocationSecurityMetadataSource;

/**
 * @author Kui
 */
public class SecurityFilter extends AbstractSecurityInterceptor implements Filter {

	/**
	 * 
	 */
	private FilterInvocationSecurityMetadataSource securityMetadataSource;

	/*
	 * (non-Javadoc)
	 * 
	 * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#obtainSecurityMetadataSource()
	 */
	@Override
	public SecurityMetadataSource obtainSecurityMetadataSource() {
		Logger.getLogger(getClass()).debug("SecurityFilter_obtainSecurityMetadataSource");
		return this.securityMetadataSource;
	}

	/*
	 * 
	 * @see javax.servlet.Filter#init(javax.servlet.FilterConfig)
	 */
	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("SecurityFilter_init");

	}

	/*
	 * 
	 * @see javax.servlet.Filter#doFilter(javax.servlet.ServletRequest, javax.servlet.ServletResponse, javax.servlet.FilterChain)
	 */
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
		Logger.getLogger(getClass()).debug("doFilter");
		
		FilterInvocation invocation  = new FilterInvocation(request, response, chain);
		InterceptorStatusToken token = super.beforeInvocation(invocation);
		
		try {
			invocation.getChain().doFilter(invocation.getRequest(), invocation.getResponse());
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			super.afterInvocation(token, null);
		}
	
	}

	/*
	 * 
	 * @see javax.servlet.Filter#destroy()
	 */
	@Override
	public void destroy() {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("SecurityFilter_destroy");

	}

	/*
	 * 
	 * @see org.springframework.security.access.intercept.AbstractSecurityInterceptor#getSecureObjectClass()
	 */
	@Override
	public Class<?> getSecureObjectClass() {
		// TODO Auto-generated method stub
		Logger.getLogger(getClass()).debug("SecurityFilter_getSecureObjectClass");

		return FilterInvocation.class;
	}

	/**
	 * @return the securityMetadataSource
	 */
	public FilterInvocationSecurityMetadataSource getSecurityMetadataSource() {
		return securityMetadataSource;
	}

	/**
	 * @param securityMetadataSource the securityMetadataSource to set
	 */
	public void setSecurityMetadataSource(FilterInvocationSecurityMetadataSource securityMetadataSource) {
		this.securityMetadataSource = securityMetadataSource;
	}

}
