package cn.getcube.develop.entity;

import java.util.Date;

/**
 * Created by Administrator on 2016/3/11.
 */
public class AppEntity {

    /**
     * 数据库ID
     */
    private int id;

    /**
     * 应用id
     */
    private String appId;

    /**
     * 应用名称
     */
    private String appName;

    /**
     * 应用类型
     */
    private String category;

    /**
     * 应用简介
     */
    private String description;

    /**
     * 运营阶段
     */
    private String appStage;

	/**
	 * 1、线上  2、线下 3、暂停
	 */
	private Integer appState;

    /**
     * 用户量级
     */
    private String appUserLevel;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 最后修改时间
     */
    private Date modifyTime;

    /**
     * 缩略图大小。长
     */
    private String thumbSizeLength;

    /**
     * 缩略图大小。宽
     */
    private String getThumbSizeWidth;

    private String appKey;
    private String clientId;
    private String clientSecret;

    private int userId;

    private String avatar;

	/**
     * @return
     */
    private int checkType = 0;

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

    private String test_appkey;
    private String test_appid;
    private String test_useid;
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
	public String getAppName() {
		return appName;
	}
	public void setAppName(String appName) {
		this.appName = appName;
	}
	public String getCategory() {
		return category;
	}
	public void setCategory(String category) {
		this.category = category;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getAppStage() {
		return appStage;
	}
	public void setAppStage(String appStage) {
		this.appStage = appStage;
	}
	public String getAppUserLevel() {
		return appUserLevel;
	}
	public void setAppUserLevel(String appUserLevel) {
		this.appUserLevel = appUserLevel;
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
	public String getThumbSizeLength() {
		return thumbSizeLength;
	}
	public void setThumbSizeLength(String thumbSizeLength) {
		this.thumbSizeLength = thumbSizeLength;
	}
	public String getGetThumbSizeWidth() {
		return getThumbSizeWidth;
	}
	public void setGetThumbSizeWidth(String getThumbSizeWidth) {
		this.getThumbSizeWidth = getThumbSizeWidth;
	}
	public String getAppKey() {
		return appKey;
	}
	public void setAppKey(String appKey) {
		this.appKey = appKey;
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
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
	}
	public String getAvatar() {
		return avatar;
	}
	public void setAvatar(String avatar) {
		this.avatar = avatar;
	}
	public int getCheckType() {
		return checkType;
	}
	public void setCheckType(int checkType) {
		this.checkType = checkType;
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
	public String getTest_appkey() {
		return test_appkey;
	}
	public void setTest_appkey(String test_appkey) {
		this.test_appkey = test_appkey;
	}
	public String getTest_appid() {
		return test_appid;
	}
	public void setTest_appid(String test_appid) {
		this.test_appid = test_appid;
	}
	public String getTest_useid() {
		return test_useid;
	}
	public void setTest_useid(String test_useid) {
		this.test_useid = test_useid;
	}

	public Integer getAppState() {
		return appState;
	}

	public void setAppState(Integer appState) {
		this.appState = appState;
	}

	public AppEntity(int id, String appId, String appName, String category, String description, String appStage,
					 String appUserLevel, Date createTime, Date modifyTime, String thumbSizeLength, String getThumbSizeWidth,
					 String appKey, String clientId, String clientSecret, int userId, String avatar, int checkType,
					 Date validityStart, Date validityEnd, String useServing, String useId, int environment, String test_appkey,
					 String test_appid, String test_useid, Integer appState) {
		super();
		this.id = id;
		this.appId = appId;
		this.appName = appName;
		this.category = category;
		this.description = description;
		this.appStage = appStage;
		this.appUserLevel = appUserLevel;
		this.createTime = createTime;
		this.modifyTime = modifyTime;
		this.thumbSizeLength = thumbSizeLength;
		this.getThumbSizeWidth = getThumbSizeWidth;
		this.appKey = appKey;
		this.clientId = clientId;
		this.clientSecret = clientSecret;
		this.userId = userId;
		this.avatar = avatar;
		this.checkType = checkType;
		this.validityStart = validityStart;
		this.validityEnd = validityEnd;
		this.useServing = useServing;
		this.useId = useId;
		this.environment = environment;
		this.test_appkey = test_appkey;
		this.test_appid = test_appid;
		this.test_useid = test_useid;
		this.appState = appState;
	}
	public AppEntity() {
		super();
	}
    

}
