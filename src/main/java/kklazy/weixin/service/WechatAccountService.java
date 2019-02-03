package kklazy.weixin.service;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.weixin.model.WechatAccount;

@Service
@Transactional(rollbackFor = Exception.class)
public class WechatAccountService extends DefaultService<WechatAccount, String> {

	/**
	 * @param corpID
	 * @param agentID
	 * @return
	 */
	public WechatAccount findBy(final String corpID, final String agentID) {
		List<WechatAccount> accounts = findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("corpId", corpID));
				criteria.add(Restrictions.eq("agentid", agentID));
				return criteria;
			}
		});
		if (accounts == null || accounts.isEmpty()) {
			return null;
		}
		return accounts.get(0);
	}


}
