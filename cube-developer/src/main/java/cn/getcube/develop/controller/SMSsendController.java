package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.utils.BaseResult;
import cn.getcube.develop.utils.RegexUtil;
import cn.getcube.develop.utils.SendMSMUtils;
import cn.getcube.develop.utils.redis.RedisKey;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Created by Administrator on 2016/4/14.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/SMS")
public class SMSsendController {

    @Resource
    JedisCluster jc;

    /**
     * Redis Key  验证
     *
     * @return
     */
    @RequestMapping(value = "/redis", method = RequestMethod.POST)
    public BaseResult product(@RequestParam(name = "redisKey") String redisKey) {
        BaseResult result = new BaseResult();
        if(jc.exists(redisKey)){
            Map<String,String> map1 = new HashMap<>();
            String redisValue = jc.get(redisKey);
            map1.put(redisKey,redisValue);
            result.setCode(StateCode.Ok.getCode());
            result.setDesc(AuthConstants.MSG_OK);
        }else{
            result.setCode(StateCode.AUTH_ERROR_10008.getCode());
            result.setDesc("查无数据");
        }
        return result;
    }

    /**
     * 短信发送验证码
     *
     * @return
     */
    @PostMapping(value = "/send/code")
    public BaseResult send(@RequestParam(name = "phone", required = true) String phone,@RequestParam(name = "type", required = true) Integer type) {
        BaseResult result = new BaseResult();
        if(type>6||type<1){
            result.setCode(StateCode.ParamInvalid.getCode());
            result.setDesc("参数不合法！");
            return result;
        }
        if (RegexUtil.checkMobile(phone)) {
            Integer sendMessage = SendMSMUtils.postRequest(phone,null,type);
            if(sendMessage == 200){
                result.setCode(StateCode.Ok.getCode());
                result.setDesc("短信发送成功！");
            }else if(sendMessage == 501){
                result.setCode(StateCode.AUTH_ERROR_9999.getCode());
                result.setDesc("短信发送失败！");
            }else if(sendMessage ==404){
                result.setCode(StateCode.AUTH_ERROR_10013.getCode());
                result.setDesc("短信发送太频繁");
            }else {
                result.setCode(StateCode.AUTH_ERROR_100.getCode());
                result.setDesc("未知错误,请重试！");
            }
        } else {
            result.setCode(StateCode.AUTH_ERROR_9998.getCode());
            result.setDesc("手机号格式错误！");
        }
        return  result;
    }

    /**
     * 验证码验证
     *
     * @return
     */
    @PostMapping(value = "/check/code")
    public BaseResult check(@RequestParam(name = "phone", required = true) String phone,
                            @RequestParam(name = "type", required = true) Integer type,
                            @RequestParam(name = "msmCode", required = true) String msmCode) {
        BaseResult result = new BaseResult();
        String code="";
        if(type>6||type<1){
            result.setCode(StateCode.ParamInvalid.getCode());
            result.setDesc("参数不合法！");
            return result;
        }
        if(RegexUtil.checkMobile(phone)){
            switch (type){
                case 1:
                    code = jc.get(RedisKey.SMS_REG+phone);
                    break;
                case 2:
                    code = jc.get(RedisKey.SMS_BIND+phone);
                    break;
                case 3:
                    code = jc.get(RedisKey.SMS_FIX_OLD+phone);
                    break;
                case 4:
                    code = jc.get(RedisKey.SMS_RESET+phone);
                    break;
                case 5:
                    code = jc.get(RedisKey.SMS_UNBIND+phone);
                    break;
                case 6:
                    code = jc.get(RedisKey.SMS_FIX_NEW+phone);
                    break;
            }
            if(!Objects.isNull(code)&&msmCode.equals(code)){
                if (type==3){
                    jc.del(RedisKey.SMS_FIX_OLD+phone);
                }
                result.setCode(StateCode.Ok.getCode());
                result.setDesc(AuthConstants.MSG_OK);
            }else{
                result.setCode(StateCode.AUTH_ERROR_10027.getCode());
                result.setDesc("验证码错误！");
            }
        }else{
            result.setCode(StateCode.AUTH_ERROR_9998.getCode());
            result.setDesc("手机号格式错误！");
        }
        return  result;
    }
}
