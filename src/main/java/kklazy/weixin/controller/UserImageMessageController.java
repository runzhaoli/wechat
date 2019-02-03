package kklazy.weixin.controller;

import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.activities.model.Activity;
import kklazy.activities.model.ActivityItem;
import kklazy.activities.model.Point;
import kklazy.activities.service.ActivityItemService;
import kklazy.activities.service.ActivityService;
import kklazy.activities.service.PointService;
import kklazy.common.constants.ContextConstants;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.newtouch.service.StaffService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.security.context.SecurityApplicationContext;
import kklazy.weixin.model.UserImageMessage;
import kklazy.weixin.service.UserImageMessageService;
import kklazy.weixin.service.WechatService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.USER_IMAGE_MESSAGE_NAMESPACE)
public class UserImageMessageController extends BasePageController<UserImageMessage, String> {

	@Autowired
	private UserImageMessageService userImageMessageService;

	@Autowired
	private WechatService wechatService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private ActivityItemService activityItemService;

	@Autowired
	private PointService pointService;

	@Autowired
	private StaffService staffService;

	@Override
	public PageService<UserImageMessage, String> pageservice() {
		return userImageMessageService;
	}

	@Override
	public String path() {
		return "/webpages/weixin/userimagemessage";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final UserImageMessage entity) {
		try {
			if (query == null) {
				query = new DefaultPageQueryModel();
			}
			Page< UserImageMessage > page = pageservice().pageBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					if (StringUtils.isNotBlank(entity.getUserID())) {
						criteria.add(Restrictions.eq("userID", entity.getUserID()));
					}
					criteria.add(Restrictions.eq("enabled", entity.getEnabled()));
					criteria.add(Restrictions.eq("delete", entity.getDelete()));
					return criteria;
				}
			}, new PageRequest(query.getPageindex(), query.getPagesize(),
					new Sort(new Sort.Order(Direction.DESC, "create"))
				)
			);
			modelMap.put(ENTITY, entity);
			modelMap.put(PAGE, page);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		List<Activity> activities =  activityService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));
		modelMap.put("staff", staffService.findByEmpno(service().findBy(id).getUserID()));
		modelMap.put("activities", activities);
		super.modifyhandler(request, response, modelMap, id);
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, UserImageMessage entity) {
		UserImageMessage temp = service().findBy(entity.getId());
		if (temp.getItem() != null) {
			// 已经审核过的图片不能进行二次审核
			retval = CommonResponse.FAILURE();
			retval.setMessage("已经审核过的图片不能进行二次审核");
			return retval;
		}
		try {
			temp.setActivity(entity.getActivity());
			temp.setItem(entity.getItem());
			temp.setModify(new Date());
			temp.setModifier(SecurityApplicationContext.getUsername() + " / " + SecurityApplicationContext.getName());
			service().merge(temp);

			Point point = new Point();
			point.setActivity(temp.getActivity());
			point.setItem(temp.getItem());
			point.setStaff(staffService.findByEmpno(temp.getUserID()));
			point.setEmpno(temp.getUserID());
			point.setType(ContextConstants.POINT_TYPE.POINT_TYPE_01);
			ActivityItem item = activityItemService.findBy(temp.getItem().getId());
			point.setScore(item.getScore());
			pointService.merge(point);

			retval = CommonResponse.SUCCESS();

		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		try {
			wechatService.messageSend(temp.getAccount(), temp.getUserID(), "您上传的图片[ID:" + temp.getMsgId() + "]已经审核，请查看您的积分~~");
		} catch (Exception e) {
			// 回复用户消息失败，不做处理
		}
		return retval;
	}

}
