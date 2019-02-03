package kklazy.hrc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 文章
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_ARTICLE")
public class Article extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -6436411025241164489L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 类型
	 */
	private String type;

	/**
	 * 简介
	 */
	private String description;

	/**
	 * logo图片
	 */
	private String img;

	/**
	 * 正文
	 */
	private String content;

	/**
	 * 页面浏览量
	 */
	private Integer pv;

	/**
	 * @return the title
	 */
	@Column(name = "TITLE")
	public String getTitle() {
		return title;
	}

	/**
	 * @param title the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
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

	/**
	 * @return the img
	 */
	@Column(name = "IMG")
	public String getImg() {
		return img;
	}

	/**
	 * @param img the img to set
	 */
	public void setImg(String img) {
		this.img = img;
	}

	/**
	 * @return the content
	 */
	@Column(name = "CONTENT", length = 10240)
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * @return the pv
	 */
	@Column(name = "PV")
	public Integer getPv() {
		return pv;
	}

	/**
	 * @param pv the pv to set
	 */
	public void setPv(Integer pv) {
		this.pv = pv;
	}

}
