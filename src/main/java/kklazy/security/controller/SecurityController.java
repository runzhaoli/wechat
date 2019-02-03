package kklazy.security.controller;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.service.PageService;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.SECURITY_NAMESPACE)
public class SecurityController extends BasePageController<DefaultModel, String> {

	public static final String LOGIN = "/login";

	public static final String ACCESSDENIED = "/accessdenied";

	@Override
	public PageService<DefaultModel, String> pageservice() { return null; }

	@Override
	public String path() { return "/webpages/security"; }

	/**
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = SecurityController.LOGIN)
	public String login(ModelMap modelMap) {
		try {
			Logger.getLogger(getClass()).info("login");
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
		}
		return path() + SecurityController.LOGIN;
	}

	/**
	 * @param modelMap
	 * @return
	 */
	@RequestMapping(value = SecurityController.ACCESSDENIED)
	public String accessdenied(ModelMap modelMap) {
		try {
			Logger.getLogger(getClass()).warn("accessdenied");
		} catch (Exception e) {
			Logger.getLogger(getClass()).error(e.getMessage(), e);
		}
		return path() + SecurityController.ACCESSDENIED;
	}

}
