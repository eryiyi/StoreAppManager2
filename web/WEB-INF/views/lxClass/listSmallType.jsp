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
            <li><a href="javascript:void (0);">商城分类</a></li>
            <li><a href="javascript:void (0);">${type.lx_class_name}分类列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>${type.lx_class_name}分类列表</span>
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
                    <button type="button" onclick="addTypeSmall('${type.lx_class_id}')" class="btn btn-default btn-sm">添加小分类</button>
                </form>
                <input type="hidden" id="lx_class_id" name="lx_class_id" value="${type.lx_class_id}">
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <%--<th>商城分类图片</th>--%>
                        <th>商城分类名称</th>
                        <th>商城分类介绍</th>
                        <th>排序/倒序</th>
                        <th>是否禁用</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index + 1}</td>
                            <%--<td><img class="img-rounded" width="50" height="50" src="${e.typeCover}"></td>--%>
                            <td>${e.lx_class_name}</td>
                            <td>${e.lx_class_content}</td>
                            <td>${e.top_number}</td>
                            <td>
                                <c:if test="${e.is_del == '0'}">未禁用</c:if>
                                <c:if test="${e.is_del == '1'}">已禁用</c:if>
                            </td>
                            <td>
                                <button class="btn btn-info" type="button" onclick="toUpdate('${e.lx_class_id}')">修改</button>
                                <button class="btn btn-info" type="button" onclick="deleteById('${e.lx_class_id}')">删除</button>
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
    function toUpdate(_id) {
        $.ajax({
            type: "GET",
            data: {"lx_class_id": _id},
            url: "/toUpdateType.do",
            success: function (response) {
                $("#content").html(response);
            }
        });
    }

    //添加小分类
    function addTypeSmall(_id) {
        $.ajax({
            type: "GET",
            data: {"f_lx_class_id": _id},
            url: "/toAddGoodsSmallLxClass.do",
            success: function (response) {
                $("#content").html(response);
            }
        });
    }




    function deleteById(_id) {
        var typeId = $("#lx_class_id").val();
        if (!confirm("确定删除该分类吗？")) {
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/deleteClassId.do",
            data: {"id": _id},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("删除分类成功");
                    window.location.href = "#module=/toListSmallLxClass&f_lx_class_id="+typeId+"&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "删除分类失败"};
                    alert(_case[data.code])
                }
            }
        });
    }


</script>


