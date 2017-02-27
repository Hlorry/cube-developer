package cn.getcube.develop.dao.developes;

import java.util.List;
import java.util.Map;

public interface SdkDao {
	List<Map<String,String>> selectNewestSdk();
}
