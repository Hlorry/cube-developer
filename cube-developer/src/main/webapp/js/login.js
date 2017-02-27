/**
 * Created by Administrator on 2016/3/14.
 */
$(function () {
    $('#login').bootstrapValidator({
        fields: {
            username: {
                validators: {
                    notEmpty: {
                        message: '用户名不能为空'
                    }
                }
            },
            password: {
                validators: {
                    notEmpty: {
                        message: '密码不能为空'
                    }
                }
            }
        }
    }).on('success.form.bv', function (e) {
        e.preventDefault();
        $.ajax({
            url: $(this).attr('action'),
            type: 'POST',
            dataType: 'json',
            data: $(this).serialize(),
            success: function (result) {
                if (result.code == '0000'||result.code == '301') {
                    window.location.href = result.data;
                } /*else if (result.code == '301') {
                    document.location.href = "http://managed.getcube.cn/auth_login.jsp" + "?token=" + result.token;
                }*/ else {
                    showMsgAfterLogin(result.target, result.err);
                }
                $('input[type=submit]').removeAttr('disabled');
            }
        });
    });
})

//弹消息
function showMsgAfterLogin(inputName, msg) {
    $(".errorinfo[target=" + inputName + "]").html(msg);
    $(".errorinfo[target=" + inputName + "]").fadeIn('fast');
    $(".input[target=" + inputName + "]").addClass('error');
}
