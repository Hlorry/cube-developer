package cn.getcube.develop.service;

import cn.getcube.develop.para.AppInfo;

public interface AppCacheService {

	public void refreshAppinfo(String appid);
	
	public void delAppInfo(String appid);

	public AppInfo getAppInfoByAppId(String appid);
	
	public AppInfo getAppInfoById(String id);
}
