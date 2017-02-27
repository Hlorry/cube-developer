<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/15
  Time: 16:56
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
    <link rel="stylesheet" type="text/css" href="../../../css/case.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top navbar_bg">
    <div class="ie_header ie_navbar_bg">
        <div class="container nav_header" style="overflow: inherit">
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
            <div id="navbar" class="navbar-collapse collapse " aria-expanded="false" style="height: 1px;">
                <ul class="nav navbar-nav navbar-left">
                    <li class="active"><a href="/app">我的应用</a></li>
                    <%--     <li><a href="#">知识库</a></li>--%>
                    <li><a href="/route/download">下载</a></li>
                    <%--<li><a href="/route/file">文档</a></li>--%>
                </ul>
                <div class="navbar-right case_nav">
                    <div class="dropdown">
                        <div id="dLabel" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            <p>欢迎您</p> <%=username %> <span class="caret"></span>
                        </div>
                        <ul class="dropdown-menu" aria-labelledby="dLabel">
                            <li><a href="/route/personal">个人信息</a></li>
                            <li><a href="/user/logout">退出</a></li>
                        </ul>
                    </div>
                </div>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</nav>
<div class="container menu_padding">
    <div class="file_tab casepage">
        <div class="row">
            <div class="col-sm-12 app_title">
                <button type="button" class="btn btn-default btn-block text-left">我的应用</button>
            </div>
        </div>
        <div class="tabs tabs-vertical-left" id="tabs">
            <div class="add_box">
                <ul class="app_box" id="showTable">
                    <li class="row" style="line-height:50px;">
                        <div class=" col-sm-4">

                            应用名称

                        </div>
                        <div class="col-sm-4">
                            <%--    <h4 class="media-heading">${app.appName}<img src="../../img/editor.png" onclick="updateAppInfo('modify-app-modal', '${app.appId}')"></h4>--%>
                            创建时间
                            <%--    <p>${app.appKey}</p>--%>
                        </div>
                        <div class="col-sm-4">
                            <%--    <h4 class="media-heading">${app.appName}<img src="../../img/editor.png" onclick="updateAppInfo('modify-app-modal', '${app.appId}')"></h4>--%>
                            状态
                            <%--    <p>${app.appKey}</p>--%>
                        </div>
                    </li>
                    <c:forEach items="${apps}" var="app" varStatus="vs">
                        <li class="row">
                            <a href="/app/detail/${app.appId}">
                                <div class="col-xs-12 col-sm-4">
                                    <div class="app_list">
                                        <span class="list_bg "><img
                                                src="${app.avatar == null ? '../../img/update_bg.jpg' : app.avatar}"
                                                width="54"
                                                height="54"></span>
                                        <span class="media-heading">${app.appName} </span>
                                    </div>
                                </div>
                                <div class="col-xs-12 col-sm-4">
                                        <%--    <h4 class="media-heading">${app.appName}<img src="../../img/editor.png" onclick="updateAppInfo('modify-app-modal', '${app.appId}')"></h4>--%>
                                    <p style="margin-top:0px;line-height:50px;"><fmt:formatDate
                                            value="${app.createTime}" type="date"
                                            pattern="yyyy年MM月dd日HH点mm分ss秒"></fmt:formatDate></p>
                                        <%--    <p>${app.appKey}</p>--%>
                                </div>
                                <div class="col-xs-12 col-sm-4">
                                        <%--    <h4 class="media-heading">${app.appName}<img src="../../img/editor.png" onclick="updateAppInfo('modify-app-modal', '${app.appId}')"></h4>--%>
                                    <p style="margin-top:0px;line-height:50px;">上线运行中</p>
                                        <%--    <p>${app.appKey}</p>--%>
                                </div>
                            </a>
                        </li>
                    </c:forEach>
                </ul>
                <div class="tabs-container">
                    <c:if test="${fn:length(apps) == 0}">
                        <div class="tab-content text-left no_app" style="display:block;">
                            <h3>您还没有创建应用</h3>
                            <p>创建应用后，可享受如下服务：IM服务、音视频服务、共享白板云上传、数据统计服务。</p>
                        </div>
                    </c:if>
                </div>
                <div class="add_app">
                    <c:if test="${fn:length(apps) < 5}">
                        <div class="app_list">
                                <%-- <span class="list_bg "><img src="../../img/add.png" width="54" height="54"></span>--%>
                            <button type="submit" class="btn btn-success" onclick="createAppInfo('create-app-modal')">
                                创建应用
                            </button>
                        </div>
                        <div class="col-xs-12 col-sm-12">
                            <p style="margin-top:0px;line-height:50px; padding-left:30px;"> 您还可以创建<span
                                    style="color:#62cdb7;">${5 - fn:length(apps)}</span>个应用</p>
                        </div>
                        </a>
                    </c:if>
                </div>
            </div>

        </div>
    </div>
