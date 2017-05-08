package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.SdkDao;
import cn.getcube.develop.entity.SdkEntity;
import cn.getcube.develop.service.SdkService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SdkServiceImpl implements SdkService{
	@Resource
	private SdkDao sdkDao;
	@Override
	public List<SdkEntity> selectNewestSdk() {
		return sdkDao.selectNewestSdk();
	}

}
