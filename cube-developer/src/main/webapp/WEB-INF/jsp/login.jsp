<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=yes">
    <title>魔方实时协作云</title>
    <jsp:include page="public/header.jsp"></jsp:include>
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
                    <%--<li role="presentation" class="dropdown">--%>
                        <%--<a class="dropdown-toggle" data-toggle="dropdown" href="" role="button"--%>
                           <%--aria-haspopup="true" aria-expanded="false">--%>
                            <%--支持--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<li><a href="/route/file">文档</a></li>--%>
                            <%--<li><a href="/route/download">下载</a></li>--%>
                            <%--&lt;%&ndash;       <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
                <form class="navbar-form  fr">
                    <a class="active" href="/route/login">登入</a>
                    <a href="/route/register">注册</a>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</nav>
<div class="container menu_padding login_page">
    <div class="row">
        <div class="col-md-8">
            <form action="/user/login" method="post" class="form-horizontal" id="login" novalidate="novalidate">
                <input type="hidden" value="7788119" id="version" name="version">
                <input type="hidden" value="/app" id="targetUrl" name="targetUrl">
                <div class="form-group">
                    <label class="col-sm-6"></label>
                    <div class="col-sm-6">
                        <h3>登录</h3>
                        <p style="margin-bottom:0px;">还没账号？<a href="/route/register">立即注册</a></p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-6  col-xs-12 control-label">注册邮箱/手机号</label>
                    <div class="col-sm-6 col-xs-12 ">
                        <span class="form-control-feedback"><img src="../../img/iconfont-youxiang-.png"></span>
                        <input type="text" class="form-control" target="username" id="username" name="username"
                               placeholder="请输入注册邮箱/手机号码">
                        <span class="errorinfo" target="username" style="display: none"></span>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-6 col-xs-12 control-label">密码</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-suo-.png"></span>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block">确定</button>
                        <p class="text-right"><a href="/route/forget">忘记密码？</a></p>
                    </div>
                </div>
            </form>
        </div>
        <div class="col-md-3 col-md-offset-4"></div>
    </div>

</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/login.js"></script>
</body>
</html>