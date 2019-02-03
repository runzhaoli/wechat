package kklazy.activities.model;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.OrderBy;

import kklazy.persistence.model.DefaultModel;

/**
 * 活动
 * 
 * @author kk
 */
@Entity
@Table(name = "ACTIVITIES_ACTIVITY")
public class Activity extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -2086446988851575232L;

	/**
	 * 活动代码
	 */
	private String code;

	/**
	 * 活动名称
	 */
	private String name;

	/**
	 * 负责人
	 */
	private String principal;

	/**
	 * 活动项目
	 */
	@OrderBy(clause = "sort asc")
	private List<ActivityItem> items;

	/**
	 * 开始时间
	 */
	private String start;

	/**
	 * 结束时间
	 */
	private String end;

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
	 * @return the principal
	 */
	@Column(name = "PRINCIPAL")
	public String getPrincipal() {
		return principal;
	}

	/**
	 * @param principal the principal to set
	 */
	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	/**
	 * @return the items
	 */
	@OneToMany(mappedBy = "activity", fetch = FetchType.LAZY)
	public List<ActivityItem> getItems() {
		return items;
	}

	/**
	 * @param items the items to set
	 */
	public void setItems(List<ActivityItem> items) {
		this.items = items;
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

}
