<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>图书列表</title>
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

        <div class="col-md-12 column" id="search"
             style="position: fixed;width: 100%;top: 0;z-index: 99; <%--导航栏固定悬浮在最上层--%>
                     background-color: #004ea0;height: 80px; display: flex; flex-direction: column; justify-content: center">

            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/bookList"
                  method="get" style="margin: 0; display: flex; justify-content: flex-end; margin-right: 65px">

                <div style="position: absolute; left: 85px">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png"
                         style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left: 130px;  font-size: 30px; color: white">
                    阿仁图书馆
                </div>

                <div class="form-group">
                    <label for="bookName" style="color: white">图书名称</label>
                    <input type="text" class="form-control" id="bookName" style="width: 140px"
                           name="bookName" placeholder="按图书名称搜索"
                           value="${mBookSearch.bookName}">
                </div>

                <div class="form-group" style="margin-left: 10px">
                    <label for="author" style="color: white">作者</label>
                    <input type="text" class="form-control" id="author" style="width: 140px"
                           name="author" placeholder="按作者搜索"
                           value="${mBookSearch.author}">
                </div>

                <div class="form-group" style="margin-left: 10px">
                    <label for="priceOrder" style="color: white">价格排序</label>
                    <select class="form-control" id="priceOrder" name="priceOrder">
                        <option value="unordered"
                        ${"unordered".equals(mBookSearch.priceOrder) ? "selected" : ""}
                        >无序
                        </option>

                        <option value="asc"
                        ${"asc".equals(mBookSearch.priceOrder) ? "selected" : ""}
                        >升序
                        </option>

                        <option value="desc"
                        ${"desc".equals(mBookSearch.priceOrder) ? "selected" : ""}
                        >降序
                        </option>
                    </select>
                </div>

                <button id="resetButton" type="reset" class="btn btn-default"
                        style="margin-left: 10px">重置
                </button>
                <button type="submit" class="btn btn-default"
                        style="margin-left: 10px">搜索
                </button>
                <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                    <a href="${pageContext.request.contextPath}/bookAdd" class="btn btn-default" role="button"
                       style="margin-left: 10px; display: flex; flex-direction: column; justify-content: center">
                        <span style="display: block; text-align: center">新增</span>
                    </a>
                </c:if>
            </form>
        </div>
        <div class="col-md-1 column" id="2" style="background-color: #004ea0;height: 80px">
        </div>
    </div>

    <div class="row clearfix" id="body" style="display: flex; min-height: 800px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="bookList"/>
        </jsp:include>
        <div class="col-md-9 column" id="content" style="background-color: white">

            <div style="display: flex; flex-wrap: wrap">
                <c:forEach var="mBook" items="${mBooks}">
                    <div class="row clearfix" style="width: 20%; margin-top: 30px; margin-left: 10px">
                        <div class="col-md-12 column" id="main">
                            <div>
                                <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                                    <div style="display: flex; justify-content: flex-end; margin-right: 13px">
                                        <img href="${pageContext.request.contextPath}/javascript:void(0)"
                                             class="book-delete-field"
                                             data-library-delete-book-id="${mBook.bookId}"
                                             src="${pageContext.request.contextPath}/static/images/deleteLogo.png"
                                             style="width: 20px; height: 20px;border-radius: 6px">
                                    </div>
                                </c:if>
                                <div style="display: flex; justify-content: center">
                                    <img href="${pageContext.request.contextPath}/lendBook?bookId=${mBook.bookId}"
                                         class="img-rounded" id="imagePreview"
                                         src="${pageContext.request.contextPath}/${mBook.imagePath}"
                                         alt="无图片"
                                         style="width: 120px; height: 150px; margin-bottom: 5px; align-items: center">
                                </div>
                                <p style="display: flex; justify-content: center">书名: ${mBook.bookName}</p>
                                <p style="display: flex; justify-content: center">价格: ${mBook.price}元</p>
                                <div style="display: flex; flex-wrap: wrap; justify-content: center">

                                    <a href="${pageContext.request.contextPath}/bookDetail?bookId=${mBook.bookId}"
                                       class="btn btn-default" role="button"
                                       style="margin-right: 10px; background-color: #2aabd2 ">
                                        <span style="display: block; text-align: center">详情</span>
                                    </a>
                                    <c:choose>
                                        <c:when test='${"是".equals(mBook.bookCount)}'>
                                            <c:choose>
                                                <c:when test='${"是".equals(mBook.usable)}'>
                                                    <a href="${pageContext.request.contextPath}/lendBook?bookId=${mBook.bookId}"
                                                       role="button"
                                                       style="background-color: #2aabd2"
                                                       id="data-library-lend-book-id"
                                                       class="data-library-lend-book-class btn btn-default">
                                                        借书
                                                    </a>
                                                </c:when>
                                                <c:otherwise>
                                                    <a href="#" role="button" class="btn btn-default" disabled
                                                       style="background-color: red; color: white">
                                                        不可借
                                                    </a>
                                                </c:otherwise>
                                            </c:choose>
                                        </c:when>
                                        <c:otherwise>
                                            <a href="#" role="button" class="btn btn-default" disabled
                                               style="background-color: red; color: white">
                                                不可借
                                            </a>
                                        </c:otherwise>
                                    </c:choose>

                                    <c:if test="${'普通用户'.equals(sessionScope.mUser.account)}">
                                        <c:choose>
                                            <c:when test='${"普通用户".equals(sessionScope.mUser.account)}'>
                                                <c:choose>
                                                    <c:when test='${"是".equals(mBook.bookCount)}'>
                                                        <a href="${pageContext.request.contextPath}/buyBook?bookId=${mBook.bookId}"
                                                           class="btn btn-default"
                                                           role="button"
                                                           style="margin-left: 10px; background-color: #2aabd2 ">
                                                            <span style="display: block; text-align: center">购买</span>
                                                        </a>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <a href="#" disabled
                                                           class="btn btn-default"
                                                           role="button"
                                                           style="margin-left: 10px; background-color: red ">
                                                            <span style="display: block; text-align: center;color: white">不卖</span>
                                                        </a>
                                                    </c:otherwise>
                                                </c:choose>
                                            </c:when>
                                            <c:otherwise>
                                                <a href="#" disabled
                                                   class="btn btn-default"
                                                   role="button"
                                                   style="margin-left: 10px; background-color: red ">
                                                    <span style="display: block; text-align: center; color: white">不卖</span>
                                                </a>
                                            </c:otherwise>
                                        </c:choose>
                                    </c:if>

                                    <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                                        <a href="${pageContext.request.contextPath}/bookUpdate?bookId=${mBook.bookId}"
                                           class="btn btn-default"
                                           role="button"
                                           style="margin-left: 10px; background-color: #2aabd2 ">
                                            <span style="display: block; text-align: center">修改</span>
                                        </a>
                                    </c:if>
                                </div>
                            </div>
                        </div>
                    </div>
                </c:forEach>
            </div>
            <%--            page and foot begin--%>
            <div>
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
            <%--            page and foot end--%>
        </div>
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2; width: 5%">
        </div>
    </div>
    <div class="row clearfix" id="foot">
        <div class="col-md-12 column" style="background-color: #004ea0;
                 height: 50px; line-height: 50px; text-align: center">
            阿仁图书馆版权所有&copy;版权所有 <a href="mailto:demo@demo.com" style="color: white;">library@library.com</a>
        </div>
    </div>

    <!-- 模态框删除确认 -->
    <div class="modal fade" id="bookDeleteConfirmModal">
        <div class="modal-dialog">
            <div class="modal-content message_align">
                <div class="modal-header">
                    <button type="button" class="close" data-dismiss="modal"
                            aria-label="Close">
                        <span aria-hidden="true">×</span>
                    </button>
                    <h4 class="modal-title">提示</h4>
                </div>
                <div class="modal-body">
                    <!-- 隐藏需要删除的id -->
                    <input type="hidden" id="deleteBookId"/>
                    <p>您确认要删除该图书吗？</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            id="bookDeleteConfirmBtn">确认
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <%--    / .modal-dialog --%>
    </div>
