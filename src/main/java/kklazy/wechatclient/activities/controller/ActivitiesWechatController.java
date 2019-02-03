package kklazy.wechatclient.activities.controller;

import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.qq.weixin.mp.aes.WXBizMsgCrypt;

import kklazy.activities.entity.VotePhoto23Entity;
import kklazy.activities.model.ActivityItem;
import kklazy.activities.model.Point;
import kklazy.activities.model.VotePhoto23;
import kklazy.activities.service.ActivityItemService;
import kklazy.activities.service.PointService;
import kklazy.activities.service.VotePhoto23Service;
import kklazy.common.constants.MappingConstants;
import kklazy.common.entity.QueryEntity;
import kklazy.newtouch.model.Staff;
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
@RequestMapping(MappingConstants.NameSpaces.ACTIVITIES_WECHAT_NAMESPACE)
public class ActivitiesWechatController extends DefaultController<DefaultModel, String> {

	private Boolean postOnly = Boolean.TRUE;
	public static final String USER = "USER";

	@Autowired
	private StaffService staffService;

	@Autowired
	private WechatAccountService accountService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private ActivityItemService activityItemService;

	@Autowired
	private PointService pointService;

	@Autowired
	private VotePhoto23Service votePhoto23Service;

	@Override
	public Service<DefaultModel, String> service() {
		return null;
	}

	@Override
	public String path() {
		return "/activitieswechatpages";
	}

	/**
	 * @return
	 */
	public WechatAccount wechatAccount() {
		final String token = SystemConfigUtils.getSystemConfig("activities.token");
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
			WXBizMsgCrypt wxbmc = new WXBizMsgCrypt(account.getToken(), account.getEncodingAesKey(), account.getCorpId());
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
			WXBizMsgCrypt wxbmc = new WXBizMsgCrypt(account.getToken(), account.getEncodingAesKey(), account.getCorpId());
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
	 * 活动首页
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.INDEX)
	public String index(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}






