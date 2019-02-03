package kklazy.security.model;

import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import org.springframework.format.annotation.DateTimeFormat;

import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.utils.DateUtils;

/**
 * 用户信息
 * 
 * @author kui.jiang
 */
@Entity
@Table(name = "SYS_EMPLOYEE")
public class Employee extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 7043443990352863265L;

	public static final String MALE = "M";
	public static final String FEMALE = "F";

	/**
	 * 
	 */
	private User user;

	/**
	 * E-Mail
	 */
	private String email;

	/**
	 * 姓名
	 */
	private String name;

	/**
	 * 用户类型
	 */
	private String type;

	/**
	 * 性别 M=Male男性 F=Female女性
	 */
	private String gender;

	/**
	 * 生日
	 */
	@DateTimeFormat(pattern = DateUtils.DAY_PATTERN)
	private Date birthday;

	/**
	 * 电话
	 */
	private String telephone;

	/**
	 * 手机
	 */
	private String mobile;

	/**
	 * 地址
	 */
	private String address;

	/**
	 * 入职时间
	 */
	@DateTimeFormat(pattern = DateUtils.DAY_PATTERN)
	private Date indate;

	/**
	 * 离职时间
	 */
	@DateTimeFormat(pattern = DateUtils.DAY_PATTERN)
	private Date outdate;

	/**
	 * 用户拥有的角色
	 */
	private Set<Role> roles;

	/**
	 * 用户归属机构
	 */
	private Organization organization;

	/**
	 * 用户数据权限
	 */
	private Set<Organization> data;

	/**
	 * 用户状态
	 */
	private String status;

	/**
	 * 
	 */
	public Employee() {}

	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public Set<Resource> gatherResources() {
		Set<Resource> resources = new HashSet<Resource>();
		if (this.roles != null && !this.roles.isEmpty()) {
			for (Role role : this.roles) {
				resources.addAll(role.gatherResources());
			}
		}
		return resources;
	}

	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public Set<String> gatherResourceIds() {
		Set<String> ids = new HashSet<String>();
		if (this.roles != null && !this.roles.isEmpty()) {
			for (Role role : this.roles) {
				ids.addAll(role.gatherResourceIds());
			}
		}
		return ids;
	}

	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public Set<String> gatherRoleIds() {
		Set<String> ids = new HashSet<String>();
		if (this.roles != null && !this.roles.isEmpty()) {
			for (Role role : roles) {
				ids.add(role.getId());
			}
		}
		return ids;
	}

	/**
	 * @return the user
	 * 
	 * <pre>
	 * @OneToOne：一对一关联
	 * mappedBy = "employee"：意思是说这里的一对一配置参考了employee
	 * employee又是什么呢?employee是User类中的getEmployee(),注意不是User类中的
	 * employee属性,User类中的OneToOne配置就是在getEmployee()方法上面配的.
	 * 如果User类中的getEmployee()方法改成getIdEmployee(),其他不变的话,
	 * 这里就要写成：mappedBy = "idEmployee"
	 * </pre>
	 */
	@OneToOne(mappedBy = "employee", fetch = FetchType.LAZY)
	public User getUser() {
		return user;
	}

	/**
	 * @param user the user to set
	 */
	public void setUser(User user) {
		this.user = user;
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
	 * @return the name
	 */
	@Column(name = "NAME", nullable = false)
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
	 * @return the gender
	 */
	@Column(name = "GENDER")
	public String getGender() {
		return gender;
	}

	/**
	 * @param gender the gender to set
	 */
	public void setGender(String gender) {
		this.gender = gender;
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
	 * @return the telephone
	 */
	@Column(name = "TELEPHONE")
	public String getTelephone() {
		return telephone;
	}

	/**
	 * @param telephone the telephone to set
	 */
	public void setTelephone(String telephone) {
		this.telephone = telephone;
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
	 * @return the outdate
	 */
	@Column(name = "OUT_DATE")
	public Date getOutdate() {
		return outdate;
	}

	/**
	 * @param outdate the outdate to set
	 */
	public void setOutdate(Date outdate) {
		this.outdate = outdate;
	}

	/**
	 * @return the roles
	 * 
	 * <pre>
	 * @ManyToMany：多对多关联
	 * 		cascade：级联,它可以有有五个值可选,分别是：
	 * 		CascadeType.PERSIST：级联新建
	 *		CascadeType.REMOVE ：级联删除
	 *		CascadeType.REFRESH：级联刷新
	 *		CascadeType.MERGE  ： 级联更新
	 *		CascadeType.ALL    ： 以上全部四项
	 *		fetch：延迟加载，有两个选项：
	 *		FetchType.LAZY  ：延迟加载
	 *		FetchType.EAGER ：即时加载
	 * @JoinTable 使用中间表进行关联
	 * 		name：中间表名 REL_EMPLOYEE_ROLE
	 * 		joinColumns：主表关联：@JoinColumn：中间表字段 EMPLOYEE_ID 对应主表字段 ID
	 * 		inverseJoinColumns：从表关联：@JoinColumn：中间表字段 ROLE_ID 对应从表字段 ID
	 * </pre>
	 */
	@ManyToMany(targetEntity = Role.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "REL_EMPLOYEE_ROLE", 
		joinColumns        = { @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID") },
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID",     referencedColumnName = "ID") })
	public Set<Role> getRoles() {
		return roles;
	}

	/**
	 * @param roles the roles to set
	 */
	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	/**
	 * @return the organization
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ORGANIZATION_ID")
	public Organization getOrganization() {
		return organization;
	}

	/**
	 * @param organization the organization to set
	 */
	public void setOrganization(Organization organization) {
		this.organization = organization;
	}

	/**
	 * @return the data
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "REL_EMPLOYEE_ORGANIZATION", 
		joinColumns        = { @JoinColumn(name = "EMPLOYEE_ID",     referencedColumnName = "ID") }, 
		inverseJoinColumns = { @JoinColumn(name = "ORGANIZATION_ID", referencedColumnName = "ID") })
	public Set<Organization> getData() {
		return data;
	}

	/**
	 * @param data the data to set
	 */
	public void setData(Set<Organization> data) {
		this.data = data;
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

}
