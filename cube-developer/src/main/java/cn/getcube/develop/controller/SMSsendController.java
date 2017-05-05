package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.service.MessageService;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/4/14.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/SMS")
public class SMSsendController {
    @Resource
    private MessageService messageService;

    @Resource
    JedisCluster jc;

    /**
     * 短信发送
     *
     * @return
     */
    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "token", required = true) String token,
                                @RequestParam(name = "phone", required = true) String phone,
                                @RequestParam(name = "message", required = false) String message) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        if (phone != null && token != null) {
            Integer sendMessage = messageService.sendMessage(phone, message);
            if(sendMessage == 200){
                map.put(AuthConstants.CODE, StateCode.Ok);
                map.put(AuthConstants.DESC, "短信发送成功！");
                jsonView.setAttributesMap(map);
            }else if(sendMessage == 501){
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10005);
                map.put(AuthConstants.DESC, "短信发送失败！");
                jsonView.setAttributesMap(map);
            }else if(sendMessage == -1){
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_100);
                map.put(AuthConstants.DESC, "未知错误,请重试");
                jsonView.setAttributesMap(map);
            }
        } else {
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10016);
            map.put(AuthConstants.DESC, "缺少参数");
            jsonView.setAttributesMap(map);
        }
        return new ModelAndView(jsonView);
    }

    /**
     * Redis Key  验证
     *
     * @return
     */
    @RequestMapping(value = "/redis", method = RequestMethod.GET)
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "redisKey", required = true) String redisKey) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        if(jc.exists(redisKey)){
            Map<String,String> map1 = new HashMap<>();
            String redisValue = jc.get(redisKey);
            map1.put(redisKey,redisValue);
            map.put(AuthConstants.CODE, StateCode.Ok);
            map.put(AuthConstants.DESC, map1);
            jsonView.setAttributesMap(map);
        }else{
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10008);
            map.put(AuthConstants.DESC, "查无数据");
            jsonView.setAttributesMap(map);
        }
        return new ModelAndView(jsonView);
    }
}
