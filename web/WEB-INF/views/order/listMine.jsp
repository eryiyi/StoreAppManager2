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
            <li><a href="javaScript:void(0)">订单管理</a></li>
            <li><a href="javaScript:void(0)">订单列表</a></li>
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
                <form class="form-inline">
                    <input type="hidden" id="is_zhiying" name="is_zhiying" value="${query.is_zhiying}">
                    <div class="form-group">
                        <input type="text" class="form-control" id="emp_name" value="${query.empName}"
                               placeholder="买家名称">
                    </div>
                    <div class="form-group">
                        <input type="tel" class="form-control" id="emp_phone" value="${query.empPhone}"
                               placeholder="买家电话">
                    </div>


                    <div class="form-group">
                        <select class="form-control" id="order_status">
                            <option value="">--选择订单状态--</option>
                            <option value="1" ${query.orderStatus=='1'?'selected':''}>订单生成</option>
                            <option value="2" ${query.orderStatus=='2'?'selected':''}>订单支付</option>
                            <option value="3" ${query.orderStatus=='3'?'selected':''}>订单取消</option>
                            <option value="4" ${query.orderStatus=='4'?'selected':''}>订单作废</option>
                            <option value="5" ${query.orderStatus=='5'?'selected':''}>订单完成</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="pay_status">
                            <option value="">--选择付款状态--</option>
                            <option value="0" ${query.payStatus=='0'?'selected':''}>未支付</option>
                            <option value="1" ${query.payStatus=='1'?'selected':''}>已支付</option>
                            <option value="2" ${query.payStatus=='2'?'selected':''}>退款</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="distribution_status">
                            <option value="">--选择配送状态--</option>
                            <option value="0" ${query.distribStatus=='0'?'selected':''}>未发货</option>
                            <option value="1" ${query.distribStatus=='1'?'selected':''}>已发货</option>
                            <option value="2" ${query.distribStatus=='2'?'selected':''}>已签收</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="is_dxk_order">
                            <option value="">--选择是否0元订单--</option>
                            <option value="0" ${query.is_dxk_order=='0'?'selected':''}>否</option>
                            <option value="1" ${query.is_dxk_order=='1'?'selected':''}>是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control" id="trade_type">
                            <option value="">--选择支付方式--</option>
                            <option value="0" ${query.trade_type=='0'?'selected':''}>支付宝</option>
                            <option value="1" ${query.trade_type=='1'?'selected':''}>微信</option>
                            <option value="2" ${query.trade_type=='2'?'selected':''}>零钱</option>
                        </select>
                    </div>
                    <button type="submit" onclick="searchOrder('1')" class="btn btn-default btn-sm">查找</button>
                </form>
                <form action="" class="form">
                    <div class="form-group">
                        <div class="col-md-2 col-lg-2">
                            <button type="button" onclick="Daochu_Select()"
                                    class="btn w12 form-control btn-block btn-danger btn-sm">批量导出Excel
                            </button>
                        </div>
                    </div>
                </form>
                <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="allmails" onclick="checkAll()"></th>
                        <th>#</th>
                        <th>支付平台订单号</th>
                        <th>支付方式</th>
                        <th>买家账号</th>
                        <th>买家名称</th>
                        <th>买家电话</th>
                        <th>收货人</th>
                        <th>收货人电话</th>
                        <th>商品名称</th>
                        <th>购买数量</th>
                        <th>商品总金额</th>
                        <th>下单时间</th>
                        <%--<th>付款时间</th>--%>
                        <%--<th>发货时间</th>--%>
                        <th>订单状态</th>
                        <th>付款状态</th>
                        <th>配送状态</th>
                        <%--<th>用户留言</th>--%>
                        <th>卖家名称</th>
                        <th>是否0元订单</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td><input type="checkbox" id="${e.order_no}" name="checkbox_one"></td>
                            <td>${st.index + 1}</td>
                            <td>${e.out_trade_no}</td>
                            <td>
                                <c:if test="${e.trade_type=='0'}">支付宝</c:if>
                                <c:if test="${e.trade_type=='1'}">微信</c:if>
                                <c:if test="${e.trade_type=='2'}">零钱</c:if>
                            </td>
                            <td>${e.emp_number}</td>
                            <td>${e.empName}</td>
                            <td>${e.emp_mobile}</td>
                            <td>${e.accept_name}</td>
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
                                <%--<td>${e.postscript}</td>--%>
                            <td>${e.sellerName}</td>
                            <td>
                                <c:if test="${e.is_dxk_order=='0'}">否</c:if>
                                <c:if test="${e.is_dxk_order=='1'}">是</c:if>
                            </td>
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
<script type="text/javascript">
    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var size = getCookie("contract_size");
        var empName = $("#emp_name").val();
        var empPhone = $("#emp_phone").val();
        var orderStatus = $("#order_status").val();
        var payStatus = $("#pay_status").val();
        var is_zhiying = $("#is_zhiying").val();
        var distributionStatus = $("#distribution_status").val();
        var trade_type = $("#trade_type").val();
        var is_dxk_order = $("#is_dxk_order").val();
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/order/listMine&page=" + page + "&size=" + size
            + "&empName=" + empName
            + "&is_zhiying=" + is_zhiying
            + "&empPhone=" + empPhone
            + "&orderStatus=" + orderStatus
            + "&payStatus=" + payStatus
            + "&distribStatus=" + distributionStatus
            + "&trade_type=" + trade_type
            +"&is_dxk_order=" + is_dxk_order
            + "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var empName = $("#emp_name").val();
        var empPhone = $("#emp_phone").val();
        var orderStatus = $("#order_status").val();
        var payStatus = $("#pay_status").val();
        var is_zhiying = $("#is_zhiying").val();
        var distributionStatus = $("#distribution_status").val();
        var trade_type = $("#trade_type").val();
        var is_dxk_order = $("#is_dxk_order").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/order/listMine&page=" + page
            + "&size=" + size
            + "&empName=" + empName
            + "&is_zhiying=" + is_zhiying
            + "&empPhone=" + empPhone
            + "&orderStatus=" + orderStatus
            + "&payStatus=" + payStatus
            + "&distribStatus=" + distributionStatus
            + "&trade_type=" + trade_type
            +"&is_dxk_order=" + is_dxk_order
            + "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function searchOrder(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var empName = $("#emp_name").val();
        var empPhone = $("#emp_phone").val();
        var orderStatus = $("#order_status").val();
        var payStatus = $("#pay_status").val();
        var is_zhiying = $("#is_zhiying").val();
        var distributionStatus = $("#distribution_status").val();
        var is_dxk_order = $("#is_dxk_order").val();
        var trade_type = $("#trade_type").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/order/listMine&page=" + page
            + "&size=" + size
            + "&empName=" + empName
            + "&is_zhiying=" + is_zhiying
            + "&empPhone=" + empPhone
            + "&orderStatus=" + orderStatus
            + "&payStatus=" + payStatus
            + "&distribStatus=" + distributionStatus
            + "&trade_type=" + trade_type
            + "&is_dxk_order=" + is_dxk_order
            + "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function Daochu_Select() {
        var select_id = '';
        var select = document.getElementsByName("checkbox_one");
        for (var i = 0; i < select.length; i++) {
            if (select[i].checked == true) {
                select_id = select_id + select[i].id + ',';
            }
        }
        if (select_id == '') {
            alert('请选择数据！');
            return
        } else {
            if (confirm("确定要导出所选择的数据吗？")) {
                $.ajax({
                    url: "/OrdersExportExcel.do",
                    data: {"ids": select_id},
                    type: "POST",
                    success: function (_data) {
                        var data = $.parseJSON(_data);
                        if (data.success) {
                            window.location.href = "/upload" + data.data;//这样就可以弹出下载对话框了
                        } else {
                            var _case = {1: "导出失败"};
                            alert(_case[data.code])
                        }
                    }
                });
            }
        }
    }

    function checkAll() {
        var all = document.getElementsByName("allmails")[0];
        var select = document.getElementsByName("checkbox_one");
        if (all.checked) {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = true;
            }
        } else {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = false;
            }
        }
    }


</script>


