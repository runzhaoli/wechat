/**
 * 
 */
package kklazy.security.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.User;

/**
 * @author Kui
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class UserService extends DefaultService<User, String> {

	/**
	 * @param username
	 * @return
	 */
	public User findByUsername(String username) {
		if (StringUtils.isBlank(username)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("username", username));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		List<User> users = findBy(criteria);
		if (users != null && !users.isEmpty()) {
			return users.get(0);
		} else {
			return null;
		}
	}

}
