package cn.getcube.develop.controller;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by HL on 2017/5/8.
 */
@RequestMapping("/auth")
@Controller
public class EmailActivation {

    @Resource
    JedisCluster jc;
    @Resource
    private UserDao userDao;


    /**
     * 邮件密码重置验证
     *
     * @param actmd5 系统生成的字符串
     * @return
     */
    @RequestMapping(value = "/password/activation", method = RequestMethod.GET)
    public String pwdActivation(@RequestParam(name = "actmd5", required = true) String actmd5,@RequestParam(name = "version", required = false) String version,
                                Model model) {
        if (Objects.nonNull(actmd5)) {
            String value = jc.get(actmd5);
            if (value != null) {
                model.addAttribute("code", 200);
                model.addAttribute("desc", "ok");
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.valueOf(value));
                UserEntity user = userDao.queryUser(userEntity);
                jc.del(actmd5);
                if (Objects.isNull(user)) {
                    model.addAttribute("code", 500);
                    model.addAttribute("desc", "无效的链接");
                }
            } else {
                model.addAttribute("code", 500);
                model.addAttribute("desc", "无效的链接");
            }
        } else {
            model.addAttribute("code", 500);
            model.addAttribute("desc", "无效的链接");
        }
        return "password-new";
    }

    /**
     * 修改邮箱 验证连接
     *
     * @param actmd5 系统生成的字符串
     * @return
     */
    @RequestMapping(value = "/fix/activation", method = RequestMethod.GET)
    public String emailFix(@RequestParam(name = "actmd5", required = true) String actmd5,@RequestParam(name = "version", required = false) String version,
                                Model model) {
        if (Objects.nonNull(actmd5)) {
            String value = jc.get(actmd5+"_fix");
            if (value != null) {
                jc.del(actmd5+"_fix");
                model.addAttribute("id", value);
                model.addAttribute("code", 200);
                String userId = value.split("_")[0];
                String email = value.split("_")[1];
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.valueOf(userId));
                UserEntity user = userDao.queryUser(userEntity);

                UserEntity userEntity1 = new UserEntity();
                userEntity1.setEmail(email);
                UserEntity user2 = userDao.queryUser(userEntity1);
                if(!Objects.isNull(user2)){
                    model.addAttribute("code", StateCode.AUTH_ERROR_10023);
                    model.addAttribute("desc", "邮箱已被注册");
                }else if (Objects.isNull(user)) {
                    model.addAttribute("code", 500);
                    model.addAttribute("desc", "无效的链接");
                }else {
                    userEntity.setEmail(email);
                    userDao.fixEmail(userEntity);
                }
            } else {
                model.addAttribute("code", 500);
                model.addAttribute("desc", "无效的链接");
            }
        } else {
            model.addAttribute("code", 500);
            model.addAttribute("desc", "无效的链接");
        }
        return "email-fix";
    }

    /**
     * 修改邮箱 验证连接
     *
     * @param actmd5 系统生成的字符串
     * @return
     */
    @RequestMapping(value = "/bind/activation", method = RequestMethod.GET)
    public String emailBind(@RequestParam(name = "actmd5", required = true) String actmd5,@RequestParam(name = "version", required = false) String version,
                           Model model) {
        if (Objects.nonNull(actmd5)) {
            String value = jc.get(actmd5+"_bind");
            if (value != null) {
                jc.del(actmd5+"_bind");
                model.addAttribute("code", 200);
                String userId = value.split("_")[0];
                String email = value.split("_")[1];
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.valueOf(userId));
                UserEntity user = userDao.queryUser(userEntity);

                UserEntity userEntity1 = new UserEntity();
                userEntity1.setEmail(email);
                UserEntity user2 = userDao.queryUser(userEntity1);
                if(!Objects.isNull(user2)){
                    model.addAttribute("code", StateCode.AUTH_ERROR_10023);
                    model.addAttribute("desc", "邮箱已被注册");
                }else if (Objects.isNull(user)) {
                    model.addAttribute("code", 500);
                    model.addAttribute("desc", "无效的链接");
                }else {
                    userEntity.setEmail(email);
                    userDao.fixEmail(userEntity);
                }
            } else {
                model.addAttribute("code", 500);
                model.addAttribute("desc", "无效的链接");
            }
        } else {
            model.addAttribute("code", 500);
            model.addAttribute("desc", "无效的链接");
        }
        return "email-bind";
    }

    /**
     * 修改邮箱 验证连接
     *
     * @param actmd5 系统生成的字符串
     * @return
     */
    @RequestMapping(value = "/unbind/activation", method = RequestMethod.GET)
    public String emailUnbind(@RequestParam(name = "actmd5", required = true) String actmd5,@RequestParam(name = "version", required = false) String version,
                            Model model) {
        if (Objects.nonNull(actmd5)) {
            String value = jc.get(actmd5+"_unbind");
            if (value != null) {
                jc.del(actmd5+"_unbind");
                model.addAttribute("code", 200);
                String userId = value.split("_")[0];
                String email = value.split("_")[1];
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.valueOf(userId));
                userEntity.setEmail(null);
                UserEntity user = userDao.queryUser(userEntity);

                if (Objects.isNull(user)) {
                    model.addAttribute("code", 500);
                    model.addAttribute("desc", "无效的链接");
                }else {
                    userEntity.setEmail(null);
                    userDao.fixEmail(userEntity);
                }
            } else {
                model.addAttribute("code", 500);
                model.addAttribute("desc", "无效的链接");
            }
        } else {
            model.addAttribute("code", 500);
            model.addAttribute("desc", "无效的链接");
        }
        return "email-bind";
    }


}
