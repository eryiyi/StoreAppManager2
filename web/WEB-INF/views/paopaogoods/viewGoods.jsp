<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<!DOCTYPE html>
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
    <title>${vo.name}</title>
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
            <a href="#" class="head-pic"><img src="${vo.empCover==''?'/img/6.gif':vo.empCover}"/></a>

            <div class="info">
                <span class="nick-name">${vo.nickName}</span>
                <span class="time">${vo.upTime}</span>
            </div>
            <a href="javascript:void(0)" onclick="window.contact.showDialog();"
               class="contact-button">咨询卖家</a>
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
            <span class="addr">已售${vo.goods_count_sale}</span>
        </div>
    </div>
    <!-- 商家信息 -->
    <%--<div class="seller-info">--%>
        <%--<h3 class="name clearfix"><img src="/img/lianxiren.png"><span>${vo.person}</span></h3>--%>
        <%--<p class="notice"></p>--%>
    <%--</div>--%>
    <div class="goods-detail">${vo.cont}</div>
    <div class="blank" style="height: 100px"><br></div>
</div>
<a href="javascript:void(0)">
    <img class="download-button" src="/img/share_download.png" alt="下载微微商城手机客户端">
</a>
</body>
</html>
