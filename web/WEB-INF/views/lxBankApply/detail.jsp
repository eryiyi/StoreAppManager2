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
            <li><a href="javascript:void (0);">提现申请</a></li>
            <li><a href="javascript:void (0);">提现申请</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>提现申请</span>
                </div>
            </div>
            <div class="box-content">
                <%--<h4 class="page-header">会员详情</h4>--%>
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${lxBankApplyVo.lx_bank_apply_id}" id="lx_bank_apply_id">
                    <input type="hidden" value="${lxBankApplyVo.emp_id}" id="emp_id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员账号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_number" readonly="true"  class="form-control" value="${lxBankApplyVo.emp_number}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员手机号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_mobile" readonly="true" class="form-control"
                                   value="${lxBankApplyVo.emp_mobile}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员昵称</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_name" class="form-control" value="${lxBankApplyVo.emp_name}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">提现用户</label>

                        <div class="col-sm-4">
                            <input type="text" id="bank_emp_name" class="form-control" value="${lxBankApplyVo.bank_emp_name}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">提现手机</label>

                        <div class="col-sm-4">
                            <input type="text" id="bank_mobile" class="form-control" value="${lxBankApplyVo.bank_mobile}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">开户行</label>

                        <div class="col-sm-4">
                            <input type="text" id="bank_kaihu_name" class="form-control" value="${lxBankApplyVo.bank_kaihu_name}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">银行</label>

                        <div class="col-sm-4">
                            <input type="text" id="bank_name" class="form-control" value="${lxBankApplyVo.bank_name}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">银行卡</label>

                        <div class="col-sm-4">
                            <input type="text" id="bank_card" class="form-control" value="${lxBankApplyVo.bank_card}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">提现金额</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_bank_apply_count" class="form-control" value="${lxBankApplyVo.lx_bank_apply_count}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">申请时间</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline_apply" class="form-control" value="${um:format(lxBankApplyVo.dateline_apply, 'yyyy-MM-dd HH:mm:ss')}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">处理时间</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline_done" class="form-control" value="${um:format(lxBankApplyVo.dateline_done, 'yyyy-MM-dd HH:mm:ss')}"  readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否处理</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="is_check">
                                <option value="0" ${lxBankApplyVo.is_check=='0'?'selected':''}>未处理</option>
                                <option value="1" ${lxBankApplyVo.is_check=='1'?'selected':''}>通过</option>
                                <option value="2" ${lxBankApplyVo.is_check=='2'?'selected':''}>不通过</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveRole('${lxBankApplyVo.lx_bank_apply_id}')">确定
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

    function saveRole(lx_bank_apply_id) {
        var is_check = $("#is_check").val();

        $.ajax({
            cache: true,
            type: "POST",
            url: "/lxBankApplyController/edit.do",
            data: {
                "lx_bank_apply_id": lx_bank_apply_id,
                "is_check": is_check
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("处理成功");
                    history.go(-1);
                } else {
                    var _case = {1: "处理失败", 2: "已经处理过了，不能重复处理！"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>

