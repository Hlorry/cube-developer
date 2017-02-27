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
    <div class="ie_header ie_navbar_bg">
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
                            <%--&lt;%&ndash;           <li><a href="/route/ knowledge">知识库</a></li>&ndash;%&gt;--%>
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

<div class=" container file_tab menu_padding login_page">
    <div class="row" id="tab">

        <div class="col-sm-3">
            <div id="tab_menu">
                <button type="button" class="btn btn-default btn-block" id="fileBlock">
                    文档列表
                </button>
                <div class="file_box" id="file_box">
                    <div class="file_list">
                        <h3 class="list_title list_icon">新手上路</h3>
                        <ul>
                            <li class="selected">开发者及后台管理</li>
                            <li>Android SDK介绍</li>
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">服务器集成（REST API）</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">Android客户端集成</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">IOS客户端集成</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">Linux SDK 集成</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">Web IM 集成</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">常见错误码</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">常见集成方案</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                    <div class="file_list">
                        <h3 class="list_title">帮助</h3>
                        <ul class="tab_hide">
                            <li>用户和好友体系集成</li>
                            <li>通信过程及聊天记录保存</li>
                            <li>客户端集成</li>
                            <li>实时消息回调</li>
                        </ul>
                    </div>
                </div>

                <div>
                </div>
            </div>

        </div>
        <div class="col-sm-8">
            <div id="tab_box" class="text-left">
                <div>
                    <h3>开发者及后台管理</h3>
                    <p>时信服务器端REST平台概述</p>
                    <p>关于REST</p>
                    <p>
                        REST（Representational State Transfer）是一种轻量级的Web
                        Service架构风格,可以翻译成“表述性状态转移”，实现和操作明显比SOAP和XML-RPC更为简洁，可以完全通过HTTP协议实现，还可以利用缓存Cache来提高响应速度，性能、效率和易用性上都优于SOAP协议.</p>
                    <p>
                        REST架构遵循了CRUD原则，CRUD原则对于资源只需要四种行为：Create（创建）、Read（读取）、Update（更新）和Delete（删除）就可以完成对其操作和处理.这四个操作是一种原子操作，对资源的操作包括获取、创建、修改和删除资源的操作正好对应HTTP协议提供的GET、POST、PUT和DELETE方法，因此REST把HTTP对一个URL资源的操作限制在GET、POST、PUT和DELETE这四个之内.这种针对网络应用的设计和开发方式，可以降低开发的复杂性，提高系统的可伸缩性.</p>
                    <p>更多REST API背景知识可以参考这篇RESTful API 设计指南</p>
                    <p>REST平台体系</p>
                    <p>
                        平台提供的是一个多租户用户体系，资源以集合【Collection】的形式来描述,这里所说的Collection包括DataBase、企业(orgs)、应用(apps)、IM用户(users)、群组(chatgroups)、消息(chatmessages)、文件(chatfiles)等等，之间的包含关系是</p>
                    <p>
                        DB = {org1, org2, …} org = {app1, app2, …} app = {users, messages, chatfiles, chatmessages,
                        chatgroups, …} users = {user1, user2, …} messages = {message1, message2, …} chatfiles =
                        {chatfile1, chatfile2, …} chatmessages = {chatmessage1, chatmessage2, …} chatgroups = {group1,
                        group2, …} 多租户是指软件架构支持一个</p>
                    <p>实例服务多个用户（Customer），每一个用户被称之为租户（Tenant），软件给予租户可以对系统进行部分定制的能力，如用户界面颜色或业务规则，但是他们不能定制修改软件的代码.</p>
                    <p>
                        其实在云计算领域，多租户的含义已被扩展.例如，软件即服务（SaaS）提供者，利用运行在一个数据库实例上的应用系统，向多个用户提供Web访问服务.在这样的场景下，租户之间的数据是隔离的，并且保证每个用户的数据对其他租户不可见.</p>
                    <p>在时信服务体系中，不同org之间的用户数据相互隔离，同一个org下不同app之间的用户数据相互隔离.</p>
                    <p>REST Server</p>
                    <p>时信的服务器端接口都是通过REST服务方式提供的, REST API基于最简单的HTTP协议, 在各个编程语言中都提供了良好的支持.</p>
                    <p>REST client</p>
                </div>
                <div class="tab_hide">
                    <h3>Android SDK介绍</h3>
                    <jsp:include page="androidsdk.jsp"></jsp:include>
                </div>
                <div class="tab_hide">
                    <h3>用户和好友体系集成</h3>
                    <p>时信服务器端REST平台概述</p>
                    <p>关于REST</p>
                    <p>
                        REST（Representational State Transfer）是一种轻量级的Web
                        Service架构风格,可以翻译成“表述性状态转移”，实现和操作明显比SOAP和XML-RPC更为简洁，可以完全通过HTTP协议实现，还可以利用缓存Cache来提高响应速度，性能、效率和易用性上都优于SOAP协议.</p>
                    <p>
                        REST架构遵循了CRUD原则，CRUD原则对于资源只需要四种行为：Create（创建）、Read（读取）、Update（更新）和Delete（删除）就可以完成对其操作和处理.这四个操作是一种原子操作，对资源的操作包括获取、创建、修改和删除资源的操作正好对应HTTP协议提供的GET、POST、PUT和DELETE方法，因此REST把HTTP对一个URL资源的操作限制在GET、POST、PUT和DELETE这四个之内.这种针对网络应用的设计和开发方式，可以降低开发的复杂性，提高系统的可伸缩性.</p>
                    <p>更多REST API背景知识可以参考这篇RESTful API 设计指南</p>
                    <p>REST平台体系</p>
                    <p>
                        平台提供的是一个多租户用户体系，资源以集合【Collection】的形式来描述,这里所说的Collection包括DataBase、企业(orgs)、应用(apps)、IM用户(users)、群组(chatgroups)、消息(chatmessages)、文件(chatfiles)等等，之间的包含关系是</p>
                    <p>
                        DB = {org1, org2, …} org = {app1, app2, …} app = {users, messages, chatfiles, chatmessages,
                        chatgroups, …} users = {user1, user2, …} messages = {message1, message2, …} chatfiles =
                        {chatfile1, chatfile2, …} chatmessages = {chatmessage1, chatmessage2, …} chatgroups = {group1,
                        group2, …} 多租户是指软件架构支持一个</p>
                    <p>实例服务多个用户（Customer），每一个用户被称之为租户（Tenant），软件给予租户可以对系统进行部分定制的能力，如用户界面颜色或业务规则，但是他们不能定制修改软件的代码.</p>
                    <p>
                        其实在云计算领域，多租户的含义已被扩展.例如，软件即服务（SaaS）提供者，利用运行在一个数据库实例上的应用系统，向多个用户提供Web访问服务.在这样的场景下，租户之间的数据是隔离的，并且保证每个用户的数据对其他租户不可见.</p>
                    <p>在时信服务体系中，不同org之间的用户数据相互隔离，同一个org下不同app之间的用户数据相互隔离.</p>
                    <p>REST Server</p>
                    <p>时信的服务器端接口都是通过REST服务方式提供的, REST API基于最简单的HTTP协议, 在各个编程语言中都提供了良好的支持.</p>
                    <p>REST client</p>
                </div>

                <div class="tab_hide"><h3>通信过程及聊天记录保存</h3></div>
                <div class="tab_hide"><h3>通信过程及聊天记录保存</h3></div>
                <div class="tab_hide"><h3>客户端集成</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
                <div class="tab_hide"><h3>实时消息回调</h3></div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="../../js/file.js"></script>
</body>
</html>