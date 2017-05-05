package cn.getcube.develop.utils;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import com.alibaba.fastjson.JSONObject;

import java.io.Serializable;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
public class BaseResult implements Serializable {
    private static final long serialVersionUID = 1L;

    protected int code;

    // 返回消息，成功为“success”，失败为具体失败信息
    protected String desc;

    public BaseResult() {
        this.code = StateCode.Ok.getCode();
        this.desc = "OK";
    }

    public BaseResult(String desc) {
        this.code = StateCode.Ok.getCode();
        this.desc = desc;
    }

    public BaseResult(StateCode stateCode, String desc) {
        this.code = stateCode.getCode();
        this.desc = desc;
    }

    public BaseResult(int stateCode, String desc) {
        this.code = stateCode;
        this.desc = desc;
    }

    /**
     * 构建成功结果对象
     */
    public static BaseResult buildOKResult() {
        return new BaseResult(StateCode.Ok, AuthConstants.MSG_OK);
    }

    public static BaseResult build(StateCode stateCode, String desc) {
        return new BaseResult(stateCode, desc);
    }

    public static BaseResult build(int stateCode, String desc) {
        return new BaseResult(stateCode, desc);
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }

    public boolean isOK() {
        return (this.code == StateCode.Ok.getCode());
    }

    public JSONObject toJSON() {
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("code", this.code);
        jsonObject.put("desc", this.desc);
        return jsonObject;
    }
}
