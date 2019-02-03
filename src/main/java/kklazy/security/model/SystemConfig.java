package kklazy.security.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

@Entity
@Table(name = "SYS_SYSTEM_CONFIG")
public class SystemConfig extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -879217312704187964L;
	
	private String key;		// 键
	
	private String value;	// 值
	
	private String remark;	// 备注

	/**
	 * @return the key
	 */
	@Column(name = "CONF_KEY")
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the value
	 */
	@Column(name = "CONF_VALUE")
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
	 * @return the remark
	 */
	@Column(name = "CONF_REMARK")
	public String getRemark() {
		return remark;
	}

	/**
	 * @param remark the remark to set
	 */
	public void setRemark(String remark) {
		this.remark = remark;
	}

}
