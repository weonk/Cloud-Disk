<%--
  Created by IntelliJ IDEA.
  User: Chris
  Date: 2018/9/12
  Time: 21:04
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="contentPath" value="${pageContext.request.contextPath}" scope="application" />
<html>
<head>
    <title>搜索一下</title>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link rel="stylesheet" href="css/style.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/bootstrap/4.1.0/css/bootstrap.min.css">
    <link rel="stylesheet" href="https://cdn.bootcss.com/font-awesome/4.7.0/css/font-awesome.css">
    <script src="https://cdn.bootcss.com/jquery/3.2.1/jquery.min.js"></script>
    <script src="js/jquery.form.js"></script>
    <script src="https://cdn.bootcss.com/popper.js/1.12.5/umd/popper.min.js"></script>
    <script src="https://cdn.bootcss.com/bootstrap/4.1.0/js/bootstrap.min.js"></script>
    <script src="js/chris.cloud.js"></script>
</head>
<body>
    <nav class="navbar navbar-expand-sm bg-dark navbar-dark">
        <a class="navbar-brand" style="font-size: 30px" href="${pageContext.request.contextPath}/cloudMain.action">Cloud ++</a>
        <ul class="navbar-nav">
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/cloudMain.action">我的网盘</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="${pageContext.request.contextPath}/listAllFile.action">搜索一下</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" target="view_window" href="https://github.com/iamchriswu">关于我们</a>
            </li>
        </ul>
        <div class="ml-auto dropdown">
            <img class="dropdown-toggle" data-toggle="dropdown" src="images/user.png" style="width: 50px;height: 50px;margin-right: 20px;">
            <ul class="dropdown-menu">
                <li>
                    <c:if test="${user.nickname != null}">
                        <span style="margin-left: 25px;font-size: 20px;">${user.nickname}</span>
                    </c:if>
                    <c:if test="${user.nickname == null}">
                        <span style="margin-left: 25px;font-size: 20px;">${user.userName}</span>
                    </c:if>
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
                        <form id="modify-info-form" class="form-group" action="${pageContext.request.contextPath}/modifyUserInfo.action" method="post">
                            <div class="form-group">
                                <label for="modify-nick">昵称</label>
                                <div class="row">
                                    <div class="col-md-10">
                                        <input class="form-control" type="text" id="modify-nick" name="userCustom.nickname" value="${user.nickname}">
                                    </div>
                                    <div class="col-md-2">
                                        <i class="fa fa-check" style="color:green;margin-top: 8px;"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="modify-email">邮箱</label>
                                <div class="row">
                                    <div class="col-md-10">
                                        <input class="form-control" type="email" id="modify-email" name="userCustom.email" value="${user.email}" placeholder="例如:123@123.com">
                                    </div>
                                    <div class="col-md-2">
                                        <i id="modify-email-right" class="fa fa-check" style="color:green;margin-top: 8px;"></i><i id="modify-email-error" class="fa fa-close" style="color:red;margin-top: 8px;display: none;"></i>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="text-right">
                            <button class="btn btn-primary" id="modify-info-btn">确认修改</button>
                            <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </div>
                        <div id="modify-info-error-info" class="alert alert-danger alert-dismissable" style="margin-top: 10px;display: none;">
                            <button type="button" class="close" onclick="$('#modify-info-error-info').hide()">&times;</button>
                            <strong>请填写正确的修改信息</strong>
                        </div>
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
                        <form id="modify-pwd-form" class="form-group" action="${pageContext.request.contextPath}/modifyPassword.action" method="post">
                            <div class="form-group">
                                <label for="modify-old-pwd">原密码</label>
                                <div class="row">
                                    <div class="col-md-10">
                                        <input class="form-control" type="password" id="modify-old-pwd" name="oldPassword" placeholder="请输入原密码">
                                    </div>
                                    <div class="col-md-2">
                                        <i id="modify-old-pwd-right" class="fa fa-check" style="color:green;margin-top: 8px;display: none"></i><i id="modify-old-pwd-error" class="fa fa-close" style="color:red;margin-top: 8px;display: none;"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="modify-new-pwd">新密码</label>
                                <div class="row">
                                    <div class="col-md-10">
                                        <input class="form-control" type="password" id="modify-new-pwd" name="userCustom.password" placeholder="请输入新密码">
                                    </div>
                                    <div class="col-md-2">
                                        <i id="modify-new-pwd-right" class="fa fa-check" style="color:green;margin-top: 8px;display: none;"></i><i id="modify-new-pwd-error" class="fa fa-close" style="color:red;margin-top: 8px;display: none;"></i>
                                    </div>
                                </div>
                            </div>
                            <div class="form-group">
                                <label for="modify-con-pwd">新密码</label>
                                <div class="row">
                                    <div class="col-md-10">
                                        <input class="form-control" type="password" id="modify-con-pwd" name="newConfirmPassword" placeholder="请输入重复密码">
                                    </div>
                                    <div class="col-md-2">
                                        <i id="modify-con-pwd-right" class="fa fa-check" style="color:green;margin-top: 8px;display: none"></i><i id="modify-con-pwd-error" class="fa fa-close" style="color:red;margin-top: 8px;display: none;"></i>
                                    </div>
                                </div>
                            </div>
                        </form>
                        <div class="text-right">
                            <button class="btn btn-primary" id="modify-pwd-btn">确认修改</button>
                            <button class="btn btn-secondary" data-dismiss="modal">取消</button>
                        </div>
                        <div id="modify-pwd-error-info" class="alert alert-danger alert-dismissable" style="margin-top: 10px;display: none;">
                            <button type="button" class="close" onclick="$('#modify-pwd-error-info').hide()">&times;</button>
                            <strong>请填写正确的修改信息</strong>
                        </div>
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
                        <a href="${pageContext.request.contextPath}/logout.action" class="btn btn-warning">退出登录</a>
                        <button type="button" class="btn btn-secondary" data-dismiss="modal">关闭</button>
                    </div>
    
                </div>
            </div>
        </div>
    
    </nav>

    <div class="container">
        <form action="${pageContext.request.contextPath}/searchFile.action" style="margin-top: 50px" method="post">
            <div class="input-group mb-3">
                <input type="text" class="form-control" name="keyName" value="${keyName}" placeholder="请输入想要搜索的文件名">
                <div class="input-group-append">
                    <button class="btn btn-success" type="submit">Go</button>
                </div>
            </div>
        </form>
    </div>

    <div class="container">
        <c:choose>
            <c:when test="${fileList == null || fileList.size() == 0}">
                <img src="images/search.jpg">
            </c:when>
            <c:otherwise>
                <table class="table table-hover">
                    <thead class="thead-dark">
                    <tr>
                        <th colspan="2">文件名</th>
                        <th>大小</th>
                        <th>上传日期</th>
                        <th colspan="2">操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach var="file" items="${fileList}">
                        <tr class="table-light">
                            <td>
                                <c:choose>
                                    <c:when test="${file.fileType == 'txt'}">
                                        <i class="fa fa-file-text-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'zip' || file.fileType == 'rar' || file.fileType == '7z'}">
                                        <i class="fa fa-file-zip-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'mp3' || file.fileType == 'cda' || file.fileType == 'wav'}">
                                        <i class="fa fa-file-audio-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'mp4' || file.fileType == 'avi' || file.fileType == 'mov' || file.fileType == 'wmv' || file.fileType == '3gp' || file.fileType == 'mkv' || file.fileType == 'flv' || file.fileType == 'm4v'}">
                                        <i class="fa fa-file-video-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'jpg' || file.fileType == 'jpeg' || file.fileType == 'gif' || file.fileType == 'png' || file.fileType == 'bmp'}">
                                        <i class="fa fa-file-image-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'xls' || file.fileType == 'xlsx'}">
                                        <i class="fa fa-file-excel-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'ppt' || file.fileType == 'pptx'}">
                                        <i class="fa fa-file-powerpoint-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'doc' || file.fileType == 'docx'}">
                                        <i class="fa fa-file-word-o"></i>
                                    </c:when>
                                    <c:when test="${file.fileType == 'pdf'}">
                                        <i class="fa fa-file-pdf-o"></i>
                                    </c:when>
                                    <c:otherwise>
                                        <i class="fa fa-file-o"></i>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                            <td>${file.fileName.substring(file.fileName.indexOf('_') + 1)}</td>
                            <td>${file.formatSize}</td>
                            <td>${file.uploadTime.toLocaleString()}</td>
                            <td><a href="${pageContext.request.contextPath}/downloadFile.action?fileId=${file.id}"><img style="width: 20px; height: 20px;" src="images/download.png" alt="下载"></a></td>
                            <td><img style="width: 20px; height: 20px;" src="images/plus.png" alt="收藏" onclick="collectionFile(${user.id}, ${file.id})"></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </c:otherwise>
        </c:choose>
    </div>

    <footer class="footer bg-dark">
        <p align="center">2018 ©Cloud ++ | <a href="https://github.com/iamchriswu" style="color: white;" target="view_window">GitHub</a></p>
    </footer>
</body>
<script>
    var contentPath = '${contentPath}';
</script>
</html>
