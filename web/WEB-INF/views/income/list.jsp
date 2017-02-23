<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javaScript:void(0)">主页</a></li>
            <li><a href="#">营业收入</a></li>
            <li><a href="#">收入查询</a></li>
        </ol>
        <div id="social" class="pull-right">
            <a href="#"><i class="fa fa-google-plus"></i></a>
            <a href="#"><i class="fa fa-facebook"></i></a>
            <a href="#"><i class="fa fa-twitter"></i></a>
            <a href="#"><i class="fa fa-linkedin"></i></a>
            <a href="#"><i class="fa fa-youtube"></i></a>
        </div>
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
                <form class="form-inline">
                    <div class="col-sm-2">
                        <input type="text" id="start_time" value="${query.startTime}" class="form-control"
                               placeholder="开始时间">
                        <span class="fa fa-calendar txt-danger form-control-feedback"></span>
                    </div>
                    <div class="col-sm-2">
                        <input type="text" id="end_time" value="${query.endTime}" class="form-control"
                               placeholder="结束时间">
                        <span class="fa fa-calendar txt-danger form-control-feedback"></span>
                    </div>

                    <button type="submit" onclick="clearData()" class="btn btn-default btn-sm">清空</button>
                    <button type="submit" onclick="searchIncome('1')" class="btn btn-default btn-sm">查找</button>
                </form>
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
                        <th>卖家名称</th>
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
                            <td>${e.sellerName}</td>
                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/order/detail&id=${e.order_no}"
                                   role="button">订单详情</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div style="margin-top: 20px;border-top: 1px solid #dedede;padding-bottom:15px; height: 50px">
                    <span style="line-height:28px;margin-top:25px;padding-left:10px; float: left">共${page.count}条/${page.pageCount}页</span>
                    <ul class="pagination" style="padding-left:100px; float: right">
                        <li>
                            <a style="margin-right:20px">每页显示&nbsp;<select name="size" id="size"
                                                                           onchange="nextPage('1')">
                                <option value="10" ${query.size==10?'selected':''}>10</option>
                                <option value="20" ${query.size==20?'selected':''}>20</option>
                                <option value="30" ${query.size==30?'selected':''}>30</option>
                                <option value="100" ${query.size==100?'selected':''}>100</option>
                                <option value="1000" ${query.size==1000?'selected':''}>1000</option>
                            </select>&nbsp;条</a>
                        </li>
                        <c:choose>
                            <c:when test="${page.page == 1}">
                                <li><a href="javascript:void(0)">首页</a></li>
                                <li><a href="javascript:void(0)"><span class="left">《</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('1')">首页</a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page-1}')"><span
                                        class="left">《</span></a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a style="height: 30px; width: 100px">第<input style="width: 40px;height:20px;" type="text"
                                                                          id="index" name="index"
                                                                          onkeyup="searchIndex(event)"
                                                                          value="${page.page}"/> 页</a></li>

                        <c:choose>
                            <c:when test="${page.page == page.pageCount}">
                                <li><a href="javascript:void(0)"><span class="right">》</span></a></li>
                                <li><a href="javascript:void(0)">末页</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page+1}')"><span
                                        class="right">》</span></a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.pageCount}')">末页</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>当前收入</span>
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
                <c:if test="${income != null || income !=''}"> ${income}</c:if>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">
    $(document).ready(function () {
        // Initialize datepicker
        $('#start_time').datepicker({setDate: new Date()});
        $('#end_time').datepicker({setDate: new Date()});
        WinMove();
    });

    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var size = getCookie("contract_size");
        var startTime = $("#start_time").val();
        var endTime = $("#end_time").val();
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/income/list&page=" + page + "&size=" + size + "&startTime=" + startTime + "&endTime=" + endTime+ "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var startTime = $("#start_time").val();
        var endTime = $("#end_time").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/income/list&page=" + page + "&size=" + size + "&startTime=" + startTime + "&endTime=" + endTime+ "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function searchIncome(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var startTime = $("#start_time").val();
        var endTime = $("#end_time").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/income/list&page=" + page + "&size=" + size + "&startTime=" + startTime + "&endTime=" + endTime+ "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function clearData() {
        $("#start_time").val("");
        $("#end_time").val("");
    }
</script>


