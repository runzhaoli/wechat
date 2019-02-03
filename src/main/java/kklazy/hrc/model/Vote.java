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
@Table(name = "HRC_VOTE")
public class Vote extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8677009833442233194L;

	private String type;		// 投票类型
	private String empno;		// 工号、团队编号
	private String name;		// 姓名、团队名称
	private String dept;		// 部门
	private String project;		// 所在项目
	private String description;	// 事迹介绍
	private String declaration;	// 个人宣言
	private String recommend;	// 推荐词
	private String reference;	// 推荐人
	private Integer total;		// 总票数
	private float percent = 0;// 总票数百分比
	private String today;		// 当天已投

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
	 * @return the dept
	 */
	@Column(name = "DEPARTMENT")
	public String getDept() {
		return dept;
	}

	/**
	 * @param dept the dept to set
	 */
	public void setDept(String dept) {
		this.dept = dept;
	}

	/**
	 * @return the project
	 */
	@Column(name = "PROJECT_NAME")
	public String getProject() {
		return project;
	}

	/**
	 * @param project the project to set
	 */
	public void setProject(String project) {
		this.project = project;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION", length = 4096)
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the declaration
	 */
	@Column(name = "DECLARATION", length = 4096)
	public String getDeclaration() {
		return declaration;
	}

	/**
	 * @param declaration the declaration to set
	 */
	public void setDeclaration(String declaration) {
		this.declaration = declaration;
	}

	/**
	 * @return the recommend
	 */
	@Column(name = "RECOMMEND", length = 4096)
	public String getRecommend() {
		return recommend;
	}

	/**
	 * @param recommend the recommend to set
	 */
	public void setRecommend(String recommend) {
		this.recommend = recommend;
	}

	/**
	 * @return the reference
	 */
	@Column(name = "REFERENCE")
	public String getReference() {
		return reference;
	}

	/**
	 * @param reference the reference to set
	 */
	public void setReference(String reference) {
		this.reference = reference;
	}

	/**
	 * @return the total
	 */
	@Transient
	public Integer getTotal() {
		return total == null ? 0 : total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Integer total) {
		this.total = total;
	}

	/**
	 * @return the percent
	 */
	@Transient
	public float getPercent() {
		return percent;
	}

	/**
	 * @param percent the percent to set
	 */
	public void setPercent(float percent) {
		this.percent = percent;
	}

	/**
	 * @return the today
	 */
	@Transient
	public String getToday() {
		return today;
	}

	/**
	 * @param today the today to set
	 */
	public void setToday(String today) {
		this.today = today;
	}

}