</div>
<!-- 创建应用 -->
<div class="modal fade add" id="create-app-modal" tabindex="-1" role="dialog" aria-labelledby="CreateModalLabel">
    <form action="/app/create" method="post" class="form-horizontal" id="create_app_form" novalidate="novalidate">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-left" id="CreateModalLabel">创建应用</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="token" id="token">
                    <input type="hidden" name="userId" id="userId">
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用名称：</label>
                        <div class="col-sm-8" target="appName">
                            <input type="text" class="form-control" name="appName" id="appCreateName"
                                   placeholder="请输入应用名称"
                                   data-bv-field="appName">
                            <span class="help-block" target="appName" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用简介：</label>
                        <div class="col-sm-8" target="description">
                                <textarea class="form-control" rows="3" name="description" id="createDescription"
                                          placeholder="请输入应用简介"></textarea>
                            <span class="help-block" target="description" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用类型：</label>
                        <div class="col-sm-8" target="category">
                            <select class="form-control" name="category" id="createCategory" data-bv-field="category">
                                <option value="">请选择应用分类</option>
                                <option value="1">游戏</option>
                                <option value="2">财务</option>
                                <option value="3">参考</option>
                                <option value="4">导航</option>
                                <option value="5">工具</option>
                                <option value="6">健康</option>
                                <option value="7">教育</option>
                                <option value="8">旅行</option>
                                <option value="9">美食</option>
                                <option value="10">商业</option>
                                <option value="11">商品指南</option>
                                <option value="12">社交</option>
                                <option value="13">摄影</option>
                                <option value="14">生活</option>
                                <option value="15">体育</option>
                                <option value="16">天气</option>
                                <option value="17">图书</option>
                                <option value="18">效率</option>
                                <option value="19">新闻</option>
                                <option value="20">医疗</option>
                                <option value="21">音乐</option>
                                <option value="22">娱乐</option>
                                <option value="23">报刊杂志</option>
                            </select>
                            <span class="help-block" target="category" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-xs-4 control-label text-right">运营阶段：</label>
                        <div class="col-xs-8" target="appStage">
                            <label class="stage"><input type="radio" name="appStage" id="appCreateStage1" value="1"
                                                        data-bv-field="appStage">未运营尚无用户</label>
                            <label class="stage"><input type="radio" name="appStage" id="appCreateStage2" value="2"
                                                        data-bv-field="appStage" checked>运营中已有用户</label>
                            <span class="help-block" target="appStage" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group" id="user_level_div">
                        <label class="col-sm-4 control-label text-right">用户量级：</label>
                        <div class="col-sm-8" target="appUserLevel">
                            <select class="form-control" name="appUserLevel" id="appCreateUserLevel"
                                    data-bv-field="appUserLevel">
                                <option value="">请选择用户量级</option>
                                <option value="1">万级</option>
                                <option value="2">十万级</option>
                                <option value="3">百万级</option>
                                <option value="4">千万级</option>
                                <option value="5">亿级</option>
                            </select>
                            <span class="help-block" target="appUserLevel" style="display: none;"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-xs-4">
                        <span class="help-block pull-left text-left" target="tips-create" style="display: none;"></span>
                    </div>
                    <div class="col-xs-8">
                        <button type="submit" class="btn btn-primary">创建</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<div class="case_footer">
    <p>Copyright 2015 RONGCLOUD 京ICP备15042119号-1</p>
</div>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.form.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/responsive.tabs.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/echarts/3.0.0/echarts.min.js"></script>
<script type="text/javascript" src="../../js/app.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript" src="../../js/dowond.js"></script>
<script type="text/javascript" src="../../js/footer.js"></script>
</body>
</html>
