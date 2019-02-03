package kklazy.activities.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 23周年庆照片投票
 * 
 * @author kk
 */
@Entity
@Table(name = "VOTE_PHOTO_23")
public class VotePhoto23 extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 5262098819513729412L;

	/**
	 * 照片编号
	 */
	private String picid;

	/**
	 * 投票人
	 */
	private String empno;

	/**
	 * @return the picid
	 */
	@Column(name = "PIC_ID")
	public String getPicid() {
		return picid;
	}

	/**
	 * @param picid the picid to set
	 */
	public void setPicid(String picid) {
		this.picid = picid;
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

}
