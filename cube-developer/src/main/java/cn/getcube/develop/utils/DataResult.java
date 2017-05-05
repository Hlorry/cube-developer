package cn.getcube.develop.utils;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
public class DataResult<T> extends BaseResult {
    private T data;

    public DataResult() {
        this(StateCode.Ok.getCode(), AuthConstants.MSG_OK);
    }

    public DataResult(String desc) {
        this(StateCode.Ok.getCode(), desc);
    }

    public DataResult(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    public DataResult(StateCode stateCode, String desc) {
        this(stateCode.getCode(), desc);
    }

    public DataResult(StateCode stateCode, String desc, T data) {
        this.code = stateCode.getCode();
        this.desc = desc;
        this.data = data;
    }

    public static DataResult build(StateCode stateCode, String desc) {
        return new DataResult(stateCode, desc);
    }

    public T getData() {
        return data;
    }

    public DataResult<T> setData(T data) {
        this.data = data;
        return this;
    }

}
