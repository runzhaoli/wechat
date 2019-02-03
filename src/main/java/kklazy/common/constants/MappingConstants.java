package kklazy.common.constants;

/**
 * 将所有页面请求的 URL Mapping 地址都指定为常量
 * 
 * @author kk
 */
public class MappingConstants {

	/**
	 * 所有Controller的NameSpaces
	 */
	public class NameSpaces {

		// Security

		public static final String TEMPLATE_NAMESPACE = "template";
		public static final String SECURITY_NAMESPACE = "security";
		public static final String KAPTCHA_NAMESPACE  = "kaptcha";
		public static final String CONSOLE_NAMESPACE = "console";
		public static final String ORGANIZATION_NAMESPACE = "organization";
		public static final String RESOURCE_NAMESPACE = "resource";
		public static final String ROLE_NAMESPACE = "role";
		public static final String EMPLOYEE_NAMESPACE = "employee";
		public static final String USER_NAMESPACE = "user";
		public static final String DICTIONARY_NAMESPACE = "dictionary";
		public static final String SYSTEM_CONFIG_NAMESPACE = "systemconfig";
		public static final String WECHAT_ACCOUNT_NAMESPACE = "wechataccount";
		
		// Other

		public static final String ACCUMULATION_FUND_NAMESPACE = "accumulationfund";
		public static final String ARTICLE_NAMESPACE = "article";
		public static final String CERTIFY_NAMESPACE = "certify";
		public static final String COMPLAINT_PROPOSAL_NAMESPACE = "complaintproposal";
		public static final String RECOMMEND_NAMESPACE = "recommend";
		public static final String SLIDER_LOOP_NAMESPACE = "sliderloop";
		public static final String STAFF_NAMESPACE = "staff";
		public static final String POINT_NAMESPACE = "point";
		public static final String ACTIVITY_NAMESPACE = "activity";
		public static final String ACTIVITY_ITEM_NAMESPACE = "activityitem";

		// weixin

		public static final String MESSAGE_TEMPLATE_NAMESPACE = "messagetemplate";
		public static final String MESSAGE_CONTENT_NAMESPACE = "messagecontent";
		public static final String WECHAT_MENU_NAMESPACE = "wechatmenu";
		public static final String USER_TEXT_MESSAGE_NAMESPACE = "usertextmessage";
		public static final String USER_IMAGE_MESSAGE_NAMESPACE = "userimagemessage";

		// client

		public static final String HRC_WECHAT_NAMESPACE = "hrcwechat";
		public static final String ACTIVITIES_WECHAT_NAMESPACE = "activitieswechat";
		public static final String SEATING_CHART_NAMESPACE = "seatingchart";
		public static final String LUCKY_NAMESPACE = "lucky";
		
		
		
	}
	
	/**
	 * 所有Mapping地址
	 */
	public class UrlMapping {

		// URL

		public static final String INIT = "init";
		public static final String OPERATION = "operation";
		public static final String SYNC = "sync";
		public static final String ACTIVITY_ITEM_DATA = "activityitemdata";


		// WECHAT

		public static final String WECHAT = "wechat";
		public static final String LOGIN = "login";
		public static final String INDEX = "index";
		public static final String SIGNUP = "signup";

		public static final String VOTE_PHOTO = "votephoto";
		public static final String VOTE_PHOTO_COMMIT = "votephotocommit";

		public static final String POINTS = "points";
		public static final String ABOUT_TEAM = "aboutteam";
		public static final String RESIGNATION = "resignation";

		public static final String ACCUMULATIONFUND = "accumulationfund";
		public static final String ACCUMULATIONFUND_SEARCH = "accumulationfundsearch";
		public static final String ARTICLE_LIST = "articlelist";

		public static final String CERTIFY_SELECT = "certifyselect";
		public static final String CERTIFY_SEARCH = "certifysearch";
		public static final String CERTIFY_SAVE = "certifysave";
		public static final String CERTIFY_RESULT = "certifyresult";

		public static final String RECOMMEND_SELECT = "recommendselect";
		public static final String RECOMMEND_SEARCH = "recommendsearch";
		public static final String RECOMMEND_SAVE = "recommendsave";
		public static final String RECOMMEND_RESULT = "recommendresult";

		public static final String COMPLAINTPROPOSAL_SELECT = "complaintproposalselect";
		public static final String COMPLAINTPROPOSAL_SEARCH = "complaintproposalsearch";
		public static final String COMPLAINTPROPOSAL_SAVE = "complaintproposalsave";
		public static final String COMPLAINTPROPOSAL_RESULT = "complaintproposalresult";

		public static final String YZL_SEARCH = "yzlsearch";
		public static final String YZL_SAVE = "yzlsave";
		public static final String YZL_RESULT = "yzlresult";

		public static final String DIVISION_RESULT = "divisionresult";

		public static final String EXCELLENT_EMPLOYEE_SELECT = "excellentemployeeselect";

		/**
		 * 基础资源[js、css等]
		 */
		public static final String BASE = "base.kklazy";

		/**
		 * 框架使用资源[js、css等]
		 */
		public static final String FRAMEWORK = "framework.kklazy";

		/**
		 * 额外其他
		 */
		public static final String JSLIDER = "jslider.kklazy";

		/**
		 * 登陆页
		 */

		/**
		 * 验证码
		 */
		public static final String VERIFY = "verify.kklazy";

		/**
		 * 无权访问跳转页
		 */
		public static final String ACCESSDENIED = "accessdenied.kklazy";

		/**
		 * 框架页
		 */
		public static final String CENTER = "center.kklazy";

		/**
		 * 首页
		 */

		/**
		 * 初始化
		 */

		/**
		 * 初始化查询
		 */
		public static final String INIT_SEARCH = "initsearch.kklazy";

		/**
		 * 查询
		 */
		public static final String SEARCH = "search.kklazy";

		/**
		 * 新增 and 修改
		 */
		public static final String MODIFY = "modify.kklazy";

		/**
		 * 提交
		 */
		public static final String COMMIT = "commit.kklazy";

		/**
		 * 添加子项使用，新增都是用modify
		 */
		public static final String ADD = "add.kklazy";

		/**
		 * 操作
		 */

		/**
		 * 上传
		 */

		/**
		 * 下载
		 */
		public static final String DOWNLOAD = "download.kklazy";

		/**
		 * 下载模板
		 */
		public static final String DOWNLOAD_TEMPLATE = "downloadtemplate.kklazy";

		/**
		 * 上传Excel
		 */
		public static final String UPLOAD_EXCEL = "uploadexcel.kklazy";

		/**
		 * 下载Excel
		 */
		public static final String DOWNLOAD_EXCEL = "downloadexcel.kklazy";

		/**
		 * 同步
		 */

		/**
		 * 个人信息
		 */
		public static final String PROFILE = "profile.kklazy";

		/**
		 * 列表
		 */

		/**
		 * 数据
		 */
		public static final String ARTICLE_DATA = "articledata.kklazy";

		/**
		 * 微信消息返回地址
		 */

	}

}
