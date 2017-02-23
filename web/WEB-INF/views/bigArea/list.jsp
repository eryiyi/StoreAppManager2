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
            <li><a href="javascript:void(0)">分区管理</a></li>
            <li><a href="javascript:void(0)">分区列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>分区列表</span>
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
                <%--<p>For basic styling add the base class <code>.table</code> to any <code>&lt;table&gt;</code>.</p>--%>
                <%--<div class="col-xs-3 col-sm-2">--%>
                    <%--<button type="button" onclick="addAd()" class="btn btn-default btn-sm">添加</button>--%>
                <%--</div>--%>
                <table class="table">
                    <thead>
                    <tr>
                        <th>分区图片</th>
                        <th>分区简介</th>
                        <th>分区欢迎语</th>
                        <th>地址</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td><img src="${e.area_pic}" style="width:60px;height: 60px;"></td>
                            <td>${e.area_title}</td>
                            <td>${e.area_content}</td>
                            <td>${e.area_url}</td>
                            <td>
                                <a class="btn btn-default btn-sm" href="javascript:void (0)"
                                   onclick="editRole('${e.area_id}')" role="button">编辑</a>
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

    function editRole(_id) {
        if (confirm("确定要编辑该分区么？")) {
            $.ajax({
                type: "GET",
                data: {"area_id": _id},
                url: "/bigAreaObjController/toEdit.do",
                success: function (response) {
                    $("#content").html(response);
                }
            });
        }
    }

    function addAd() {
        window.location.href = "#module=adObj/add";
    }
</script>


