package kklazy.security.controller;

import java.util.Collections;
import java.util.Comparator;

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

import kklazy.common.constants.ContextConstants;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.security.manager.DefaultFilterInvocationSecurityMetadataSource;
import kklazy.security.model.Resource;
import kklazy.security.service.ResourceService;
import kklazy.security.support.dictionary.utils.DictionaryUtils;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.RESOURCE_NAMESPACE)
public class ResourceController extends BasePageController<Resource, String> {

	public static final String PARENT = "parent";

	public static final String RESOURCE_TYPE = "resource_type";

	@Autowired
	private ResourceService resourceService;

	@Autowired
	private DefaultFilterInvocationSecurityMetadataSource securityMetadataSource;

	@Override
	public PageService<Resource, String> pageservice() {
		return resourceService;
	}

	@Override
	public String path() {
		return "/webpages/security/resource";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Resource entity) {
		try {

			query = assembleQuery(query);

			Page<Resource> page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {

				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {

					criteria.add(Restrictions.isNull("parent"));

					criteria.add(Restrictions.eq("enabled", entity.getEnabled()));
					criteria.add(Restrictions.eq("delete", entity.getDelete()));

					return criteria;
				}

			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.ASC, "sort"))));

			for (Resource resource : page.getContent()) {

				Collections.sort(resource.getSublevel(), new Comparator<Resource>() {

					/**
					 * @param up
					 * @param down
					 * @return
					 * 
					 * @author Kui
					 */
					@Override
					public int compare(Resource up, Resource down) {
						if (up.getSort() == null || down.getSort() == null) {
							return 1;
						}
						return up.getSort().compareTo(down.getSort());
					}

				});

			}

			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}

	}

	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		modelMap.put(RESOURCE_TYPE, DictionaryUtils.getDictionaryByCode(ContextConstants.GROUP.RESOURCE_TYPE, ContextConstants.RESOURCE_TYPE.TYPE_MENU));
	}

	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		if (StringUtils.isNotBlank(id)) {
			Resource resource = service().findBy(id);
			modelMap.put(PARENT, resource);
			modelMap.put(RESOURCE_TYPE, DictionaryUtils.getNextDictionaryByValue(ContextConstants.GROUP.RESOURCE_TYPE, resource.getType()));
		}
	}

	@Override
	protected void detailhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		Resource entity = service().findBy(id);
		modelMap.put(ENTITY, entity);
		if (entity != null) {
			modelMap.put(PARENT, entity.getParent());
		}
	}

	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		Resource entity = service().findBy(id);
		modelMap.put(ENTITY, entity);
		if (entity != null) {
			modelMap.put(PARENT, entity.getParent());
		}
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Resource entity) {
		Resource temp = service().findBy(entity.getId());

		if (temp != null) {
			entity.setSublevel(temp.getSublevel());
			entity.setRoles(temp.getRoles());

			if (StringUtils.isBlank(entity.getParent().getId())) {
				entity.setParent(temp.getParent());
			}

			entity.setModifier(SecurityApplicationContext.getName());
		} else {

			if (StringUtils.isBlank(entity.getParent().getId())) {
				entity.setParent(null);
			}

			entity.setCreator(SecurityApplicationContext.getName());
		}

		securityMetadataSource.clean();

		return super.commithandler(request, response, modelMap, entity);
	}

}
