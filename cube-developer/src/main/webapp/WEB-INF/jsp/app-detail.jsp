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
<%
	String path = request.getContextPath();
	String basePath = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort()
			+ path + "/";
%>	
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
    <link type="text/css " rel="stylesheet" href="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.css">
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
                <button type="button" class="btn btn-default btn-block text-left" onclick="goApp()">
                    我的应用><span>${appObj.appName}</span></button>
            </div>
        </div>
        <div class="tabs tabs-vertical-left" id="tabs">
            <div class="tabs-container" style="margin-top:20px;">
                <div class="tab-content text-left" style="display:block;">
                    <div class="tabs tabs_next" id="tabBox">
                        <ul class="tabs-list">
                            <li class="active"><a href="javascript:void(0);">基本信息</a></li>
                            <%--<li><a href="javascript:void(0);">IM服务</a></li>--%>
                            <li><a href="javascript:void(0);" onclick="managedAppUser()">用户管理</a></li>
                            <li><a href="javascript:void(0);">数据统计</a></li>
                            <li id="licence"><a href="javascript:void(0);">Licence 管理</a></li>
                        </ul>
                        <div class="tabs-container list_detail ">
                            <div class="tab-content" style="display:block;">
                                <div class="row">
                                    <div class="col-md-12 ">
                                        <div class="">
                                            <form role="form" id="avatarForm" autocomplete="off" class="form-horizontal"
                                                  novalidate="novalidate" enctype="multipart/form-data">
                                                <input type="hidden" name="appId" value="${appObj.appId}">
                                                <a class="app_list_detail file"><img id="appAvatar"
                                                                                     src="${appObj.avatar == null ? '../../img/update_bg.jpg' : appObj.avatar}"
                                                                                     width="69"
                                                                                     height="69">
                                                    <span></span>
                                                    <input type="file" id="avatar" name="avatar"
                                                           role="button" value="上传"
                                                           data-bv-field="license"
                                                           onchange="handleFiles(this.files)"></a>
                                            </form>
                                        </div>
                                        <div class="row ">

                                            <div class="col-xs-12 col-sm-12">
                                                <label class="col-xs-4 col-sm-3 control-label"> 应用名称</label>
                                                <span>${appObj.appName}</span>
                                            </div>
                                        </div>
                                       <%--  <div class="row ">

                                            <div class="col-xs-12 col-sm-12">
                                                <label class="col-xs-4 col-sm-3 control-label"> 应用标识（AppKey）</label>
                                                <span>${appObj.appKey}</span>
                                            </div>
                                        </div> --%>
                                        
                                        <div class="row ">

                                            <div class="col-xs-12 col-sm-12">
                                                <label class="col-xs-4 col-sm-3 control-label"> 创建时间</label>
                                                <span><fmt:formatDate value="${appObj.createTime}" type="date"
                                                                      pattern="yyyy年MM月dd日HH点mm分ss秒"></fmt:formatDate></span>
                                                <%--<span>${app.createTime}</span>--%>
                                            </div>
                                        </div>
                                        <div class="row">

                                            <div class="col-xs-12 col-sm-12">
                                                <label class="col-xs-4 col-sm-3 control-label"> 最后修改时间</label>
                                                <span> <fmt:formatDate value="${appObj.modifyTime}" type="date"
                                                                       pattern="yyyy年MM月dd日HH点mm分ss秒"></fmt:formatDate></span>
                                                <%--<span>${app.modifyTime}</span>--%>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <div class="col-xs-12 col-sm-12">
                                                <label class="col-xs-4 col-sm-3 control-label"> 应用简介</label>
                                                <span> ${appObj.description}</span>
                                            </div>
                                        </div>
                                        <div class="form-group btn_up">
                                            <div class="col-sm-4 text-right">
                                            </div>
                                            <div class="col-sm-4">
                                                <button type="button"
                                                        onclick="updateAppInfo('modify-app-modal', '${appObj.appId}')"
                                                        class="btn btn-warning btn-block">修改应用
                                                </button>
                                                <button type="button" class="btn btn-success btn-block"
                                                        onclick="appDelete('delete-app-modal','${appObj.appId}')">
                                                    删除应用
                                                </button>
                                                <%--       <p>您还未开通付费功能<a href="#">马上开通</a></p>--%>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                            </div>
                            <!-- 用户管理start -->
                            <div class="tab-content">
                                <div class="row im_search">
                                    <div class="col-sm-8 col-md-offset-2 ">
                                        <div class="input-group">
                                        </div>
                                    </div>
                                </div>
                                <div class="im_table">
                                    <div class="row">
                                        <div class="col-sm-12"></div>
                                        </div>
                                    </div>
                                    <table id="app_user_table">
                                        <thead>
                                        <tr>
                                            <th data-field="cube">号码</th>
                                            <th data-field="display_name">名称</th>
                                            <th data-field="first" data-formatter="formatDatetime">第一次登录时间</th>
                                            <th data-field="latest" data-formatter="formatDatetime">最近一次登录时间</th>
                                            <th data-field="times">累计登录次数</th>
                                        </tr>
                                        </thead>
                                    </table>
                                </div>
                            <!-- 用户管理end -->
                            <div class="tab-content">
                                <div class="data_part1 data_margin">
                                    <p class="text-right">提示：目前数据统计只针对已上线的应用声场的数据进行统计</p>
                                    <div class="row data_radius">
                                        <p>昨日使用人数</p>
                                        <div class="col-sm-6 col-md-3">
                                            <div class="thumbnail">
                                                <div class="caption">
                                                    <h3>0</h3>
                                                    <p>活跃人数</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-md-3">
                                            <div class="thumbnail">
                                                <div class="caption">
                                                    <h3>0</h3>
                                                    <p>发消息人数</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-md-3">
                                            <div class="thumbnail">
                                                <div class="caption">
                                                    <h3>0</h3>
                                                    <p>新增人数</p>
                                                </div>
                                            </div>
                                        </div>
                                        <div class="col-sm-6 col-md-3">
                                            <div class="thumbnail">
                                                <div class="caption">
                                                    <h3>0</h3>
                                                    <p>新增讨论组数</p>
                                                </div>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="data_part1 data_margin">
                                    <div class="row data_radius ">
                                        <ul class="data_chart">
                                            <li class="active">历史使用人数统计</li>
                                            <li>活跃人数</li>
                                            <li>发消息人数</li>
                                            <li>新增人数</li>
                                            <li>新增讨论组数</li>
                                        </ul>
                                        <div class="row">
                                            <div id="main" style="width: 100%;height:400px;"></div>
                                        </div>

                                    </div>
                                </div>
                                <div class="data_part1 data_margin">
                                    <div class="row data_radius">
                                        <table class="table table-condensed">
                                            <thead>
                                            <tr>
                                                <th>统计时间</th>
                                                <th>活跃人数</th>
                                                <th>活跃人数</th>
                                                <th>新增人数</th>
                                                <th>新增讨论组数</th>
                                            </tr>
                                            </thead>
                                            <tbody>
                                            <tr>
                                                <th scope="row">暂无数据</th>
                                                <td>暂无数据</td>
                                                <td>暂无数据</td>
                                                <td>暂无数据</td>
                                                <td>暂无数据</td>
                                            </tr>
                                            </tbody>
                                        </table>
                                    </div>
                                </div>
                            </div>
                            <div class="tab-content">
                                <div class="row download_box">
                                    <h3> License版本：1.0</h3>
                                    <div>
                                    	<!--测试环境  -->
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"> Appkey：</div>
                                            <div class="col-sm-10">
                                                <span id="appTestKeyInfo"></span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"> AppId：</div>
                                            <div class="col-sm-10">
                                                <span id="appTestIdInfo"></span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"> 可用Id：</div>
                                            <div class="col-sm-10">
                                                <span id="TestuseId"></span>
                                            </div>
                                        </div>
                                        <!--生产环境 -->
                                         <div class="row ">
                                            <div class="col-sm-2 control-label"> Appkey：</div>
                                            <div class="col-sm-10">
                                                <span id="appKeyInfo"></span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"> AppId：</div>
                                            <div class="col-sm-10">
                                                <span id="appIdInfo"></span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"> 可用Id：</div>
                                            <div class="col-sm-10">
                                                <span id="useId"></span>
                                            </div>
                                        </div>
                                        
                                        <div class="row ">
                                            <div class="col-sm-2 control-label">有效日期:</div>
                                            <div class="col-sm-10">
                                                <span id="timeInfo">2016/04/01～201604/07</span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label">环境选择:</div>
                                            <div class="col-sm-10">
                                                <span id="environment">
                                                    <input type="radio" id="liceStage1" name="service" value="1" checked="checked"/> 测试环境
                                                    <input type="radio" id="liceStage2" develop_appid="" checktype="" name="service" value="2"/> 生产环境
                                                </span>
                                            </div>
                                        </div>
                                         <div class="row ">
                                            <div class="col-sm-2 control-label">启用服务：</div>
                                            <div class="col-sm-10" id="useServing">
                                            </div>
                                        </div> 
                                        <div class="row  service_check">
                                            <div class="col-xs-12" >
                                            </div>
                                        </div>
                                         <div class="row ">
                                            <div class="col-sm-2 control-label">使用说明：</div>
  
                                            <div class="col-sm-9" id="webDetail" >
                                            <pre id="explain1" hidden="hidden">
