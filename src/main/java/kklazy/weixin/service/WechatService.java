package kklazy.weixin.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.qq.weixin.entity.menu.BaseButton;
import com.qq.weixin.entity.menu.Button;
import com.qq.weixin.entity.menu.ClickButton;
import com.qq.weixin.entity.menu.ComplexButton;
import com.qq.weixin.entity.menu.ViewButton;
import com.qq.weixin.entity.response.ResponseArticleContent;
import com.qq.weixin.entity.response.ResponseArticleMessage;
import com.qq.weixin.entity.response.ResponseTextMessage;
import com.qq.weixin.entity.send.SendTextContent;
import com.qq.weixin.entity.send.SendTextMessage;
import com.qq.weixin.entity.wechatxml.WechatContext;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import kklazy.common.constants.ContextConstants;
import kklazy.common.constants.WechatConstants;
import kklazy.common.constants.WechatUrlConstants;
import kklazy.newtouch.model.Staff;
import kklazy.newtouch.service.StaffService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.SupportModel;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.DateUtils;
import kklazy.persistence.utils.StringUtils;
import kklazy.utils.WechatHttpUtils;
import kklazy.utils.WechatXmlUtil;
import kklazy.weixin.model.DefaultMessage;
import kklazy.weixin.model.MessageContent;
import kklazy.weixin.model.MessageTemplate;
import kklazy.weixin.model.UserImageMessage;
import kklazy.weixin.model.UserTextMessage;
import kklazy.weixin.model.WechatAccount;
import kklazy.weixin.model.WechatMenu;
import net.sf.json.JSONObject;

/**
 * 微信公众平台的接口访问集合
 * 
 * @author kk
 */
@Service("wechatService")
@Transactional(rollbackFor = Exception.class)
public class WechatService extends DefaultService<SupportModel, String> {

	@Autowired
	private WechatAccountService wechatAccountService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private WechatMenuService wechatMenuService;

	@Autowired
	private UserTextMessageService userTextMessageService;

	@Autowired
	private UserImageMessageService userImageMessageService;

	/**
	 * @param account
	 * @return
	 * @throws Exception
	 */
	public String getAccessToken(WechatAccount account) throws Exception {
		if (account == null) {
			return null;
		}
		account = wechatAccountService.findBy(account.getId());
		/**
		 * 已经存在AccessToken,需要判断是否超时
		 */
		if (StringUtils.isNotBlank(account.getAccesstoken())) {

			// 当前时间
			long now = DateUtils.currentDate().getTime();
			// 获取AccessToken的时间
			long tokentime = account.getTokentime() == null ? 0 : account.getTokentime().getTime();
			// 服务器上获取的超时时间单位是秒,需要 * 1000 转换成毫秒
			long expires = account.getExpiresin() * 1000;
			/**
			 * 判断AccessToken是否超时
			 */
			if (now - tokentime <= expires) {
				return account.getAccesstoken();
			}
		}

		/**
		 * 通过凭证(Appid)和密钥(Appsecret)重新获取AccessToken
		 */
		String url = WechatUrlConstants.QY_ACCESS_TOKEN_URL;
		url = url.replace(WechatConstants.APPID, account.getAppid());
		url = url.replace(WechatConstants.APPSECRET, account.getAppsecret());

		JSONObject object = WechatHttpUtils.https(url, WechatHttpUtils.GET, null);

		/**
		 * 获取AccessToken失败
		 */
		if (object == null) {
			return null;
		}
		try {
			account.setAccesstoken(object.getString("access_token"));
			account.setTokentime(DateUtils.currentDate());
			account.setExpiresin(object.getInt("expires_in"));
			wechatAccountService.merge(account);
		} catch (Exception e) {
			Logger.getLogger(getClass()).error("获取Token失败,errcode: " + object.getString("errcode") + ", errmsg: " + object.getString("errmsg"));
			return null;
		}

		return account.getAccesstoken();
	}

