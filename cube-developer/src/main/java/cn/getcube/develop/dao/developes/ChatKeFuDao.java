package cn.getcube.develop.dao.developes;

import cn.getcube.develop.entity.ChatKeFuEntity;
import cn.getcube.develop.entity.KeFuEntity;
import cn.getcube.develop.storage.ChatKeFuManager;

import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
public interface ChatKeFuDao {

    void insertDevices(List<ChatKeFuEntity> list);
    void insertDevice(ChatKeFuEntity chatKeFuEntity);
    void insertKeFu(List<KeFuEntity> list);

    ChatKeFuEntity findOneByLastUseTime();

    void updateChatKeFu(ChatKeFuEntity chatKeFuEntity);

    KeFuEntity findKeFuById(Integer id);

    KeFuEntity findKeFuByCube(Integer cube);

    ChatKeFuEntity findOneByDeviceId(String deviceId);

    ChatKeFuEntity findOneByTopLastUseTime();
}
