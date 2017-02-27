package cn.getcube.develop.para;

import java.util.List;


public class AppInfo {
	private int id;
	private String appid;
	private String appName;
	private int category;
	private String description;
	private int appStage;
	private int appUserLevel;
	private String createTime;
	private String modifyTime;
	private int thumb_size_length;
	private int thumb_size_width;
	private String appKey;
	private String clientid;
	private String clientSecret;
	private String userid;
	private int checkType;
	private String avatar;
	private String validityStart;
	private String validityEnd;
	private int environment;
	private String useId;//cube号码段
	private List<Integer> nodes;//app节点信息
	private String test_appid;
	private String test_appkey;
	private String test_useid;
	private List<Integer> testnodes;
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getAppid() {
		return appid;
	}
	public void setAppid(String appid) {
		this.appid = appid;
	}
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public int getCategory() {
		return category;
	}
	public void setCategory(int category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getAppStage() {
		return appStage;
	}
	public void setAppStage(int appStage) {
		this.appStage = appStage;
	}
	public int getAppUserLevel() {
		return appUserLevel;
	}
	public void setAppUserLevel(int appUserLevel) {
		this.appUserLevel = appUserLevel;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getModifyTime() {
		return modifyTime;
	}
	public void setModifyTime(String modifyTime) {
		this.modifyTime = modifyTime;
	}
	public int getThumb_size_length() {
		return thumb_size_length;
	}
	public void setThumb_size_length(int thumb_size_length) {
		this.thumb_size_length = thumb_size_length;
	}
	public int getThumb_size_width() {
		return thumb_size_width;
	}
	public void setThumb_size_width(int thumb_size_width) {
		this.thumb_size_width = thumb_size_width;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
	}
	public String getClientid() {
		return clientid;
	}
	public void setClientid(String clientid) {
		this.clientid = clientid;
	}
	public String getClientSecret() {
		return clientSecret;
	}
	public void setClientSecret(String clientSecret) {
		this.clientSecret = clientSecret;
	}
	public String getUserid() {
		return userid;
	}
	public void setUserid(String userid) {
		this.userid = userid;
	}
	public int getCheckType() {
		return checkType;
	}
	public void setCheckType(int checkType) {
		this.checkType = checkType;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public String getValidityStart() {
		return validityStart;
	}
	public void setValidityStart(String validityStart) {
		this.validityStart = validityStart;
	}
	public String getValidityEnd() {
		return validityEnd;
	}
	public void setValidityEnd(String validityEnd) {
		this.validityEnd = validityEnd;
	}
	public int getEnvironment() {
		return environment;
	}
	public void setEnvironment(int environment) {
		this.environment = environment;
	}
	public String getUseId() {
		return useId;
	}
	public void setUseId(String useId) {
		this.useId = useId;
	}
	public List<Integer> getNodes() {
		return nodes;
	}
	public void setNodes(List<Integer> nodes) {
		this.nodes = nodes;
	}
	public String getTest_appid() {
		return test_appid;
	}
	public void setTest_appid(String test_appid) {
		this.test_appid = test_appid;
	}
	public String getTest_appkey() {
		return test_appkey;
	}
	public void setTest_appkey(String test_appkey) {
		this.test_appkey = test_appkey;
	}
	public String getTest_useid() {
		return test_useid;
	}
	public void setTest_useid(String test_useid) {
		this.test_useid = test_useid;
	}
	public List<Integer> getTestnodes() {
		return testnodes;
	}
	public void setTestnodes(List<Integer> testnodes) {
		this.testnodes = testnodes;
	}
	public AppInfo(int id, String appid, String appName, int category, String description, int appStage,
			int appUserLevel, String createTime, String modifyTime, int thumb_size_length, int thumb_size_width,
			String appKey, String clientid, String clientSecret, String userid, int checkType, String avatar,
			String validityStart, String validityEnd, int environment, String useId, List<Integer> nodes,
			String test_appid, String test_appkey, String test_useid, List<Integer> testnodes) {
		super();
		this.id = id;
		this.appid = appid;
		this.appName = appName;
		this.category = category;
		this.description = description;
		this.appStage = appStage;
		this.appUserLevel = appUserLevel;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.thumb_size_length = thumb_size_length;
		this.thumb_size_width = thumb_size_width;
		this.appKey = appKey;
		this.clientid = clientid;
		this.clientSecret = clientSecret;
		this.userid = userid;
		this.checkType = checkType;
		this.avatar = avatar;
		this.validityStart = validityStart;
		this.validityEnd = validityEnd;
		this.environment = environment;
		this.useId = useId;
		this.nodes = nodes;
		this.test_appid = test_appid;
		this.test_appkey = test_appkey;
		this.test_useid = test_useid;
		this.testnodes = testnodes;
	}
	public AppInfo() {
		super();
	};
	
	
	
}
