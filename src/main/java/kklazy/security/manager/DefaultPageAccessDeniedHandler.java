/**
 * 
 */
package kklazy.security.manager;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import kklazy.persistence.utils.StringUtils;

import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.web.WebAttributes;
import org.springframework.security.web.access.AccessDeniedHandlerImpl;

/**
 * @author Kui
 */
public class DefaultPageAccessDeniedHandler extends AccessDeniedHandlerImpl {
	
	/**
	 * 
	 */
	private String accessDeniedPage;
	
	/**
	 * 
	 * @see org.springframework.security.web.access.AccessDeniedHandlerImpl#handle(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.access.AccessDeniedException)
	 */
	public void handle(HttpServletRequest request, HttpServletResponse response, AccessDeniedException accessDeniedException) throws IOException, ServletException {
		if (!response.isCommitted()) {
			if (StringUtils.isNotBlank(accessDeniedPage)) {
				// Put exception into request scope (perhaps of use to a view)
				request.setAttribute(WebAttributes.ACCESS_DENIED_403, accessDeniedException);

				// Set the 403 status code.
				response.setStatus(HttpServletResponse.SC_FORBIDDEN);

				response.sendRedirect(request.getContextPath() + accessDeniedPage);
			} else {
				response.sendError(HttpServletResponse.SC_FORBIDDEN, accessDeniedException.getMessage());
			}
			super.handle(request, response, accessDeniedException);
		}
	}

	/**
	 * @param accessDeniedPage the accessDeniedPage to set
	 */
	public void setAccessDeniedPage(String accessDeniedPage) {
		this.accessDeniedPage = accessDeniedPage;
	}

}
