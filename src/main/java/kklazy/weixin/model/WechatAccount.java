package kklazy.weixin.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import kklazy.persistence.model.DefaultModel;

/**
 * 微信公众平台配置信息
 * 
 * @author kk
 */
@Entity
@Table(name = "WECHAT_ACCOUNT")
public class WechatAccount extends DefaultModel {

	/**
	 * 
	 */
	private static final long serialVersionUID = -938789849156957228L;

	/**
	 * 服务号、企业号名称
	 */
	private String name;

	/**
	 * 公众号类型
	 */
	private String type;

	/**
	 * 电子邮箱
	 */
	private String email;

	/**
	 * 服务号、企业号描述
	 */
	private String description;

	/**
	 * 服务号、企业号共用参数：TOKEN(验证微信返回消息时使用,在微信平台中自己配置)
	 */
	private String token;

	/**
	 * 服务号参数
	 */
	private String accountid;

	/**
	 * 企业号参数 ENCODINGAESKEY
	 */
	private String encodingAesKey;

	/**
	 * 服务号 APPID
	 */
	private String appid;

	/**
	 * 企业号 CORPID
	 */
	private String corpId;

	/**
	 * 服务号、企业号 APPSECRET
	 */
	private String appsecret;

	/**
	 * 服务号、企业号凭证 ACCESS_TOKEN
	 */
	private String accesstoken;

	/**
	 * 凭证有效时间
	 */
	private int expiresin;

	/**
	 * 凭证 TOKEN 获取时间
	 */
	private Date tokentime;

	/**
	 * 企业号 agentid
	 */
	private String agentid;

	/**
	 * 
	 */
	public WechatAccount() {
		
	}

	/**
	 * @param id
	 * @param agentid
	 */
	public WechatAccount(String id, String agentid) {
		this.id = id;
		this.agentid = agentid;
	}

	/**
	 * @return the name
	 */
	@Column(name = "NAME")
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the token
	 */
	@Column(name = "TOKEN")
	public String getToken() {
		return token;
	}

	/**
	 * @param token the token to set
	 */
	public void setToken(String token) {
		this.token = token;
	}

	/**
	 * @return the accountid
	 */
	@Column(name = "ACCOUNT_ID")
	public String getAccountid() {
		return accountid;
	}

	/**
	 * @param accountid the accountid to set
	 */
	public void setAccountid(String accountid) {
		this.accountid = accountid;
	}

	/**
	 * @return the type
	 */
	@Column(name = "TYPE")
	public String getType() {
		return type;
	}

	/**
	 * @param type the type to set
	 */
	public void setType(String type) {
		this.type = type;
	}

	/**
	 * @return the email
	 */
	@Column(name = "EMAIL")
	public String getEmail() {
		return email;
	}

	/**
	 * @param email the email to set
	 */
	public void setEmail(String email) {
		this.email = email;
	}

	/**
	 * @return the description
	 */
	@Column(name = "DESCRIPTION")
	public String getDescription() {
		return description;
	}

	/**
	 * @param description the description to set
	 */
	public void setDescription(String description) {
		this.description = description;
	}

	/**
	 * @return the encodingAesKey
	 */
	@Column(name = "ENCODING_AES_KEY")
	public String getEncodingAesKey() {
		return encodingAesKey;
	}

	/**
	 * @param encodingAesKey the encodingAesKey to set
	 */
	public void setEncodingAesKey(String encodingAesKey) {
		this.encodingAesKey = encodingAesKey;
	}

	/**
	 * @return the appid
	 */
	@Column(name = "APPID")
	public String getAppid() {
		return appid;
	}

	/**
	 * @param appid the appid to set
	 */
	public void setAppid(String appid) {
		this.appid = appid;
	}

	/**
	 * @return the corpId
	 */
	@Column(name = "CORP_ID")
	public String getCorpId() {
		return corpId;
	}

	/**
	 * @param corpId the corpId to set
	 */
	public void setCorpId(String corpId) {
		this.corpId = corpId;
	}

	/**
	 * @return the appsecret
	 */
	@Column(name = "APPSECRET")
	public String getAppsecret() {
		return appsecret;
	}

	/**
	 * @param appsecret the appsecret to set
	 */
	public void setAppsecret(String appsecret) {
		this.appsecret = appsecret;
	}

	/**
	 * @return the accesstoken
	 */
	@Column(name = "ACCESS_TOKEN", length = 1024)
	public String getAccesstoken() {
		return accesstoken;
	}

	/**
	 * @param accesstoken the accesstoken to set
	 */
	public void setAccesstoken(String accesstoken) {
		this.accesstoken = accesstoken;
	}

	/**
	 * @return the expiresin
	 */
	@Column(name = "EXPIRES_IN")
	public int getExpiresin() {
		return expiresin;
	}

	/**
	 * @param expiresin the expiresin to set
	 */
	public void setExpiresin(int expiresin) {
		this.expiresin = expiresin;
	}

	/**
	 * @return the tokentime
	 */
	@Column(name = "TOKEN_TIME")
	public Date getTokentime() {
		return tokentime;
	}

	/**
	 * @param tokentime the tokentime to set
	 */
	public void setTokentime(Date tokentime) {
		this.tokentime = tokentime;
	}

	/**
	 * @return the agentid
	 */
	@Column(name = "AGENTID")
	public String getAgentid() {
		return agentid;
	}

	/**
	 * @param agentid the agentid to set
	 */
	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

}
