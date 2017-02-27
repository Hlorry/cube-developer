<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<style>
h1,
h2,
h3,
h4,
h5,
h6,
p,
blockquote {
    margin: 0;
    padding: 0;
}
table {
	margin: 10px 0 15px 0;
	border-collapse: collapse;
}
td,th {	
	border: 1px solid #ddd;
	padding: 3px 10px;
}
th {
	padding: 5px 10px;	
}

a {
    color: #0069d6;
}
a:hover {
    color: #0050a3;
    text-decoration: none;
}
a img {
    border: none;
}
p {
    margin-bottom: 9px;
}
h1,
h2,
h3,
h4,
h5,
h6 {
    color: #404040;
    line-height: 36px;
}
h1 {
    margin-bottom: 18px;
    font-size: 30px;
}
h2 {
    font-size: 24px;
}
h3 {
    font-size: 18px;
}
h4 {
    font-size: 16px;
}
h5 {
    font-size: 14px;
}
h6 {
    font-size: 13px;
}
hr {
    margin: 0 0 19px;
    border: 0;
    border-bottom: 1px solid #ccc;
}
blockquote {
    padding: 13px 13px 21px 15px;
    margin-bottom: 18px;
    font-family:georgia,serif;
    font-style: italic;
}
blockquote:before {
    content:"\201C";
    font-size:40px;
    margin-left:-10px;
    font-family:georgia,serif;
    color:#eee;
}
blockquote p {
    font-size: 14px;
    font-weight: 300;
    line-height: 18px;
    margin-bottom: 0;
    font-style: italic;
}
code, pre {
    font-family: Monaco, Andale Mono, Courier New, monospace;
}
code {
    background-color: #fee9cc;
    color: rgba(0, 0, 0, 0.75);
    padding: 1px 3px;
    font-size: 12px;
    -webkit-border-radius: 3px;
    -moz-border-radius: 3px;
    border-radius: 3px;
}
pre {
    display: block;
    padding: 14px;
    margin: 0 0 18px;
    line-height: 16px;
    font-size: 11px;
    border: 1px solid #d9d9d9;
    white-space: pre-wrap;
    word-wrap: break-word;
}
pre code {
    background-color: #fff;
    color:#737373;
    font-size: 11px;
    padding: 0;
}
sup {
    font-size: 0.83em;
    vertical-align: super;
    line-height: 0;
}
* {
	-webkit-print-color-adjust: exact;
}

#tab_box .file_boxs h3{
    border-bottom: inherit;
}
@media print {
	body,code,pre code,h1,h2,h3,h4,h5,h6 {
		color: black;
	}
	table, pre {
		page-break-inside: avoid;
	}
}
</style>
<div style=" overflow: hidden" class="file_boxs">
    <h1>魔方引擎（CubeEngine）Android SDK 介绍</h1>

<pre><code>魔方引擎SDK为用户开发远程教育，远程培训，即时会行业应用提供了一套稳定的开发组件。
    魔方引擎SDK主要包含以下几个模块：
</code></pre>

    <h2>魔方引擎核心框架</h2>

<pre><code>魔方引擎核心框架 cube-engine-*.jar 基础框架，集成必备。
    魔方引擎网络框架 cube-cellcloud-*.jar 基础框架，集成必备。
    魔方引擎音视频框架 cube-rtc-*.jar libcube_rtc.so 音视频通信模块，集成（可选）。
    魔方引擎信令框架 cube-sip-*.jar 群组音视频聊天模块，集成（可选）。
    魔方引擎直播框架 cube-live-*.jar 直播功能模块，集成（可选）。
    魔方引擎白板框架 cube-whiteboard-*.ast 白板功能模块，集成（可选）。
