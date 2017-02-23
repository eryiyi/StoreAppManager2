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
            <li><a href="javascript:void (0);">申请提现</a></li>
            <li><a href="javascript:void (0);">申请提现</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>申请提现</span>
                </div>
            </div>
            <div class="box-content">
                <%--<h4 class="page-header">会员详情</h4>--%>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_name" readonly="true"  class="form-control" value="${countVo.emp_name}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">剩余通用积分</label>
                        <div class="col-sm-4">
                            <input type="text" id="jifenCount" readonly="true"  class="form-control" value="${countVo.count}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">银行卡</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="bank_id">
                                <option value="">--选择银行卡--</option>
                                <c:forEach items="${listBanks}" var="e" varStatus="st">
                                    <option value="${e.bank_id}">${e.bank_card}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">提现金额</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_bank_apply_count" class="form-control"  placeholder="请输入金额，1分=1元"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>

                    </div>
                    提示：1、手续费1%，提现金额必须是100的整数倍，否则不予受理。
                    2、提现时间：9:30-5:00，高峰期可能会有延迟。

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveRole()">确定申请
                            </button>
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

    function saveRole() {
        var jifenCount = $("#jifenCount").val();
        var bank_id = $("#bank_id").val();
        var lx_bank_apply_count = $("#lx_bank_apply_count").val();

        if (bank_id.replace(/\s/g, '') == '') {
            alert("银行卡号不能为空");
            return;
        }

        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
        if (lx_bank_apply_count.replace(/\s/g, '') == '') {
            alert("提现金额不能为空");
            return;
        } else {
            if (!reg.test(lx_bank_apply_count)) {
                alert("提现金额必须为100的整数倍！");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/lxBankApplyController/save.do",
            data: {
                "bank_id": bank_id,
                "lx_bank_apply_count": lx_bank_apply_count
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("申请成功");
                    history.go(-1);
                } else {
                    var _case = {1: "申请失败！",2:"用户ID不存在，请检查！",3:"银行卡不存在！", 4:"提现金额不能为空！",5:"用户积分不存在",6:"提现金额大于用户剩余积分",7:"提现金额必须是100的整数倍"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>

