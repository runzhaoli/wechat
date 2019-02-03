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
 * 推荐
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_RECOMMEND")
public class Recommend extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4425825974867584508L;

	/**
	 * 推荐人信息
	 */
	private Staff staff;

	/**
	 * 推荐人工号
	 */
	private String empno;

	/**
	 * 被推荐人姓名
	 */
	private String name;

	/**
	 * 被推荐职位
	 */
	private String position;

	/**
	 * 被推荐人工作年限
	 */
	private String workingYears;

	/**
	 * 被推荐人联系电话
	 */
	private String mobile;

	/**
	 * 被推荐人联系邮箱
	 */
	private String email;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 审核状态
	 * <pre>
	 * 1、未通过
	 * 2、通过
	 * 3、
	 * </pre>
	 */
	private String auditStatus;

	/**
	 * 审核描述
	 */
	private String auditRemark;

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
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the position
	 */
	@Column(name = "POSITION")
	public String getPosition() {
		return position;
	}

	/**
	 * @param position the position to set
	 */
	public void setPosition(String position) {
		this.position = position;
	}

	/**
	 * @return the workingYears
	 */
	@Column(name = "WORKING_YEARS")
	public String getWorkingYears() {
		return workingYears;
	}

	/**
	 * @param workingYears the workingYears to set
	 */
	public void setWorkingYears(String workingYears) {
		this.workingYears = workingYears;
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
	 * @return the email
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION", length = 10240)
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
	@Column(name = "AUDIT_REMARK", length = 1024)
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
	 * @return
	 */
	@Transient
	public int getProgress() {
		int i = StringUtils.isNotBlank(auditStatus) ? Integer.parseInt(auditStatus) : 0;
		return i == 0 ? 100 : i * 100 / 6;
	}

}