</div>


<script>

    $(document).ready(function () {
        document.getElementById("resetButton").addEventListener("click", function (event) {
            event.stopPropagation()
            event.preventDefault()

            $("#bookId").val("")
            $("#priceOrder").val("unordered")

            $(this).blur()
        })
    })

    $(document).ready(function () {
        $(".book-usable-field").on("click", "", {}, function (event) {
            event.stopPropagation()
            event.preventDefault()

            const bookId = $(this).attr("data-library-book-id")
            let usable = $(this).text().trim()

            if ("是" === usable) {
                usable = "否"
            } else {
                usable = "是"
            }

            const url = "${pageContext.request.contextPath}/doBookUpdateUsable"
            const data = {
                bookId: bookId,
                usable: usable,
            }

            const outThis = this
            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $(outThis).text(usable)
                    const response = JSON.parse(data)
                    if (usable === "是") {
                        alert("编号为" + response.bookId + "的图书上架状态成功！")
                    } else {
                        alert("编号为" + response.bookId + "的图书下架状态成功！")
                    }
                },
                error: function (data, textStatus) {
                    if (textStatus === "timeout") {
                        alert("更新上下架状态失败！请求处理超时")
                    } else {
                        alert("更新上下架状态失败！错误信息:" + data.responseText)
                    }
                },
                timeout: 6000
            });
        })
    })

    //删除事件
    $(document).ready(function () {
        $(".book-delete-field").on("click", "", {}, function (event) {
            event.stopPropagation()
            event.preventDefault()

            const bookId = $(this).attr("data-library-delete-book-id")

            $("#deleteBookId").val(bookId);//将模态框中需要删除的图书ID设为需要删除的ID

            $("#bookDeleteConfirmModal").modal({
                backdrop: "static",
                keyboard: false
            });
        })
    })

    $(document).ready(function () {
        $("#bookDeleteConfirmBtn").on("click", "", {}, function (event) {
            event.stopPropagation()
            event.preventDefault()

            const bookId = $("#deleteBookId").val()

            const url = "${pageContext.request.contextPath}/doBookDelete"
            const data = {
                bookId: bookId,
            }

            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text",
                success: function (data, textStatus) {
                    $("#bookDeleteConfirmModal").modal("hide")
                    const response = JSON.parse(data)
                    $("[data-library-delete-book-id=" + response.bookId + "]").parent().parent().remove()

                    setTimeout(function () {
                        alert("编号为" + response.bookId + "的图书删除成功！")
                    }, 1000)
                },
                error: function (data, textStatus) {
                    $("#bookDeleteConfirmModal").modal("hide")
                    setTimeout(function () {
                        if (textStatus === "timeout") {
                            alert("删除失败！请求处理超时")
                        } else {
                            alert("删除失败！错误信息:" + data.responseText)
                        }
                    }, 1000)
                },
                timeout: 6000
            });
        })
    })
</script>

</body>
</html>







