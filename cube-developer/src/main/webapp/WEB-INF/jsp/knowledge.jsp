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
<nav class="navbar navbar-default navbar-fixed-top navbar_bg">
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
                    <li><a href="/route/price">服务</a></li>
                    <li role="presentation" class="dropdown">
	                   <a class="dropdown-toggle" data-toggle="dropdown" href="" 
	                   role="button" aria-haspopup="true" aria-expanded="false">支持</a>
	                   	<ul class="dropdown-menu">
	                   		<li><a href="/route/download">下载</a></li>
	                   		<li><a href="/route/feedback">反馈</a></li>
	                   	</ul>
                   	</li>
                    <%--<li role="presentation" class="dropdown active">--%>
                        <%--<a class="dropdown-toggle" data-toggle="dropdown" href="" role="button"--%>
                           <%--aria-haspopup="true" aria-expanded="false">--%>
                            <%--支持--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="/route/file">文档</a></li>--%>
                            <%--<li><a href="/route/download">下载</a></li>--%>
                            <%--&lt;%&ndash;      <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
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
<div class="container menu_padding ">
    <div class="row menu_padding knowledge_box">
        <div class="col-md-6 col-md-offset-3">
            <div class="input-group">
                <input type="text" class="form-control" placeholder="简单描述一下你的问题,我们帮你找到答案"
                       aria-describedby="basic-addon2">
                <span class="input-group-addon" id="basic-addon2"><img src="../../img/k_search.png"></span>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">Android</button>
                    <ul>
                        <li><a href="">怎样设置接收消息监听？</a></li>
                        <li><a href="">怎样设置聊天界面点击头像事件？</a></li>
                        <li><a href="">怎样能去掉语音通话？</a></li>
                        <li><a href="">如何界面的头像修改为圆形？</a></li>
                        <li><a href="">如何支持地理位置信息消息？</a></li>
                        <li><a href="">如何单击好直接打开聊天的界面？</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">iOS</button>
                    <ul>
                        <li><a href="">怎样设置接收消息监听？</a></li>
                        <li><a href="">怎样设置聊天界面点击头像事件？</a></li>
                        <li><a href="">怎样能去掉语音通话？</a></li>
                        <li><a href="">如何界面的头像修改为圆形？</a></li>
                        <li><a href="">如何支持地理位置信息消息？</a></li>
                        <li><a href="">如何单击好直接打开聊天的界面？</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">Web</button>
                    <ul>
                        <li><a href="">怎样设置接收消息监听？</a></li>
                        <li><a href="">怎样设置聊天界面点击头像事件？</a></li>
                        <li><a href="">怎样能去掉语音通话？</a></li>
                        <li><a href="">如何界面的头像修改为圆形？</a></li>
                        <li><a href="">如何支持地理位置信息消息？</a></li>
                        <li><a href="">如何单击好直接打开聊天的界面？</a></li>
                    </ul>
                </div>
            </div>
            <div class="row">
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">服务端</button>
                    <ul>
                        <li><a href="">怎样设置接收消息监听？</a></li>
                        <li><a href="">怎样设置聊天界面点击头像事件？</a></li>
                        <li><a href="">怎样能去掉语音通话？</a></li>
                        <li><a href="">如何界面的头像修改为圆形？</a></li>
                        <li><a href="">如何支持地理位置信息消息？</a></li>
                        <li><a href="">如何单击好直接打开聊天的界面？</a></li>
                    </ul>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">技术问题</button>
                    <div class="no_list">正在补充...</div>
                </div>
                <div class="col-md-4">
                    <button type="submit" class="btn btn-success btn-block">业务咨询</button>
                    <div class="no_list">正在补充...</div>
                </div>
            </div>
        </div>

    </div>
</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="../../js/file.js"></script>
</body>
</html>