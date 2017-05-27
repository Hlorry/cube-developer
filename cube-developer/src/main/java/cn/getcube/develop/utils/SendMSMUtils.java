package cn.getcube.develop.utils;

import cn.getcube.develop.utils.redis.RedisKey;
import cube.sms.common.Result;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

import static cn.getcube.develop.utils.SMSUtil.sned;

/**
 * Created by Administrator on 2016/4/14.
 */
public class SendMSMUtils {
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

    /**
     * http Post 通用方法 Json传参
     *
     * @param message
     * @return
     */
    public static int postRequest(String phone, String message,int type) {
        try {
            //验证码
            String random = EmailUtils.getStringRandom(6);
            int type_sms = type==1?1:type==4?2:3;
            String key="";
            Result result = SMSUtil.sned(random,"",phone,type_sms);
            if(result.getStateCode().getCode()==200){
                switch (type){
                    case 1:
                        key = RedisKey.SMS_REG;
                        break;
                    case 2:
                        key = RedisKey.SMS_BIND;
                        break;
                    case 3:
                        key = RedisKey.SMS_FIX;
                        break;
                    case 4:
                        key = RedisKey.SMS_RESET;
                        break;
                    case 5:
                        key = RedisKey.SMS_UNBIND;
                        break;
                }
                jc.set(key+phone, random);
                jc.expire(key+phone, 5 * 60);
                return 200;
            }else {
                return result.getStateCode().getCode();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return -1;
        }
    }
}