package cn.getcube.develop.service;

import cn.getcube.develop.entity.SdkEntity;

import java.util.List;

public interface SdkService {
	List<SdkEntity> selectNewestSdk();
}