</code></pre>

    <h2>魔方引擎 SDK 主要涵盖以下核心功能</h2>

    <h3>1.即时消息</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 文本 </td>
            <td> 可以发送任意字符文字，一次最多可发送52428800（个汉字）。 </td>
        </tr>
        <tr>
            <td> 图片 </td>
            <td> 可以发送任意格式图片，图片大小不能超过10M每张。 </td>
        </tr>
        <tr>
            <td> 短语音 </td>
            <td> 可使用引擎API，录制短语音，每条短语音消息时长不能超过1分钟。 </td>
        </tr>
        <tr>
            <td> 短视频 </td>
            <td> 可使用引擎API，录制短视频，每条短视频消息时长不能超过1分钟。 </td>
        </tr>
        <tr>
            <td> 自定义消息 </td>
            <td> 可按自己需求定制自己的消息内容和数据格式。 </td>
        </tr>
        <tr>
            <td> 地理位置 </td>
            <td> 设备硬件支持的情况下可发送当前位置消失。 </td>
        </tr>
        </tbody>
    </table>


    <h3>2.音视频通信</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 实时语音通信 </td>
            <td> 同时支持P2P和服务器转发的语音聊天，默认采用P2P通信，可降低私有云服务器成本。 </td>
        </tr>
        <tr>
            <td> 实时视频通信 </td>
            <td> 同时支持P2P和服务器转发的视频聊天，默认采用P2P通信，可降低私有云服务器成本。 </td>
        </tr>
        </tbody>
    </table>


    <h3>3.群组</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 群组即时消息 </td>
            <td> 最大可支持一千人的群组聊天功能。可已操作群组成员，更新群组信息等。 </td>
        </tr>
        <tr>
            <td> 群组实时音视频 </td>
            <td> 支持多人群组实时音视频聊天。 </td>
        </tr>
        </tbody>
    </table>


    <h3>4.会议</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 高清视频会议 </td>
            <td> 支持多人视频，音频，以及手机接入会议。 </td>
        </tr>
        <tr>
            <td> 会议控制 </td>
            <td> 支持主讲人切换，静音，禁视，踢人等完整会控功能 。</td>
        </tr>
        <tr>
            <td> 实时会议记录 </td>
            <td> 支持会议录制，会议数据自定义存储等功能 。</td>
        </tr>
        </tbody>
    </table>


    <h3>5.白板</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 白板笔记 </td>
            <td> 可以制作白板笔记，文档标注，本地涂鸦，保存至服务器等。 </td>
        </tr>
        <tr>
            <td> 白板共享 </td>
            <td> 可共享Office文档，PDF文件等，可实时标注文档信息，可按时间回放白板记录。 </td>
        </tr>
        </tbody>
    </table>


    <h3>6.直播</h3>

    <table>
        <thead>
        <tr>
            <th> 功能 </th>
            <th> 描述 </th>
        </tr>
        </thead>
        <tbody>
        <tr>
            <td> 音频直播 </td>
            <td> 开发中... </td>
        </tr>
        <tr>
            <td> 视频直播 </td>
            <td> 开发中... </td>
        </tr>
        </tbody>
    </table>


    <h2>魔方引擎 SDK 集成</h2>

<pre><code>1.集成SDK前，请到http://www.getcube.cn/注册账号，或者下载试用的cube.license授权文件。
    2.下载魔方引擎试用Demo项目，CubeWareDemo，并且导入eclipse开发工具中等到如下可运行项目，请注意选择5.0以上Android编译环境。
</code></pre>

    <h2>魔方引擎 SDK API 介绍</h2>

    <h3>启动引擎</h3>

<pre><code>配置引擎相关参数
    CubeConfig config = new CubeConfig();
    config.setVideoSize(VideoSize.VGA);//视频通话分辨率，如有需要则配置。
    config.setResourceDir(Environment.getExternalStorageDirectory() + "/test/demo");//配置文件消息存放路径
    CubeEngine.getInstance().setCubeConfig(config);

    启动引擎
    CubeEngine.getInstance().startup(this);

    停止引擎
    CubeEngine.getInstance().shutdown();
</code></pre>

    <h3>注册模块</h3>

<pre><code>引擎注册
    设置注册监听器
    CubeEngine.getInstance().addRegistrationListener(new RegistrationListener());
    CubeEngine.getInstance().register("cubeId", "pwd", "nickName");

    引擎注销
    CubeEngine.getInstance().unregister();
</code></pre>

    <h3>即时消息模块</h3>

<pre><code>设置消息监听器
    CubeEngine.getInstance().getMessageService().addMessageListener(new MessageListener());

    发送文本消息
    TextMessage message = new TextMessage(content);
    message.setReceiver(toChatCubeId);
    CubeEngine.getInstance().getMessageService().sendMessage(message);

    发送图片消息
    File image = new File(imagePath);
    ImageMessage imageMessage = new ImageMessage(image);
    imageMessage.setReceiver(toChatCubeId);
    CubeEngine.getInstance().getMessageService().sendMessage(imageMessage);

    发送文件消息
    File file = new File(filePath);
    FileMessage fileMessage = new FileMessage(file);
    fileMessage.setReceiver(toChatCubeId);
    CubeEngine.getInstance().getMessageService().sendMessage(fileMessage);

    发送语音消息
    CubeEngine.getInstance().getMediaService().startVoiceClipRecording(null);//开始录音
    VoiceClipMessage vcm = CubeEngine.getInstance().getMediaService().stopVoiceClipRecording();//停止录音
    vcm.setReceiver(toChatCubeId);
    CubeEngine.getInstance().getMessageService().sendMessage(vcm);
    CubeEngine.getInstance().getMediaService().discardVoiceClipRecording();//如果不需要，可以丢弃录音

    发送视频消息
    CubeEngine.getInstance().getMediaService().displayVideoClipRecording(surfaceView, CameraInfo.CAMERA_FACING_BACK);//配置预览画面，和使用摄像头
    CubeEngine.getInstance().getMediaService().startVideoClipRecording(null);//开始录制
    VideoClipMessage vcm = CubeEngine.getInstance().getMediaService().stopVideoClipRecording();//停止录制
    vcm.setReceiver(toChatCubeId);
    CubeEngine.getInstance().getMessageService().sendMessage(vcm);

    发送自定义消息
    CustomMessage message = new CustomMessage(content);
    message.setReceiver(toChatCubeId);
    message.addAttribute("key1", "value1");
    message.addAttribute("key2", "value2");
    CubeEngine.getInstance().getMessageService().sendMessage(message);
