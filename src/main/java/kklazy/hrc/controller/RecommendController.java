/**
 * 
 */
package kklazy.hrc.controller;

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
import kklazy.hrc.model.Recommend;
import kklazy.hrc.service.RecommendService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;

/**
 * 我要举荐
 * 
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.RECOMMEND_NAMESPACE)
public class RecommendController extends BasePageController<Recommend, String> {

	@Autowired
	private RecommendService recommendService;

	@Override
	public PageService<Recommend, String> pageservice() {
		return recommendService;
	}

	@Override
	public String path() {
		return "/webpages/hrc/recommend";
	}

	/* (non-Javadoc)
	 * @see kklazy.common.controller.BasePageController#searchhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultPageQueryModel, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Recommend entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page<Recommend> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
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
	 * @see kklazy.persistence.controller.DefaultController#commithandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Recommend entity) {
		Recommend temp = pageservice().findBy(entity.getId());
		temp.setAuditStatus(entity.getAuditStatus());
		temp.setAuditRemark(entity.getAuditRemark());
		pageservice().merge(temp);
		return CommonResponse.SUCCESS();
	}

}
