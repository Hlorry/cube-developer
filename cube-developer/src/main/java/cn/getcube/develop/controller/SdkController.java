package cn.getcube.develop.controller;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.entity.SdkEntity;
import cn.getcube.develop.service.SdkService;
import cn.getcube.develop.utils.DataResult;
import cn.getcube.develop.utils.SdkConstants;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.annotation.Resource;
import java.util.List;

@Controller
@RequestMapping(value = "/sdk")
public class SdkController {
	@Resource
	private SdkService sdkService;
	
	@RequestMapping(value="/newest/sdk", method = RequestMethod.POST)
	public DataResult<List<SdkEntity>> getNewestSdk() {
		try {
			List<SdkEntity> list = sdkService.selectNewestSdk();
			return new DataResult<>(list);
		} catch (Exception e) {
			return new DataResult<>(StateCode.AUTH_ERROR_10026, SdkConstants.QUERY_ERROR);
		}
	}
}
