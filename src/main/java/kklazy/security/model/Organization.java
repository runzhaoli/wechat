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
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 组织机构信息
 * 
 * @author Kui
 */
@Entity
@Table(name = "SYS_ORGANIZATION")
public class Organization extends DefaultModel {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = -8021778246574572108L;

	/**
	 * 机构代码
	 */
	private String code;
	
	/**
	 * 机构名称
	 */
	private String name;
	
	/**
	 * 机构类型
	 */
	private String type;
	
	/**
	 * 机构级别
	 */
	private String level;
	
	/**
	 * 上级机构
	 */
	private Organization parent;
	
	/**
	 * 下级机构
	 */
	private List<Organization> sublevel;
	
	/**
	 * 该机构所关联的员工
	 */
	private Set<Employee> employees;
	
	/**
	 * 机构状态
	 */
	private String status;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * @return the code
	 */
	@Column(name = "ORG_CODE", nullable = false)
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
	@Column(name = "ORG_NAME")
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
	@Column(name = "ORG_TYPE")
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
	@Column(name = "ORG_LEVEL")
	public String getLevel() {
		return level;
	}

	/**
	 * @param level the level to set
	 */
	public void setLevel(String level) {
		this.level = level;
	}

	/**
	 * @return the parent
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public Organization getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(Organization parent) {
		this.parent = parent;
	}

	/**
	 * @return the sublevel
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	public List<Organization> getSublevel() {
		return sublevel;
	}

	/**
	 * @param sublevel the sublevel to set
	 */
	public void setSublevel(List<Organization> sublevel) {
		this.sublevel = sublevel;
	}

	/**
	 * @return the employees
	 */
	@OneToMany(mappedBy = "organization", fetch = FetchType.LAZY)
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
