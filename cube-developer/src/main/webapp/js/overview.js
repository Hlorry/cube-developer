/**
 * Created by Administrator on 2016/3/14.
 */
$(function () {
    $('#tabs').respTabs({
        /* responsive : false*/
    });
    $('#tabBox').respTabs({
        /* responsive : false*/
    });
    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });

    // 创建应用表单
    $('#modify_app_form').bootstrapValidator({
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
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        var $form = $(e.target);
        $.ajax({
            url: $form.attr('action'),
            type: 'POST',
            data: $form.serialize(),
            success: function (result) {
                var obj = result;
                if (result.state && result.state == 200) {
                    window.location.href = "/app/detail/" + obj.appId;
                } else if (result.errcode && result.errcode == 10088) {
                    $("#modify-error").css("display", "block");
                }
            }
        });
    });

    $('input[name="appStage"]').on('ifClicked', function (event) {
        event.preventDefault();
        var v = event.target.defaultValue;
        if (v == 1) {
            $("#user_level_div").hide();
            $('#modify_app_form').bootstrapValidator({
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
            });
        } else {
            $("#user_level_div").show();
            $('#modify_app_form').bootstrapValidator({
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
            });
        }
    });
});

function updateAppInfo() {
    var appId = $("#appId").val();
    $.ajax({
        type: "post", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: "/app/param", //要访问的后台地址
        data: {'appId': appId}, //要发送的数据
        success: function (data) {//msg为返回的数据，在这里做数据绑定
            if (data.result.category) {
                $("select#category").val(data.result.category);
            }

            if (data.result.appStage == 1) {
                $("#appStage1").click();
                $("#appStage1").iCheck("check");
                $("#user_level_div").hide();
            }

            if (data.result.appStage == 2) {
                $("#appStage2").iCheck("check");
                $("#user_level_div").show();
                if (data.result.appUserLevel) {
                    $("select#appUserLevel").val(data.result.appUserLevel);
                }
            }

            if (data.result.category) {
                $("select#category").val(data.result.category);
            }

            $("#modify-apps").find("#appId").val(appId);
            if (data.result.appName) {
                $("#modify-apps").find("#appName").val(data.result.appName);
            }
            if (data.result.description) {
                $("#modify-apps").find("#content_text").val(data.result.description);
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {

        }
    });
}

var myChart = echarts.init(document.getElementById('main'));

var option = {
    tooltip: {
        trigger: 'axis'
    },
    legend: {
        data: ['历史使用人数统计', '活跃人数', '发消息人数', '新增人数', '新增讨论组数']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    toolbox: {
        feature: {
            saveAsImage: {}
        }
    },
    xAxis: {
        type: 'category',
        boundaryGap: false,
        data: ['周一', '周二', '周三', '周四', '周五', '周六', '周日']
    },
    yAxis: {
        type: 'value'
    },
    series: [
        {
            name: '历史使用人数统计',
            type: 'line',
            stack: '总量',
            data: [120, 132, 101, 134, 90, 230, 210]
        },
        {
            name: '活跃人数',
            type: 'line',
            stack: '总量',
            data: [220, 182, 191, 234, 290, 330, 310]
        },
        {
            name: '发消息人数',
            type: 'line',
            stack: '总量',
            data: [150, 232, 201, 154, 190, 330, 410]
        },
        {
            name: '新增人数',
            type: 'line',
            stack: '总量',
            data: [320, 332, 301, 334, 390, 330, 320]
        },
        {
            name: '新增讨论组数',
            type: 'line',
            stack: '总量',
            data: [820, 932, 901, 934, 1290, 1330, 1320]
        }
    ]
};

// 使用刚指定的配置项和数据显示图表。
myChart.setOption(option);
window.onresize = myChart.resize;