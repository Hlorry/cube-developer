package cn.getcube.develop.controller;

import cn.getcube.develop.anaotation.TokenVerify;
import cn.getcube.develop.entity.ChatKeFuEntity;
import cn.getcube.develop.entity.KeFuEntity;
import cn.getcube.develop.entity.UserEntity;
import cn.getcube.develop.entity.UserSession;
import cn.getcube.develop.service.ChatKeFuService;
import cn.getcube.develop.service.UserService;
import cn.getcube.develop.storage.ChatKeFuManager;
import cn.getcube.develop.utils.BaseResult;
import cn.getcube.develop.utils.DataResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.data.transaction.ChainedTransactionManager;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@RestController
@Scope("prototype")
@RequestMapping(value = "/auth")
public class ChatKeFuController {

    @Autowired
    ChatKeFuManager chatKeFuManager;

    @PostMapping("/kefu/chat")
    @TokenVerify
    public DataResult<JSONObject> chatKeFu(@RequestParam(name = "token", required = false) String token,
                                           @RequestParam(name = "deviceId", required = true) String deviceId,
                                           UserSession userSession) {
        Integer masterId = 0;
        return chatKeFuManager.chatKeFu(masterId, deviceId);
    }

    @PostMapping("/insertData")
    public DataResult insert() {
        /*List<KeFuEntity> keFuEntities = new ArrayList<>();
        for (int i = 20001; i < 20006; i ++) {
            KeFuEntity keFuEntity = new KeFuEntity(i+"", "客服"+i, "", "", "", new Date(), new Date());
            keFuEntities.add(keFuEntity);
        }
        chatKeFuService.insertKeFu(keFuEntities);*/
       /* List<ChatKeFuEntity> chatKeFuEntities = new ArrayList<>();
        long cur = System.currentTimeMillis();
        System.out.println("start time = " + cur);
        for (int i = 40006; i <= 45000; i++) {
            ChatKeFuEntity chatKeFuEntity = new ChatKeFuEntity(i + "",i + "", "", 0, "");
            chatKeFuEntities.add(chatKeFuEntity);
        }
        chatKeFuManager.insertDevices(chatKeFuEntities);
        System.out.println("end Time  = " + (System.currentTimeMillis() - cur));*/
        /*ChatKeFuEntity chatKeFuEntity = chatKeFuService.findOneByLastUseTime();
        System.out.println("chatKeFuEntity = " + chatKeFuEntity.toString());*/
        return new DataResult(200, "Ok.");
    }
}
