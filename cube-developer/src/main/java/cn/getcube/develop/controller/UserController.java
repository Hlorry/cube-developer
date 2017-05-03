package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.HttpUriCode;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.UserService;
import cn.getcube.develop.utils.FileUploadUtils;
import cn.getcube.develop.utils.MD5;
import com.alibaba.fastjson.JSON;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.view.AbstractView;
import org.springframework.web.servlet.view.json.MappingJackson2JsonView;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.*;

/**
 * Created by SubDong on 2016/3/8.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/user")
public class UserController {

    @Resource
    private UserService userService;

    @Resource
    JedisCluster jc;


    /**
     * 注册账号
     *
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "name", required = true) String name,
                                @RequestParam(name = "email", required = true) String email,
                                @RequestParam(name = "password", required = true) String password,
//                                @RequestParam(name = "phone", required = true) String phone,
                                @RequestParam(name = "userType", required = true) Integer userType,
                                @RequestParam(name = "way", required = true) Integer way) {
        AbstractView jsonView = new MappingJackson2JsonView();
        if (name != null && email != null && password != null && userType != null && way != null) {

            UserEntity userEntity = new UserEntity();
            userEntity.setName(name);
            userEntity.setEmail(email);
            //MD5加密
            MD5 md5 = new MD5.Builder().source(password).salt(AuthConstants.USER_SALT).build();
            userEntity.setPassword(md5.getMD5());
//            userEntity.setPhone(phone);
            userEntity.setUsertype(userType);
            userEntity.setWay(way);

            //获取uri 邮箱验证时用户访问页面
            //String uri = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();

            Map<String, Object> addUser = userService.addUser(userEntity, HttpUriCode.HTTP_CODE_URI);
            jsonView.setAttributesMap(addUser);
        }
        return new ModelAndView(jsonView);
    }

    /**
     * 多条件查询用户
     *
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public ModelAndView product(HttpServletRequest request, HttpServletResponse response,
                                @RequestParam(name = "token", required = false) String token,
                                @RequestParam(name = "version", required = false) String version,
                                @RequestParam(name = "id", required = false) Integer id,
                                @RequestParam(name = "name", required = false) String name,
                                @RequestParam(name = "email", required = false) String email,
                                @RequestParam(name = "phone", required = false) String phone) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        if (token != null) {
            if (!jc.exists(token)) {
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10015);
                map.put(AuthConstants.AUTH_ERRMSG, "token become invalid");
                map.put("token", token);
                jsonView.setAttributesMap(map);
                return new ModelAndView(jsonView);
            }
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName(name);
        userEntity.setEmail(email);
        userEntity.setPhone(phone);

        UserEntity user = userService.queryUser(userEntity);


        if (Objects.isNull(user)) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10008);
            map.put(AuthConstants.AUTH_ERRMSG, "no such check information");

            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }
        if (user.getAvatar() != null) user.setAvatar(HttpUriCode.HTTP_CODE_URI + user.getAvatar());
        map.put("cube", user);

        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);

    }

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public ModelAndView signin(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "version", required = false) String version,
                               @RequestParam(name = "targetUrl", required = false) String targetUrl,
                               @RequestParam(name = "username", required = false) String username,
                               @RequestParam(name = "password", required = false) String password) throws UnsupportedEncodingException {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        UserEntity userEntity = new UserEntity();
        if (username == null || username.isEmpty()) {
            map.put("target", "username");
            map.put("err", "用户名不能为空");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }

        if (password == null || password.isEmpty()) {
            map.put("target", "password");
            map.put("err", "密码不能为空");
            jsonView.setAttributesMap(map);
            return new ModelAndView(jsonView);
        }

        if (username != null && username.indexOf("@") != -1) {
            userEntity.setEmail(username);
        } else {
            userEntity.setPhone(username);
        }

        MD5 md5 = new MD5.Builder().source(password).salt(AuthConstants.USER_SALT).build();
        userEntity.setPassword(md5.getMD5());
        userEntity = userService.login(userEntity);
        if (userEntity == null) {
            map.put("target", "username");
            map.put("err", "用户名或密码错误");
        } else {
            map.put("code", "0000");
            map.put("data", targetUrl);

//            jc.set("token", UUID.randomUUID().toString());
            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            jc.set(uuid, JSON.toJSONString(userEntity));
            if (4 == userEntity.getUsertype()) {
                map.put("code", "301");
            }
            map.put("token", uuid);
            Cookie cookie = new Cookie("cube_develops_token", uuid);
            Cookie cookie1 = new Cookie("cube_develops_userId", userEntity.getId() + "");
            Cookie cookie2 = new Cookie("cube_develops_userName", URLEncoder.encode(userEntity.getName(), "UTF-8"));
            Cookie cookie3 = new Cookie("cube_develops_activation", userEntity.getActivation() + "");
            setCookie(response, cookie);
            setCookie(response, cookie1);
            setCookie(response, cookie2);
            setCookie(response, cookie3);
        }
        jsonView.setAttributesMap(map);
        return new ModelAndView(jsonView);
    }

    private void setCookie(HttpServletResponse response, Cookie cookie) {
        cookie.setMaxAge(3600);
        cookie.setPath("/");
        response.addCookie(cookie);
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public ModelAndView logout(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "version", required = false) String version) {

        String token = request.getParameter("token");
        if (token == null) {
            for (Cookie cookie : request.getCookies()) {
                if (cookie.getName().equals("cube_develops_token")) {
                    token = cookie.getValue();
                }
                Cookie cookie1 = new Cookie(cookie.getName(), null);
                cookie1.setPath("/");
                cookie1.setMaxAge(0);
                response.addCookie(cookie1);
            }
        }

        jc.del(token);

        try {
            response.sendRedirect("/route/login");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 修改账户名称
     *
     * @param request
     * @param response
     * @param token
     * @param id
     * @param name
     * @param version
     * @return
     */
    @RequestMapping(value = "/updateUserName", method = RequestMethod.POST)
    public ModelAndView param(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "token", required = true) String token,
                              @RequestParam(name = "id", required = true) Integer id,
                              @RequestParam(name = "name", required = true) String name,
                              @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        if (token != null && !token.equals("")) {
            UserEntity userEntity = new UserEntity();
            userEntity.setId(id);
            userEntity.setName(name);
            userEntity.setUpdate_time(new Date());
            int updateUser = userService.updateUser(userEntity);
            if (updateUser > 0) {
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "修改成功");
                jsonView.setAttributesMap(map);
            } else {
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10017);
                map.put(AuthConstants.AUTH_ERRMSG, "修改失败");
                jsonView.setAttributesMap(map);
            }
        } else {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10000);
            map.put(AuthConstants.AUTH_ERRMSG, "无使用权限");
            jsonView.setAttributesMap(map);
        }
        return new ModelAndView(jsonView);
    }

    /**
     * 手机验证
     *
     * @param request
     * @param response
     * @param token
     * @param id
     * @param msmCode
     * @param version
     * @return
     */
    @RequestMapping(value = "/phoneVer", method = RequestMethod.POST)
    public ModelAndView param(HttpServletRequest request, HttpServletResponse response,
                              @RequestParam(name = "token", required = true) String token,
                              @RequestParam(name = "id", required = true) Integer id,
                              @RequestParam(name = "phone", required = true) String phone,
                              @RequestParam(name = "msmCode", required = true) String msmCode,
                              @RequestParam(name = "version", required = false) String version) {
        AbstractView jsonView = new MappingJackson2JsonView();
        Map<String, Object> map = new HashMap<>();
        if (token != null && !token.equals("")) {
            String codeKey = jc.get(phone + "data");
            if (codeKey != null && !codeKey.equals("")) {
                if ((msmCode.toLowerCase()).equals(codeKey.toLowerCase())) {
                    UserEntity userEntity = new UserEntity();
                    userEntity.setId(id);
                    userEntity.setPhone(phone);
                    userEntity.setPhone_verify(1);
                    userEntity.setUpdate_time(new Date());
                    int updateUser = userService.updateUser(userEntity);
                    if (updateUser > 0) {
                        jc.expire(phone + "data", 1);
                        map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                        map.put(AuthConstants.AUTH_SUCCESS, "手机绑定成功");
                        jsonView.setAttributesMap(map);
                    } else {
                        map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10017);
                        map.put(AuthConstants.AUTH_ERRMSG, "绑定手机失败，请重试！");
                        jsonView.setAttributesMap(map);
                    }
                } else {
                    map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10018);
                    map.put(AuthConstants.AUTH_ERRMSG, "验证失败！请填写正确的验证信息");
                    jsonView.setAttributesMap(map);
                }
            } else {
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10012);
                map.put(AuthConstants.AUTH_ERRMSG, "验证过期");
                jsonView.setAttributesMap(map);
            }
        } else {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10000);
            map.put(AuthConstants.AUTH_ERRMSG, "无使用权限");
            jsonView.setAttributesMap(map);
        }
        return new ModelAndView(jsonView);
    }

    /**
     * 头像上传
     *
     * @param request
     * @param response
     * @param token
     * @param id
     * @param version
     * @return
     */
    @RequestMapping(value = "/upload", method = RequestMethod.POST)
    public ModelAndView upload(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "token", required = true) String token,
                               @RequestParam(name = "id", required = true) Integer id,
                               @RequestParam(name = "avatar", required = true) MultipartFile avatar,
                               @RequestParam(name = "version", required = false) String version) {
        Map<String, Object> map = new HashMap<>();
        if (token == null) {
            map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10000);
            map.put(AuthConstants.AUTH_ERRMSG, "无使用权限");
        } else {
            String avatarUrl = FileUploadUtils.uploadFile(avatar, 1, request);
            UserEntity userEntity = new UserEntity();
            userEntity.setUpdate_time(new Date());
            userEntity.setAvatar(avatarUrl);
            userEntity.setId(id);
            int updateUser = userService.updateUser(userEntity);
            if (updateUser > 0) {
                map.put(AuthConstants.AUTH_STATE, AuthConstants.AUTH_SUCCESS_200);
                map.put(AuthConstants.AUTH_SUCCESS, "头像上传成功");
            } else {
                map.put(AuthConstants.AUTH_ERRCODE, AuthConstants.AUTH_ERROR_10017);
                map.put(AuthConstants.AUTH_ERRMSG, "头像上传失败");
            }
        }
        return new ModelAndView("redirect:/route/personal", map);
    }
}
