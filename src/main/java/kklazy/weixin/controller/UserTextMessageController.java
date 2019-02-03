package kklazy.weixin.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
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

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.weixin.model.UserTextMessage;
import kklazy.weixin.service.UserTextMessageService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.USER_TEXT_MESSAGE_NAMESPACE)
public class UserTextMessageController extends BasePageController<UserTextMessage, String> {

	@Autowired
	private UserTextMessageService userTextMessageService;

	@Override
	public PageService<UserTextMessage, String> pageservice() {
		return userTextMessageService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/usertextmessage";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final UserTextMessage entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page< UserTextMessage > page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getUserID())) {
						criteria.add(Restrictions.eq("userID", entity.getUserID()));
					}
					criteria.add(Restrictions.eq("enabled", entity.getEnabled()));
					criteria.add(Restrictions.eq("delete", entity.getDelete()));
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

}
