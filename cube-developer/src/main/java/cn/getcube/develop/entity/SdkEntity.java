package cn.getcube.develop.entity;

import java.util.Date;

/**
 * Created by zjx on 2017/5/6.
 */
public class SdkEntity {

    private Integer switchs;

    private Integer id;

    private String type;

    private String version;

    private Date sdktime;

    private Date createtime;

    private String showapp;

    private String doc;

    private String showdoc;

    public Integer getSwitchs() {
        return switchs;
    }

    public void setSwitchs(Integer switchs) {
        this.switchs = switchs;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVersion() {
        return version;
    }

    public void setVersion(String version) {
        this.version = version;
    }

    public Date getSdktime() {
        return sdktime;
    }

    public void setSdktime(Date sdktime) {
        this.sdktime = sdktime;
    }

    public Date getCreatetime() {
        return createtime;
    }

    public void setCreatetime(Date createtime) {
        this.createtime = createtime;
    }

    public String getShowapp() {
        return showapp;
    }

    public void setShowapp(String showapp) {
        this.showapp = showapp;
    }

    public String getDoc() {
        return doc;
    }

    public void setDoc(String doc) {
        this.doc = doc;
    }

    public String getShowdoc() {
        return showdoc;
    }

    public void setShowdoc(String showdoc) {
        this.showdoc = showdoc;
    }
}
