package com.qq.weixin.entity.support;

/**
 * @author kk
 */
public class BaseResponseMessage {

	/**
	 * 接收方帐号(OpenID)
	 */
	private String ToUserName;

	/**
	 * 开发者微信号
	 */
	private String FromUserName;

	/**
	 * 消息创建时间
	 */
	private long CreateTime;

	/**
	 * 消息类型（text/music/news）
	 */
	private String MsgType;

	/**
	 * @return the toUserName
	 */
	public String getToUserName() {
		return ToUserName;
	}

	/**
	 * @param toUserName the toUserName to set
	 */
	public void setToUserName(String toUserName) {
		ToUserName = toUserName;
	}

	/**
	 * @return the fromUserName
	 */
	public String getFromUserName() {
		return FromUserName;
	}

	/**
	 * @param fromUserName the fromUserName to set
	 */
	public void setFromUserName(String fromUserName) {
		FromUserName = fromUserName;
	}

	/**
	 * @return the createTime
	 */
	public long getCreateTime() {
		return CreateTime;
	}

	/**
	 * @param createTime the createTime to set
	 */
	public void setCreateTime(long createTime) {
		CreateTime = createTime;
	}

	/**
	 * @return the msgType
	 */
	public String getMsgType() {
		return MsgType;
	}

	/**
	 * @param msgType the msgType to set
	 */
	public void setMsgType(String msgType) {
		MsgType = msgType;
	}
    
}
