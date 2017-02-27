<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/15
  Time: 16:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix='fmt' uri="http://java.sun.com/jsp/jstl/fmt" %>
<html>
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width,minimum-scale=1.0,maximum-scale=1.0,user-scalable=no"/>
    <meta name="format-detection" content="telephone=yes">
    <title>魔方实时协作云</title>
    <%@ include file="public/header.jsp" %>
    <%--<jsp:include page="public/header.jsp"></jsp:include>--%>
    <link rel="stylesheet" href="../../css/case.css">
</head>
<body>
<nav class="navbar navbar-default navbar-fixed-top navbar_bg">
    <div class="ie_header ie_navbar_bg">
        <div class="container" style="overflow: inherit">
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
                    <li><a href="/app">我的应用</a></li>
                    <%--  <li><a href="/route/product">知识库</a></li>--%>
                    <li><a href="/route/download">下载</a></li>
                    <%--<li><a href="/route/file">文档</a></li>--%>
                </ul>
                <div class="navbar-right">
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
        <div class="tabs tabs-vertical-left" id="tabs">
            <div class="add_box">
                <a href="/app" style="font-size: 15px;">我的应用</a>
                <div><img src="../../img/editor.png">${app.appName}</div>
            </div>
            <div class="tabs-container">
                <div class="tab-content text-left" style="display:block;">
                    <div class="tabs tabs_next" id="tabBox">
                        <ul class="tabs-list">
                            <li class="active"><a href="javascript:void(0);">基本信息</a></li>
                            <li><a href="javascript:void(0);">数据统计</a></li>
                        </ul>
                        <div class="tabs-container list_detail">
                            <div class="tab-content" style="display:block;">
                                <div class="row">
                                    <div class="col-md-8 col-md-offset-3">
                                        <p class="text-center"><img src="../../images/wenxin_large.png" width="120"
                                                                    height="120"></p>
                                        <div class="row ">
                                            <label class="col-xs-6 col-sm-6 control-label"> 应用名称</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <span>${app.appName}<a href="javascript:void(0);" data-toggle="modal"
                                                                       data-target="#modify-apps"
                                                                       onclick="updateAppInfo()"><img
                                                        src="../../img/editor.png"></a></span>
                                                <input type="hidden" id="appId" name="appId" value="${app.appId}">
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <label class="col-xs-6 col-sm-6 control-label"> 应用标识（AppKey）</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <span>${app.appKey}</span>
                                            </div>
                                        </div>
                                        <div class="row ">
                                            <label class="col-xs-6 col-sm-6 control-label"> 创建时间</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <fmt:formatDate value="${app.createTime}" type="date"
                                                                pattern="yyyy年MM月dd日HH点mm分ss秒"></fmt:formatDate>
                                                <%--<span>${app.createTime}</span>--%>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-6 control-label"> 最后修改时间</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <fmt:formatDate value="${app.modifyTime}" type="date"
                                                                pattern="yyyy年MM月dd日HH点mm分ss秒"></fmt:formatDate>
                                                <%--<span>${app.modifyTime}</span>--%>
                                            </div>
                                        </div>
                                        <%--<div class="row">--%>
                                        <%--<label class="col-xs-6 col-sm-6control-label"> 用户注册模式</label>--%>
                                        <%--<div class="col-xs-6 col-sm-6">--%>
                                        <%--<span>授权注册</span>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <%--<div class="row">--%>
                                        <%--<label class="col-xs-6 col-sm-6 control-label"> 当前用户总数</label>--%>
                                        <%--<div class="col-xs-6 col-sm-6">--%>
                                        <%--<span>0</span>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-6 control-label"> Client Id</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <span>${app.clientId}</span>
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-6 control-label"> Client Secret</label>
                                            <div class="col-xs-6 col-sm-6">
                                                <span>${app.clientSecret}</span>
                                            </div>
                                        </div>
                                        <%--<div class="row">--%>
                                        <%--<label class="col-xs-6 col-sm-6 control-label"> 缩略图大小</label>--%>
                                        <%--<div class="col-xs-6 col-sm-6">--%>
                                        <%--<span>170px* 170px(长*宽)<img src="../../img/editor.png"></span>--%>
                                        <%--</div>--%>
                                        <%--</div>--%>
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-6 control-label"> 应用简介</label>
                                            <div class="col-xs-6 col-sm-6">
                                            </div>
                                        </div>
                                        <div class="row">
                                            <label class="col-xs-6 col-sm-6 control-label"></label>
                                            ${app.description}
                                        </div>
                                        <div class="form-group btn_up">
                                            <div class="col-sm-3 text-right">
                                            </div>
                                            <div class="col-sm-6">
                                                <button type="button" class="btn btn-success btn-block"
                                                        onclick="appDelete('delete-app-modal','${app.appId}')">删除应用
                                                </button>
                                                <p>您还未开通付费功能<a href="#">马上开通</a></p>
                                            </div>
                                        </div>

                                    </div>
                                </div>
                                <div class="row download_list">
                                    <div class="col-sm-6 col-md-4">
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <div class="download_title download_radius">
                                                    <h3><img src="../../img/Andriod.png"> <label>Andriod</label></h3>
                                                </div>
                                                <div class="download_radius download ">
                                                    <p><span class="text-left">V1.3.5</span><span class="text-right"
                                                                                                  style="float:right">更新时间2016-3-3</span>
                                                    </p>
                                                    <button type="submit" class="btn btn-success btn-block">SDK下载
                                                    </button>
                                                    <button type="submit" class="btn btn-success btn-block">Demo安装<img
                                                            src="../../img/iconfont-erweima(1).png"></button>
                                                </div>
                                                <div class="download_title download_radius download_under">
                                                    <p><span class="text-left">支持</span><span class="text-right"
                                                                                              style="float:right">历史版本</span>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <div class="download_title download_radius">
                                                    <h3><img src="../../img/ios.png"> <label>IOS</label></h3>
                                                </div>
                                                <div class="download_radius download ">
                                                    <p><span class="text-left">V1.3.5</span><span class="text-right"
                                                                                                  style="float:right">更新时间2016-3-3</span>
                                                    </p>
                                                    <button type="submit" class="btn btn-success btn-block">SDK下载
                                                    </button>
                                                    <button type="submit" class="btn btn-success btn-block">Demo安装<img
                                                            src="../../img/iconfont-erweima(1).png"></button>
                                                </div>
                                                <div class="download_title download_radius download_under">
                                                    <p><span class="text-left">支持</span><span class="text-right"
                                                                                              style="float:right">历史版本</span>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                    <div class="col-sm-6 col-md-4">
                                        <div class="thumbnail">
                                            <div class="caption">
                                                <div class="download_title download_radius">
                                                    <h3><img src="../../img/web.png"> <label>WEB</label></h3>
                                                </div>
                                                <div class="download_radius download ">
                                                    <p><span class="text-left">V1.3.5</span><span class="text-right"
                                                                                                  style="float:right">更新时间2016-3-3</span>
                                                    </p>
                                                    <button type="submit" class="btn btn-success btn-block">SDK下载
                                                    </button>
                                                    <p class="text-center">Web IM 源码</p>
                                                </div>
                                                <div class="download_title download_radius download_under">
                                                    <p><span class="text-left">支持</span><span class="text-right"
                                                                                              style="float:right">历史版本</span>
                                                    </p>
                                                </div>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                            </div>
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
                                    <div class="row data_radius">
                                        <%--      <ul>
                                                  <li class="active">历史使用人数统计</li>
                                                  <li>活跃人数</li>
                                                  <li>发消息人数</li>
                                                  <li>新增人数</li>
                                                  <li>新增人数</li>

                                              </ul>--%>
                                        <div id="main" style="width: 100%;height:400px;"></div>
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
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<!-- 修改应用 -->
<div class="modal fade" id="modify-apps" tabindex="-1" role="dialog" aria-labelledby="ModalLabel">
    <form action="/app/update" method="post" class="form-horizontal" id="modify_app_form" novalidate="novalidate">
        <div class="modal-dialog" role="document">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-left" id="ModalLabel">修改应用资料</h4>
                </div>
                <div class="modal-body">
                    <input type="hidden" name="appId" id="appId">
                    <div class="row">
                        <div class="form-group">
                            <label class="col-sm-3 control-label"> 应用名称</label>
                            <div class="col-sm-9">
                                <input type="text" class="form-control" name="appName" id="appName"
                                       placeholder="请输入应用名称" data-bv-field="appName">
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"> 应用简介</label>
                            <div class="col-sm-9">
                                <textarea class="form-control" rows="3" name="description" id="content_text"
                                          placeholder="请输入应用简介"></textarea>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"> 应用类型</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="category" id="category" data-bv-field="category">
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
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-3 control-label"> 运营阶段</label>
                            <div class="col-sm-9">
                                <label><input type="radio" name="appStage" id="appStage1" value="1"
                                              data-bv-field="appStage">未运营尚无用户</label>
                                <label><input type="radio" name="appStage" id="appStage2" value="2"
                                              data-bv-field="appStage">运营中已有用户</label>
                            </div>
                        </div>
                        <div class="form-group" id="user_level_div">
                            <label class="col-sm-3 control-label"> 用户量级</label>
                            <div class="col-sm-9">
                                <select class="form-control" name="appUserLevel" id="appUserLevel"
                                        data-bv-field="appUserLevel">
                                    <option value="">请选择用户量级</option>
                                    <option value="1">万级</option>
                                    <option value="2">十万级</option>
                                    <option value="3">百万级</option>
                                    <option value="4">千万级</option>
                                    <option value="5">亿级</option>
                                </select>
                            </div>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-xs-8">
                        <span class="help-block puii-left text-left " style="color: rgb(169, 68, 66); display: none;"
                              target="tips" id="modify-error">当前用户下已经存在这个 App 名称了</span>
                    </div>
                    <div>
                        <button type="submit" class="btn btn-primary">保存</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>
<!-- 删除应用 -->
<div class="modal fade" id="delete-app-modal" tabindex="-1" role="dialog" aria-labelledby="ModalLabel">
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
                        <div class="col-xs-3">
                            <span class="help-block" target="name" style="display: none;"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-3 control-label text-right">登录密码：</label>
                        <div class="col-sm-5">
                            <input class="form-control" placeholder="请输入您的登录密码" type="password" name="password"
                                   id="del-password">
                        </div>
                        <div class="col-xs-3">
                            <span class="help-block" target="password" style="display: none;"></span>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-xs-8">
                        <span class="help-block puii-left text-left" target="tips-del" style="display: none;"></span>
                    </div>
                    <div class="col-xs-4">
                        <button type="submit" class="btn btn-primary">删除应用</button>
                        <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
                    </div>
                </div>
            </div>
        </div>
    </form>
</div>

<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../js/jquery.form.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/responsive.tabs.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/echarts/3.0.0/echarts.min.js"></script>
<script type="text/javascript" src="../../js/overview.js"></script>
<script type="text/javascript" src="../../js/appdel.js"></script>
<script type="text/javascript" src="../../js/common.js"></script>
</body>
</html>
