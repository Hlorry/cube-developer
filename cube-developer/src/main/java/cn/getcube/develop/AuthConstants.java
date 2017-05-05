package cn.getcube.develop;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface AuthConstants {

    //redis key 失效时间
    int AUTH_TOKEN_FAIL_TIME = 60 * 30;

    //密码MD5加密通配字符串
    String USER_SALT = "user_password";

    //邮箱格式错误
    String FORMAT_ERROR = "Email format error";

    //手机格式错误
    String PHONE_FORMAT_ERROR = "phone format error";

    //注册失败
    String REGISTER_ERROR = "register failed";

    //手机已被注册
    String PHONE_EXISTS = "this phone has been registered";

    //邮箱已被注册
    String EMAIL_EXISTS = "this email has been registered";

    //用户名不能为空
    String NULL_NAME = "the userName is can not null";

    //密码不能为空
    String NULL_PASSWORD = "the password is can not null";

    //账户或者密码错误
    String USER_PSD_ERROR = "userName or password error";

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
