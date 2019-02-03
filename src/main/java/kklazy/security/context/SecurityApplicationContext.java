/**
 * 
 */
package kklazy.security.context;

import kklazy.security.model.Employee;
import kklazy.security.vo.PassportDetails;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * @author Kui
 */
public class SecurityApplicationContext {
	
	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public static PassportDetails getPassportDetails() {
		try {
			Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
			if (authentication != null && authentication.getPrincipal() instanceof PassportDetails) {
				return (PassportDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
			}
		} catch (Exception e) {
			Logger.getLogger(new SecurityApplicationContext().getClass()).error("Get Passport Details Exception", e);
		}
		return null;
	}
	
	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public static Employee getEmployee() {
		PassportDetails details = getPassportDetails();
		if (details != null) {
			return details.getEmployee();
		}
		return null;
	}

	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public static String getName() {
		Employee employee = getEmployee();
		if (employee != null) {
			return employee.getName();
		}
		return null;
	}
	
	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public static String getUsername() {
		PassportDetails details = getPassportDetails();
		if (details != null) {
			return details.getUsername();
		}
		return null;
	}
	
}
