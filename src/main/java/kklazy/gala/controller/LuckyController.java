package kklazy.gala.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.gala.entity.Lucky;
import kklazy.gala.service.LuckyService;
import kklazy.persistence.constants.DefaultMappingConstants;
import kklazy.persistence.controller.DefaultController;
import kklazy.persistence.service.Service;

@Controller
@RequestMapping(MappingConstants.NameSpaces.LUCKY_NAMESPACE)
public class LuckyController extends DefaultController<Lucky, String> {

	@Autowired
	private LuckyService luckyService;

	@Override
	public Service<Lucky, String> service() {
		return luckyService;
	}

	@Override
	public String path() {
		return "/gala/lucky";
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
			List<Lucky> luckies = luckyService.findAll();
			modelMap.put("luckies", luckies);
		} catch (Exception e) {
			logger.error(e.getMessage(), e);
		}
		return path() + DefaultMappingConstants.SEARCH;
	}

}
