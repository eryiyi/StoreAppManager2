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
            <li><a href="javascript:void(0)">定向卡充值</a></li>
            <li><a href="javascript:void(0)">定向卡充值</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>定向卡充值</span>
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
                <h4 class="page-header">定向卡充值</h4>
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="emp_id" name="emp_id" value="${memberVO.empId}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户昵称</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_name" value="${memberVO.empName}" class="form-control"
                                   placeholder="用户昵称" data-toggle="tooltip" data-placement="bottom" readonly="true"
                                   title="Tooltip for name">
                        </div>
                    </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-4">
                        <input type="text" id="emp_mobile" value="${memberVO.empMobile}" class="form-control"
                               placeholder="手机号" data-toggle="tooltip" data-placement="bottom" readonly="true"
                               title="Tooltip for name">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">充值金额</label>
                    <div class="col-sm-4">
                        <input type="text" id="lx_consumption_count" class="form-control" readonly="true" value="${money}"
                               placeholder="充值金额" data-toggle="tooltip" data-placement="bottom"
                               title="Tooltip for name">
                    </div>
                </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">过期时间</label>

                        <div class="col-sm-2">
                            <input type="text" id="input_date" class="form-control" placeholder="Date">
                            <span class="fa fa-calendar txt-danger form-control-feedback"></span>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" id="btn_chongzhi" name = "btn_chongzhi" class="btn btn-primary" onclick="chongzhi()">确定
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
    $(document).ready(function () {
        // Initialize datepicker
        $('#input_date').datepicker({setDate: new Date()});
        WinMove();
    });

    var kaiguan=1;

    function chongzhi(){
        if(kaiguan == 1){
            //充值
            var emp_id = $("#emp_id").val();
            var lx_consumption_count = $("#lx_consumption_count").val();

            var regInt = /^([0-9]\d*)$/;
            if (lx_consumption_count.replace(/\s/g, '') == '') {
                alert("充值金额不能为空");
                return;
            }

            var endTime = $("#input_date").val();
            if (endTime == "") {
                alert("请选择过期时间");
                return;
            }
            kaiguan = 0;
            $.ajax({
                cache: true,
                type: "POST",
                url: "/lxConsumptionController/chongzhiDxk.do",
                data: {
                    "emp_id": emp_id,
                    "lx_consumption_type": '3',
                    "lx_card_emp_end_time": endTime,
                    "lx_consumption_count": lx_consumption_count
                },
                async: false,
                success: function (_data) {
                    var data = $.parseJSON(_data);
                    if (data.success) {
                        alert("充值成功");
                        history.go(-1);
                    } else {
                        var _case = {1: "充值失败"};
                        alert(_case[data.code])
                    }
                }
            });
        }

    }

</script>

