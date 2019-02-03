/**
 * 
 */
package kklazy.security.vo;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Set;

import kklazy.security.model.Employee;
import kklazy.security.model.Role;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author Kui
 */
public class PassportDetails implements UserDetails {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4324929089835037282L;

	/**
	 * 用户名
	 */
	private String username;
	
	/**
	 * 密码
	 */
	private String password;

	/**
	 * 密码确认
	 */
	private String confirm;
	
	/**
	 * 验证码
	 */
	private String verify;
	
	/**
	 * 用户配色,根据性别来设置不同的色调(默认:男)
	 */
	private String color = "#57c8f2";

	/**
	 * 关联员工
	 */
	private Employee employee;
	
	private Boolean accountNonExpired = Boolean.TRUE;
	private Boolean accountNonLocked  = Boolean.TRUE;
	private Boolean credentialsNonExpired = Boolean.TRUE;

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getAuthorities()
	 */
	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
		Set<Role> roles = employee.getRoles();
		for (Role role : roles) {
			authorities.add(new SimpleGrantedAuthority(role.getCode()));
		}
		return authorities;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getPassword()
	 */
	@Override
	public String getPassword() {
		return password;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#getUsername()
	 */
	@Override
	public String getUsername() {
		return username;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonExpired()
	 */
	@Override
	public boolean isAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isAccountNonLocked()
	 */
	@Override
	public boolean isAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isCredentialsNonExpired()
	 */
	@Override
	public boolean isCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @see org.springframework.security.core.userdetails.UserDetails#isEnabled()
	 */
	@Override
	public boolean isEnabled() {
		return employee.getEnabled();
	}

	/**
	 * @param username the username to set
	 */
	public void setUsername(String username) {
		this.username = username;
	}

	/**
	 * @return the confirm
	 */
	public String getConfirm() {
		return confirm;
	}

	/**
	 * @param confirm the confirm to set
	 */
	public void setConfirm(String confirm) {
		this.confirm = confirm;
	}

	/**
	 * @return the verify
	 */
	public String getVerify() {
		return verify;
	}

	/**
	 * @param verify the verify to set
	 */
	public void setVerify(String verify) {
		this.verify = verify;
	}

	/**
	 * @return the color
	 */
	public String getColor() {
		if (Employee.FEMALE.equals(employee.getGender())) {
			return "pink";
		}
		return color;
	}

	/**
	 * @param color the color to set
	 */
	public void setColor(String color) {
		this.color = color;
	}

	/**
	 * @return the employee
	 */
	public Employee getEmployee() {
		return employee;
	}

	/**
	 * @param employee the employee to set
	 */
	public void setEmployee(Employee employee) {
		this.employee = employee;
	}

	/**
	 * @return the accountNonExpired
	 */
	public Boolean getAccountNonExpired() {
		return accountNonExpired;
	}

	/**
	 * @param accountNonExpired the accountNonExpired to set
	 */
	public void setAccountNonExpired(Boolean accountNonExpired) {
		this.accountNonExpired = accountNonExpired;
	}

	/**
	 * @return the accountNonLocked
	 */
	public Boolean getAccountNonLocked() {
		return accountNonLocked;
	}

	/**
	 * @param accountNonLocked the accountNonLocked to set
	 */
	public void setAccountNonLocked(Boolean accountNonLocked) {
		this.accountNonLocked = accountNonLocked;
	}

	/**
	 * @return the credentialsNonExpired
	 */
	public Boolean getCredentialsNonExpired() {
		return credentialsNonExpired;
	}

	/**
	 * @param credentialsNonExpired the credentialsNonExpired to set
	 */
	public void setCredentialsNonExpired(Boolean credentialsNonExpired) {
		this.credentialsNonExpired = credentialsNonExpired;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

}
