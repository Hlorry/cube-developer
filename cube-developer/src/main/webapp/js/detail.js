/**
 * Created by Administrator on 2016/4/11.
 */

$(function () {

    $('input').iCheck({
        checkboxClass: 'icheckbox_square-green',
        radioClass: 'iradio_square-green',
        increaseArea: '20%' // optional
    });
    /*    $('#tabs').respTabs({
     responsive : false
     });*/

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

    $('#tabBox').respTabs({
        responsive: false
    });

    function chart() {
        var myChart = echarts.init(document.getElementById('main'));
        var option = {
            tooltip: {
                trigger: 'axis'
            },
            title: {
                text: "暂无数据"
            },
            legend: {},
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
            series: []
        };
        option.series = seriesData;
        myChart.setOption(option);
        window.onresize = myChart.resize;
    }

    var data1 = [];
    var data2 = [];
    var data3 = [];
    var data4 = [];
    var data5 = [];
    data1 = {
        name: '历史使用人数统计',
        type: 'line',
        stack: '总量',
        data: [0, 0, 0, 0, 0, 0, 0]
    };
    data2 = {
        name: '活跃人数',
        type: 'line',
        stack: '总量',
        data: [0, 0, 0, 0, 0, 0, 0]
    };
    data3 = {
        name: '发消息人数',
        type: 'line',
        stack: '总量',
        data: [0, 0, 0, 0, 0, 0, 0]
    };
    data4 = {
        name: '新增人数',
        type: 'line',
        stack: '总量',
        data: [0, 0, 0, 0, 0, 0, 0]
    };
    data5 = {
        name: '新增讨论组数',
        type: 'line',
        stack: '总量',
        data: [0, 0, 0, 0, 0, 0, 0]
    };
    var seriesData = [];
    $(".tabs_next ul li:nth-child(3)").click(function () {
        // 使用刚指定的配置项和数据显示图表。
        seriesData.push(data1);
        chart();
        $(".data_chart  li").click(function () {
            $(".data_chart li ").siblings(".active").removeClass("active");
            $(this).addClass("active");
        })
    });

    $(".data_chart li").click(function () {
        var index = $(".data_chart li").index(this);
        seriesData = [];
        switch (index) {
            case 0:
                seriesData.push(data1);
                chart();
                break;
            case 1:
                seriesData.push(data2);
                chart();
                break;
            case 2:
                seriesData.push(data3);
                chart();
                break;
            case 3:
                seriesData.push(data4);
                chart();
                break;
            case 4:
                seriesData.push(data5);
                chart();
                break;
        }
    });

    $("#channel").click(function () {
        $("#channel_box").toggle(function () {
            if ($(this).is(':hidden')) {
                $("#channel").find("img").replaceWith("<img src='../../img/checked-y.png'>")
            }
            else {
                $("#channel").find("img").replaceWith("<img src='../../img/checked-h.png'>")
            }
        });
    });

    $('#modify_app_form').ajaxForm({
        url: '/app/update',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            hideAllMsg();
            var name = $("#modify_app_form").find('#appModifyName').val();
            var description = $("#modify_app_form").find('#modifyDescription').val();
            var category = $("#modify_app_form").find('#modifyCategory').val();
            var appStage = $("#modify_app_form").find("input[name='appStage']:checked").val();
            var appUserLevel = $("#modify_app_form").find('#appModifyUserLevel').val();
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
                showMsgAfter('tips-modify', result.errmsg);
            }
            $('input[type=submit]').removeAttr('disabled');
        },
        error: function () {
        }
    });

    $("#avatarForm").ajaxForm({
        url: '/app/avatar',
        type: 'post',
        dataType: 'json',
        beforeSubmit: function () {
            return true;
        },
        success: function (result) {
            if (result.code == 200) {
                $("#appAvatar").attr("src", result.uri);
            } else {
                $('#alertBox').modal('show');
                $("#message").html("上传图标失败");
            }
        },
        error: function () {
        }
    });

});

function updateAppInfo(id, appId) {
    if (!appId) {
        return false;
    }
    $('#' + id).modal('toggle');
    $.ajax({
        type: "post", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: "/app/param", //要访问的后台地址
        data: {'appId': appId}, //要发送的数据
        success: function (data) {//msg为返回的数据，在这里做数据绑定
            if (data.result.category) {
                $("select#modifyCategory").val(data.result.category);
            }
            $("#timeInfo").html(new Date(data.result.validityStart).format('')-new Date(data.result.validityEnd).format(''));
            if (data.result.appStage == 1) {
                $("#appStage1").click();
                $("#appStage1").iCheck("check");
                $("#modify_user_level_div").hide();
            }
            $("#liceStage2").attr("develop_appid",appId);
            if (data.result.appStage == 2) {
                $("#appStage2").iCheck("check");
                $("#modify_user_level_div").show();
                if (data.result.appUserLevel) {
                    $("select#appModifyUserLevel").val(data.result.appUserLevel);
                }
            }

            if (data.result.category) {
                $("select#modifyCategory").val(data.result.category);
            }

            $("#modify-app-modal").find("#appId").val(appId);
            if (data.result.appName) {
                $("#modify-app-modal").find("#appModifyName").val(data.result.appName);
            }
            if (data.result.description) {
                $("#modify-app-modal").find("#modifyDescription").val(data.result.description);
            }
        }, error: function (XMLHttpRequest, textStatus, errorThrown) {
        	
        }
    });

}

