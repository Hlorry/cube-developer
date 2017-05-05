package cn.getcube.develop.service.Impl;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.EmailConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.service.UserService;
import cn.getcube.develop.utils.EmailUtils;
import cn.getcube.develop.utils.Md5Helper;
import org.springframework.stereotype.Service;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;


/**
 * Created by SubDong on 2016/3/8.
 */
@Service
public class UserServiceImpl implements UserService {

    @Resource
    private UserDao userDao;

    @Resource
    JedisCluster jc;

    @Override
    public Map<String, Object> addUser(UserEntity userEntity, String uri) {
        Map<String, Object> map = new HashMap<>();

        try {
            userEntity.setCreate_time(new Date());
            userEntity.setUpdate_time(new Date());
            userEntity.setActivation(0);
            userEntity.setBiz_verify(0);
            userEntity.setPhone_verify(0);
            userDao.addUser(userEntity);
            map.put(AuthConstants.CODE, StateCode.Ok);
            map.put("id", String.valueOf(userEntity.getId()));

            //发送Email 验证
            //MD5去重算法生成mail验证
            String md5 = Md5Helper.MD5.getMD5(userEntity.getName());
            jc.set(md5, userEntity.getId() + "");
            jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);
            //发送数据
            EmailUtils.sendHtmlEmail("cube-开发者平台-注册验证", String.format(EmailConstants.registerTemplate, userEntity.getName(), uri + "/auth/activation?actmd5=" + md5), userEntity.getEmail());
            return map;

        } catch (Exception e) {
            e.printStackTrace();
            map.put(AuthConstants.CODE, StateCode.AUTH_ERROR_100);
            map.put(AuthConstants.DESC, "unknown mistake");
            return map;
        }
    }

    @Override
    public UserEntity queryUser(UserEntity userEntity) {
        return userDao.queryUser(userEntity);
    }

    @Override
    public int updateUser(UserEntity userEntity) {
        int updateUser = userDao.updateUser(userEntity);
        return updateUser;
    }

    @Override
    public UserEntity login(UserEntity userEntity) {
        return userDao.login(userEntity);
    }
}
