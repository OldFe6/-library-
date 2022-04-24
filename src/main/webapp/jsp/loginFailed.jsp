<%--
  Created by IntelliJ IDEA.
  User: lenovo
  Date: 2021/11/22
  Time: 11:22
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>登录失败</title>
    <meta http-equiv="refresh" content="5;url=${pageContext.request.contextPath}/jsp/login.jsp"/>
</head>
<body>

<p>
    用户没有登录，请登陆后再访问此页面！将在<span id="timespan" style="color: #ff0000; font-weight: bold">5</span>秒后跳转到登录页面...
</p>

<script>
    const refreshHandler = function (event) {
        let intervalResult;
        const intervalHandler = function () {
            let time = parseInt(document.getElementById("timespan").innerText)
            time -= 1;
            document.getElementById("timespan").innerText = "" + time
            if (time === 0) {
                clearInterval(intervalResult);
            }
        }
        intervalResult = setInterval(intervalHandler, 1000)
    }
    window.addEventListener("load", refreshHandler)
</script>

</body>
</html>
