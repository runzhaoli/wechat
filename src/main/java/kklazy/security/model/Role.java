/**
 * 
 */
package kklazy.security.model;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import kklazy.persistence.model.DefaultModel;

/**
 * 角色
 * 
 * @author Kui
 */
@Entity
@Table(name = "SYS_ROLE")
public class Role extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2594130674930898182L;

	/**
	 * 角色代码
	 */
	private String code;
	
	/**
	 * 角色名称
	 */
	private String name;
	
	/**
	 * 角色类型
	 */
	private String type;
	
	/**
	 * 角色图标
	 */
	private String icon;
	
	/**
	 * 备注、说明
	 */
	private String description;
	
	/**
	 * 角色下有权限访问的资源项
	 */
	@OrderBy(clause = "sort asc")
	private List<Resource> resources;
	
	/**
	 * 拥有该角色的用户
	 */
	private Set<Employee> employees;
	
	/**
	 * 角色状态
	 */
	private String status;

	/**
	 * 排序
	 */
	private Integer sort;
	
	/**
	 * 
	 */
	public Role() {}
	
	/**
	 * @return
	 * 
	 * @author Kui
	 */
	public Set<Resource> gatherResources() {
		Set<Resource> resources = new HashSet<Resource>();
		if (this.resources != null && !this.resources.isEmpty()) {
			resources.addAll(this.resources);
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
		if (this.resources != null && !this.resources.isEmpty()) {
			for (Resource resource : this.resources) {
				ids.add(resource.getId());
			}
		}
		return ids;
	}

	/**
	 * @return the code
	 */
	@Column(name = "ROLE_CODE")
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
	 * @return the name
	 */
	@Column(name = "ROLE_NAME")
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
	@Column(name = "ROLE_TYPE")
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
	 * @return the icon
	 */
	@Column(name = "ICON")
	public String getIcon() {
		return icon;
	}

	/**
	 * @param icon the icon to set
	 */
	public void setIcon(String icon) {
		this.icon = icon;
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
	 * @return the resources
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "REL_ROLE_RESOURCE", 
		joinColumns        = { @JoinColumn(name = "ROLE_ID",     referencedColumnName = "ID") },
		inverseJoinColumns = { @JoinColumn(name = "RESOURCE_ID", referencedColumnName = "ID") })
	public List<Resource> getResources() {
		return resources;
	}

	/**
	 * @param resources the resources to set
	 */
	public void setResources(List<Resource> resources) {
		this.resources = resources;
	}

	/**
	 * @return the employees
	 */
	@ManyToMany(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinTable(name = "REL_EMPLOYEE_ROLE", 
		joinColumns        = { @JoinColumn(name = "ROLE_ID", referencedColumnName = "ID") },
		inverseJoinColumns = { @JoinColumn(name = "EMPLOYEE_ID", referencedColumnName = "ID") })
	public Set<Employee> getEmployees() {
		
		return employees;
	}

	/**
	 * @param employees the employees to set
	 */
	public void setEmployees(Set<Employee> employees) {
		this.employees = employees;
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
	@Column(name = "ROLE_SORT_ORDER")
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
