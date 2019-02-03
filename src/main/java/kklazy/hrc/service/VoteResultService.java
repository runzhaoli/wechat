/**
 * 
 */
package kklazy.hrc.service;

import java.math.BigInteger;
import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.VoteResult;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.service.impl.DefaultService;
import kklazy.persistence.utils.StringUtils;

/**
 * @author kk
 *
 */
@Service("voteResultService")
@Transactional(rollbackFor = Exception.class)
public class VoteResultService extends DefaultService<VoteResult, String> {

	/**
	 * @param result
	 * @param size 最多可以票数量
	 * @param repeat 是否允许重复投同一人
	 * @return
	 */
	public synchronized Boolean saveVoteResult(final VoteResult result, final int size, final boolean repeat) {
		if (StringUtils.isBlank(result.getType())) {
			return Boolean.FALSE;
		}
		// 查询当天投票总数
		List<VoteResult> total = findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("type", result.getType()));
				criteria.add(Restrictions.eq("vempno", result.getVempno()));
				criteria.add(Restrictions.eq("vdate", result.getVdate()));
				return criteria;
			}
		});
		// 总票数 大于等于可投票数，返回空
		if (total != null && total.size() >= size) {
			return null;
		}
		// 是否允许重复投票
		if (repeat) {
			// 允许
			persist(result);
			return Boolean.TRUE;
		} else {
			// 不允许
			List<VoteResult> results = findBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					criteria.add(Restrictions.eq("type", result.getType()));
					criteria.add(Restrictions.eq("empno", result.getEmpno()));
					criteria.add(Restrictions.eq("vempno", result.getVempno()));
					criteria.add(Restrictions.eq("vdate", result.getVdate()));
					return criteria;
				}
			});
			// 是否已投
			if (results == null || results.isEmpty()) {
				persist(result);
				return Boolean.TRUE;
			}
		}
		return Boolean.FALSE;
	}

	/**
	 * @param result
	 */
	public void removeVoteResult(VoteResult result) {
		result.setType(null);
		List<VoteResult> results = findVoteResults(result);
		if (results != null && !results.isEmpty()) {
			remove(results.get(0));
		}
	}

	/**
	 * @param result
	 * @param size
	 * @return
	 */
	public List<VoteResult> findVoteResults(final VoteResult result) {
		List<VoteResult> results = findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				if (StringUtils.isNotBlank(result.getType())) {
					criteria.add(Restrictions.eq("type", result.getType()));
				}
				if (StringUtils.isNotBlank(result.getEmpno())) {
					criteria.add(Restrictions.eq("empno", result.getEmpno()));
				}
				if (StringUtils.isNotBlank(result.getVempno())) {
					criteria.add(Restrictions.eq("vempno", result.getVempno()));
				}
				if (StringUtils.isNotBlank(result.getVdate())) {
					criteria.add(Restrictions.eq("vdate", result.getVdate()));
				}
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "create")));
		return results;
	}

	/**
	 * @param type		投票类型
	 * @param repeat	是否计算重复投票
	 * @return
	 */
	public int findTotalVote(final String type, final boolean repeat) {
		StringBuilder sql = new StringBuilder();
		if (repeat) {
			sql.append(" SELECT COUNT(*) FROM HRC_VOTE_RESULT T WHERE T.TYPE = '" + type + "' ");
		} else {
			sql.append(" SELECT COUNT(*) FROM (SELECT DISTINCT EMPNO, TYPE, V_EMPNO, V_DATE from HRC_VOTE_RESULT) T WHERE T.TYPE = '" + type + "' ");
		}
		@SuppressWarnings("unchecked")
		List<Object> result = (List<Object>) findByNativeSql(sql);
		int retval = 0;
		if (result != null) {
			retval = ((BigInteger) result.get(0)).intValue();
		}
		return retval;
	}

}
