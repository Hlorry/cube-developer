package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.UserEntity;

/**
 * Created by SubDong on 2016/3/8.
 */
public interface UserDao {

    /**
     * 添加（注册）用户
     * @param userEntity
     */
    void addUser(UserEntity userEntity);

    /**
     * 多条件查询用户  可通过id 或 name 或 phone 或 email 查询用户信息
     * @param userEntity
     * @return
     */
    UserEntity queryUser(UserEntity userEntity);

    /**
     * 更新用户信息
     * @param userEntity
     */
    int updateUser(UserEntity userEntity);

    UserEntity login(UserEntity userEntity);
}
