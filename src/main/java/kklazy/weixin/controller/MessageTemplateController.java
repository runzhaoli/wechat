package kklazy.weixin.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.service.PageService;
import kklazy.weixin.model.MessageTemplate;
import kklazy.weixin.service.MessageTemplateService;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.MESSAGE_TEMPLATE_NAMESPACE)
public class MessageTemplateController extends BasePageController<MessageTemplate, String> {

	@Autowired
	private MessageTemplateService messageTemplateService;

	@Override
	public PageService<MessageTemplate, String> pageservice() {
		return messageTemplateService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/messagetemplate";
	}

}
