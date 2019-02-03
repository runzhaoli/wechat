package kklazy.activities.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.activities.model.Activity;
import kklazy.persistence.service.impl.DefaultService;

@Service("activityService")
@Transactional(rollbackFor = Exception.class)
public class ActivityService extends DefaultService<Activity, String> {

}
