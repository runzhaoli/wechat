package kklazy.weixin.model;

import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.OrderBy;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 微信公众号的菜单
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_MENU")
public class WechatMenu extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -4367263823880947072L;

	/**
	 * 该菜单关联的企业号ID
	 */
	private String accountId;

	/**
	 * 菜单名称
	 */
	private String name;

	/**
	 * 菜单类型
	 */
	private String type;

	/**
	 * 菜单KEY
	 */
	private String key;

	/**
	 * 菜单URL(适用于view类型的菜单)
	 */
	private String url;

	/**
	 * 菜单关联的消息模板
	 */
	private MessageTemplate template;

	/**
	 * 父菜单
	 */
	private WechatMenu parent;

	/**
	 * 子菜单
	 */
	private Set<WechatMenu> sublevel;

	/**
	 * 排序
	 */
	private Integer sort;

	/**
	 * @return the accountId
	 */
	@Column(name = "ACCOUNT_ID")
	public String getAccountId() {
		return accountId;
	}

	/**
	 * @param accountId the accountId to set
	 */
	public void setAccountId(String accountId) {
		this.accountId = accountId;
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
	 * @return the key
	 */
	@Column(name = "MENU_KEY")
	public String getKey() {
		return key;
	}

	/**
	 * @param key the key to set
	 */
	public void setKey(String key) {
		this.key = key;
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
	 * @return the template
	 */
	@OneToOne(targetEntity = MessageTemplate.class, cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "TEMPLATE_ID", nullable = true)
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

	/**
	 * @return the parent
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "PARENT_ID")
	public WechatMenu getParent() {
		return parent;
	}

	/**
	 * @param parent the parent to set
	 */
	public void setParent(WechatMenu parent) {
		this.parent = parent;
	}

	/**
	 * @return the sublevel
	 */
	@OneToMany(mappedBy = "parent", fetch = FetchType.LAZY)
	@OrderBy(value = "sort DESC")
	public Set<WechatMenu> getSublevel() {
		return sublevel;
	}

	/**
	 * @param sublevel the sublevel to set
	 */
	public void setSublevel(Set<WechatMenu> sublevel) {
		this.sublevel = sublevel;
	}

}
