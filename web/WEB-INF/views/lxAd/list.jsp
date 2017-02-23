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
            <li><a href="javascript:void(0)">广告管理</a></li>
            <li><a href="javascript:void(0)">广告列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>广告列表</span>
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
                <div class="col-xs-3 col-sm-2">
                    <button type="button" onclick="addAd()" class="btn btn-default btn-sm">添加</button>
                </div>
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>广告图片</th>
                        <th>商品链接</th>
                        <th>广告类型</th>
                        <th>备注</th>
                        <th>排序</th>
                        <th>添加时间</th>
                        <th>操作</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <td><img src="${e.ad_pic}" width="60px" height="60px"></td>
                            <td>${e.goods_name}</td>
                            <td>
                                <c:if test="${e.ad_type=='1'}">推荐顶部轮播图</c:if>
                                <c:if test="${e.ad_type=='2'}">推荐中部广告（大）</c:if>
                                <c:if test="${e.ad_type=='3'}">推荐中部广告（小）</c:if>
                                <c:if test="${e.ad_type=='4'}">商城顶部轮播图</c:if>
                                <c:if test="${e.ad_type=='5'}">商城首发新品</c:if>
                                <c:if test="${e.ad_type=='6'}">商城特惠专区</c:if>
                                <c:if test="${e.ad_type=='7'}">定向卡商家广告</c:if>

                                <c:if test="${e.ad_type=='8'}">闪购商品</c:if>
                                <c:if test="${e.ad_type=='9'}">首页闪购下方的广告</c:if>
                                <c:if test="${e.ad_type=='10'}">主题活动（大）</c:if>
                                <c:if test="${e.ad_type=='11'}">主题活动（小）</c:if>
                                <c:if test="${e.ad_type=='12'}">特色市场（大）</c:if>
                                <c:if test="${e.ad_type=='13'}">特色市场（小）</c:if>
                                <c:if test="${e.ad_type=='14'}">附近中间三个广告</c:if>
                            </td>
                            <td>${e.ad_content}</td>
                            <td>${e.top_num}</td>
                            <td>${um:format(e.dateline, 'yyyy-MM-dd')}</td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editRole('${e.ad_id}')" role="button">编辑</a>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="deleteRole('${e.ad_id}')" role="button">删除</a>
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

    function editRole(_id) {
        if (confirm("确定要编辑该广告么？")) {
            window.location.href = "#module=/lxAdController/edit&ad_id=" + _id + "&_t=" + new Date().getTime();
        }
    }

    function deleteRole(_id) {
        if (confirm("确定要删除该广告么？")) {
            $.ajax({
                url: "/lxAdController/delete.do",
                data: {"ad_id": _id},
                type: "POST",
                success: function (_data) {
                    var data = $.parseJSON(_data);
                    if (data.success) {
                        alert("删除成功");
                        var _index = $("#index").val();
                        var size = getCookie("contract_size");
                        window.location.href = "#module=lxAdController/list&page=" + _index + "&size=" + size + "&_t=" + new Date().getTime();
                    } else {
                        var _case = {1: "删除失败"};
                        alert(_case[data.code])
                    }
                }
            });
        }
    }

    function addAd() {
        window.location.href = "#module=lxAdController/add";
    }

    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var size = getCookie("contract_size");
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=lxAdController/list&page=" + _index + "&size=" + size + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1) || schoolId != '') {
            window.location.href = "#module=lxAdController/list&page=" + page + "&size=" + size + "&_t=" + new Date().getTime();
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }


</script>


