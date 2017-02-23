<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>微微商城后台登陆</title>
    <meta name="description" content="description">
    <meta name="author" content="Evgeniya">
    <meta name="keyword" content="keywords">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <script src="/js/md5.js"></script>
    <script src="/js/cookie.js"></script>
    <script src="/js/jquery.min.js"></script>
    <script src="/js/Util.js"></script>
    <script src="/js/validation.js"></script>
    <script src="/js/china2.js"></script>
    <script src="/js/ajaxfileupload.js"></script>
    <script src="/js/jquery.js"></script>
    <script src="/js/jquery_latest.js"></script>

</head>
<body class="login-bg" onload="loginAuto()">
<div class="login">
    <div class="above">
        <img src="/img/logo.png" alt="微微商城后台登陆"/>
    </div>
    <div class="bottom">
        <div class="line">
            <span class="addon">姓名：</span>
            <input type="text" id="username" name="username"/>
        </div>
        <div class="line">
            <span class="addon">密码：</span>
            <input type="password" id="password" name="password"/>
        </div>
    </div>
    <div class="submit"><button class="" onclick="login();">登录</button></div>
</div>
<script type="text/javascript">
    function login() {
        var username = $("#username").val();
        var password = $("#password").val();
        if (username.replace(/\s/g, '') == '') {
            alert("用户名不能为空");
            return;
        }
        if (password.replace(/\s/g, '') == '') {
            alert("密码不能为空");
            return;
        }
        var passwordCode = hex_md5(password);
        $.ajax({
            cache: true,
            type: "POST",
            url: "/adminLogin.do",
            data: {"username": username, "password": passwordCode},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    addCookie("loginName_mrmb", username, 36);
                    addCookie("loginPassword_mrmb", password, 36);
                    window.location.href = "/main.do";
                } else {
                    var _case = {
                        1: "用户名不能为空",
                        2: "密码不能为空",
                        3: "该用户不存在",
                        4: "密码不正确",
                        5: "登陆失败",
                        6: "您的账号没有权限，请联系微微商城管理员"
                    };
                    alert(_case[data.code])
                }
            }
        });
    }
    $(function(){
       resizeHeight();
    });
    $(window).on('resize',function(){
        resizeHeight();
    })
    function resizeHeight(){
        var window_h = $(window).height();
        var login_h = $('.login').height();
        $('.login').css('margin-top',(window_h-login_h)/2-80);
    }

    function loginAuto() {
        var username = getCookie("loginName_mrmb");
        var password = getCookie("loginPassword_mrmb");
        if(username != 0 && username !='0'){
            document.getElementById("username").value = username
        }
        if(password != 0 && password !='0'){
            document.getElementById("password").value = password
        }
    }

</script>

<style>
    *{
        outline: none !important;
    }
    .login-bg {
        background-color: #eeeeee;
    }
    .login {
        -webkit-box-shadow: inset 1px 2px 5px #ffffff, 1px 2px 10px rgba(0, 0, 0, 0.1);
        -moz-box-shadow: inset 1px 2px 5px #ffffff, 1px 2px 10px rgba(0, 0, 0, 0.1);
        box-shadow: inset 1px 2px 5px #ffffff, 1px 2px 10px rgba(0, 0, 0, 0.1);
        margin-left: auto;
        margin-right: auto;
        font-size: 16px;
        width: 500px;
        border-radius: 10px;
        background-color: #fcfcfc;
        border: 1px solid #d5d5d5;
    }
    .login .above {
        border-bottom: 1px solid #e7eaec;
        padding: 20px 0;
    }
    .login .above img {
        width: 350px;
        display: block;
        margin: 0 auto;
    }
    .login .bottom {
        padding: 35px 50px 0 50px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
    }
    .login .bottom .line {
        margin: 0 auto 25px;
        display: table;
    }
    .login .bottom .line .addon {
        padding-right: 5px;
        line-height: 26px;
        display: table-cell;
    }
    .login .bottom .line input {
        -webkit-box-shadow: inset 1px 1px 3px rgba(0, 0, 0, 0.05);
        -moz-box-shadow: inset 1px 1px 3px rgba(0, 0, 0, 0.05);
        box-shadow: inset 1px 1px 3px rgba(0, 0, 0, 0.05);
        line-height: 26px;
        width: 320px;
        display: table-cell;
        border: 1px solid #d5d5d5;
        padding: 5px 8px;
        border-radius: 3px;
        -webkit-box-sizing: border-box;
        -moz-box-sizing: border-box;
        box-sizing: border-box;
        transition: all .3s linear;
    }
    .login .bottom .line input:focus {
        transition: all .3s linear;
        border-color: #2dc3e8;
    }
    .login .submit {
        padding-bottom: 30px;
    }
    .login .submit button {
        color: #ffffff;
        line-height: 35px;
        display: block;
        margin: 0 auto;
        padding: 0 50px;
        border-radius: 3px;
        background-color: #2dc3e8;
        border: 1px solid #19bbe3;
    }
    .login .submit button:hover {
        background-color: #19bbe3;
    }
    .login .submit button:active {
        transform: translateY(1px);
        background-color: #17afd5;
    }
</style>
</body>
</html>
