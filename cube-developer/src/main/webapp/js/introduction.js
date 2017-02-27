/**
 * Created by Administrator on 2016/3/14.
 */
$(function () {
    $(".tabs-list li:nth-child(4)").click(function(){
        scrollBox();
    });
    var htmlUrl = getUrlParam("contact");
    if (htmlUrl == 1) {
        $('#tabs').respTabs({
            startIndex: 1
        });
    }
    else if (htmlUrl == 2) {
        $('#tabs').respTabs({
            startIndex: 2
        });
    } else if (htmlUrl == 3) {
        $('#tabs').respTabs({
            startIndex: 3
        });
        $(window).resize(function () {
            scrollBox();
        });
        scrollBox();

    }else{
        $('#tabs').respTabs();
    }
    function scrollBox(){
        if ($(window).width() > 767) {
            $('.scroll_item').perfectScrollbar();
        }
    }
    //获取url中的参数
    function getUrlParam(name) {
        var reg = new RegExp("(^|&)" + name + "=([^&]*)(&|$)"); //构造一个含有目标参数的正则表达式对象
        var r = window.location.search.substr(1).match(reg);//匹配目标参数
        if (r != null) return unescape(r[2]);
        return null; //返回参数值
    }
})
