package cn.getcube.develop.service;

import cn.getcube.develop.entity.UserEntity;

import java.util.Map;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface UserService {

    /**
     * 添加（注册）用户
     *
     * @param userEntity
     * @param uri        邮箱验证时用户访问页面
     *                   uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
     */
    Map<String, Object> addUser(UserEntity userEntity, String uri);

    /**
     * 多条件查询用户  可通过id 或 name 或 phone 或 email 查询用户信息
     *
     * @param userEntity
     * @return
     */
    UserEntity queryUser(UserEntity userEntity);

    /**
     * 更新用户信息
     *
     * @param userEntity
     */
    int updateUser(UserEntity userEntity);

    /**
     *
     */
    UserEntity login(UserEntity userEntity);

}
