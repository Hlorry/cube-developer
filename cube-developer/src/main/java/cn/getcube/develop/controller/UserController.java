package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.HttpUriCode;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.UserService;
import cn.getcube.develop.utils.*;
import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
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
    public DataResult<UserEntity> product(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "name", required = true) String name,
                               @RequestParam(name = "account", required = true) String account,
                               @RequestParam(name = "password", required = true) String password,
                               @RequestParam(name = "userType", required = true) Integer userType,
                               @RequestParam(name = "way", required = false) Integer way) {
        DataResult<UserEntity> result = new DataResult<>();
        if (name != null && account != null && password != null && userType != null) {

            UserEntity userEntity = new UserEntity();

            if(account.indexOf("@") != -1){
                userEntity.setEmail(account);
                //邮箱验证
                if (!RegexUtil.isEmail(userEntity.getEmail())) {
                    return new DataResult<>(StateCode.AUTH_ERROR_10004.getCode(),AuthConstants.FORMAT_ERROR);
                }
                UserEntity param = new UserEntity();
                param.setEmail(account);
                UserEntity  user = userService.queryUser(param);
                if(user != null){
                    return new DataResult<>(StateCode.AUTH_ERROR_10023.getCode(),AuthConstants.EMAIL_EXISTS);
                }
            }else {
                userEntity.setPhone(account);
                if (!RegexUtil.checkMobile(userEntity.getPhone())) {
                    return new DataResult<>(StateCode.AUTH_ERROR_10022.getCode(),AuthConstants.PHONE_FORMAT_ERROR);
                }

                UserEntity param = new UserEntity();
                param.setPhone(account);
                UserEntity  user = userService.queryUser(param);
                if(user != null){
                    return new DataResult<>(StateCode.AUTH_ERROR_10024.getCode(),AuthConstants.PHONE_EXISTS);
                }
            }

            //MD5加密
            MD5 md5 = new MD5.Builder().source(password).salt(AuthConstants.USER_SALT).build();
            userEntity.setName(name);
            userEntity.setPassword(md5.getMD5());
            userEntity.setUsertype(userType);
            if(way != null){
                userEntity.setWay(way);
            }
            userEntity.setCreate_time(new Date());
            userEntity.setUpdate_time(new Date());

            try {
                userService.addUser(userEntity);
               MessageUtils.getInstance().sendEmailOrPhone(jc,account,userEntity);
                return new DataResult<>(userEntity);
            } catch (Exception e) {
                return new DataResult<>(StateCode.AUTH_ERROR_10009,AuthConstants.REGISTER_ERROR);
            }
        }
       return new DataResult<>(StateCode.AUTH_ERROR_10009,AuthConstants.REGISTER_ERROR);
    }

    /**
     * 多条件查询用户
     *
     * @return
     */
    @RequestMapping(value = "/query", method = RequestMethod.POST)
    public DataResult<UserEntity> product(HttpServletRequest request, HttpServletResponse response,
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
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10015);
                map.put(AuthConstants.DESC, "token become invalid");
                map.put("token", token);
                jsonView.setAttributesMap(map);
                return new DataResult<>();
            }
        }

        UserEntity userEntity = new UserEntity();
        userEntity.setId(id);
        userEntity.setName(name);
        userEntity.setEmail(email);
        userEntity.setPhone(phone);

        UserEntity user = userService.queryUser(userEntity);


        if (Objects.isNull(user)) {
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10008);
            map.put(AuthConstants.DESC, "no such check information");

            jsonView.setAttributesMap(map);
            return new DataResult<>();
        }
        if (user.getAvatar() != null) user.setAvatar(HttpUriCode.HTTP_CODE_URI + user.getAvatar());
        map.put("cube", user);

        jsonView.setAttributesMap(map);
        return new DataResult<>();

    }

    /**
     * 登陆
     *
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public DataResult<JSONObject> signin(HttpServletRequest request, HttpServletResponse response,
                               @RequestParam(name = "version", required = false) String version,
                               @RequestParam(name = "targetUrl", required = false) String targetUrl,
                               @RequestParam(name = "username", required = true) String username,
                               @RequestParam(name = "password", required = true) String password) throws UnsupportedEncodingException {
//        Map<String, Object> map = new HashMap<>();
        UserEntity userEntity = new UserEntity();
        if (username == null || username.isEmpty()) {
            return new DataResult<>(StateCode.AUTH_ERROR_10016.getCode(),AuthConstants.NULL_NAME);
        }

        if (password == null || password.isEmpty()) {
            return new DataResult<>(StateCode.AUTH_ERROR_10016.getCode(),AuthConstants.NULL_PASSWORD);
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
            return new DataResult<>(StateCode.AUTH_ERROR_10002.getCode(),AuthConstants.USER_PSD_ERROR);
        } else {
//            map.put("code", "0000");
//            map.put("data", targetUrl);

            String uuid = UUID.randomUUID().toString().replaceAll("-", "");
            jc.set("token", uuid);

            jc.set(uuid, JSON.toJSONString(userEntity));
//            if (4 == userEntity.getUsertype()) {
//                map.put("code", "301");
//            }
            JSONObject jsonObject = new JSONObject();
            jsonObject.put("token",uuid);
            jsonObject.put("user",userEntity);

            DataResult<JSONObject> dataResult = new DataResult<>();
            dataResult.setData(jsonObject);
            dataResult.setCode(StateCode.Ok.getCode());
            dataResult.setDesc(AuthConstants.MSG_OK);

            return dataResult;

        }

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
                map.put(AuthConstants.CODE, StateCode.Ok);
                map.put(AuthConstants.DESC, "修改成功");
                jsonView.setAttributesMap(map);
            } else {
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10017);
                map.put(AuthConstants.DESC, "修改失败");
                jsonView.setAttributesMap(map);
            }
        } else {
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10000);
            map.put(AuthConstants.DESC, "无使用权限");
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
                        map.put(AuthConstants.CODE, StateCode.Ok);
                        map.put(AuthConstants.DESC, "手机绑定成功");
                        jsonView.setAttributesMap(map);
                    } else {
                        map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10017);
                        map.put(AuthConstants.DESC, "绑定手机失败，请重试！");
                        jsonView.setAttributesMap(map);
                    }
                } else {
                    map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10018);
                    map.put(AuthConstants.DESC, "验证失败！请填写正确的验证信息");
                    jsonView.setAttributesMap(map);
                }
            } else {
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10012);
                map.put(AuthConstants.DESC, "验证过期");
                jsonView.setAttributesMap(map);
            }
        } else {
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10000);
            map.put(AuthConstants.DESC, "无使用权限");
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
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10000);
            map.put(AuthConstants.DESC, "无使用权限");
        } else {
            String avatarUrl = FileUploadUtils.uploadFile(avatar, 1, request);
            UserEntity userEntity = new UserEntity();
            userEntity.setUpdate_time(new Date());
            userEntity.setAvatar(avatarUrl);
            userEntity.setId(id);
            int updateUser = userService.updateUser(userEntity);
            if (updateUser > 0) {
                map.put(AuthConstants.CODE, StateCode.Ok);
                map.put(AuthConstants.DESC, "头像上传成功");
            } else {
                map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_10017);
                map.put(AuthConstants.DESC, "头像上传失败");
            }
        }
        return new ModelAndView("redirect:/route/personal", map);
    }
}
