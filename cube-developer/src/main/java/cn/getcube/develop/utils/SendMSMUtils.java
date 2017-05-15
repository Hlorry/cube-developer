package cn.getcube.develop.utils;

import cn.getcube.develop.utils.redis.RedisKey;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.io.InputStreamReader;
import java.util.*;

/**
 * Created by Administrator on 2016/4/14.
 */
public class SendMSMUtils {
    static JedisCluster jc;

    private static final String CHARSET_UTF_8 = "UTF-8";
    private final HttpClientConnectionManager connManager = new PoolingHttpClientConnectionManager();

    private static Logger log = LoggerFactory.getLogger(HttpClientUtils.class);

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

    private static class LazyHolder {
        private static final HttpClientUtils INSTANCE = new HttpClientUtils();
    }

    private static HttpClientUtils getInstance() {
        return LazyHolder.INSTANCE;
    }


    private HttpClientConnectionManager getConnManager() {
        return connManager;
    }


    /**
     * http Post 通用方法 Json传参
     *
     * @param message
     * @return
     */
    public static int postRequest(String phone, String message,int type) {
        String url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend";

        try {

            //验证码
            String random = EmailUtils.getStringRandom(6);

            String Content;
            if (message != null && !message.equals("")) {
                Content = java.net.URLEncoder.encode(message + "【时信互联】", "UTF-8");
            } else {
                Content = java.net.URLEncoder.encode(random + "  (验证码)，此验证码仅限于该手机验证；请在5分钟内完成验证，时信将持续为你服务。【时信互联】", "UTF-8");
            }
            Properties props = new Properties();
            props.load(new InputStreamReader(SendMSMUtils.class.getClassLoader().getResourceAsStream("SMS.properties"), CHARSET_UTF_8));

            Map<String, Object> map = new HashMap<>();
            map.put("UserID", props.getProperty("sms.id"));
            map.put("Account", props.getProperty("sms.account"));
            map.put("Password", props.getProperty("sms.password"));
            map.put("SendType", props.getProperty("sms.sendType"));
            map.put("PostFixNumber", props.getProperty("sms.PostFixNumber"));
            map.put("SendTime", "");
            map.put("Phones", phone);
            map.put("Content", Content);

            String request = null;
            for (int i = 0; i < 3; i++) {
                request = HttpClientUtils.postRequest(url, map);
                if(request != null){
                    break;
                }
            }
            if (request == null) {
                return -1;
            }

            int beginPoint = request.indexOf("<RetCode>");
            int endPoint = request.indexOf("</RetCode>");

            String substring = request.substring(beginPoint + 9, endPoint);
            if (substring.equals("Sucess")) {
                String key="";
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
            } else {
                return 501;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return -1;
        }
    }
}