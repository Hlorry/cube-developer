package cn.getcube.develop.service.Impl;

import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import cn.getcube.develop.dao.developes.SdkDao;
import cn.getcube.develop.service.SdkService;

@Service
public class SdkServiceImpl implements SdkService{
	@Resource
	private SdkDao sdkDao;
	@Override
	public List<Map<String, String>> selectNewestSdk() {
		return sdkDao.selectNewestSdk();
	}

}