1.Android端将cube.license文件放到相关project的assets目录下
2.Web端访问测试，需要修改
   demo/call-demo.html
   demo/message-demo.html
   demo/whiteboard-demo.html
 三个文件分别对应呼叫测试、消息测试、白板测试
3.<!-- http://managed.getcube.cn/auth/license?appid -->
</pre>
<pre id="explain">
1.Android端将cube.license文件放到相关project的assets目录下
2.Web端访问测试，需要修改
   demo/call-demo.html
   demo/message-demo.html
   demo/whiteboard-demo.html
 三个文件分别对应呼叫测试、消息测试、白板测试
3.<!-- http://managed.getcube.cn/auth/license?appid -->
</pre>
          
<pre id="explain2">
1.Android端将cube.license文件放到相关project的assets目录下
2.Web端访问测试，需要修改
   demo/call-demo.html
   demo/message-demo.html
   demo/whiteboard-demo.html
 三个文件分别对应呼叫测试、消息测试、白板测试
3.<!-- http://managed.getcube.cn/auth/license?appid -->
</pre>
                                          
                                            </div>
                                        </div> 
                                         <div class="row ">
                                            <div class="col-sm-2 control-label"></div>
                                            <div class="col-sm-7" id="webDetail" >
                                            	<a href="" id="licenseUrl" class="btn btn-success btn-sm" style="margin-top: 0px;" download>获取生产license(v1)</a>
                                            	<a href="javascript:void(0);" id="licenseUrlt" class="btn btn-success btn-sm" style="margin-top: 0px;" download>获取生产license(v2)</a>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <div class="col-sm-2 control-label"></div>
                                            <div class="col-sm-7" id="webDetail" >
                                            	<a href="" id="licenseUrl1" class="btn btn-success btn-sm" style="margin-top: 0px;" download>获取测试license(v1)</a>
                                            	<a href="javascript:void(0);" id="licenseUrl1t" class="btn btn-success btn-sm" style="margin-top: 0px;" download>获取测试license(v2)</a>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>

            </div>
        </div>
    </div>
