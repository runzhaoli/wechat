package kklazy.gala.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.gala.entity.SeatingChart;
import kklazy.gala.service.SeatingChartService;
import kklazy.persistence.constants.DefaultMappingConstants;
import kklazy.persistence.controller.DefaultController;
import kklazy.persistence.service.Service;

@Controller
@RequestMapping(MappingConstants.NameSpaces.SEATING_CHART_NAMESPACE)
public class SeatingChartController extends DefaultController<SeatingChart, String> {

	@Autowired
	private SeatingChartService seatingChartService;

	@Override
	public Service<SeatingChart, String> service() {
		return seatingChartService;
	}

	@Override
	public String path() {
		return "/gala/seatingchart";
	}

	/**
	 * @param request
	 * @param response
	 * @param modelMap
	 * @param name
	 * @return
	 * @throws Exception
	 */
	@RequestMapping(value = DefaultMappingConstants.SEARCH)
	public String search(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, String empno, String name) throws Exception {
		try {
			if (StringUtils.isNotBlank(name) || StringUtils.isNotBlank(empno)) {
				List<SeatingChart> charts = seatingChartService.findByName(name, empno);
				modelMap.put("empno", empno);
				modelMap.put("name", name);
				modelMap.put("charts", charts);
				if (charts != null && charts.size() > 1) {
					modelMap.put("message", "哇哦，缘分呐！<br/>有和您同名的哎！<br/>看看以下哪一桌有您的小伙伴呢？");
				}
				if (charts == null || charts.isEmpty()) {
					modelMap.put("message", "没找到您的信息呢<br/>要不再检查一下您的姓名是否输入正确？<br/>或者咨询您附近的礼仪小姐姐<br/>请她们帮帮您~~");
				}
			}
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return path() + DefaultMappingConstants.SEARCH;
	}

}
