package cn.getcube.develop.service;

import cn.getcube.develop.entity.UserEntity;

import java.util.List;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface UserService {

    /**
     * 添加（注册）用户
     *
     * @param userEntity
     *                   uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
     */
    void addUser(UserEntity userEntity);

    /**
     * 多条件查询用户  可通过id 或 name 或 phone 或 email 查询用户信息
     *
     * @param userEntity
     * @return
     */
    List<UserEntity> queryUsers(UserEntity userEntity);

    /**
     * 返回单个用户信息
     * @param userEntity
     * @return
     */
    UserEntity queryUser(UserEntity userEntity);

    int queryExists(UserEntity userEntity);

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

    /**
     * 修改邮箱
     *
     * @param userEntity
     */
    int fixEmail(UserEntity userEntity);


    /**
     * 修改手机号
     *
     * @param userEntity
     */
    int fixPhone(UserEntity userEntity);

}
