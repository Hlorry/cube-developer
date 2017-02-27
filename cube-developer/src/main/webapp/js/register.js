/**
 * Created by Administrator on 2016/3/11.
 */
$(function () {
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });
    /**注册信息切换***/
    var userType = $("input[name=userType]:checked").val();
    inputShow(userType);

    $('input[name="userType"]').on('ifClicked', function (data) {
        inputShow(data.currentTarget.defaultValue);
    });
    function inputShow(userType) {
        if (userType == 1) {
            $("#enterprise_progress").hide();
            $("#register_progress").show();

        } else {
            $("#register_progress").hide();
            $("#enterprise_progress").show();
        }
    }

    /**注册验证**/
    $('#register_form').bootstrapValidator({
        fields: {
            name: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    },
                    stringLength: {
                        min: 3,
                        max: 18,
                        message: '用户名长度必须在3到18位之间'
                    }
                }
            },
            password: {
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
            passwordrepeat: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }, identical: {//相同
                        field: 'password',
                        message: '两次密码不一致'
                    },
                    different: {//不能和用户名相同
                        field: 'username',
                        message: '不能和用户名相同'
                    }

                }

            },
            /*phone: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: '手机号码不能为空'
                    },
                    stringLength: {
                        min: 11,
                        max: 11,
                        message: '请输入11位手机号码'
                    },
                    regexp: {
                        regexp: /^1[3|5|7|8]{1}[0-9]{9}$/,
                        message: '请输入正确的手机号码'
                    },
                    callback: {
                        message: "手机号重复",
                        callback: function (value, validator, t) {
                            var res = true;
                            if (value != "") {
                                $.ajax({
                                    url: '/user/query',
                                    type: 'post',
                                    dataType: 'json',
                                    async: false,
                                    data: {phone: value},
                                    success: function (data) {
                                        if (data.cube != undefined) {
                                            res = false;
                                        }
                                    }
                                });
                            }
                            return res;
                        }
                    }
                }

            },*/
            email: {
                trigger: 'blur',
                validators: {
                    notEmpty: {
                        message: '邮箱不能为空'
                    },
                    emailAddress: {
                        message: '请输入正确的邮件地址如：123@qq.com'
                    },
                    callback: {
                        message: "邮箱已被注册",
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
                                        if (data.cube != undefined) {
                                            res = false;
                                        }
                                    }
                                });
                            }
                            return res;
                        }
                    }
                }
            }
        }

    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target)
        var userType = $("input[name=userType]:checked").val();
        if (userType == 1) {
            $.ajax({
                url: $form.attr('action'),
                type: 'POST',
                data: $form.serialize(),
                success: function (result) {
                    $("#emailSure").html($form.serialize().substring($form.serialize().indexOf("email=") + 6, $form.serialize().indexOf("&password=")).replace("%40", "@"))
                    $("#register_progress").find(".step2").addClass("active");
                    $("#register_progress").find(".step_progress").addClass("step2").removeClass("step1");
                    $("#register_form").css("display", "none");
                    $("#register_email").css("display", "block");
                    var emailVaue = $("input[name=user]").html();
                    $("#emailSure").html(emailVaue);
                }
            });
            //个人跳过验证
            $("#completeRegister").click(function () {
                $("#register_form").hide();
                $("#register_email").hide();
                $("#register_sucess").show();
                $("#register_progress").find(".step3").addClass("active");
                $("#register_progress").find(".step_progress").addClass("step3").removeClass("step2");
            });
        } else if (userType == 2) {
            $.ajax({
                url: $form.attr('action'),
                type: 'POST',
                data: $form.serialize(),
                success: function (result) {
                    var email_app = $form.serialize().substring($form.serialize().indexOf("email=") + 6, $form.serialize().indexOf("&password=")).replace("%40", "@")
                    $("#certifiedEmail").val(email_app);
                    $("#emailSure").html(email_app);
                    $("#enterprise_progress").find(".step2").addClass("active");
                    $("#enterprise_progress").find(".step_progress").addClass("step2").removeClass("step1");
                    $("#register_form").css("display", "none");
                    $("#register_email").css("display", "none");
                    $("#enterprise_form").css("display", "block");
                    //var emailVaue = $("input[name=user]").html();
                    //$("#emailSure").html(emailVaue);
                    $("#certifiedUserId").val(result.id);
                }
            });
            //企业完成注册
            $("#completeRegister").click(function () {
                $("#register_form").hide();
                $("#register_email").hide();
                $("#register_sucess").show();
                $("#register_progress").find(".step_last").addClass("active");
                $("#register_progress").find(".step_progress").addClass("step_last").removeClass("step2");
            });
        }

    });

    $("#completeRegister").click(function () {
        $("#register_form").hide();
        $("#register_email").hide();
        $("#register_sucess").show();
        $("#register_progress").find(".step_last").addClass("active");
        $("#register_progress").find(".step_progress").addClass("step_last").removeClass("step2");
        $("#enterprise_progress").find(".step_last").addClass("active");
        $("#enterprise_progress").find(".step_progress").addClass("step_last").removeClass("step3");
    });

    $('#enterprise_form').bootstrapValidator({
        message: 'This value is not valid',
        fields: {
            cpName: {
                validators: {
                    notEmpty: {
                        message: '公司名称不能为空'
                    }
                }
            },
            cpAddress: {
                validators: {
                    notEmpty: {
                        message: '公司地址不能为空'
                    }
                }
            },
            licenseNum: {
                validators: {
                    notEmpty: {
                        message: '营业执照号不能为空'
                    }
                }
            },
            agency: {
                validators: {
                    notEmpty: {
                        message: '组织机构号不能为空'
                    }
                }

            },
            taxNum: {
                validators: {
                    notEmpty: {
                        message: '税务登记号不能为空'
                    }
                }

            },
            corporate: {
                validators: {
                    notEmpty: {
                        message: '法定代表不能为空'
                    }
                }
            },
            cpPhone: {
                validators: {
                    notEmpty: {
                        message: '公司电话不能为空'
                    }
                }
            },
            cpWebsite: {
                validators: {
                    notEmpty: {
                        message: '公司网站不能为空'
                    }
                }
            },
            license: {
                validators: {
                    notEmpty: {
                        message: '请上传营业执照'
                    }
                }
            },
            agencyImg: {
                validators: {
                    notEmpty: {
                        message: '请上传组织机构证'
                    }
                }
            },
            taxImg: {
                validators: {
                    notEmpty: {
                        message: '请上传税务登记证'
                    }
                }
            }
        }

    }).on('success.form.bv', function (e) {
    });

    $("#channel").click(function () {
        $("#channel_box").toggle(function(){
            if ($(this).is(':hidden')) {
               $("#channel").find("img").replaceWith("<img src='../../img/checked-y.png'>")
            }
            else{
                $("#channel").find("img").replaceWith("<img src='../../img/checked-h.png'>")
            }

        });


    })
    $("#endReg").click(function () {
        $("#enterprise_progress").find(".step3").addClass("active");
        $("#enterprise_progress").find(".step_progress").addClass("step3").removeClass("step2");
        $("#register_form").css("display", "none");
        $("#enterprise_form").css("display", "none");
        $("#register_email").css("display", "block");

    })

    $("#emailActivation").click(function () {
        var email = $("#emailSure").html()
        var emailUrl = "http://mail." + email.substring(email.indexOf("@") + 1);
        window.open(emailUrl);
    });

    var state = getUrlParam("state");
    var errcode = getUrlParam("errcode");
    if (state != undefined && state == 200) {
        var success = getUrlParam("success");
        /**企业验证**/
        if (success == "certified+save+ok") {
            /*$("#enterprise_progress").find(".step3").addClass("active");
             $("#enterprise_progress").find(".step_progress").addClass("step3").removeClass("step2");
             $("#register_form").css("display", "none");
             $("#enterprise_form").css("display", "none");
             $("#register_email").css("display", "block");
             var emailVaue = $("input[name=user]").html();
             $("#emailSure").html(emailVaue);*/

            var email = getUrlParam("email");
            $("#emailSure").html(email);
            $("#register_progress").hide();
            $("#enterprise_progress").show();
            $("#enterprise_progress").find(".step2").addClass("active");
            $("#enterprise_progress").find(".step3").addClass("active");
            $("#enterprise_progress").find(".step_progress").addClass("step3").removeClass("step2");
            $("#register_form").css("display", "none");
            $("#enterprise_form").css("display", "none");
            $("#register_email").css("display", "block");
            var emailVaue = $("input[name=user]").html();
            $("#emailSure").html(emailVaue);
        } else {
            /**个人验证**/
            $("#register_form").css("display", "none");
            $("#register_email").css("display", "none");
            $("#register_sucess").css("display", "block");
            $("#register_progress").find(".step_last").addClass("active");
            $("#register_progress").find(".step2").addClass("active");
            $("#register_progress").find(".step_progress").addClass("step_last").removeClass("step2");
            setTimeout("location.href = '/route/login'", 5000);
        }
    } else if (errcode != null && errcode == 10012) {
        $('#alertBox').modal('show');
        $("#message").html("验证链接已失效，请登陆你的账号重新验证邮箱！");
    }

    var level = getUrlParam("level");
    if(level == 1){
        $("#certifiedUserId").val(cookie.get("cube_develops_userId"));
        $("#level").val(1);
        $("#enterprise_progress").find(".step2").addClass("active");
        $("#enterprise_progress").find(".step_progress").addClass("step2").removeClass("step1");
        $("#register_form").css("display", "none");
        $("#register_email").css("display", "none");
        $("#enterprise_form").css("display", "block");
        $("#endReg").html("")
    }
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);//匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }

    $("#suiwu").uploadPreview({
        Img: "suiwuImg",
        Width: 244,
        Height: 233,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    $("#yingye").uploadPreview({
        Img: "yingyeImg",
        Width: 244,
        Height: 233,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });
    $("#jigou").uploadPreview({
        Img: "jigouImg",
        Width: 244,
        Height: 233,
        ImgType: ["gif", "jpeg", "jpg", "bmp", "png"],
        Callback: function () {
        }
    });

    $("#restSend").click(function () {
        var email = $("#emailSure").html();
        $.ajax({
            url: "/auth/verify",
            type: 'POST',
            data: {
                "email": email
            },
            success: function (result) {
                if (result.state == 200) {
                    /*    alert("验证信息已发送！请尽快验证你的邮箱。");*/
                    $('#alertBox').modal('show');
                    $("#message").html("验证信息已发送！请尽快验证你的邮箱。");
                    $("#testHtml").html("验证信息已发送")

                }
            }
        });
    })
})
