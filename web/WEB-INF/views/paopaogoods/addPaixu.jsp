<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>--%>
<%--<link rel="stylesheet" href="/ueditor/themes/default/css/ueditor.css" type="text/css">--%>
<script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = location.protocol + '//' + document.domain + (location.port ? (":" + location.port) : "") + "/ueditor/";
</script>
<style>
    form {
        margin: 0;
    }

    textarea {
        display: block;
    }
</style>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javaScript:void(0)">商品管理</a></li>
            <li><a href="javaScript:void(0)">排序管理</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>排序管理</span>
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
                <h4 class="page-header">商品详情</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">
                    <input type="hidden" id="goods_id" value="${goods.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_title" class="form-control" value="${goods.name}" readonly="true"
                                   placeholder="商品名字" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">置顶数字</label>

                        <div class="col-sm-4">
                            <input type="text" id="top_number" value="${goods.top_number}" class="form-control"
                                   placeholder="置顶数字 数字越大越靠前" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否置顶</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="goods_position">
                                <option value="">--选择--</option>
                                <option value="0" ${goods.goods_position=='0'?'selected':''}>否</option>
                                <option value="1" ${goods.goods_position=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="savePaopaoGoods();">
                                <span><i class="fa fa-clock-o"></i></span>
                                提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function savePaopaoGoods() {
        var goods_id = $("#goods_id").val();
        var top_number = $("#top_number").val();
        var goods_position = $("#goods_position").val();

        var regInt = /^([0-9]\d*)$/;
        if (top_number.replace(/\s/g, '') == '') {
            alert("排序数字不能为空！");
            return;
        } else {
            if (!regInt.test(top_number)) {
                alert("排序数字必须是整数！");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/paopaogoods/updatePosition.do",
            data: {
                "id": goods_id,
                "top_number": top_number,
                "goods_position": goods_position
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("操作成功");
                    history.go(-1);
                } else {
                    var _case = {1: "操作失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

</script>


