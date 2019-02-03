package kklazy.newtouch.service;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.newtouch.model.Staff;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.MD5Utils;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.security.model.Employee;
import kklazy.security.model.Role;
import kklazy.security.model.User;
import kklazy.security.service.EmployeeService;
import kklazy.security.service.UserService;

@Service("staffService")
@Transactional(rollbackFor = Exception.class)
public class StaffService extends DefaultService<Staff, String> {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private UserService userService;

	/**
	 * @param empno
	 * @return
	 * 
	 */
	public Staff findByEmpno(String empno) {
		if (StringUtils.isBlank(empno)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("empno", empno));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		List<Staff> staffs = findBy(criteria);
		if (staffs != null && !staffs.isEmpty()) {
			return staffs.get(0);
		}
		return null;
	}

	/**
	 * @param empno
	 * @return
	 * 
	 */
	public Staff findByEmpnoAndIdentity(String empno, String identity) {
		if (StringUtils.isBlank(empno) || StringUtils.isBlank(identity)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("empno", empno));
		criteria.add(Restrictions.eq("identity", identity));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		List<Staff> staffs = findBy(criteria);
		if (staffs != null && !staffs.isEmpty()) {
			return staffs.get(0);
		}
		return null;
	}

	/**
	 * @return
	 */
	public String findMaxStatus() {
		String retval = "0";
		StringBuilder sql = new StringBuilder();
		sql.append(" SELECT MAX(STATUS) FROM NEWTOUCH_STAFF ");
		@SuppressWarnings("unchecked")
		List<String> result = (List<String>) findByNativeSql(sql);
		if (result != null && !result.isEmpty()) {
			if (!retval.equals(result.get(0))) {
				retval = result.get(0);
			}
		}
		return retval;
	}

	/**
	 * @param staffs
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> importStaffs(List<Staff> staffs) throws Exception {
		Map<String, Integer> retval = new HashMap<String, Integer>();
		int success = 0;
		int fail	= 0;

		String status = findMaxStatus();
		status = String.valueOf(Long.valueOf(status == null ? "0" : status) + 1);

		for (Staff staff : staffs) {
			if (StringUtils.isBlank(staff.getEmpno())) {
				continue ;
			}
			try {
				Staff temp = findByEmpno(staff.getEmpno());
				if (temp != null) {
					if (StringUtils.isNotBlank(staff.getIdentity())) {
						temp.setIdentity(staff.getIdentity());
					}
					if (StringUtils.isNotBlank(staff.getName())) {
						temp.setName(staff.getName());
					}
					if (StringUtils.isNotBlank(staff.getBusinessdepartment())) {
						temp.setBusinessdepartment(staff.getBusinessdepartment());
					}
					if (StringUtils.isNotBlank(staff.getDepartment())) {
						temp.setDepartment(staff.getDepartment());
					}
					if (StringUtils.isNotBlank(staff.getEmail())) {
						temp.setEmail(staff.getEmail());
					}
					if (staff.getIndate() != null) {
						temp.setIndate(staff.getIndate());
					}
					if (StringUtils.isNotBlank(staff.getArea())) {
						temp.setArea(staff.getArea());
					}
					if (StringUtils.isNotBlank(staff.getStatus())) {
						temp.setStatus(staff.getStatus());
					} else {
						if (!"0".equals(temp.getStatus())) {
							temp.setStatus(status);
						}
					}
					if (staff.getBirthday() != null) {
						temp.setBirthday(staff.getBirthday());
					}
					if (StringUtils.isNotBlank(staff.getLevel())) {
						temp.setLevel(staff.getLevel());
					}
				} else {
					staff.setStatus(status);
					staff.setType("0");
					temp = staff;
				}
				merge(temp);
				success ++;
			} catch (Exception e) {
				e.printStackTrace();
				fail ++;
			}
		}
		Logger.getLogger(getClass()).info("total: " + staffs.size() + ", fail: " + fail);
		retval.put("total", staffs.size());
		retval.put("success", success);
		retval.put("fail", fail);
		return retval;
	}

	/* (non-Javadoc)
	 * @see kklazy.wechat.service.StaffService#operationGuest(java.lang.String)
	 */
	public synchronized Boolean operationGuest(String empno) throws Exception {
		Staff staff = findByEmpno(empno);
		if (StringUtils.isNotBlank(staff.getType()) && !"0".equals(staff.getType())) {
			staff.setType("0");
			addorremoveUser(staff);
			return Boolean.FALSE;
		} else {
			staff.setType("1");
			addorremoveUser(staff);
			return Boolean.TRUE;
		}
	}

	/**
	 * 添加或删除系统用户
	 * 
	 * @param staff
	 */
	private void addorremoveUser(Staff staff) {
		Employee current = SecurityApplicationContext.getEmployee();
		Employee employee = employeeService.findByEmpno(staff.getEmpno()); 
		if ("0".equals(staff.getType())) {
			if (employee != null) {
				employee.setEnabled(Boolean.FALSE);
				employee.getUser().setEnabled(Boolean.FALSE);
			}
		} else {
			if (employee == null) {
				Employee temp = new Employee();
				temp.setName(staff.getName());
				temp.setOrganization(current.getOrganization());
				Set<Role> roles = current.getRoles();
				Set<Role> rs  = new HashSet<Role>();
				for (Role role : roles) {
					Role r = new Role();
					r.setId(role.getId());
					rs.add(r);
				}
				temp.setRoles(rs);
				User user = new User();
				user.setUsername(staff.getEmpno());
				user.setPassword(MD5Utils.md5Hex(staff.getEmpno() + "{" + staff.getEmpno() + "}"));
				user.setEmployee(temp);
				userService.merge(user);
			}
		}
	}

}
