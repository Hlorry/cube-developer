package cn.getcube.develop.utils.redis;

import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import com.alibaba.fastjson.JSON;
import redis.clients.jedis.JedisCluster;

/**
 * Created by HL on 2017/5/17.
 */
public class UpdateUserRedis {

    public static void updateUser(JedisCluster jedisCluster, int userId, String token, UserDao userDao){
        UserEntity para = new UserEntity();
        para.setId(userId);
        UserEntity db = userDao.queryUser(para);
        jedisCluster.set(token, JSON.toJSONString(db));
    }
}
