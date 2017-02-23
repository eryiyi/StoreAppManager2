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
            <li><a href="javascript:void(0)">店铺管理</a></li>
            <li><a href="javascript:void(0)">店铺列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>店铺列表</span>
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

                    <div class="form-group">

                        <div class="col-sm-6">
                            <select class="form-control w12" id="is_card">
                                <option value="">-是否定向卡商家-</option>
                                <option value="0" ${query.is_card=='0'?'selected':''}>否</option>
                                <option value="1" ${query.is_card=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <button type="submit" onclick="nextPage('1')"
                                class="btn form-control btn-warning btn-sm btn-block">搜索
                        </button>

                    </div>
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

                <table class="table">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="allmails" onclick="checkAll()"></th>
                        <th>#</th>
                        <th>店铺名称</th>
                        <th>营业执照</th>
                        <th>身份证</th>
                        <th>会员</th>
                        <th>会员电话</th>
                        <th>店铺分类</th>
                        <th>联系人</th>
                        <th>联系电话</th>
                        <th>定向卡商家</th>
                        <th>是否审核</th>
                        <th>操作</th>
                        <th>操作</th>
                        <th>操作</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td><input type="checkbox" id="${e.id}" name="checkbox_one"></td>
                            <td>${st.index + 1}</td>
                            <td>${e.company_name}</td>

                            <td><a href="${e.company_yyzz}" target="_blank"><img src="${e.company_yyzz}" style="height: 60px;width: 100px;"></a></td>
                            <td><a href="${e.idcardUrl}" target="_blank"><img src="${e.idcardUrl}"  style="height: 60px;width: 100px;"></a></td>

                            <td>${e.emp_name}</td>
                            <td>${e.emp_mobile}</td>
                            <td>${e.lx_class_name}</td>
                            <td>${e.company_person}</td>
                            <td>${e.company_tel}</td>
                            <td>
                                <c:if test="${e.is_card == '0'}">否</c:if>
                                <c:if test="${e.is_card == '1'}">是</c:if>
                            </td>
                            <td>
                                <c:if test="${e.is_check == '0'}">否</c:if>
                                <c:if test="${e.is_check == '1'}">是</c:if>
                                <c:if test="${e.is_check == '2'}">不通过</c:if>
                            </td>

                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editRole('${e.id}')" role="button">审核</a>
                            </td>

                            <td>
                                <a class="btn btn-default btn-sm"
                                   href="#module=/paihangDianpuController/toTuijian&id=${e.emp_id}"
                                   role="button">推荐</a>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="deleteById('${e.id}')" role="button">删除</a>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editById('${e.id}')" role="button">编辑</a>
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
            $.ajax({
                type: "GET",
                data: {"id": _id},
                url: "/managerinfo/toDetail.do",
                success: function (response) {
                    $("#content").html(response);
                }
            });
    }


    function deleteById(_id) {
        if (!confirm("确定要删除该店铺么？")) {
            return;
        }
        $.ajax({
            type: "post",
            data: {"info_id": _id},
            url: "/managerinfo/deleteById.do",
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("删除成功");
                    var _index = $("#index").val();
                    var size = $("#size").val();
                    var is_card = $("#is_card").val();
                    window.location.href = "#module=managerinfo/list&page=" +_index + "&size=" + size + "&_t=" + new Date().getTime()+"&is_card="+is_card;
                } else {
                    var _case = {1: "删除失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

    function editById(_id) {
        if (!confirm("确定要编辑该店铺么？")) {
            return;
        }
        window.location.href = "#module=managerinfo/toCardDetail&id=" +_id + "&_t=" + new Date().getTime();
    }

    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var is_card = $("#is_card").val();
        var size = getCookie("contract_size");
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=managerinfo/list&page=" + _index + "&size=" + size+ "&_t="+ new Date().getTime()+"&is_card="+is_card;
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }

    function nextPage(_page) {
        var page = parseInt(_page);
        var size = $("#size").val();
        var is_card = $("#is_card").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1) || schoolId != '') {
            window.location.href = "#module=managerinfo/list&page=" + page + "&size=" + size+ "&_t="+ new Date().getTime()+"&is_card="+is_card;
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
                    url: "/dianpuExportExcel.do",
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


