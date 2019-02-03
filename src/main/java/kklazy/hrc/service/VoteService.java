/**
 * 
 */
package kklazy.hrc.service;

import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.Vote;
import kklazy.persistence.service.impl.DefaultService;

/**
 * @author kk
 *
 */
@Service("voteService")
@Transactional(rollbackFor = Exception.class)
public class VoteService extends DefaultService<Vote, String> {

	/**
	 * @param type		投票类型
	 * @param repeat	是否计算重复投票
	 * @return
	 */
	public List<Vote> findAllTotal(String type, boolean repeat) {
		List<Vote> retval = new ArrayList<Vote>();
		StringBuilder sql = new StringBuilder();
		if (repeat) {
			sql.append(" SELECT EMPNO, COUNT(*) FROM HRC_VOTE_RESULT T WHERE T.TYPE = '" + type + "' GROUP BY T.EMPNO, T.TYPE ");
		} else {
			sql.append(" SELECT EMPNO, COUNT(*) FROM (SELECT DISTINCT EMPNO, TYPE, V_EMPNO, V_DATE from HRC_VOTE_RESULT) T WHERE T.TYPE = '" + type + "' GROUP BY T.EMPNO, T.TYPE ");
		}
		@SuppressWarnings("unchecked")
		List<Object[]> result = (List<Object[]>) findByNativeSql(sql);
		for (Object[] objects : result) {
			Vote temp = new Vote();
			temp.setEmpno((String) objects[0]);
			temp.setTotal(((BigInteger) objects[1]).intValue());
			retval.add(temp);
		}
		return retval;
	}

}
