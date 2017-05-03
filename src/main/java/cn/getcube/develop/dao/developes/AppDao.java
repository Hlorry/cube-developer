package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.para.AppInfo;
import cn.getcube.develop.para.AppPara;

import java.util.List;

/**
 * Created by Administrator on 2016/3/11.
 */
public interface AppDao {
    List<AppEntity> queryAllApps(AppPara appPara);

    AppEntity queryAppByAppId(AppPara appPara);

    void deleteApp(AppPara appPara);

    int createApp(AppPara appPara);

    void modifyApp(AppPara appPara);

    void avatarAppByAppId(AppPara appPara);

    AppEntity isAppNameExits(AppPara appPara);

    AppEntity isAppNameExitsByAppId(AppPara appPara);

    Integer updateEnvironment(AppPara appPara);
    
    AppInfo getAppinfoByAppid(String appid);
    
    //获取所有的测试环境的使用的cubeid
    List<String> getAllUseid();
}
