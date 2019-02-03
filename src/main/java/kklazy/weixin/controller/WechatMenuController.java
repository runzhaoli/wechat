package kklazy.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.weixin.model.MessageTemplate;
import kklazy.weixin.model.WechatAccount;
import kklazy.weixin.model.WechatMenu;
import kklazy.weixin.service.MessageTemplateService;
import kklazy.weixin.service.WechatAccountService;
import kklazy.weixin.service.WechatMenuService;
import kklazy.weixin.service.WechatService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.WECHAT_MENU_NAMESPACE)
public class WechatMenuController extends BasePageController<WechatMenu, String> {

	@Autowired
	private WechatMenuService wechatMenuService;

	@Autowired
	private WechatAccountService wechatAccountService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private MessageTemplateService messageTemplateService;

	@Override
	public PageService<WechatMenu, String> pageservice() {
		return wechatMenuService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/wechatmenu";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final WechatMenu entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page<WechatMenu> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {

					if (entity instanceof DefaultModel) {
						criteria.add(Restrictions.eq("enabled", ((DefaultModel) entity).getEnabled()));
						criteria.add(Restrictions.eq("delete", ((DefaultModel) entity).getDelete()));
					}
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.DESC, "create"))
				)
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#modifyhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.io.Serializable)
	 */
	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		WechatMenu entity = pageservice().findBy(id);
		Iterable<MessageTemplate> templates = messageTemplateService.findAll();
		modelMap.put("parent", entity != null && entity.getParent() != null ? entity.getParent() : null);
		modelMap.put("templates", templates);
		super.modifyhandler(request, response, modelMap, id);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#createhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		Iterable<MessageTemplate> templates = messageTemplateService.findAll();
		modelMap.put("templates", templates);
		super.createhandler(request, response, modelMap);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#createhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.io.Serializable)
	 */
	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		WechatMenu parent = pageservice().findBy(id);
		Iterable<MessageTemplate> templates = messageTemplateService.findAll();
		modelMap.put("parent", parent);
		modelMap.put("templates", templates);
		super.createhandler(request, response, modelMap, id);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#commithandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, WechatMenu entity) {
		if (entity.getParent() != null && StringUtils.isBlank(entity.getParent().getId())) {
			entity.setParent(null);
		}
		if (entity.getTemplate() != null && StringUtils.isBlank(entity.getTemplate().getId())) {
			entity.setTemplate(null);
		}
		return super.commithandler(request, response, modelMap, entity);
	}

	/**
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param accountId
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.SYNC)
	@ResponseBody
	public CommonResponse sync(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String accountId) throws Exception {
		CommonResponse retval = CommonResponse.FAILURE();
		try {
			WechatAccount account = wechatAccountService.findBy(accountId);
			wechatService.sameMenu(account);
			retval = CommonResponse.SUCCESS();
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
			retval.setMessage(e.getMessage());
		}
		return retval;
	}

}
