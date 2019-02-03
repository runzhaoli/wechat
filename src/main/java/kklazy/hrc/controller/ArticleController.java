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
import kklazy.hrc.model.Article;
import kklazy.hrc.service.ArticleService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.ARTICLE_NAMESPACE)
public class ArticleController extends BasePageController<Article, String> {

	@Autowired
	private ArticleService articleService;

	@Override
	public PageService<Article, String> pageservice() {
		return articleService;
	}

	@Override
	public String path() {
		return "/webpages/hrc/article";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Article entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page<Article> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {

					if (entity instanceof DefaultModel) {
						criteria.add(Restrictions.eq("enabled", ((DefaultModel) entity).getEnabled()));
						criteria.add(Restrictions.eq("delete", ((DefaultModel) entity).getDelete()));
					}
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.DESC, "modify"))
				)
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

}
