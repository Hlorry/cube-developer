<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=yes">
    <title>魔方实时协作云</title>
    <%@ include file="public/header.jsp" %>
    <%--<jsp:include page="public/header.jsp"></jsp:include>--%>
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top">
    <div class="ie_header">
        <div class="container nav_header">
            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="/"><span><img src="../../img/logo.png"></span></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse navbar-right" aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav">
                    <li><a href="/">首页</a></li>
                    <li class="active"><a href="/route/product">产品</a></li>
                    <li><a href="/route/price">服务</a></li>
                    <li role="presentation" class="dropdown">
	                   <a class="dropdown-toggle" data-toggle="dropdown" href="" 
	                   role="button" aria-haspopup="true" aria-expanded="false">支持</a>
	                   	<ul class="dropdown-menu">
	                   		<li><a href="/route/download">下载</a></li>
	                   		<li><a href="/route/feedback">反馈</a></li>
	                   	</ul>
                   	</li>
                    <%--<li role="presentation" class="dropdown">--%>
                        <%--<a class="dropdown-toggle" data-toggle="dropdown" href="" role="button"--%>
                           <%--aria-haspopup="true" aria-expanded="false">--%>
                            <%--支持--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="/route/file">文档</a></li>--%>
                            <%--<li><a href="/route/download">下载</a></li>--%>
                            <%--&lt;%&ndash; <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
                <%if (token == null || token == "") {%>
                <form class="navbar-form fr">
                    <a href="/route/login">登入</a>
                    <a href="/route/register">注册</a>
                </form>
                <%
                } else {
                %>
                <form class="navbar-form fr">
                    <a href="/app" class="navbar_form_ctr">我的控制台</a>
                    <a href="/user/logout">退出</a>
                </form>
                <%}%>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</nav>
<div class="product_banner">
    <img src="../../images/product_banner.jpg">
    <div class="flick-inner">
        <div class="flick-content other_img">
            <h3 class="wow zoomIn  animated">稳定高效</h3>
            <div class="flick-sub-text wow zoomIn  animated">技术的自信使我们敢于坦诚相见</div>
            <p class="product_btn">
                <a href="/route/file" class="btn register wow zoomIn " data-wow-iteration="1">文档</a>
                <a href="/route/register" class="btn register wow zoomIn " data-wow-iteration="1">注册</a>
                <a href="/route/download" class="btn register wow zoomIn " data-wow-iteration="1">SDK下载</a>
            </p>
        </div>
        <div class="flick-content ie_img">
            <h3>稳定高效</h3>
            <div class="flick-sub-text">技术的自信使我们敢于坦诚相见</div>
            <p class="product_btn">
                <a href="/route/file" class="btn register wow zoomIn " data-wow-iteration="1">文档</a>
                <a href="/route/register" class="btn register wow zoomIn " data-wow-iteration="1">注册</a>
                <a href="/route/download" class="btn register wow zoomIn " data-wow-iteration="1">SDK下载</a>
            </p>
        </div>
    </div>
</div>
<div class="introduce introduce_next introduce_point">
    <div class="container ">
        <div class="row featurette">
            <div class="col-md-5 ">
                <h2 class="featurette-heading">强大的IM工具</h2>
                <p class="lead text-left">即时消息：文字，图片，文件，短语音，短视频，位置消息，
                    自定义消息</p>
                <p class="lead text-left"> 500~2000人大型群组，可实现单聊所有功能，群成员管理。</p>
                <p><img src="../../images/product_part1.png"></p>
            </div>
            <div class="col-md-7" data-wow-delay="1s">
                <img class="featurette-image img-responsive center-block" src="../../images/IM.png"
                     alt="Generic placeholder image">
            </div>
        </div>

    </div>
</div>
<div class="introduce introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5 product_right">
                <h2 class="featurette-heading">多功能白板分享</h2>
                <p class="lead text-left">方便快捷的实现文档（Word、PPT、Excel、PDF），图
                    片共享，支持自由涂鸦。</p>
                <p class="lead text-left">实时记录文档标注，可按时间查看和回放白板记录。</p>
                <p><img src="../../images/black_detail.png"></p>
            </div>
            <div class="col-md-7 ">
                <img class="featurette-image img-responsive center-block" src="../../images/product_black.png"
                     alt="Generic placeholder image">
            </div>
        </div>

    </div>
