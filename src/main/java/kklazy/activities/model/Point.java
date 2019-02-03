package kklazy.activities.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

import kklazy.newtouch.model.Staff;
import kklazy.persistence.model.DefaultModel;

/**
 * 积分
 */
@Entity
@Table(name = "ACTIVITIES_POINT")
public class Point extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3391360487837308183L;

	private Activity activity;	// 活动

	private ActivityItem item;	// 项目

	private Staff staff;	// 人员

	private String empno;	// 工号

	private String type;	// 积分类型

	private Integer score = 0;	// 分数

	/**
	 * @return the activity
	 */
	@OneToOne(targetEntity = Activity.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_ID", nullable = true)
	public Activity getActivity() {
		return activity;
	}

	/**
	 * @param activity the activity to set
	 */
	public void setActivity(Activity activity) {
		this.activity = activity;
	}

	/**
	 * @return the item
	 */
	@OneToOne(targetEntity = ActivityItem.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_ITEM_ID", nullable = true)
	public ActivityItem getItem() {
		return item;
	}

	/**
	 * @param item the item to set
	 */
	public void setItem(ActivityItem item) {
		this.item = item;
	}

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
	 * @return the score
	 */
	@Column(name = "SCORE")
	public Integer getScore() {
		return score;
	}

	/**
	 * @param score the score to set
	 */
	public void setScore(Integer score) {
		this.score = score;
	}



}
