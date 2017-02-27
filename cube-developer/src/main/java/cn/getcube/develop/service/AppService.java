package cn.getcube.develop.service;

import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.para.AppPara;

import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface AppService {
    AppEntity queryApp(AppPara appPara);

    List<AppEntity> queryAllApps(AppPara appPara);

    Map<String, String> createApp(AppPara appPara);

    Map<String, String> modifyApp(AppPara appPara);

    Map<String, String> avatarApp(AppPara appPara);

    Map<String, String> deleteApp(String appId, String appName, String password);

    Map<String, Object> updateEnvironment(String environment, String appId, Integer userId);
    
    String generateUseId(int length);
    
    public Map<String,Object> changeEnvironment(String appid);
}
