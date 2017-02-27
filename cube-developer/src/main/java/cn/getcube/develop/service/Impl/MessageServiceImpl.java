package cn.getcube.develop.service.Impl;

import cn.getcube.develop.service.MessageService;
import cn.getcube.develop.utils.EmailUtils;
import cn.getcube.develop.utils.HttpClientUtils;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * Created by Administrator on 2016/4/14.
 */
@Service
public class MessageServiceImpl implements MessageService {

    @Resource
    JedisCluster jc;

    private static final String CHARSET_UTF_8 = "UTF-8";

    @Override
    public Integer sendMessage(String phone, String message) {
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
            props.load(new InputStreamReader(MessageServiceImpl.class.getClassLoader().getResourceAsStream("SMS.properties"), CHARSET_UTF_8));

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
                jc.set(phone + "data", random);
                jc.set(phone, "");
                jc.expire(phone + "data", 5 * 60);
                jc.expire(phone, 60);
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
