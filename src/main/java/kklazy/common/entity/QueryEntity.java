package kklazy.common.entity;

import java.io.Serializable;

import kklazy.persistence.utils.StringUtils;

/**
 * 查询条件集合类,所有查询字段均写入该对象
 * 
 * @author kk
 */
public class QueryEntity implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4103470276991673237L;

	private String id;

	private String title;

	private String code;

	private String name;

	private String empno;

	private String identity;

	private String department;

	private String status;

	private String type;

	private String nickname;

	private String date;

	private String key;

	private int pageindex;

	private int pagesize;

	private Boolean anonymous;

	/**
	 * @return the id
	 */
	public String getId() {
		return id;
	}

	/**
	 * @param id
	 *            the id to set
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * @return the title
	 */
	public String getTitle() {
		return title;
	}

	/**
	 * @param title
	 *            the title to set
	 */
	public void setTitle(String title) {
		this.title = title;
	}

	/**
	 * @return the code
	 */
	public String getCode() {
		return StringUtils.trim(code);
	}

	/**
	 * @param code
	 *            the code to set
	 */
	public void setCode(String code) {
		this.code = code;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return StringUtils.trim(name);
	}

	/**
	 * @param name
	 *            the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the empno
	 */
	public String getEmpno() {
		return StringUtils.trim(empno);
	}

	/**
	 * @param empno
	 *            the empno to set
	 */
	public void setEmpno(String empno) {
		this.empno = empno;
	}

	/**
	 * @return the identity
	 */
	public String getIdentity() {
		return StringUtils.trim(identity == null ? "" : identity.toUpperCase());
	}

	/**
	 * @param identity
	 *            the identity to set
	 */
	public void setIdentity(String identity) {
		this.identity = identity;
	}

	/**
	 * @return the department
	 */
	public String getDepartment() {
		return StringUtils.trim(department);
	}

	/**
	 * @param department
	 *            the department to set
	 */
	public void setDepartment(String department) {
		this.department = department;
	}

	/**
	 * @return the status
	 */
	public String getStatus() {
		return status;
	}

	/**
	 * @param status
	 *            the status to set
	 */
	public void setStatus(String status) {
		this.status = status;
	}

	/**
	 * @return the type
	 */
	public String getType() {
		return type;
	}

	/**
	 * @param type
	 *            the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the nickname
	 */
	public String getNickname() {
		return nickname;
	}

	/**
	 * @param nickname
	 *            the nickname to set
	 */
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}

	/**
	 * @return the date
	 */
	public String getDate() {
		return date;
	}

	/**
	 * @param date
	 *            the date to set
	 */
	public void setDate(String date) {
		this.date = date;
	}

	/**
	 * @return the key
	 */
	public String getKey() {
		return key;
	}

	/**
	 * @param key
	 *            the key to set
	 */
	public void setKey(String key) {
		this.key = key;
	}

	/**
	 * @return the pageindex
	 */
	public int getPageindex() {
		return pageindex;
	}

	/**
	 * @param pageindex
	 *            the pageindex to set
	 */
	public void setPageindex(int pageindex) {
		this.pageindex = pageindex;
	}

	/**
	 * @return the pagesize
	 */
	public int getPagesize() {
		return pagesize;
	}

	/**
	 * @param pagesize
	 *            the pagesize to set
	 */
	public void setPagesize(int pagesize) {
		this.pagesize = pagesize;
	}

	/**
	 * @return the anonymous
	 */
	public Boolean getAnonymous() {
		return anonymous;
	}

	/**
	 * @param anonymous
	 *            the anonymous to set
	 */
	public void setAnonymous(Boolean anonymous) {
		this.anonymous = anonymous;
	}

}
