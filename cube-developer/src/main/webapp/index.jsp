<%--
  Created by IntelliJ IDEA.
  User: SubDong
  Date: 2016/3/8
  Time: 20:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="zh-CN">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=yes">
    <title>魔方实时协作云</title>
    <%@ include file="WEB-INF/jsp/public/header.jsp" %>
    <%--<jsp:include page="WEB-INF/jsp/public/header.jsp"></jsp:include>--%>
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
                <a class="navbar-brand" href="#"><span><img src="img/logo.png"></span><span></span></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse navbar-right" aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav">
                    <li class="active"><a href="/">首页</a></li>
                    <li><a href="/route/product">产品</a></li>
                    <li><a href="/route/price">服务</a></li>
                    <!-- <li><a href="/route/download">下载</a></li> -->
                    <li role="presentation" class="dropdown">
	                   <a class="dropdown-toggle" data-toggle="dropdown" href="" 
	                   role="button" aria-haspopup="true" aria-expanded="false">支持</a>
	                   	<ul class="dropdown-menu">
	                   		<li><a href="/route/download">下载</a></li>
	                   		<li><a href="/route/feedback">反馈</a></li>
	                   	</ul>
                   	</li>
                    <%--<li role="presentation" class="dropdown">--%>
                        <%--<a class="dropdown-toggle" data-toggle="dropdown" href="" role="button" id="dLabel"--%>
                           <%--aria-haspopup="true" aria-expanded="false">--%>
                            <%--支持--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu" aria-labelledby="dLabel">--%>
                            <%--<li><a href="/route/file">文档</a></li>--%>
                            <%--<li><a href="/route/download">下载</a></li>--%>
                            <%--&lt;%&ndash;  <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
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
<div id="myCarousel" class="carousel slide" data-ride="carousel">
    <!-- Indicators -->
    <ol class="carousel-indicators">
        <li data-target="#myCarousel" data-slide-to="0" class="active"></li>
        <li data-target="#myCarousel" data-slide-to="1"></li>
        <li data-target="#myCarousel" data-slide-to="2"></li>
    </ol>
    <div class="carousel-inner" role="listbox">
        <div class="item active">
            <img class="first-slide" src="images/banner_first.jpg" alt="First slide">
            <div class="container">
                <div class="carousel-caption ">
                    <h1 class=""><img src="images/banner_text.png"></h1>
                    <%--    <h3 class="">让每一个web、移动端应用更加个性化，更具互动性</h3>--%>
                    <p><a class="btn btn-lg register index_register  " href="/route/register"
                          role="button">注册</a></p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="second-slide" src="images/banner_second.jpg" alt="Second slide">
            <div class="container">
                <div class="carousel-caption carousel-caption-img">
                    <p class=""><img src="images/banner_zhaomu.png"></p>
                    <p><a class="btn btn-lg  register " href="#" role="button" id="abouts">加入我们</a>
                    </p>
                </div>
            </div>
        </div>
        <div class="item">
            <img class="third-slide" src="images/banner_third.jpg" alt="Third slide">
            <div class="container">
                <div class="carousel-caption carousel-caption-img">
                    <p class=""><img src="images/banner_tiyan.png"></p>
                    <p><a class="btn btn-lg register " href="/route/register"
                          role="button">注册</a></p>
                </div>
            </div>
        </div>
    </div>
    <a class="left carousel-control" href="#myCarousel" role="button" data-slide="prev">
        <span class="glyphicon glyphicon-chevron-left" aria-hidden="true"> </span>
        <span class="sr-only">Previous</span>
    </a>
    <a class="right carousel-control" href="#myCarousel" role="button" data-slide="next">
        <span class="glyphicon glyphicon-chevron-right" aria-hidden="true"></span>
        <span class="sr-only">Next</span>
    </a>
</div>
<div class="container product">
    <div class="row">
        <div class="col-xs-6 col-md-3">
            <span><img src="img/chat_only.jpg"></span>
            <h3>单聊</h3>
            <p>发语音，图片，位置，附件等可扩展自定义消息类型</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span><img src="img/chat_all.jpg"></span>
            <h3>群聊</h3>
            <p>支持500人到2000人大群完善的群组权限管理</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span><img src="img/black.jpg"></span>
            <h3>多功能白板</h3>
            <p>方便快捷的实现文档，图片共享，支持自由涂鸦</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span><img src="img/video.jpg"></span>
            <h3>实时音视频通话</h3>
            <p>声音清晰、画面流畅、带宽自适应的高清音视频</p>
        </div>
    </div>
    <div class="row">
        <div class="col-xs-6 col-md-3">
            <span><img src="img/huiyi.jpg"></span>
            <h3>超级会议</h3>
            <p>机场 高铁 的士 巴士 立即多方音视频通话、文件分享</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span> <img src="img/wendang.jpg"></span>
            <h3>云文档管理</h3>
            <p>集中云存储、权限管理、移动办公、协同与分享、备份与恢复</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span><img src="img/shipin.jpg"></span>
            <h3>视频直播</h3>
            <p>可以同时承载百万级用户同时在线的大型直播业务</p>
        </div>
        <div class="col-xs-6 col-md-3">
            <span><img src="img/pingtai.jpg"></span>
            <h3>多平台支持</h3>
            <p>Web、iOS、 Android、操作系统</p>
        </div>
    </div>
</div>
<div class="container">
    <div class="row featurette">
        <div class="col-md-12">
            <h2 class="featurette-heading">海量迸发的强劲性能</h2>
            <div class="col-md-8 col-md-offset-3">
                <p class="lead"><span class="point"></span> 无状态服务集群，服务、数据、网络均可动态伸缩，无限扩展，重新定义高性能。</p>
                <p class="lead" style="margin-top:20px;"><span class="point"></span>
                    多点接入，整体接入网络优化，优质的用户体验全面提升，不限制用户数量、不限制存储空间.</p>
            </div>

        </div>
        <div class="col-md-12">
            <img class="featurette-image img-responsive center-block wow pulse animated other_img"
                 src="img/introduce.jpg"
                 alt="Generic placeholder image">
            <img class="featurette-image img-responsive center-block ie_img" src="img/introduce.jpg"
                 alt="Generic placeholder image">
        </div>
    </div>
</div>
<div class="container index_last">
    <div class="row featurette">
        <div class="col-md-12">
            <h2 class="featurette-heading">针对移动设备深度优化</h2>
            <div class="col-md-8 col-md-offset-3">
                <p class="lead"><span class="point"></span> 通讯协议为移动网络深度优化，省电，省流量，弱网络环境下依然保证消息必达,支持各种硬件。</p>
                <p class="lead" style="margin-top:20px;"><span class="point"></span>
                    在Android平台上保持长连接，实现消息推送；在iOS平台完美对接APNS。</p>
            </div>
        </div>
        <div class="col-md-12">
            <img class="featurette-image img-responsive center-block  wow pulse animated other_img"
                 src="img/introduce_next.jpg"
                 alt="Generic placeholder image">
            <img class="featurette-image img-responsive center-block ie_img" src="img/introduce_next.jpg"
                 alt="Generic placeholder image">

        </div>
    </div>
</div>
<jsp:include page="WEB-INF/jsp/public/footer.jsp"></jsp:include>
<script type="text/javascript" src="js/index.js"></script>
<script type="text/javascript">
    new WOW().init();

</script>
</body>
</html>