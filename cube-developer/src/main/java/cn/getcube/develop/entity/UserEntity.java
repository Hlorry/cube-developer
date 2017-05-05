package cn.getcube.develop.entity;

import java.util.Date;

/**
 * Created by SubDong on 2016/3/9.
 */
public class UserEntity {

    private Integer id;

    /**
     * 个人、企业、组织名
     */
    private String name;

    /**
     * 用户密码
     */
    private String password;

    /**
     * 用户邮箱
     */
    private String email;

    /**
     * 用户手机号
     */
    private String phone;

    /**
     * 用户类型
     */
    private Integer usertype;

    /**
     * 了解途径
     */
    private Integer way = 1;

    /**
     * 创建时间
     */
    private Date create_time;

    /**
     * 修改时间
     */
    private Date update_time;

    /**
     * 账号是否激活（0.未激活  1.激活）
     */
    private Integer activation = 0;

    /**
     * 企业认证
     * （0.未认证 1.已认证）
     */
    private Integer biz_verify = 0;

    /**
     * 手机号认证
     */
    private Integer phone_verify = 0;

    /**
     *
     **/
    private String avatar;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
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

    public Integer getUsertype() {
        return usertype;
    }

    public void setUsertype(Integer usertype) {
        this.usertype = usertype;
    }

    public Integer getWay() {
        return way;
    }

    public void setWay(Integer way) {
        this.way = way;
    }

    public Date getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Date create_time) {
        this.create_time = create_time;
    }

    public Date getUpdate_time() {
        return update_time;
    }

    public void setUpdate_time(Date update_time) {
        this.update_time = update_time;
    }

    public Integer getActivation() {
        return activation;
    }

    public void setActivation(Integer activation) {
        this.activation = activation;
    }

    public Integer getBiz_verify() {
        return biz_verify;
    }

    public void setBiz_verify(Integer biz_verify) {
        this.biz_verify = biz_verify;
    }

    public Integer getPhone_verify() {
        return phone_verify;
    }

    public void setPhone_verify(Integer phone_verify) {
        this.phone_verify = phone_verify;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", password='" + password + '\'' +
                ", email='" + email + '\'' +
                ", phone='" + phone + '\'' +
                ", usertype=" + usertype +
                ", way=" + way +
                ", create_time=" + create_time +
                ", update_time=" + update_time +
                ", activation=" + activation +
                ", biz_verify=" + biz_verify +
                ", phone_verify=" + phone_verify +
                ", avatar='" + avatar + '\'' +
                '}';
    }
}
