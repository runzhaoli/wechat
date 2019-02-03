package kklazy.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.UserImageMessage;

@Service("userImageMessageService")
@Transactional(rollbackFor = Exception.class)
public class UserImageMessageService extends DefaultService<UserImageMessage, String> {

}
