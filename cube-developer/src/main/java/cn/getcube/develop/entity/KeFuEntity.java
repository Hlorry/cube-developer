package cn.getcube.develop.entity;

import java.util.Date;

/**
 * Created by Administrator on 2017/5/26.
 */
public class KeFuEntity {

    private int id;

    private String cube;

    private String name;

    private String email;

    private String phone;

    private String avatar;

    private Date createTime;

    private Date updateTime;

    public KeFuEntity() {
    }

    public KeFuEntity(String cube, String name, String email, String phone, String avatar, Date createTime, Date updateTime) {
        this.cube = cube;
        this.name = name;
        this.email = email;
        this.phone = phone;
        this.avatar = avatar;
        this.createTime = createTime;
        this.updateTime = updateTime;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCube() {
        return cube;
    }

    public void setCube(String cube) {
        this.cube = cube;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public Date getUpdateTime() {
        return updateTime;
    }

    public void setUpdateTime(Date updateTime) {
        this.updateTime = updateTime;
    }
}