		modelMap.put("user", user);
		return path() + "/index";
	}

	/**
	 * 活动报名
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.SIGNUP)
	public String signup(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}



		modelMap.put("user", user);
		return path() + "/activity/signup";
	}

	/**
	 * 最美照片评选
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.VOTE_PHOTO)
	public String votephoto(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, VotePhoto23 entity) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		boolean flag = true; // 是否允许进行下一步判断
		boolean overtime = false; // 是否超时
		List<VotePhoto23> votes = votePhoto23Service.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				return criteria;
			}
		});
		if (flag && new Date().compareTo(DateUtils.parse("2017-09-01 18:00")) >= 1) {
			overtime = true;
			modelMap.put("message", "投票已结束~感谢您的参与~");
			flag = false;
		}
		if (flag && votes.size() >= 5) {
			modelMap.put("message", "你已经投过 5 票啦~!");
			flag = false;
		}
		if (flag && StringUtils.isNotBlank(entity.getPicid())) {
			for (VotePhoto23 vote : votes) {
				if (vote.getPicid().equals(entity.getPicid())) {
					modelMap.put("message", "不要重复投票噢~!");
					flag = false;
					break ;
				}
			}
			if (flag) {
				try {
					entity.setEmpno(user.getEmpno());
					entity = votePhoto23Service.persist(entity);
					modelMap.put("message", "投票成功~!");
				} catch (Exception e) {
					modelMap.put("message", "出错喽~大概是你不能再投票了吧~!");
					flag = false;
				}
			}
		}
		votes = votePhoto23Service.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				criteria.add(Restrictions.eq("empno", user.getEmpno()));
				return criteria;
			}
		});
		List<VotePhoto23> allvotes = votePhoto23Service.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		});
		Map<String, Long> points = new HashMap<String, Long>();
		for (int i = 1; i <= 82; i ++) {
			points.put(StringUtils.EMPTY + i, 0L);
		}
		for (VotePhoto23 vote : allvotes) {
			if (StringUtils.isBlank(vote.getPicid())) {
				continue ;
			}
			try {
				points.put(vote.getPicid(), points.get(vote.getPicid()) + sumpoint(vote.getEmpno()));
			} catch (Exception e) {
				// TODO: handle exception
				// 有异常，不需要处理，可能是投票编号大于现有排序数量（历史数据造成的问题）
			}
		}
		Set<String> indexs = points.keySet();
		List<VotePhoto23Entity> retval = new ArrayList<VotePhoto23Entity>();
		for (String picid : indexs) {
			VotePhoto23Entity temp = new VotePhoto23Entity();
			temp.setPicid(picid);
			for (VotePhoto23 vote : votes) {
				if (picid.equals(vote.getPicid())) {
					temp.setEmpno(vote.getEmpno());
				}
			}
			temp.setTotal(points.get(picid));
			retval.add(temp);
		}
		Collections.sort(retval, new Comparator<VotePhoto23Entity>() {
			@Override
			public int compare(VotePhoto23Entity o1, VotePhoto23Entity o2) {
				return new Long(o1.getPicid()).compareTo(new Long(o2.getPicid()));
			}
		});
		modelMap.put("votes", retval);
		modelMap.put("total", votes.size());
		modelMap.put("overtime", overtime);
		modelMap.put("user", user);
		return path() + "/vote/100photo";
	}

	/**
	 * @param empno
	 * @return
	 */
	private Long sumpoint(String empno) {
		if (StringUtils.isBlank(empno)) {
			return 1L;
		}
		if ("94001".equals(empno)) {
			return 100L;
		}
		Staff staff = staffService.findByEmpno(empno);
		if (staff == null) {
			return 1L;
		}
		if ("12".equals(staff.getLevel())) {
			return 50L;
		}
		if ("11".equals(staff.getLevel())) {
			return 20L;
		}
		if ("10".equals(staff.getLevel())) {
			return 20L;
		}
		return 1L;
	}

	/**
	 * 积分详情
	 * 
	 * @param request
	 * @param response
	 * @param modelMap
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = MappingConstants.UrlMapping.POINTS)
	public String points(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) throws Exception {
		final Staff user = (Staff) request.getSession().getAttribute(USER);
		if (user == null) {
			return "redirect:" + MappingConstants.UrlMapping.LOGIN;
		}
		List<ActivityItem> items = activityItemService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.ASC, "sort")));
		Integer total = 0;
//		boolean sign = false;
		boolean team = false;
		String sign_code = "20170710001";
		String team_code = "20170710002";
		for (final ActivityItem item : items) {
			try {
				Integer count = 0;
				List<Point> points = pointService.findBy(new AssembleCriteriaParamsCallBack() {
					@Override
					public DetachedCriteria assembleParams(DetachedCriteria criteria) {
						criteria.createAlias("item", "item").add(Restrictions.eq("item.id", item.getId()));
						criteria.add(Restrictions.eq("empno", user.getEmpno()));
						return criteria;
					}
				});
				if (points != null && !points.isEmpty()) {
					int size = points.size();
					int num = item.getNum().intValue();
					count = size >= num ? num * item.getScore() : size * item.getScore();
					total += count;
					Point point = new Point();
					point.setScore(count);
					item.setPoint(point);
//					if (sign_code.equals(item.getCode())) {
//						sign = true;
//					}
					if (team_code.equals(item.getCode())) {
						team = true;
					}
				}
			} catch (Exception e) {
				continue;
			}
		}
		for (final ActivityItem item : items) {
			if (sign_code.equals(item.getCode()) && team) {
				total -= item.getPoint().getScore();
				item.setPoint(new Point());
			}
		}
		modelMap.put("items", items);
//		modelMap.put("points", points);
		modelMap.put("total", total);
		modelMap.put("user", user);
		return path() + "/point/detail";
	}

}
