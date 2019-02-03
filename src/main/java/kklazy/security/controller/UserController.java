package kklazy.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

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
import kklazy.persistence.support.CommonResponse;
import kklazy.persistence.utils.MD5Utils;
import kklazy.persistence.utils.StringUtils;
import kklazy.security.model.User;
import kklazy.security.service.UserService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.USER_NAMESPACE)
public class UserController extends BasePageController<User, String> {

	@Autowired
	private UserService userService;

	@Override
	public PageService<User, String> pageservice() {
		return userService;
	}

	@Override
	public String path() {
		return "/webpages/security/user";
	}
	
	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final User entity) {
		try {
			query = assembleQuery(query);
			Page< User > page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getUsername())) {
						criteria.add(Restrictions.eq("username", entity.getUsername()));
					}
					criteria.add(Restrictions.eq("enabled", entity.getEnabled()));
					criteria.add(Restrictions.eq("delete", entity.getDelete()));
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.ASC, "username")))
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, User entity) {
		User temp = pageservice().findBy(entity.getId());
		temp.setPassword(MD5Utils.md5Hex(entity.getPassword() + "{" + temp.getUsername() + "}"));
		return super.commithandler(request, response, modelMap, temp);
	}
}
