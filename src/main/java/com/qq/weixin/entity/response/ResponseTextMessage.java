package com.qq.weixin.entity.response;

import com.qq.weixin.entity.support.BaseResponseMessage;

import kklazy.persistence.utils.DateUtils;
import kklazy.utils.WechatXmlUtil;

/**
 * 响应的文本消息
 * 
 * @author kk
 */
public class ResponseTextMessage extends BaseResponseMessage {

	/**
	 * 文本内容
	 */
	private String Content;

	/**
	 * @param toUserName 接收方帐号(OpenID)
	 * @param fromUserName 开发者微信号
	 */
	public ResponseTextMessage(String toUserName, String fromUserName) {

		setToUserName(fromUserName);
		setFromUserName(toUserName);

		setCreateTime(DateUtils.currentDate().getTime());
		setMsgType(WechatXmlUtil.RESP_MESSAGE_TYPE_TEXT);

	}

	/**
	 * @return the content
	 */
	public String getContent() {
		return Content;
	}

	/**
	 * @param content the content to set
	 */
	public void setContent(String content) {
		Content = content;
	}

}
