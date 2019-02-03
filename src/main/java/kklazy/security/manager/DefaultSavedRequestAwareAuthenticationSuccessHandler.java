/**
 * 
 */
package kklazy.security.manager;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;

import kklazy.persistence.support.CommonResponse;

/**
 * @author kk
 *
 */
public class DefaultSavedRequestAwareAuthenticationSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {

	/**
	 * @see org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler#onAuthenticationSuccess(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.security.core.Authentication)
	 */
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response, Authentication authentication) throws ServletException, IOException {
		if ("XMLHttpRequest".equals(request.getHeader("x-requested-with"))) {
			
			CommonResponse retval = CommonResponse.SUCCESS();
			Map<String, Object> body = new HashMap<String, Object>();
			body.put("url", this.getDefaultTargetUrl());
			retval.setBody(body);
			
			response.setContentType("application/json");
			PrintWriter out = response.getWriter();
			out.print(retval);
			out.flush();
			out.close();
		} else {
			super.onAuthenticationSuccess(request, response, authentication);
		}
	}

}
