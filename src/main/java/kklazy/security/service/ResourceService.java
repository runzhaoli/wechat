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
import kklazy.security.model.Resource;

/**
 * @author Kui
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class ResourceService extends DefaultService<Resource, String> {

	/**
	 * @param url
	 * @return
	 */
	public Resource findByUrl(String url) {
		if (StringUtils.isBlank(url)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("url", url));

		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));

		List<Resource> resources = findBy(criteria);
		if (resources != null && !resources.isEmpty()) {
			return resources.get(0);
		} else {
			return null;
		}
	}

}
