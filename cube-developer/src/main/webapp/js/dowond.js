/**
 * Created by Administrator on 2016/3/14.
 */
var code; //在全局 定义验证码
function createCode() {
    code = "";
    var codeLength = 4;//验证码的长度
    var checkCode = document.getElementById("checkCode");
    var selectChar = new Array(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 'A', 'B', 'C', 'D', 'E', 'F', 'G', 'H', 'I', 'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V', 'W', 'X', 'Y', 'Z');//所有候选组成验证码的字符，当然也可以用中文的
    for (var i = 0; i < codeLength; i++) {
        var charIndex = Math.floor(Math.random() * 36);
        code += selectChar[charIndex];
    }
    if (checkCode) {
        checkCode.className = "code";
        checkCode.value = code;
    }
}

createCode();
$(function () {

    /**忘记密码验证**/
    $('#download').bootstrapValidator({
        fields: {
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
        $.ajax({
            url: "https://license.shixinyun.com/auth/license/free",
            type: 'GET',
            success: function (result) {
                if (result.state == 200) {
                    var blob = new Blob([result.license], {type: "text/plain;charset=utf-8"});
                    saveAs(blob, "cube.license");
                } else if (result.state == 450) {
                    $('#alertBox').modal('show');
                    $("#message").html("请求过于频繁，喝杯咖啡休息下！");
                } else {
                    $('#alertBox').modal('show');
                    $("#message").html("未知错误");
                }
            }
        });
    });

    /**
     * 页面动态化
     */
   /* $.ajax({
        url: "http://localhost:8080/v1/sdk/querySDK",
    	url:"/sdk/newestSdk",
        type: 'POST',
        success: function (result) {
                var data = result.sdks;
                data.forEach(function (item, i) {
                    if (item.type == 0) {
                        $("#andVersion").html(item.version);
                        $("#andUpdateTime").html(item.sdktime);
                        $("#andSDKDown").attr("href", item.sdk);
                        $("#andShowApp").attr("href", item.showapp);
                        $("#andDoc").attr("href", item.doc);
                        $("#andCode").attr("href", item.showcode);
                    }
                    if(item.type == 1){
                        $("#iosVersion").html(item.version);
                        $("#iosUpdateTime").html(item.sdktime);
                        $("#iosSDKDown").attr("href", item.sdk);
                        $("#iosDoc").attr("href", item.doc);
                        $("#iosCode").attr("href", item.showcode);
                    }
                    if(item.type == 2){
                        $("#webVersion").html(item.version);
                        $("#webUpdateTime").html(item.sdktime);
                        $("#webSDKDown").attr("href", item.sdk);
                    }
                });
         
        }
    });*/

})
