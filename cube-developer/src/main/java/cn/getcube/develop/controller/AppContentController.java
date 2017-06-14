package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.commons.zookeeper.SyncLock;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.entity.LoginLog;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.para.LoginLogPara;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.service.LoginLogService;
import cn.getcube.develop.utils.DataResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by skmbg on 2016/6/2.
 *
 * @author WeiMinSheng
 * @version V1.0
 *          Copyright (c)2016 tyj-版权所有
 * @title developers
 */
@RestController
@RequestMapping(value = "/app")
@Scope("prototype")
public class AppContentController {
    @Autowired
    public AppService appService;

    @Autowired
    public LoginLogService loginLogService;

    @TokenVerify
    @RequestMapping(value = "/userCube", method = RequestMethod.POST)
    public DataResult<Map> queryAppUser(@RequestParam(name = "token") String token,
                                        @RequestParam(name = "appId") String appId,
                                        UserEntity userSession) {
        SyncLock lock = new SyncLock();
        DataResult<Map> result= new DataResult<>();
        try {
            lock.acquire("app/"+token+"-"+appId);
            Map<String, Object> map = new HashMap<>();
            AppPara appPara = new AppPara();
            appPara.setAppId(appId);
            AppEntity appEntity = appService.queryApp(appPara);

            List<LoginLog> loginLogs = new ArrayList<>();

            LoginLogPara para=new LoginLogPara();
            para.setNumbers(appEntity.getUseId());
            loginLogs = loginLogService.queryInterval(para);
            result.setCode(StateCode.Ok.getCode());
            result.setDesc(AuthConstants.MSG_OK);
            map.put("data", loginLogs);
            result.setData(map);
        }catch (Exception e){
            result.setCode(StateCode.Unknown.getCode());
            result.setDesc("Unknown error");
        }finally {
            lock.release();
        }
        return result;
    }
}
