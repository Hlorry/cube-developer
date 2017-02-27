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
                            <%--&lt;%&ndash;     <li><a href="/route/knowledge">知识库</a></li>&ndash;%&gt;--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                </ul>
                <form class="navbar-form  fr">
                    <a  href="/route/login">登入</a>
                    <a href="/route/register">注册</a>
                </form>
            </div><!--/.nav-collapse -->
        </div>
    </div>
</nav>
<div class="container menu_padding login_page">
    <div class="row">
        <div class="col-md-8">
            <form class="form-horizontal" id="forget">
                <div class="form-group">
                    <label class="col-sm-4"></label>
                    <div class="col-sm-8">
                        <h3>忘记密码</h3>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-5 col-xs-12 control-label">邮箱地址</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-youxiang-.png"></span>
                        <input type="text" class="form-control" id="emailForId" name="email" placeholder="请输入注册邮箱"
                               autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-5 col-xs-12 control-label">验证码</label>
                    <div class="col-sm-6 col-xs-12 code_box">
                        <div class="col-xs-6 col-sm-6" style="padding-left:0px;">
                            <input type="text" class="form-control proving" id="input1" name="code_text">
                        </div>
                        <div class="col-xs-6 col-sm-6">
                            <input type="text" name="code" readonly="readonly"
                                   id="checkCode" class="unchanged"/> <a href="#" onclick="createCode()">换一个</a>
                        </div>

                        <%--<input type="password" class="form-control" name="password" placeholder="请输入验证码">--%>
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-5"></div>
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block">下一步</button>
                        <p class="text-right"><a href="">联系客服？</a></p>
                    </div>
                </div>
            </form>
            <form class="form-horizontal" id="Reset">
                <div class="form-group">
                    <label class="col-sm-4"></label>
                    <div class="col-sm-4">
                        <h3>重置密码</h3>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-4 control-label"></label>
                    <div class="col-sm-4">
                        邮件已发送到<a href="#">123385***@163.com</a> 请尽快点击链接重置密码
                        链接30分钟内有效
                    </div>
                </div>

                <div class="form-group">
                    <div class="col-sm-4"></div>
                    <div class="col-sm-4">
                        <button type="submit" class="btn btn-success btn-block" id="back">返回上一步</button>
                    </div>
                </div>
            </form>
            <!--新密码设置-->
            <form class="form-horizontal" id="newPassword">
                <div class="form-group">
                    <label class="col-sm-4"></label>
                    <div class="col-sm-8">
                        <h3>设置新密码</h3>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-6 col-xs-12 control-label">新的密码</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-youxiang-.png"></span>
                        <input type="password" class="form-control" id="newPwd" name="newPassword" placeholder="请输入新密码"
                               autofocus>
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-6 col-xs-12 control-label">再次输入</label>
                    <div class="col-sm-6 col-xs-12">
                        <span class="form-control-feedback"><img src="../../img/iconfont-suo-.png"></span>
                        <input type="password" class="form-control" name="repeatPassword" placeholder="请再次输入密码">
                    </div>
                </div>
                <div class="form-group">
                    <div class="col-sm-6"></div>
                    <div class="col-sm-6">
                        <button type="submit" class="btn btn-success btn-block">确认</button>

                    </div>
                </div>
            </form>

        </div>
        <div class="col-md-3 col-md-offset-4"></div>
    </div>

</div>
<jsp:include page="public/footer.jsp"></jsp:include>
<script type="text/javascript" src="//cdn.bootcss.com/bootstrap-validator/0.5.3/js/bootstrapValidator.min.js"></script>
<script type="text/javascript" src="../../js/forget.js"></script>
<script type="text/javascript">

</script>

</body>
</html>