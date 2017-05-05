package cn.getcube.develop.utils;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.EmailConstants;
import cn.getcube.develop.HttpUriCode;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.dao.developes.UserDao;
import cn.getcube.develop.entity.UserEntity;
import redis.clients.jedis.JedisCluster;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/5
 */
public class MessageUtils {
    private final static MessageUtils instance = new MessageUtils();
    @Resource
    private UserDao userDao;

    public final static MessageUtils getInstance() {
        return MessageUtils.instance;
    }

    public boolean sendEmailOrPhone(JedisCluster jc, String emailOrPhone) {
        UserEntity userEntity = new UserEntity();
        if (emailOrPhone.contains("@")) {
            userEntity.setEmail(emailOrPhone);
            UserEntity user = userDao.queryUser(userEntity);
            if (Objects.nonNull(user)) {
                //发送Email 验证
                //MD5去重算法生成mail验证
                String md5 = Md5Helper.MD5.getMD5(user.getName());
                jc.set(md5, user.getId() + "");
                jc.expire(md5, AuthConstants.AUTH_TOKEN_FAIL_TIME);
                //发送数据
                EmailUtils.sendHtmlEmail("cube-开发者平台-注册验证", String.format(EmailConstants.registerTemplate, user.getName(), HttpUriCode.HTTP_CODE_URI + "/auth/activation?actmd5=" + md5), user.getEmail());
                return true;
            } else {
                return false;
            }
        } else {
            SendMSMUtils.postRequest(userEntity.getPhone(), null);
            return true;
        }
    }
}