function goApp() {
    window.location.href = "/app";
}
//table加载
$(function () {
	
    var data = [{
        field: 'id',
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }, {
        "username": "时信",
        "userstate": "在线",
        "name": "时信"
    }];


    $(function () {
        $('#table').bootstrapTable({
            data: data

        });
        
    });
    var envir = 0;
    $("#licence").click(function () {
        getData()
    });
    var getData = function () {
        var appId = $("input[name=appId]").val();
        $.ajax({
            type: "post", //使用post方法访问后台
            dataType: "json", //返回json格式的数据
            url: "/app/param", //要访问的后台地址
            data: {'appId': appId}, //要发送的数据
            success: function (data) {//msg为返回的数据，在这里做数据绑定
                if (data.result != undefined && data.result != "") {
                    data = data.result;
                    $("#appTestKeyInfo").html(data.test_appkey);
                    $("#appTestIdInfo").html(data.test_appid);
                    $("#TestuseId").html(data.test_useid);
                    
                    $("#appKeyInfo").html(data.appKey);
                    $("#appIdInfo").html(data.appId);
                    $("#useId").html(data.useId);
                    
                    $("#appKeyInfo").parent().parent().hide();
                    $("#appIdInfo").parent().parent().hide();
                    $("#useId").parent().parent().hide();
                    var ex='';
                    
                    //生产
					$("#explain").html($("#explain1").html()+'license(v1):<br>http://managed.getcube.cn/auth/license?appid='+data.appId+'&appkey='+data.appKey+'<br>license(v2):<br>http://managed.getcube.cn/v2/license?appid='+data.appId+'&appkey='+data.appKey);
					//测试
					$("#explain2").html($("#explain1").html()+'license(v1):<br>http://managed.getcube.cn/auth/license?appid='+data.test_appid+'&appkey='+data.test_appkey+'<br>license(v2):<br>http://managed.getcube.cn/v2/license?appid='+data.test_appid+'&appkey='+data.test_appkey);
					
					$("#explain").hide();
                    $("#timeInfo").html(new Date(data.validityStart).format("yyyy-mm-dd") + " ～ " + new Date(data.validityEnd).format("yyyy-mm-dd"));
                    
                    $("#liceStage2").attr("develop_appid",appId);
                    $("#liceStage2").attr("checktype",data.checkType);
                    $("#liceStage2").attr("envir",data.environment);
                    if (data.useServing != undefined && data.useServing != "") {
                        var datajson = eval('(' + data.useServing + ')');
                        var html = "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.IM == true ? "IM消息" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.audio == true ? "音频呼叫" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.video == true ? "视频呼叫" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.whiteboard == true ? "白板服务" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.groups == true ? "群组服务" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.live == true ? "直播服务" : "") + "</div>"
                            + "<div class='radio' style='margin:-4px 0 0 15px;'>" + (datajson.meet == true ? "会议服务" : "") + "</div>";
                        $("#useServing").html(html);
                    }
                  //  if (data.environment == 1) {
                        $("#liceStage1").iCheck("check");
                   // }
                    if (data.environment == 2&&data.checkType==0) {
                        $("#environment").val('审核中');
                    }
                    if (data.environment == 2&&data.checkType==1) {
                        $("#environment").val('生产环境');
                    }
                    envir = data.environment;
                    //生产
                    $('#licenseUrl').attr('href','http://managed.getcube.cn/auth/license/download?appkey='+data.appKey);
                    $('#licenseUrlt').attr('href',"http://managed.getcube.cn/v2/license/download?appkey="+data.appKey);
                  
                    //测试
                    $('#licenseUrl1').attr('href','http://managed.getcube.cn/auth/license/download?appkey='+data.test_appkey);
                    $('#licenseUrl1t').attr('href',"http://managed.getcube.cn/v2/license/download?appkey="+data.test_appkey);
                    //v2 license
                   // $('#licenseUrl1t').attr('href','http://managed.getcube.cn/v2/license/download?appkey='+data.test_appkey);
                    $('#licenseUrl').parent().parent().hide();
                    
  
                }
            }
        });
    }
    $("#liceStage1").on('ifClicked',function(event){
    	  $("#appKeyInfo").parent().parent().hide();
          $("#appIdInfo").parent().parent().hide();
          $("#useId").parent().parent().hide();
          $("#explain2").show();
          $("#explain").hide();
          $("#appTestKeyInfo").parent().parent().show();
          $("#appTestIdInfo").parent().parent().show();
          $("#TestuseId").parent().parent().show();
          $('#licenseUrl1').parent().parent().show();
          $('#licenseUrl').parent().parent().hide();
    });
    $("#liceStage2").on('ifClicked',function(event){
    	var check=$("#liceStage2").attr("checktype");
		var appid=$("#liceStage2").attr("develop_appid");
		var environment=$("#liceStage2").attr("envir");
		if(environment==1&&check==0){
			$.confirm('生产环境需要审核，是否提交审核？',function(){
				$.ajax({
					url:'/app/change_env',
					type:'POST',
					data:{
						appid:appid
					},dataType:'json',
					success:function(data){
						if(data.state==200){
							$.messager.alert('提交成功');
						}else{
							$.messager.alert(data.msg);
						}
						getData();
					},error:function(){
						$.messager.alert('服务器出错了，请稍后再试');
					}
				})
			});
			getData();
		}else if(environment==2&&check==0){
			$.messager.alert('审核中。。。');
			getData();
		}else{
			$("#appKeyInfo").parent().parent().show();
            $("#appIdInfo").parent().parent().show();
            $("#useId").parent().parent().show();  

            $("#explain2").hide();
            $("#explain").show();
            
            $("#appTestKeyInfo").parent().parent().hide();
            $("#appTestIdInfo").parent().parent().hide();
            $("#TestuseId").parent().parent().hide();
            
            $('#licenseUrl').parent().parent().show();
            $('#licenseUrl1').parent().parent().hide();
		}
  });
 
});
function actionFormatter(value, row, index) {
    return [
        '<div class="table_click"><img src="../../img/checked-y.png"></div>',
        '<div class="action_box"><a class="modify" href="javascript:void(0)" title="Like">修改信息</a>',
        '<a class="delete ml10" href="javascript:void(0)" title="delete">删除</a>',
        '<a class="impasswordSure ml10" href="javascript:void(0)" title="impasswordSure">重置密码</a>' +
        '<a class="ims_send ml10" href="javascript:void(0)" title="Remove">发送消息</a>' +
        '<a class="ban ml10" href="javascript:void(0)" title="Remove">封禁</a>' +
        '<a class="offline ml10" href="javascript:void(0)" title="Remove">强制下线</a>' +
        '</div>'
    ].join('');
}
window.actionEvents = {
    'click .table_click': function (e, value, row, index) {
        $(this).next().toggle();
        if ($(this).next().is(":hidden")) {
            $(this).next().hide();
            //当前是show状态
        } else {
            $(".table_click").next().siblings(".action_box").hide();
            $(".table_click").siblings().hide();
            $(this).next().show();
        }
        e.stopPropagation();
        $(document).click(function (e) {
            if (!$(e.target).is('.action_box,.action_box *')) {
                $(".action_box").hide();
            }
        });
    },
    'click .modify': function (e, value, row, index) {
        $('#modify').modal('show');
        $("#modifySure").click(function () {
            $('#modify').modal('hide');
        })
    },
    'click .delete': function (e, value, row, index) {
        var banHtml = " 确定要删除用户<span class='text_green'>32423rfer</span>么？";
        $("#modeText").html(banHtml);
        $('#delete').modal('show');
        $("#deleteSure").click(function () {
            $('#delete').modal('hide');
        })
    },
    'click .impasswordSure': function (e, value, row, index) {
        $('#impassword').modal('show');
        $("#impasswordSure").click(function () {
            $('#impassword').modal('hide');
        })
    },
    'click .ims_send': function (e, value, row, index) {
        $('#imSend').modal('show');
        $("#imSendSure").click(function () {
            licence
            $('#imSend').modal('hide');
        })
    }, 'click .ban': function (e, value, row, index) {
        var banHtml = " 确定要封禁用户<span class='text_green'>32423rfer</span>么？";
        $("#modeText").html(banHtml);
        $('#delete').modal('show');
        $("#deleteSure").click(function () {
            $('#delete').modal('hide');
        })
    }, 'click .offline': function (e, value, row, index) {
        var banHtml = "确定要强制下线<span class='text_green'>32423rfer</span>么？";
        $("#modeText").html(banHtml);
        $('#delete').modal('show');
        $("#deleteSure").click(function () {
            $('#delete').modal('hide');
        })
    }
};

function handleFiles(files) {
    if (files.length) {
        var file = files[0];
        var imageType = /image.*/;

        if (!file.type.match(imageType)) {
            $('#alertBox').modal('show');
            $("#message").html("格式错误");
            return;
        }
        $("#avatarForm").submit();
    }
}

function managedAppUser() {
    var appId = $("input[name=appId]").val();
    $.ajax({
        type: "post", //使用post方法访问后台
        dataType: "json", //返回json格式的数据
        url: "/v1/app/userCube",
        data: {appId: appId},
        success: function (data) {//msg为返回的数据，在这里做数据绑定
            if (data.state == 200) {
                $('#app_user_table').bootstrapTable({
                    data: data.data
                });
            }

        }
    });
}

function formatDatetime(dateTime) {
    return new Date(parseInt(dateTime)).format("yyyy-mm-dd HH:MM:ss")
}
