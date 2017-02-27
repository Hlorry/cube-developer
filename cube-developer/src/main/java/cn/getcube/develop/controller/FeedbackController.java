package cn.getcube.develop.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import cn.getcube.develop.entity.FeedbackEntity;
import cn.getcube.develop.service.FeedbackService;

@Controller
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;
	
	@ResponseBody
	@RequestMapping("/add")
	public Map<String, Object> add(FeedbackEntity entity){
		feedbackService.add(entity);
		Map<String, Object> map = new HashMap<>();
		map.put("httpCode", 200);
		return map;
	}
}
