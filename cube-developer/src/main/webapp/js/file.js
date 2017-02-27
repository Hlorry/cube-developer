/**
 * Created by Administrator on 2016/3/15.
 */
$(function () {
    var $div_li = $(".file_list ul li ");
    $div_li.click(function () {
        $(".file_list ul li ").siblings(".selected").removeClass("selected");
        $(this).addClass("selected");
        var div_index = $div_li.index(this);
        $("#tab_box>div").eq(div_index).show().siblings().hide();
    }).hover(function () {
        $(this).addClass("hover");
    }, function () {
        $(this).removeClass("hover");
    });
    $(".list_title").click(function () {
        $(this).next("ul").toggle();
        if ($(this).next("ul").is(":hidden")) {
            //当前是hide状态
            $(this).removeClass("list_icon");
        } else {
            $(this).addClass("list_icon");
            //当前是show状态
        }

    });
    /**文档标题点击***/
    $("#fileBlock").click(function (e) {
        $("#file_box").toggle();
        e.stopPropagation();
    });
    fileHide();
    $(window).resize(function () {
        fileHide();
    });
    /**文档标题响应式方法**/
    function fileHide() {
        if ($(window).width() < 767) {
            $(document).click(function (e) {
                if (!$(e.target).is('#file_box, #file_box *')) {
                    $("#file_box").hide();
                }
            });
            $div_li.click(function () {
                $("#file_box").hide();
            })
        }
        else if ($(window).width() > 768) {
            $("#file_box").show();
            $(document).click(function (e) {
                $("#file_box").show();
                e.stopPropagation();
            });
        }

    }

})