/**
 * 
 */
package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.YiZhouChat;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 */
@Service("yiZhouChatService")
@Transactional(rollbackFor = Exception.class)
public class YiZhouChatService extends DefaultService<YiZhouChat, String> {

	/**
	 * @param entity
	 * @return
	 */
	public Boolean audit(YiZhouChat entity) {
		try {
			YiZhouChat temp = findBy(entity.getId());
			temp.setChatStatus(entity.getChatStatus());
			return Boolean.TRUE;
		} catch (Exception e) {
			return Boolean.FALSE;
		}
	}

}
