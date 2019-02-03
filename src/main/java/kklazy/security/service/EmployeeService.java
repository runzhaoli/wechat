package kklazy.security.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.Employee;

/**
 * @author Kui
 */
@Service
@Transactional(rollbackFor = Exception.class)
public class EmployeeService extends DefaultService<Employee, String> {

	/**
	 * @param empno
	 * @return
	 */
	public Employee findByEmpno(String empno) {
		if (StringUtils.isBlank(empno)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		DetachedCriteria user = criteria.createCriteria("user");
		user.add(Restrictions.eq("username", empno));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		List<Employee> employees = findBy(criteria);
		if (employees != null && !employees.isEmpty()) {
			return employees.get(0);
		}
		return null;
	}

}
