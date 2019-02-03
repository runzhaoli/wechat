package kklazy.weixin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 消息正文
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_MESSAGE_CONTENT")
public class MessageContent extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -3009252824996167343L;

	/**
	 * 关联的模板
	 */
	private MessageTemplate template;

	/**
	 * 消息标题
	 */
	private String title;

	/**
	 * 消息主体
	 */
	private String description;

	/**
	 * 如果是图文消息，则是关联的图片
	 * 图片链接，支持JPG、PNG格式，较好的效果为大图360*200，小图200*200
	 */
	private String img;

	/**
	 * 链接
	 */
	private String url;

	/**
	 * @return the template
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID")
	public MessageTemplate getTemplate() {
		return template;
	}

	/**
	 * @param template the template to set
	 */
	public void setTemplate(MessageTemplate template) {
		this.template = template;
	}

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
	@Column(name = "NAME")
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

}
