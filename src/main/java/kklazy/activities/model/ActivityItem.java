package kklazy.activities.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Transient;

import kklazy.persistence.model.DefaultModel;

/**
 * 活动项目
 * 
 * @author kk
 */
@Entity
@Table(name = "ACTIVITIES_ACTIVITY_ITEM")
public class ActivityItem extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 2258885811622317919L;

	/**
	 * 活动项目代码
	 */
	private String code;

	/**
	 * 活动项目名称
	 */
	private String name;

	/**
	 * 活动项目小图标
	 */
	private String icon;

	/**
	 * 活动大类
	 */
	private Activity activity;

	/**
	 * 开始时间
	 */
	private String start;

	/**
	 * 结束时间
	 */
	private String end;

	/**
	 * 项目类型
	 */
	private String type;

	/**
	 * 可参加几次
	 */
	private Integer num = 0;

	/**
	 * 每次获得的分数
	 */
	private Integer score = 0;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * 展示用户各项目活动积分
	 */
	private Point point = new Point();

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
	 * @return the activity
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACTIVITY_ID")
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
	 * @return the start
	 */
	@Column(name = "START_DATE")
	public String getStart() {
		return start;
	}

	/**
	 * @param start the start to set
	 */
	public void setStart(String start) {
		this.start = start;
	}

	/**
	 * @return the end
	 */
	@Column(name = "END_DATE")
	public String getEnd() {
		return end;
	}

	/**
	 * @param end the end to set
	 */
	public void setEnd(String end) {
		this.end = end;
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
	 * @return the num
	 */
	@Column(name = "NUM")
	public Integer getNum() {
		return num;
	}

	/**
	 * @param num the num to set
	 */
	public void setNum(Integer num) {
		this.num = num;
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

	/**
	 * @return the point
	 */
	@Transient
	public Point getPoint() {
		return point;
	}

	/**
	 * @param point the point to set
	 */
	public void setPoint(Point point) {
		this.point = point;
	}

}
