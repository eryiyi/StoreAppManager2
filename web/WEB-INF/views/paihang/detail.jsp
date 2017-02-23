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
            <li><a href="javascript:void (0);">金牌榜</a></li>
            <li><a href="javascript:void (0);">上榜商户详情</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>推荐商品详情</span>
                </div>
            </div>
            <div class="box-content">
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${recordVO.mm_paihang_id}" id="mm_paihang_id">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名称</label>

                        <div class="col-lg-8">
                            <div id="mm_emp_nickname">${recordVO.goods_name}</div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否显示</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="is_del">
                                <option value="">--请选择--</option>
                                <option value="0" ${recordVO.is_del=='0'?'selected':''}>是</option>
                                <option value="1" ${recordVO.is_del=='1'?'selected':''}>否</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">置顶数字</label>

                        <div class="col-sm-4">
                            <input type="text" id="top_num" class="form-control" placeholder="置顶数字越大 越靠前"
                                   value="${recordVO.top_num}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">到期日期</label>

                        <div class="col-sm-2">
                            <input type="date" id="end_time" class="form-control" value="${recordVO.end_time}">
                        </div>
                    </div>

                    <%--展示位置--%>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">展示位置</label>
                        <%--0推荐首页 1首发新品 2特惠专区--%>
                        <div class="col-sm-4">
                            <select class="form-control" id="is_type">
                                <option value="">--选择--</option>
                                <option value="0" ${recordVO.is_type=='0'?'selected':''}>推荐首页</option>
                                <option value="1" ${recordVO.is_type=='1'?'selected':''}>首发新品</option>
                                <option value="2" ${recordVO.is_type=='2'?'selected':''}>特惠专区</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary"
                                    onclick="saveRole('${recordVO.mm_paihang_id}')">修改
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function saveRole(mm_emp_id) {
        var mm_paihang_id = $("#mm_paihang_id").val();
        var is_del = $("#is_del").val();
        var top_num = $("#top_num").val();
        var end_time = $("#end_time").val();
        var is_type = $("#is_type").val();
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
        if (is_type.replace(/\s/g, '') == '') {
            alert("请选择推荐商品显示位置！");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/paihang/update.do",
            data: {
                "mm_paihang_id": mm_paihang_id,
                "is_del": is_del,
                "end_time": end_time,
                "is_type": is_type,
                "top_num": top_num
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=paihang/list"+ "&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;
</script>


