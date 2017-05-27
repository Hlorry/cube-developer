package cn.getcube.develop.entity;

import java.sql.Timestamp;
import java.util.Date;

/**
 * Created by zjx on 2017/5/6.
 */
public class SdkEntity {

    /**
     * id
     */
    private Integer id;

    /**
     * sdk名称
     */
    private String type;

    /**
     * sdk版本号
     */
    private String version;

    /**
     * sdk更新时间
     */
    private String sdktime;

    /**
     * 此条消息创建时间
     */
    private Timestamp createtime;

    /**
     * sdk下载地址
     */
    private String sdk;

    /**
     * 应用演示地址
     */
    private String showapp;

    /**
     * 文档下载地址
     */
    private String doc;

    /**
     * 演示源码下载地址
     */
    private String showcode;

    /**
     * 自述文件
     */
    private String file;

    /**
     * 更新日志
     */
    private String updatelog;

    /**
     * 更新时间
     */
    private Timestamp updatetime;

    /**
     * 是否启用当前配置
     */
    private Integer switchs;

    /**
     * 全部下载连接
     */
    private String downAll;

    public Integer getId() { return id; }

    public void setId(Integer id) { this.id = id; }

    public String getType() { return type; }

    public void setType(String type) { this.type = type; }

    public String getVersion() { return version; }

    public void setVersion(String version) { this.version = version; }

    public String getSdktime() { return sdktime; }

    public void setSdktime(String sdktime) { this.sdktime = sdktime; }

    public Timestamp getCreatetime() { return createtime; }

    public void setCreatetime(Timestamp createtime) { this.createtime = createtime; }

    public String getSdk() { return sdk; }

    public void setSdk(String sdk) { this.sdk = sdk; }

    public String getShowapp() { return showapp; }

    public void setShowapp(String showapp) { this.showapp = showapp; }

    public String getDoc() { return doc; }

    public void setDoc(String doc) { this.doc = doc; }

    public String getShowcode() { return showcode; }

    public void setShowcode(String showcode) { this.showcode = showcode; }

    public String getFile() { return file; }

    public void setFile(String file) { this.file = file; }

    public String getUpdatelog() { return updatelog; }

    public void setUpdatelog(String updatelog) { this.updatelog = updatelog; }

    public Timestamp getUpdatetime() { return updatetime; }

    public void setUpdatetime(Timestamp updatetime) { this.updatetime = updatetime; }

    public Integer getSwitchs() { return switchs; }

    public void setSwitchs(Integer switchs) { this.switchs = switchs; }

    public String getDownAll() { return downAll; }

    public void setDownAll(String downAll) { this.downAll = downAll; }
}
