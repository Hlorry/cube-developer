package cn.getcube.develop.controller;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.entity.SdkEntity;
import cn.getcube.develop.service.SdkService;
import cn.getcube.develop.utils.DataResult;
import cn.getcube.develop.utils.SdkConstants;
import com.alibaba.fastjson.JSONObject;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping(value = "/sdk")
public class SdkController {
	@Resource
	private SdkService sdkService;
	
	@RequestMapping(value="/newest/sdk", method = RequestMethod.POST)
	public DataResult<JSONObject> getNewestSdk() {
		try {
			List<SdkEntity> list = sdkService.selectNewestSdk();
			JSONObject jsonObject = new JSONObject();
			jsonObject.put("sdks",list);
			return new DataResult<>(jsonObject);
		} catch (Exception e) {
			return new DataResult<>(StateCode.AUTH_ERROR_10026, SdkConstants.QUERY_ERROR);
		}
	}
}
