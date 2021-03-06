package cn.getcube.develop.controller;

import java.util.HashMap;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import cn.getcube.develop.service.SdkService;

@Controller
@RequestMapping(value = "/sdk")
public class SdkController {
	@Resource
	private SdkService sdkService;
	
	@RequestMapping(value="/newestSdk")
	public ModelAndView getNewestSdk() {
		AbstractView jsonView = new MappingJackson2JsonView();
		Map<String, Object> map = new HashMap<>();
		map.put("sdks", sdkService.selectNewestSdk());
		jsonView.setAttributesMap(map);
		return new ModelAndView(jsonView);
	}
}
