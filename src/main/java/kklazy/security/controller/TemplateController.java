package kklazy.security.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.persistence.model.DefaultModel;
import kklazy.persistence.service.PageService;
import kklazy.persistence.support.CommonResponse;
import kklazy.persistence.utils.StringUtils;

/**
 * @author kk
 *
 */
@Controller
@RequestMapping(MappingConstants.NameSpaces.TEMPLATE_NAMESPACE)
public class TemplateController extends BasePageController<DefaultModel, String> {

	public static final String BASE = "base";

	public static final String FRAMEWORK = "framework";

	public static final String MOBILE = "mobile";

	public static final String WEUI = "weui";

	@Override
	public PageService<DefaultModel, String> pageservice() { return null; }

	@Override
	public String path() { return StringUtils.EMPTY; }

	private Map<String, Object> json = new HashMap<String, Object>();

	/**
	 * @return
	 */
	@RequestMapping(value = TemplateController.BASE)
	@ResponseBody
	public CommonResponse base() {

		List<String> script = new ArrayList<String>();
		List<String> style = new ArrayList<String>();

//		script.add("/script/jquery/jquery-ui-1.10.3.custom.min.js");
		script.add("/script/bootstrap/bootstrap.min.js");
		script.add("/script/kklazy/kklazy-validation.js");

		style.add("/style/bootstrap/bootstrap.min.css");
		style.add("/style/font-awesome/font-awesome.min.css");
		style.add("/style/kklazy/kklazy-common-0.0.1.css");

		retval = CommonResponse.SUCCESS();
		json.put("script", script);
		json.put("style", style);
		retval.setBody(json);

		return retval;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = TemplateController.FRAMEWORK)
	@ResponseBody
	public CommonResponse framework() {

		List<String> script = new ArrayList<String>();
		List<String> style = new ArrayList<String>();

		style.add("/style/kklazy/kklazy-framework.css");

		retval = CommonResponse.SUCCESS();
		json.put("script", script);
		json.put("style", style);
		retval.setBody(json);

		return retval;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = TemplateController.MOBILE)
	@ResponseBody
	public CommonResponse mobile() {

		List<String> script = new ArrayList<String>();
		List<String> style  = new ArrayList<String>();

		script.add("/script/kklazy/kklazy-validation.js");

		style.add("/style/bootstrap/bootstrap.min.css");
		style.add("/style/font-awesome/font-awesome.min.css");
		style.add("/style/kklazy/wechat/wechat-mobile.css");

		retval = CommonResponse.SUCCESS();
		json.put("script", script);
		json.put("style", style);
		retval.setBody(json);

		return retval;
	}

	/**
	 * @return
	 */
	@RequestMapping(value = TemplateController.WEUI)
	@ResponseBody
	public CommonResponse weui() {

		List<String> script = new ArrayList<String>();
		List<String> style  = new ArrayList<String>();

		script.add("/script/weui/jquery-weui.min.js");
		script.add("/script/weui/city-picker.min.js");
		script.add("/script/weui/swiper.min.js");

		style.add("/style/weui/jquery-weui.min.css");
		style.add("/style/weui/weui.min.css");

		retval = CommonResponse.SUCCESS();
		json.put("script", script);
		json.put("style", style);
		retval.setBody(json);

		return retval;
	}

}
