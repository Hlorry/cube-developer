<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/16
  Time: 13:17
  To change this template use File | Settings | File Templates.
--%>
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
    <%--<jsp:include page="public/header.jsp" flush="false" />--%>
    <link rel="stylesheet" href="../../css/case.css">
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
                    <%--           <li><a href="/route/product">知识库</a></li>--%>
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
<div class="container casepage menu_padding">
    <div class="tabs personal">
        <ul class="tabs-list ">
            <li class="active" id="personal"><a href="javascript:void(0);">个人信息</a></li>
            <li><a href="javascript:void(0);" id="certified">认证信息</a></li>
            <li><a href="javascript:void(0);">修改密码</a></li>
        </ul>
        <div class="tabs-container">
            <div class="tab-content" style="display:block;">
                <form action="/user/upload" class="form-horizontal" id="avatarFrm" method="post"
                      enctype="multipart/form-data">
                    <input type="hidden" id="avatarToken" name="token" value="">
                    <input type="hidden" id="avatarid" name="id" value="">
                    <div class="form-group col-sm-12 text-left">
                        <div class="col-sm-12">
                            <a class="app_list_detail file">
                                <img src="../../img/update_bg.jpg" id="avatarImg" width="69" height="69">
                                <span></span>
                                <input type="file" name="avatar" id="avatarInput" role="button" value="上传"
                                       data-bv-field="license"></a>
                        </div>
                    </div>
                </form>
                <form class="form-horizontal">
                    <div class="form-group">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>当前身份：</b></div>
                            <div class="col-sm-6 control-label text-left" id="userType"></div>
                        </div>

                    </div>
                    <div class="form-group ">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>开发者名称：</b></div>
                            <div class="col-sm-6 control-label text-left">
                                <div class='Name_box'>
                                    <div id="developName"></div>
                                    <span id="editorName">修改开发者名称</span>
                                </div>
                                <div class='input_box' style="display: none">
                                    <input type='text' class='form-control' id='inputValue'
                                           value=''>
                                    <span id='Namesure'>确认</span>
                                    <span id="Namecancel">取消</span>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>手机号：</b></div>
                            <div class="col-sm-6 control-label text-left">
                                <div id="devePhone"></div>
                                <div id="phoneHtml"><span id="information">绑定手机</span></div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>邮箱：</b></div>
                            <div class="col-sm-6 control-label text-left" id="deveEmail">123385059@163.com</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>创建时间:</b></div>
                            <div class="col-sm-6 control-label text-left" id="create_time">2016-03-02 10：:55</div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-6">
                            <div class="col-sm-4 control-label text-left"><b>认证信息：</b></div>
                            <div class="col-sm-6 control-label text-left">
                                <div id="devecrip"></div>
                            </div>
                        </div>
                    </div>
                </form>
            </div>
            <div class="tab-content" style="display: none;">
                <form action="/auth/personal/save" method="post" enctype="multipart/form-data"
                      class="form-horizontal login_page" id="PersonalText">
                    <input type="hidden" id="token" name="token" value="">
                    <input type="hidden" id="userId" name="id" value="">
                    <div class="form-group text-center">
                        <div class="col-sm-12">
                            <a href="#" class="alert-link">温馨提示：为了您的账户安全，请进行身份验证</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">真实姓名</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="plName" name="plName">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label first_label">证件类型</label>
                        <div class="col-sm-4">
                            <span id="plTypeDiv"></span>
                            <span id="pltypespan">
                                <label><input type="radio" name="plType" value="1" checked>身份证</label>
                                <label><input type="radio" name="plType" value="2">护照</label>
                            </span>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">证件号码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="plCardNum" name="plCardNum">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">证件电子版</label>
                        <div class="col-sm-4" id="IdCard">
                            <div class="col-xs-6 col-sm-6">
                                <div class="thumbnail">
                                    <div class="img_box">
                                        <div class="img_box_label"><img data-src="holder.js/100%x244" id="positiveImg"
                                                                        alt="100%x244"
                                                                        src=""
                                                                        data-holder-rendered="true"
                                                                        style="width:100%;height: 100%">身份证正面
                                        </div>
                                    </div>
                                    <div class="caption" id="positiveIddiv">
                                        <a class="file">上传<input type="file" id="positiveId" name="plPositiveImg"
                                                                 role="button"
                                                                 value="上传"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 file_text">
                                <div class="thumbnail">
                                    <div class="img_box">
                                        <div class="img_box_label"><img data-src="holder.js/100%x244" id="plSideImg"
                                                                        alt="100%x244"
                                                                        src=""
                                                                        data-holder-rendered="true"
                                                                        style="width:100%;height: 100%">身份证反面
                                        </div>
                                    </div>
                                    <div class="caption" id="plSideIddiv">
                                        <a class="file">上传<input type="file" id="plSideId" name="plSideImg"
                                                                 role="button" value="上传"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 ">
                                <a href="#" class="alert-link">请上传真实有效、图像清晰身份证电子版。图片大小请控制在2M之，图片格式支持JPG/JPEG/PNG</a>
                            </div>
                        </div>
                        <div class="col-sm-4" id="passport">
                            <div class="col-xs-12 col-sm-12">
                                <div class="thumbnail">
                                    <div class="img_box">
                                        <div class="img_box_label"><img data-src="holder.js/100%x244" id="passportImg"
                                                                        alt="100%x244"
                                                                        src=""
                                                                        data-holder-rendered="true"
                                                                        style="width:100%;height: 100%">护照
                                        </div>
                                    </div>
                                    <div class="caption" id="passportIddiv">
                                        <a class="file">上传<input type="file" id="passportId" name="passport"
                                                                 role="button" value="上传"></a>
                                    </div>
                                </div>
                                <a href="#" class="alert-link">请上传真实有效、图像清晰身份证电子版。图片大小请控制在2M之，图片格式支持JPG/JPEG/PNG</a>
                            </div>

                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">手持证件照片</label>
                        <div class="col-sm-4">
                            <div class="col-xs-12 col-sm-12">
                                <div class="thumbnail">
                                    <div class="img_box" style="height:340px">
                                        <div class="img_box_label" style="height:300px;"><img
                                                data-src="holder.js/100%x300" id="plHidnumberImg" alt="100%x244"
                                                src=""
                                                data-holder-rendered="true"
                                                style="width:100%;height: 300px">护照
                                        </div>
                                    </div>
                                    <div class="caption" id="plHidnumberIddiv">
                                        <a class="file">上传<input type="file" id="plHidnumberId" name="plHidnumber"
                                                                 role="button" value="上传"></a>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-12 col-sm-12 ">
                                <a href="#" class="alert-link">照片中的信息必须真实有效手持证件人五官清晰可见身份证上的所有信息清晰可见</a>
                            </div>
                        </div>
                    </div>
                    <div class="form-group" id="Personalhiesd">
                        <div class="col-sm-4 text-right">
                        </div>
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-success btn-block">下一步</button>
                        </div>
                    </div>
                    <div class="form-group" id="PersonalShow" style="display: none">
                        <div class="col-sm-4 text-right">
                        </div>
                        <div class="col-sm-4">
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-default btn-block" id="backEditor">返回修改</button>
                            </div>
                            <div class="col-sm-6">

                                <button type="submit" class="btn btn-success btn-block" id="sureEditor">提交认证</button>
                            </div>
                        </div>
                    </div>
                </form>
                <%--<form class="form-horizontal login_page" id="PersonalShow" style="display: none">
                    <div class="form-group text-center">
                        <div class="col-sm-12">
                            <a href="#" class="alert-link">温馨提示：为了您的账户安全，请进行身份验证</a>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">真实姓名</label>
                        <div class="col-sm-4">
                            时信
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">身份证号</label>
                        <div class="col-sm-4">
                            31244234234343423
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">证件电子版</label>
                        <div class="col-sm-4">
                            <div class="col-xs-6 col-sm-6">
                                <div class="thumbnail">
                                    <div class="img_box">
                                        <div class="img_box_label"><img data-src="holder.js/100%x244"
                                                                        alt="100%x244"
                                                                        src=""
                                                                        data-holder-rendered="true"
                                                                        style="width:100%;height: 100%">身份证正面
                                        </div>
                                    </div>
                                </div>
                            </div>
                            <div class="col-xs-6 col-sm-6 file_text">
                                <div class="thumbnail">
                                    <div class="img_box">
                                        <div class="img_box_label"><img data-src="holder.js/100%x244"
                                                                        alt="100%x244"
                                                                        src=""
                                                                        data-holder-rendered="true"
                                                                        style="width:100%;height: 100%">身份证反面
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>

                    </div>
                    <div class="form-group">
                        <label class="col-sm-4 control-label">手持证件照片</label>
                        <div class="col-sm-4">
                            <div class="col-xs-12 col-sm-12">
                                <div class="thumbnail">
                                    <div class="img_box" style="height:340px">
                                        <div class="img_box_label" style="height:300px;"><img
                                                data-src="holder.js/100%x300" alt="100%x244"
                                                src=""
                                                data-holder-rendered="true"
                                                style="width:100%;height: 300px">护照
                                        </div>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4 text-right">
                        </div>
                        <div class="col-sm-4">
                            <div class="col-sm-6">
                                <button type="button" class="btn btn-default btn-block" id="backEditor">返回修改</button>
                            </div>
                            <div class="col-sm-6">

                                <button type="submit" class="btn btn-success btn-block" id="sureEditor">提交认证</button>
                            </div>
                        </div>
                    </div>
                </form>--%>
            </div>
            <div class="tab-content login_page" style="display: none;">
                <form class="form-horizontal" id="changePassword">
                    <div class="form-group ">
                        <label class="col-sm-4 control-label">当前密码</label>
                        <div class="col-sm-4">
                            <input type="text" class="form-control" id="oldPassword" name="oldPassword">
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-4 control-label">新的密码</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" id="newPwd" name="newPassword">
                        </div>
                    </div>
                    <div class="form-group ">
                        <label class="col-sm-4 control-label">再次输入</label>
                        <div class="col-sm-4">
                            <input type="password" class="form-control" name="repeatpassword">
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4"></div>
                        <div class="col-sm-4">
                            <button type="submit" class="btn btn-success btn-block">修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<div class="case_footer">
    <p>Copyright 2015 RONGCLOUD 京ICP备15042119号-1</p>
</div>
<jsp:include page="public/alert.jsp"></jsp:include>
<script type="text/javascript" src="../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/responsive.tabs.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/echarts/3.0.0/echarts.min.js"></script>
<script type="text/javascript" src="../../js/dataformat.js"></script>
<script type="text/javascript" src="../../js/personal.js"></script>
<script type="text/javascript" src="../../js/previewImage.js"></script>
<script type="text/javascript" src="../../js/footer.js"></script>
</body>
</html>
