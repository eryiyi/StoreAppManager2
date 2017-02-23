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
            <li><a href="javaScript:void(0)">汇款结算</a></li>
            <li><a href="javaScript:void(0)">结算查询</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>商家结算列表</span>
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
                    <button type="submit" onclick="searchSettlementSellers('1')" class="btn btn-default btn-sm">查找
                    </button>
                </form>
                <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>商家名称</th>
                        <th>商家电话</th>
                        <th>收入总计</th>
                        <th>是否结算</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${e.empName}</td>
                            <td>${e.empMobile}</td>
                            <td>${e.totalMoney}</td>
                            <td>
                                <c:if test="${e.isAccount == 0}">未结算</c:if>
                                <c:if test="${e.isAccount != 0}">已结算</c:if>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm"
                                   href="#module=/settlement/orders&date=${query.date}&empId=${e.empId}" role="button">订单列表</a>
                                <a class="btn btn-default btn-sm"
                                   href="#module=/settlement/endOrder&date=${query.date}&empId=${e.empId}"
                                   role="button">去结算</a>
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
<script type="text/javascript">

    $(document).ready(function () {
        $('#search_date').datepicker({setDate: new Date()});
        WinMove();
    });
    function searchSettlementSellers(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var date = $("#search_date").val();
        window.location.href = "#module=/settlement/sellerlist&page=" + page + "&size=" + size + "&date=" + date+ "&_t="+ new Date().getTime();
    }

    function clearSearch() {
        $("#search_date").val("");
    }


    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var size = getCookie("contract_size");
        var date = $("#search_date").val();
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=ajax/listMember&page=" + _index + "&size=" + size + "&date=" + date+ "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var schoolId = $("#s2_country").val();
        var date = $("#search_date").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1) || schoolId != '') {
            window.location.href = "#module=ajax/listMember&page=" + _index + "&size=" + size + "&date=" + date+ "&_t="+ new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
</script>


