/**
 * 
 */
package kklazy.newtouch.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.newtouch.model.AccumulationFund;
import kklazy.newtouch.model.Staff;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;

/**
 * @author kk
 *
 */
@Service("accumulationFundService")
@Transactional(rollbackFor = Exception.class)
public class AccumulationFundService extends DefaultService<AccumulationFund, String> {

	@Autowired
	private StaffService staffService;

	/**
	 * @param empno
	 * @return
	 */
	public AccumulationFund findByEmpno(String empno) {
		if (StringUtils.isBlank(empno)) {
			return null;
		}
		DetachedCriteria criteria = getDetachedCriteria();
		criteria.add(Restrictions.eq("empno", empno));
		criteria.add(Restrictions.eq("enabled", Boolean.TRUE));
		criteria.add(Restrictions.eq("delete", Boolean.FALSE));
		List<AccumulationFund> staffs = findBy(criteria);
		if (staffs != null && !staffs.isEmpty()) {
			return staffs.get(0);
		}
		return null;
	}

	/**
	 * @param funds
	 * @return
	 * @throws Exception
	 */
	public Map<String, Integer> importAccumulationFunds(List<AccumulationFund> funds) throws Exception {
		Map<String, Integer> retval = new HashMap<String, Integer>();
		int success = 0;
		int fail    = 0;
		for (AccumulationFund fund : funds) {
			if (StringUtils.isBlank(fund.getEmpno())) {
				continue ;
			}
			try {
				AccumulationFund temp = findByEmpno(fund.getEmpno());
				if (temp != null) {
					if (StringUtils.isNotBlank(fund.getEmpno())) {
						temp.setEmpno(fund.getEmpno());
					}
					if (StringUtils.isNotBlank(fund.getAccumulationFundAccount())) {
						temp.setAccumulationFundAccount(fund.getAccumulationFundAccount());
					}
				} else {
					temp = fund;
				}
				Staff staff = staffService.findByEmpno(fund.getEmpno());
				if (staff != null) {
					temp.setStaff(staff);
				}
				merge(temp);
				success ++;
			} catch (Exception e) {
				e.printStackTrace();
				fail ++;
			}
		}

		Logger.getLogger(getClass()).info("total: " + funds.size() + ", fail: " + fail);
		retval.put("total", funds.size());
		retval.put("success", success);
		retval.put("fail", fail);
		return retval;
	}
	
}
