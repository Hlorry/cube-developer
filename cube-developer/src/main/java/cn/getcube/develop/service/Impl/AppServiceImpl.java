package cn.getcube.develop.service.Impl;

import cn.getcube.develop.AppConstants;
import cn.getcube.develop.dao.developes.AppDao;
import cn.getcube.develop.dao.developes.NodeDao;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.para.AppInfo;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.service.AppCacheService;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.utils.Md5Helper;
import org.springframework.beans.factory.annotation.Autowired;
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
    public Map<String, String> createApp(AppPara appPara) {
        Map<String, String> map = new HashMap<>();
        
        List<String> ids=appDao.getAllUseid();
		List<Map<String,Integer>> useids=new ArrayList<>();
		int max=0;
		String [] num,tmp=null;
		if(ids!=null&&!ids.isEmpty()){
			for(String e:ids){
					num=e.split(",");
					for(String ex:num){
						tmp=ex.split("-");
						Map<String, Integer> m=new HashMap<>();
						m.put("start", Integer.valueOf(tmp[0]));
						m.put("end", Integer.valueOf(tmp[1]));
						max=Integer.valueOf(tmp[1])>max?Integer.valueOf(tmp[1]):max;
						useids.add(m);
					}
			}
		}
        
        try {
            if (null != appDao.isAppNameExits(appPara)) {
                map.put("errcode", "10088");
                map.put("errmsg", "应用名称已经存在");
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
                date.set(Calendar.YEAR,date.get(Calendar.YEAR)+1);
                Date endDate = dft.parse(dft.format(date.getTime()));

                appPara.setValidityEnd(endDate);

        		//生成100个id
                //appPara.setUseId((max+1)+"-"+(max+100));
                appPara.setTest_useid((max+1)+"-"+(max+100));
                appPara.setUseServing(AppConstants.USESERVING_JSON);
                //测试环境1，生产环境2
                appPara.setEnvironment(1);
                appPara.setCheckType(0);
                appDao.createApp(appPara);
                map.put("state", "200");
                map.put("appId", appPara.getAppId());
                
                //随机获取一个默认测试节点
                Integer nodeid=nodeDao.getOneDefaultTestNode();
                if(nodeid==null){
                	//默认测试节点不存在，随机获取一个测试节点
                	nodeid=nodeDao.getOneTestNode();
                }
                Map<String,Object> para=new HashMap<>();
               // para.put("appid", app_id);
                para.put("appid", appPara.getTest_appid());
                para.put("nodeid", nodeid);
                nodeDao.insertAppNode(para);
                //更新缓存
                appCache.refreshAppinfo(appPara.getAppId());
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errcode", "10089");
            map.put("errmsg", "create app error");
        }
        return map;
    }

    @Override
    public Map<String, String> modifyApp(AppPara appPara) {
        Map<String, String> map = new HashMap<>();
        try {
            if (null != appDao.isAppNameExitsByAppId(appPara)) {
                map.put("errcode", "10088");
                map.put("errmsg", "应用名称已经存在");
            } else {
                appPara.setModifyTime(new Date());
                appDao.modifyApp(appPara);
                map.put("state", "200");
                map.put("appId", String.valueOf(appPara.getAppId()));
            }
        } catch (Exception e) {
            e.printStackTrace();
            map.put("errcode", "10089");
            map.put("errmsg", "modify app error");
        }
        //更新缓存
        appCache.refreshAppinfo(appPara.getAppId());
        return map;
    }

    @Override
    public Map<String, String> deleteApp(String appId, String appName, String password) {
        // 判断用户输入的应用名称是否和应用ID匹配
        // 如果不匹配返回
        try {
            AppPara appPara = new AppPara();
            //更新缓存
            AppInfo info=appDao.getAppinfoByAppid(appId);
            appPara.setId(info.getId());
            appPara.setAppId(appId);
            appPara.setTest_appid(info.getTest_appid());
            AppEntity appEntity = appDao.queryAppByAppId(appPara);
            if (!appEntity.getAppName().equals(appName)) {
                Map<String, String> map = new HashMap<>();
                map.put("code", "4005");
                map.put("msg", "应用名称不匹配");
                return map;
            }
            UserEntity ue = new UserEntity();
            ue.setId(appEntity.getUserId());
            ue = userDao.queryUser(ue);
            // 判断用户密码是否正确
            // 如果不正确返回
            if (!ue.getPassword().equals(password)) {
                Map<String, String> map = new HashMap<>();
                map.put("code", "4005");
                map.put("msg", "登录密码不匹配");
                return map;
            }
            // 根据应用ID进行删除操作
            Map<String, String> map = new HashMap<>();
            try {
				appDao.deleteApp(appPara);
			} catch (Exception e) {
				e.printStackTrace();
			}
            map.put("code", "4000");
            map.put("msg", "成功");
            appCache.delAppInfo(info.getAppid());
            return map;
        } catch (Exception exception) {
        	exception.printStackTrace();
            Map<String, String> map = new HashMap<>();
            map.put("code", "4006");
            map.put("msg", "删除应用出错");
            return map;
        }
    }

    @Override
    public Map<String, Object> updateEnvironment(String environment, String appId, Integer userId) {
        AppPara appPara = new AppPara();

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
            Map<String, Object> map = new HashMap<>();
            map.put("code", 10020);
            map.put("msg", "帐号认证审核中");
            return map;
        }
        Integer integer = appDao.updateEnvironment(appPara);
        if (integer == null) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 4006);
            map.put("msg", "申请失败");
            return map;
        } else if (integer > 0) {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 200);
            map.put("msg", "成功");
          //更新缓存
            appCache.refreshAppinfo(appId);
            return map;
        } else {
            Map<String, Object> map = new HashMap<>();
            map.put("code", 100);
            map.put("msg", "未知错误");
            return map;
        }
    }

    @Override
    public Map<String, String> avatarApp(AppPara appPara) {
        try {
            appDao.avatarAppByAppId(appPara);
            Map<String, String> map = new HashMap<>();
            map.put("code", "200");
            map.put("msg", "成功");
            appCache.refreshAppinfo(appPara.getAppId());
            return map;
        } catch (Exception e) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "4006");
            map.put("msg", "出错");
            return map;
        }

    }

	
	public String generateUseId(int length) {
		List<String> ids=appDao.getAllUseid();
		List<Map<String,Integer>> useids=new ArrayList<>();
		int max=0;
		if(ids!=null&&!ids.isEmpty()){
			for(String e:ids){
				try {
					Map<String, Integer> m=new HashMap<>();
					m.put("start", Integer.valueOf(e.split("-")[0]));
					m.put("end", Integer.valueOf(e.split("-")[1]));
					max=Integer.valueOf(e.split("-")[1])>max?Integer.valueOf(e.split("-")[1]):max;
					useids.add(m);
				} catch (NumberFormatException e1) {
					
				}
			}
		}
		
		return (max+1)+"-"+(max+length);
	}
	
	public Map<String,Object> changeEnvironment(String appid){
		AppPara para=new AppPara();
		para.setAppId(appid);
		para.setEnvironment(2);
		Map<String,Object> map=new HashMap<>();
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
