$(function () {
    $('#delete-app-form')
        .ajaxForm({
            url: '/app/delete',
            type: 'post',
            dataType: 'json',
            beforeSubmit: function () {
                hideAllMsg();
                var name = $('#del-name').val();
                var password = $('#del-password').val();
                var checked = true;
                if (name == '') {
                    showMsgAfter('name', '请输入应用名称');
                    checked = false;
                    return false;
                }
                if (password == '') {
                    showMsgAfter('password', '请输入登陆密码');
                    checked = false;
                    return false;
                }
                if (checked) {
                    $('input[type=submit]').attr('disabled', "disabled");
                }
                return checked;
            },
            success: function (data) {
                if (data.code == '4000') {
                    window.location.href = '/app';
                } else {
                    showMsgAfter('tips-del', data.msg);
                }
                $('input[type=submit]').removeAttr('disabled');
            },
            error: function () {
            }
        });

});

function appDelete(id, appId) {
    hideAllMsg();
    //清除表单值
    $(':input', '#delete-app-form')
        .not(':button, :submit')
        .val('')
        .removeAttr('checked')
        .removeAttr('selected');

    $('#delAppId').val(appId);
    $('#' + id).modal('toggle');
}