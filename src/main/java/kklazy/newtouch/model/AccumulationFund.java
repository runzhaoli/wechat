package kklazy.newtouch.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 公积金账号
 * 
 * @author kk
 */
@Entity
@Table(name = "NEWTOUCH_ACCUMULATION_FUND")
public class AccumulationFund extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8820614259028084544L;
	
	/**
	 * 员工信息
	 */
	private Staff staff;

	/**
	 * 工号
	 */
	private String empno;
	
	/**
	 * 身份证号
	 */
	private String identity;
	
	/**
	 * 公积金账号
	 */
	private String accumulationFundAccount;

	/**
	 * @return the stff
	 */
	@OneToOne(targetEntity = Staff.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable = true)
	public Staff getStaff() {
		return staff;
	}

	/**
	 * @param stff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * @return the empno
	 */
	@Column(name = "EMPNO")
	public String getEmpno() {
		return empno;
	}

	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(String empno) {
		this.empno = empno;
	}

	/**
	 * @return the identity
	 */
	@Column(name = "IDENTITY")
	public String getIdentity() {
		return identity;
	}

	/**
	 * @param identity the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the accumulationFundAccount
	 */
	@Column(name = "ACCUMULATION_FUND_ACCOUNT")
	public String getAccumulationFundAccount() {
		return accumulationFundAccount;
	}

	/**
	 * @param accumulationFundAccount the accumulationFundAccount to set
	 */
	public void setAccumulationFundAccount(String accumulationFundAccount) {
		this.accumulationFundAccount = accumulationFundAccount;
	}

}
