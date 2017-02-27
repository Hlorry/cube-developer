package cn.getcube.develop.controller;

import cn.getcube.develop.constants.AuthConstants;
import cn.getcube.develop.constants.HttpUriCode;
import cn.getcube.develop.entity.AppEntity;
import cn.getcube.develop.para.AppPara;
import cn.getcube.develop.service.AppService;
import cn.getcube.develop.utils.FileUploadUtils;
import cn.getcube.develop.utils.MD5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;

import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

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
    public ModelAndView createApp(@RequestParam(name = "token", required = false) String token,
                                  @RequestParam(name = "appName", required = false) String appName,
                                  @RequestParam(name = "appStage", required = false) String appStage,
                                  @RequestParam(name = "category", required = false) String category,
                                  @RequestParam(name = "description", required = false) String description,
                                  @RequestParam(name = "appUserLevel", required = false) String appUserLevel,
                                  @RequestParam(name = "userId", required = false) String userId,
                                  @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
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
        Map<String, String> map = appService.createApp(appPara);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/update")
    public ModelAndView updateApp(HttpServletRequest request,
                                  @RequestParam(name = "token", required = false) String token,
                                  @RequestParam(name = "appName", required = false) String appName,
                                  @RequestParam(name = "appStage", required = false) String appStage,
                                  @RequestParam(name = "category", required = false) String category,
                                  @RequestParam(name = "description", required = false) String description,
                                  @RequestParam(name = "appUserLevel", required = false) String appUserLevel,
                                  @RequestParam(name = "userId", required = false) String userId,
                                  @RequestParam(name = "appId", required = false) String appId,
                                  @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
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
        Map<String, String> map = appService.modifyApp(appPara);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/delete")
    public ModelAndView deleteApp(@RequestParam(name = "token", required = false) String token,
                                  @RequestParam(name = "delAppId", required = false) String appId,
                                  @RequestParam(name = "name", required = false) String appName,
                                  @RequestParam(name = "password", required = false) String password,
                                  @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
        // 判断用户输入的应用名称是否和应用ID匹配
        // 如果不匹配返回
        // 判断用户密码是否正确
        // 如果不正确返回
        // 根据应用ID进行删除操作
        MD5 md5 = new MD5.Builder().source(password).salt(AuthConstants.USER_SALT).build();
        Map<String, String> map = appService.deleteApp(appId, appName, md5.getMD5());
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ModelAndView queryApp(@RequestParam(name = "token", required = true) String token,
                                 @RequestParam(name = "appId", required = false) String appId,
                                 @RequestParam(name = "appIndex", required = false) String appIndex,
                                 @RequestParam(name = "appName", required = false) String appName,
                                 @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        // 验证token。判断用户时候登陆
        // 在拦截器中统一验证
        // 验证查询条件
        if (appId == null && appIndex == null && appName == null) {
            map.put("errcode", "10088");
            map.put("errmsg", "Query param error.");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }

        // 根据应用的ID查询应用信息
        AppPara appPara = new AppPara();
        appPara.setId(Integer.parseInt(appId));
        AppEntity appEntity = appService.queryApp(appPara);
        map.put("state", "200");
        map.put("app", appEntity);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/query/all", method = RequestMethod.POST)
    public ModelAndView queryAllApp(@RequestParam(name = "token", required = true) String token,
                                    @RequestParam(name = "userId", required = true) String userId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        // 验证token。判断用户时候登陆
        // 在拦截器中统一验证
        // 验证查询条件
        if (userId == null || userId == "") {
            Map<String, String> map = new HashMap<>();
            map.put("errcode", "10088");
            map.put("errmsg", "Query param error.");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }

        // 根据用户的ID查询所有的应用信息
        AppPara appPara = new AppPara();
        appPara.setUserId(Integer.parseInt(userId));
//        Map<String, Object> map = appService.queryAllApps(appPara);
//        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "", method = RequestMethod.GET)
    public ModelAndView app(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView();
        String userId = request.getParameter("userId");
        if (userId == null && request.getCookies() != null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("cube_develops_userId")) {
                    userId = cookie.getValue();
                }
            }
        }
        if (userId == null) {
            // userId等于空。。。
            userId = "0";
        }
        // 根据用户的ID查询所有的应用信息
        AppPara appPara = new AppPara();
        appPara.setUserId(Integer.parseInt(userId));
        List<AppEntity> apps = appService.queryAllApps(appPara);
        modelAndView.addObject("apps", apps);
        modelAndView.setViewName("app");
        return modelAndView;
    }

    @RequestMapping(value = "/detail/{appId}", method = RequestMethod.GET)
    public ModelAndView overview(HttpServletResponse response, @PathVariable("appId") String appId) throws IOException {
        ModelAndView modelAndView = new ModelAndView();
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);
        if (appEntity == null) {
            response.sendRedirect("/app");
            return null;
        } else {
            modelAndView.addObject("appObj", appEntity);
            modelAndView.setViewName("app-detail");
            return modelAndView;
        }
    }

    @RequestMapping(value = "/param", method = RequestMethod.POST)
    public ModelAndView param(@RequestParam(name = "appId", required = false) String appId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        AppEntity appEntity = appService.queryApp(appPara);
        String[] appStage = new String[]{"", "未运营尚无用户", "运营中已有用户"};
        map.put("appStage", appStage);
        map.put("result", appEntity);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/avatar", method = RequestMethod.POST)
    public ModelAndView avatar(HttpServletRequest request, @RequestParam(name = "avatar", required = false) MultipartFile avatar, @RequestParam(name = "appId", required = false) String appId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        String avatarUrl = FileUploadUtils.uploadFile(avatar, 4, request);
        if (null == avatarUrl || avatarUrl.equals("error")) {
            Map<String, String> map = new HashMap<>();
            map.put("code", "4008");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }
        AppPara appPara = new AppPara();
        appPara.setAppId(appId);
        appPara.setAvatar(avatarUrl);
        Map<String, String> map = appService.avatarApp(appPara);
        map.put("uri", HttpUriCode.HTTP_CODE_URI + avatarUrl);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/environment", method = RequestMethod.POST)
    public ModelAndView avatar(HttpServletRequest request,
                               @RequestParam(name = "environment", required = false) String environment,
                               @RequestParam(name = "appId", required = false) String appId,
                               @RequestParam(name = "userId", required = false) Integer userId) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = appService.updateEnvironment(environment, appId, userId);
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    @RequestMapping(value = "/change_env", method = RequestMethod.POST)
    public Map<String,Object> changeEnvironment(String appid){
    	return appService.changeEnvironment(appid);
    }
}
