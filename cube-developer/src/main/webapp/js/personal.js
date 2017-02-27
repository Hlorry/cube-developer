/**
 * Created by Administrator on 2016/3/16.
 */
var a = 0;
$(function () {
    var b = $("body");
    /**tabs**/
    $('.tabs').respTabs({
        responsive: false
    });
    /**checked**/
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });
    /**个人信息验证**/
    $('#PersonalText').bootstrapValidator({
        fields: {
            plName: {
                validators: {
                    notEmpty: {
                        message: '姓名不能为空'
                    }
                }
            },
            plCardNum: {
                validators: {
                    notEmpty: {
                        message: '证件号码不能为空'
                    }
                }
            },
            plPositiveImg: {
                validators: {
                    callback: {
                        message: "请上传正面身份证",
                        callback: function (value, validator, t) {
                            var res = true;
                            var plType = $("input[name=plType]:checked").val();
                            if (plType == 1) {
                                if (value == undefined || value == null || value == "") {
                                    res = false;
                                }
                            }
                            return res;
                        }
                    }
                }
            },
            plSideImg: {
                validators: {
                    callback: {
                        message: "请上传反面身份证",
                        callback: function (value, validator, t) {
                            var res = true;
                            var plType = $("input[name=plType]:checked").val();
                            if (plType == 1) {

                                if (value == undefined || value == null || value == "") {
                                    res = false;
                                }
                            }
                            return res;
                        }
                    }
                }
            },
            passport: {
                validators: {
                    callback: {
                        message: "请上传护照",
                        callback: function (value, validator, t) {
                            var res = true;
                            var plType = $("input[name=plType]:checked").val();
                            if (plType == 2) {
                                if (value == undefined || value == null || value == "") {
                                    res = false;
                                }
                            }
                            return res;
                        }
                    }
                }
            },
            plHidnumber: {
                validators: {
                    callback: {
                        message: "请上传手持证件",
                        callback: function (value, validator, t) {
                            var res = true;
                            if (value == undefined || value == null || value == "") {
                                res = false;
                            }
                            return res;
                        }
                    }
                }
            }
        }

    }).on('success.form.bv', function (e) {
        if (a == 0) {
            e.preventDefault();
        }
        $("#Personalhiesd").hide();
        $("#PersonalShow").show();
        $("#plName").attr("readonly", true);
        $("#plCardNum").attr("readonly", true);
        $("#positiveIddiv").hide();
        $("#plSideIddiv").hide();
        $("#passportIddiv").hide();
        $("#plHidnumberIddiv").hide();
        $("#pltypespan").hide();
        var pl = $("input[name=plType]:checked").val()
        $("#plTypeDiv").html(pl == 1 ? "身份证" : "护照")
        $("#sureEditor").attr("disabled", false)

    });
    $("#backEditor").click(function () {
        $("#Personalhiesd").show();
        $("#PersonalShow").hide();
        $("#plName").attr("readonly", false);
        $("#plCardNum").attr("readonly", false);
        $("#positiveIddiv").show();
        $("#plSideIddiv").show();
        $("#passportIddiv").show();
        $("#plHidnumberIddiv").show();
        $("input[name=plType]").attr("disabled", false);
    });
    $("#sureEditor").click(function () {
        a = 1
        $('#PersonalText').submit();
    });

    /*****修改密码*****/
    $('#changePassword').bootstrapValidator({
        fields: {
            oldPassword: {
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
            repeatpassword: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }, identical: {//相同
                        field: 'newPassword',
                        message: '两次密码不一致'
                    }
                }
            }

        }

    }).on('success.form.bv', function (e) {
        e.preventDefault();
        if (cookie.get("cube_develops_token") == null) {
            $('#alertBox').modal('show');
            $("#message").html("请先登陆");
        }
        var oldPassword = $("#oldPassword").val();
        var newPassword = $("#newPwd").val();
        $.ajax({
            url: '/auth/password/update',
            type: 'post',
            dataType: 'json',
            data: {
                token: cookie.get("cube_develops_token"),
                id: cookie.get("cube_develops_userId"),
                oldPwd: oldPassword,
                newPwd: newPassword
            },
            success: function (data) {
                if (data.state != undefined && data.state == 200) {
                    $('#alertBox').modal('show');
                    $("#message").html("密码修改成功");
                } else if (data.errcode != undefined && data.errcode == 10003) {
                    $('#alertBox').modal('show');
                    $("#message").html("旧密码输入错误！");
                } else if (data.errcode != undefined && data.errcode == 10014) {
                    $('#alertBox').modal('show');
                    $("#message").html("请先登陆你的账号!");
                } else if (data.errcode != undefined && data.errcode == 100) {
                    $('#alertBox').modal('show');
                    $("#message").html("未知错误，请重试。");
                }
            }
        });
    });
    /***身份证和护照选项切换***/
    var userType = $("input[name=plType]:checked").val();
    redioSwitching(userType);

    $('input[name="plType"]').on('ifClicked', function (data) {
        redioSwitching(data.currentTarget.defaultValue);
    });
    function redioSwitching(userType) {
        if (userType == 1) {
            $("#passport").hide();
            $("#IdCard").show();

        } else {
            $("#IdCard").hide();
            $("#passport").show();
        }
    }

    $("#positiveId").uploadPreview({
        Img: "positiveImg",
        Width: 150,
        Height: 180,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    $("#plSideId").uploadPreview({
        Img: "plSideImg",
        Width: 150,
        Height: 180,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    $("#passportId").uploadPreview({
        Img: "passportImg",
        Width: 150,
        Height: 180,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    $("#plHidnumberId").uploadPreview({
        Img: "plHidnumberImg",
        Width: 150,
        Height: 180,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    var findInfo = function () {
        $.ajax({
            url: "/auth/certified/find",
            type: 'POST',
            data: {
                token: cookie.get("cube_develops_token"),
                id: cookie.get("cube_develops_userId")
            },
            success: function (result) {
                var data = result.cube;
                if (data != undefined) {
                    $("#plName").val(data.plName);
                    $("input[name='plType']").eq(data.plType).attr("checked", "checked");
                    $("#plCardNum").val(data.plCardNum);
                    $("#positiveImg").attr('src', data.plPositiveImg);
                    $("#plSideImg").attr('src', data.plSideImg);
                    $("#passportImg").attr('src', data.passport);
                    $("#plHidnumberImg").attr('src', data.plHidnumber);
                }
            }
        });
    };

    var pers = function () {
        $.ajax({
            url: "/user/query",
            type: 'POST',
            data: {
                token: cookie.get("cube_develops_token"),
                id: cookie.get("cube_develops_userId")
            },
            success: function (result) {
                var data = result.cube;
                if (data != undefined) {
                    if (data.usertype == 1) {
                        $("#userType").html("个人开发者 <span href='javascript:void(0)' id='level'>升级为企业开发者</span>")
                    } else if (data.usertype == 2) {
                        $("#userType").html("企业开发者");
                    }
                    $("#developName").html(data.name);
                    if (data.phone_verify == 1) {
                        $("#phoneHtml").html("")
                    }
                    $("#devePhone").html(data.phone);
                    if (data.activation == 0) {
                        $("#deveEmail").html("<div id='emailInfo'>" + data.email + "</div>&nbsp;&nbsp;&nbsp;&nbsp;<span id='emailSendInfo'><span  id='sendEmail'>未验证，立即验证</span></span>");
                    } else {
                        $("#deveEmail").html(data.email);
                    }
                    if (data.avatar != null && data.avatar != "http://www.getcube.cn") {
                        $("#avatarImg").attr("src", data.avatar);
                    }

                    $("#create_time").html(new Date(data.create_time).format("yyyy-mm-dd HH:MM:ss"));
                    if (data.biz_verify == 0) {
                        $("#devecrip").html("未认证 <span href='javascript:void(0)' id='verify_info'>立即认证</span>");
                    } else if (data.biz_verify == 1) {
                        $("#devecrip").html("认证用户");
                    }
                }
            }
        });
    };
    pers();
    $("#personal").click(function () {
        pers();
    });

    $("#certified").click(function () {
        findInfo();
    });
    b.on("click", "#verify_info", function () {
        $('.tabs').respTabs({
            startIndex: 1
        });
        findInfo();
    });

    b.on("click", "#level", function () {
        $.ajax({
            url: "/auth/certified/queryByUserId",
            type: 'POST',
            data: {
                token: cookie.get("cube_develops_token"),
                userId: cookie.get("cube_develops_userId")
            },
            success: function (result) {
                if (result.state == 200) {
                    window.location.href = "/route/register?level=1"
                } else if (result.errcode == 10019) {
                    $('#alertBox').modal('show');
                    $("#message").html("你已上传企业证人信息，请耐心等待审核结果");
                } else if (result.errcode == 10016) {
                    $('#alertBox').modal('show');
                    $("#message").html("无权操作，请重新登录再试");
                }
            }
        });

    });

    b.on("click", "#sendEmail", function () {
        var email = $("#emailInfo").html();
        $.ajax({
            url: "/auth/verify",
            type: 'POST',
            data: {
                "email": email
            },
            success: function (result) {
                if (result.state == 200) {
                    $("#emailSendInfo").html("验证信息已发送")
                }
            }
        });
    });

    if (cookie.get("cube_develops_token") == null) {
        $('#alertBox').modal('show');
        $("#message").html("请先登陆");
        location.href = "/route/login";
    } else {
        if (cookie.get("cube_develops_userId") == null) {
            $('#alertBox').modal('show');
            $("#message").html("请先登陆");
            location.href = "/route/login";
        } else {
            $("#userId").val(cookie.get("cube_develops_userId"))
            $("#token").val(cookie.get("cube_develops_token"))
        }
    }

    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);//匹配目标参数
        if (r != null) return decodeURI(r[2]);
        return null; //返回参数值
    }

    var state = getUrlParam("state");
    var success = getUrlParam("success");
    var errcode = getUrlParam("errcode");
    var errmsg = getUrlParam("errmsg");
    if (state != null && state == 200) {
        $('#alertBox').modal('show');
        $("#message").html(success);
    } else if (errcode == 10019) {
        $('#alertBox').modal('show');
        $("#message").html(errmsg);
    }
    $("body").on("click", "#alertBoxBtn", function () {
        window.location.href = "/route/personal";
    })

    //修改开发者名称
    $("#editorName").click(function () {
        var Namebox = $("#developName");
        var developNameValue = Namebox.text();
        $(".Name_box").hide();
        $(".input_box").show();
        $("#inputValue").val(developNameValue);
        $("#Namesure").click(function () {
            var name = $("#inputValue").val();
            if (name != undefined && name != "") {
                $.ajax({
                    url: "/user/updateUserName",
                    type: 'POST',
                    data: {
                        token: cookie.get("cube_develops_token"),
                        id: cookie.get("cube_develops_userId"),
                        name: name
                    },
                    success: function (result) {
                        if (result.state == 200) {
                            $('#alertBox').modal('show');
                            $("#message").html(result.success);
                            $(".Name_box").show();
                            $(".input_box").hide();
                            $("#developName").text($("#inputValue").val());
                            pers();
                        } else if (result.errcode == 10017) {
                            $('#alertBox').modal('show');
                            $("#message").html(result.errmsg);
                        } else {
                            $('#alertBox').modal('show');
                            $("#message").html("未知错误");
                        }
                    }
                });
            }
        });
        $("#Namecancel").click(function () {
            $(".Name_box").show();
            $(".input_box").hide();
            $("#developName").text(developNameValue);
        });
    })
    //短信认证
    $("#information").click(function () {
        $('#informations').modal('show');
    })
    /*****修改密码*****/
    $('#inform').bootstrapValidator({
        fields: {
            infoPhone: {
                validators: {
                    notEmpty: {
                        message: '请先输入您的手机号码'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|7|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    }
                }
            }, code_text: {
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
            },
            SMS: {
                validators: {
                    notEmpty: {
                        message: '验证码不能为空'
                    }
                }
            }


        }

    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var phone = $("#inputPhones").val();
        var msmCode = $("#SMSInput").val();
        $.ajax({
            url: "/user/phoneVer",
            type: 'POST',
            data: {
                token: cookie.get("cube_develops_token"),
                id: cookie.get("cube_develops_userId"),
                phone: phone,
                msmCode: msmCode
            },
            success: function (result) {
                if (result.state == 200) {
                    $('#alertBox').modal('show');
                    $("#message").html(result.success);
                    window.location.reload()
                } else if (result.errcode == 10012) {
                    $('#alertBox').modal('show');
                    $("#message").html(result.errmsg);
                } else if (result.errcode == 10000) {
                    $('#alertBox').modal('show');
                    $("#message").html(result.errmsg);
                } else {
                    $('#alertBox').modal('show');
                    $("#message").html("未知错误");
                }
            }
        });
    });

    var timing = function () {
        var ia = 60, o = $("#msmyanzheng");
        o.html("重新发送 (60)");
        o.attr("disabled", true)
        var aa = setInterval(function () {
            o.html("重新发送 (" + ia + ")");
            if (ia == 0) {
                clearInterval(aa);
                o.html("重新发送");
                o.attr("disabled", false)
            }
            ia--;
        }, 1000);
    }

    $("#msmyanzheng").click(function () {
        var phone = $("#inputPhones").val();
        if (phone == undefined || phone == "") {
            $('#alertBox').modal('show');
            $("#message").html("请输入手机号");
            return
        }
        $.ajax({
            url: "/SMS/send",
            type: 'get',
            data: {
                token: cookie.get("cube_develops_token"),
                phone: phone
            },
            success: function (result) {
                if (result.state == 200) {
                    $('#alertBox').modal('show');
                    $("#message").html(result.success);
                    timing();
                } else if (result.errcode == 10005) {
                    $('#alertBox').modal('show');
                    $("#message").html(result.errmsg);
                } else {
                    $('#alertBox').modal('show');
                    $("#message").html("未知错误");
                }
            }
        });
    })

    $("#avatarInput").change(function () {
        $("#avatarToken").val(cookie.get("cube_develops_token"));
        $("#avatarid").val(cookie.get("cube_develops_userId"));
        $("#avatarFrm").submit();
    })
});