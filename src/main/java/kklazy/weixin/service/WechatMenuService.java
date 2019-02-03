package kklazy.weixin.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.WechatMenu;

@Service("wechatMenuService")
@Transactional(rollbackFor = Exception.class)
public class WechatMenuService extends DefaultService<WechatMenu, String> {

}
