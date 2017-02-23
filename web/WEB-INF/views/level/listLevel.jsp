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
            <li><a href="#">等级管理</a></li>
            <li><a href="#">等级列表</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>等级列表</span>
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
                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>等级名称</th>
                        <th>起始金额</th>
                        <th>结束金额</th>
                        <th>折扣</th>
                        <th>操作</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index+1}</td>
                            <td>${e.levelName}</td>
                            <td>${e.levelStart}</td>
                            <td>${e.levelEnd}</td>
                            <td>${e.level_zhe}折</td>
                            <td>
                                <button class="btn btn-primary" type="button" onclick="deleteLevel('${e.levelId}')">修改
                                </button>
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
    function deleteLevel(_id) {
        if (!confirm("确定要修改该等级么？")) {
            return;
        }
        window.location.href = "#module=/toEditLevel&levelId="+_id+ "&_t="+ new Date().getTime();
//        $.ajax({
//            type: "post",
//            data: {"levelId": _id},
//            url: "/deleteLevel.do",
//            success: function (_data) {
//                var data = $.parseJSON(_data);
//                if (data.success) {
//                    alert("删除成功");
//                    window.location.href = "#module=ajax/listLevel" + "&_t=" + new Date().getTime();
////          $.ajax({
////            type: "GET",
////            url: "/listLevel.do",
////            success: function(response){
////              $("#content").html(response);
////            }
////
////          });
////          history.pushState('', 'New URL: '+"/ajax/listLevel.do", "/#/ajax/listLevel.do");
//                } else {
//                    var _case = {1: "删除失败"};
//                    alert(_case[data.code])
//                }
//            }
//        });
    }
</script>

