/**
 * 
 */
package kklazy.hrc.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import org.apache.commons.lang3.StringUtils;

import kklazy.newtouch.model.Staff;
import kklazy.persistence.model.DefaultModel;

/**
 * 投诉与建议
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_COMPLAINT_PROPOSAL")
public class ComplaintProposal extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 6392673780127253066L;

	/**
	 * 关联员工
	 */
	private Staff staff;

	/**
	 * 是否匿名
	 */
	private Boolean anonymous = Boolean.FALSE;

	/**
	 * 投诉与建议类型
	 */
	private String type;

	/**
	 * 描述
	 */
	private String description;

	/**
	 * 回复
	 */
	private String reply;

	/**
	 * @return the staff
	 */
	@OneToOne(targetEntity = Staff.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "STAFF_ID", nullable = true)
	public Staff getStaff() {
		return staff;
	}

	/**
	 * @param staff the staff to set
	 */
	public void setStaff(Staff staff) {
		this.staff = staff;
	}

	/**
	 * @return the anonymous
	 */
	@Column(name = "ANONYMOUS")
	public Boolean getAnonymous() {
		return anonymous;
	}

	/**
	 * @param anonymous the anonymous to set
	 */
	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
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
	 * @return the description
	 */
	@Column(name = "DESCRIPTION", length = 2048)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	@Transient
	public String getDesc() {
		return StringUtils.isNotBlank(description) ? description.length() >= 10 ? description.substring(0, 10) + " ..." : description : "";
	}

	/**
	 * @return the reply
	 */
	@Column(name = "REPLY", length = 2048)
	public String getReply() {
		return reply;
	}

	/**
	 * @param reply the reply to set
	 */
	public void setReply(String reply) {
		this.reply = reply;
	}
	
}
