<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8"/>
    <title>登录页面</title>

    <link href="${pageContext.request.contextPath}/static/css/register.css" type="text/css" rel="stylesheet">

    <style>
        .form .formbox form {
            background: #FFFFFF;
            width: 300px;
            height: 300px;
            margin: 0 auto;
            padding: 10px;
            border-radius: 5px;
            /*过渡动画*/
            transition-duration: 2s;
            transition-property: all;
            transition-timing-function: ease;
            transition-delay: 0s;
            box-shadow: 5px 7px 8px #808e9b;
        }
    </style>

</head>
<body>

<!--背景图切换-->
<div class="header">
    <nav>
        <ul>
            <li id="bjt1"><a href="#">切换背景图1</a></li>
            <li id="bjt2"><a href="#">切换背景图2</a></li>
            <li id="bjt3"><a href="#">切换背景图3</a></li>
            <li id="bjt4"><a href="#">切换背景图4</a></li>
            <li id="bjt5"><a href="#">切换背景图5</a></li>
            <li id="bjt6"><a href="#">切换背景图6</a></li>
        </ul>
    </nav>
</div>
<!--表单-->
<div class="form">
    <div class="formbox">
        <form id="login" role="form" action="${pageContext.request.contextPath}/doLogin" method="post">
            <!--账号-->
            <label for="zh" class="zhmm"><p>账号：</p></label>
            &nbsp;&nbsp;
            <input id="zh" name="username" value="" type="text" placeholder="账号" required/>
            <p style="padding-left: 20px"></p>
            <br>
            <!--密码-->
            <label for="mm" class="zhmm"><p>密码：</p></label>
            &nbsp;&nbsp;
            <input id="mm" name="password" value="" type="password" placeholder="密码" required/>
            <p style="padding-left: 20px"></p>
            <br>
            <!--注册提交-->
            <br>
            <button type="button" onclick="window.open('register.jsp')">注册</button>
            <!--注册提交-->
            <br>
            <button id="" type="submit">登录</button>
        </form>
    </div>
</div>
<!--底部版权-->
<footer>
    <h6>Copyright &copy; 2021 aliang. All rights reserved</h6>
    <h6>商务合作微信号:<a href="#">lulaotie</a></h6>
    <p>本图书馆网站将在全世界开发并收款<a
            href="http://nimg.ws.126.net/?url=http://dingyue.ws.126.net/2021/0409/f70ecd5ej00qr9nwd000mc000i200bmc.jpg&thumbnail=650x2147483647&quality=80&type=jpg">加好友</a>广告位有限，请有需要的尽快联系
    </p>
</footer>

<script>
    $(document).ready(function () {
        const loginResult = "${sessionScope.result}";
        if ("failed" === loginResult) {
            alert("用户名或密码错误，请重新登录！")
        }
    })
</script>

<script>
    //进网页随机加载图片
    if (sjs(1, 5) == 1) {
        sj1();
    } else if (sjs(1, 5) == 2) {
        sj2();
    } else if (sjs(1, 5) == 3) {
        sj3();
    } else if (sjs(1, 5) == 4) {
        sj4();
    } else if (sjs(1, 5) == 5) {
        sj5();
    } else {
        sj6();
    }
</script>

<script>
    //绑定body
    var bodyEle = document.body;
    //绑定按钮元素
    var bjt1 = document.getElementById('bjt1');
    var bjt2 = document.getElementById('bjt2');
    var bjt3 = document.getElementById('bjt3');
    var bjt4 = document.getElementById('bjt4');
    var bjt5 = document.getElementById('bjt5');
    var bjt6 = document.getElementById('bjt6');

    //定义背景图随机函数
    var sj1 = function () {
        bodyEle.style.background = 'url(../static/img/bj1.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj2 = function () {
        bodyEle.style.background = 'url(../static/img/bj2.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj3 = function () {
        bodyEle.style.background = 'url(../static/img/bj3.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj4 = function () {
        bodyEle.style.background = 'url(../static/img/bj4.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj5 = function () {
        bodyEle.style.background = 'url(../static/img/bj5.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj6 = function () {
        bodyEle.style.background = 'url(../static/img/bj6.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }

    //定义随机数
    function sjs(min, max) {
        return Math.floor(Math.random() * (max - min + 1)) + 1;
    }


    bjt1.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj1.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt2.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj2.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt3.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj3.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt4.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj4.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt5.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj5.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt6.onclick = function () {
        bodyEle.style.background = 'url(../static/img/bj6.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }

</script>
</body>
</html>
