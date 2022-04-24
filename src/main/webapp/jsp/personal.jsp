<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/11
  Time: 11:07
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>用户列表</title>
    <link href="static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css" type="text/css" rel="stylesheet">
    <script src="static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript" rel="script"></script>
    <script src="static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js" type="text/javascript" rel="script"></script>

    <link href="${pageContext.request.contextPath}/static/css/personal.css" rel="stylesheet" type="text/css"/>

    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet">

</head>
<body>
<div class="container" id="container" style="width: 100%">
    <div class="row clearfix" id="head">
        <div class="col-md-1 column" id="1" style="background-color: #004ea0;height: 80px">
        </div>

        <div class="col-md-12 column" style="position: fixed;width: 100%;top: 0;z-index: 99; <%--导航栏固定悬浮在最上层--%>
                background-color: #004ea0;height: 80px; align-items: center; display: flex; justify-content: center">
            <div style="display: flex; justify-content: flex-start">
                <div style="position: absolute; left: 85px; top: 23px;">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png"
                         style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left: 130px;top: 23px; font-size: 30px; color: white">
                    阿仁图书馆
                </div>
            </div>
            <div style="font-size: 20px; color:white">
                个人资料
            </div>
        </div>

        <div class="col-md-1 column" id="2" style="background-color: #004ea0;height: 80px">
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex;min-height: 800px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="personal"/>
        </jsp:include>
        <div class="col-md-9 column" id="content">
            <div style="height: 60px">
            </div>

            <div class="form">
                <div class="formbox">
                    <form role="form" action="${pageContext.request.contextPath}/jsp/personalUpdate.jsp" method="post"
                          enctype="multipart/form-data">

                        <div style="display: flex; justify-content: center; height: 30%">
                            <img class="img-circle" id="imagePreview"
                                 src="${mUser.imagePath} no-repeat center"
                                 alt="无图片"
                                 style="width: 50%; height: 100%; margin-bottom: 5px">
                        </div>

                        <div style="height: 40px; margin-top: 15px">
                            <label class="zhmm"><p>用户编号：</p></label>
                            <span>${mUser.userId}</span>
                        </div>

                        <div style="height: 40px">
                            <label class="zhmm"><p>用户名：</p></label>
                            &nbsp;&nbsp;&nbsp;
                            <span>${mUser.username}</span>
                        </div>

                        <div style="height: 40px">
                            <label class="zhmm"><p>密码：</p></label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span>${mUser.password}</span>
                        </div>

                        <div style="height: 40px">
                            <label class="zhmm"><p>性别：</p></label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span>${mUser.sex}</span>
                        </div>

                        <div style="height: 40px">
                            <label class="zhmm"><p>余额：</p></label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span>${mUser.balance}元</span>
                        </div>

                        <div style="height: 40px">
                            <label class="zhmm"><p>角色：</p></label>
                            &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                            <span>${mUser.account}</span>
                        </div>
                        <button><a
                                href="${pageContext.request.contextPath}/personalUpdate?userId=${mUser.userId}">修改</a>
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

<script>
    // 选择图片预览事件
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
