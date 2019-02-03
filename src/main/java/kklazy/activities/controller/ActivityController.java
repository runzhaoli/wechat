package kklazy.activities.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.activities.model.Activity;
import kklazy.activities.service.ActivityService;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.service.PageService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.ACTIVITY_NAMESPACE)
public class ActivityController extends BasePageController<Activity, String> {

	@Autowired
	private ActivityService activityService;

	@Override
	public PageService<Activity, String> pageservice() {
		return activityService;
	}

	@Override
	public String path() {
		return "/webpages/activities/activity";
	}

}
