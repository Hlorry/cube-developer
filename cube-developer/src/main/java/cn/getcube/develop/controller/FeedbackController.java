package cn.getcube.develop.controller;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.entity.FeedbackEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.FeedbackService;
import cn.getcube.develop.utils.DataResult;
import cn.getcube.develop.utils.FeedbackConstants;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
@RequestMapping("/feedback")
public class FeedbackController {
	@Autowired
	private FeedbackService feedbackService;

	@RequestMapping(value = "/add",method = RequestMethod.POST)
	@TokenVerify
	public DataResult<JSONObject> add(@RequestParam(name = "token", required = true) String token,
									  @RequestParam(name = "title")String title,
									  @RequestParam(name = "content")String content,
									  @RequestParam(name = "phone")String phone,
									  UserEntity userSession){
		FeedbackEntity feedbackEntity = new FeedbackEntity();
		feedbackEntity.setContent(content);
		feedbackEntity.setPhone(phone);
		feedbackEntity.setTitle(title);
		feedbackEntity.setUserId(userSession.getId());
		Date date = new Date();
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		feedbackEntity.setCreateTime(simpleDateFormat.format(date));
		try {
			feedbackService.add(feedbackEntity);

			JSONObject jsonObject = new JSONObject();
			jsonObject.put("feedback",feedbackEntity);
			return new DataResult<>(jsonObject);
		} catch (Exception e) {
			return new DataResult<>(StateCode.AUTH_ERROR_10009.getCode(), FeedbackConstants.CREATE_FAILED);
		}
	}
}
