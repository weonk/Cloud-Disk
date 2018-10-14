$(function () {
    var nameStatus = false;
    var passwordStatus = false;
    var confirmPasswordStatus = false;
    var emailStatus = false;

    $('#log-name').focus(function () {
        $('#log-error-info').fadeOut();
    });

    $('#log-pwd').focus(function () {
        $('#log-error-info').fadeOut();
    });

    $("#log-btn-submit").click(function () {
        if ($('#log-name').val().trim().length ===0 || $('#log-pwd').val().trim().length === 0) {
            $('#log-error-info').fadeIn();
        } else {
            $('#log-form').submit();
        }
    });

    $("#reg-name").change(function () {
        $("#name-error").hide();
        $("#name-right").hide();
        var text = $("#reg-name").val().trim();
        if (text.length >= 6 && text.length <= 15) {
            var reg = /^[a-zA-Z0-9_]+$/;
            if (!reg.test(text)) {
                nameStatus = false;
                $("#name-error").fadeIn();
            } else {
                $.post(contentPath + '/userNameCheck.action',
                    {
                        "userCustom.userName":text
                    }, function (data) {
                        if (Object.keys(data).length !== 0) {
                            nameStatus = false;
                            $("#name-error").fadeIn();
                        } else {
                            nameStatus = true;
                            $("#name-right").fadeIn();
                        }
                    });
            }
        } else {
            nameStatus = false;
            $("#name-error").fadeIn();
        }
    });

    $('#reg-name').focus(function () {
        $('#reg-error-info').fadeOut();
    });

    $("#reg-pwd").change(function () {
        $("#pwd-error").hide();
        $("#pwd-right").hide();
        $("#cpwd-error").hide();
        $("#cpwd-right").hide();
        var text1 = $("#reg-pwd").val().trim();
        var text2 = $("#reg-cPwd").val().trim();
        if (text1 !== text2) {
            confirmPasswordStatus = false;
            $("#cpwd-error").fadeIn();
        } else {
            confirmPasswordStatus = true;
            $("#cpwd-right").fadeIn();
        }
        if (text1.length >= 6) {
            passwordStatus = true;
            $("#pwd-right").fadeIn();
        } else {
            passwordStatus = false;
            $("#pwd-error").fadeIn();
        }
    });

    $('#reg-pwd').focus(function () {
        $('#reg-error-info').fadeOut();
    });

    $("#reg-cPwd").change(function () {
        $("#cpwd-error").hide();
        $("#cpwd-right").hide();
        var text1 = $("#reg-pwd").val().trim();
        var text2 = $("#reg-cPwd").val().trim();
        if (text1 !== text2) {
            confirmPasswordStatus = false;
            $("#cpwd-error").fadeIn();
        } else {
            confirmPasswordStatus = true;
            $("#cpwd-right").fadeIn();
        }
    });

    $('#reg-cPwd').focus(function () {
        $('#reg-error-info').fadeOut();
    });

    $("#reg-email").change(function () {
        $("#email-error").hide();
        $("#email-right").hide();
        var text = $("#reg-email").val().trim();
        var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(text)) {
            emailStatus = false;
            $("#email-error").fadeIn();
        } else {
            emailStatus = true;
            $("#email-right").fadeIn();
        }
    });

    $('#reg-email').focus(function () {
        $('#reg-error-info').fadeOut();
    });

    $("#reg-btn-submit").click(function () {
        if (nameStatus && passwordStatus && confirmPasswordStatus && emailStatus) {
            $("#reg-form").submit();
        } else {
            $('#reg-error-info').fadeIn();
        }
    });

});

//打开文件选择框
function selectFile(){
    $("#file").trigger("click");
}

function fileUpload(){
    $.ajax({
        url : contentPath + "/uploadFile.action",
        type : 'post',
        cache: false,
        data: new FormData($('#upLoadForm')[0]),
        processData: false,
        contentType: false,
        success : function(data) {
            if (data) {
                window.location.href=contentPath + '/cloudMain.action';
                alert("上传成功!");
            } else {
                window.location.href=contentPath + '/cloudMain.action';
                alert("上传失败!");
            }
        } ,
        error:function(){
            alert("页面请求失败！");
        }
    });
}

