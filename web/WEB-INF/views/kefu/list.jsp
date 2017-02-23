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
            <li><a href="javascript:void(0)">客服QQ</a></li>
            <li><a href="javascript:void(0)">客服电话</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>客服QQ</span>
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
                <table class="table table-hover">
                    <thead>
                    <tr>
                        <th>客服</th>
                        <th>昵称</th>
                        <th>头像</th>
                        <th>类型</th>
                        <th>操作</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${e.mm_tel}</td>
                            <td>${e.mm_name}</td>
                            <td><img src="${e.mm_cover}" style="width: 60px;height: 60px;"></td>
                            <td>
                                <c:if test="${e.mm_tel_type == '0'}">QQ</c:if>
                                <c:if test="${e.mm_tel_type == '1'}">电话</c:if>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editKefu('${e.mm_tel_id}')" role="button">编辑</a>
                            </td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="deleteKefu('${e.mm_tel_id}','${e.mm_tel}')" role="button">删除</a>
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

    function editKefu(_id) {
        if (confirm("确定要编辑该客服么？")) {
            $.ajax({
                type: "GET",
                data: {"typeId": _id},
                url: "/kefu/edit.do",
                success: function (response) {
                    $("#content").html(response);
                }
            });
        }
    }
    function deleteKefu(_id, _mm_tel) {
        if (confirm("确定要删除该客服么？")) {
            $.ajax({
                type: "GET",
                data: {"mm_tel_id": _id, "mm_tel": _mm_tel},
                url: "/kefu/delete.do",
                success: function (_data) {
                    var data = $.parseJSON(_data);
                    if (data.success) {
                        alert("删除成功");
                        window.location.href = "#module=/kefu/list" + "&_t=" + new Date().getTime();
                    } else {
                        var _case = {1: "删除失败"};
                        alert(_case[data.code])
                    }
                }
            });
        }
    }

</script>


