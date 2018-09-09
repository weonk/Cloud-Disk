<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2018/9/9
  Time: 22:35
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>注册</title>
</head>
<body>
    <form action="/register" method="post">
        账号<input name="userName"><br>
        密码<input type="password" name="password"><br>
        密码<input type="password" name="confirmPassword"><br>
        <input type="submit" value="注册">
    </form>
</body>
</html>
