package kklazy.common.constants;

/**
 * 常量
 * 
 * @author kk
 */
public class ContextConstants {

	/**
	 * 优秀员工投票
	 */
	public static final String TYPE_1 = "excellentemployee";

	/**
	 * 优秀团队投票
	 */
	public static final String TYPE_2 = "excellentgroup";

	/**
	 * 字典(Dictionary)常量分组
	 * Dictionary Group
	 */
	public static final class GROUP {
		/** 资源类型 */
		public static final String RESOURCE_TYPE = "RESOURCE_TYPE";
		/** 分页信息 */
		public static final String PAGE_SIZE = "PAGE_SIZE";
		/** 状态 */
		public static final String STATUS = "STATUS";
		/** 性别 */
		public static final String GENDER = "GENDER";
		/** 用户类型 */
		public static final String EMPLOYEE_TYPE = "EMPLOYEE_TYPE";
		/** 微信菜单类型 */
		public static final String WECHAT_MENU_TYPE = "WECHAT_MENU_TYPE";
		/** 微信菜单返回的消息模板所属类型 */
		public static final String MESSAGE_TEMPLATE_TYPE = "MESSAGE_TEMPLATE_TYPE";
		/** 证明类型,1.在职证明,2.收入证明 */
		public static final String PROOF_TYPE = "PROOF_TYPE";
		/** 接收类型,1.顺丰快递,2.公司自取 */
		public static final String RECEIVE_TYPE = "RECEIVE_TYPE";
		/** 开证明——审核状态 */
		public static final String CERTIFY_AUDIT_STATUS = "CERTIFY_AUDIT_STATUS";
		/** 推荐人才——审核状态 */
		public static final String RECOMMEND_AUDIT_STATUS = "RECOMMEND_AUDIT_STATUS";
		/** 文章类型 */
		public static final String ARTICLE_TYPE = "ARTICLE_TYPE";
		/** 投诉与建议 */
		public static final String COMPLAINT_PROPOSAL_TYPE = "COMPLAINT_PROPOSAL_TYPE";
		/** 企业号、服务号 类型*/
		public static final String WECHAT_ACCOUNT_TYPE = "WECHAT_ACCOUNT_TYPE";
		/** 积分类型 */
		public static final String POINT_TYPE = "POINT_TYPE";
		/** 活动类型 */
		public static final String ACTIVITY_ITEM_TYPE = "ACTIVITY_ITEM_TYPE";
		/** 消息状态 */
		public static final String USER_MESSAGE_TYPE = "USER_MESSAGE_TYPE";
		
	}

	/**
	 * 其他都是GROUP下的子组
	 * Dictionary Code
	 */

	/**
	 * 菜单资源类型
	 *
	 */
	public static final class RESOURCE_TYPE {
		/** 菜单 */
		public static final String TYPE_MENU = "TYPE_MENU";
		/** 超链接 */
		public static final String TYPE_URL = "TYPE_URL";
		/** 按钮 */
		public static final String TYPE_BUTTON = "TYPE_BUTTON";
	}

	/**
	 * 每页显示数据条数
	 */
	public static final class PAGE_SIZE {
		/** A：默认10条、实际以数据字典配置为准 */
		public static final String A = "A";
		/** B：默认20条、实际以数据字典配置为准 */
		public static final String B = "B";
		/** C：默认30条、实际以数据字典配置为准 */
		public static final String C = "C";
		/** D：默认50条、实际以数据字典配置为准 */
		public static final String D = "D";
		/** E：默认100条、实际以数据字典配置为准 */
		public static final String E = "E";
	}

	/**
	 * 状态
	 */
	public static final class STATUS {
		/** 未开始 */
		public static final String STATUS_01 = "STATUS_01";
		/** 进行中 */
		public static final String STATUS_02 = "STATUS_02";
		/** 已结束 */
		public static final String STATUS_03 = "STATUS_03";
	}

	/**
	 * 性别
	 */
	public static final class GENDER {
		/** 男 */
		public static final String GENDER_MALE = "MALE";
		/** 女 */
		public static final String GENDER_FEMALE = "FEMALE";
	}

