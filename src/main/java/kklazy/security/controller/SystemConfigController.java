package kklazy.security.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.security.model.SystemConfig;
import kklazy.security.service.SystemConfigService;
import kklazy.security.support.systemconfig.utils.SystemConfigUtils;

/**
 * @author kk
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.SYSTEM_CONFIG_NAMESPACE)
public class SystemConfigController extends BasePageController<SystemConfig, String> {

	@Autowired
	private SystemConfigService systemConfigService;

	@Override
	public PageService<SystemConfig, String> pageservice() {
		return systemConfigService;
	}

	@Override
	public String path() {
		return "/webpages/security/systemconfig";
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, SystemConfig entity) {
		SystemConfigUtils.clean();
		return super.commithandler(request, response, modelMap, entity);
	}

}
