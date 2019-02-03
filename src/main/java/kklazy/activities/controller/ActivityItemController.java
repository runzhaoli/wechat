package kklazy.activities.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kklazy.activities.entity.ActivityItemEntity;
import kklazy.activities.model.Activity;
import kklazy.activities.model.ActivityItem;
import kklazy.activities.service.ActivityItemService;
import kklazy.activities.service.ActivityService;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;

@Controller
@RequestMapping(MappingConstants.NameSpaces.ACTIVITY_ITEM_NAMESPACE)
public class ActivityItemController extends BasePageController<ActivityItem, String> {

	@Autowired
	private ActivityItemService activityItemService;

	@Autowired
	private ActivityService activityService;

	@Override
	public PageService<ActivityItem, String> pageservice() {
		return activityItemService;
	}

	@Override
	public String path() {
		return "/webpages/activities/activityitem";
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#createhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<Activity> activities =  activityService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));
		modelMap.put("activities", activities);
		super.createhandler(request, response, modelMap);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#modifyhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.io.Serializable)
	 */
	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		List<Activity> activities =  activityService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));
		modelMap.put("activities", activities);
		super.modifyhandler(request, response, modelMap, id);
	}

	/**
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param id
	 * @return
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.ACTIVITY_ITEM_DATA)
	@ResponseBody
	public CommonResponse activityitemdata(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		CommonResponse retval = CommonResponse.SUCCESS();

		List<ActivityItem> items =  pageservice().findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));

		List<ActivityItemEntity> entities = new ArrayList<ActivityItemEntity>();
		for (ActivityItem item : items) {
			ActivityItemEntity entity = new ActivityItemEntity();
			entity.setId(item.getId());
			entity.setCode(item.getCode());
			entity.setName(item.getName());
			entities.add(entity);
		}
		retval.setBody(entities);

		return retval;
	}
}
