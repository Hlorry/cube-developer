<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2016/3/11
  Time: 9:55
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<footer>
    <div class="container footer">
        <div class="row">
            <div class=" col-md-4 footer_contact">
                <h3>联系我们</h3>
                <ul>
                    <li>
                        <div class="fl">
                            <img src="../../img/phone.png">
                        </div>
                        <div class="fl">
                            <a href="tel:010-84922996"><span>咨询电话</span>
                            <p>84922996</p></a>
                        </div>

                    </li>
                    <li>
                        <div class="fl">
                            <img src="../../img/email.png">
                        </div>
                        <div class="fl footer_email">
                            <a href="javascript:void(0)"> <span>官方邮箱</span>
                            <p>service@shixinyun.com</p></a>
                        </div>

                    </li>

                    <li>
                        <div class="fl">
                            <img src="../../img/qq.png">
                        </div>
                        <div class="fl">
                            <a href="http://wpa.qq.com/msgrd?V=1&uin=2161045552&Site=QQ">
                                <span>官方qq</span>
                                <p>2161045552</p>
                            </a>
                        </div>

                    </li>
                </ul>
            </div>
            <div class="col-md-4">
                <h3>开发者服务</h3>
                <ul>
                    <li><a href="" class="footer_disabled">知识库</a></li>
                    <li><a href="/route/download">SDK下载</a></li>
                    <li><a href="/route/file">文档下载</a></li>
             <%--       <li><a href="" class="footer_disabled">运行教程</a></li>--%>
                </ul>
            </div>
            <div class="col-md-4">
                <h3>关于我们</h3>
                <ul style="min-height:inherit">
                    <li><a href="/route/introduction">公司简介</a></li>
                    <li><a href="#" id="contact">联系方式</a></li>
                    <li><a href="#" id="about">加入我们</a></li>
                    <li><a href="#" id="service">服务条款</a></li>
                </ul>
            </div>
        </div>

    </div>
    <jsp:include page="footerl.jsp"></jsp:include>
</footer>

<jsp:include page="alert.jsp"></jsp:include>
<script type="text/javascript" src="../../../js/jquery-1.11.1.min.js"></script>
<script type="text/javascript" src="../../../js/bootstrap.min.js"></script>
<script type="text/javascript" src="../../../js/wow.min.js"></script>
<script type="text/javascript" src="../../../js/footer.js"></script>



