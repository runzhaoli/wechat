package kklazy.security.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 用户
 * 
 * @author Kui
 */
@Entity
@Table(name = "SYS_USER")
public class User extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1300646667085300999L;
	
	public static final String DEFAULT_PASSWORD = "888888";

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 关联员工
	 */
	private Employee employee;

	/**
	 * @return the username
	 */
	@Column(name = "USERNAME", nullable = false)
	public String getUsername() {
		return username;
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the password
	 */
	@Column(name = "PASSWORD", nullable = false)
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * @return the employee
	 * 
	 * <pre>
	 * @OneToOne：一对一关联
	 *		cascade：级联,它可以有有五个值可选,分别是：
	 *		CascadeType.PERSIST：级联新建
	 *		CascadeType.REMOVE ：级联删除
	 *		CascadeType.REFRESH：级联刷新
	 *		CascadeType.MERGE  ：级联更新
	 *		CascadeType.ALL    ：以上全部四项
	 *		fetch：延迟加载，有两个选项：
	 *		FetchType.LAZY  ：延迟加载
	 *		FetchType.EAGER ：即时加载
	 * @JoinColumn：主表外键字段 EMPLOYEE_ID
	 * </pre>
	 */
	@OneToOne(targetEntity = Employee.class, cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	@JoinColumn(name = "EMPLOYEE_ID", nullable = true)
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}
	
}
