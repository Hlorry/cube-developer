/**
 * Created by Administrator on 2016/3/17.
 */
var contact = 1;
var about = 2;
var service = 3;
$("#contact").click(function () {
    window.location.href = "/route/introduction?contact=" + contact;
});
$("#about").click(function () {
    window.location.href = "/route/introduction?contact=" + about;
})
$("#abouts").click(function () {
    window.location.href = "/route/introduction?contact=" + about;
})
$("#service").click(function () {
    window.location.href = "/route/introduction?contact=" + service;
})

var Html = "<div class='ie_bg'><div class='ie_box'>" +
    " <h3>尊敬的用户为了能有更好的浏览体验，</h3>" +
    " <h3>请升级后继续使用时信的服务</h3>"
    + " <div align=center class=menu-list>"
    + "<a href='http://rj.baidu.com/soft/detail/14744.html' id='google'><img src='../img/google.png'> <span class=zh>谷歌浏览器</span><br><span class=en>Google Chrome</span></a>" +
    " <a  href='http://www.mozilla.org/zh-CN/firefox/new/'  type=submit><img src='../img/firefox.png'><span class=zh>火狐浏览器</span><BR><span class=en>Mozilla Firefox</span></a>" +
    "<a  href='http://ie.sogou.com/' type=submit><img src='../img/sougou.png'><span class=zh>搜狗浏览器</span><br><span class=en>Sogou Explorer</span></a>" +
    "<a  href='http://chrome.360.cn/' type=submit><img src='../img/360.png'><span class=zh>360极速浏览器</span><br><span class=en>360 Chrome</span></a>" +
    "  <a  href='http://www.microsoft.com/china/windows/IE/upgrade/index.aspx' type=submit><img src='../img/ie.png'><span class=zh>IE浏览器 9+</span><br><span class=en>Internet Explorer</span></a>"
    + "</div>"
    + "</div></div>";
/******IE低版本浏览器提示*****/
var once_per_session = 1;
function get_cookie(Name) {
    var search = Name + "=";
    var returnvalue = "";
    if (document.cookie.length > 0) {
        offset = document.cookie.indexOf(search);
        if (offset != -1) {
            offset += search.length;
            end = document.cookie.indexOf(";", offset);
            if (end == -1)
                end = document.cookie.length;
            returnvalue = unescape(document.cookie.substring(offset, end))
        }
    }
    return returnvalue;
}
function alertornot() {
    if (get_cookie('alerted') == '') {
        loadalert();
        document.cookie = "alerted=yes"
    }
}
function loadalert() {
    $('#iealertBox').modal('show');
    $("#ieMessage").append(Html);
}
var ie6 = navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE6.0";
var ie7 = navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE7.0";
var ie8 = navigator.appName == "Microsoft Internet Explorer" && navigator.appVersion.split(";")[1].replace(/[ ]/g, "") == "MSIE8.0";
if (once_per_session == 0) {
    if (ie6) {
        loadalert()
    }
    else if (ie7) {
        loadalert()
    }
    else if (ie8) {
        loadalert()
    }
}
else {
    if (ie6) {
        alertornot()
    }
    else if (ie7) {
        alertornot()
    }
    else if (ie8) {
        alertornot()
    }
}
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
//导航点击事件
$(".navbar-toggle").click(function (e) {
    $('#navbar').collapse('toggle');
    e.stopPropagation();
});
$(document).click(function (e) {
    if (!$(e.target).is('#navbar, #navbar *')) {
        $('#navbar').collapse('hide');
    }
});