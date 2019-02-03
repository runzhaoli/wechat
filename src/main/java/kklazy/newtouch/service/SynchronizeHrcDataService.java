package kklazy.newtouch.service;

import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import cn.newtouch.hrc.data.dto.DepartmentDTO;
import cn.newtouch.hrc.data.dto.EmployeeDTO;
import cn.newtouch.hrc.data.rpc.DepartmentServiceRPC;
import cn.newtouch.hrc.data.rpc.EmployeeServiceRPC;
import kklazy.newtouch.model.AccumulationFund;
import kklazy.newtouch.model.Staff;
import kklazy.persistence.model.SupportModel;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.DateUtils;
import kklazy.persistence.utils.StringUtils;

/**
 * 
 * @since manager 1.0
 * @author <a href="mailto:kklazy@live.cn">kk</a>
 */
@Service("synchronizeHrcDataService")
@Transactional(rollbackFor = Exception.class)
public class SynchronizeHrcDataService extends DefaultService<SupportModel, String> {

	protected final Log logger = LogFactory.getLog(getClass());

	public static final String ENCRYPT_KEY_MOBILE = "MOBILE";
	public static final String ENCRYPT_KEY_IDENTITY = "IDENTITY";

	@Autowired private AccumulationFundService accumulationFundService;
	@Autowired private StaffService staffService;

	@Autowired private DepartmentServiceRPC departmentServiceRpc;
	@Autowired private EmployeeServiceRPC employeeServiceRpc;

	/**
	 * @param empdtos
	 * @param departments
	 * @param keyMobile
	 * @param keyIdentity
	 */
	@Transactional(readOnly = false)
	public void syncEmployeesAndAccountsLimit(Map<String, Staff> staffs, Map<String, AccumulationFund> accumulationFunds, Map<Long, DepartmentDTO> departments, List<EmployeeDTO> empdtos) {
		String week = DateUtils.getWeek(DateUtils.getCalendar());
		for (EmployeeDTO dto : empdtos) {
			if (dto == null) {
				continue ;
			}
			Staff staff = staffs.get(StringUtils.trim(dto.getBadge()));
			if (staff == null) {
				staff = new Staff();
				staff.setType("0");
				staff.setEmpno(StringUtils.trim(dto.getBadge()));
			}
			staff.setName(dto.getName());
			staff.setIdentity(dto.getCertno());
			if (StringUtils.isNotBlank(dto.getCertno()) && dto.getCertno().length() == 18) {
				try {
					String birthday = dto.getCertno().substring(6, 14);
					staff.setBirthday(DateUtils.parse(birthday, "yyyyMMdd"));
				} catch (Exception e) {

				}
			}
			DepartmentDTO d = departments.get(dto.getDepid());
			if (d != null) {
				staff.setDepartment(d.getDepabbr());
				DepartmentDTO p = departments.get(d.getAdminid());
				if (p != null) {
					staff.setBusinessdepartment(p.getDepabbr());
				} else {
					staff.setBusinessdepartment(d.getDepabbr());
				}
			}
			staff.setIndate(dto.getJoindate());
			staff.setArea(dto.getZone());
			staff.setSex(dto.getGender());
//			staff.setPhone(dto.getMobile());
//			staff.setStatus(dto.getEstatus());
			staff.setEmail(dto.getEmail());
//			staff.setType(dto.getEtype());
			staff.setStatus(week);
			if (!"在职".equals(dto.getEstatus())) {
				staff.setType("0");
				staff.setStatus("0");
			}
			staff.setLevel(dto.getEgrade());
			staff.setModify(new Date());
			staff = staffService.merge(staff);
			AccumulationFund fund = accumulationFunds.get(StringUtils.trim(dto.getBadge()));
			if (fund == null) {
				fund = new AccumulationFund();
				fund.setStaff(staff);
			}
			fund.setAccumulationFundAccount(dto.getAccuaccount());
			accumulationFundService.merge(fund);
		}
		staffService.flush();
		accumulationFundService.flush();
	}

	/**
	 * 
	 */
	public void syncEmployeesAndAccounts() {
		if (employeeServiceRpc == null) {
			return ;
		}
		List<EmployeeDTO> empdtos = employeeServiceRpc.findAllEmployees();
		if (empdtos == null || empdtos.isEmpty()) {
			return ;
		}
		logger.info("Employee Size: " + empdtos.size());
		try {
			List<DepartmentDTO> deptdtos = departmentServiceRpc.findAllDeparts();
			Map<Long, DepartmentDTO> departments = new HashMap<Long, DepartmentDTO>();
			for (DepartmentDTO dept : deptdtos) {
				departments.put(dept.getId(), dept);
			}
			List<Staff> s = staffService.findAll();
			Map<String, Staff> staffs = new HashMap<String, Staff>();
			for (Staff staff : s) {
				staffs.put(staff.getEmpno(), staff);
			}
			List<AccumulationFund> a = accumulationFundService.findAll();
			Map<String, AccumulationFund> accumulationFunds = new HashMap<String, AccumulationFund>();
			for (AccumulationFund accumulationFund : a) {
				String empno = accumulationFund.getStaff() != null ? accumulationFund.getStaff().getEmpno() : null;
				if (StringUtils.isNotBlank(empno)) {
					accumulationFunds.put(empno, accumulationFund);
				}
			}
			int size = 1000;
			int i = empdtos.size() / size;
			for (int j = 0; j <= i; j++) {
				if (empdtos.size() == 0)
					break ;
				int limit = empdtos.size() >= size ? size : empdtos.size();
				List<EmployeeDTO> temp = empdtos.subList(0, limit);
				syncEmployeesAndAccountsLimit(staffs, accumulationFunds, departments, temp);
				temp.clear();
				logger.info("Commit ... Employee Size: " + limit);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
