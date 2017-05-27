package cn.getcube.develop.service.Impl;

import cn.getcube.develop.commons.zookeeper.LockPath;
import cn.getcube.develop.commons.zookeeper.SyncLock;
import cn.getcube.develop.dao.developes.ChatKeFuDao;
import cn.getcube.develop.entity.ChatKeFuEntity;
import cn.getcube.develop.entity.KeFuEntity;
import cn.getcube.develop.service.ChatKeFuService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

/**
 * Created by Administrator on 2017/5/26.
 */
@Service
public class ChatKeFuServiceImpl implements ChatKeFuService {

   /* @Resource
    private ChatKeFuDao chatKeFuDao;

    @Override
    public ChatKeFuEntity findDevice(ChatKeFuEntity chatKeFuEntity) {
        return chatKeFuDao.findDevice(chatKeFuEntity);
    }

    @Override
    public ChatKeFuEntity findOneByLastUseTime() {
        return chatKeFuDao.findOneByLastUseTime();
    }

    @Override
    public void updateCubeLastTime(String cube, long lastUseTime) {
        chatKeFuDao.updateCubeLastTime(cube, lastUseTime);
    }

    @Override
    public void insertDevices(List<ChatKeFuEntity> chatKeFuEntities) {
        chatKeFuDao.insertDevices(chatKeFuEntities);
    }

    @Override
    public void insertDevice(ChatKeFuEntity chatKeFuEntity) {
        chatKeFuDao.insertDevice(chatKeFuEntity);
    }

    @Override
    public void insertKeFu(List<KeFuEntity> keFuEntities) {
        chatKeFuDao.insertKeFu(keFuEntities);
    }

    @Override
    public KeFuEntity findKeFuById(long id) {
        return chatKeFuDao.findKeFuById(id);
    }

    @Override
    public ChatKeFuEntity findOneByDeviceId(String deviceId) {
        return null;
    }

    @Override
    public ChatKeFuEntity findOneByTopLastUseTime() {
        return chatKeFuDao;
    }*/
}
