package kklazy.activities.entity;

/**
 * 23周年庆照片投票
 * 
 * @author kk
 */
public class VotePhoto23Entity {

	/**
	 * 照片编号
	 */
	private String picid;

	/**
	 * 是否本人投票
	 */
	private String empno;

	/**
	 * 总投票数
	 */
	private Long total;

	/**
	 * @return the picid
	 */
	public String getPicid() {
		return picid;
	}

	/**
	 * @param picid the picid to set
	 */
	public void setPicid(String picid) {
		this.picid = picid;
	}

	/**
	 * @return the empno
	 */
	public String getEmpno() {
		return empno;
	}

	/**
	 * @param empno the empno to set
	 */
	public void setEmpno(String empno) {
		this.empno = empno;
	}

	/**
	 * @return the total
	 */
	public Long getTotal() {
		return total;
	}

	/**
	 * @param total the total to set
	 */
	public void setTotal(Long total) {
		this.total = total;
	}
	
}
