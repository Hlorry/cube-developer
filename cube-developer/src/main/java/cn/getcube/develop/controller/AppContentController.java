package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.entity.LoginLog;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.para.LoginLogPara;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.service.LoginLogService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

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
@RequestMapping(value = "/v1/app")
@Scope("prototype")
public class AppContentController {
    @Autowired
    public AppService appService;

    @Autowired
    public LoginLogService loginLogService;

    @RequestMapping(value = "/userCube", method = RequestMethod.POST)
    public ModelAndView queryAppUser(@RequestParam(name = "appId", required = false) String appId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();

        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);

      /*  JSONArray objects = JSON.parseArray(appEntity.getUseId());*/

        List<LoginLog> loginLogs = new ArrayList<>();
        
        LoginLogPara para=new LoginLogPara();
        para.setNumbers(appEntity.getUseId());
        if(para.getCubes()!=null){
            loginLogs = loginLogService.queryInterval(para);
            map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
            map.put("data", loginLogs);
        }else{
        	//loginLogs = loginLogService.queryInterval(para);
            map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_ERROR_10008);
            map.put("data", loginLogs);
        }
       /* objects.forEach(a -> {
            JSONObject aa = (JSONObject) a;
            int[] number = ArrayNumberUtils.getArrayNumber(aa.get("numStart").toString(), aa.get("numEnd").toString());
            List<LoginLog> logs = loginLogService.queryInterval(number);
            loginLogs.addAll(logs);
        });*/
       

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }
}
