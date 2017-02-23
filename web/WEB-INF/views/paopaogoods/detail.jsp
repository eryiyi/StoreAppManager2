<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>商品详情页</title>
    <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
    <meta name="viewport"
          content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
    <link rel="stylesheet" href="/css/goods_detail.css">
    <link rel="stylesheet" href="/css/reset.css">
</head>
<body>
<div class="container">
    <!-- 出售信息 -->
    <div class="good-info">
        <h2 class="good-name">${vo.name}</h2>

        <div class="person clearfix">
            <a href="#" class="head-pic"><img src="${vo.empCover}"/></a>

            <div class="info">
                <span class="nick-name">${vo.nickName}</span>
                <span class="time">${vo.upTime}</span>
            </div>
            <a href="protocol://lianxi" onclick="window.contact.showDialog();" class="contact-button">咨询卖家</a>
        </div>
        <!-- 状态：价格，剩余 -->
        <div class="status clearfix">
            <span class="price">￥<span class="big">${vo.sellPrice}</span></span>
            （￥
            <del class="discount">${vo.marketPrice}</del>
            ）
            <span class="left">剩余：${vo.count}</span>
        </div>
        <div class="address">
            <span class="addr">${vo.schoolName}</span>
        </div>
    </div>
    <!-- 商家信息 -->
    <div class="seller-info">
        <%--<h3 class="name clearfix"><img src="/img/lianxiren.png"><span>${vo.person}</span></h3>--%>

        <%--<h3 class="address clearfix"><img src="/img/dizhi.png"><span>${vo.address}</span></h3>--%>

        <%--<p class="notice">友情提示：本商城为您提供第三方保障，请勿线下交易，否则您的消费权益将无法保障！<br>--%>
            <%--欢迎各位小主对本店服务提出宝贵的建议与意见，您的满意就是对我们最大的支持！</p>--%>
    </div>
    <div class="goods-detail">${vo.cont}</div>
    <c:forEach items="${listPics}" var="eee" varStatus="st">
        <img src="${eee}">
    </c:forEach>
    <%--<img src="http://7xt74j.com1.z0.glb.clouddn.com/97da00883bee48f19d476fe2e5ff3d97">--%>
    <div class="blank" style="height: 100px"><br></div>
</div>
</body>
</html>
