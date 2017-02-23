<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html >
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
    <title>分享标题</title>
</head>

<body>

<div class="h">
    <img class="h1" src="${goods.empCover}"/>

    <div class="h2">${goods.nickName}</div>
</div>
<div class="m">
    <span class="m1">${goods.money}<i>元</i></span>
    <a href="JavaScript:" class="m2">联系卖家</a>
</div>

<div class="m3">${goods.content}
    <c:forEach items="${pics}" var="e">
        <img src="${e}"/>
        <a href="http://www.liangxunwang.com/paopao/index.html" class="conta1">
            <img src="upload/20150504/1430681974978.jpg">
        </a>
    </c:forEach>
</div>
<style>
    * {
        margin: 0px;
        padding: 0px;
        font-family: "微软雅黑"
    }

    .h {
        width: 90%;
        padding: 10px 5%;
        float: left;
        border-bottom: 1px solid #ececec
    }

    .h1 {
        width: 50px;
        height: 50px;
        border-radius: 30px;
        float: left
    }

    .h2 {
        width: 70%;
        float: left;
        margin-left: 10px;
        font-size: 18px;
        height: 50px;
        line-height: 50px;
    }

    .m {
        width: 90%;
        padding: 0px 5% 0px 5%;
        border-bottom: 1px solid #ececec;
        height: 40px;
        line-height: 40px;
        float: left;
    }

    .m3 img {
        max-width: 100%;
        max-height: 500px;
        display: block;
        margin: 10px 0px
    }

    .m3 {
        font-size: 14px;
        color: #242424;
        line-height: 20px;
        width: 90%;
        padding: 10px 5%;
        float: left
    }

    .m1 {
        font-size: 14px;
        font-weight: bold;
        float: left
    }

    .m1 i {
        font-style: normal;
        color: #bc0000
    }

    .m1 img {
        max-width: 100%;
        max-height: 500px;
        display: block;
        margin: 5px 0px
    }

    .m2 {
        float: right;
        display: inline-block;
        width: 80px;
        height: 30px;
        line-height: 30px;
        background: #bc0000;
        margin-top: 5px;
        font-size: 12px;
        color: #fff;
        text-decoration: none;
        text-align: center
    }
</style>
</body>
</html>
