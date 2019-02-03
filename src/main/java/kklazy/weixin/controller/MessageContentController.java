package kklazy.weixin.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.service.PageService;
import kklazy.weixin.model.MessageContent;
import kklazy.weixin.model.MessageTemplate;
import kklazy.weixin.service.MessageContentService;
import kklazy.weixin.service.MessageTemplateService;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.MESSAGE_CONTENT_NAMESPACE)
public class MessageContentController extends BasePageController<MessageContent, String> {

	@Autowired
	private MessageContentService messageContentService;

	@Autowired
	private MessageTemplateService messageTemplateService;

	@Override
	public PageService<MessageContent, String> pageservice() {
		return messageContentService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/messagecontent";
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#createhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<MessageTemplate> templates = messageTemplateService.findAll();
		modelMap.put("templates", templates);
		super.createhandler(request, response, modelMap);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#modifyhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.io.Serializable)
	 */
	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		List<MessageTemplate> templates = messageTemplateService.findAll();
		modelMap.put("templates", templates);
		super.modifyhandler(request, response, modelMap, id);
	}

}
