package cn.getcube.develop;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface AuthConstants {

    //redis key 失效时间
    int AUTH_TOKEN_FAIL_TIME = 60 * 30;

    //密码MD5加密通配字符串
    String USER_SALT = "user_password";

    /**
     * 返回code信息
     */
    Integer OK = 200;



    /**
     * 状态 标志
     */
    String CODE = "code";
    /**
     * 状态 标志
     */
    String DATA = "data";
    /**
     * 消息描述 标志
     */
    String DESC = "desc";
    /**
     * 成功
     */
    String MSG_OK = "OK";
}
