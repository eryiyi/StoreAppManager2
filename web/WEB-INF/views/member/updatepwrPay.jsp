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
            <li><a href="javascript:void (0);">会员管理</a></li>
            <li><a href="javascript:void (0);">修改支付密码</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改支付密码</span>
                </div>
            </div>
            <div class="box-content">
                <%--<h4 class="page-header">会员详情</h4>--%>
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${empVO.empId}" id="emp_id">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">账号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_number" readonly="true"  class="form-control" value="${empVO.emp_number}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_mobile" readonly="true" class="form-control"
                                   value="${empVO.empMobile}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">昵称</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_name" class="form-control" readonly="true" value="${empVO.empName}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">新的支付密码</label>

                        <div class="col-sm-4">
                            <input type="text"  class="form-control" id="newPwr"  name="newPwr"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">确认支付密码</label>

                        <div class="col-sm-4">
                            <input type="text"  class="form-control" id="surePwr"  name="surePwr"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveRole('${empVO.empId}')">确定修改
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

    function saveRole(mm_emp_id) {
        var emp_id = $("#emp_id").val();
        var newPwr = $("#newPwr").val();
        var surePwr = $("#surePwr").val();

        if (newPwr.replace(/\s/g, '') == '') {
            alert("请输入新密码");
            return;
        }
        if (surePwr.replace(/\s/g, '') == '') {
            alert("请输入确认密码");
            return;
        }

        if (surePwr == '') {
            alert("请再次输入新密码");
            return;
        }
        if (newPwr != surePwr) {
            alert("两次新密码输入不一致，请重新输入");
            $("#surePwr").val("");
            return;
        }

        newPwr = hex_md5(newPwr);

        $.ajax({
            cache: true,
            type: "POST",
            url: "/updatePwrPayById.do",
            data: {
                "empId": emp_id,
                "emp_pay_pass": newPwr
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    history.go(-1);
                } else {
                    var _case = {1: "修改失败,请确认用户ID是否存在！", 2: "修改失败,请确认用户新密码是否为空！"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>
