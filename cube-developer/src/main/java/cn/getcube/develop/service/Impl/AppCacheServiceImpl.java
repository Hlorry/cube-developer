package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.AppDao;
import cn.getcube.develop.para.AppInfo;
import cn.getcube.develop.service.AppCacheService;
import cn.getcube.develop.utils.redis.RedisKey;
import com.alibaba.fastjson.JSON;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;

@Component
public class AppCacheServiceImpl implements AppCacheService {

	/**
	 * 更新app的缓存
	 */
	@Autowired
	private AppDao appDao;

	@Resource
	private JedisCluster jedisCluster;
	
	@Override
	public void refreshAppinfo(String appid) {
		AppInfo info = appDao.getAppinfoByAppid(appid);
		if (info != null) {
			jedisCluster.set(RedisKey.APP_PREFIX + info.getId(), JSON.toJSONString(info));
			jedisCluster.set(RedisKey.APPID_PREFIX + info.getAppid(), info.getId() + "");
			jedisCluster.set(RedisKey.APPKEY_PREFIX + info.getAppKey(), info.getId() + "");
			jedisCluster.set(RedisKey.APPID_PREFIX + info.getTest_appid(), info.getId() + "");
			jedisCluster.set(RedisKey.APPKEY_PREFIX + info.getTest_appkey(), info.getId() + "");
		}
	}

	@Override
	public void delAppInfo(String appid) {
		AppInfo info =getAppInfoByAppId(appid);
		if (info != null) {
			jedisCluster.del(RedisKey.APP_PREFIX + info.getId());
			jedisCluster.del(RedisKey.APPID_PREFIX + info.getAppid());
			jedisCluster.del(RedisKey.APPKEY_PREFIX + info.getAppKey());
			jedisCluster.del(RedisKey.APPTESTID_PREFIX+info.getTest_appid());
			jedisCluster.del(RedisKey.APPTESTKEY_PREFIX+info.getTest_appkey());
		}
	}
	public AppInfo getAppInfoByAppId(String appid){
		String id=jedisCluster.get(RedisKey.APPID_PREFIX+appid);
		if(id==null||id.equals("")){
			id=jedisCluster.get(RedisKey.APPTESTID_PREFIX+appid);
		}
		if(id==null||id.equals("")){
			return null;
		}
		return JSON.parseObject(jedisCluster.get(RedisKey.APP_PREFIX+id), AppInfo.class);
	}
	public AppInfo getAppInfoById(String id){
		return JSON.parseObject(jedisCluster.get(RedisKey.APP_PREFIX+id), AppInfo.class);
	}
}
