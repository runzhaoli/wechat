package kklazy.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.MessageTemplate;

/**
 * @author kk
 */
@Service("messageTemplateService")
@Transactional(rollbackFor = Exception.class)
public class MessageTemplateService extends DefaultService<MessageTemplate, String> {

}
