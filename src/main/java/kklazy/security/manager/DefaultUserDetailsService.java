/**
 * 
 */
package kklazy.security.manager;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.MessageSource;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import kklazy.security.service.UserService;
import kklazy.security.vo.PassportDetails;

/**
 * @author Kui
 */
@Service("userDetailsService")
public class DefaultUserDetailsService implements UserDetailsService {
	
	@Autowired
	private MessageSource messageSource;

    @Autowired
	private UserService userService;

	/**
	 * @see org.springframework.security.core.userdetails.UserDetailsService#loadUserByUsername(java.lang.String)
	 */
	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		MessageSourceAccessor messages;
		
		Logger.getLogger(DefaultUserDetailsService.class).debug(new StringBuffer("Method: loadUserByUsername(); ").append("Username: " + username + "; ").toString());

		kklazy.security.model.User user = userService.findByUsername(username);
		
		if (user == null) {
			messages = new MessageSourceAccessor(messageSource);
			throw new UsernameNotFoundException(messages.getMessage("DefaultUserDetailsService.usernameNotFound", new Object[] { username }, "Username {0} not found"));
		}
		
		kklazy.security.vo.PassportDetails details = new PassportDetails();
		
		details.setUsername(user.getUsername());
		details.setPassword(user.getPassword());
		details.setEmployee(user.getEmployee());
		
//		"admin", "ceb4f32325eda6142bd65215f4c0f371"
//		return new org.springframework.security.core.userdetails.User(user.getUnitcode(), user.getPassword(), authorities);
		return details;
	}

}
