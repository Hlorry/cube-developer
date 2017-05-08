package cn.getcube.develop;

/**
 * Created by Rainbow
 *  状态码
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
public enum StateCode {
    Ok(200),
    AUTH_ERROR_100(100),
    ParamMiss(700),                     //参数缺失
    TokenFailed(800),                   //token失效

    AUTH_ERROR_9999(9999),              //短信发送失败
    AUTH_ERROR_10000(10000),            //无使用权限
    AUTH_ERROR_10001(10001),            //注册失败
    AUTH_ERROR_10002(10002),            //账号或密码错误
    AUTH_ERROR_10003(10003),            //账号密码修改失败
    AUTH_ERROR_10004(10004),            //邮箱格式错误
    AUTH_ERROR_10005(10005),            //邮件发送失败
    AUTH_ERROR_10006(10006),            //图片上传失败
    AUTH_ERROR_10007(10007),            //认证信息保存失败
    AUTH_ERROR_10008(10008),            //查无此信息
    AUTH_ERROR_10009(10009),            //创建失败
    AUTH_ERROR_10010(10010),            //该账户不属于企业或非盈利组织用户
    AUTH_ERROR_10011(10011),            //删除失败
    AUTH_ERROR_10012(10012),            //验证过期
    AUTH_ERROR_10013(10013),            //消息发送过快
    AUTH_ERROR_10014(10014),            //未登陆账号
    AUTH_ERROR_10015(10015),            //Token无效
    AUTH_ERROR_10016(10016),            //参数缺失
    AUTH_ERROR_10017(10017),            //信息修改失败
    AUTH_ERROR_10018(10018),            //信息验证失败
    AUTH_ERROR_10019(10019),            //信息已存在
    AUTH_ERROR_10020(10020),            //账户信息认证未通过
    AUTH_ERROR_10021(10021),           //用户不存在
    AUTH_ERROR_10022(10022),            //手机格式错误
    AUTH_ERROR_10023(10023),            //邮箱已被注册
    AUTH_ERROR_10024(10024),            //手机已被注册

    Unknown(20001),                     //未知错误
    APP_NAME_EXIST(10088),              //应用名称已存在
    APP_CREATE_ERROR(10089),            //创建应用出错
    APP_MODIFY_ERROR(10090),            //修改应用信息出错
    APP_QUERY_PARAM_ERROR(10091),       //查询参数错误

    APP_UPLOAD_AVATAR_ERROR(10092),     //删除应用图标出错
    APP_UPDATE_ENVIRONMENT_ERROR(10093),      //环境更新失败
    APP_NAME_NOT_MATCH(10094),          //应用名称不匹配
    APP_LOGIN_PWD_NOT_MATCH(10095),    //登陆密码不匹配









    AUTH_ERROR_20000(20000);            //结尾，以后删除


    private int code;

    StateCode(int code) {
        this.code = code;
    }

    public int getCode() {
        return this.code;
    }
}
