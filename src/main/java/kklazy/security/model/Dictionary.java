/**
 * 
 */
package kklazy.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * @author Kui
 */
@Entity
@Table(name = "SYS_DICTIONARY")
public class Dictionary extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8206068931226339996L;

	private String group;	// 分组

	private String code;	// 代码

	private String name;	// 显示名称

	private String value;	// 值

	private String type;	// 类型

	private String status;	// 状态

	private String desc;	// 注释

	private Integer sort;	// 排序

	/**
	 * 
	 */
	public Dictionary() { }

	/**
	 * @param group
	 * @param code
	 * @param name
	 * @param value
	 * @param type
	 * @param status
	 * @param desc
	 * @param sort
	 */
	public Dictionary(String group, String code, String name, String value, String type, String status, String desc, Integer sort) {
		this.group  = group;
		this.code   = code;
		this.name   = name;
		this.value  = value;
		this.type   = type;
		this.status = status;
		this.desc   = desc;
		this.sort   = sort;
	}

	/**
	 * @return the group
	 */
	@Column(name = "DIC_GROUP")
	public String getGroup() {
		return group;
	}

	/**
	 * @param group the group to set
	 */
	public void setGroup(String group) {
		this.group = group;
	}

	/**
	 * @return the value
	 */
	@Column(name = "DIC_CODE")
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
	@Column(name = "DIC_NAME")
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
	 * @return the value
	 */
	@Column(name = "DIC_VALUE")
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
	 * @return the desc
	 */
	@Column(name = "DESCRIPTION")
	public String getDesc() {
		return desc;
	}

	/**
	 * @param desc the desc to set
	 */
	public void setDesc(String desc) {
		this.desc = desc;
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
