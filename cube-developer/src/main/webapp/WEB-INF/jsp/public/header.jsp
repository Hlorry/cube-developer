<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/15
  Time: 16:59
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="java.net.URLDecoder" %>
<%
    Cookie[] cookies = request.getCookies();
    String username = "";
    String token = "";
    if (cookies != null) {
        for (Cookie c : cookies) {
            if ("cube_develops_userName".equals(c.getName())) {
                username = URLDecoder.decode(c.getValue(), "utf-8");

            }
            if ("cube_develops_token".equals(c.getName())) {
                token = c.getValue();
            }
        }
    }
%>
<link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">
<link rel="stylesheet" type="text/css" href="//cdn.bootcss.com/iCheck/1.0.2/skins/all.css">
<link rel="stylesheet" type="text/css"
      href="//cdn.bootcss.com/bootstrap-validator/0.5.3/css/bootstrapValidator.min.css">
<link type="text/css" rel="stylesheet" href="../../../css/responsive.tabs.css">
<link type="text/css" rel="stylesheet" href="../../../css/animate.min.css">
<link rel="stylesheet" type="text/css" href="../../../css/public.css">
<link rel="stylesheet" type="text/css" href="../../../css/index.css">
<link rel="stylesheet" type="text/css" href="../../../css/media.css">
<!--[if lt IE 9]>
<link rel="stylesheet" type="text/css" href="../../css/ie.css">
<script src="//cdn.bootcss.com/html5shiv/3.7.2/html5shiv.min.js"></script>
<script src="//cdn.bootcss.com/respond.js/1.4.2/respond.min.js"></script>
<![endif]-->
<script>
    var cookie = {};
    cookie.set = function (k, h, d) {
        document.cookie = k + "=" + escape(h) + ";expires=" + d.toUTCString() + ";path=/;";
    };
    cookie.get = function (h) {
        var c, k = new RegExp("(^| )" + h + "=([^;]*)(;|$)");
        if (c = document.cookie.match(k)) {
            return unescape(c[2])
        } else {
            return null
        }
    };
    cookie.remove = function (c) {
        var k = new Date();
        k.setTime(k.getTime() - 1);
        var h = cookie.get(c);
        if (h != null) {
            document.cookie = c + "=" + h + ";expires=" + k.toGMTString() + "; path=/"
        }
    };

    function cookieRest() {
        var date = new Date();
        date.setTime(date.getTime() + 3600 * 1000);
        cookie.set("cube_develops_token", cookie.get("cube_develops_token"), date);
        cookie.set("cube_develops_activation", cookie.get("cube_develops_activation"), date);
        cookie.set("cube_develops_userId", cookie.get("cube_develops_userId"), date);
        cookie.set("cube_develops_userName", cookie.get("cube_develops_userName"), date);
    }

    function redisRest() {
        var cube_develops_token = cookie.get("cube_develops_token");
        if (cube_develops_token != undefined && cube_develops_token != "") {
            $.ajax({
                url: '/auth/token/reset',
                type: 'post',
                dataType: 'json',
                data: {token: cube_develops_token},
                success: function (result) {
                    if (result.state == 200) {
                        cookieRest();
                    }
                }
            });
        }
    }

    setInterval('redisRest()', 15 * 60 * 1000);
</script>
