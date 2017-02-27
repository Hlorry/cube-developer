package cn.getcube.develop.constants;

/**
 * Created by Administrator on 2016/4/11.
 */
public interface AppConstants {
    /**
     * 返回code信息
     */
    String CODE_200 = "200";
    String CODE_400 = "400";
    String CODE_404 = "404";

    /**
     *
     */
    String HTTP_CODE = "status";
    String HTTP_MSG = "message";
    String HTTP_DATA = "data";

    /**
     * 创建应用默认启用服务
     *
     * IM -> IM消息
     * audio -> 音频呼叫
     * video -> 视频呼叫
     * whiteboard -> 白板服务
     * groups -> 群组服务
     * live -> 直播服务
     * meet -> 会议服务
     */
    String USESERVING_JSON = "{\"IM\":true,\"audio\":true,\"video\":true,\"whiteboard\":true,\"groups\":true,\"live\":true,\"meet\":true}";

}
