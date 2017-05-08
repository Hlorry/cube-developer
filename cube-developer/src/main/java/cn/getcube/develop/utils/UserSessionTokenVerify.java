package cn.getcube.develop.utils;

import cn.getcube.develop.entity.UserEntity;
import com.alibaba.fastjson.JSONObject;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.util.Date;
import java.util.HashSet;
import java.util.Objects;
import java.util.Set;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
public class UserSessionTokenVerify {

    static JedisCluster jc;

    static {
        Set<HostAndPort> jedisClusterNodes = new HashSet<>();
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7000));
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7001));
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7002));
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7003));
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7004));
        jedisClusterNodes.add(new HostAndPort("125.208.1.67", 7005));
        jc = new JedisCluster(jedisClusterNodes);
    }

    public static UserEntity get(String Token) {
        String userString = jc.get(Token);
        if (Objects.nonNull(userString)) {
            JSONObject jsonObject = JSONObject.parseObject(userString);
            UserEntity userEntity = new UserEntity();
            userEntity.setId(jsonObject.get("id") != null ? (Integer) jsonObject.get("id") : null);
            userEntity.setPhone(jsonObject.get("phone") != null ? (String)  jsonObject.get("phone") : null);
            userEntity.setEmail(jsonObject.get("email") != null ?(String) jsonObject.get("email") : null);
            userEntity.setActivation(jsonObject.get("activation") != null ?(Integer) jsonObject.get("activation") : null);
            userEntity.setAvatar(jsonObject.get("avatar") != null ?(String) jsonObject.get("avatar") : null);
            userEntity.setBiz_verify(jsonObject.get("biz_verify") != null ?(Integer) jsonObject.get("biz_verify") : null);
            userEntity.setName(jsonObject.get("name") != null ?(String) jsonObject.get("name") : null);
            userEntity.setUsertype(jsonObject.get("usertype") != null ?(Integer) jsonObject.get("usertype") : null);
            userEntity.setWay(jsonObject.get("way") != null ?(Integer) jsonObject.get("way") : null);
            return userEntity;
        }
        return null;
    }
}
