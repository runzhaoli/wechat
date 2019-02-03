package kklazy.common.constants;

/**
 * 微信的请求地址都初始化为常量
 * 
 * @author kk
 */
public class WechatUrlConstants {

	/**
	 * 企业号
	 */

	/**
	 * 使用APPID和APPSECRET调用本接口来获取ACCESS_TOKEN（GET） 限200（次/天）
	 * <pre>
	 * 公众号调用各接口时都需使用ACCESS_TOKEN
	 * ACCESS_TOKEN的存储至少要保留512个字符空间
	 * ACCESS_TOKEN的有效期目前为2个小时
	 * 重复获取将导致上次获取的ACCESS_TOKEN失效
	 * </pre>
	 */
	public final static String QY_ACCESS_TOKEN_URL = "https://qyapi.weixin.qq.com/cgi-bin/gettoken?corpid=APPID&corpsecret=APPSECRET";

	/**
	 * 菜单创建（POST） 限100（次/天）
	 */
	public final static String QY_MENU_CREATE_URL = "https://qyapi.weixin.qq.com/cgi-bin/menu/create?access_token=ACCESS_TOKEN&agentid=AGENTID";

	/**
	 * 获取用户基本信息接口地址
	 */
	public final static String QY_GET_USER_INFO_URL = "https://qyapi.weixin.qq.com/cgi-bin/user/getuserinfo?access_token=ACCESS_TOKEN&code=CODE&agentid=AGENTID";

	/**
	 * 获取临时素材文件
	 * 通过media_id获取图片、语音、视频等文件，协议和普通的http文件下载完全相同。该接口即原"下载多媒体文件"接口
	 */
	public final static String QY_GET_MEDIA_URL = "https://qyapi.weixin.qq.com/cgi-bin/media/get?access_token=ACCESS_TOKEN&media_id=MEDIA_ID";

	/**
	 * 发送消息接口
	 */
	public final static String QY_MESSAGE_SEND_URL = "https://qyapi.weixin.qq.com/cgi-bin/message/send?access_token=ACCESS_TOKEN";



	/**
	 * 服务号
	 */

	/**
	 * 客服接口地址
	 */
//	public final static String SEND_MESSAGE_URL = "https://api.weixin.qq.com/cgi-bin/message/custom/send?access_token=ACCESS_TOKEN";

	/**
	 * 获取服务号粉丝用户列表  ( &next_openid=NEXT_OPENID 不填写该值，默认从第一个获取 )
	 */
//	public final static String GET_USER_LIST_URL = "https://api.weixin.qq.com/cgi-bin/user/get?access_token=ACCESS_TOKEN";

}
