/**
 * 
 */
package kklazy.hrc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 人事分工
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_DIVISION")
public class Division extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4635142266605500421L;

	private String module;	// 模块

	private String principal;	// 负责人

	private String duty;	// 职责

	private String contact;	// 联系方式

	private Integer sort;

	/**
	 * @return the module
	 */
	@Column(name = "MODULE")
	public String getModule() {
		return module;
	}

	/**
	 * @param module the module to set
	 */
	public void setModule(String module) {
		this.module = module;
	}

	/**
	 * @return the principal
	 */
	@Column(name = "PRINCIPAL")
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the duty
	 */
	@Column(name = "DUTY")
	public String getDuty() {
		return duty;
	}

	/**
	 * @param duty the duty to set
	 */
	public void setDuty(String duty) {
		this.duty = duty;
	}

	/**
	 * @return the contact
	 */
	@Column(name = "CONTACT")
	public String getContact() {
		return contact;
	}

	/**
	 * @param contact the contact to set
	 */
	public void setContact(String contact) {
		this.contact = contact;
	}

	/**
	 * @return the sort
	 */
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}
}