</div>
<!-- 修改应用 -->
<div class="modal fade add" id="modify-app-modal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel">
    <form action="/app/update" method="post" class="form-horizontal" id="modify_app_form" novalidate="novalidate">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-left" id="ModalLabel">修改应用资料</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="token" id="token_modify">
                    <input type="hidden" name="userId" id="userId_modify">
                    <input type="hidden" name="appId" id="appId">
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用名称：</label>
                        <div class="col-sm-8" target="appName">
                            <input type="text" class="form-control" name="appName" id="appModifyName"
                                   placeholder="请输入应用名称"
                                   data-bv-field="appName">
                            <span class="help-block" target="appName" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用简介：</label>
                        <div class="col-sm-8" target="description">
                                <textarea class="form-control" rows="3" name="description" id="modifyDescription"
                                          placeholder="请输入应用简介"></textarea>
                            <span class="help-block" target="description" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label text-right">应用类型：</label>
                        <div class="col-sm-8" target="category">
                            <select class="form-control" name="category" id="modifyCategory" data-bv-field="category">
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
                            <label class="stage"><input type="radio" name="appStage" id="appStage1" value="1"
                                                        data-bv-field="appStage">未运营尚无用户</label>
                            <label class="stage"><input type="radio" name="appStage" id="appStage2" value="2"
                                                        data-bv-field="appStage" checked>运营中已有用户</label>
                            <span class="help-block" target="appStage" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group" id="modify_user_level_div">
                        <label class="col-sm-4 control-label text-right">用户量级：</label>
                        <div class="col-sm-8" target="appUserLevel">
                            <select class="form-control" name="appUserLevel" id="appModifyUserLevel"
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
                        <span class="help-block pull-left text-left" target="tips-modify" style="display: none;"></span>
                    </div>
                    <div class="col-xs-8">
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 删除应用 -->
<div class="modal fade add" id="delete-app-modal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel">
    <form role="form" id="delete-app-form" autocomplete="off" class="form-horizontal" novalidate="novalidate">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-left">删除应用</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="delAppId" id="delAppId">
                    <div class="alert alert-danger">
                        请输入应用名称和登录密码后方可删除应用，删除后不可恢复，请谨慎操作。
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label text-right">应用名称：</label>
                        <div class="col-sm-5">
                            <input type="text" class="form-control" name="name" id="del-name"
                                   placeholder="请输入要删除的应用名称" data-bv-field="delName">
                        </div>
                        <div class="col-sm-3">
                            <span class="help-block" target="name" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label text-right">登录密码：</label>
                        <div class="col-sm-5">
                            <input class="form-control" placeholder="请输入您的登录密码" type="password" name="password"
                                   id="del-password">
                        </div>
                        <div class="col-sm-3">
                            <span class="help-block" target="password" style="display: none;"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-xs-4">
                        <span class="help-block puii-left text-left" target="tips-del" style="display: none;"></span>
                    </div>
                    <div class="col-xs-8">
                        <button type="submit" class="btn btn-primary">删除应用</button>
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
<jsp:include page="public/alert.jsp"></jsp:include>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-table/1.10.1/bootstrap-table.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.form.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/responsive.tabs.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/echarts/3.0.0/echarts.min.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
<script type="text/javascript" src="../../js/appdel.js"></script>
<%--<script type="text/javascript" src="../../js/dowond.js"></script>--%>
<script type="text/javascript" src="../../js/footer.js"></script>
<script type="text/javascript" src="../../js/detail.js"></script>
<script type="text/javascript" src="../../js/dataformat.js"></script>
<script type="text/javascript" src="/js/jquery.bootstrap.min.js"></script>
</body>
</html>
