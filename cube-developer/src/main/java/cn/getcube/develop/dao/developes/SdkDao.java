package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.SdkEntity;

import java.util.List;

public interface SdkDao {
	List<SdkEntity> selectNewestSdk();
}
