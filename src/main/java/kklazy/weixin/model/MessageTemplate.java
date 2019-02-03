package kklazy.weixin.model;

import java.util.Set;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 消息模板
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_MESSAGE_TEMPLATE")
public class MessageTemplate extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -1550458021393803622L;

	/**
	 * 模板名称
	 */
	private String name;

	/**
	 * 该模板的消息类型
	 */
	private String type;

	/**
	 * 模板对应的消息正文
	 */
	private Set<MessageContent> contents;

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
	 * @return the contents
	 */
	@OneToMany(mappedBy = "template", fetch = FetchType.LAZY)
	public Set<MessageContent> getContents() {
		return contents;
	}

	/**
	 * @param contents the contents to set
	 */
	public void setContents(Set<MessageContent> contents) {
		this.contents = contents;
	}
	
}
