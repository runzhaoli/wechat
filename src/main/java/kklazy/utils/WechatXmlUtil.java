package kklazy.utils;

import java.io.StringReader;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

import com.qq.weixin.entity.response.ResponseArticleContent;
import com.qq.weixin.entity.response.ResponseArticleMessage;
import com.qq.weixin.entity.response.ResponseTextMessage;
import com.qq.weixin.mp.aes.WXBizMsgCrypt;
import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.core.util.QuickWriter;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import com.thoughtworks.xstream.io.xml.PrettyPrintWriter;
import com.thoughtworks.xstream.io.xml.XppDriver;

/**
 * @author kk
 */
public class WechatXmlUtil {

	/**
	 * 返回消息类型：文本
	 */
	public static final String RESP_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 返回消息类型：音乐
	 */
	public static final String RESP_MESSAGE_TYPE_MUSIC = "music";

	/**
	 * 返回消息类型：图文
	 */
	public static final String RESP_MESSAGE_TYPE_NEWS = "news";

	/**
	 * 请求消息类型：文本
	 */
	public static final String REQ_MESSAGE_TYPE_TEXT = "text";

	/**
	 * 请求消息类型：图片
	 */
	public static final String REQ_MESSAGE_TYPE_IMAGE = "image";

	/**
	 * 请求消息类型：链接
	 */
	public static final String REQ_MESSAGE_TYPE_LINK = "link";

	/**
	 * 请求消息类型：地理位置
	 */
	public static final String REQ_MESSAGE_TYPE_LOCATION = "location";

	/**
	 * 请求消息类型：音频
	 */
	public static final String REQ_MESSAGE_TYPE_VOICE = "voice";

	/**
	 * 请求消息类型：推送
	 */
	public static final String REQ_MESSAGE_TYPE_EVENT = "event";

	/**
	 * 事件类型：enter_agent(成员进入应用)
	 */
	public static final String EVENT_TYPE_ENTER_AGENT = "enter_agent";

	/**
	 * 事件类型：subscribe(订阅)
	 */
	public static final String EVENT_TYPE_SUBSCRIBE = "subscribe";

	/**
	 * 事件类型：unsubscribe(取消订阅)
	 */
	public static final String EVENT_TYPE_UNSUBSCRIBE = "unsubscribe";

	/**
	 * 事件类型：CLICK(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_CLICK = "click";

	/**
	 * 事件类型：VIEW(自定义菜单点击事件)
	 */
	public static final String EVENT_TYPE_VIEW = "view";

	/**
	 * 解析微信发来的请求（XML）
	 * 
	 * @param request
	 * @return
	 * @throws Exception
	 */
	@SuppressWarnings("unchecked")
	public static Map<String, String> parseXml(HttpServletRequest request, WXBizMsgCrypt wxcpt) throws Exception {

		Map<String, String> map = new HashMap<String, String>();

		String msg_signature = request.getParameter("msg_signature"); // 加密签名
		String timeStamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数

		int isEnd = 0;
		StringBuffer bufferXml = new StringBuffer();
		while (true) {
			isEnd = request.getReader().read();
			if (isEnd != -1) {
				char c = (char) isEnd;
				bufferXml.append(String.valueOf(c));
			} else {
				break;
			}
		}

		String message = wxcpt.DecryptMsg(msg_signature, timeStamp, nonce, bufferXml.toString());

		Logger.getLogger(WechatXmlUtil.class).info("decrypt message:\n" + message);

		StringReader sr = new StringReader(message);
		SAXReader reader = new SAXReader();
		Document document = reader.read(sr);
		Element root = document.getRootElement();
		List<Element> elementList = root.elements();

		for (Element e : elementList) {
			map.put(e.getName(), e.getText());
		}
		sr.close();
		return map;
	}

	/**
	 * 文本消息对象转换成XML
	 * 
	 * @param message
	 * @return
	 */
	public static String textMessageToXml(ResponseTextMessage message) {
		xstream.alias("xml", message.getClass());
		return xstream.toXML(message);
	}

	/**
	 * 图文消息对象转换成XML
	 * 
	 * @param message
	 * @return
	 */
	public static String articleMessageToXml(ResponseArticleMessage message) {
		xstream.alias("xml", message.getClass());
		xstream.alias("item", new ResponseArticleContent().getClass());
		return xstream.toXML(message);
	}

	/**
	 * 扩展XStream,使其支持CDATA块
	 */
	private static XStream xstream = new XStream(new XppDriver() {
		@Override
		public HierarchicalStreamWriter createWriter(Writer out) {
			return new PrettyPrintWriter(out) {
				boolean cdata = false;

				@Override
				@SuppressWarnings("rawtypes")
				public void startNode(String name, Class clazz) {
					super.startNode(name, clazz);
				}

				@Override
				public void setValue(String text) {
					// 浮点型判断
					Pattern patternInt = Pattern.compile("[0-9]*(\\.?)[0-9]*");
					// 整型判断
					Pattern patternFloat = Pattern.compile("[0-9]+");
					// 如果不为空，且长度小于等于两位或者大于等于八位，且是整数或浮点数，就不要加[CDATA[]了
					if (StringUtils.isNotBlank(text) && (text.length() <= 2 || text.length() >= 8) && (patternInt.matcher(text).matches() || patternFloat.matcher(text).matches())) {
						cdata = false;
					} else {
						cdata = true;
					}
					super.setValue(text);
				}

				@Override
				protected void writeText(QuickWriter writer, String text) {
					if (cdata) {
						writer.write("<![CDATA[");
						writer.write(text);
						writer.write("]]>");
					} else {
						writer.write(text);
					}
				}
			};
		}
	});
}
