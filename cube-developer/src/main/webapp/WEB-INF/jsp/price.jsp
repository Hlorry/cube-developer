<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
                    <li><a href="/route/product">产品</a></li>
                    <li class="active"><a href="/route/price">服务</a></li>
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
                            <%--&lt;%&ndash;   <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
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
<div class="product_banner price_banner">
    <img src="../../images/price_banner.jpg">
    <div class="flick-inner">
        <div class="flick-content other_img">
            <h3 class="wow zoomIn  animated ">让您每一分钱都物有所值</h3>
            <p>
                <a href="/route/register" class="btn register  wow zoomIn  animated ">注册</a>
            </p>
        </div>
        <div class="flick-content ie_img">
            <h3 class="">让您每一分钱都物有所值</h3>
            <p>
                <a href="/route/register" class="btn register">注册</a>
            </p>
        </div>
    </div>
</div>
<div class="price">
    <div class="container">
        <h1>CubeEngine</h1>
        <table border="1" cellspacing="0" cellpadding="0">
            <thead>
            <tr>
                <td>编号</td>
                <td>功能分类</td>
                <td>标准服务</td>
                <td>高级服务</td>
                <td>企业服务</td>

            </tr>
            </thead>
            <tbody>
            <tr>
                <td rowspan="11">1</td>
                <td rowspan="11" class="table_title">即时聊天</td>
                <td>文本消息</td>
                <td rowspan="11"><img src="../../img/ok.png"></td>
                <td rowspan="11"><img src="../../img/ok.png"></td>

            </tr>
            <tr>
                <td>图片消息</td>
            </tr>
            <tr>
                <td>文件消息</td>
            </tr>
            <tr>
                <td>短语音</td>
            </tr>
            <tr>
                <td>短视频</td>
            </tr>
            <tr>
                <td>阅后即焚</td>
            </tr>
            <tr>
                <td>位置消息</td>
            </tr>
            <tr>
                <td>消息回执</td>
            </tr>
            <tr>
                <td>消息回执</td>
            </tr>
            <tr>
                <td>自定义消息推送内容</td>
            </tr>
            <tr>
                <td>自定义消息推送内容</td>
            </tr>

            <tr>
                <td rowspan="4">2</td>
                <td rowspan="4" class="table_title">音视频通话</td>
                <td>双向视频通话</td>
                <td rowspan="4"><img src="../../img/ok.png"></td>
                <td rowspan="4"><img src="../../img/ok.png"></td>

            </tr>
            <tr>
                <td>双向音频通话</td>
            </tr>
            <tr>
                <td>视频语音记录存储</td>
            </tr>
            <tr>
                <td>视频语音回放</td>
            </tr>
            <tr>
                <td rowspan="3">3</td>
                <td rowspan="3" class="table_title">白板协作</td>
                <td>常用涂鸦工具</td>
                <td rowspan="3"><img src="../../img/ok.png"></td>
                <td rowspan="3"><img src="../../img/ok.png"></td>
            </tr>
            <tr>
                <td>支持多种文件格式分享
                    （文档，图片，表格，幻灯片）
                </td>
            </tr>
            <tr>
                <td>白板记录回放</td>
            </tr>
            <tr>
                <td rowspan="8">4</td>
                <td rowspan="8" class="table_title">会议功能</td>
                <td>会议安排</td>
                <td rowspan="8"><img src="../../img/ok.png"></td>
                <td rowspan="8"><img src="../../img/ok.png"></td>
            </tr>
            <tr>
                <td>加入会议</td>
            </tr>
            <tr>
                <td>会议控制</td>
            </tr>
            <tr>
                <td>会议全程记录</td>
            </tr>
            <tr>
                <td>会议回放</td>
            </tr>
            <tr>
                <td>第三方访客参会</td>

            </tr>
            <tr>
                <td>微信参会</td>
            </tr>
            <tr>
                <td>会议查询</td>
            </tr>
            <tr>
                <td  rowspan="8">5</td>
                <td rowspan="8" class="table_title">群组及好友管理</td>
                <td>查找本地好友</td>
                <td rowspan="8"><img src="../../img/ok.png"></td>
                <td rowspan="8"><img src="../../img/ok.png"></td>

            </tr>
            <tr>
                <td>查找陌生好友</td>
            </tr>
            <tr>
                <td>创建/删除群组</td>
            </tr>
            <tr>
                <td>群组成员管理</td>
            </tr>
            <tr>
                <td>群组成员邀请</td>
            </tr>
            <tr>
                <td>群组公告</td>
            </tr>
            <tr>
                <td>群组文件管理</td>
            </tr>
            <tr>
                <td>群组历史消息查询</td>
            </tr>
            <tr>
                <td rowspan="5">6</td>
                <td rowspan="5" class="table_title">部署整合</td>
                <td>服务器采用集群方式</td>
                <td rowspan="5"><img src="../../img/ok.png"></td>
                <td rowspan="5"><img src="../../img/ok.png"></td>
            </tr>
            <tr>
                <td>服务器采用多级缓冲</td>
            </tr>
            <tr>
                <td>媒体服务器实时转码</td>
            </tr>
            <tr>
                <td>数据传输加密</td>
            </tr>
            <tr>
                <td>数据备份加密</td>
            </tr>
            </tbody>
        </table>
        <%--      <a href="#" class="price_btn wow bounceInDown animated">马上咨询</a>--%>


    </div>
</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript">
    new WOW().init();
</script>
</body>
</html>