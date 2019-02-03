package kklazy.security.controller;

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
import kklazy.persistence.support.CommonResponse;
import kklazy.security.model.Dictionary;
import kklazy.security.service.DictionaryService;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.DICTIONARY_NAMESPACE)
public class DictionaryController extends BasePageController<Dictionary, String> {

	@Autowired
	private DictionaryService dictionaryService;

	@Override
	public PageService<Dictionary, String> pageservice() {
		return dictionaryService;
	}

	@Override
	public String path() {
		return "/webpages/security/dictionary";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Dictionary entity) {
		try {

			query = assembleQuery(query);

			Page< Dictionary > page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getGroup())) {
						criteria.add(Restrictions.eq("group", entity.getGroup()));
					}

					criteria.add(Restrictions.eq("enabled", entity.getEnabled()));
					criteria.add(Restrictions.eq("delete", entity.getDelete()));

					return criteria;
				}
				
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.ASC, "group"), new Sort.Order(Direction.ASC, "sort")))
			);

			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Dictionary entity) {
		DictionaryUtils.clean();
		return super.commithandler(request, response, modelMap, entity);
	}

}
