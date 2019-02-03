package kklazy.activities.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.activities.model.Point;
import kklazy.persistence.service.impl.DefaultService;

@Service("pointService")
@Transactional(rollbackFor = Exception.class)
public class PointService extends DefaultService<Point, String> {

}
