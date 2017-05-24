package cn.getcube.develop;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface AuthConstants {

    //圖片臨時存放目錄
    String AUTH_FILE_PATH = "/home/cube/cube-developer/conf/static";

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

    String ACTIVATION_FAILED = "该账号没有激活，请激活后再登录！";

    //数据修改失败
    String UPDATE_ERROR = "data update failed";

    //手机绑定失败
    String PHONE_BINDING_ERROR = "phone binding error";

    //手机绑定失败
    String PHONE_UNBINDING_ERROR = "phone unbinding error";

    //验证失败
    String VERIFY_FAILED = "verify failed,please again write verify information";

    //验证过期
    String VERIFY_EXPIRE = "verify has been expired";

    //头像上次失败
    String FACE_UPLOAD = "face upload failed";

    //注销失败
    String LOGOUT_ERROR = "logout error";

    //查无此信息
    String QUERY_NO_DATA = "no data";

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
