<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>订单详情</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/goods_detail.css">
    <link rel="stylesheet" href="/css/reset.css">
</head>
<body>
<div class="container">
        <div class="good-info">
            订单号：<h2 class="good-name">  ${vo.order_no}</h2>
            会员名：<h2 class="good-name">  ${vo.empName}</h2>
            订单金额：<h2 class="good-name">  ${vo.payable_amount}</h2>
        </div>
</div>
</body>
</html>
