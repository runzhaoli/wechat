package kklazy.newtouch.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import kklazy.persistence.model.DefaultModel;

/**
 * 用于绑定人事小助手的所有员工信息
 * 
 * @author kk
 */
@Entity
@Table(name = "NEWTOUCH_STAFF")
public class Staff extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -139260819043302385L;

	/**
	 * 工号
	 */
	private String empno;

	/**
	 * 身份证号
	 */
	private String identity;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 事业部
	 */
	private String businessdepartment;

	/**
	 * 部门
	 */
	private String department;

	/**
	 * 进公司日期
	 */
	private Date indate;

	/**
	 * 区域
	 */
	private String area;

	/**
	 * 电话、手机
	 */
	private String phone;

	/**
	 * Email
	 */
	private String email;

	/**
	 * 生日
	 */
	private Date birthday;

	/**
	 * 昵称
	 */
	private String nickname;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 状态(0:离职状态,n:每次导入状态都会+1)
	 */
	private String status;

	/**
	 * 用户类型(0、普通用户，1、管理员用户)
	 */
	private String type;

	/**
	 * 职级
	 */
	private String level;

	/**
	 * 获取身份证号四位
	 * 
	 * @return
	 */
	@Transient
	public String getIdno() {
		if (StringUtils.isNotBlank(identity)) {
			String prefix = identity.substring(0, 2);
			String suffix = identity.substring(identity.length() - 2);
			return prefix + "******" + suffix;
		}
		return identity;
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
	 * @return the businessdepartment
	 */
	@Column(name = "BUSINESS_DEPARTMENT")
	public String getBusinessdepartment() {
		return businessdepartment;
	}

	/**
	 * @param businessdepartment the businessdepartment to set
	 */
	public void setBusinessdepartment(String businessdepartment) {
		this.businessdepartment = businessdepartment;
	}

	/**
	 * @return the department
	 */
	@Column(name = "DEPARTMENT")
	public String getDepartment() {
		return department;
	}

	/**
	 * @param department the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the indate
	 */
	@Column(name = "IN_DATE")
	public Date getIndate() {
		return indate;
	}

	/**
	 * @param indate the indate to set
	 */
	public void setIndate(Date indate) {
		this.indate = indate;
	}

	/**
	 * @return the area
	 */
	@Column(name = "AREA")
	public String getArea() {
		return area;
	}

	/**
	 * @param area the area to set
	 */
	public void setArea(String area) {
		this.area = area;
	}

	/**
	 * @return the phone
	 */
	@Column(name = "PHONE")
	public String getPhone() {
		return phone;
	}

	/**
	 * @param phone the phone to set
	 */
	public void setPhone(String phone) {
		this.phone = phone;
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
	 * @return the birthday
	 */
	@Column(name = "BIRTHDAY")
	public Date getBirthday() {
		return birthday;
	}

	/**
	 * @param birthday the birthday to set
	 */
	public void setBirthday(Date birthday) {
		this.birthday = birthday;
	}

	/**
	 * @return the nickname
	 */
	@Column(name = "NICKNAME")
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the sex
	 */
	@Column(name = "SEX")
	public String getSex() {
		return sex;
	}

	/**
	 * @param sex the sex to set
	 */
	public void setSex(String sex) {
		this.sex = sex;
	}

	/**
	 * @return the status
	 */
	@Column(name = "STATUS")
	public String getStatus() {
		return status;
	}

	/**
	 * @param status the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the level
	 */
	@Column(name = "LEVEL")
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}
	
}
