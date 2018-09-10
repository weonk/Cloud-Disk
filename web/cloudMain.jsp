<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2018/9/9
  Time: 22:24
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
    <title>文件上传</title>
</head>
<body>
    <form action="/uploadFile" enctype="multipart/form-data" method="post">
        上传文件：<input type="file" name="file"><br/>
        <input type="submit" value="提交">
    </form>
    <table border="0.5" width="1200">
        <tr align="center">
            <th>文件名</th>
            <th>大小</th>
            <th>上传日期</th>
            <th colspan="2">操作</th>
        </tr>
        <c:forEach var="file" items="${fileLists}">
            <tr align="center">
                <td>${file.name}</td>
                <td>${file.size}</td>
                <td>${file.date}</td>
                <td><a href="/downloadFile?fileId=${file.id}">下载</a></td>
                <td><a href="#">删除</a></td>
            </tr>
        </c:forEach>
    </table>
</body>
</html>
