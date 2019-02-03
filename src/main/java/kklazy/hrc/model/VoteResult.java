/**
 * 
 */
package kklazy.hrc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.persistence.Transient;

import kklazy.persistence.model.DefaultModel;

/**
 * @author kk
 *
 */
@Entity
@Table(name = "HRC_VOTE_RESULT")
public class VoteResult extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8829940476850694436L;

	private String type;	// 被投票类型
	private String empno;	// 被投票人
	private String name;	// 被投票人
	private String vdept;	// 投票人部门
	private String vempno;	// 投票人工号
	private String vname;	// 投票人姓名
	private String vdate;	// 投票日期

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
	 * @return the empno
	 */
	@Column(name = "EMPNO")
	public String getEmpno() {
		return empno;
	}

	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(String empno) {
		this.empno = empno;
	}

	/**
	 * @return the name
	 */
	@Transient
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
	 * @return the vdept
	 */
	@Column(name = "V_DEPT")
	public String getVdept() {
		return vdept;
	}

	/**
	 * @param vdept the vdept to set
	 */
	public void setVdept(String vdept) {
		this.vdept = vdept;
	}

	/**
	 * @return the vempno
	 */
	@Column(name = "V_EMPNO")
	public String getVempno() {
		return vempno;
	}

	/**
	 * @param vempno the vempno to set
	 */
	public void setVempno(String vempno) {
		this.vempno = vempno;
	}

	/**
	 * @return the vname
	 */
	@Column(name = "V_NAME")
	public String getVname() {
		return vname;
	}

	/**
	 * @param vname the vname to set
	 */
	public void setVname(String vname) {
		this.vname = vname;
	}

	/**
	 * @return the vdate
	 */
	@Column(name = "V_DATE")
	public String getVdate() {
		return vdate;
	}

	/**
	 * @param vdate the vdate to set
	 */
	public void setVdate(String vdate) {
		this.vdate = vdate;
	}

}
