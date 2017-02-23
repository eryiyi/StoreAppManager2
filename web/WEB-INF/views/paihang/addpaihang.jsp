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
            <li><a href="javascript:void (0);">商品推荐管理</a></li>
            <li><a href="javascript:void (0);">添加推荐商品</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>添加推荐商品</span>
                </div>
            </div>
            <div class="box-content">
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${good.id}" id="goods_id">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_name" readonly="true" value="${good.name}"
                                   placeholder="商品名" class="form-control" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">置顶数字</label>

                        <div class="col-sm-4">
                            <input type="text" id="top_num" placeholder="置顶数字" value="0" class="form-control"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否显示</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="is_del">
                                <option value="">--选择是否显示--</option>
                                <option value="0" selected="selected">是</option>
                                <option value="1">否</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">到期日期</label>

                        <div class="col-sm-2">
                            <input type="date" id="end_time" class="form-control">
                        </div>
                    </div>

                    <%--展示位置--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">展示位置</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="is_type">--%>
                                <%--<option value="">--选择--</option>--%>
                                <%--<option value="0" >推荐首页</option>--%>
                                <%--<option value="1" >首发新品</option>--%>
                                <%--<option value="2" >特惠专区</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>

                    <%--</div>--%>
                    <h5 class="page-header">展示位置</h5>
                    <%--<c:forEach items="${schools}" var="e" varStatus="st">--%>
                        <div class="form-group">
                            <div class="col-sm-1"><input type="checkbox" name="school" value="0"></div>
                            <label class="col-sm-2 control-label">推荐首页</label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-1"><input type="checkbox" name="school" value="1"></div>
                            <label class="col-sm-2 control-label">首发新品</label>
                        </div>
                        <div class="form-group">
                            <div class="col-sm-1"><input type="checkbox" name="school" value="2"></div>
                            <label class="col-sm-2 control-label">特惠专区</label>
                        </div>
                    <%--</c:forEach>--%>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="addPaihang()">确定</button>
                            <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

    function addPaihang() {
        var goods_id = $("#goods_id").val();
        var is_del = $("#is_del").val();
        var top_num = $("#top_num").val();
        var end_time = $("#end_time").val();
//        var is_type = $("#is_type").val();
        if (top_num.replace(/\s/g, '') == '') {
            alert("请填写置顶数字，最小为0，填写整数！");
            return;
        }
        if (is_del.replace(/\s/g, '') == '') {
            alert("请选择是否显示！");
            return;
        }
        if (end_time.replace(/\s/g, '') == '') {
            alert("请选择到期日期！");
            return;
        }
//        if (is_type.replace(/\s/g, '') == '') {
//            alert("请选择推荐商品显示位置！");
//            return;
//        }

        var school_ary = new Array();
        $('input[name="school"]:checked').each(function () {
            school_ary.push($(this).val());//向数组中添加元素

        });
        var schools = school_ary.join('|');//将数组元素连接起来以构建一个字符串
        if (schools == null || schools == '') {
            alert("请选择位置");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/paihang/add.do",
            data: {
                "goods_id": goods_id,
                "is_del": is_del,
                "top_num": top_num,
                "is_type": schools,
                "end_time": end_time
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("添加成功");
                    window.location.href = "#module=/paihang/list&page=1"+ "&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "添加失败", 2: "添加失败，该商品已经存在推荐首页"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>


