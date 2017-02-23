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
            <li><a href="javaScript:void(0)">结算管理</a></li>
            <li><a href="javaScript:void(0)">结算列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>结算列表</span>
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
                        <input type="text" id="search_date" value="${query.date}" class="form-control"
                               placeholder="查询日期">
                        <span class="fa fa-calendar txt-danger form-control-feedback"></span>
                    </div>
                    <button type="submit" onclick="clearSearch()" class="btn btn-default btn-sm">清空</button>
                    <button type="submit" onclick="searchSettlement('1')" class="btn btn-default btn-sm">查找</button>
                </form>
                <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>日期</th>
                        <th>结算费率</th>
                        <th>收入总计</th>
                        <th>是否结算</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${e.date}</td>
                            <td>${e.rate}</td>
                            <td>${e.income}</td>
                            <td>
                                <c:if test="${e.isAccount == 0}">未结算</c:if>
                                <c:if test="${e.isAccount != 0}">已结算</c:if>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/settlement/orders&date=${e.date}"
                                   role="button">订单列表</a>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        $('#search_date').datepicker({setDate: new Date()});
        WinMove();
    });
    function searchSettlement(_page) {
        var page = parseInt(_page);
        var date = $("#search_date").val();
        window.location.href = "#module=/settlement/list&page=" + page + "&date=" + date+ "&_t="+ new Date().getTime();
    }

    function clearSearch() {
        $("#search_date").val("");
    }
</script>


