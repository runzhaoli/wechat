package com.qq.weixin.entity.wechatxml;

/**
 * 微信请求信息
 * 
 * @author kk
 */
public class WechatContext {

	// 公共部分

	/**
	 * 发送方帐号（open_id）
	 */
	private String fromUserName;

	/**
	 * 服务号AccountId、企业号CorpID
	 */
	private String toUserName;

	/**
	 * 消息创建时间（整型）
	 */
	private Long createTime;

	/**
	 * 消息类型
	 */
	private String msgType;

	/**
	 * 企业应用的id，整型。可在应用的设置页面查看
	 */
	private Long agentID;

	// 接收普通消息

	/**
	 * 消息id，64位整型
	 */
	private String msgId;

	/**
	 * 文本消息内容
	 */
	private String content;

	/**
	 * 图片链接
	 */
	private String picUrl;

	/**
	 * 	图片媒体文件id，可以调用获取媒体文件接口拉取数据
	 */
	private String mediaId;

	// 接收事件

	/**
	 * 如果msgType是event的情况下，可以从request中获取事件类型字段
	 */
	private String eventType;

	/**
	 * 如果是自定义菜单，则可以从request中获取请求的菜单key值
	 */
	private String eventKey;

	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return fromUserName;
	}

	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		this.fromUserName = fromUserName;
	}

	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return toUserName;
	}

	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		this.toUserName = toUserName;
	}

	/**
	 * @return the createTime
	 */
	public Long getCreateTime() {
		return createTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(Long createTime) {
		this.createTime = createTime;
	}

	/**
	 * @return the msgType
	 */
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
	 * @return the agentID
	 */
	public Long getAgentID() {
		return agentID;
	}

	/**
	 * @param agentID the agentID to set
	 */
	public void setAgentID(Long agentID) {
		this.agentID = agentID;
	}

	/**
	 * @return the msgId
	 */
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
	 * @return the content
	 */
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
	 * @return the picUrl
	 */
	public String getPicUrl() {
		return picUrl;
	}

	/**
	 * @param picUrl the picUrl to set
	 */
	public void setPicUrl(String picUrl) {
		this.picUrl = picUrl;
	}

	/**
	 * @return the mediaId
	 */
	public String getMediaId() {
		return mediaId;
	}

	/**
	 * @param mediaId the mediaId to set
	 */
	public void setMediaId(String mediaId) {
		this.mediaId = mediaId;
	}

	/**
	 * @return the eventType
	 */
	public String getEventType() {
		return eventType;
	}

	/**
	 * @param eventType the eventType to set
	 */
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}

	/**
	 * @return the eventKey
	 */
	public String getEventKey() {
		return eventKey;
	}

	/**
	 * @param eventKey the eventKey to set
	 */
	public void setEventKey(String eventKey) {
		this.eventKey = eventKey;
	}

}
