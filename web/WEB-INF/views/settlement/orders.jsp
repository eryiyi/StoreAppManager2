<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javaScript:void(0)">营业收入</a></li>
            <li><a href="javaScript:void(0)">结算管理</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>订单列表</span>
                </div>
                <div class="box-icons">
                    <a class="collapse-link">
                        <i class="fa fa-chevron-up"></i>
                    </a>
                    <a class="expand-link">
                        <i class="fa fa-expand"></i>
                    </a>
                    <a class="close-link">
                        <i class="fa fa-times"></i>
                    </a>
                </div>
                <div class="no-move"></div>
            </div>
            <div class="box-content">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>买家名称</th>
                        <th>买家电话</th>
                        <th>商品名称</th>
                        <th>购买数量</th>
                        <th>商品总金额</th>
                        <th>下单时间</th>
                        <%--<th>付款时间</th>--%>
                        <%--<th>发货时间</th>--%>
                        <th>订单状态</th>
                        <th>付款状态</th>
                        <th>配送状态</th>
                        <th>用户留言</th>
                        <th>结算状态</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${e.empName}</td>
                            <td>${e.phone}</td>
                            <td>${e.goodsName}</td>
                            <td>${e.goods_count}</td>
                            <td>${e.payable_amount}</td>
                            <td>
                                <c:if test="${e.create_time!=null && e.create_time!=''}">${um:format(e.create_time, "yyyy-MM-dd HH:mm:ss")}</c:if>
                            </td>
                                <%--<td>--%>
                                <%--<c:if test="${e.pay_time!=null && e.pay_time!=''}">${um:format(e.pay_time, "yyyy-MM-dd HH:mm:ss")}</c:if>--%>
                                <%--</td>--%>
                                <%--<td>--%>
                                <%--<c:if test="${e.send_time!=null && e.send_time!=''}">${um:format(e.send_time, "yyyy-MM-dd HH:mm:ss")}</c:if>--%>
                                <%--</td>--%>
                            <td>
                                <c:if test="${e.status=='1'}">订单生成</c:if>
                                <c:if test="${e.status=='2'}">订单支付</c:if>
                                <c:if test="${e.status=='3'}">订单取消</c:if>
                                <c:if test="${e.status=='4'}">订单作废</c:if>
                                <c:if test="${e.status=='5'}">订单完成</c:if>
                            </td>
                            <td>
                                <c:if test="${e.pay_status=='0'}">未支付</c:if>
                                <c:if test="${e.pay_status=='1'}">已支付</c:if>
                                <c:if test="${e.pay_status=='2'}">退款</c:if>
                            </td>
                            <td>
                                <c:if test="${e.distribution_status=='0'}">未发货</c:if>
                                <c:if test="${e.distribution_status=='1'}">已发货</c:if>
                                <c:if test="${e.distribution_status=='2'}">已签收</c:if>
                            </td>
                            <td>${e.postscript}</td>
                            <td>
                                <c:if test="${e.isAccount=='0'}">未结算</c:if>
                                <c:if test="${e.isAccount=='1'}">已结算</c:if>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/order/detail&id=${e.order_no}"
                                   role="button">订单详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>



