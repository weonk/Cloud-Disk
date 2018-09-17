<%@ page import="com.chris.cloud.entity.User" %>
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
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script type="text/javascript">
        //打开文件选择框
        function selectFile(){
            $("#file").trigger("click");
        }

        function fileUpload(){
            var option = {
                url : "http://localhost:8080/uploadFile",
                type : 'POST',
                datatype:'json',
                clearForm: true,//提交后是否清空
                success : function(map) {
                    window.location.reload();
                    alert("上传成功!");
                } ,
                error:function(data){
                    alert("页面请求失败！");
                }
            };
            $("#upLoadForm").ajaxSubmit(option);
            return false;
        }

    </script>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" style="font-size: 30px" href="cloudMain">Cloud ++</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="cloudMain">我的网盘</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listAllFiles">搜索一下</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="#">关于我们</a>
            </li>
        </ul>
        <div class="ml-auto dropdown">
            <img class="dropdown-toggle" data-toggle="dropdown" src="images/user.png" style="width: 50px;height: 50px;margin-right: 20px;">
            <ul class="dropdown-menu">
                <li>
                    <span style="margin-left: 25px;font-size: 20px;"><%=((User) session.getAttribute("user")).getUserName() %></span>
                </li>
                <li>
                    <a class="dropdown-item" data-toggle="modal" data-target="#modifyUserInfo">修改资料</a>
                </li>
                <li>
                    <a class="dropdown-item" data-toggle="modal" data-target="#modifyUserPassword">修改密码</a>
                </li>
                <li>
                    <a class="dropdown-item" data-toggle="modal" data-target="#logout">退出登录</a>
                </li>
            </ul>
            <span style="margin-right: 20px; color: #11a3fc">  |  客户端下载</span>
        </div>

        <div class="modal fade" id="modifyUserInfo">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">用户资料修改</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <form class="form-group" action="modifyUserInfo" method="post">
                            <div class="form-group">
                                <label for="name">用户名</label>
                                <input class="form-control" type="text" id="name" name="userName" value="<%=((User) session.getAttribute("user")).getUserName() %>" placeholder="6-15位字母或数字">
                            </div>
                            <div class="form-group">
                                <label for="email">邮箱</label>
                                <input class="form-control" type="email" id="email" name="email" value="<%=((User) session.getAttribute("user")).getUserName() %>" placeholder="例如:123@123.com">
                            </div>
                            <div class="text-right">
                                <button class="btn btn-primary" type="submit">确认修改</button>
                                <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="modifyUserPassword">
            <div class="modal-dialog">
                <div class="modal-content">
                    <div class="modal-header">
                        <h4 class="modal-title">用户密码修改</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">×</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        <form class="form-group" action="modifyPassword" method="post">
                            <div class="form-group">
                                <label for="oldPassword">原密码</label>
                                <input class="form-control" type="text" id="oldPassword" name="oldPassword" placeholder="请输入原密码">
                            </div>
                            <div class="form-group">
                                <label for="newPassword">新密码</label>
                                <input class="form-control" type="password" id="newPassword" name="newPassword" placeholder="请输入新密码">
                            </div>
                            <div class="form-group">
                                <label for="newConfirmPassword">新密码</label>
                                <input class="form-control" type="password" id="newConfirmPassword" name="newConfirmPassword" placeholder="请输入重复密码">
                            </div>
                            <div class="text-right">
                                <button class="btn btn-primary" type="submit">确认修改</button>
                                <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                            </div>
                        </form>
                    </div>
                </div>
            </div>
        </div>

        <div class="modal fade" id="logout">
            <div class="modal-dialog">
                <div class="modal-content">

                    <div class="modal-header">
                        <h4 class="modal-title">Warning</h4>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">x</span>
                        </button>
                    </div>

                    <div class="modal-body">
                        您确定要退出登录吗？
                    </div>

                    <div class="modal-footer">
                        <a href="logout" class="btn btn-warning">退出登录</a>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    </div>

                </div>
            </div>
        </div>

    </nav>

    <div class="container">
        <form id="upLoadForm" enctype="multipart/form-data">
            <input type="file" name="file" id="file" style="display:none" multiple="multiple" onchange="fileUpload()">
            <span id="upLoad" class="btn btn-primary btn-sm" style="margin-top: 35px;" onclick="selectFile()">上传文件</span>
        </form>
    </div>

    <div class="container">
        <%
            if (request.getAttribute("fileLists") == null) {%>
                <img src="images/nofile.png">
            <%} else {%>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th>文件名</th>
                        <th>大小</th>
                        <th>上传日期</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="file" items="${fileLists}">
                        <tr class="table-light">
                            <td>${file.name}</td>
                            <td>${file.size}</td>
                            <td>${file.date}</td>
                            <td><a href="downloadFile?fileId=${file.id}"><img style="width: 20px; height: 20px;" src="images/download.png" alt="下载"></a></td>
                            <td><a data-toggle="modal" data-target="#${file.id}"><img style="width: 20px; height: 20px;" src="images/delete.png" alt="删除"></a></td>
                        </tr>
                        <div class="modal fade" id="${file.id}">
                            <div class="modal-dialog">
                                <div class="modal-content">

                                    <div class="modal-header">
                                        <h4 class="modal-title">提示信息</h4>
                                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                            <span aria-hidden="true">×</span>
                                        </button>
                                    </div>

                                    <div class="modal-body">
                                        您确认要删除该文件吗？
                                    </div>

                                    <div class="modal-footer">
                                        <a href="deleteFile?fileId=${file.id}" class="btn btn-primary">确认删除</a>
                                        <button type="button" class="btn btn-secondary" data-dismiss="modal">取消</button>
                                    </div>

                                </div>
                            </div>
                        </div>
                    </c:forEach>
                    </tbody>
                </table>
            <%}
        %>
    </div>

    <footer class="footer bg-dark">
        <p align="center">2018 ©Cloud ++ | <a href="https://github.com/iamchriswu" style="color: white">GitHub</a></p>

    </footer>
</body>
</html>
