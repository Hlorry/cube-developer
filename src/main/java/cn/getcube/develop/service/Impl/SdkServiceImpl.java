package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.SdkDao;
import cn.getcube.develop.service.SdkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

@Service
public class SdkServiceImpl implements SdkService{
	@Resource
	private SdkDao sdkDao;
	@Override
	public List<Map<String, String>> selectNewestSdk() {
		return sdkDao.selectNewestSdk();
	}

}
