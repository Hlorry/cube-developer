package cn.getcube.develop.utils;

import cn.getcube.develop.utils.redis.RedisConnectionManager;
import cube.sms.SMSServiceTest;
import cube.sms.common.Result;
import cube.sms.common.SMSBusinessName;
import cube.sms.model.Policy;
import cube.sms.model.SMSEntity;
import cube.sms.model.SMSServiceName;
import cube.sms.service.SMSPublicService;
import cube.sms.service.SMSServiceManager;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by wms on 2017/3/16.
 *
 * @author WeiMinSheng
 * @version V2.0
 *          Copyright (c)2016 tyj-版权所有
 * @since 2017/3/16
 */
public class SMSUtil {

    public static Result sned(String code,String name,String mobile,int type) {
        name="魔方";
        String tmpCode = "SMS_27800138";
        switch (type){
            //注册
            case 1:
                tmpCode="SMS_27800140";
                break;
            //修改密码
            case 2:
                tmpCode="SMS_27800138";
                break;
            //修改信息
            case 3:
                tmpCode="SMS_27800137";
                break;
        }
        String signName = "时信互联";
        HashMap paramMap = new HashMap();
        paramMap.put("code", code);
        paramMap.put("product", name);
        SMSServiceManager.getInstance().startup(RedisConnectionManager.getInstance().getBinaryJedisCluster());
        SMSPublicService publicService = SMSServiceManager.getInstance().getPublicService(SMSBusinessName.PUBLIC, SMSServiceName.AliYun);
        SMSEntity smsEntity = new SMSEntity(SMSBusinessName.PUBLIC, tmpCode, signName, paramMap);
        return publicService.sendOnce(smsEntity, mobile);
    }

}