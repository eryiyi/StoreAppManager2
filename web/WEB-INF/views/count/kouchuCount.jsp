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
            <li><a href="javascript:void (0);">积分管理</a></li>
            <li><a href="javascript:void (0);">扣除积分</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>扣除积分</span>
                </div>
            </div>
            <div class="box-content">
                <form class="form-horizontal" role="form">
                    <input type="hidden" id="emp_id" name="emp_id" value="${countVo.empId}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_name" readonly="true"  class="form-control" value="${countVo.emp_name}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员手机</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_mobile" readonly="true"  class="form-control" value="${countVo.emp_mobile}"
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
                        <label class="col-sm-2 control-label">扣除额度</label>

                        <div class="col-sm-4">
                            <input type="text" id="countNumber" class="form-control"  placeholder="请输入扣除额度"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveRole()">确定扣除
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
        var emp_id = $("#emp_id").val();
        var jifenCount = $("#jifenCount").val();
        var countNumber = $("#countNumber").val();

        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
        if (countNumber.replace(/\s/g, '') == '') {
            alert("扣除积分不能为空");
            return;
        } else {
            if (!reg.test(countNumber)) {
                alert("扣除积分必须是正整数！");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/kouchuCount.do",
            data: {
                "emp_id": emp_id,
                "jifenCount": jifenCount,
                "countNumber": countNumber
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("扣除积分成功");
                    history.go(-1);
                } else {
                    var _case = {1: "扣除积分失败！",2:"用户ID不存在，请检查用户！",3:"会员剩余积分为空，请检查！",4:"请输入要扣除多少积分！",5:"扣除的额度不能大于用户剩余积分！"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>