</div>
<div class="introduce introduce_next introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5">
                <h2 class="featurette-heading">实时音视频通话</h2>
                <p class="lead text-left">快速连通，高清视频画面，低延时，防抖动，防丢帧，默
                    认采用P2P通信，大大降低私有云服务器成本。</p>
                <p class="lead text-left"> 无需特殊启动程序，自动调用摄像头。</p>
                <p class="lead text-left">画面更清晰更流畅，音视频保证同步。</p>
            </div>
            <div class="col-md-7">
                <img class="featurette-image img-responsive center-block" src="../../images/product_video.png"
                     alt="Generic placeholder image">
            </div>
        </div>

    </div>
</div>
<div class="introduce introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5 product_right">
                <h2 class="featurette-heading">超级会议</h2>
                <p class="lead text-left">高效会议，随时随地一键发起一个会议。</p>
                <p class="lead text-left">无需账号，也可一键参与正在进行中的会议。</p>
                <p class="lead text-left"> 超大容量，可同时容纳1000人参加会议。</p>
                <p class="lead text-left">具备完善的会议控制功能，如：禁音，禁视，踢人，邀
                    请 ，等细节的会议控制及权限控制能力。</p>
            </div>
            <div class="col-md-7">
                <img class="featurette-image img-responsive center-block" src="../../images/meeting.png"
                     alt="Generic placeholder image">
            </div>

        </div>

    </div>
</div>
<div class="introduce introduce_next introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5">
                <h2 class="featurette-heading">云文档管理</h2>
                <p class="lead text-left"> 所有聊天记录，文件数据，白板文件，会议记录等相关数据，均永久且加密存放在云端，同时支持私有云和公有云，永不丢失，可在任意终端同步任意数据。</p>
            </div>
            <div class="col-md-7">
                <img class="featurette-image img-responsive center-block" src="../../images/product_flie.png"
                     alt="Generic placeholder image">
            </div>
        </div>

    </div>
</div>
<div class="introduce introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5 product_right">
                <h2 class="featurette-heading">视频直播</h2>
                <p class="lead text-left"> 提供稳定流畅、低延时、高并发的在线直播平台。</p>
                <p class="lead text-left"> 支持HTTP、RTMP协议，HLS协议的M3U8文件。</p>
                <p class="lead text-left">
                    支持软硬解码，实时监控全链路网络情况，自适应调整直播流。可管理直播频道，并提供多维度统计数据，实时查看直
                    播流量使用情况。</p>
            </div>
            <div class="col-md-7">
                <img class="featurette-image img-responsive center-block" src="../../images/online.png"
                     alt="Generic placeholder image">
            </div>

        </div>

    </div>
</div>
<div class="introduce introduce_next introduce_point">
    <div class="container">
        <div class="row featurette">
            <div class="col-md-5">
                <h2 class="featurette-heading">多平台支持</h2>
                <p class="lead text-left"> 跨平台，跨终端，可使用Web,iOS,Android,Windows,Mac等。
                    PC与手机设备同步登陆以及信息同步。在任何一个设备上处理
                    消息，其他设备也都全部同步。支持企业通讯录，方便找人。</p>
            </div>
            <div class="col-md-7">
                <img class="featurette-image img-responsive center-block" src="../../images/support.png"
                     alt="Generic placeholder image">
            </div>

        </div>

    </div>
</div>
<div class="product_banner introduce_point ">
    <img src="../../images/product_banner_next.jpg">
    <div class="flick-inner">
        <div class="flick-content other_img">
            <h3 class="wow slideInLeft">即刻成为时信开发者，让未来大有所为</h3><br>
            <p>
                <a href="/route/register" class="btn register wow zoomIn " data-wow-iteration="1">注册</a>
            </p>
        </div>
        <div class="flick-content ie_img">
            <h3>即刻成为时信开发者，让未来大有所为</h3><br>
            <p>
                <a href="/route/register" class="btn register" data-wow-iteration="1">注册</a>
            </p>
        </div>
    </div>
</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript">
    new WOW().init();
</script>
</body>
</html>