package kklazy.activities.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.activities.model.ActivityItem;
import kklazy.persistence.service.impl.DefaultService;

@Service("activityItemService")
@Transactional(rollbackFor = Exception.class)
public class ActivityItemService extends DefaultService<ActivityItem, String> {

}
