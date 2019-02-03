package kklazy.wechatclient.hrc.controller;

import java.io.PrintWriter;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.hibernate.sql.JoinType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import kklazy.common.constants.ContextConstants;
import kklazy.common.constants.MappingConstants;
import kklazy.common.entity.QueryEntity;
import kklazy.component.file.model.Attachfile;
import kklazy.component.file.service.AttachfileService;
import kklazy.component.mail.HrcMailSender;
import kklazy.hrc.model.Article;
import kklazy.hrc.model.Certify;
import kklazy.hrc.model.ComplaintProposal;
import kklazy.hrc.model.Division;
import kklazy.hrc.model.Recommend;
import kklazy.hrc.model.SliderLoop;
import kklazy.hrc.model.Vote;
import kklazy.hrc.model.VoteResult;
import kklazy.hrc.model.YiZhouChat;
import kklazy.hrc.service.ArticleService;
import kklazy.hrc.service.CertifyService;
import kklazy.hrc.service.ComplaintProposalService;
import kklazy.hrc.service.DivisionService;
import kklazy.hrc.service.RecommendService;
import kklazy.hrc.service.SliderLoopService;
import kklazy.hrc.service.VoteResultService;
import kklazy.hrc.service.VoteService;
import kklazy.hrc.service.YiZhouChatService;
import kklazy.newtouch.model.AccumulationFund;
import kklazy.newtouch.model.Staff;
import kklazy.newtouch.service.AccumulationFundService;
import kklazy.newtouch.service.StaffService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.controller.DefaultController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.service.Service;
import kklazy.persistence.utils.DateUtils;
import kklazy.security.support.systemconfig.utils.SystemConfigUtils;
import kklazy.weixin.model.WechatAccount;
import kklazy.weixin.service.WechatAccountService;
import kklazy.weixin.service.WechatService;

/**
 * @author kk
 */
@Controller
@Scope("prototype")
@RequestMapping(MappingConstants.NameSpaces.HRC_WECHAT_NAMESPACE)
public class HrcWechatController extends DefaultController<DefaultModel, String> {

	private Boolean postOnly = Boolean.TRUE;
	public static final String USER = "USER";

	@Autowired
	private ArticleService articleService;

	@Autowired
	private SliderLoopService sliderLoopService;

	@Autowired
	private StaffService staffService;

	@Autowired
	private AccumulationFundService accumulationFundService;

	@Autowired
	private CertifyService certifyService;

	@Autowired
	private RecommendService recommendService;

	@Autowired
	private ComplaintProposalService complaintProposalService;

	@Autowired
	private WechatAccountService accountService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private AttachfileService attachfileService;

	@Autowired
	private YiZhouChatService yiZhouChatService;

	@Autowired
	private DivisionService divisionService;

	@Autowired
	private VoteService voteService;

	@Autowired
	private VoteResultService voteResultService;

	@Override
	public Service<DefaultModel, String> service() {
		return null;
	}

	@Override
	public String path() {
		return "/hrcwechatpages";
	}