function deleteUserFile(userId, fileId) {
    $.post(contentPath + '/deleteUserFile.action',
        {
            'userFileCustom.userId': userId,
            'userFileCustom.fileId': fileId
        }, function (data) {
            if (data) {
                window.location.href=contentPath + '/cloudMain.action';
                alert("删除成功！");
            }
        });
}

$(function () {

    var nicknameStatus = true;

    $("#modify-email").change(function () {
        $('#modify-email-right').hide();
        $('#modify-email-error').hide();
        var text = $('#modify-email').val().trim();
        var reg = /^[a-zA-Z0-9_-]+@[a-zA-Z0-9_-]+(\.[a-zA-Z0-9_-]+)+$/;
        if (!reg.test(text)) {
            nicknameStatus = false;
            $('#modify-email-error').fadeIn();
        } else {
            nicknameStatus = true;
            $('#modify-email-right').fadeIn();
        }
    });
    $('#modify-email').focus(function () {
        $('#modify-info-error-info').hide();
    });

    $('#modify-info-btn').click(function () {
        if (nicknameStatus) {
            $('#modify-info-form').submit();
        } else {
            $('#modify-info-error-info').fadeIn();
        }
    });

    var modifyOldPwdStatus = false;
    var modifyNewPwdStatus = false;
    var modifyConPwdStatus = false;

    $('#modify-old-pwd').change(function () {
        $('#modify-old-pwd-right').hide();
        $('#modify-old-pwd-error').hide();
        var text = $('#modify-old-pwd').val().trim();
        if (text.length >= 6) {
            $.post(contentPath + '/passwordCheck.action',
                {
                    oldPassword: text
                }, function (data) {
                    if (data === "success") {
                        modifyOldPwdStatus = true;
                        $('#modify-old-pwd-right').fadeIn();
                    } else {
                        modifyOldPwdStatus = false;
                        $('#modify-old-pwd-error').fadeIn();
                    }
                })
        } else {
            modifyOldPwdStatus = false;
            $('#modify-old-pwd-error').fadeIn();
        }
    });
    $('#modify-old-pwd').focus(function () {
        $('#modify-pwd-error-info').fadeOut();
    });

    $('#modify-new-pwd').change(function () {
        $('#modify-new-pwd-right').hide();
        $('#modify-new-pwd-error').hide();
        $('#modify-con-pwd-right').hide();
        $('#modify-con-pwd-error').hide();
        var text1 = $('#modify-new-pwd').val().trim();
        var text2 = $('#modify-con-pwd').val().trim();
        if (text1 !== text2) {
            modifyConPwdStatus = false;
            $("#modify-con-pwd-error").fadeIn();
        } else {
            modifyConPwdStatus = true;
            $("#modify-con-pwd-right").fadeIn();
        }
        if (text1.length >= 6) {
            modifyNewPwdStatus = true;
            $("#modify-new-pwd-right").fadeIn();
        } else {
            modifyNewPwdStatus = false;
            $("#modify-new-pwd-error").fadeIn();
        }
    });
    $('#modify-new-pwd').focus(function () {
        $('#modify-pwd-error-info').fadeOut();
    });

    $('#modify-con-pwd').change(function () {
        $('#modify-con-pwd-right').hide();
        $('#modify-con-pwd-error').hide();
        var text1 = $('#modify-new-pwd').val().trim();
        var text2 = $('#modify-con-pwd').val().trim();
        if (text1 !== text2) {
            modifyConPwdStatus = false;
            $("#modify-con-pwd-error").fadeIn();
        } else {
            modifyConPwdStatus = true;
            $("#modify-con-pwd-right").fadeIn();
        }
    });
    $('#modify-con-pwd').focus(function () {
        $('#modify-pwd-error-info').fadeOut();
    });

    $('#modify-pwd-btn').click(function () {
        if (modifyOldPwdStatus && modifyNewPwdStatus && modifyConPwdStatus) {
            $('#modify-pwd-form').submit();
        } else {
            $('#modify-pwd-error-info').fadeIn();
        }
    });

});

function collectionFile(userId, fileId) {
    $.post(contentPath + '/collectionFile.action',
        {
            'userFileCustom.userId': userId,
            'userFileCustom.fileId': fileId
        }, function (data) {
            if (data) {
                alert("文件收藏成功！")
            } else {
                alert("文件已存在，请勿重复收藏！")
            }
        });
}