/**
 * 
 */
package kklazy.hrc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import kklazy.newtouch.model.Staff;
import kklazy.persistence.model.DefaultModel;

/**
 * 证明
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_CERTIFY")
public class Certify extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3592598400556693452L;

	/**
	 * 员工信息
	 */
	private Staff staff;

	/**
	 * 工号
	 */
	private String empno;

	/**
	 * 身份证号 ID No.
	 */
	private String identity;

	/**
	 * 用途描述 Use description
	 */
	private String description;

	/**
	 * 证明类型
	 * 
	 * <pre>
	 * 1.在职证明
	 * 2.收入证明
	 * </pre>
	 */
	private String proofType;

	/**
	 * 接收类型
	 * 
	 * <pre>
	 * 1.顺丰快递,快递到付
	 * 2.公司自取，401-402办公室找李晓红
	 * </pre>
	 */
	private String receiveType;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 寄送地址
	 */
	private String address;

	/**
	 * 审核状态
	 * <pre>
	 * 0、审核未通过
	 * 1、申请中
	 * 2、已受理
	 * 3、材料准备中
	 * 4、快递已寄出
	 * </pre>
	 */
	private String auditStatus;

	/**
	 * 审核描述
	 */
	private String auditRemark;
	
	/**
	 * 快递单号
	 */
	private String express;

	/**
	 * @return the staff
	 */
	@OneToOne(targetEntity = Staff.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable = true)
	public Staff getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
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
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the proofType
	 */
	@Column(name = "PROOF_TYPE")
	public String getProofType() {
		return proofType;
	}

	/**
	 * @param proofType the proofType to set
	 */
	public void setProofType(String proofType) {
		this.proofType = proofType;
	}

	/**
	 * @return the receiveType
	 */
	@Column(name = "RECEIVE_TYPE")
	public String getReceiveType() {
		return receiveType;
	}

	/**
	 * @param receiveType the receiveType to set
	 */
	public void setReceiveType(String receiveType) {
		this.receiveType = receiveType;
	}

	/**
	 * @return the mobile
	 */
	@Column(name = "MOBILE")
	public String getMobile() {
		return mobile;
	}

	/**
	 * @param mobile the mobile to set
	 */
	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	/**
	 * @return the address
	 */
	@Column(name = "ADDRESS")
	public String getAddress() {
		return address;
	}

	/**
	 * @param address the address to set
	 */
	public void setAddress(String address) {
		this.address = address;
	}

	/**
	 * @return the auditStatus
	 */
	@Column(name = "AUDIT_STATUS")
	public String getAuditStatus() {
		return auditStatus;
	}

	/**
	 * @param auditStatus the auditStatus to set
	 */
	public void setAuditStatus(String auditStatus) {
		this.auditStatus = auditStatus;
	}

	/**
	 * @return the auditRemark
	 */
	@Column(name = "AUDIT_REMARK")
	public String getAuditRemark() {
		return auditRemark;
	}

	/**
	 * @param auditRemark the auditRemark to set
	 */
	public void setAuditRemark(String auditRemark) {
		this.auditRemark = auditRemark;
	}
	
	/**
	 * @return the express
	 */
	@Column(name = "EXPRESS")
	public String getExpress() {
		return express;
	}

	/**
	 * @param express the express to set
	 */
	public void setExpress(String express) {
		this.express = express;
	}

	/**
	 * @return
	 */
	@Transient
	public int getProgress() {
		int i = StringUtils.isNotBlank(auditStatus) ? Integer.parseInt(auditStatus) : 0;
		return i == 0 ? 100 : i * 100 / 4;
	}

}
