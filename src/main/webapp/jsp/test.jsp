<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2022/1/6
  Time: 18:09
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<div class="row clearfix">
    <div class="col-md-12 column" id="title" style="padding: 0; display: flex; justify-content: center">
        <form role="form" action="${pageContext.request.contextPath}/doPersonalUpdate" method="post"
              enctype="multipart/form-data"
              style="width: 50%; margin-top: 20px">

            <div class="form-group">
                <label for="userId">用户编号</label>
                <input type="text" class="form-control" id="userId"
                       name="userId" readonly
                       value="${mUser.userId}">
            </div>
            <div class="form-group">
                <label for="username">用户名</label>
                <input type="text" class="form-control" id="username"
                       name="username" placeholder="请输入用户名"
                       value="${mUser.username}">
            </div>
            <div class="form-group">
                <label for="password">密码</label>
                <input type="text" class="form-control" id="password"
                       name="password" placeholder="请输入密码"
                       value="${mUser.password}">
            </div>
            <div class="form-group">
                <label class="form-group">性别:</label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="man" style="margin-left: 20px" value="1"
                    ${"1".equals(mUser.sex)?"checked":""}>男
                </label>
                <label class="radio-inline">
                    <input type="radio" name="sex" id="woman" style="margin-left: 20px" value="0"
                    ${"0".equals(mUser.sex)?"checked":""}>女
                </label>
            </div>
            <div class="form-group">
                <label for="tel">电话</label>
                <input type="text" class="form-control" id="tel"
                       name="tel" placeholder="请输入电话号码"
                       value="${mUser.tel}">
            </div>
            <div class="form-group">
                <label class="form-group">角色:</label>
                <select id="account" class="form-control" name="account">
                    <c:if test="${'管理员'.equals(sessionScope.mUser.account)}">
                        <option value="1">管理员</option>
                    </c:if>
                    <option value="0">普通用户</option>
                </select>
            </div>

            <div class="form-group">
                <label for="image">照片</label>
                <div>
                    <img class="img-rounded" id="imagePreview"
                         src="${mUser.imagePath}"
                         alt="无图片"
                         style="width: 200px; height: 150px; margin-bottom: 5px">
                </div>
                <input type="file" id="image" name="image">
            </div>


            <div style="display: flex; justify-content: flex-end">
                <button type="reset" class="btn btn-default">重置</button>
                <button type="submit" class="btn btn-default btn-primary" style="margin-left: 30px">提交
                </button>
            </div>

        </form>
    </div>
</div>

</body>
</html>
