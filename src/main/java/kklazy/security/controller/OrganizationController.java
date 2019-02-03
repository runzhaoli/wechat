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
import kklazy.security.model.Organization;
import kklazy.security.service.OrganizationService;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.ORGANIZATION_NAMESPACE)
public class OrganizationController extends BasePageController<Organization, String> {

	@Autowired
	private OrganizationService organizationService;

	@Override
	public PageService<Organization, String> pageservice() {
		return organizationService;
	}

	@Override
	public String path() {
		return "/webpages/security/organization";
	}

	@Override
	protected CommonResponse commithandler(HttpServletRequest request, HttpServletResponse response, ModelMap modelMap, Organization entity) {
		Organization temp = service().findBy(entity.getId());

		if (temp != null) {
			entity.setParent(temp.getParent());
			entity.setSublevel(temp.getSublevel());
			entity.setEmployees(temp.getEmployees());
		}

		return super.commithandler(request, response, modelMap, entity);
	}

}
