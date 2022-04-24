<%--
  Created by IntelliJ IDEA.
  User: Administrator
  Date: 2021/11/15
  Time: 18:45
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
        a {
            width: 120px;
        }
    </style>
</head>
<body style="background-color: #004ea0">

<div style="height: 400px; display: flex; flex-direction: row; justify-content: center; align-items: center">

    <div class="panel panel-default" style="width: 40%">
        <div class="panel-heading">
            <h3 class="panel-title">
                结果
            </h3>
        </div>
        <div class="panel-body">
            <div style="display: flex; flex-direction: row; justify-content: center">
                <c:if test="${'succeed'.equals(sessionScope.result)}">
                    <h3 class="text-success">${sessionScope.message}</h3>
                </c:if>

                <c:if test="${'failed'.equals(sessionScope.result)}">
                    <h3 class="text-danger">${sessionScope.message}</h3>
                </c:if>
            </div>
            <div style="display: flex; justify-content: flex-end; margin-top: 20px">
                <a href="${pageContext.request.contextPath}/userList" class="btn btn-primary" role="button"
                   style="margin-right: 20px">
                    返回用户列表
                </a>
            </div>
        </div>
    </div>
</div>

</body>
</html>

