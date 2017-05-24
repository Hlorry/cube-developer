package cn.getcube.develop.service.Impl;

import cn.getcube.develop.AppConstants;
import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.dao.developes.AppDao;
import cn.getcube.develop.dao.developes.NodeDao;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.para.AppInfo;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.service.AppCacheService;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.utils.BaseResult;
import cn.getcube.develop.utils.DataResult;
import cn.getcube.develop.utils.Md5Helper;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by Administrator on 2016/3/11.
 */
@Service
public class AppServiceImpl implements AppService {

    @Resource
    private AppDao appDao;

    @Resource
    private UserDao userDao;

    @Resource
    private NodeDao nodeDao;

    @Resource
    private AppCacheService appCache;

    @Override
    public AppEntity queryApp(AppPara appPara) {
        return appDao.queryAppByAppId(appPara);
    }

    @Override
    public List<AppEntity> queryAllApps(AppPara appPara) {
        List<AppEntity> appEntities = new ArrayList<>();
        try {
            appEntities = appDao.queryAllApps(appPara);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return appEntities;
    }

    @Transactional("transactionManager")
    public DataResult<Map<String, Object>> createApp(AppPara appPara) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();

        List<String> ids = appDao.getAllUseid();
        List<Map<String, Integer>> useids = new ArrayList<>();
        int max = 0;
        String[] num, tmp = null;
        if (ids != null && !ids.isEmpty()) {
            for (String e : ids) {
                num = e.split(",");
                for (String ex : num) {
                    tmp = ex.split("-");
                    Map<String, Integer> m = new HashMap<>();
                    m.put("start", Integer.valueOf(tmp[0]));
                    m.put("end", Integer.valueOf(tmp[1]));
                    max = Integer.valueOf(tmp[1]) > max ? Integer.valueOf(tmp[1]) : max;
                    useids.add(m);
                }
            }
        }

        try {
            if (null != appDao.isAppNameExits(appPara)) {
                dataResult.setCode(StateCode.APP_NAME_EXIST.getCode());
                dataResult.setDesc("app name is exist.");
            } else {
                String appid = Md5Helper.MD5.getMD516(new Date().toString());
                appPara.setAppId(UUID.randomUUID().toString().replaceAll("-", ""));
                appPara.setAppKey(UUID.randomUUID().toString().replaceAll("-", ""));
                appPara.setTest_appid(UUID.randomUUID().toString().replaceAll("-", ""));
                appPara.setTest_appkey(UUID.randomUUID().toString().replaceAll("-", ""));
                appPara.setValidityStart(new Date());

                SimpleDateFormat dft = new SimpleDateFormat("yyyy-MM-dd");
                Date beginDate = new Date();
                Calendar date = Calendar.getInstance();
                date.setTime(beginDate);
                //date.set(Calendar.DATE, date.get(Calendar.DATE) + 30);
                date.set(Calendar.YEAR, date.get(Calendar.YEAR) + 1);
                Date endDate = dft.parse(dft.format(date.getTime()));

                appPara.setValidityEnd(endDate);

                //生成100个id
                //appPara.setUseId((max+1)+"-"+(max+100));
                appPara.setTest_useid((max + 1) + "-" + (max + 100));
                appPara.setUseServing(AppConstants.USESERVING_JSON);
                //测试环境1，生产环境2
                appPara.setEnvironment(1);
                appPara.setCheckType(0);
                appDao.createApp(appPara);
                map.put("app", appPara);
                dataResult.setCode(StateCode.Ok.getCode());
                dataResult.setDesc("Ok");
                dataResult.setData(map);

                //随机获取一个默认测试节点
                Integer nodeid = nodeDao.getOneDefaultTestNode();
                if (nodeid == null) {
                    //默认测试节点不存在，随机获取一个测试节点
                    nodeid = nodeDao.getOneTestNode();
                }
                Map<String, Object> para = new HashMap<>();
                // para.put("appid", app_id);
                para.put("appid", appPara.getTest_appid());
                para.put("nodeid", nodeid);
                nodeDao.insertAppNode(para);
                //更新缓存
                appCache.refreshAppinfo(appPara.getAppId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            dataResult.setCode(StateCode.APP_CREATE_ERROR.getCode());
            dataResult.setDesc("create app error");
        }
        return dataResult;
    }

    @Override
    public BaseResult updateAppState(Integer id, Integer state) {
        AppPara appPara = new AppPara();
        appPara.setId(id);
        appPara.setAppState(state);
        int appState = appDao.updateAppState(appPara);
        if (appState > 0) {
                return BaseResult.build(StateCode.Ok,AuthConstants.MSG_OK);
        }
        return BaseResult.build(StateCode.Unknown,"update app <state> error.");
    }

    @Override
    public DataResult<Map<String, Object>> modifyApp(AppPara appPara) {
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        Map<String, Object> map = new HashMap<>();
        try {
            if (null != appDao.isAppNameExitsByAppId(appPara)) {
                dataResult.setCode(StateCode.APP_NAME_EXIST.getCode());
                dataResult.setDesc("app name is exist.");
            } else {
                appPara.setModifyTime(new Date());
                appDao.modifyApp(appPara);
                AppEntity appEntity = appDao.queryAppByAppId(appPara);
                map.put("app", appEntity);
                dataResult.setCode(StateCode.Ok.getCode());
                dataResult.setDesc("Ok.");
                dataResult.setData(map);
            }
        } catch (Exception e) {
            e.printStackTrace();
            dataResult.setCode(StateCode.APP_MODIFY_ERROR.getCode());
            dataResult.setDesc("modify app error");
        }
        //更新缓存
        appCache.refreshAppinfo(appPara.getAppId());
        return dataResult;
    }

    @Override
    public DataResult<Map<String, Object>> deleteApp(String appId, String appName, String password) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        // 判断用户输入的应用名称是否和应用ID匹配
        // 如果不匹配返回
        try {
            AppPara appPara = new AppPara();
            //更新缓存
            AppInfo info = appDao.getAppinfoByAppid(appId);
            appPara.setId(info.getId());
            appPara.setAppId(appId);
            appPara.setTest_appid(info.getTest_appid());
            AppEntity appEntity = appDao.queryAppByAppId(appPara);
            if (!appEntity.getAppName().equals(appName)) {
                dataResult.setCode(StateCode.APP_NAME_NOT_MATCH.getCode());
                dataResult.setDesc("app name is not match.");
                return dataResult;
            }
            UserEntity ue = new UserEntity();
            ue.setId(appEntity.getUserId());
            ue = userDao.queryUser(ue);
            // 判断用户密码是否正确
            // 如果不正确返回
            if (!ue.getPassword().equals(password)) {
                dataResult.setCode(StateCode.APP_LOGIN_PWD_NOT_MATCH.getCode());
                dataResult.setDesc("login pwd is not match.");
                return dataResult;
            }
            // 根据应用ID进行删除操作
            try {
                appDao.deleteApp(appPara);
                appDao.delAppNodes(appPara);
            } catch (Exception e) {
                e.printStackTrace();
            }
            dataResult.setCode(StateCode.Ok.getCode());
            dataResult.setDesc("Ok.");
            appCache.delAppInfo(info.getAppid());
            return dataResult;
        } catch (Exception exception) {
            exception.printStackTrace();
            //map.put("code", "4006");
            //map.put("msg", "删除应用出错");
            dataResult.setCode(StateCode.Unknown.getCode());
            dataResult.setDesc("Unknown error");
            return dataResult;
        }
    }

    @Override
    public DataResult<Map<String, Object>> updateEnvironment(String environment, String appId, Integer userId) {
        AppPara appPara = new AppPara();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();

        if (environment != null) {
            appPara.setEnvironment(Integer.parseInt(environment));
        }
        if (appId != null) {
            appPara.setAppId(appId);
        }
        UserEntity userEntity = new UserEntity();
        userEntity.setId(userId);
        UserEntity user = userDao.queryUser(userEntity);
        if (user.getBiz_verify() == 0) {
            dataResult.setCode(StateCode.AUTH_ERROR_10020.getCode());
            dataResult.setDesc("帐号认证审核中");
            return dataResult;
        }
        Integer integer = appDao.updateEnvironment(appPara);
        if (integer == null) {
            dataResult.setCode(StateCode.APP_UPDATE_ENVIRONMENT_ERROR.getCode());
            dataResult.setDesc("update fail");
            return dataResult;
        } else if (integer > 0) {
            dataResult.setCode(StateCode.Ok.getCode());
            dataResult.setDesc("Ok.");
            //更新缓存
            appCache.refreshAppinfo(appId);
            return dataResult;
        } else {
            dataResult.setCode(StateCode.Unknown.getCode());
            dataResult.setDesc("Unknown error");
            return dataResult;
        }
    }

    @Override
    public DataResult<Map<String, Object>> avatarApp(AppPara appPara) {
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        try {
            appDao.avatarAppByAppId(appPara);
            dataResult.setCode(StateCode.Ok.getCode());
            dataResult.setDesc("Ok.");
            appCache.refreshAppinfo(appPara.getAppId());
            return dataResult;
        } catch (Exception e) {
            dataResult.setCode(StateCode.Unknown.getCode());
            dataResult.setDesc("Unknown error.");
            return dataResult;
        }

    }


    public String generateUseId(int length) {
        List<String> ids = appDao.getAllUseid();
        List<Map<String, Integer>> useids = new ArrayList<>();
        int max = 0;
        if (ids != null && !ids.isEmpty()) {
            for (String e : ids) {
                try {
                    Map<String, Integer> m = new HashMap<>();
                    m.put("start", Integer.valueOf(e.split("-")[0]));
                    m.put("end", Integer.valueOf(e.split("-")[1]));
                    max = Integer.valueOf(e.split("-")[1]) > max ? Integer.valueOf(e.split("-")[1]) : max;
                    useids.add(m);
                } catch (NumberFormatException e1) {

                }
            }
        }

        return (max + 1) + "-" + (max + length);
    }

    public Map<String, Object> changeEnvironment(String appid) {
        AppPara para = new AppPara();
        para.setAppId(appid);
        para.setEnvironment(2);
        Map<String, Object> map = new HashMap<>();
        try {
            appDao.updateEnvironment(para);
            map.put("state", 200);
            map.put("msg", "ok");
        } catch (Exception e) {
            map.put("state", 400);
            map.put("msg", "服务器出错了");
        }
        return map;
    }
}