	/**
	 * 微信菜单类型
	 */
	public static final class WECHAT_MENU_TYPE {
		/**
		 * 点击推事件
		 * <pre>
		 * 用户点击click类型按钮后
		 * 微信服务器会通过消息接口推送消息类型为event的结构给开发者
		 * 并且带上按钮中开发者填写的key值
		 * 开发者可以通过自定义的key值与用户进行交互
		 * </pre>
		 */
		public static final String CLICK = "click";
		/**
		 * 跳转URL
		 * <pre>
		 * 用户点击view类型按钮后
		 * 微信客户端将会打开开发者在按钮中填写的网页URL
		 * 可与网页授权获取用户基本信息接口结合
		 * 获得用户基本信息
		 * </pre>
		 */
		public static final String VIEW = "view";
		/**
		 * 扫码推事件
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将调起扫一扫工具
		 * 完成扫码操作后显示扫描结果（如果是URL，将进入URL）
		 * 且会将扫码的结果传给开发者
		 * 开发者可以下发消息
		 * </pre>
		 */
		public static final String SCANCODE_PUSH = "scancode_push";
		/**
		 * 扫码推事件且弹出“消息接收中”提示框
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将调起扫一扫工具
		 * 完成扫码操作后
		 * 将扫码的结果传给开发者
		 * 同时收起扫一扫工具
		 * 然后弹出“消息接收中”提示框
		 * 随后可能会收到开发者下发的消息
		 * </pre>
		 */
		public static final String SCANCODE_WAITMSG = "scancode_waitmsg";
		/**
		 * 弹出系统拍照发图
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将调起系统相机
		 * 完成拍照操作后
		 * 会将拍摄的相片发送给开发者
		 * 并推送事件给开发者
		 * 同时收起系统相机
		 * 随后可能会收到开发者下发的消息
		 * </pre>
		 */
		public static final String PIC_SYSPHOTO = "pic_sysphoto";
		/**
		 * 弹出拍照或者相册发图
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将弹出选择器供用户选择“拍照”或者“从手机相册选择”
		 * 用户选择后即走其他两种流程
		 * </pre>
		 */
		public static final String PIC_PHOTO_OR_ALBUM = "pic_photo_or_album";
		/**
		 * 弹出微信相册发图器
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将调起微信相册
		 * 完成选择操作后
		 * 将选择的相片发送给开发者的服务器
		 * 并推送事件给开发者
		 * 同时收起相册
		 * 随后可能会收到开发者下发的消息
		 * </pre>
		 */
		public static final String PIC_WEIXIN = "pic_weixin";
		/**
		 * 弹出地理位置选择器
		 * <pre>
		 * 用户点击按钮后
		 * 微信客户端将调起地理位置选择工具
		 * 完成选择操作后
		 * 将选择的地理位置发送给开发者的服务器
		 * 同时收起位置选择工具
		 * 随后可能会收到开发者下发的消息
		 * </pre>
		 */
		public static final String LOCATION_SELECT = "location_select";
	}

	/**
	 * 返回给微信菜单请求的消息模板所属类型(发送被动回复消息)
	 */
	public static final class MESSAGE_TEMPLATE_TYPE {
		/** 回复文本消息 */
		public static final String TEXT = "text";
		/** 回复图片消息 */
		public static final String IMAGE = "image";
		/** 回复语音消息 */
		public static final String VOICE = "voice";
		/** 回复视频消息 */
		public static final String VIDEO = "video";
		/** 回复音乐消息 */
		public static final String MUSIC = "music";
		/** 回复图文消息 */
		public static final String NEWS = "news";
	}

	/**
	 * 开证明——审核状态
	 */
	public static final class CERTIFY_AUDIT_STATUS {
		/** 0.审核未通过 */
		public static final String AUDIT_STATUS_00 = "0";
		/** 1.申请中 */
		public static final String AUDIT_STATUS_01 = "1";
		/** 2.已受理 */
		public static final String AUDIT_STATUS_02 = "2";
		/** 3.材料准备中 */
		public static final String AUDIT_STATUS_03 = "3";
		/** 4.快递已寄出 */
		public static final String AUDIT_STATUS_04 = "4";
	}

	/**
	 * 推荐人才——审核状态
	 */
	public static final class RECOMMEND_AUDIT_STATUS {
		/** 0.已关闭 */
		public static final String AUDIT_STATUS_00 = "0";
		/** 1.申请中 */
		public static final String AUDIT_STATUS_01 = "1";
		/** 2.面试 */
		public static final String AUDIT_STATUS_02 = "2";
		/** 3.入职 */
		public static final String AUDIT_STATUS_03 = "3";
		/** 4.试用 */
		public static final String AUDIT_STATUS_04 = "4";
		/** 5.转正 */
		public static final String AUDIT_STATUS_05 = "5";
		/** 6.推荐费发放完毕 */
		public static final String AUDIT_STATUS_06 = "6";
	}

	/**
	 * 用户消息状态
	 */
	public static final class USER_MESSAGE_TYPE {
		/** 1.初始消息 */
		public static final String USER_MESSAGE_TYPE_01 = "1";
		/** 2.工牌录入 */
		public static final String USER_MESSAGE_TYPE_02 = "2";
		/** 3.参加23周年庆活动 */
		public static final String USER_MESSAGE_TYPE_03 = "3";
		/** 4.公益活动照片 */
		public static final String USER_MESSAGE_TYPE_04 = "4";
		/** 5.每周一次集体照 */
		public static final String USER_MESSAGE_TYPE_05 = "5";
	}

	/**
	 * 用户消息状态
	 */
	public static final class POINT_TYPE {
		/** 1.活动积分 */
		public static final String POINT_TYPE_01 = "1";
		/** 2.补录积分 */
		public static final String POINT_TYPE_02 = "2";
	}

}
