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

import kklazy.newtouch.model.Staff;
import kklazy.persistence.model.DefaultModel;

/**
 * 伊周聊
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_YZL")
public class YiZhouChat extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5850767711837633611L;

	/**
	 * 员工信息
	 */
	private Staff staff;

	/**
	 * 工号
	 */
	private String empno;

	/**
	 * 预约时间
	 */
	private String chattime;

	/**
	 * 沟通形式
	 */
	private String chattype;

	/**
	 * 手机号码
	 */
	private String mobile;

	/**
	 * 沟通人
	 */
	private String chater;

	/**
	 * 沟通确认日期
	 */
	private String chatdate;

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
	private String chatStatus;

	/**
	 * 审核描述
	 */
	private String chatRemark;

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
	 * @return the chattime
	 */
	@Column(name = "CHAT_TIME")
	public String getChattime() {
		return chattime;
	}

	/**
	 * @param chattime the chattime to set
	 */
	public void setChattime(String chattime) {
		this.chattime = chattime;
	}

	/**
	 * @return the chattype
	 */
	@Column(name = "CHAT_TYPE")
	public String getChattype() {
		return chattype;
	}

	/**
	 * @param chattype the chattype to set
	 */
	public void setChattype(String chattype) {
		this.chattype = chattype;
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
	 * @return the chater
	 */
	@Column(name = "CHATER")
	public String getChater() {
		return chater;
	}

	/**
	 * @param chater the chater to set
	 */
	public void setChater(String chater) {
		this.chater = chater;
	}

	/**
	 * @return the chatdate
	 */
	@Column(name = "CHAT_DATE")
	public String getChatdate() {
		return chatdate;
	}

	/**
	 * @param chatdate the chatdate to set
	 */
	public void setChatdate(String chatdate) {
		this.chatdate = chatdate;
	}

	/**
	 * @return the chatStatus
	 */
	@Column(name = "CHAT_STATUS")
	public String getChatStatus() {
		return chatStatus;
	}

	/**
	 * @param chatStatus the chatStatus to set
	 */
	public void setChatStatus(String chatStatus) {
		this.chatStatus = chatStatus;
	}

	/**
	 * @return the chatRemark
	 */
	@Column(name = "CHAT_REMARK")
	public String getChatRemark() {
		return chatRemark;
	}

	/**
	 * @param chatRemark the chatRemark to set
	 */
	public void setChatRemark(String chatRemark) {
		this.chatRemark = chatRemark;
	}

}
