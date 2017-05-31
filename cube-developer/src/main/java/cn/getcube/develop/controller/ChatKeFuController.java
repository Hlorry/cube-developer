package cn.getcube.develop.controller;

import cn.getcube.develop.entity.UserSession;
import cn.getcube.develop.storage.ChatKeFuManager;
import cn.getcube.develop.utils.DataResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Scope;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

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
    public DataResult<JSONObject> chatKeFu(@RequestParam(name = "token", required = false) String token,
                                           @RequestParam(name = "deviceId", required = true) String deviceId,
                                           UserSession userSession) {
        Integer masterId = 0;
        DataResult<JSONObject> jsonObjectDataResult = chatKeFuManager.chatKeFu(masterId, deviceId);
        JSONObject jsonObject = new JSONObject();
        jsonObject.put("expires", 1507132800000l);
        jsonObject.put("appid", "ac57297238834e7ab7db926b35f811c5");
        jsonObject.put("version", "1.8");
        jsonObject.put("token", "f80925ff7f4a40a0b47dab52a07e241a");
        jsonObjectDataResult.getData().put("license", jsonObject);
        return jsonObjectDataResult;
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
