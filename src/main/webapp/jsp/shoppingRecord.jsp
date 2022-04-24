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
    <title>租车记录</title>

    <link href="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css"
          type="text/css" rel="stylesheet">
    <script src="${pageContext.request.contextPath}/static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript"
            rel="script"></script>
    <script src="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js"
            type="text/javascript" rel="script"></script>

    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet">

</head>
<body>

<div class="container" id="container" style="width: 100%">
    <div class="row clearfix" id="head">
        <div class="col-md-1 column" id="1" style="background-color: #004ea0;height: 80px">
        </div>

        <div class="col-md-12 column" id="search" style="position: fixed;width: 100%;top: 0;z-index: 99;
        background-color: #004ea0;height: 80px; display: flex; flex-direction: column; justify-content: center">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/shoppingRecord"
                  method="get" style="margin: 0; margin-right: 65px; display: flex; justify-content: flex-end">

                <div style="position:absolute; left: 85px">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png"
                         style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left: 130px; font-size: 30px; color: white">
                    阿仁图书馆
                </div>
                <div class="form-group">
                    <label for="bookId" style="color: white">图书编号</label>
                    <input type="text" class="form-control" id="bookId" style="width: 140px"
                           name="bookId" placeholder="按图书编号搜索"
                           value="${mBookSearch.bookId}">
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <label for="bookName" style="color: white">图书名称</label>
                    <input type="text" class="form-control" id="bookName" style="width: 140px"
                           name="bookName" placeholder="按图书名称搜索"
                           value="${mBookSearch.bookName}">
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <label for="userId" style="color: white">用户编号</label>
                    <input type="text" class="form-control" id="userId" style="width: 140px"
                           name="userId" placeholder="按用户编号搜索"
                           value="${mUserSearch.userId}">
                </div>

                <button id="resetButton" type="reset" class="btn btn-default"
                        style="margin-left: 10px">重置
                </button>
                <button type="submit" class="btn btn-default"
                        style="margin-left: 10px">搜索
                </button>
            </form>
        </div>

        <div class="col-md-1 column" id="2" style="background-color: #004ea0;height: 80px">
        </div>
    </div>
    <div class="row clearfix" id="body" style="display: flex; min-height: 800px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="shoppingRecord"/>
        </jsp:include>
        <div class="col-md-9 column" id="content">

            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0">
                                        <table class="table table-bordered"
                           style="margin: 20px auto 0 auto;width: 96%; font-size: 14px">
                        <thead>
                        <tr>
                            <th>编号</th>
                            <th>用户编号</th>
                            <th>用户名</th>
                            <th>图书编号</th>
                            <th>图书名称</th>
                            <th>作者</th>
                            <th>价格</th>
                            <th>图书库存</th>
                            <th>购买时间</th>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mShops" items="${requestScope.mShops}">
                            <tr>
                                <td style="line-height: 30px">${mShops.id}</td>
                                <td style="line-height: 30px">${mShops.userId}</td>
                                <td style="line-height: 30px">${mShops.username}</td>
                                <td style="line-height: 30px">${mShops.bookId}</td>
                                <td style="line-height: 30px">${mShops.bookName}</td>
                                <td style="line-height: 30px">${mShops.author}</td>
                                <td style="line-height: 30px">${mShops.price}</td>
                                <td style="line-height: 30px">${mShops.bookCount}</td>
                                <td style="line-height: 30px">${mShops.buyTime}</td>
                            </tr>
                        </c:forEach>
                        </tbody>
                    </table>
                    <div style="display: flex; justify-content: center; font-size: 12px">
                        <ul class="pagination">
                            <li><a href="${pageContext.request.contextPath}/${mPageNav.first.url}">首页</a></li>
                            <li><a href="${pageContext.request.contextPath}/${mPageNav.previous.url}">&laquo;</a></li>
                            <c:forEach var="page" items="${mPageNav.pages}">
                                <li class="${page.index.equals(mPageNav.currentPageIndex) ? "active" : ""}">
                                    <a href="${pageContext.request.contextPath}/${page.url}">${page.index}</a>
                                </li>
                            </c:forEach>
                            <li><a href="${pageContext.request.contextPath}/${mPageNav.next.url}">&raquo;</a></li>
                            <li><a href="${pageContext.request.contextPath}/${mPageNav.max.url}">尾页</a></li>
                        </ul>
                    </div>
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
    // 重置事件
    $(document).ready(function () {
        // 点击重置按钮触发的事件
        document.getElementById("resetButton").addEventListener("click", function (event) {
            event.stopPropagation()
            event.preventDefault()

            $("#bookId").val("")
            $("#userId").val("")

            $(this).blur()
        })
    })

    $(document).ready(function () {
        $(".book-lendBook-field").on("click", "", {}, function (event) {
            event.stopPropagation()
            // event.preventDefault()

            $(this).text("已还书");

            const bookId = $(this).attr("data-library-book-id")
            const id = $(this).attr("data-library-record-id")
            let status = $(this).text().trim()


            const url = "${pageContext.request.contextPath}/doBookUpdateStatus"
            const data = {
                bookId: bookId,
                status: status,
                id: id,
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
                    if (status === "已还书") {
                        alert("编号为" + response.bookId + "的图书已还！")
                    }
                },
                error: function (data, textStatus) {
                    if (textStatus === "timeout") {
                        alert("还书失败！请求处理超时")
                    } else {
                        alert("还书状态失败！错误信息:" + data.responseText)
                    }
                },
                timeout: 6000
            });
            $(this).unbind("click");
        })
    })
</script>

</body>
</html>
