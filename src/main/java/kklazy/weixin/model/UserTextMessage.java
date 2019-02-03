package kklazy.weixin.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * 接收用户发送的文本消息
 * 消息类型，此时固定为：text
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_USER_TEXT_MESSAGE")
public class UserTextMessage extends DefaultMessage {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8769349881840009015L;


	/**
	 * 文本消息内容
	 */
	private String content;

	/**
	 * @return the content
	 */
	@Column(name = "CONTENT")
	public String getContent() {
		return content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		this.content = content;
	}

}
