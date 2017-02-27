<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/28
  Time: 11:03
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=yes">
    <title>魔方实时协作云</title>
    <%@ include file="public/header.jsp" %>
    <%--<jsp:include page="public/header.jsp" flush="false" />--%>
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
                <a class="navbar-brand" href="/"><span><img src="../../img/logo.png"></span><span></span></a>
            </div>
            <div id="navbar" class="navbar-collapse collapse navbar-right" aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav">
                    <li><a href="/">首页</a></li>
                    <li><a href="/route/product">产品</a></li>
                    <li><a href="/route/price">服务</a></li>
                    <li role="presentation" class="dropdown active">
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
                            <%--&lt;%&ndash;  <li><a href="/route/ knowledge">知识库</a></li>&ndash;%&gt;--%>
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
<div class="container menu_padding download_padding">
	<div class="row">
			<div class="col-md-offset-3 col-md-6">
				
				<form id="feedback_form" class="form-horizontal">
					<div class="form-group">
						<div class="col-sm-12" style="color: #00cdaf;text-align: center;">
					      <p class="form-control-static" >你好，我是Cube的产品经理，欢迎您给我们提出产品改进的宝贵建议</p>
					    </div>
					</div>
					<div class="form-group">
						<label for="title" class="col-sm-2 control-label">标题</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="title" name="title"
								placeholder="请输入4-30个字符">
						</div>
					</div>
					<div class="form-group">
						<label for="content" class="col-sm-2 control-label">内容</label>
						<div class="col-sm-10">
							<textarea class="form-control" name="content" id="content" rows="6"
								placeholder="感谢您给我们提出建议，我们将尽快解决问题"></textarea>
						</div>
					</div>
					<div class="form-group">
						<label for="phone" class="col-sm-2 control-label">联系方式</label>
						<div class="col-sm-10">
							<input type="text" class="form-control" id="phone" name="phone"
								placeholder="联系方式">
						</div>
					</div>
					<div class="form-group">
						<div class="col-sm-offset-2 col-sm-10">
							<button type="submit" class="btn btn-block btn-success">反馈</button>
						</div>
					</div>
				</form>
			</div>
		</div>
</div>
<jsp:include page="public/alert.jsp"></jsp:include>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/feedback.js"></script>
</body>
</html>
