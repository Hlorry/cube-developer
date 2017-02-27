package cn.getcube.develop.utils;

import org.apache.http.HttpStatus;
import org.apache.http.NameValuePair;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.JedisCluster;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static java.nio.charset.StandardCharsets.UTF_8;

/**
 * Created by Administrator on 2016/4/14.
 */
public class SendMSMUtils {
    static JedisCluster jc;

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
     * @param params
     * @return
     */
    public static String postRequest(String phone, String params) {
        String url = "http://www.mxtong.net.cn/GateWay/Services.asmx/DirectSend";

        try (CloseableHttpClient httpClient = HttpClients.createDefault()) {
            HttpPost httpPost = new HttpPost(url);
            httpPost.addHeader("Content-Disposition: form-data;name=media", "application/json; charset=utf-8");
            httpPost.setHeader("Accept", "application/json");

            //验证码
            String random = EmailUtils.getStringRandom(6);

            String Content;
            if (params != null && !params.equals("")) {
                Content = java.net.URLEncoder.encode(params + "【时信互联】", "UTF-8");
            } else {
                Content = java.net.URLEncoder.encode(random + "  (验证码)，此验证码仅限于该手机验证；请在5分钟内完成验证，时信将持续为你服务。【时信互联】", "UTF-8");
            }


            List<NameValuePair> data = new ArrayList<>();
            data.add(new BasicNameValuePair("UserID", "995836"));
            data.add(new BasicNameValuePair("Account", "admin"));
            data.add(new BasicNameValuePair("Password", "1JKY7K"));
            data.add(new BasicNameValuePair("Phones", phone));
            data.add(new BasicNameValuePair("SendType", "1"));
            data.add(new BasicNameValuePair("SendTime", ""));
            data.add(new BasicNameValuePair("PostFixNumber", ""));
            data.add(new BasicNameValuePair("Content", Content));
            httpPost.setEntity(new UrlEncodedFormEntity(data, UTF_8));
            CloseableHttpResponse response = httpClient.execute(httpPost);

            if (response.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
                jc.set(phone + "data", random);
                jc.set(phone, "");
                jc.expire(phone + "data", 5 * 60);
                jc.expire(phone, 60);
                return EntityUtils.toString(response.getEntity(), UTF_8);
            } else {
                return null;
            }
        } catch (IOException e) {
            e.printStackTrace();
            return null;
        }
    }
}
