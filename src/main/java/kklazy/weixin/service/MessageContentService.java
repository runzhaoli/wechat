package kklazy.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.MessageContent;

/**
 * @author kk
 */
@Service("messageContentService")
@Transactional(rollbackFor = Exception.class)
public class MessageContentService extends DefaultService<MessageContent, String> {

}
