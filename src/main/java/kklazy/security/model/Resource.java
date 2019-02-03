/**
 * 
 */
package kklazy.security.model;

import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import kklazy.persistence.model.DefaultModel;

/**
 * 资源（菜单、按钮、超链接）
 * 
 * @author Kui
 */
@Entity
@Table(name = "SYS_RESOURCE")
public class Resource extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4753244547379660956L;
	
	/**
	 * 
	 */
	private String code;

	/**
	 * 
	 */
	private String number;
	
	/**
	 * 
	 */
	private String name;
	
	/**
	 * 
	 */
	private String value;

	/**
	 * 初始化图标
	 */
	private String iconInit;
	
	/**
	 * 点击后显示的图标
	 */
	private String iconOpen;
	
	/**
	 * URL
	 */
	private String url;
	
	/**
	 * 提示
	 */
	private String tips;
	
	/**
	 * 备注、说明
	 */
	private String description;
	
	/**
	 * 
	 */
	private String type;
	
	/**
	 * 
	 */
	private Resource parent;
	
	/**
	 * 
	 */
	private List<Resource> sublevel;
	
	/**
	 * 
	 */
	private Set<Role> roles;
	
	/**
	 * 
	 */
	private String status;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 
	 */
	public Resource () {}
	
	/**
	 * @param orig
	 */
	public Resource (Resource orig) {
		this.id = orig.getId();
		this.code = orig.getCode();
		this.number = orig.getNumber();
		this.name = orig.getName();
		this.value = orig.getValue();
		this.iconInit = orig.getIconInit();
		this.iconOpen = orig.getIconOpen();
		this.url = orig.getUrl();
		this.tips = orig.getTips();
		this.description = orig.getDescription();
		this.type = orig.getType();
		this.status = orig.getStatus();
		this.sort = orig.getSort();
	}
	
	/**
	 * @return the code
	 */
	@Column(name = "RESOURCE_CODE")
	public String getCode() {
		return code;
	}

	/**
	 * @param code the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the number
	 */
	@Column(name = "RESOURCE_NUMBER")
	public String getNumber() {
		return number;
	}

	/**
	 * @param number the number to set
	 */
	public void setNumber(String number) {
		this.number = number;
	}

	/**
	 * @return the name
	 */
	@Column(name = "RESOURCE_NAME")
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
	@Column(name = "RESOURCE_TYPE")
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
	 * @return the value
	 */
	@Column(name = "RESOURCE_VALUE")
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the iconInit
	 */
	@Column(name = "ICONINIT")
	public String getIconInit() {
		return iconInit;
	}

	/**
	 * @param iconInit the iconInit to set
	 */
	public void setIconInit(String iconInit) {
		this.iconInit = iconInit;
	}

	/**
	 * @return the iconOpen
	 */
	@Column(name = "ICONOPEN")
	public String getIconOpen() {
		return iconOpen;
	}

	/**
	 * @param iconOpen the iconOpen to set
	 */
	public void setIconOpen(String iconOpen) {
		this.iconOpen = iconOpen;
	}

	/**
	 * @return the url
	 */
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
	}

	/**
	 * @return the tips
	 */
	@Column(name = "RESOURCE_TIPS")
	public String getTips() {
		return tips;
	}

	/**
	 * @param tips the tips to set
	 */
	public void setTips(String tips) {
		this.tips = tips;
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
	 * @return the parent
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Resource getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Resource parent) {
		this.parent = parent;
	}

	/**
	 * @return the sublevel
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy(clause = "sort asc")
	public List<Resource> getSublevel() {
		return sublevel;
	}

	/**
	 * @param sublevel the sublevel to set
	 */
	public void setSublevel(List<Resource> sublevel) {
		this.sublevel = sublevel;
	}

	/**
	 * @return the roles
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "REL_ROLE_RESOURCE", 
		joinColumns        = { @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID") },
		inverseJoinColumns = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") })
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
	 * @return the sort
	 */
	@Column(name = "SORT_ORDER")
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