</code></pre>

    <h3>音视频模块</h3>

<pre><code>设置呼叫监听器
    CubeEngine.getInstance().getCallService().addCallListener(new CallListener());

    音频呼叫
    CubeEngine.getInstance().getCallService().makeCall(toChatCubeId, false);

    视频呼叫
    CubeEngine.getInstance().getCallService().makeCall(toChatCubeId, true);

    本地视频画面
    CubeEngine.getInstance().getCallService().getLocalView();

    远端视频画面
    CubeEngine.getInstance().getCallService().getRemoteView();

    来电接听
    CubeEngine.getInstance().getCallService().answerCall();

    挂断或取消呼叫
    CubeEngine.getInstance().getCallService().terminateCall();

    音视频媒体操作
    CubeEngine.getInstance().getMediaService().setVideoEnabled(false);//开启或关闭视频，视频通话时可用
    CubeEngine.getInstance().getMediaService().setAudioEnabled(false);//开启或关闭生意，通话时可用
    CubeEngine.getInstance().getMediaService().openSpeaker();//打开扬声器
    CubeEngine.getInstance().getMediaService().closeSpeaker();//关闭扬声器
    CubeEngine.getInstance().getMediaService().switchCamera();//切换摄像头，视频通话时可用
</code></pre>

    <h3>群组模块</h3>

<pre><code>设置群组监听器
    CubeEngine.getInstance().getGroupService().addGroupListener(new GroupListener());

    创建群组
    List&lt;String&gt; cubeIds = new ArrayList&lt;String&gt;();
    CubeEngine.getInstance().getGroupService().createGroup("groupName", cubeIds);

    删除群组
    CubeEngine.getInstance().getGroupService().deleteGroup("groupId");

    添加成员
    List&lt;String&gt; cubeIds = new ArrayList&lt;String&gt;();
    CubeEngine.getInstance().getGroupService().addMembers("groupId", cubeIds);

    删除成员
    List&lt;String&gt; cubeIds = new ArrayList&lt;String&gt;();
    CubeEngine.getInstance().getGroupService().removeMembers("groupId", cubeIds);

    查询所有加入的群组
    CubeEngine.getInstance().getGroupService().queryGroups(new GroupQueryListener());

    查询单个群组详情
    CubeEngine.getInstance().getGroupService().queryGroupDetails("groupId", new GroupDetailsListener());
</code></pre>

    <h3>会议模块</h3>

<pre><code>设置会议监听器
    CubeEngine.getInstance().getConferenceService().addConferenceListener(new ConferenceListener());

    申请会议
    CubeEngine.getInstance().getConferenceService().applyConference("groupId", 2 * 60 * 60);//申请会议必须先创建组，填入组号及会议时长

    关闭会议
    CubeEngine.getInstance().getConferenceService().closeConference("groupId");//关闭会议

    会控
    CubeEngine.getInstance().getConferenceService().changeFloor("conferenceNumber", "cubeId");//切换主讲人
    CubeEngine.getInstance().getConferenceService().setMemberDeaf("conferenceNumber", "cubeId", true);//禁听
    CubeEngine.getInstance().getConferenceService().setMemberMute("conferenceNumber", "cubeId", true);//禁音
    CubeEngine.getInstance().getConferenceService().setMemberVideo("conferenceNumber", "cubeId", true);//禁视
    CubeEngine.getInstance().getConferenceService().kick("conferenceNumber", "cubeId");//踢人
</code></pre>

    <h3>白板模块</h3>

<pre><code>设置白板监听器
    CubeEngine.getInstance().getWhiteboardService().addWhiteboardListener(new WhiteboardListener());

    创建白板
    CubeEngine.getInstance().getWhiteboardService().createView(this);

    销毁白板
    CubeEngine.getInstance().getWhiteboardService().destroyView();

    获取白板
    View whiteboard = CubeEngine.getInstance().getWhiteboardService().getView();//先创建，才能获取白板

    白板分享文档
    CubeEngine.getInstance().getWhiteboardService().shareFile("filePath");

    白板共享画面
    CubeEngine.getInstance().getWhiteboardService().shareWith("cubeId");//共享
    CubeEngine.getInstance().getWhiteboardService().revokeSharing();//停止共享

    白板简单操作
    CubeEngine.getInstance().getWhiteboardService().zoom("float");//放大缩小
    CubeEngine.getInstance().getWhiteboardService().cleanup();//清空
    CubeEngine.getInstance().getWhiteboardService().erase();//擦除所有标注
    CubeEngine.getInstance().getWhiteboardService().prevPage();//文档上一页
    CubeEngine.getInstance().getWhiteboardService().nextPage();//文档下一页
</code></pre>

    <h3>直播模块</h3>

<pre><code>研发中...
</code></pre>

    <p>以上为简单API操作指导，更详细类容请查阅相关开发文档。</p>

</div>
