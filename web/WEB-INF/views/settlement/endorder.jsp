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
            <li><a href="javaScript:void(0)">结算管理</a></li>
            <li><a href="javaScript:void(0)">结算信息</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>结算信息</span>
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
                <dl class="dl-horizontal">
                    <dt>结算费率</dt>
                    <dd>${settlement.rate}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家总收入</dt>
                    <dd>${settlement.income}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家应结算</dt>
                    <dd>${income}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家真实姓名</dt>
                    <dd>${info.realName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家身份证号</dt>
                    <dd>${info.idcard}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家支付宝账号</dt>
                    <dd>${info.payNumber}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家支付宝校验姓名</dt>
                    <dd>${info.checkName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家银行卡</dt>
                    <dd>${info.bankCard}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家银行卡所属银行</dt>
                    <dd>${info.bankType}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家银行卡开户行地址</dt>
                    <dd>${info.bankAddress}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家银行卡开户人姓名</dt>
                    <dd>${info.bankName}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>商家联系电话</dt>
                    <dd>${info.mobile}</dd>
                </dl>
            </div>

            <div class="form-group">
                <div class="col-sm-2 col-sm-offset-2">
                    <button type="button" class="btn btn-primary"
                            onclick="settlement('${query.date}','${query.empId}');">
                        <span><i class="fa fa-clock-o"></i></span>
                        确认结算
                    </button>
                </div>
            </div>
        </div>
    </div>
</div>
<script type="text/javascript">

    $(document).ready(function () {
        $('#search_date').datepicker({setDate: new Date()});
        WinMove();
    });
    function searchSettlement(_page) {
        var page = parseInt(_page);
        var date = $("#search_date").val();
        window.location.href = "#module=/settlement/list&page=" + page + "&date=" + date+ "&_t="+ new Date().getTime();
    }

    function clearSearch() {
        $("#search_date").val("");
    }

    function settlement(_date, _empId) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/settlement/settlementEnd.do",
            data: {"date": _date, "empId": _empId},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("结算成功");
                } else {
                    alert("结算失败");
                }
            }
        });
    }
</script>