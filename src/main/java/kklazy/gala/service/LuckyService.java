package kklazy.gala.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.gala.entity.Lucky;
import kklazy.persistence.service.impl.DefaultService;

/**
 * 
 */
@Service("luckyService")
@Transactional(rollbackFor = Exception.class)
public class LuckyService extends DefaultService<Lucky, String> {


}
