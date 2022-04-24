<%--<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>--%>
<%--<jsp:forward page="login.jsp"/>--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="utf-8" />
    <title>注册页面</title>
    <link href="${pageContext.request.contextPath}/static/css/register.css" rel="stylesheet" type="text/css"/>

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
        <form role="form" action="${pageContext.request.contextPath}/doRegister" method="post" enctype="multipart/form-data">
            <!--账号-->
            <label for="zh" class="zhmm"><p>账号：</p></label>
            &nbsp;&nbsp;
            <input id="zh" name="userName" value="" type="text" placeholder="账号" required/>
            <p class="message"></p>
            <br>
            <!--密码-->
            <label for="mm" class="zhmm"><p>密码：</p></label>
            &nbsp;&nbsp;
            <input id="mm" name="password" value="" type="password" placeholder="密码" required/>
            <p class="message1"></p>
            <br>
            <!--手机号-->
            <label for="sjh" class="zhmm"><p>手机号：</p></label>
            <input id="sjh" name="tel" value="" type="number" placeholder="手机号" required/> <!--pattern="^1[3-9][0-9]{9}$-->
            <p class="message3"></p>
            <br>
            <!--性别-->
            <label for="sex" class="zhmm"><p>性别：</p></label>
            &nbsp;&nbsp;
            <select id="sex" class="form-control" name="sex">
                <option value="1">男</option>
                <option value="0">女</option>
            </select>
            <p style="padding-left: 20px"></p>
            <br>
            <!--账户类型-->
            <label for="account" class="zhmm"><p>角色：</p></label>
            &nbsp;&nbsp;
            <select id="account" class="form-control" name="account">
<%--                <option value="1">管理员</option>--%>
                <option value="0" readonly="readonly">普通用户</option>
            </select>
            <!--注册提交-->
            <br>
            <button id="zctj" type="submit">注册</button>
        </form>
    </div>
</div>
<!--底部版权-->
<footer>
    <h6>Copyright &copy; 2021 aliang. All rights reserved</h6>
    <h6>商务合作微信号:<a href="#">lulaotie</a></h6>
    <p>本图书馆网站将在全世界开发并收款<a href="http://nimg.ws.126.net/?url=http://dingyue.ws.126.net/2021/0409/f70ecd5ej00qr9
    nwd000mc000i200bmc.jpg&thumbnail=650x2147483647&quality=80&type=jpg">加好友</a>广告位有限，请有需要的尽快联系</p>
</footer>

<script>
    //进网页随机加载图片
    if (sjs(1,5) == 1) {
        sj1();
    } else if (sjs(1,5) == 2) {
        sj2();
    } else if (sjs(1,5) == 3) {
        sj3();
    } else if (sjs(1,5) == 4) {
        sj4();
    } else if (sjs(1,5) == 5) {
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
    var zh = document.getElementById('zh');
    var mm = document.getElementById('mm');
    var sjh = document.getElementById('sjh');
    var message = document.querySelector('.message');
    var message1 = document.querySelector('.message1');
    var message3 = document.querySelector('.message3');
    var zctj = document.getElementById('zctj');

    //定义背景图随机函数
    var sj1 = function(){
        bodyEle.style.background = 'url(../static/img/bj1.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj2 = function(){
        bodyEle.style.background = 'url(../static/img/bj2.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj3 = function(){
        bodyEle.style.background = 'url(../static/img/bj3.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj4 = function(){
        bodyEle.style.background = 'url(../static/img/bj4.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj5 = function(){
        bodyEle.style.background = 'url(../static/img/bj5.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    var sj6 = function(){
        bodyEle.style.background = 'url(../static/img/bj6.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }

    //定义随机数
    function sjs(min,max){
        return Math.floor(Math.random()*(max-min+1))+1;
    }


    bjt1.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj1.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt2.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj2.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt3.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj3.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt4.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj4.jpg)';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt5.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj5.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }
    bjt6.onclick = function(){
        bodyEle.style.background = 'url(../static/img/bj6.jpg) no-repeat center';
        bodyEle.style.backgroundSize = '100% 100%';
    }

    zh.onblur = function(){
        if(zh.value.length<3 || zh.value.length >12)
        {
            message.className = 'message yanzheng';
            message.innerHTML = '您输入的账号需要满足3-16';
        }
        else if(zh.value.length>3 || zh.value.length <12)
        {
            message.className = 'message right';
            message.innerHTML = '满足条件';
            var tj1 = true;
        }
    }
    //定义正则表达式  起始位置是1，第二位是3-9，结尾以9个0到9的数字。
    var phoneReg = /^1[3-9][0-9]{9}$/;

    sjh.onblur = function(){
        if(phoneReg.test(sjh.value) == false){
            message3.className = 'message wrong';
            message3.innerHTML = '请输入正确的手机号';
        }
        else if(phoneReg.test(sjh.value) == true){
            message3.className = 'message right';
            message3.innerHTML = '您的手机号输入成功！';
        }
    }

    mm.onblur = function(){
        if(mm.value.length<3 || mm.value.length >12)
        {
            message1.className = 'message1 yanzheng';
            message1.innerHTML = '您输入的密码需要满足3-16';
        }
        else if(mm.value.length>3 || mm.value.length <12)
        {
            message1.className = 'message1 right';
            message1.innerHTML = '满足条件';
//	    console.log(mm.value);
        }
    }

    zctj.onclick = function(){
        if(mm.value.length<3 || mm.value.length >12 || zh.value.length<3 || zh.value.length >12 ||phoneReg.test(sjh.value) == false)
        {
            alert('没满足条件无法注册');
            return false;
        }
        else
        {
            alert("注册成功");
        }
    }
</script>
</body>
</html>