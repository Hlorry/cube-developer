/**
 * Created by Administrator on 2016/3/14.
 */
$(function () {
    /**忘记密码验证**/
    $('#forget').bootstrapValidator({
        fields: {
            email: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: '邮件地址不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    },
                    callback: {
                        message: "用户邮箱不存在",
                        callback: function (value, validator, t) {
                            var res = true;
                            if (value != "") {
                                $.ajax({
                                    url: '/user/query',
                                    type: 'post',
                                    dataType: 'json',
                                    async: false,
                                    data: {email: value},
                                    success: function (data) {
                                        if (data.cube == undefined) {
                                            res = false;
                                        }
                                    }
                                });
                            }
                            return res;
                        }
                    }

                }
            },
            code_text: {
                validators: {
                    callback: {
                        message: '验证码错误',
                        callback: function (value, validator) {
                            if (value == null || value == "") {
                                return false;
                            }
                            value = value.toUpperCase();
                            var captcha = $("input[name='code']:text").val().toUpperCase();
                            return value == captcha;
                        }
                    },
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            }
        }

    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var email = $("#emailForId").val();
        $.ajax({
            url: "/auth/password/forget",
            type: 'POST',
            data: {
                "email": email
            },
            success: function (result) {
                if (result.state == 200) {
                    $('#alertBox').modal('show');
                    $("#message").html("验证信息已发送！请尽快验证你的邮箱。");
                    $("#forget").hide();
                    $("#Reset").show();
                }
            }
        });
    });


    $('#newPassword').bootstrapValidator({
        fields: {
            newPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    },
                    stringLength: {
                        min: 6,
                        max: 18,
                        message: '密码长度必须在6到18位之间'
                    },
                    regexp: {
                        regexp: /^[a-zA-Z0-9_]+$/,
                        message: '密码只能包含大写、小写、数字和下划线'
                    }
                }
            },
            repeatPassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }, identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    }
                }

            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var newPwd = $("#newPwd").val();
        $.ajax({
            url: "/auth/password/mailupdate",
            type: 'POST',
            data: {
                "actmd5": getUrlParam("token"),
                "id": getUrlParam("id"),
                "newPwd": newPwd
            },
            success: function (result) {
                if (result.state == 200) {
                    $('#alertBox').modal('show');
                    $("#message").html("密码重置成功！！");
                } else if (result.errcode == 10012) {
                    $('#alertBox').modal('show');
                    $("#message").html("密码重置验证已过期！请重新进行邮箱验证！");

                }
            }
        });
    })
    var state = getUrlParam("state");
    if (state != null && state == 200) {
        $("#forget").hide();
        $("#Reset").hide();
        $("#newPassword").show();
    }
    var errcode = getUrlParam("errcode");
    if (errcode != null && errcode == 10012) {
        $('#alertBox').modal('show');
        $("#message").html("密码重置验证已过期！请重新进行邮箱验证");
    }

    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);//匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    $("#back").click(function () {
        $("#Reset").hide();
        $("#forget").show();
    })


})
