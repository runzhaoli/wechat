/**
 * 
 */
package kklazy.security.filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import kklazy.security.exception.DefaultAuthenticationException;
import kklazy.persistence.utils.StringUtils;

import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.util.TextEscapeUtils;

/**
 * @author Kui
 */
public class VerifyUsernamePasswordAuthenticationFilter extends UsernamePasswordAuthenticationFilter {

    public static final String SPRING_SECURITY_FORM_VERIFY_KEY = "j_verify";
	
	private String verifyParameter = SPRING_SECURITY_FORM_VERIFY_KEY;
	private Boolean postOnly = Boolean.TRUE;
	
	/* (non-Javadoc)
	 * @see org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter#attemptAuthentication(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	@Override
	@SuppressWarnings("deprecation")
	public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException {
		// TODO Auto-generated method stub

		if (postOnly && !request.getMethod().equals("POST")) {
			throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
		}

		String username = StringUtils.trim(obtainUsername(request));
		String password = StringUtils.trim(obtainPassword(request));
		String verify   = StringUtils.trim(obtainVerify(request));

		if (StringUtils.isBlank(username)) {
			throw new DefaultAuthenticationException(messages.getMessage("VerifyUsernamePasswordAuthenticationFilter.emptyUsername", "Empty username not allowed"));
		}

		// Place the last username attempted into HttpSession for views
		HttpSession session = request.getSession(false);
		if (session != null || getAllowSessionCreation()) {
			request.getSession().setAttribute(SPRING_SECURITY_LAST_USERNAME_KEY, TextEscapeUtils.escapeEntities(username));
		}

		if (StringUtils.isBlank(password)) {
			throw new DefaultAuthenticationException(messages.getMessage("VerifyUsernamePasswordAuthenticationFilter.emptyPassword", "Empty password not allowed"));
		}

		if (StringUtils.isBlank(verify)) {
			throw new DefaultAuthenticationException(messages.getMessage("VerifyUsernamePasswordAuthenticationFilter.emptyVerify", "Empty verify not allowed"));
		}

		UsernamePasswordAuthenticationToken authRequest = new UsernamePasswordAuthenticationToken(username, password);
		
		if (!checkVerify(request)) {
			throw new DefaultAuthenticationException(messages.getMessage("VerifyUsernamePasswordAuthenticationFilter.badVerify", "Bad Verify"));
		}

		// Allow subclasses to set the "details" property
		setDetails(request, authRequest);

		return this.getAuthenticationManager().authenticate(authRequest);
		
	}
	
	/**
	 * @param request
	 * @return
	 * 
	 * @author Kui
	 */
	protected Boolean checkVerify(HttpServletRequest request) {
		String parameterVerify = StringUtils.trim(obtainVerify(request));
		String sessionVerify   = StringUtils.trim(sessionVerify(request));
		
		if (StringUtils.isNotBlank(sessionVerify) && StringUtils.isNotBlank(parameterVerify)) {
			if (sessionVerify.equals(parameterVerify)) {
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}
	
	/**
	 * @param request
	 * @return
	 * 
	 * @author Kui
	 */
	protected String obtainVerify(HttpServletRequest request) {
		return request.getParameter(verifyParameter);
	}
	
	/**
	 * @param request
	 * @return
	 * 
	 * @author Kui
	 */
	protected String sessionVerify(HttpServletRequest request) {
		return (String) request.getSession().getAttribute(com.google.code.kaptcha.Constants.KAPTCHA_SESSION_KEY);
	}
	
}