	/**
	 * 通过accessToken和用户OPENID(code)获取用户工号(EMPNO)
	 * 
	 * @param accessToken
	 * @param code
	 * @return
	 */
	public String getUserInfo(WechatAccount account, String code) throws Exception {
		String accesstoken = getAccessToken(account);
		/**
		 * 通过accessToken和用户OPENID(code)获取用户工号(EMPNO)
		 */
		WechatAccount temp = wechatAccountService.findBy(account.getId());
		String url = WechatUrlConstants.QY_GET_USER_INFO_URL;
		url = url.replace(WechatConstants.ACCESS_TOKEN, accesstoken);
		url = url.replace(WechatConstants.CODE, code);
		url = url.replace(WechatConstants.AGENTID, temp.getAgentid());

		JSONObject object = WechatHttpUtils.https(url, WechatHttpUtils.GET, null);

		return object.getString("UserId");
	}

	/**
	 * @param account
	 * @throws Exception
	 */
	public void sameMenu(final WechatAccount account) throws Exception {
		List<WechatMenu> menus = wechatMenuService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("accountId", account.getId()));
				criteria.add(Restrictions.isNull("parent"));
				return criteria;
			}
		});

		String accesstoken = getAccessToken(account);

		if (StringUtils.isBlank(accesstoken)) {
			Logger.getLogger(getClass()).error("同步菜单时获取ACCESS_TOKEN出错!");
			return ;
		}

