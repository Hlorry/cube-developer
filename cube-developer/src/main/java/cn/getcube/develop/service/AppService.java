package cn.getcube.develop.service;

import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.utils.DataResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface AppService {
    AppEntity queryApp(AppPara appPara);

    List<AppEntity> queryAllApps(AppPara appPara);

    DataResult<Map<String, Object>> createApp(AppPara appPara);

    DataResult<Map<String, Object>> modifyApp(AppPara appPara);

    DataResult<Map<String, Object>> avatarApp(AppPara appPara);

    DataResult<Map<String, Object>> deleteApp(String appId, String appName, String password);

    DataResult<Map<String, Object>> updateEnvironment(String environment, String appId, Integer userId);
    
    String generateUseId(int length);
    
    public Map<String,Object> changeEnvironment(String appid);
}