	/**
	 * @return
	 */
	public WechatAccount wechatAccount() {
		final String token = SystemConfigUtils.getSystemConfig("hrhelper.token");
		List<WechatAccount> accounts = accountService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("token", token));
				return criteria;
			}
		});
		return accounts.get(0);
	}

	/**
	 * 企业号验证请求
	 * 
	 * @param request
	 * @param response
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.WECHAT, method = RequestMethod.GET)
	public void wechatGet(HttpServletRequest request, HttpServletResponse response) {
		String msg_signature = request.getParameter("msg_signature"); // 加密签名
		String timeStamp = request.getParameter("timestamp"); // 时间戳
		String nonce = request.getParameter("nonce"); // 随机数
		String echostr = request.getParameter("echostr"); // 加密的随机字符串
		Logger.getLogger(getClass()).info("signature: " + msg_signature + ", timestamp: " + timeStamp + ", nonce: " + nonce + ", echostr: " + echostr);
		try {
			WechatAccount account = wechatAccount();
			WXBizMsgCrypt wxbmc = new WXBizMsgCrypt(account.getToken(), account.getAccountid(), account.getAppid());
			String sEchoStr = wxbmc.VerifyURL(msg_signature, timeStamp, nonce, echostr);
			Logger.getLogger(getClass()).info("sEchoStr:" + sEchoStr);
			response.setCharacterEncoding("UTF-8");
			response.setContentType("text/html; charset=utf-8");
			PrintWriter out = response.getWriter();
			out.write(sEchoStr);
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 解析微信请求
	 * 
	 * @param response
	 * @param request
	 * 
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.WECHAT, method = RequestMethod.POST)
	public void wechatPost(HttpServletRequest request, HttpServletResponse response) throws Exception {
		Logger.getLogger(getClass()).info("request: " + request.toString());
		try {
			WechatAccount account = wechatAccount();
			WXBizMsgCrypt wxbmc = new WXBizMsgCrypt(account.getToken(), account.getAccountid(), account.getAppid());
			String message = wechatService.wechat(request, wxbmc);
			Logger.getLogger(getClass()).info("response message:\n" + message);
			String sEncryptMsg = wxbmc.EncryptMsg(message, request.getParameter("timestamp"), request.getParameter("nonce"));
			Logger.getLogger(getClass()).info("encrypt message:\n" + sEncryptMsg);
			PrintWriter pw = response.getWriter();
			pw.write(sEncryptMsg);
			pw.flush();
			pw.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * 登录
	 * 
	 * @param request
	 * @param response
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.LOGIN)
	public String login(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		try {
			WechatAccount account = wechatAccount();
			String empno = wechatService.getUserInfo(account, query.getCode());
			Staff staff = staffService.findByEmpno(empno);
			if (staff == null) {
				modelMap.put("message", "员工 [ " + query.getEmpno() + " ] 不存在 或 状态异常!");
				return path() + "/" + MappingConstants.UrlMapping.LOGIN;
			}
			request.getSession().setAttribute(USER, staff);
			Logger.getLogger(getClass()).info("wechat user login: " + staff.getEmpno());
			return "redirect:" + MappingConstants.UrlMapping.INDEX;
		} catch (Exception e) {
			if (StringUtils.isBlank(query.getEmpno()) || StringUtils.isBlank(query.getIdentity())) {
				modelMap.put("message", "请先登录!");
				return path() + "/" + MappingConstants.UrlMapping.LOGIN;
			}
			if (postOnly && !request.getMethod().equals("POST")) {
				// 非 post 请求,判断为异常行为
				modelMap.put("message", "无效登录！");
				Logger.getLogger(getClass()).error("eeeeee: " + query.getEmpno());
				return path() + "/" + MappingConstants.UrlMapping.LOGIN;
			}
			Staff staff = staffService.findByEmpno(query.getEmpno());
			if (staff == null ) {
				modelMap.put("message", "员工 [ " + query.getEmpno() + " ] 不存在 或 状态异常!");
				return path() + "/" + MappingConstants.UrlMapping.LOGIN;
			}
			String identity = staff.getIdentity();
			if (StringUtils.isBlank(identity) || identity.length() < 4 || !query.getIdentity().equals(identity.substring(identity.length() - 4).toUpperCase())) {
				modelMap.put("message", "工号或身份证号码填写有误!");
				return path() + "/" + MappingConstants.UrlMapping.LOGIN;
			}
			request.getSession().setAttribute(USER, staff);
			Logger.getLogger(getClass()).info("wechat user login: " + staff.getEmpno());
			return "redirect:" + MappingConstants.UrlMapping.INDEX;
		}
	}

	/**
	 * 小助手首页
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.INDEX)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		List<SliderLoop> sliderloop = sliderLoopService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.or(Restrictions.eq("end", ""), Restrictions.ge("end", DateUtils.format(DateUtils.DAY_PATTERN))));
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "sort")));

		long showtime_start = DateUtils.parse(DateUtils.DEFAULT_PATTERN, "2017-12-19 23:59:59").getTime();
		long showtime_end   = DateUtils.parse(DateUtils.DEFAULT_PATTERN, "2018-02-15 23:59:59").getTime();
		modelMap.put("v_status", showtime_start <= System.currentTimeMillis() && System.currentTimeMillis() <= showtime_end);
		modelMap.put("sliderloop", sliderloop);
		return path() + "/" + MappingConstants.UrlMapping.INDEX;
	}

	/**
	 * 公积金信息查询
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.ACCUMULATIONFUND)
	public String accumulationfundinit(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("user", user);
		return path() + "/accumulationfund/search";

	}

	/**
	 * 公积金信息查询
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.ACCUMULATIONFUND_SEARCH)
	public String accumulationfundsearch(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("user", user);
		if (!user.getIdentity().equals(StringUtils.trim(query.getIdentity()))) {
			modelMap.put("message", "你输入的身份证号码有误!");
			return path() + "/accumulationfund/search";
		}
		List<AccumulationFund> funds = accumulationFundService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				criteria.createAlias("staff", "staff", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("staff.identity", user.getIdentity()));
				return criteria;
			}
		});
		if (funds == null || funds.isEmpty()) {
			modelMap.put("message", "暂时无法查询到您的公积金帐号信息!");
			return path() + "/accumulationfund/search";
		}
		modelMap.put("fund", funds.get(0));
		return path() + "/accumulationfund/result";
	}

	/**
	 * 公告列表
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.ARTICLE_LIST)
	public String articlelist(HttpServletRequest request, HttpServletResponse response, final QueryEntity query, ModelMap modelMap) throws Exception {
		List<Article> article = articleService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("type", query.getType()));
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "create")));
		modelMap.put("article", article);
		modelMap.put("query", query);
		return path() + "/article/list";
	}

	/**
	 * 我要开证明(选择需要的操作)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.CERTIFY_SELECT)
	public String certifyselect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("user", user);
		return path() + "/certify/select";
	}

	/**
	 * 我要开证明(申请证明)
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.CERTIFY_SEARCH)
	public String certifysearch(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("query", query);
		modelMap.put("user", user);
		return path() + "/certify/search";
	}

	/**
	 * 我要开证明(保存证明)
	 * 
	 * @param request
	 * @param certify
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.CERTIFY_SAVE)
	public String certifysave(HttpServletRequest request, HttpServletResponse response, final Certify certify, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		QueryEntity query = new QueryEntity();
		query.setType(certify.getProofType());
		modelMap.put("query", query);

		modelMap.put("certify", certify);
		modelMap.put("user", user);

		if (!user.getIdentity().equals(StringUtils.trim(certify.getIdentity()))) {
			modelMap.put("message", "你输入的身份证号码有误!");
			return path() + "/certify/search";
		}
		if (StringUtils.isBlank(certify.getProofType()) || StringUtils.isBlank(certify.getDescription())) {
			modelMap.put("message", "数据输入有误!");
			return path() + "/certify/search";
		}

		List<Certify> certifies = certifyService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				criteria.add(Restrictions.eq("proofType", certify.getProofType()));
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "create")));
		for (Certify temp : certifies) {
			String date = DateUtils.format(DateUtils.DAY_PATTERN, temp.getCreate());
			String day  = DateUtils.format(DateUtils.DAY_PATTERN, new Date());
			if (day.equals(date)) {
				modelMap.put("message", "同一类型的证明当天只允许提交一次!");
				return path() + "/certify/search";
			}
		}

		certify.setStaff(user);
		certify.setAuditStatus(ContextConstants.CERTIFY_AUDIT_STATUS.AUDIT_STATUS_01);
		certifyService.merge(certify);

		HrcMailSender.send(certify);

		return "redirect:" + MappingConstants.UrlMapping.CERTIFY_RESULT;

	}

	/**
	 * 我要开证明(查询证明)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.CERTIFY_RESULT)
	public String certifyresult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		List<Certify> certifies = certifyService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				return criteria;
			}
		});
		modelMap.put("certifies", certifies);
		modelMap.put("user", user);
		return path() + "/certify/result";
	}

	/**
	 * 我要举荐(选择需要的操作)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.RECOMMEND_SELECT)
	public String recommendselect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("user", user);
		return path() + "/recommend/select";
	}

	/**
	 * 我要举荐(申请举荐)
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.RECOMMEND_SEARCH)
	public String recommendsearch(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("query", query);
		modelMap.put("user", user);
		return path() + "/recommend/search";
	}

	/**
	 * 我要举荐(保存举荐)
	 * 
	 * @param request
	 * @param recommend
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.RECOMMEND_SAVE)
	public String recommendsave(HttpServletRequest request, HttpServletResponse response, Recommend recommend, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		recommend.setStaff(user);
		recommend.setAuditStatus(ContextConstants.RECOMMEND_AUDIT_STATUS.AUDIT_STATUS_01);
		recommendService.merge(recommend);

		HrcMailSender.send(recommend);

		return "redirect:" + MappingConstants.UrlMapping.RECOMMEND_RESULT;
	}

	/**
	 * 我要举荐(查询历史举荐)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.RECOMMEND_RESULT)
	public String recommendresult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		List<Recommend> recommends = recommendService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				return criteria;
			}
		});

		modelMap.put("recommends", recommends);
		modelMap.put("user", user);
		return path() + "/recommend/result";
	}

	/**
	 * 投诉与建议(选择需要的操作)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SELECT)
	public String complaintproposalselect(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("user", user);
		return path() + "/complaintproposal/select";
	}

	/**
	 * 投诉与建议(我要投诉或我要建议)
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SEARCH)
	public String complaintproposalsearch(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("query", query);
		modelMap.put("user", user);
		return path() + "/complaintproposal/search";
	}

	/**
	 * 投诉与建议(保存投诉与建议)
	 * 
	 * @param request
	 * @param complaintproposal
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.COMPLAINTPROPOSAL_SAVE)
	public String complaintproposalsave(HttpServletRequest request, HttpServletResponse response, ComplaintProposal complaintproposal, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		complaintproposal.setStaff(user);
		complaintProposalService.merge(complaintproposal);

		HrcMailSender.send(complaintproposal);

		return "redirect:" + MappingConstants.UrlMapping.COMPLAINTPROPOSAL_RESULT;
	}

	/**
	 * 投诉与建议(查询历史投诉与建议)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.COMPLAINTPROPOSAL_RESULT)
	public String complaintproposalresult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		List<ComplaintProposal> complaintproposals = complaintProposalService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.createAlias("staff", "staff", JoinType.LEFT_OUTER_JOIN).add(Restrictions.eq("staff.empno", user.getEmpno()));
//				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				criteria.add(Restrictions.eq("anonymous", Boolean.FALSE));
				return criteria;
			}
		});
		modelMap.put("complaintproposals", complaintproposals);
		modelMap.put("user", user);
		return path() + "/complaintproposal/result";
	}

	/**
	 * 团队信息
	 * 
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.ABOUT_TEAM)
	public String aboutteam(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		return path() + "/aboutteam/team";
	}

	/**
	 * 下载离职文档
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.RESIGNATION)
	public String resignation(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		List<Attachfile> attachfiles = attachfileService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("code", "resignation"));
				return criteria;
			}
		});
		Attachfile entity = null;
		for (Attachfile attachfile : attachfiles) {
			if (attachfile.getPath().indexOf(user.getArea()) > 0) {
				entity = attachfile;
			}
		}
		if (entity != null) {
			HrcMailSender.send(entity, user);
			modelMap.put("message", "文档已经发送到您的公司邮箱,请查收!");
		} else {
			modelMap.put("message", "您所在的分公司目前该文档还在整理中，敬请期待噢~!");
		}
		QueryEntity query = new QueryEntity();
		query.setType("HELP");
		List<Article> article = articleService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("type", "HELP"));
				return criteria;
			}
		});

		modelMap.put("article", article);
		modelMap.put("query", query);

		return path() + "/article/list";
	}

	/**
	 * 优秀员工评选
	 * 
	 * @param request
	 * @param response
	 * @param result
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.EXCELLENT_EMPLOYEE_SELECT)
	public String excellentemployeeselect(HttpServletRequest request, HttpServletResponse response, VoteResult result, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			// 用户 session 为空,回到登录页面
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}

		/**
		 * 投票参数
		 */
		String v_date = DateUtils.format(DateUtils.DAY_PATTERN, new Date());
		int size = 3; // 可投票数
		boolean repeat = false; // 是否允许重复投票

		boolean _vote_start = System.currentTimeMillis() < DateUtils.parse(DateUtils.DEFAULT_PATTERN, "2018-01-27 14:30:00").getTime();
		boolean _vote_end   = DateUtils.parse(DateUtils.DEFAULT_PATTERN, "2018-01-27 18:30:00").getTime() <= System.currentTimeMillis();
		boolean _vote_weekend = false;
		String today = DateUtils.format(DateUtils.DAY_PATTERN, new Date());
		if ("2018-01-06".equals(today) || "2018-01-07".equals(today)) {
			_vote_weekend = true;
		}

		result.setVempno(user.getEmpno());
		result.setVdate(v_date);

		if (result != null &&"delete".equals(result.getType())) {
			voteResultService.removeVoteResult(result);
			modelMap.put("message", "投票已取消~");
			result.setEmpno(null);
			result.setType(null);
		}
		if (result != null && StringUtils.isNotBlank(result.getEmpno()) && StringUtils.isNotBlank(result.getType())) {
			if (_vote_start || _vote_end || _vote_weekend) {
				// 非有效时间内的请求,判断为刷票行为
				modelMap.put("message", "警告: 工号 [ " + user.getEmpno() + " ],您的投票无效，请通过正确的途径参与投票~");
				Logger.getLogger(getClass()).error("kkkkkk: " + user.getDepartment() + ", " + user.getEmpno() + ", " + user.getName());
			} else {
				List<VoteResult> results = voteResultService.findVoteResults(result);
				if (postOnly && !request.getMethod().equals("POST")) {
					// 非 post 请求,判断为刷票行为
					modelMap.put("message", "警告: 工号 [ " + user.getEmpno() + " ],您的投票无效，请通过正确的途径参与投票~");
					Logger.getLogger(getClass()).error("kkkkkk: " + user.getDepartment() + ", " + user.getEmpno() + ", " + user.getName());
				} else if (results != null && results.size() >= size) {
					modelMap.put("message", "您的投票权已经用完啦~");
				} else {
					result.setVdept(user.getDepartment());
					result.setVname(user.getName());
					Boolean status = voteResultService.saveVoteResult(result, size, repeat);
					if (status == null) {
						modelMap.put("message", "您的投票权已经用完啦~");
//						if (ContextConstants.TYPE_1.equals(result.getType())) {
//							modelMap.put("message", "您今天的 [优秀员工] 投票权已经用完啦~");
//						}
//						if (ContextConstants.TYPE_2.equals(result.getType())) {
//							modelMap.put("message", "您今天的 [优秀团队] 投票权已经用完啦~");
//						}
					} else if (status.booleanValue()) {
						modelMap.put("success", "投票成功 [ " + result.getEmpno() + " ] + 1~");
					} else {
						modelMap.put("message", "投票无效哦~");
					}
				}
			}
		}
		List<Vote> votes_type_1 = voteService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("type", ContextConstants.TYPE_1));
				return criteria;
			}
		});
		List<Vote> votes_type_2 = voteService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("type", ContextConstants.TYPE_2));
				return criteria;
			}
		});
		List<Vote> _total_type_1 = voteService.findAllTotal(ContextConstants.TYPE_1, repeat);
		List<Vote> _total_type_2 = voteService.findAllTotal(ContextConstants.TYPE_2, repeat);
		VoteResult search = new VoteResult();
		search.setVempno(user.getEmpno());
		search.setVdate(v_date);
		List<VoteResult> results = voteResultService.findVoteResults(search);
		int type_1_count = voteResultService.findTotalVote(ContextConstants.TYPE_1, repeat);
		int type_2_count = voteResultService.findTotalVote(ContextConstants.TYPE_2, repeat);
		// 个人投票总数、当天已投
		for (Vote v : votes_type_1) {
			if (_total_type_1 != null) {
				for (Vote t : _total_type_1) {
					if (v.getEmpno().equals(t.getEmpno()) ) {
						v.setTotal(t.getTotal());
						try {
							v.setPercent(((float) t.getTotal() / (float) type_1_count) * 100);
						} catch (Exception e) {
							v.setPercent(0);
						}
					}
				}
			}
			if (results != null) {
				for (VoteResult r : results) {
					if (v.getEmpno().equals(r.getEmpno()) ) {
						v.setToday("yes");
					}
				}
			}
		}
		// 团队投票总数、当天已投
		for (Vote v : votes_type_2) {
			if (_total_type_2 != null) {
				for (Vote t : _total_type_2) {
					if (v.getEmpno().equals(t.getEmpno()) ) {
						v.setTotal(t.getTotal());
						try {
							v.setPercent(((float) t.getTotal() / (float) type_2_count) * 100);
						} catch (Exception e) {
							v.setPercent(0);
						}
					}
				}
			}
			if (results != null) {
				for (VoteResult r : results) {
					if (v.getEmpno().equals(r.getEmpno()) ) {
						v.setToday("yes");
					}
				}
			}
		}

		modelMap.put("votes_type_1", votes_type_1);
		modelMap.put("votes_type_2", votes_type_2);
		modelMap.put("user", user);
		modelMap.put("_vote_start", _vote_start);
		modelMap.put("_vote_end", _vote_end);
		modelMap.put("_vote_weekend", _vote_weekend);
		return path() + "/vote/2017list";
	}

	/**
	 * 已投票情况查询
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = "excellentemployeequery")
	public String votequery(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			// 用户 session 为空,回到登录页面
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		VoteResult search = new VoteResult();
		search.setVempno(user.getEmpno());
		List<VoteResult> results = voteResultService.findVoteResults(search);
		for (VoteResult r : results) {
			if (StringUtils.isBlank(r.getEmpno())) {
				continue ;
			}
			final String empno = r.getEmpno();
			List<Vote> votes = voteService.findBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					criteria.add(Restrictions.eq("empno", empno));
					return criteria;
				}
			});
			r.setName(votes != null ? votes.get(0).getName() : "");
		}
		modelMap.put("results", results);
		modelMap.put("user", user);
		return path() + "/vote/2017query";
	}

	/**
	 * 伊周聊
	 * 
	 * @param request
	 * @param query
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.YZL_SEARCH)
	public String yzlsearch(HttpServletRequest request, HttpServletResponse response, QueryEntity query, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("query", query);
		modelMap.put("user", user);
		return path() + "/yizhouchat/search";
	}

	/**
	 * 伊周聊(保存)
	 * 
	 * @param request
	 * @param yzl
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.YZL_SAVE)
	public String yzlsave(HttpServletRequest request, HttpServletResponse response, YiZhouChat yzl, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		modelMap.put("yzl", yzl);
		modelMap.put("user", user);
		yzl.setStaff(user);
		yzl.setChatStatus(ContextConstants.CERTIFY_AUDIT_STATUS.AUDIT_STATUS_01);
		yiZhouChatService.merge(yzl);
		HrcMailSender.send(yzl);
		return "redirect:yzlresult.kklazy";
	}

	/**
	 * 伊周聊(查询记录)
	 * 
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.YZL_RESULT)
	public String yzlresult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		QueryEntity query = new QueryEntity();
		query.setEmpno(user.getEmpno());
		List<YiZhouChat> chats = yiZhouChatService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				return criteria;
			}
		});
		modelMap.put("chats", chats);
		modelMap.put("user", user);
		return path() + "/yizhouchat/result";
	}

	/**
	 * @param request
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.DIVISION_RESULT)
	public String divisionresult(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		QueryEntity query = new QueryEntity();
		query.setEmpno(user.getEmpno());
		List<Division> divisions = divisionService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.ASC, "sort")));
		modelMap.put("divisions", divisions);
		modelMap.put("user", user);
		return path() + "/division/result";
	}

	@RequestMapping(value = "divisiontemp")
	public String divisiontemp(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {

		return path() + "/division/temp";
	}

	@RequestMapping(value = "workflowoperation")
	public String workflowoperation(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {

		return path() + "/workflow";
	}

}
