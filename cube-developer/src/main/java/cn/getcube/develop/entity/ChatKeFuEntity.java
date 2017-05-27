package cn.getcube.develop.entity;

import com.alibaba.fastjson.JSONObject;

/**
 * Created by Administrator on 2017/5/26.
 */
public class ChatKeFuEntity {

    private String id;

    private String deviceCube;

    private String deviceId;

    /**
     * 最后使用时间0表示未使用, lastUserTime 目前用来记录发送聊天的时间
     */
    private long lastUseTime;

    private String keFuCube;

    public ChatKeFuEntity() {
    }

    public ChatKeFuEntity(String id, String deviceCube, String deviceId, long lastUseTime, String keFuCube) {
        this.id = id;
        this.deviceCube = deviceCube;
        this.deviceId = deviceId;
        this.lastUseTime = lastUseTime;
        this.keFuCube = keFuCube;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getDeviceCube() {
        return deviceCube;
    }

    public void setDeviceCube(String deviceCube) {
        this.deviceCube = deviceCube;
    }

    public String getDeviceId() {
        return deviceId;
    }

    public void setDeviceId(String deviceId) {
        this.deviceId = deviceId;
    }

    public long getLastUseTime() {
        return lastUseTime;
    }

    public void setLastUseTime(long lastUseTime) {
        this.lastUseTime = lastUseTime;
    }

    public String getKeFuCube() {
        return keFuCube;
    }

    public void setKeFuCube(String keFuCube) {
        this.keFuCube = keFuCube;
    }

    public JSONObject toJson(){
        JSONObject jsonObject = new JSONObject();
        try {
            jsonObject.put("deviceId", this.deviceId);
            jsonObject.put("deviceCube", this.deviceCube);
            jsonObject.put("lastUseTime", this.lastUseTime);
            jsonObject.put("keFuCube", this.keFuCube);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return jsonObject;
    }

    @Override
    public String toString() {
        return "ChatKeFuEntity{" +
                "id='" + id + '\'' +
                ", deviceCube='" + deviceCube + '\'' +
                ", deviceId='" + deviceId + '\'' +
                ", lastUseTime=" + lastUseTime +
                ", keFuCube='" + keFuCube + '\'' +
                '}';
    }
}
