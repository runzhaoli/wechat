package kklazy.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.service.PageService;
import kklazy.weixin.model.WechatAccount;
import kklazy.weixin.service.WechatAccountService;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.WECHAT_ACCOUNT_NAMESPACE)
public class WechatAccountController extends BasePageController<WechatAccount, String> {

	@Autowired
	private WechatAccountService wechatAccountService;

	@Override
	public PageService<WechatAccount, String> pageservice() {
		return wechatAccountService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/wechataccount";
	}

}
