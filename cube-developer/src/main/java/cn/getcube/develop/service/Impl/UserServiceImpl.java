package cn.getcube.develop.service.Impl;

import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.UserService;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.List;


/**
 * Created by SubDong on 2016/3/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    JedisCluster jc;

    @Override
    public void addUser(UserEntity userEntity) {
        userDao.addUser(userEntity);
    }


    @Override
    public List<UserEntity> queryUsers(UserEntity userEntity) {
        return userDao.queryUsers(userEntity);
    }

    @Override
    public UserEntity queryUser(UserEntity userEntity) {
        return userDao.queryUser(userEntity);
    }

    @Override
    public int queryExists(UserEntity userEntity) {
        return userDao.queryExists(userEntity);
    }

    @Override
    public int updateUser(UserEntity userEntity) {
        int updateUser = userDao.updateUser(userEntity);
        return updateUser;
    }

    @Override
    public UserEntity login(UserEntity userEntity) {
        return userDao.login(userEntity);
    }

    @Override
    public int fixEmail(UserEntity userEntity) {
        return userDao.fixEmail(userEntity);
    }

    @Override
    public int fixPhone(UserEntity userEntity) {
        return userDao.fixPhone(userEntity);
    }
}
