<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2018/9/9
  Time: 16:19
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Cloud ++</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" href="#">Cloud ++</a>
    </nav>
    <div class="container">
        <div class="row">
            <div class="col-md-offset-3 col-md-6">
                <form class="form-horizontal" action="login" method="post">
                    <span class="heading">用户登录</span>
                    <div class="form-group">
                        <input type="text" class="form-control" name="userName" placeholder="用户名或电子邮件">
                    </div>
                    <div class="form-group help">
                        <input type="password" class="form-control" name="password" placeholder="密　码">
                    </div>
                    <div class="form-group">
                        <div class="main-checkbox">
                            <input type="checkbox" value="None" id="checkbox1" name="check"/>
                            <label for="checkbox1"></label>
                        </div>
                        <span class="text">Remember me</span>
                        <button type="submit" class="btn btn-default">登录</button>
                        <button type="button" class="btn btn-default" data-toggle="modal" data-target="#register">注册</button>
                    </div>
                </form>
                <div class="modal fade" id="register">
                    <div class="modal-dialog">
                        <div class="modal-content form-horizontal" style="height: auto">
                            <div class="modal-body">
                                <button class="close" data-dismiss="modal">
                                    <span>&times;</span>
                                </button>
                            </div>
                            <div class="modal-title">
                                <span class="heading">用户注册</span>
                            </div>
                            <div class="modal-body">
                                <form class="form-group" action="register" method="post">
                                    <div class="form-group">
                                        <label for="name">用户名</label>
                                        <input class="form-control" type="text" id="name" name="userName" placeholder="6-15位字母或数字">
                                    </div>
                                    <div class="form-group">
                                        <label for="pwd">密码</label>
                                        <input class="form-control" type="password" id="pwd" name="password" placeholder="至少6位字母或数字">
                                    </div>
                                    <div class="form-group">
                                        <label for="cPwd">再次输入密码</label>
                                        <input class="form-control" type="password" id="cPwd" name="confirmPassword" placeholder="至少6位字母或数字">
                                    </div>
                                    <div class="form-group">
                                        <label for="email">邮箱</label>
                                        <input class="form-control" type="email" id="email" name="email" placeholder="例如:123@123.com">
                                    </div>
                                    <div class="text-right">
                                        <button class="btn btn-primary" type="submit">提交</button>
                                        <button class="btn btn-danger" data-dismiss="modal">取消</button>
                                    </div>
                                </form>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>

    <footer class="footer bg-dark">
        <p align="center">2018 ©Cloud ++ | <a href="https://github.com/iamchriswu" style="color: white">GitHub</a></p>

    </footer>

</body>
</html>