//		WechatAccount temp = wechatAccountService.findBy(account.getId());
		String url = WechatUrlConstants.QY_MENU_CREATE_URL;
		url = url.replace(WechatConstants.ACCESS_TOKEN, accesstoken);
		url = url.replace(WechatConstants.AGENTID, account.getAgentid());

		Button button = new Button();

		BaseButton firstbuttons[] = new BaseButton[menus.size()];
		for (int i = 0; i < menus.size(); i++) {
			WechatMenu menu = menus.get(i);

			if (menu.getSublevel() == null || menu.getSublevel().isEmpty()) {
				/**
				 * 无子菜单,直接添加一级菜单
				 */
				if (ContextConstants.WECHAT_MENU_TYPE.CLICK.equals(menu.getType())) {
					ClickButton click = new ClickButton();
					click.setName(menu.getName());
					click.setType(menu.getType());
					click.setKey(menu.getKey());
					firstbuttons[i] = click;
				}
				if (ContextConstants.WECHAT_MENU_TYPE.VIEW.equals(menu.getType())) {
					ViewButton view = new ViewButton();
					view.setName(menu.getName());
					view.setType(menu.getType());
					view.setUrl(menu.getUrl());
					firstbuttons[i] = view;
				}
			} else {
				/**
				 * 添加一级菜单and二级菜单
				 */
				ComplexButton complexButton = new ComplexButton();
				complexButton.setName(menu.getName());

				List<WechatMenu> sublevel = new ArrayList<WechatMenu>(menu.getSublevel());

				BaseButton secondbuttons[] = new BaseButton[sublevel.size()];
				for (int j = 0; j < sublevel.size(); j++) {
					if (ContextConstants.WECHAT_MENU_TYPE.CLICK.equals(menu.getType())) {
						ClickButton click = new ClickButton();
						click.setName(menu.getName());
						click.setType(menu.getType());
						click.setKey(menu.getKey());
						secondbuttons[j] = click;
					}
					if (ContextConstants.WECHAT_MENU_TYPE.VIEW.equals(menu.getType())) {
						ViewButton view = new ViewButton();
						view.setName(menu.getName());
						view.setType(menu.getType());
						view.setUrl(menu.getUrl());
						secondbuttons[j] = view;
					}
				}
				complexButton.setSub_button(secondbuttons);
				firstbuttons[i] = complexButton;
			}
		}

		button.setButton(firstbuttons);
		JSONObject json = JSONObject.fromObject(button);

		Logger.getLogger(getClass()).info("button json:" + json);
		JSONObject object = WechatHttpUtils.https(url, WechatHttpUtils.POST, json.toString());

		if (object == null || object.getInt("errcode") != 0) {
			String message = "同步菜单数据出错!";
			message += object != null ? ",errcode: " + object.getString("errcode") + ", errmsg: " + object.getString("errmsg") : "";
			Logger.getLogger(getClass()).error(message);
		}

	}

	/**
	 * @param account
	 * @param userid
	 * @param content
	 * @throws Exception
	 */
	public void messageSend(final WechatAccount account, String userid, String content) throws Exception {

		String accesstoken = getAccessToken(account);

		if (StringUtils.isBlank(accesstoken)) {
			Logger.getLogger(getClass()).error("同步菜单时获取ACCESS_TOKEN出错!");
			return ;
		}

		String url = WechatUrlConstants.QY_MESSAGE_SEND_URL;
		url = url.replace(WechatConstants.ACCESS_TOKEN, accesstoken);

		SendTextMessage text = new SendTextMessage();
		text.setAgentid(account.getAgentid());
		text.setTouser(userid);
		text.setText(new SendTextContent(content));
		
		JSONObject json = JSONObject.fromObject(text);

		Logger.getLogger(getClass()).info("button json:" + json);
		JSONObject object = WechatHttpUtils.https(url, WechatHttpUtils.POST, json.toString());

		if (object == null || object.getInt("errcode") != 0) {
			String message = "发送消息出错!";
			message += object != null ? ",errcode: " + object.getString("errcode") + ", errmsg: " + object.getString("errmsg") : "";
			Logger.getLogger(getClass()).error(message);
		}
	}

	/**
	 * @param request
	 * @param wxbmc
	 * @return
	 * @throws Exception
	 */
	public String wechat(HttpServletRequest request, WXBizMsgCrypt wxbmc) throws Exception {
		WechatContext context = assembleWechatContext(WechatXmlUtil.parseXml(request, wxbmc));
		try {
			String retval = handle(context);
			Staff user = staffService.findByEmpno(context.getFromUserName());
			request.getSession().setAttribute("USER", user);
			return retval;
		} catch (Exception e) {
			e.printStackTrace();
			return assembleTextMessage(context, null, "暂时无法回复您的消息哟~");
		}
	}

	/**
	 * 组装微信请求信息
	 * 
	 * @param content
	 * @return
	 * @throws Exception
	 */
	public WechatContext assembleWechatContext(Map<String, String> content) throws Exception {
		WechatContext retval = new WechatContext();

		// 公共部分
		retval.setToUserName(content.get("ToUserName"));				// 企业号CorpID
		retval.setFromUserName(content.get("FromUserName"));			// 成员UserID
		retval.setCreateTime(Long.valueOf(content.get("CreateTime")));	// 消息创建时间（整型）
		retval.setMsgType(content.get("MsgType"));						// 消息类型
		retval.setAgentID(Long.valueOf(content.get("AgentID")));		// 企业应用的id，整型

		// 接收普通消息
		retval.setMsgId(content.get("MsgId"));							// 消息ID
		retval.setContent(content.get("Content"));						// 消息内容
		retval.setPicUrl(content.get("PicUrl"));						// 图片链接
		retval.setMediaId(content.get("MediaId"));						// 图片媒体文件id，可以调用获取媒体文件接口拉取数据

		// 接收事件
		retval.setEventType(content.get("Event"));						// 接收事件
		retval.setEventKey(content.get("EventKey"));					// 事件KEY值

		return retval;
	}

	/**
	 * 处理微信请求信息
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	public String handle(final WechatContext context) throws Exception {
		String retval = StringUtils.EMPTY;
		/**
		 * 文本消息
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_TEXT.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:文本[" + WechatXmlUtil.REQ_MESSAGE_TYPE_TEXT + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			WechatAccount account = wechatAccountService.findBy(context.getToUserName(), String.valueOf(context.getAgentID()));
			UserTextMessage message = new UserTextMessage();
			message.setAccount(account);
			assembleMessage(context, message);
			message.setContent(context.getContent());	// 文本消息内容
			message.setCreate(DateUtils.currentDate());
			userTextMessageService.merge(message);
			return doText(context, "系统已经收到您的消息,暂时无人回复喔~(您可通过[人事服务小助手]自助查看对应人员联系方式,希望可以帮到您~)");
		}
		/**
		 * 图片消息
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_IMAGE.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:图片[" + WechatXmlUtil.REQ_MESSAGE_TYPE_IMAGE + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			UserImageMessage message = new UserImageMessage();
			assembleMessage(context, message);
			message.setPicUrl(context.getPicUrl());		// 图片链接
			message.setMediaId(context.getMediaId());	// 图片媒体文件id，可以调用获取媒体文件接口拉取数据
			try {
				WechatAccount account = wechatAccountService.findBy(context.getToUserName(), String.valueOf(context.getAgentID()));
				String accesstoken = getAccessToken(account);
				if (StringUtils.isBlank(accesstoken)) {
					Logger.getLogger(getClass()).error("获取用户上传图片文件时获取ACCESS_TOKEN出错!");
				}
				String url = WechatUrlConstants.QY_GET_MEDIA_URL;
				url = url.replace(WechatConstants.ACCESS_TOKEN, accesstoken);
				url = url.replace(WechatConstants.MEDIA_ID, context.getMediaId());
				String localUrl = WechatHttpUtils.download(url, WechatHttpUtils.GET);
				message.setLocalUrl(localUrl);
				message.setAccount(account);
			} catch (Exception e) {
				e.printStackTrace();
			}
			message.setCreate(DateUtils.currentDate());
			userImageMessageService.merge(message);
			return doText(context, "系统已经收到您的图片[ID：" + context.getMsgId() + "]啦~请稍等,后台会有专人处理...");
		}
		/**
		 * 地理位置消息
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_LOCATION.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:地理位置[" + WechatXmlUtil.REQ_MESSAGE_TYPE_LOCATION + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			
		}
		/**
		 * 链接消息
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_LINK.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:链接[" + WechatXmlUtil.REQ_MESSAGE_TYPE_LINK + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			
		}
		/**
		 * 音频消息
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_VOICE.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:音频[" + WechatXmlUtil.REQ_MESSAGE_TYPE_VOICE + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			
		}
		/**
		 * 事件推送
		 */
		if (WechatXmlUtil.REQ_MESSAGE_TYPE_EVENT.equals(context.getMsgType())) {
			Logger.getLogger(getClass()).info("请求消息类型:事件推送[" + WechatXmlUtil.REQ_MESSAGE_TYPE_EVENT + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
			/**
			 * 订阅事件
			 */
			if (WechatXmlUtil.EVENT_TYPE_SUBSCRIBE.equals(context.getEventType())) {
				Logger.getLogger(getClass()).info("请求事件类型:(事件推送)订阅[" + WechatXmlUtil.EVENT_TYPE_SUBSCRIBE + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
				return doEventSubscribe(context);
			}
			/**
			 * 取消订阅事件
			 */
			if (WechatXmlUtil.EVENT_TYPE_UNSUBSCRIBE.equals(context.getEventType())) {
				Logger.getLogger(getClass()).info("请求事件类型:(事件推送)取消订阅[" + WechatXmlUtil.EVENT_TYPE_UNSUBSCRIBE + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
				
			}
			/**
			 * 自定义菜单点击事件
			 */
			if (WechatXmlUtil.EVENT_TYPE_CLICK.equals(context.getEventType())) {
				Logger.getLogger(getClass()).info("请求事件类型:(事件推送)自定义菜单点击事件[" + WechatXmlUtil.EVENT_TYPE_CLICK + "],请求AgentID:[" + context.getAgentID() + "],请求人:" + context.getFromUserName());
				return doEventClick(context);
			}
		}
		return retval;
	}

	/**
	 * @param context
	 * @param message
	 */
	protected void assembleMessage(WechatContext context, DefaultMessage message) {
		message.setCorpID(context.getToUserName());					// 企业号CorpID
		message.setUserID(context.getFromUserName());				// 成员UserID
		try {
			message.setCreate(new Date(context.getCreateTime()));	// 消息创建时间（整型）
		} catch (Exception e) {
			message.setCreate(DateUtils.currentDate());				// 创建时间出错，则使用当前时间
		}
		message.setMsgType(context.getMsgType());					// 消息类型，此时固定为：text
		message.setMsgId(context.getMsgId());						// 消息id，64位整型
		message.setAgentID(context.getAgentID());					// 企业应用的id，整型
	}

	/**
	 * 处理文本消息
	 * 
	 * @param context
	 * @return
	 */
	protected String doText(WechatContext context, String message) throws Exception {
		return assembleTextMessage(context, null, message);
	}

	/**
	 * 处理订阅事件消息
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected String doEventSubscribe(WechatContext context) throws Exception {

		return "EventSubscribe";
	}

	/**
	 * 处理自定义菜单点击事件
	 * 
	 * @param context
	 * @return
	 * @throws Exception
	 */
	protected String doEventClick(final WechatContext context) throws Exception {
		List<WechatMenu> menus = wechatMenuService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("key", context.getEventKey()));
				return criteria;
			}
		});
		if (menus != null && !menus.isEmpty()) {
			WechatMenu menu = menus.get(0);
			MessageTemplate template = menu.getTemplate();
			/**
			 * 回复文本消息
			 */
			if (WechatXmlUtil.RESP_MESSAGE_TYPE_TEXT.equals(template.getType())) {
				return assembleTextMessage(context, template, StringUtils.EMPTY);
			}
			/**
			 * 回复图文消息
			 */
			if (WechatXmlUtil.RESP_MESSAGE_TYPE_NEWS.equals(template.getType())) {
				return assembleArticleMessage(context, template);
			}
		}
		return StringUtils.EMPTY;
	}

	/**
	 * 组装文本消息
	 * 
	 * @param context	微信请求信息
	 * @param template	对应的回复模板
	 * @param message	对应的回复消息(如果有回复消息,则优先回复消息)
	 * @return
	 * @throws Exception
	 */
	protected String assembleTextMessage(WechatContext context, MessageTemplate template, String message) throws Exception {
		ResponseTextMessage text = new ResponseTextMessage(context.getToUserName(), context.getFromUserName());
		if (StringUtils.isNotBlank(message)) {
			text.setContent(message);
		} else {
			for (MessageContent content : template.getContents()) {
				text.setContent(content.getDescription());
			}
		}
		return WechatXmlUtil.textMessageToXml(text);
	}

	/**
	 * 回复图文消息
	 * 
	 * @param context	微信请求信息
	 * @param template	对应的回复模板
	 * @return
	 * @throws Exception
	 */
	protected String assembleArticleMessage(WechatContext context, MessageTemplate template) throws Exception {
		ResponseArticleMessage article = new ResponseArticleMessage(context.getToUserName(), context.getFromUserName());

		article.setArticles(new ArrayList<ResponseArticleContent>());
		article.setArticleCount(template.getContents().size());

		for (MessageContent message : template.getContents()) {
			ResponseArticleContent content = new ResponseArticleContent();
			content.setTitle(message.getTitle());
			content.setPicUrl(message.getImg());
			content.setDescription(message.getDescription());
			content.setUrl(message.getUrl());
			article.getArticles().add(content);
		}
		return WechatXmlUtil.articleMessageToXml(article);
	}

}
