package kklazy.security.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.SystemConfig;

@Service("systemConfigService")
@Transactional(rollbackFor = Exception.class)
public class SystemConfigService extends DefaultService<SystemConfig, String> {

	/**
	 * @param group
	 * @return
	 * 
	 * @author Kui
	 */
	public List<SystemConfig> findByKey(String key) {

		if (StringUtils.isBlank(key)) {
			return null;
		}
		
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("key", key));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		
		return findBy(criteria);
	}
	
}
