package cn.getcube.develop.storage;

import cn.getcube.develop.StateCode;
import cn.getcube.develop.commons.zookeeper.LockPath;
import cn.getcube.develop.commons.zookeeper.SyncLock;
import cn.getcube.develop.dao.developes.ChatKeFuDao;
import cn.getcube.develop.entity.ChatKeFuEntity;
import cn.getcube.develop.entity.KeFuEntity;
import cn.getcube.develop.utils.DataResult;
import com.alibaba.fastjson.JSONObject;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service
public class ChatKeFuManager {

    @Resource
    private ChatKeFuDao chatKeFuDao;

    public DataResult<JSONObject> chatKeFu(int masterId, String deviceId) {
        SyncLock lock = new SyncLock();
        try {
            lock.acquire(deviceId);
            ChatKeFuEntity chatKeFuEntity = chatKeFuDao.findOneByDeviceId(deviceId);
            if (chatKeFuEntity == null) {
                chatKeFuEntity = this.getChatKeFu(deviceId);
                if (chatKeFuEntity == null) {
                    ChatKeFuEntity chatKeFuReUsed = chatKeFuDao.findOneByTopLastUseTime();
                    chatKeFuEntity = new ChatKeFuEntity();
                    chatKeFuEntity.setDeviceId(deviceId);
                    chatKeFuEntity.setDeviceCube(chatKeFuReUsed.getDeviceCube());
                    chatKeFuEntity.setLastUseTime(System.currentTimeMillis());
                    KeFuEntity keFuEntity = this.getKeFu(chatKeFuReUsed.getDeviceCube());
                    if (keFuEntity != null) {
                        chatKeFuEntity.setKeFuCube(keFuEntity.getCube());
                    }
                    chatKeFuDao.updateChatKeFu(chatKeFuEntity);
                }
            }
            return new DataResult<>(StateCode.Ok, "Ok", chatKeFuEntity.toJson());
        } catch (Exception e) {

        } finally {
            lock.release();
        }
        return new DataResult<>(StateCode.Unknown, "Unknown error.");
    }

    private KeFuEntity getKeFu(String deviceCube) {
        Integer id = Integer.parseInt(deviceCube) % 5 + 1;
        return chatKeFuDao.findKeFuById(id);
    }

    private ChatKeFuEntity getChatKeFu(String deviceId) {
        SyncLock lock = new SyncLock();
        ChatKeFuEntity chatKeFuEntity = null;
        try {
            lock.acquire(LockPath.GET_CUBE);
            chatKeFuEntity = chatKeFuDao.findOneByLastUseTime();
            if(chatKeFuEntity != null) {
                chatKeFuEntity.setLastUseTime(System.currentTimeMillis());
                chatKeFuEntity.setDeviceId(deviceId);
                //获取客服cube
                KeFuEntity keFu = this.getKeFu(chatKeFuEntity.getDeviceCube());
                if (keFu != null)
                    chatKeFuEntity.setKeFuCube(keFu.getCube());
                chatKeFuDao.updateChatKeFu(chatKeFuEntity);
            }
        } catch (Exception e) {
        }finally {
            lock.release();
        }
        return chatKeFuEntity;
    }

    public void insertDevices(List<ChatKeFuEntity> chatKeFuEntities) {
        chatKeFuDao.insertDevices(chatKeFuEntities);
    }
}
