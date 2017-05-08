package cn.getcube.develop.controller;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.utils.BaseResult;
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
     * @param token
     * @return
     */
    @RequestMapping(value = "/password/activation", method = RequestMethod.GET)
    public String pwdActivation(@RequestParam(name = "actmd5", required = true) String actmd5,
                                @RequestParam(name = "token", required = false) String token,
                                Model model) {
        if (Objects.nonNull(actmd5)) {
            String value = jc.get(actmd5);
            if (value != null) {
                model.addAttribute("id", value);
                model.addAttribute("code", 200);
                UserEntity userEntity = new UserEntity();
                userEntity.setId(Integer.valueOf(value));
                UserEntity user = userDao.queryUser(userEntity);
                if (Objects.isNull(user)) {
                    model.addAttribute("code", 500);
                }
            } else {
                model.addAttribute("code", 500);
            }
        } else {
            model.addAttribute("code", 500);
        }
        return "password-new";
    }
}
