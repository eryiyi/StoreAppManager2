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
            <li><a href="javascript:void(0)">推荐首页管理</a></li>
            <li><a href="javascript:void(0)">推荐商品列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>推荐商品列表</span>
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

                    <div class="form-group">
                        <select class="form-control w12" id="is_del">
                            <option value="">--是否显示--</option>
                            <option value="0" ${query.is_del=='0'?'selected':''}>是</option>
                            <option value="1" ${query.is_del=='1'?'selected':''}>否</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-4">
                            <input class="form-control" id="keywords" value="${query.keyword}" type="text"
                                   placeholder="商品名称">
                        </div>
                    </div>
                    <div class="form-group">
                        <select class="form-control w12" id="is_type">
                            <option value="">--显示位置--</option>
                            <option value="0" ${query.is_del=='0'?'selected':''}>推荐首页</option>
                            <option value="1" ${query.is_del=='1'?'selected':''}>首发新品</option>
                            <option value="2" ${query.is_del=='2'?'selected':''}>特惠专区</option>
                        </select>
                    </div>
                    <button type="submit" onclick="searchOrder('1')"
                            class="btn form-control btn-warning btn-sm btn-block">查找
                    </button>
                </form>


                <table class="table table-hover">
                    <thead>
                    <tr>
                        <%--<th>全选<input type="checkbox" name="allmails" onclick="checkAll()"></th>--%>
                            <th>#</th>
                        <th>商品名</th>
                        <th>销售价格</th>
                        <th>市场价格</th>
                        <th>联系人</th>
                        <th>电话</th>
                        <th>上架时间</th>
                        <th>商品数量</th>
                        <th>已卖商品数量</th>
                        <th>商家/自营</th>
                        <th>是否显示</th>
                        <th>置顶数字</th>
                        <th>结束时间</th>
                        <th>展示位置</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <%--<td><input type="checkbox" id="${e.mm_paihang_id}" name="checkbox_one"></td>--%>
                                <td>${st.index + 1}</td>
                            <td>${e.goods_name}</td>
                            <td>${e.sell_price}</td>
                            <td>${e.market_price}</td>
                            <td>${e.goods_person}</td>
                            <td>${e.goods_tel}</td>
                            <td>${um:format(e.up_time, 'yyyy-MM-dd')}</td>
                            <td>${e.goods_count}</td>
                            <td>${e.goods_count_sale}</td>
                            <td>
                                <c:if test="${e.is_zhiying=='0'}">商家</c:if>
                                <c:if test="${e.is_zhiying=='1'}">平台自营</c:if>
                            </td>
                            <td>
                                <c:if test="${e.is_del=='0'}">是</c:if>
                                <c:if test="${e.is_del=='1'}">否</c:if>
                            </td>
                            <td>${e.top_num}</td>
                            <td>${e.end_time}</td>
                                <td>
                                    <c:if test="${e.is_type=='0'}">推荐首页</c:if>
                                    <c:if test="${e.is_type=='1'}">首发新品</c:if>
                                    <c:if test="${e.is_type=='2'}">特惠专区</c:if>
                                </td>
                            <td>
                                <a class="btn btn-default btn-sm"
                                   href="#module=/paihang/toDetail&mm_paihang_id=${e.mm_paihang_id}"
                                   role="button">管理</a>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="deleteRole('${e.mm_paihang_id}')" role="button">删除</a>
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
    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var size = getCookie("contract_size");
        var is_del = $("#is_del").val();
        var keywords = $("#keywords").val();
        var is_type = $("#is_type").val();
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/paihang/list&page=1"
            + "&size=" + size
            + "&is_del=" + is_del
            + "&is_type=" + is_type
            + "&keyword=" + keywords
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var is_del = $("#is_del").val();
        var keywords = $("#keywords").val();
        var is_type = $("#is_type").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/paihang/list&page=" + page
            + "&size=" + size
            + "&is_del=" + is_del
            + "&is_type=" + is_type
            + "&keyword=" + keywords
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function searchOrder(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var is_del = $("#is_del").val();
        var keywords = $("#keywords").val();
        var is_type = $("#is_type").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/paihang/list&page=" + page
            + "&size=" + size
            + "&is_del=" + is_del
            + "&is_type=" + is_type
            + "&keyword=" + keywords
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
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
            if (confirm("确定要导出所选择的信息吗？")) {
                $.ajax({
                    url: "/paihang/daochuAll.do",
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


    function deleteRole(_id) {
        if (confirm("确定要删除该条信息么？")) {
            $.ajax({
                url: "/paihang/delete.do",
                data: {"mm_paihang_id": _id},
                type: "POST",
                success: function (_data) {
                    var data = $.parseJSON(_data);
                    if (data.success) {
                        alert("删除成功");
                        var size = $("#size").val();
                        var is_del = $("#is_del").val();
                        var keywords = $("#keywords").val();
                        var is_type = $("#is_type").val();
                        window.location.href = "#module=/paihang/list&page=1"
                        + "&size=" + size
                        + "&is_del=" + is_del
                        + "&is_type=" + is_type
                        + "&keyword=" + keywords
                        + "&_t=" + new Date().getTime();
                    } else {
                        var _case = {1: "删除失败"};
                        alert(_case[data.code])
                    }
                }
            });
        }
    }



</script>


