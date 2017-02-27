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
            <div id="navbar" class="navbar-collapse collapse navbar-right " aria-expanded="false" style="height: 1px;">
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
                            <%--&lt;%&ndash;   <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
                <%if (token == null || token == "") {%>
                <form class="navbar-form fr">
                    <a href="/route/login">登入</a>
                    <a class="active" href="/route/register">注册</a>
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
<div class="container menu_padding login_page register_page">
    <div class="row">
        <div class="register_progress " id="register_progress">
            <div class="register_personal">
                <ul class="register_step">
                    <li class="steps step1 active"><span><img src="../../img/iconfont-pen.png"> </span> <b>1.填写注册信息</b>
                    </li>
                    <li class="steps step2"><span><img src="../../img/register_email.png"> </span><b>2.邮箱验证</b></li>
                    <li class="steps step_last"><span><img src="../../img/register_ok.png"></span><b>3完成</b></li>
                </ul>
                <div class="step_progress step1">
                    <p class="step_progress_bar">
                        <span class="step_progress_bar_highlight"></span>
                    </p>
                </div>
            </div>
        </div>
        <div class="register_progress enterprise_progress" id="enterprise_progress">
            <div class="register_personal">
                <ul class="register_step">
                    <li class="steps step1 active"><span><img src="../../img/iconfont-pen.png"> </span> <b>1.填写注册信息</b>
                    </li>
                    <li class="steps step2"><span><img src="../../img/register_email.png"> </span><b>2.企业认证</b></li>
                    <li class="steps step3"><span><img src="../../img/register_email.png"> </span><b>2.邮箱验证</b></li>
                    <li class="steps step_last"><span><img src="../../img/register_ok.png"></span><b>3完成</b></li>
                </ul>
                <div class="step_progress step1">
                    <p class="step_progress_bar">
                        <span class="step_progress_bar_highlight"></span>
                    </p>
                </div>
            </div>
        </div>
        <div class="col-md-8 col-md-offset-1">
            <form action="/user/register" method="post" class="form-horizontal register_form" id="register_form">
                <p style="margin-bottom:20px;">通过[企业开发者]认证的企业可得到优先的技术支持及更多的功能权限</p>
                <div class="form-group register_label text-left ">
                    <label class="col-xs-3 col-sm-4 control-label first_label">用户类型</label>
                    <div class="col-xs-9 col-sm-6">
                        <label><input type="radio" name="userType" value="1" checked>个人开发者</label>
                        <label><input type="radio" name="userType" value="2">企业开发者</label>
                    </div>

                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label"> 企业/个人名称</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/username.png"></span>
                        <input type="text" class="form-control" name="name" placeholder="请输入企业/个人名称"
                               autofocus>
                        <%--    <input type="email" class="form-control" id="inputEmail3" placeholder="请输入注册邮箱/手机号码">--%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">邮箱地址</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-youxiang-.png"></span>
                        <input type="text" class="form-control" name="email" placeholder="请输入注册邮箱"
                               autofocus>
                        <%--    <input type="email" class="form-control" id="inputEmail3" placeholder="请输入注册邮箱/手机号码">--%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">密码</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-suo-.png"></span>
                        <input type="password" class="form-control" name="password" placeholder="请输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">确认密码</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-suo-.png"></span>
                        <input type="password" class="form-control" name="passwordrepeat"
                               placeholder="重复输入密码">
                    </div>
                </div>
                <%--     <div class="form-group">
                         <label class="col-sm-4 control-label">联系电话</label>
                         <div class="col-sm-6">
                             <span class="form-control-feedback"><img src="../../img/register_phone.png"></span>
                             <input type="text" class="form-control" name="phone" placeholder="请输入联系电话">
                         </div>
                     </div>--%>
                <div class="form-group ">
                    <div class="col-sm-4 text-right">
                    </div>
                    <div class="col-sm-6 register_checked text-left">
                        <span id="channel"><img src="../../img/checked-h.png"></span> 您是通过什么途径了解魔方产品的
                        <div id="channel_box">
                            <div class="radio">
                                <label>
                                    <input type="radio" name="way" value="1" checked/> 朋友推荐
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="way" value="2"/> 互联网搜索
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="way" value="3"/> 官方账号
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="way" value="4"/> 会展活动
                                </label>
                            </div>
                            <div class="radio">
                                <label>
                                    <input type="radio" name="way" value="5"/> 媒体报道
                                </label>
                            </div>
                        </div>

                        <p><label><input type="checkbox" name="sure" checked> 我已阅读并同意</label> <a
                                href="javascript:void(0)" id="service">《魔方Cube服务条款》</a>
                        </p>
                    </div>

                </div>

                <div class="form-group">
                    <div class="col-sm-4 text-right">
                    </div>
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block">下一步</button>
                        <p class="text-right">已有账号？<a href="/route/login">立即登入？</a></p>
                    </div>
                </div>


            </form>
            <form action="/auth/certified/save" method="post" enctype="multipart/form-data"
                  class="form-horizontal register_form" id="enterprise_form">
                <input type="hidden" id="certifiedUserId" name="id" value="">
                <input type="hidden" id="certifiedEmail" name="email" value="">
                <input type="hidden" id="level" name="level" value="">
                <p style="margin-bottom:20px;">温馨提示：如果暂时资料准备不齐全无法完成企业认证，可点击此处跳过认证直接注册</p>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label"> 企业名称</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="cpName" placeholder="请输入企业名称"
                               autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">企业地址</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="cpAddress" placeholder="请输入企业地址"
                               autofocus>
                        <%--    <input type="email" class="form-control" id="inputEmail3" placeholder="请输入注册邮箱/手机号码">--%>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">营业执照号</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="licenseNum"
                               placeholder="请输入营业执照号或统一社会信用代码">
                        <p class="text-left">例如1101011032765943</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">营业执照</label>
                    <div class="col-sm-6 col-xs-12">
                        <div class="col-xs-6 col-sm-6">
                            <div class="thumbnail">
                                <div><img data-src="holder.js/100%x244" id="yingyeImg" alt="100%x244"
                                          src="../../img/zhizhao.png"
                                          data-holder-rendered="true"
                                          style="max-height: 244px; width: 233px; display: block;"></div>
                                <div class="caption">
                                    <a class="file">上传<input type="file" id="yingye" name="license"
                                                             role="button" value="上传"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-6 file_text">
                            <p>通过年检的复印件盖章后，请上传真实有效的扫描件、照片、控制图片大小在 2M 范围内，支持 JPG / JPEG / PNG</p>
                            <b>如您是三证合一的企业在此上传营业执照即可，不用再次填写或上传组织机构代码证和税务</b>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">组织机构号</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="agency"
                               placeholder="组织机构号">
                        <p class="text-left">例如110036743</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4  col-xs-12 control-label">组织机构证</label>
                    <div class="col-sm-6 col-xs-12 ">
                        <div class="col-xs-6 col-sm-6">
                            <div class="thumbnail">
                                <div><img data-src="holder.js/100%x244" id="jigouImg" alt="100%x244"
                                          src="../../img/jigou.png"
                                          data-holder-rendered="true"
                                          style="max-height: 244px; width: 233px; display: block;"></div>
                                <div class="caption">
                                    <a class="file">上传<input type="file" id="jigou" name="agencyImg"
                                                             role="button" value="上传"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-6 file_text">
                            <p>通过年检的复印件盖章后，请上传真实有效的扫描件、照片、控制图片大小在 2M 范围内，支持 JPG / JPEG / PNG</p>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">税务登记号</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="taxNum"
                               placeholder="请输入15位数字税务登记号">
                        <p class="text-left">例如110101032765943</p>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">税务登记证</label>
                    <div class="col-sm-6 col-xs-12">
                        <div class="col-xs-6 col-sm-6">
                            <div class="thumbnail">
                                <div><img data-src="holder.js/100%x244" id="suiwuImg" alt="100%x244"
                                          src="../../img/shuiwu.png"
                                          data-holder-rendered="true"
                                          style="max-height: 244px; width: 233px; display: block;"></div>
                                <div class="caption">
                                    <a class="file">上传<input type="file" id="suiwu" name="taxImg"
                                                             class="btn btn-primary" role="button"
                                                             value="上传"></a>
                                </div>
                            </div>
                        </div>
                        <div class="col-xs-6 col-sm-6 file_text">
                            <p>通过年检的复印件盖章后，请上传真实有效的扫描件、照片、控制图片大小在 2M 范围内，支持 JPG / JPEG / PNG</p>
                        </div>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">法定代表</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="corporate"
                               placeholder="请输入企业法定代表名称">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">企业电话</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="cpPhone"
                               placeholder="请输入企业联系电话">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 col-xs-12 control-label">企业网站</label>
                    <div class="col-sm-6 col-xs-12">
                        <input type="text" class="form-control" name="cpWebsite"
                               placeholder="请输入企业网站">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-4 text-right">
                    </div>
                    <div class="col-sm-6">
                        <input type="submit" class="btn btn-success btn-block" value="下一步"/>
                        <p class="text-right">跳过验证<a href="javascript:void(0)" id="endReg">直接注册</a></p>
                    </div>
                </div>
            </form>
            <div class="form-horizontal register_form" id="register_email">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object" data-src="holder.js/64x64" alt="64x64"
                                 src="../../img/iconfont-youxiang.png" style="width: 55px; height: 53px;">
                        </a>
                    </div>
                    <div class="media-body register_media text-left">
                        <h4 class="media-heading">邮箱验证</h4>
                        <p>请查收您的邮箱<span id="emailSure">666666@66.com </span>前往邮箱验证后可创建App</p>
                    </div>
                    <div class="form-group">
                        <div class="col-md-5 col-md-offset-1">
                            <button type="button" class="btn btn-success btn-block" id="emailActivation">进行邮箱验证</button>
                            <p class="text-right">跳过验证 <a href="/route/login" id="completeRegister">完成注册？</a></p>
                            <ul>
                                <li><h4>如果邮箱没有收到邮件可尝试以下方法：</h4></li>
                                <li>1.检查邮件是否在垃圾邮件中</li>
                                <li id="testHtml">2.请尝试<a href="javascript:void(0);" id="restSend">重新发送</a>，验证邮件</li>
                                <li>3.如长时间未能收到验证邮件，请<a href="">联系客服</a></li>
                            </ul>
                        </div>
                    </div>

                </div>
            </div>
            <div class="register_sucess register_form" id="register_sucess">
                <div class="media">
                    <div class="media-left">
                        <a href="#">
                            <img class="media-object" data-src="holder.js/64x64" alt="64x64"
                                 src="../../img/register_sucess.png" style="width: 53px; height: 53px;">
                        </a>
                    </div>
                    <div class="media-body text-left">
                        <h4 class="media-heading">邮箱验证</h4>
                    </div>
                    <p class="text-left">系统将在5秒钟后跳转，如果不想等待请点击<a href="/route/login">跳转</a> 。</p>
                </div>
            </div>
        </div>
        <div class="col-md-3 col-md-offset-4"></div>
    </div>
</div>
<!-- 服务条款 -->
<div class="modal fade add" id="register_service" tabindex="-1" role="dialog" aria-labelledby="register_service">
    <div class="form-horizontal">
        <div class="modal-dialog">
            <div class="modal-content">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal" aria-label="Close"><span
                            aria-hidden="true">&times;</span></button>
                    <h4 class="modal-title text-left">网站注册服务条款</h4>
                </div>
                <div class="modal-body">
                    <div class="">
                        <div class="text-left">
                            <p>尊敬的用户，欢迎您注册成为本网站用户。在注册前请您仔细阅读如下服务条款： 本服务协议双方为本网站与本网站用户，本服务协议具有合同效力。 </p>
                            <p>您确认本服务协议后，本服务协议即在您和本网站之间产生法律效力。请您务必在注册之前认真阅读全部服务协议内容，如有任何疑问，可向本网站咨询。 </p>
                            <p>论您事实上是否在注册之前认真阅读了本服务协议，只要您点击协议正本下方的"注册"按钮并按照本网站注册程序成功注册为用户，您的行为仍然表示您同意并签署了本服务协议。</p>
                            <h4>1．本网站服务条款的确认和接纳</h4><br>
                            <p>本网站各项服务的所有权和运作权归本网站拥有。</p>
                            <h4>3．用户在本网站上交易平台上不得发布下列违法信息： </h4><br>
                            <p>(1)反对宪法所确定的基本原则的； (2).危害国家安全，泄露国家秘密，颠覆国家政权，破坏国家统一的； (3).损害国家荣誉和利益的；
                                (4).煽动民族仇恨、民族歧视，破坏民族团结的； (5).破坏国家宗教政策，宣扬邪教和封建迷信的； (6).散布谣言，扰乱社会秩序，破坏社会稳定的；
                                (7).散布淫秽、色情、赌博、暴力、凶杀、恐怖或者教唆犯罪的； (8).侮辱或者诽谤他人，侵害他人合法权益的； (9).含有法律、行政法规禁止的其他内容的。</p>
                            <h4></h4>
                        </div>
                    </div>
                </div>
                <div class="modal-footer">
                    <div class="col-xs-12">
                        <button type="button" class="btn btn-primary" data-dismiss="modal">确认</button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.bootcss.com/iCheck/1.0.2/icheck.min.js"></script>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/register.js"></script>
<script src="http://code.jquery.com/jquery-migrate-1.2.1.js"></script>
<script type="text/javascript" src="../../js/previewImage.js"></script>
</body>
</html>