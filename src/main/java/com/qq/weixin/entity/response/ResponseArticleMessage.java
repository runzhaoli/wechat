package com.qq.weixin.entity.response;

import java.util.List;

import com.qq.weixin.entity.support.BaseResponseMessage;

import kklazy.persistence.utils.DateUtils;
import kklazy.utils.WechatXmlUtil;

/**
 * 图文消息
 * 
 * @author kk
 */
public class ResponseArticleMessage extends BaseResponseMessage {

	/**
	 * 图文消息个数，限制为10条以内
	 */
	private int ArticleCount;

	/**
	 * 多条图文消息信息，默认第一个item为大图
	 */
	private List<ResponseArticleContent> Articles;

	/**
	 * @param toUserName 接收方帐号(OpenID)
	 * @param fromUserName 开发者微信号
	 */
	public ResponseArticleMessage(String toUserName, String fromUserName) {

		setToUserName(fromUserName);
		setFromUserName(toUserName);

		setCreateTime(DateUtils.currentDate().getTime());
		setMsgType(WechatXmlUtil.RESP_MESSAGE_TYPE_NEWS);

	}

	/**
	 * @return the articleCount
	 */
	public int getArticleCount() {
		return ArticleCount;
	}

	/**
	 * @param articleCount the articleCount to set
	 */
	public void setArticleCount(int articleCount) {
		ArticleCount = articleCount;
	}

	/**
	 * @return the articles
	 */
	public List<ResponseArticleContent> getArticles() {
		return Articles;
	}

	/**
	 * @param articles the articles to set
	 */
	public void setArticles(List<ResponseArticleContent> articles) {
		Articles = articles;
	}
    
}
