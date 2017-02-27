package cn.getcube.develop.para;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/11.
 */
public class AppPara {

    private int id;
    
    private String appId;

    private int userId;

    private String appName;

    private String appStage;

    private String category;

    private String appUserLevel;

    private String description;

    private String test_appid;
    private String test_appkey;
    private String test_useid;
    private Date createTime = new Date();

    private Date modifyTime = new Date();

    private String clientId = "1236545645213213123";

    private String clientSecret = "21654213123132131";

    private String length;

    private String width;

    private String appKey;

    private int checkType;

    private String avatar;

    /**
     * 有效期开始时间
     */
    private Date validityStart;

    /**
     * 有效期结束时间
     */
    private Date validityEnd;

    /**
     * 启用服务
     */
    private String useServing;

    /**
     * 使用ID（号码段）
     */
    private String useId;

    /**
     * 选择环境管理
     */
    private int environment;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getAppStage() {
        return appStage;
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

	public void setAppStage(String appStage) {
        this.appStage = appStage;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getAppUserLevel() {
        return appUserLevel;
    }

    public void setAppUserLevel(String appUserLevel) {
        this.appUserLevel = appUserLevel;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getModifyTime() {
        return modifyTime;
    }

    public void setModifyTime(Date modifyTime) {
        this.modifyTime = modifyTime;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }

    public String getLength() {
        return length;
    }

    public void setLength(String length) {
        this.length = length;
    }

    public String getWidth() {
        return width;
    }

    public void setWidth(String width) {
        this.width = width;
    }

    public String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        this.appKey = appKey;
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

    public Date getValidityStart() {
        return validityStart;
    }

    public void setValidityStart(Date validityStart) {
        this.validityStart = validityStart;
    }

    public Date getValidityEnd() {
        return validityEnd;
    }

    public void setValidityEnd(Date validityEnd) {
        this.validityEnd = validityEnd;
    }

    public String getUseServing() {
        return useServing;
    }

    public void setUseServing(String useServing) {
        this.useServing = useServing;
    }

    public String getUseId() {
        return useId;
    }

    public void setUseId(String useId) {
        this.useId = useId;
    }

    public int getEnvironment() {
        return environment;
    }

    public void setEnvironment(int environment) {
        this.environment = environment;
    }
}
