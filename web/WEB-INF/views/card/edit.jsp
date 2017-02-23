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
            <li><a href="javascript:void(0)">定向卡维护</a></li>
            <li><a href="javascript:void(0)">定向卡维护</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>定向卡维护</span>
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
                <h4 class="page-header">定向卡维护</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${adObj.lx_card_id}" id="lx_card_id">


                    <div class="form-group">
                        <label class="col-sm-2 control-label">初始金额</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_card_count" value="${adObj.lx_card_count}" class="form-control"
                                   placeholder="初始金额" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">递减</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_card_dijian" value="${adObj.lx_card_dijian}" class="form-control"
                                   placeholder="递减" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">最低值</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_card_low" value="${adObj.lx_card_low}" class="form-control"
                                   placeholder="最低值" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveP()">更新</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function saveP() {
        var lx_card_id = $("#lx_card_id").val();
        var lx_card_count = $("#lx_card_count").val();
        var lx_card_dijian = $("#lx_card_dijian").val();
        var lx_card_low = $("#lx_card_low").val();

        var regInt = /^([0-9]\d*)$/;
        if (lx_card_count.replace(/\s/g, '') == '') {
            alert("请输入初始金额");
            return;
        } else {
            if (!regInt.test(lx_card_count)) {
                alert("初始金额必须是正整数");
                return;
            }
        }

        if (lx_card_dijian.replace(/\s/g, '') == '') {
            alert("请输入递减金额");
            return;
        } else {
            if (!regInt.test(lx_card_dijian)) {
                alert("递减金额必须是正整数");
                return;
            }
        }

        if (lx_card_low.replace(/\s/g, '') == '') {
            alert("请输入最低值");
            return;
        } else {
            if (!regInt.test(lx_card_low)) {
                alert("请最低值必须是正整数");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/lxCardObjController/edit.do",
            data: {
                "lx_card_id": lx_card_id,
                "lx_card_count": lx_card_count,
                "lx_card_dijian": lx_card_dijian,
                "lx_card_low": lx_card_low
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=lxCardObjController/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert("执行失败，请检查")
                }
            }
        });
    }
</script>
