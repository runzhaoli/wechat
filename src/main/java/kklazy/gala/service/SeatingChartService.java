package kklazy.gala.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.gala.entity.SeatingChart;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;

/**
 * 
 */
@Service("seatingChartService")
@Transactional(rollbackFor = Exception.class)
public class SeatingChartService extends DefaultService<SeatingChart, String> {

	/**
	 * @param name
	 * @return
	 */
	public List<SeatingChart> findByName(String name, String empno) {
		if (StringUtils.isBlank(name) && StringUtils.isBlank(empno)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		if (StringUtils.isNotBlank(name)) {
			criteria.add(Restrictions.or(
				Restrictions.eq("a", name),
				Restrictions.eq("c", name),
				Restrictions.eq("e", name),
				Restrictions.eq("g", name),
				Restrictions.eq("i", name),
				Restrictions.eq("k", name),
				Restrictions.eq("m", name),
				Restrictions.eq("o", name),
				Restrictions.eq("q", name),
				Restrictions.eq("s", name),
				Restrictions.eq("u", name),
				Restrictions.eq("w", name),
				Restrictions.eq("y", name)
			));
		}
		if (StringUtils.isNotBlank(empno)) {
			criteria.add(Restrictions.or(
				Restrictions.eq("b", empno),
				Restrictions.eq("d", empno),
				Restrictions.eq("f", empno),
				Restrictions.eq("h", empno),
				Restrictions.eq("j", empno),
				Restrictions.eq("l", empno),
				Restrictions.eq("n", empno),
				Restrictions.eq("p", empno),
				Restrictions.eq("r", empno),
				Restrictions.eq("t", empno),
				Restrictions.eq("v", empno),
				Restrictions.eq("x", empno),
				Restrictions.eq("z", empno)
			));
		}
		return findBy(criteria);
	}

}
