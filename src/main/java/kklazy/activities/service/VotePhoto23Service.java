package kklazy.activities.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.activities.model.VotePhoto23;
import kklazy.persistence.service.impl.DefaultService;

@Service("votePhoto23Service")
@Transactional(rollbackFor = Exception.class)
public class VotePhoto23Service extends DefaultService<VotePhoto23, String> {

}
