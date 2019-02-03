package kklazy.activities.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.data.domain.Sort.Direction;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.activities.model.Activity;
import kklazy.activities.model.Point;
import kklazy.activities.service.ActivityService;
import kklazy.activities.service.PointService;
import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.newtouch.model.Staff;
import kklazy.newtouch.service.StaffService;
import kklazy.persistence.callback.AssembleCriteriaParamsCallBack;
import kklazy.persistence.model.DefaultPageQueryModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;

@Controller
@RequestMapping(MappingConstants.NameSpaces.POINT_NAMESPACE)
public class PointController extends BasePageController<Point, String> {

	@Autowired
	private PointService pointService;

	@Autowired
	private ActivityService activityService;

	@Autowired
	private StaffService staffService;

	@Override
	public PageService<Point, String> pageservice() {
		return pointService;
	}

	@Override
	public String path() {
		return "/webpages/activities/point";
	}

	@Override
	protected void searchhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, DefaultPageQueryModel query, final Point entity) {
		try {
			query = assembleQuery(query);
			List< Point > points = pageservice().findBy(new AssembleCriteriaParamsCallBack() {
				@Override
				public DetachedCriteria assembleParams(DetachedCriteria criteria) {
					criteria.add(Restrictions.eq("delete", entity.getDelete()));
					return criteria;
				}
			}, new Sort(new Sort.Order(Direction.DESC, "id")));
			modelMap.put(ENTITY, entity);

			String sign_code = "20170710001";
			String team_code = "20170710002";
			Map<String, List<Point>> temp = new HashMap<String, List<Point>>();
			for (Point point : points) {
				List<Point> p = temp.get(point.getEmpno());
				p = p == null ? new ArrayList<Point>() : p;
				point.setScore(point.getItem().getScore());
				p.add(point);
				temp.put(point.getEmpno(), p);
			}
			Set<String> empnos = temp.keySet();
			List< Point > retval = new ArrayList< Point >();
			for (String empno : empnos) {
				boolean team = false;
				List<Point> p = temp.get(empno);
				for (Point point : p) {
					if (team_code.equals(point.getItem().getCode())) {
						team = true;
					}
				}
				for (Point point : p) {
					if (sign_code.equals(point.getItem().getCode()) && team) {
						point.setScore(0);
					}
					retval.add(point);
				}
			}
			modelMap.put(PAGE, retval);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#createhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap)
	 */
	@Override
	protected void createhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap) {
		List<Activity> activities =  activityService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));
		modelMap.put("activities", activities);
		super.createhandler(request, response, modelMap);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#modifyhandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, java.io.Serializable)
	 */
	@Override
	protected void modifyhandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String id) {
		List<Activity> activities =  activityService.findBy(new AssembleCriteriaParamsCallBack() {
			@Override
			public DetachedCriteria assembleParams(DetachedCriteria criteria) {
				return criteria;
			}
		}, new Sort(new Sort.Order(Direction.DESC, "start")));
		modelMap.put("activities", activities);
		super.modifyhandler(request, response, modelMap, id);
	}

	/* (non-Javadoc)
	 * @see kklazy.persistence.controller.DefaultController#commithandler(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse, org.springframework.ui.ModelMap, kklazy.persistence.model.DefaultModel)
	 */
	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Point entity) {
		CommonResponse retval = CommonResponse.FAILURE();
		if (entity == null) {
			return retval;
		}
		Staff staff = staffService.findByEmpno(entity.getEmpno());
		if (staff == null) {
			retval.setMessage("输入的工号不正确~");
			return retval;
		}
		entity.setStaff(staff);
		return super.commithandler(request, response, modelMap, entity);
	}

}
