package cn.getcube.develop.entity;

import com.alibaba.fastjson.JSONObject;
import com.sun.org.apache.regexp.internal.RE;

/**
 * Created by Administrator on 2016/3/14.
 */
public class CertifiedEntity {
    /**
     * 数据库ID
     */
    private int id;

    /**
     * 公司名称
     */
    private String companyName;

    /**
     * 公司地址
     */
    private String companyAddress;

    /**
     * 营业执照号
     */
    private String licenseNum;

    /**
     * 营业执照（图片地址）
     */
    private String licenseImg;

    /**
     * 组织机构号
     */
    private String agencyNum;

    /**
     * 组织机构证
     */
    private String agencyImg;

    /**
     * 税务登记号
     */
    private String taxNum;

    /**
     * 税务登记证
     */
    private String taxImg;

    /**
     * 法定代表人
     */
    private String corporate;

    /**
     * 公司电话
     */
    private String companyPhone;

    /**
     * 公司网站
     */
    private String companyWebsite;

    /**
     * 用户ID
     */
    private int userId;

/******************************个人信息**********************************/
    /**
     * 真实姓名
     */
    private String plName;

    /**
     * 证件类型 1.身份证 2护照
     */
    private Integer plType;

    /**
     * 证件号
     */
    private String plCardNum;

    /**
     * 身份证正面照片
     */
    private String plPositiveImg;

    /**
     * 身份证反面照片
     */
    private String plSideImg;

    /**
     * 手持证件照片
     */
    private String plHidnumber;

    /**
     * 护照照片
     */
    private String passport;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getCompanyName() {
        return companyName;
    }

    public void setCompanyName(String companyName) {
        this.companyName = companyName;
    }

    public String getCompanyAddress() {
        return companyAddress;
    }

    public void setCompanyAddress(String companyAddress) {
        this.companyAddress = companyAddress;
    }

    public String getLicenseNum() {
        return licenseNum;
    }

    public void setLicenseNum(String licenseNum) {
        this.licenseNum = licenseNum;
    }

    public String getLicenseImg() {
        return licenseImg;
    }

    public void setLicenseImg(String licenseImg) {
        this.licenseImg = licenseImg;
    }

    public String getAgencyNum() {
        return agencyNum;
    }

    public void setAgencyNum(String agencyNum) {
        this.agencyNum = agencyNum;
    }

    public String getAgencyImg() {
        return agencyImg;
    }

    public void setAgencyImg(String agencyImg) {
        this.agencyImg = agencyImg;
    }

    public String getTaxNum() {
        return taxNum;
    }

    public void setTaxNum(String taxNum) {
        this.taxNum = taxNum;
    }

    public String getTaxImg() {
        return taxImg;
    }

    public void setTaxImg(String taxImg) {
        this.taxImg = taxImg;
    }

    public String getCorporate() {
        return corporate;
    }

    public void setCorporate(String corporate) {
        this.corporate = corporate;
    }

    public String getCompanyPhone() {
        return companyPhone;
    }

    public void setCompanyPhone(String companyPhone) {
        this.companyPhone = companyPhone;
    }

    public String getCompanyWebsite() {
        return companyWebsite;
    }

    public void setCompanyWebsite(String companyWebsite) {
        this.companyWebsite = companyWebsite;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getPlName() {
        return plName;
    }

    public void setPlName(String plName) {
        this.plName = plName;
    }

    public Integer getPlType() {
        return plType;
    }

    public void setPlType(Integer plType) {
        this.plType = plType;
    }

    public String getPlCardNum() {
        return plCardNum;
    }

    public void setPlCardNum(String plCardNum) {
        this.plCardNum = plCardNum;
    }

    public String getPlPositiveImg() {
        return plPositiveImg;
    }

    public void setPlPositiveImg(String plPositiveImg) {
        this.plPositiveImg = plPositiveImg;
    }

    public String getPlSideImg() {
        return plSideImg;
    }

    public void setPlSideImg(String plSideImg) {
        this.plSideImg = plSideImg;
    }

    public String getPlHidnumber() {
        return plHidnumber;
    }

    public void setPlHidnumber(String plHidnumber) {
        this.plHidnumber = plHidnumber;
    }

    public String getPassport() {
        return passport;
    }

    public void setPassport(String passport) {
        this.passport = passport;
    }

    public JSONObject toJsonEnterprise(){
        JSONObject json = new JSONObject();
        json.put("id",this.id);
        json.put("companyName",this.companyName);
        json.put("companyAddress",this.companyAddress);
        json.put("licenseNum",this.licenseNum);
        json.put("licenseImg",this.licenseImg);
        json.put("agencyNum",this.agencyNum);
        json.put("agencyImg",this.agencyImg);
        json.put("taxNum",this.taxNum);
        json.put("taxImg",this.taxImg);
        json.put("corporate",this.corporate);
        json.put("companyPhone",this.companyPhone);
        json.put("companyWebsite",this.companyWebsite);
        json.put("userId",this.userId);
        return json;
    }

    public JSONObject toJsonPersonal(){
        JSONObject json = new JSONObject();
        json.put("id",this.id);
        json.put("plName",this.plName);
        json.put("plType",this.plType);
        json.put("plCardNum",this.plCardNum);
        json.put("plPositiveImg",this.plPositiveImg);
        json.put("plSideImg",this.plSideImg);
        json.put("plHidnumber",this.plHidnumber);
        json.put("passport",this.passport);
        return json;
    }

    @Override
    public String toString() {
        return "CertifiedEntity{" +
                "id=" + id +
                ", companyName='" + companyName + '\'' +
                ", companyAddress='" + companyAddress + '\'' +
                ", licenseNum='" + licenseNum + '\'' +
                ", licenseImg='" + licenseImg + '\'' +
                ", agencyNum='" + agencyNum + '\'' +
                ", agencyImg='" + agencyImg + '\'' +
                ", taxNum='" + taxNum + '\'' +
                ", taxImg='" + taxImg + '\'' +
                ", corporate='" + corporate + '\'' +
                ", companyPhone='" + companyPhone + '\'' +
                ", companyWebsite='" + companyWebsite + '\'' +
                ", userId=" + userId +
                ", plName='" + plName + '\'' +
                ", plType='" + plType + '\'' +
                ", plCardNum='" + plCardNum + '\'' +
                ", plPositiveImg='" + plPositiveImg + '\'' +
                ", plSideImg='" + plSideImg + '\'' +
                ", plHidnumber='" + plHidnumber + '\'' +
                ", passport='" + passport + '\'' +
                '}';
    }
}
