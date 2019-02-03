package kklazy.hrc.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 图片轮播
 * 
 * @author kk
 */
@Entity
@Table(name = "HRC_SLIDER_LOOP")
public class SliderLoop extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8221865305711399332L;

	/**
	 * 标题
	 */
	private String title;

	/**
	 * 图片
	 */
	private String img;

	/**
	 * 链接地址
	 */
	private String url;

	/**
	 * 轮播图片有效期开始时间
	 */
	private String start;

	/**
	 * 轮播图片有效期结束时间
	 */
	private String end;

	/**
	 * 排序
	 */
	private Integer sort;

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
	 * @return the url
	 */
	@Column(name = "URL")
	public String getUrl() {
		return url;
	}

	/**
	 * @param url the url to set
	 */
	public void setUrl(String url) {
		this.url = url;
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
	 * @return the sort
	 */
	@Column(name = "SORT")
	public Integer getSort() {
		return sort;
	}

	/**
	 * @param sort the sort to set
	 */
	public void setSort(Integer sort) {
		this.sort = sort;
	}

}
