/**
 * 
 */
package kklazy.component.file.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * @author kk
 *
 */
@Entity
@Table(name = "COMPONENT_ATTACH_FILE")
public class Attachfile extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3881800454823689260L;

	private String code;

	private String name;

	private String path;

	/**
	 * @return the code
	 */
	@Column(name = "CODE")
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
	 * @return the path
	 */
	@Column(name = "PATH")
	public String getPath() {
		return path;
	}

	/**
	 * @param path the path to set
	 */
	public void setPath(String path) {
		this.path = path;
	}

}
