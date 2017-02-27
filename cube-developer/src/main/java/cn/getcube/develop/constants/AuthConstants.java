package cn.getcube.develop.constants;

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
    Integer AUTH_SUCCESS_200 = 200;

    Integer AUTH_ERROR_100 = 100;
    //无使用权限
    Integer AUTH_ERROR_10000 = 10000;
    //注册失败
    Integer AUTH_ERROR_10001 = 10001;
    //账号或密码错误
    Integer AUTH_ERROR_10002 = 10002;
    //账号密码修改失败
    Integer AUTH_ERROR_10003 = 10003;
    //邮箱格式错误
    Integer AUTH_ERROR_10004 = 10004;
    //邮件发送失败
    Integer AUTH_ERROR_10005 = 10005;
    //图片上传失败
    Integer AUTH_ERROR_10006 = 10006;
    //认证信息保存失败
    Integer AUTH_ERROR_10007 = 10007;
    //查无此信息
    Integer AUTH_ERROR_10008 = 10008;
    //创建失败
    Integer AUTH_ERROR_10009 = 10009;
    //该账户不属于企业或非盈利组织用户
    Integer AUTH_ERROR_10010 = 10010;
    //删除失败
    Integer AUTH_ERROR_10011 = 10011;
    //验证过期
    Integer AUTH_ERROR_10012 = 10012;
    //消息发送过快
    Integer AUTH_ERROR_10013 = 10013;
    //未登陆账号
    Integer AUTH_ERROR_10014 = 10014;
    //Token无效
    Integer AUTH_ERROR_10015 = 10015;
    //参数缺失
    Integer AUTH_ERROR_10016 = 10016;
    //信息修改失败
    Integer AUTH_ERROR_10017 = 10017;
    //信息验证失败
    Integer AUTH_ERROR_10018 = 10018;
    //信息已存在
    Integer AUTH_ERROR_10019 = 10019;
    //账户信息认证未通过
    Integer AUTH_ERROR_10020 = 10020;

    String AUTH_STATE = "state";
    String AUTH_SUCCESS = "success";

    String AUTH_ERRCODE = "errcode";
    String AUTH_ERRMSG = "errmsg";
}
