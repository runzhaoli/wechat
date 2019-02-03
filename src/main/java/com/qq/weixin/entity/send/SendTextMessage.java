package com.qq.weixin.entity.send;

public class SendTextMessage {

	private String touser;

	private String toparty;

	private String totag;

	private String msgtype = "text";

	private String agentid;

	private SendTextContent text;

	private String safe = "0";

	/**
	 * @return the touser
	 */
	public String getTouser() {
		return touser;
	}

	/**
	 * @param touser the touser to set
	 */
	public void setTouser(String touser) {
		this.touser = touser;
	}

	/**
	 * @return the toparty
	 */
	public String getToparty() {
		return toparty;
	}

	/**
	 * @param toparty the toparty to set
	 */
	public void setToparty(String toparty) {
		this.toparty = toparty;
	}

	/**
	 * @return the totag
	 */
	public String getTotag() {
		return totag;
	}

	/**
	 * @param totag the totag to set
	 */
	public void setTotag(String totag) {
		this.totag = totag;
	}

	/**
	 * @return the msgtype
	 */
	public String getMsgtype() {
		return msgtype;
	}

	/**
	 * @param msgtype the msgtype to set
	 */
	public void setMsgtype(String msgtype) {
		this.msgtype = msgtype;
	}

	/**
	 * @return the agentid
	 */
	public String getAgentid() {
		return agentid;
	}

	/**
	 * @param agentid the agentid to set
	 */
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	/**
	 * @return the text
	 */
	public SendTextContent getText() {
		return text;
	}

	/**
	 * @param text the text to set
	 */
	public void setText(SendTextContent text) {
		this.text = text;
	}

	/**
	 * @return the safe
	 */
	public String getSafe() {
		return safe;
	}

	/**
	 * @param safe the safe to set
	 */
	public void setSafe(String safe) {
		this.safe = safe;
	}

}
