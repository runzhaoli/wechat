package kklazy.hrc.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import kklazy.common.constants.MappingConstants;
import kklazy.common.controller.BasePageController;
import kklazy.hrc.model.SliderLoop;
import kklazy.hrc.service.SliderLoopService;
import kklazy.persistence.service.PageService;

@Controller
@RequestMapping(MappingConstants.NameSpaces.SLIDER_LOOP_NAMESPACE)
public class SliderLoopController extends BasePageController<SliderLoop, String> {

	@Autowired
	private SliderLoopService sliderLoopService;

	@Override
	public PageService<SliderLoop, String> pageservice() {
		return sliderLoopService;
	}

	@Override
	public String path() {
		return "/webpages/hrc/sliderloop";
	}

}
