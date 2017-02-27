// 提示消息
function showMsgAfter(inputName, msg) {
    $(".help-block[target=" + inputName + "]").html(msg);
    $(".help-block[target=" + inputName + "]").show();
    $("div[target=" + inputName + "]").addClass('has-error');
}

// 隐藏提示信息 移除高亮错误类
function hideAllMsg() {
    $(".help-block").hide();
    $("div").removeClass('has-error');
}

function getCookie(name) {
    var arr, reg = new RegExp("(^| )" + name + "=([^;]*)(;|$)");
    if (arr = document.cookie.match(reg))
        return unescape(arr[2]);
    else
        return null;
}