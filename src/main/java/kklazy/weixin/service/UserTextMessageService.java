package kklazy.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.UserTextMessage;

@Service("userTextMessageService")
@Transactional(rollbackFor = Exception.class)
public class UserTextMessageService extends DefaultService<UserTextMessage, String> {

}
