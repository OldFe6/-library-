<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/18
  Time: 15:37
  To change this template use File | Settings | File Templates.
--%>
<
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>用户列表</title>

    <script src="${pageContext.request.contextPath}/static/web-libs/jquery/jquery-3.6.0.js" type="text/javascript"
            rel="script"></script>

    <link href="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/css/bootstrap.css"
          type="text/css" rel="stylesheet">

    <script src="${pageContext.request.contextPath}/static/web-libs/bootstrap-3.4.1-dist/js/bootstrap.js"
            type="text/javascript" rel="script"></script>

    <link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet">


</head>
<body>

<div class="container" id="container" style="width: 100%">
    <div class="row clearfix" id="head">
        <div class="col-md-1 column" id="1" style="background-color: #004ea0; height: 60px">
        </div>

        <div class="col-md-10 column" id="search"
             style="position: fixed;width: 100%;top: 0;z-index: 99; <%--导航栏固定悬浮在最上层--%>
                     background-color: #004ea0;height: 80px; display: flex; flex-direction: column; justify-content: center">
            <form class="form-inline" role="form" action="${pageContext.request.contextPath}/userList"
                  method="get"
                  style="margin: 0; display: flex; justify-content: flex-end; margin-right: 65px">

                <div style="position: absolute; left:85px">
                    <img src="${pageContext.request.contextPath}/static/images/sun.png" style="width: 40px; height: 40px">
                </div>
                <div style="position: absolute; left:130px; font-size: 30px; color: white">
                    阿仁图书馆
                </div>

                <div class="form-group">
                    <label for="userId" style="color: white">用户Id</label>
                    <input type="text" class="form-control" id="userId" style="width: 140px"
                           name="userId" placeholder="按用户id搜索"
                           value="${mUserSearch.userId}">
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <label for="username" style="color: white">用户姓名</label>
                    <input type="text" class="form-control" id="username" style="width: 140px"
                           name="username" placeholder="按用户名搜索"
                           value="${mUserSearch.username}">
                </div>
                <div class="form-group" style="margin-left: 10px">
                    <label for="account" style="color: white">用户类型</label>
                    <input type="text" class="form-control" id="account" style="width: 140px"
                           name="account" placeholder="按类型搜索"
                           value="${mUserSearch.account}">
                </div>

                <button id="resetButton" type="reset" class="btn btn-default"
                        style="margin-left: 10px">重置
                </button>
                <button type="submit" class="btn btn-default"
                        style="margin-left: 10px">搜索
                </button>
                <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                    <a href="${pageContext.request.contextPath}/userAdd" class="btn btn-default" role="button"
                       style="margin-left: 10px; display: flex; flex-direction: column; justify-content: center">
                        <span style="display: block; text-align: center">新增</span>
                    </a>
                </c:if>
            </form>
        </div>
        <div class="col-md-1 column" id="2" style="background-color: #004ea0; height: 60px">
        </div>
    </div>

    <div class="row clearfix" id="body" style="display: flex; min-height: 800px">
        <div class="col-md-1 column" id="content" style="background-color: #aeb0b2;width: 5%">
        </div>
        <jsp:include page="left.jsp">
            <jsp:param name="navItem" value="userList"/>
        </jsp:include>
        <div class="col-md-9 column" id="content" style="background-color: white">


            <div class="row clearfix">
                <div class="col-md-12 column" id="main" style="padding: 0">
                    <table class="table"
                           style="margin: 20px auto 0 auto;width: 96%; font-size: 14px">
                        <thead>
                        <tr>
                            <th>用户编号</th>
                            <th>照片</th>
                            <th>用户名</th>
                            <th>密码</th>
                            <th>性别</th>
                            <th>电话</th>
                            <th>角色（账户类型）</th>
                            <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                                <th>修改</th>
                                <th>删除</th>
                            </c:if>
                        </tr>
                        </thead>
                        <tbody>
                        <c:forEach var="mUser" items="${mUsers}">
                            <tr>
                                <td style="line-height: 60px">${mUser.userId}</td>
                                <td style="line-height: 60px">
                                    <img class="img-circle" id="imagePreview"
                                         src="${pageContext.request.contextPath}/${mUser.imagePath}"
                                         alt="无图片"
                                         style="height: 60px; width: 60px">
                                </td>
                                <td style="line-height: 60px">${mUser.username}</td>
                                <td style="line-height: 60px">${mUser.password}</td>
                                <td style="line-height: 60px">${mUser.sex}</td>
                                <td style="line-height: 60px">${mUser.tel}</td>
                                <td style="line-height: 60px">${mUser.account}</td>
                                <td style="line-height: 60px">
                                    <a href="${pageContext.request.contextPath}/userUpdate?userId=${mUser.userId}">修改</a>
                                </td>
                                <td style="line-height: 60px">
                                    <a href="${pageContext.request.contextPath}/javascript:void(0)"
                                       class="user-delete-field"
                                       data-library-delete-user-id="${mUser.userId}">
                                        删除
                                    </a>
                                </td>
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

    <div class="modal fade" id="userDeleteConfirmModal">
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
                    <input type="hidden" id="deleteUserId"/>
                    <p>您确认要删除该用户吗？</p>
                </div>
                <div class="modal-footer">
                    <button type="button" class="btn btn-default"
                            data-dismiss="modal">取消
                    </button>
                    <button type="button" class="btn btn-primary"
                            id="userDeleteConfirmBtn">确认
                    </button>
                </div>
            </div>
            <!-- /.modal-content -->
        </div>
        <%--    / .modal-dialog --%>
    </div>
</div>


<script>
    // 选择图片预览
    $(document).ready(function () {
        document.getElementById("image").addEventListener("change", function (event) {
            event.stopPropagation()
            event.preventDefault()

            const file = this.files[0]
            const url = window.URL.createObjectURL(file)
            $("#imagePreview").attr("src", url)


        })
    })


    // 删除事件
    $(document).ready(function () {
        $(".user-delete-field").on("click", "", {}, function (event) {
            event.stopPropagation()
            event.preventDefault()

            // 获取当前标签的 data-library-delete-user-id 属性
            const userId = $(this).attr("data-library-delete-user-id")

            $("#deleteUserId").val(userId); //将模态框中需要删除的用户的ID设为需要删除的ID

            $("#userDeleteConfirmModal").modal({
                backdrop: 'static',
                keyboard: false
            });
        })
    })

    // 模态框事件
    $(document).ready(function () {
        // 点击模态框确认按钮触发事件
        $("#userDeleteConfirmBtn").on("click", "", {}, function (event) {
            event.stopPropagation()
            event.preventDefault()

            const userId = $("#deleteUserId").val()

            const url = "${pageContext.request.contextPath}/doUserDelete"
            const data = {
                userId: userId,
            }

            $.ajax({
                url: url,
                type: "post",
                data: data,
                dataType: "text", // 返回类型
                success: function (data, textStatus) {
                    $("#userDeleteConfirmModal").modal("hide")
                    const response = JSON.parse(data)
                    $("[data-library-delete-user-id=" + response.userId + "]").parent().parent().remove()

                    setTimeout(function () {
                        alert("编号为：" + response.userId + "的用户成功！")
                    }, 1000)
                },
                error: function (data, textStatus) {
                    $("#userDeleteConfirmModal").modal("hide")

                    setTimeout(function () {
                        if (textStatus === "timeout") {
                            alert("删除用户失败！请求处理超时")
                        } else {
                            alert("删除用户失败！错误信息：" + data.responseText)
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
