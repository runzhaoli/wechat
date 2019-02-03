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
import kklazy.security.model.Dictionary;

/**
 * @author Kui
 */
@Service("dictionaryService")
@Transactional(rollbackFor = Exception.class)
public class DictionaryService extends DefaultService<Dictionary, String> {

	/**
	 * @param group
	 * @return
	 * 
	 * @author Kui
	 */
	public List<Dictionary> findByGroup(String group) {

		if (StringUtils.isBlank(group)) {
			return null;
		}

		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("group", group));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));

		return findBy(criteria);
	}

}
