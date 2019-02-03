/**
 * 
 */
package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.Recommend;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 */
@Service("recommendService")
@Transactional(rollbackFor = Exception.class)
public class RecommendService extends DefaultService<Recommend, String> {


}
