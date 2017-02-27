/**
 * Created by Administrator on 2016/3/14.
 */
$(function () {

    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });

    $('#tabBox').respTabs({
        responsive: false
    });

    $("#token").val(getCookie("cube_develops_token"));
    $("#userId").val(getCookie("cube_develops_userId"));
    $("#token_modify").val(getCookie("cube_develops_token"));
    $("#userId_modify").val(getCookie("cube_develops_userId"));

    // 运营阶段判断
    $('input[name="appStage"]').on('ifClicked', function (event) {
        event.preventDefault();
        var v = event.target.defaultValue;
        if (v == 1) {
            var obj = {
                fields: {
                    appName: {
                        validators: {
                            notEmpty: {
                                message: '请输入应用名称'
                            }
                        }
                    },
                    description: {
                        validators: {
                            notEmpty: {
                                message: '请输入应用简介'
                            }
                        }
                    },
                    category: {
                        validators: {
                            notEmpty: {
                                message: '请选择应用分类'
                            }
                        }
                    },
                    appStage: {
                        validators: {
                            notEmpty: {
                                message: '请选择运营阶段'
                            }
                        }
                    }
                }
            };
            $("#user_level_div").hide();
            $("#modify_user_level_div").hide();
            //$('#create_app_form').bootstrapValidator(obj);
            //$('#modify_app_form').bootstrapValidator(obj);
        } else {
            var obj = {
                fields: {
                    appName: {
                        validators: {
                            notEmpty: {
                                message: '请输入应用名称'
                            }
                        }
                    },
                    description: {
                        validators: {
                            notEmpty: {
                                message: '请输入应用简介'
                            }
                        }
                    },
                    category: {
                        validators: {
                            notEmpty: {
                                message: '请选择应用分类'
                            }
                        }
                    },
                    appStage: {
                        validators: {
                            notEmpty: {
                                message: '请选择运营阶段'
                            }
                        }
                    },
                    appUserLevel: {
                        validators: {
                            notEmpty: {
                                message: '请选择用户量级'
                            }
                        }
                    }
                }
            };
            $("#user_level_div").show();
            $("#modify_user_level_div").show();
            //$('#create_app_form').bootstrapValidator(obj);
            //$('#modify_app_form').bootstrapValidator(obj);
        }
    });

    $('#create_app_form')
        .ajaxForm({
            url: '/app/create',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
                hideAllMsg();
                var name = $("#create_app_form").find('#appCreateName').val();
                var description = $("#create_app_form").find('#createDescription').val();
                var category = $("#create_app_form").find('#createCategory').val();
                var appStage = $("#create_app_form").find("input[name='appStage']:checked").val();
                var appUserLevel = $("#create_app_form").find('#appCreateUserLevel').val();
                var checked = true;
                if (name.replace(/\s+/g, "") == '') {
                    showMsgAfter('appName', '请输入应用名称');
                    checked = false;
                }
                if (description.replace(/\s+/g, "") == '') {
                    showMsgAfter('description', '请输入应用简介');
                    checked = false;
                }
                if (category == '') {
                    showMsgAfter('category', '请选择应用分类');
                    checked = false;
                }
                if (appStage == '' || appStage == undefined) {
                    showMsgAfter('appStage', '请选择运营阶段');
                    checked = false;
                } else if (appStage == 2) {
                    if (appUserLevel == '') {
                        showMsgAfter('appUserLevel', '请选择用户量级');
                        checked = false;
                    }
                }
                if (checked) {
                    $('input[type=submit]').attr('disabled', "disabled");
                }
                return checked;
            },
            success: function (result) {
                if (result.state && result.state == 200) {
                    window.location.href = "/app/detail/" + result.appId;
                } else if (result.errcode && result.errcode == 10088) {
                    showMsgAfter('tips-create', result.errmsg);
                }
                $('input[type=submit]').removeAttr('disabled');
            },
            error: function () {
            }
        });

});

function createAppInfo(id) {
    hideAllMsg();
    $('#appCreateName').val('');
    $('#createDescription').val('');
    $('#createCategory').val('');
    $("#appCreateStage1").click();
    $("#appCreateStage1").iCheck("check");
    $("#user_level_div").hide();
    $('#' + id).modal('toggle');
}
