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
    <title>图书管理</title>

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
            width: 316px;
            height: 730px;
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
            font-size: 17px;
            font-weight: bold;
            display: inline;
            margin:5px 5px 5px 2px;
        }
        .form .formbox form input {
            margin: 10px;
            height: 30px;
            width: 180px;
        }
        .form .formbox form span {
            font-size: 17px;
            margin: 10px;
            height: 35px;
            width: 204px;
        }
        .form .formbox form button {
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
        .form .formbox:hover form button {
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
                <div style="position: absolute;left: 85px; top: 23px">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png"
                         style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left: 130px; top: 23px; font-size: 30px; color: white">
                    阿仁图书馆
                </div>
            </div>
            <div style="font-size: 20px; color:white">
                买书
            </div>
        </div>

        <div class="col-md-1 column" id="2" style="background-color: #004ea0;height: 80px">
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex; min-height: 800px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="lendManage"/>
        </jsp:include>
        <div class="col-md-9 column" id="content">
            <div style="height: 60px">
            </div>

            <div class="form">
                <div class="formbox">
                    <form role="form" action="${pageContext.request.contextPath}/buyBook" method="get"
                          enctype="multipart/form-data">
                        <label for="image">照片:</label>
                        <div style="display: flex; justify-content: center; height: 20%">
                            <img class="img-rounded" id="imagePreview"
                                 src="${mBook.imagePath} no-repeat center"
                                 alt="无图片"
                                 style="width: 50%; height: 100%; margin-bottom: 5px">
                        </div>
                        <input type="file" id="image" name="image">
                        <!--账号-->
                        <label for="zh1" class="zhmm"><p>图书编号：</p></label>
                        <input id="zh1" name="bookId" value="${mBook.bookId}" type="text" placeholder="请输入书名"  readonly required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--账号-->
                        <label for="zh" class="zhmm"><p>图书名称：</p></label>
                        <input id="zh" name="bookName" value="${mBook.bookName}" type="text" placeholder="请输入书名" readonly required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--用户名-->
                        <label for="username" class="zhmm"><p>作者：</p></label>
                        &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;
                        <input id="username" name="author" value="${mBook.author}" type="text" placeholder="请输入作者" readonly required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--密码-->
                        <label for="mm" class="zhmm"><p>出版社：</p></label>
                        &nbsp;&nbsp;&nbsp;
                        <input id="mm" name="publish" value="${mBook.publish}" type="text" placeholder="请输入出版社" readonly required/>
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--手机号-->
                        <label for="sjh" class="zhmm"><p>图书库存：</p></label>
                        <input id="sjh" name="bookCount" value="${mBook.bookCount}" type="number" placeholder="请输入库存" readonly required/> <!--pattern="^1[3-9][0-9]{9}$-->
                        <p style="padding-left: 20px"></p>
                        <br>
                        <!--性别-->
                        <label for="sex" class="zhmm"><p>图书价格：</p></label>

                        <input id="sex" name="price" value="${mBook.price}" type="text" placeholder="请输入价格" readonly required/>

<%--                        <br>--%>
<%--                        <!--账户类型-->--%>
<%--                        <label for="usable" class="zhmm"><p>是否上架：</p></label>--%>

<%--                        <input type="checkbox" id="usable" name="usable" style="width: 50px"  readonly checked/>--%>
<%--                        <!--注册提交-->--%>
                        <br>
                        <button type="submit">
                            <a style="text-decoration: none; color: whitesmoke" href=""
                               id="updateStatus"
                               data-library-book-id="${mBook.bookId}"
                               data-library-user-id="${sessionScope.mUser.userId}"
<%--                               data-library-book-status="${mBook.status}">--%>
                                >
                                够买
                            </a></button>
<%--                        <button>--%>
<%--                            <a style="border: 0; color: whitesmoke; text-decoration:none" href="${pageContext.request.contextPath}/bookList" onClick="javascript:history.go(-1)">不买了</a>--%>
<%--                        </button>--%>
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
    $(document).ready(function () {
        $("#updateStatus").on("click", "", {}, function (event) {
            event.stopPropagation()//阻止冒泡
            // event.preventDefault()//阻止默认事件

            const bookId = $(this).attr("data-library-book-id")
            const userId = $(this).attr("data-library-user-id")
            // const status = $(this).attr("data-library-book-status")

            const url = "${pageContext.request.contextPath}/doBookBuy"
            const data = {
                bookId: bookId,
                userId: userId,
                // status: status,
            }

            const outThis = this
            $.ajax({
                async: false,
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $(outThis).text(status)
                    const response = JSON.parse(data)
                    if (status === true) {
                        alert("编号为" + response.bookId + "的图书够买成功！")
                        window.location.href="myShopping?userId="+${sessionScope.mUser.userId}
                    } else {
                        alert("编号为" + response.bookId + "的图书够买成功！")
                    }
                },
                error: function (data, textStatus) {
                    if (textStatus === "timeout") {
                        alert("够买图书失败！请求处理超时")
                    } else {
                        alert("够买图书失败！错误信息:" + data.responseText)
                    }
                },
                beforeSend: function (xhr) {
                    xhr.setRequestHeader("header001", "header001")
                },
                timeout: 6000
            });
        })
    })
</script>

</body>
</html>
