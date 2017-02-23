<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" pageEncoding="utf-8"%>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javaScript:void(0)">商品管理</a></li>
            <li><a href="javaScript:void(0)">商城产品列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>商城产品列表</span>
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
                <!-- style -->
                <style>
                    .w12 {
                        max-width: 12rem;
                    }
                </style>
                <!-- style -->
                <form class="form-inline">
                    <input type="hidden" id="is_zhiying" name="is_zhiying" value="${query.is_zhiying}">

                    <div class="form-group">
                        <div class="col-sm-4">
                            <input class="form-control" id="keywords" value="${query.cont}" type="text"
                                   placeholder="商品名称">
                        </div>
                    </div>

                    <div class="form-group">
                        <select class="form-control w12" id="isUse">
                            <option value="">-是否下架-</option>
                            <option value="0" ${query.isUse=='0'?'selected':''}>否</option>
                            <option value="1" ${query.isUse=='1'?'selected':''}>是</option>
                        </select>
                    </div>

                    <div class="form-group">
                        <select class="form-control w12" id="is_dxk">
                            <option value="">-是否定向卡商品-</option>
                            <option value="0" ${query.is_dxk=='0'?'selected':''}>否</option>
                            <option value="1" ${query.is_dxk=='1'?'selected':''}>是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control w12" id="goods_position">
                            <option value="">-是否商城排序商品-</option>
                            <option value="0" ${query.goods_position=='0'?'selected':''}>否</option>
                            <option value="1" ${query.goods_position=='1'?'selected':''}>是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <select class="form-control w12" id="dianpu_number">
                            <option value="">-店铺内排序查询-</option>
                            <option value="1" ${query.dianpu_number=='1'?'selected':''}>是</option>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" onclick="nextPage('1')"
                                class="btn form-control btn-warning btn-sm btn-block">搜索
                        </button>
                    </div>
                </form>

                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>商品名称</th>
                        <th>类别</th>
                        <th>销售价格</th>
                        <th>市场价格</th>
                        <th>PV利润</th>
                        <th>商品数量</th>
                        <th>已售数量</th>
                        <th>发布时间</th>
                        <th>是否下架</th>
                        <th>是否定向卡商品</th>
                        <th>是否推荐</th>
                        <th>是否排序</th>
                        <th>排序号</th>
                        <th>是否折扣</th>
                        <th>折扣</th>
                        <th>店铺内排序</th>

                        <th>操作</th>

                        <c:if test="${is_admin == '0'}"><th>操作</th></c:if>

                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td>${e.name}</td>

                            <td>
                                <c:if test="${e.is_zhiying == '0'}"> ${e.lx_class_name}</c:if>
                                <c:if test="${e.is_zhiying == '1'}"> ${e.type_name}</c:if>

                            </td>
                            <td>${e.sellPrice}</td>
                            <td>${e.marketPrice}</td>
                            <td>${e.pv_prices}</td>
                            <td>${e.count}</td>
                            <td>${e.goods_count_sale}</td>
                            <td>${um:format(e.upTime, 'yyyy-MM-dd')}
                            </td>
                            <td>
                                <c:if test="${e.isUse == '0'}">否</c:if>
                                <c:if test="${e.isUse == '1'}">是</c:if>
                            </td>
                            <td>
                                <c:if test="${e.is_dxk == '0'}">否</c:if>
                                <c:if test="${e.is_dxk == '1'}">是</c:if>
                            </td>
                            <td>
                                <c:if test="${e.is_tuijian == '0'}">否</c:if>
                                <c:if test="${e.is_tuijian == '1'}">是</c:if>
                            </td>
                            <td>
                                <c:if test="${e.goods_position == '0'}">否</c:if>
                                <c:if test="${e.goods_position == '1'}">是</c:if>
                            </td>
                            <td>${e.top_number}</td>
                            <td>
                                <c:if test="${e.is_zhekou == '0'}">否</c:if>
                                <c:if test="${e.is_zhekou == '1'}">是</c:if>
                            </td>
                            <td>${e.zhekou_number}</td>
                            <td>${e.dianpu_number}</td>

                            <td>
                                <a class="btn btn-default btn-sm" href="#module=/paopaogoods/edit&id=${e.id}"
                                   role="button">修改</a>
                                <button class="btn btn-primary" type="button"
                                        onclick="deletePaopaoGoods('${e.id}')">删除
                                </button>
                            </td>
                            <c:if test="${is_admin == '0'}">
                                <td>
                                    <a class="btn btn-default btn-sm" href="#module=/paopaogoods/tuijian&id=${e.id}"
                                       role="button">推荐</a>
                                </td>
                                <td>
                                    <a class="btn btn-default btn-sm" href="#module=/paopaogoods/paixu&id=${e.id}"
                                       role="button">排序</a>
                                </td>
                            </c:if>
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
        var keywords = $("#keywords").val();
        var isUse = $("#isUse").val();
        var is_dxk = $("#is_dxk").val();
        var is_zhiying = $("#is_zhiying").val();
        var goods_position = $("#goods_position").val();
        var dianpu_number = $("#dianpu_number").val();
        var size = getCookie("contract_size");
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/paopaogoods/list&page=" + _index
            + "&size=" + size
            + "&is_dxk=" + is_dxk
            + "&is_zhiying=" + is_zhiying
            + "&goods_position=" + goods_position
            + "&isUse=" + isUse
            + "&cont=" + keywords
            + "&dianpu_number=" + dianpu_number
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var keywords = $("#keywords").val();
        var isUse = $("#isUse").val();
        var is_dxk = $("#is_dxk").val();
        var size = $("#size").val();
        var is_zhiying = $("#is_zhiying").val();
        var goods_position = $("#goods_position").val();
        var dianpu_number = $("#dianpu_number").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1)) {
            window.location.href = "#module=/paopaogoods/list&page=" + page
            + "&size=" + size
            + "&is_dxk=" + is_dxk
            + "&is_zhiying=" + is_zhiying
            + "&goods_position=" + goods_position
            + "&isUse=" + isUse
            + "&cont=" + keywords
            + "&dianpu_number=" + dianpu_number
            + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function deletePaopaoGoods(_id) {
        if (!confirm("确定要删除该商品？")) {
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/paopaogoods/delete.do",
            data: {"id": _id},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("删除成功");
                    var _index = $("#index").val();
                    var size = getCookie("contract_size");
                    var keywords = $("#keywords").val();
                    var isUse = $("#isUse").val();
                    var is_zhiying = $("#is_zhiying").val();
                    var goods_position = $("#goods_position").val();
                    var is_dxk = $("#is_dxk").val();
                    var dianpu_number = $("#dianpu_number").val();
                    window.location.href = "#module=/paopaogoods/list&page=" + _index
                    + "&size=" + size
                    + "&is_dxk=" + is_dxk
                    + "&is_zhiying=" + is_zhiying
                    + "&goods_position=" + goods_position
                    + "&isUse=" + isUse
                    + "&dianpu_number=" + dianpu_number
                    + "&cont=" + keywords + "&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "删除失败"};
                    alert(_case[data.code])
                }
            }
        });
    }


</script>


