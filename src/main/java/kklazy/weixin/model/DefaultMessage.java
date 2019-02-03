package kklazy.weixin.model;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.MappedSuperclass;

import kklazy.persistence.model.DefaultModel;

@MappedSuperclass
public class DefaultMessage extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1502533535428956094L;

	/**
	 * 企业号
	 */
	private WechatAccount account;

	/**
	 * 企业号CorpID
	 */
	private String corpID;

	/**
	 * 成员UserID
	 */
	private String userID;

	/**
	 * 	消息类型
	 */
	private String msgType;

	/**
	 * 消息id，64位整型
	 */
	private String msgId;

	/**
	 * 	企业应用的id，整型。可在应用的设置页面查看
	 */
	private Long agentID;

	/**
	 * @return the account
	 */
	@ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
	@JoinColumn(name = "ACCOUNT_ID")
	public WechatAccount getAccount() {
		return account;
	}

	/**
	 * @param account the account to set
	 */
	public void setAccount(WechatAccount account) {
		this.account = account;
	}

	/**
	 * @return the corpID
	 */
	@Column(name = "CORP_ID")
	public String getCorpID() {
		return corpID;
	}

	/**
	 * @param corpID the corpID to set
	 */
	public void setCorpID(String corpID) {
		this.corpID = corpID;
	}

	/**
	 * @return the userID
	 */
	@Column(name = "USER_ID")
	public String getUserID() {
		return userID;
	}

	/**
	 * @param userID the userID to set
	 */
	public void setUserID(String userID) {
		this.userID = userID;
	}

	/**
	 * @return the msgType
	 */
	@Column(name = "MSG_TYPE")
	public String getMsgType() {
		return msgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		this.msgType = msgType;
	}

	/**
	 * @return the msgId
	 */
	@Column(name = "MSG_ID")
	public String getMsgId() {
		return msgId;
	}

	/**
	 * @param msgId the msgId to set
	 */
	public void setMsgId(String msgId) {
		this.msgId = msgId;
	}

	/**
	 * @return the agentID
	 */
	@Column(name = "AGENT_ID")
	public Long getAgentID() {
		return agentID;
	}

	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(Long agentID) {
		this.agentID = agentID;
	}

}
