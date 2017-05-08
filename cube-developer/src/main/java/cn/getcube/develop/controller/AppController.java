package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.HttpUriCode;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.utils.DataResult;
import cn.getcube.develop.utils.FileUploadUtils;
import cn.getcube.develop.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2016/3/11.
 */
@RestController
@RequestMapping(value = "/app")
@Scope("prototype")
public class AppController {

    @Autowired
    public AppService appService;

    @RequestMapping(value = "/create", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> createApp(@RequestParam(name = "token", required = true) String token,
                                                     @RequestParam(name = "appName", required = false) String appName,
                                                     @RequestParam(name = "appStage", required = false) String appStage,
                                                     @RequestParam(name = "category", required = false) String category,
                                                     @RequestParam(name = "description", required = false) String description,
                                                     @RequestParam(name = "appUserLevel", required = false) String appUserLevel,
                                                     @RequestParam(name = "userId", required = false) String userId,
                                                     @RequestParam(name = "version", required = false) String version) {
        // 判断用户名下该应用名称是否已经存在
        // 如果存在返回
        // 进行存储操作

        // 验证输入值
        AppPara appPara = new AppPara();
        appPara.setUserId(userId == null ? 0 : Integer.parseInt(userId));
        appPara.setAppName(appName);
        appPara.setAppStage(appStage);
        if ("2".equals(appStage)) {// 运营中已有用户，设置用户量级
            appPara.setAppUserLevel(appUserLevel);
        }
        appPara.setCategory(category);
        appPara.setDescription(description);
        return appService.createApp(appPara);
    }

    @RequestMapping(value = "/update", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> updateApp(HttpServletRequest request,
                                                     @RequestParam(name = "token", required = true) String token,
                                                     @RequestParam(name = "appName", required = false) String appName,
                                                     @RequestParam(name = "appStage", required = false) String appStage,
                                                     @RequestParam(name = "category", required = false) String category,
                                                     @RequestParam(name = "description", required = false) String description,
                                                     @RequestParam(name = "appUserLevel", required = false) String appUserLevel,
                                                     @RequestParam(name = "userId", required = false) String userId,
                                                     @RequestParam(name = "appId", required = true) String appId,
                                                     @RequestParam(name = "version", required = false) String version) {
        // 判断用户名下该应用名称是否已经存在
        // 如果存在返回
        // 进行修改操作

        // 验证输入值
        AppPara appPara = new AppPara();
//        appPara.setUserId(userId == null ? 0 : Integer.parseInt(userId));
        appPara.setAppName(appName);
        appPara.setAppStage(appStage);
        if ("1".equals(appStage)) {
            appPara.setAppUserLevel(null);
        }
        if ("2".equals(appStage)) {
            appPara.setAppUserLevel(appUserLevel);
        }
        appPara.setCategory(category);
        appPara.setDescription(description);
        appPara.setAppId(appId);
        return appService.modifyApp(appPara);
    }

    @RequestMapping(value = "/delete")
    @TokenVerify
    public DataResult<Map<String, Object>> deleteApp(@RequestParam(name = "token") String token,
                                                     @RequestParam(name = "appId") String appId,
                                                     @RequestParam(name = "name") String appName,
                                                     @RequestParam(name = "password") String password,
                                                     @RequestParam(name = "version", required = false) String version) {
        // 判断用户输入的应用名称是否和应用ID匹配
        // 如果不匹配返回
        // 判断用户密码是否正确
        // 如果不正确返回
        // 根据应用ID进行删除操作
        MD5 md5 = new MD5.Builder().source(password).salt(AuthConstants.USER_SALT).build();
        return appService.deleteApp(appId, appName, md5.getMD5());
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> queryApp(@RequestParam(name = "token", required = true) String token,
                                                    @RequestParam(name = "appId", required = true) String appId,
                                                    @RequestParam(name = "appIndex", required = false) String appIndex,
                                                    @RequestParam(name = "appName", required = false) String appName,
                                                    @RequestParam(name = "version", required = false) String version) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        // 验证token。判断用户时候登陆
        // 在拦截器中统一验证
        // 验证查询条件
        if (appId == null && appIndex == null && appName == null) {
            dataResult.setCode(StateCode.APP_QUERY_PARAM_ERROR.getCode());
            dataResult.setDesc("Query param error.");
            return dataResult;
        }

        // 根据应用的ID查询应用信息
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);
        map.put("app", appEntity);
        dataResult.setCode(StateCode.Ok.getCode());
        dataResult.setDesc("Ok.");
        dataResult.setData(map);
        return dataResult;
    }

    @RequestMapping(value = "/query/all", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> queryAllApp(@RequestParam(name = "token", required = true) String token,
                                                       @RequestParam(name = "userId", required = true) String userId) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        // 验证token。判断用户时候登陆
        // 在拦截器中统一验证
        // 验证查询条件
        if (userId == null || userId == "") {
            dataResult.setCode(StateCode.APP_QUERY_PARAM_ERROR.getCode());
            dataResult.setDesc("Query param error.");
            return dataResult;
        }

        // 根据用户的ID查询所有的应用信息
        AppPara appPara = new AppPara();
        appPara.setUserId(Integer.parseInt(userId));
        List<AppEntity> apps = appService.queryAllApps(appPara);
        map.put("apps", apps);
        dataResult.setCode(StateCode.Ok.getCode());
        dataResult.setDesc("Ok.");
        dataResult.setData(map);
        return dataResult;
    }

    @RequestMapping(value = "", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> app(HttpServletRequest request,
                                               @RequestParam(name = "token", required = true) String token,
                                               UserEntity userSession) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        if (userSession.getId() == null) {
            // userId等于空。。。
            userSession.setId(0);
        }
        // 根据用户的ID查询所有的应用信息
        AppPara appPara = new AppPara();
        appPara.setUserId(userSession.getId());
        List<AppEntity> apps = appService.queryAllApps(appPara);
        map.put("apps", apps);
        dataResult.setCode(StateCode.Ok.getCode());
        dataResult.setDesc("Ok.");
        dataResult.setData(map);
        return dataResult;
    }

    @RequestMapping(value = "/detail", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> overview(HttpServletResponse response,
                                                    @RequestParam(name = "token", required = true) String token,
                                                    @RequestParam(name = "appId", required = true) String appId) throws IOException {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);
        if (appEntity == null) {
            response.sendRedirect("/app");
            return null;
        } else {
            //modelAndView.addObject("appObj", appEntity);
            //modelAndView.setViewName("app-detail");
            map.put("app", appEntity);
            dataResult.setCode(StateCode.Ok.getCode());
            dataResult.setDesc("Ok.");
            dataResult.setData(map);
            return dataResult;
        }
    }

    @RequestMapping(value = "/param", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> param(@RequestParam(name = "token", required = true) String token,
                                                 @RequestParam(name = "appId", required = true) String appId) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);
        String[] appStage = new String[]{"", "未运营尚无用户", "运营中已有用户"};
        map.put("stage", appStage);
        map.put("app", appEntity);
        dataResult.setCode(StateCode.Ok.getCode());
        dataResult.setDesc("Ok.");
        dataResult.setData(map);
        return dataResult;
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    @TokenVerify
    public DataResult avatar(@RequestParam(name = "token") String token,
                             @RequestParam(name = "avatar", required = true) MultipartFile avatar,
                             @RequestParam(name = "appId") String appId) {
        Map<String, Object> map = new HashMap<>();
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        String avatarUrl = FileUploadUtils.uploadFile(avatar, 4);
        if (null == avatarUrl || avatarUrl.equals("error")) {
            dataResult.setCode(StateCode.APP_UPLOAD_AVATAR_ERROR.getCode());
            dataResult.setDesc("upload error.");
            return dataResult;
        }
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        appPara.setAvatar(avatarUrl);
        dataResult = appService.avatarApp(appPara);
        map.put("uri", HttpUriCode.HTTP_CODE_URI + avatarUrl);
        dataResult.setData(map);
        return dataResult;
    }

    @RequestMapping(value = "/environment", method = RequestMethod.POST)
    @TokenVerify
    public DataResult<Map<String, Object>> avatar(HttpServletRequest request,
                                                  @RequestParam(name = "token", required = true) String token,
                                                  @RequestParam(name = "environment", required = false) String environment,
                                                  @RequestParam(name = "appId", required = false) String appId,
                                                  @RequestParam(name = "userId", required = false) Integer userId) {
        return appService.updateEnvironment(environment, appId, userId);
    }

    @RequestMapping(value = "/change_env", method = RequestMethod.POST)
    public Map<String, Object> changeEnvironment(String appid) {
        return appService.changeEnvironment(appid);
    }
}
