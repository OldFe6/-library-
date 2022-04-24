<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<link href="${pageContext.request.contextPath}/static/css/style.css" type="text/css" rel="stylesheet">

<div class="col-md-1 column" id="left" style="background-color: #004ea0;width: 15%">
    <ul class="nav nav-pills" style="">
        <li class='${"bookList".equals(param.navItem)?"active":""}'>
            <a href="${pageContext.request.contextPath}/bookList" style="color: white">图书列表</a>
        </li>

        <c:if test='${"管理员".equals(sessionScope.mUser.account)}'>
            <li class='${"bookManage".equals(param.navItem)?"active":""} || ${"bookAdd".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/bookAdd" style="color: white">图书管理</a>
            </li>
        </c:if>

        <c:if test='${"管理员".equals(sessionScope.mUser.account)}'>
            <li class='${"recordList".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/recordList" style="color: white">借阅记录</a>
            </li>
        </c:if>

        <c:if test='${"管理员".equals(sessionScope.mUser.account)}'>
            <li class='${"shoppingRecord".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/shoppingRecord" style="color: white">购买记录</a>
            </li>
        </c:if>

        <c:if test='${"普通用户".equals(sessionScope.mUser.account)}'>
            <li class='${"myShopping".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/myShopping?userId=${sessionScope.mUser.userId}" style="color: white">购买记录</a>
            </li>

            <li class='${"personalRecord".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/personalRecord?userId=${sessionScope.mUser.userId}" style="color: white">借阅记录</a>
            </li>
        </c:if>

        <c:if test='${"管理员".equals(sessionScope.mUser.account)}'>
            <li class='${"userList".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/userList" style="color: white">用户列表</a>
            </li>
        </c:if>

        <li class='${"personal".equals(param.navItem)?"active":""}'>
            <a href="${pageContext.request.contextPath}/personal?userId=${sessionScope.mUser.userId}" style="color: white">我的账户</a>
        </li>

        <c:if test='${"管理员".equals(sessionScope.mUser.account)}'>
            <li class='${"userManage".equals(param.navItem)?"active":""}'>
                <a href="${pageContext.request.contextPath}/userAdd" style="color: white">用户管理</a>
            </li>
        </c:if>

        <li class='${"login".equals(param.navItem)?"active":""}'>
            <a href="${pageContext.request.contextPath}/doLoginOut" id="loginOut" style="color: white">退出登录</a>
        </li>

    </ul>
</div>