<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/21
  Time: 17:12
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<div class="modal fade bs-example-modal-sm " id="alertBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" id="message">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-primary"
                        data-dismiss="modal" id="alertBoxBtn">确认
                </button>
            </div>
        </div>
    </div>
</div>
<%--修改信息--%>
<div class="modal fade add table_text bs-example-modal-sm " id="modify" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header text-left">
                修改信息
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label text-left">用户名：</label>
                        <div class="col-sm-10 text-left">
                            32423rfer
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label text-left">昵称：</label>
                        <div class="col-sm-10">
                            <input type="password" class="form-control" id="name" placeholder="小猪"
                                   style="max-width:200px">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="modifySure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--删除用户--%>
<div class="modal fade add table_text bs-example-modal-sm " id="delete" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog bs-example-modal-sm" style="max-width:400px;">
        <div class="modal-content">
            <div class="modal-header text-left" id="modeText">
                <%-- 确定要删除用户<span class="text_green">32423rfer</span>么？--%>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="deleteSure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--重置密码--%>
<div class="modal fade add table_text bs-example-modal-sm " id="impassword" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog bs-example-modal-sm">
        <div class="modal-content">
            <div class="modal-header text-left">
                重置密码
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-12 control-label text-left">输入新密码：</label>
                        <div class="col-sm-12 text-left">
                            <input type="password" class="form-control" id="newPasswords"
                                   placeholder="请输入新密码（1位以上数字或字母）">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-12 control-label text-left">再次输入密码：</label>
                        <div class="col-sm-12">
                            <input type="password" class="form-control" id="repeatPassword" placeholder="请再次输入密码">
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="impasswordSure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--发送消息--%>
<div class="modal fade add table_text bs-example-modal-sm " id="imSend" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog bs-example-modal-sm">
        <div class="modal-content">
            <div class="modal-header text-left">
                发送消息
            </div>
            <div class="modal-body">
                <form class="form-horizontal">
                    <div class="form-group row">
                        <label class="col-sm-12 control-label text-left">发送图片：</label>
                        <div class="col-sm-12 text-left">
                            <div class="row">
                                <div class="col-sm-8 text-left">
                                    <input type="password" class="form-control">
                                </div>
                                <div class="col-sm-4 text-left">
                                    <a class="file model_flie">上传<input type="file" id="yingye" name="license"
                                                                        role="button" value="上传"></a>
                                </div>
                            </div>


                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-12 control-label text-left">输入文字：</label>
                        <div class="col-sm-12">
                            <textarea class="form-control" rows="3"></textarea>
                        </div>
                    </div>

                </form>
            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="imSendSure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
        </div>
    </div>
</div>
<%--短信认证--%>
<div class="modal fade add table_text bs-example-modal-sm" id="informations" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog bs-example-modal-sm">
        <div class="modal-content">
            <div class="modal-header text-left">
                手机号验证
            </div>
            <form class="form-horizontal" id="inform">
            <div class="modal-body">

                    <div class="form-group" style="margin-bottom:10px;">
                        <label class="col-sm-3 control-label text-left" style="margin:0px;">请输入手机号码：</label>
                        <div class="col-sm-8">
                            <input type="text" name="infoPhone" id="inputPhones" class="form-control" placeholder="请输入手机号码">
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:10px;">
                        <label class="col-sm-3 control-label text-left" style="margin:0px;"></label>
                        <div class="col-sm-9 code_box">
                            <div class="col-xs-6 col-sm-4" style="padding-left:0px;">
                                <input type="text" class="form-control proving" id="input1" name="code_text"
                                       placeholder="请输入验证码">
                            </div>
                            <div class="col-xs-6 col-sm-6">
                                <input type="text" name="code" readonly="readonly"
                                       id="checkCode" class="unchanged"/> <a href="#" onclick="createCode()">换一个</a>
                            </div>
                            <%--<input type="password" class="form-control" name="password" placeholder="请输入验证码">--%>
                        </div>
                    </div>
                    <div class="form-group" style="margin-bottom:10px;">
                        <label class="col-sm-3 control-label text-left" style="margin:0px;"></label>
                        <div class="col-sm-9">
                            <div class="col-xs-6 col-sm-4" style="padding-left:0px;">
                                <input type="text" name="SMS" id="SMSInput" class="form-control" placeholder="请输入验证码">
                            </div>
                            <div class="col-xs-6 col-sm-8 code_btn">
                                <button type="button" id="msmyanzheng" class="btn btn-primary">发送验证码</button>
                            </div>
                            <%--<input type="password" class="form-control" name="password" placeholder="请输入验证码">--%>
                        </div>
                    </div>

            </div>
            <div class="modal-footer">
                <button type="submit" class="btn btn-primary" id="informationsSure">确定</button>
                <button type="button" class="btn btn-default" data-dismiss="modal">取消</button>
            </div>
            </form>
        </div>
    </div>
</div>
<%--ie--%>
<div class="modal fade bs-example-modal-sm " id="iealertBox" tabindex="-1" role="dialog"
     aria-labelledby="myModalLabel" aria-hidden="true">
    <div class="modal-dialog">
        <div class="modal-content">
            <div class="modal-header" id="ieMessage">
            </div>
            <div class="modal-footer">
                <button type="button" class="btn btn-default btn-primary"
                        data-dismiss="modal" id="ieBoxBtn">继续浏览
                </button>
            </div>
        </div>
    </div>
</div>