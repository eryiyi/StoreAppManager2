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
            <li><a href="javascript:void(0)">定向卡会员管理</a></li>
            <li><a href="javascript:void(0)">定向卡会员到期时间修改</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>定向卡会员到期时间修改</span>
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
                <h4 class="page-header">定向卡会员到期时间修改</h4>
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="emp_id" name="emp_id" value="${cardEmp.emp_id}">
                    <input type="hidden" id="lx_card_emp_year" name="lx_card_emp_year" value="${cardEmp.lx_card_emp_year}">
                    <input type="hidden" id="is_use" name="is_use" value="${cardEmp.is_use}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户昵称</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_name" value="${cardEmp.emp_name}" class="form-control"
                                   placeholder="用户昵称" data-toggle="tooltip" data-placement="bottom" readonly="true"
                                   title="Tooltip for name">
                        </div>
                    </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-4">
                        <input type="text" id="emp_mobile" value="${cardEmp.emp_mobile}" class="form-control"
                               placeholder="手机号" data-toggle="tooltip" data-placement="bottom" readonly="true"
                               title="Tooltip for name">
                    </div>
                </div>

                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">过期日期</label>

                        <div class="col-sm-2">
                            <input type="text" id="input_date" class="form-control" placeholder="Date" value="${cardEmp.lx_card_emp_end_time}">
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
            var lx_card_emp_year = $("#lx_card_emp_year").val();
            var is_use = $("#is_use").val();

            var endTime = $("#input_date").val();
            if (endTime == "") {
                alert("请选择过期时间");
                return;
            }
            kaiguan = 0;
            $.ajax({
                cache: true,
                type: "POST",
                url: "/cardEmpController/updateCardEmp.do",
                data: {
                    "emp_id": emp_id,
                    "lx_card_emp_end_time": endTime,
                    "lx_card_emp_year": lx_card_emp_year,
                    "is_use": is_use
                },
                async: false,
                success: function (_data) {
                    var data = $.parseJSON(_data);
                    if (data.success) {
                        alert("修改成功");
                        history.go(-1);
                    } else {
                        var _case = {1: "修改失败"};
                        alert(_case[data.code])
                    }
                }
            });
        }

    }

</script>

