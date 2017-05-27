package cn.getcube.develop.controller;

import cn.getcube.develop.AuthConstants;
import cn.getcube.develop.StateCode;
import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.utils.BaseResult;
import cn.getcube.develop.utils.DataResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.Map;

/**
 * Created by Rainbow
 *
 * @author SubDong
 * @version V2.0
 *          Copyright (c)2017 shixin-版权所有
 * @since 2017/5/22
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/auth")
public class AuthInvitationController {

    @RequestMapping(value = "/invitation", method = RequestMethod.POST)
    public BaseResult certifiedFind(@RequestParam(name = "token", required = false) String token,
                                    UserEntity userSession) {
        JSONObject mainJson = new JSONObject();
        /*JSONObject license = new JSONObject();
        license.put("expires", 1507132800000l);
        license.put("appid", "ac57297238834e7ab7db926b35f811c5");
        license.put("version", "1.8");
        license.put("token", "f80925ff7f4a40a0b47dab52a07e241a");
        mainJson.put("license", license);
        mainJson.put("expires", 315360000);

        JSONObject inviter = new JSONObject();
        inviter.put("face", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/c43cb794-2656-4ed0-b80d-4ec0dbe248e1");
        inviter.put("largeFace", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/3cb933a1-f319-4fda-aa3c-e96a06577488");
        inviter.put("smallFace", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/62208a93-9ea8-4cb0-a5d7-5558360fd61b");
        inviter.put("role", 1);
        inviter.put("displayName", "yiwen");
        inviter.put("contactId", 42783);
        inviter.put("cube", 201068);
        inviter.put("userId", 204314);

        JSONObject contact = new JSONObject();
        contact.put("mobile", 18658415201l);
        contact.put("email", "346551886@qq.com");

        inviter.put("contact", contact);
        mainJson.put("inviter", inviter);

        JSONObject user = new JSONObject();
        user.put("face", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/0b5fbe32-3f53-4407-a530-d7969fdd7c5b");
        user.put("largeFace", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/0b5fbe32-3f53-4407-a530-d7969fdd7c5b");
        user.put("smallFace", "http://zuobiao-faces.oss-cn-beijing.aliyuncs.com/0b5fbe32-3f53-4407-a530-d7969fdd7c5b");
        user.put("role", 1);
        user.put("contactId", 42783);
        user.put("displayName", "匿名用户");
        user.put("cube", 10070);
        user.put("userId", 10070);
        mainJson.put("user", user);*/
        DataResult<Map<String, Object>> dataResult = new DataResult<>();
        dataResult.setCode(StateCode.Ok.getCode());
        dataResult.setDesc(AuthConstants.MSG_OK);
        dataResult.setData(mainJson);
        return dataResult;
    }
}
