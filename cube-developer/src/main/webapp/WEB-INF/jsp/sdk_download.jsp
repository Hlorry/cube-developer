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
                  <!--   <li class="active"><a href="/route/download">下载</a></li> -->
                  
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
    <div class="file_tab casepage">
        <div class="row download_list">
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <div class="caption">
                        <div class="download_title download_radius">
                            <h3><img src="../../img/Andriod.png"> <label>Andriod</label>
                            </h3>
                        </div>
                        <div class="download_radius download ">
                            <p>
                                <span class="text-left">版本：<span id="andVersion">1.5.18</span></span>
                                <span class="text-right" style="float:right">更新时间：<span id="andUpdateTime">2016-7-20</span></span>
                            </p>
                            <a id="andSDKDown" href="http://www.getcube.cn/api/android/Android_SDK_v1.5.11.zip"
                               class="btn btn-success btn-block">SDK下载
                            </a>
                            <a id="andShowApp" href="http://www.getcube.cn/api/android/CubeWareDemo_v1.4.62.apk"
                               class="btn btn-success btn-block">演示应用
                            </a>
                            <a id="andDoc" href="http://www.getcube.cn/api/android/CubeEngineDoc_v1.5.11.zip"
                               class="btn btn-success btn-block">文档
                            </a>
                            <a id="andCode" href="http://www.getcube.cn/api/android/CubeWareDemo_v1.4.62.zip"
                               class="btn btn-success btn-block">演示源码
                            </a>

                        </div>
                        <div class="download_title download_radius download_under">
                            <div class="row">
                                <div class="col-xs-6 col-md-6"><span class="text-center">自述文件</span></div>
                                <div class="col-xs-6 col-md-6"><span class="text-center">更新日志</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <div class="caption">
                        <div class="download_title download_radius">
                            <h3><img src="../../img/ios.png"> <label>iOS</label></h3>
                        </div>
                        <div class="download_radius download ">
                            <p>
                                <span class="text-left">版本：<span id="iosVersion">1.5.27</span></span>
                                <span class="text-right" style="float:right">更新时间：<span id="iosUpdateTime">2016-7-20</span></span>
                            </p>
                            <a id="iosSDKDown" href="http://www.getcube.cn/api/iOS/CubeEngine_iOS_SDK_1.5.14.zip"
                               class="btn btn-success btn-block">SDK下载
                            </a>
                            <a id="iosDoc" href="http://www.getcube.cn/api/iOS/CubeEngine_iOS_Doc_1.5.14.zip"
                               class="btn btn-success btn-block">文档
                            </a>
                            <a id="iosCode" href="http://www.getcube.cn/api/iOS/CubeDemo_1.5.14.zip"
                               class="btn btn-success btn-block">演示源码
                            </a>

                        </div>
                        <div class="download_title download_radius download_under">
                            <div class="row">
                                <div class="col-xs-6 col-md-6"><span class="text-center">自述文件</span></div>
                                <div class="col-xs-6 col-md-6"><span class="text-center">更新日志</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
            <div class="col-sm-6 col-md-4">
                <div class="thumbnail">
                    <div class="caption">
                        <div class="download_title download_radius">
                            <h3><img src="../../img/web.png"> <label>Web</label></h3>
                        </div>
                        <div class="download_radius download ">
                            <p>
                                <span class="text-left">版本：<span id="webVersion">1.5.7</span></span>
                                <span class="text-right" style="float:right">更新时间：<span id="webUpdateTime">2016-7-20</span></span>
                            </p>

                            <a id="webSDKDown" href="http://www.getcube.cn/api/CubeEngine-JS-1.5.6.zip"
                               class="btn btn-success btn-block">SDK下载
                            </a>
                            <a href="http://www.getcube.cn/api/web/doc/js/index.html"
                               class="btn btn-success btn-block">文档
                            </a>
                            <a href="https://www.shixinyun.com/engine/demo/index.html"
                               class="btn btn-success btn-block">演示源码
                            </a>
                        </div>
                        <div class="download_title download_radius download_under">
                            <div class="row">
                                <div class="col-xs-6 col-md-6"><span class="text-center">自述文件</span></div>
                                <div class="col-xs-6 col-md-6"><span class="text-center">更新日志</span></div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
        <div class="row download_box">
            <div class="col-md-3 col-md-offset-4">
                <p>非注册用户请下载试用<a href="">license</a></p>
                <form class="form-horizontal" id="download">
                    <div class="form-group">
                        <div class="col-sm-12 code_box">
                            <div class="col-xs-8 col-sm-8">
                                <input type="text" class="form-control proving" id="input1" name="code_text"
                                       placeholder="请输入验证码">
                            </div>
                            <div class="col-xs-4 col-sm-4">
                                <input type="text" name="code" readonly="readonly"
                                       id="checkCode" class="unchanged" onclick="createCode()"/>
                            </div>

                            <%--<input type="password" class="form-control" name="password" placeholder="请输入验证码">--%>
                        </div>
                    </div>
                    <button type="submit" class="btn btn-success btn-block" disabled="disabled">点击下载</button>
                </form>
            </div>
        </div>
    </div>
</div>
<jsp:include page="public/alert.jsp"></jsp:include>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/fileSaver.js"></script>
<script type="text/javascript" src="../../js/sdkdowond.js"></script>
</body>
</html>
