<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户管理</title>

    <script src="${pageContext.request.contextPath}/static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript"
            rel="script"></script>
    <link href="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css"
          type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js"
            type="text/javascript" rel="script"></script>

    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet">


    <style>
        * {
            margin: 0px;
            padding: 0px;
        }
        .form {
            width: 100%;
            height: 92%;
            /*height: 65%;*/
            display: flex;
            justify-content: center;
        }
        .form .formbox {
            background: #004ea0;
            border-radius: 10px;
            padding: 50px;
            /*过渡动画*/
            transition-duration: 3s;
            transition-property: all;
            transition-timing-function: ease;
            transition-delay: 0s;
        }
        .form .formbox form {
            background: #aeb0b2;
            width: 330px;
            height: 765px;
            margin: 0 auto;
            padding: 10px;
            border-radius: 5px;
            /*过渡动画*/
            transition-duration: 2s;
            transition-property: all;
            transition-timing-function: ease;
            transition-delay: 0s;
        }

        .form .formbox form p {
            text-align: left;
            color: #2d3436;
            font-size: 14px;
            font-weight: bold;
            display: inline;
            margin:5px 5px 5px 2px;
        }
        .form .formbox form input {
            margin: 10px;
            height: 30px;
            width: 200px;
        }
        .form .formbox form select {
            display: inline-block;
            margin: 10px;
            height: 35px;
            width: 204px;
        }
        .form .formbox form button{
            width: 200px;
            height: 35px;
            margin: 20px 0px 0px 50px;
            border: 1px solid #57606f;
            background: #7bed9f;
            transition-duration: 2s;
            transition-property: all;
            transition-timing-function: ease;
            transition-delay: 0s;
        }
        .form .formbox:hover form button{
            background: #2d3436;
            color: #FFFFFF;
        }
    </style>


</head>
<body>

<div class="container" id="container" style="width: 100%">
    <div class="row clearfix" id="head">
        <div class="col-md-1 column" id="1" style="background-color: #004ea0;height: 80px">
        </div>

        <div class="col-md-12 column" style="position: fixed;width: 100%;top: 0;z-index: 99; <%--导航栏固定悬浮在最上层--%>
                background-color: #004ea0;height: 80px; align-items: center; display: flex; justify-content: center">
            <div style="display: flex; justify-content: flex-start">
                <div style="position: absolute; left: 85px">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png"
                         style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left: 130px; font-size: 30px; color: white">
                    阿仁图书馆
                </div>
            </div>
            <div style="font-size: 20px; color:white">
                修改用户
            </div>
        </div>

        <div class="col-md-1 column" id="2" style="background-color: #004ea0;height: 80px">
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex; min-height: 600px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="userManage"/>
        </jsp:include>
        <div class="col-md-9 column" id="content">
            <div style="height: 60px">
            </div>

            <div class="form">
                <div class="formbox">
                    <form role="form" action="${pageContext.request.contextPath}/doUserUpdate" method="post"
                          enctype="multipart/form-data">
                        <label for="image">照片:</label>
                        <div style="display: flex; justify-content: center; height: 20%">
                            <img class="img-circle" id="imagePreview"
                                 src="${mUser.imagePath} no-repeat center"
                                 alt="无图片"
                                 style="width: 50%; height: 100%; margin-bottom: 5px">
                        </div>
                        <input type="file" id="image" name="image">
                        <!--账号-->
                        <label for="zh" class="zhmm"><p>用户编号：</p></label>
                        <input id="zh" name="userId" value="${mUser.userId}" type="text" placeholder="账号" readonly required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--用户名-->
                        <label for="username" class="zhmm"><p>用户名：</p></label>
                        &nbsp;&nbsp;
                        <input id="username" name="username" value="${mUser.username}" type="text" placeholder="用户名" required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--密码-->
                        <label for="mm" class="zhmm"><p>密码：</p></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="mm" name="password" value="${mUser.password}" type="password" placeholder="密码" required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--手机号-->
                        <label for="sjh" class="zhmm"><p>手机号码：</p></label>
                        <input id="sjh" name="tel" value="${mUser.tel}" type="number" placeholder="手机号码" required/> <!--pattern="^1[3-9][0-9]{9}$-->
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--性别-->
                        <label for="sex" class="zhmm"><p>性别：</p></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <select id="sex" class="form-control" name="sex">
                            <option value="1">男</option>
                            <option value="0">女</option>
                        </select>

                        <br>
                        <!--账户类型-->
                        <label for="account" class="zhmm"><p>角色：</p></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <select id="account" class="form-control" name="account">
                            <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                                <option value="1">管理员</option>
                            </c:if>
                            <option value="0">普通用户</option>
                        </select>
                        <!--注册提交-->
                        <br>
                        <button type="submit" style="color: whitesmoke">确认修改</button>
                        <button>
                            <a style="border: 0; color: whitesmoke; text-decoration:none" href="${pageContext.request.contextPath}/userList" onClick="javascript:history.go(-1)">取消修改</a>
                        </button>
                    </form>
                </div>
            </div>
        </div>

        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column" style="background-color: #004ea0;
                 height: 50px; line-height: 50px; text-align: center">
            阿仁图书馆版权所有&copy;版权所有 <a href="mailto:demo@demo.com" style="color: white;">library@library.com</a>
        </div>
    </div>
</div>

<script language=javascript>
    function go()
    {
        window.history.go(-1);
    }
</script>


<script>
    $(document).ready(function () {
        document.getElementById("image").addEventListener("change", function (event) {
            event.stopPropagation()
            event.preventDefault()

            const file = this.files[0]
            const url = window.URL.createObjectURL(file)
            $("#imagePreview").attr("src", url)
        })
    })
</script>

</body>
</html>
