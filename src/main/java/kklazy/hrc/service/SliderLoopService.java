package kklazy.hrc.service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import kklazy.hrc.model.SliderLoop;
import kklazy.persistence.service.impl.DefaultService;

@Service("sliderLoopService")
@Transactional(rollbackFor = Exception.class)
public class SliderLoopService extends DefaultService<SliderLoop, String> {


}
