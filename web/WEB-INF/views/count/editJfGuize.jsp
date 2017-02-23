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
            <li><a href="javascript:void(0)">积分规则</a></li>
            <li><a href="javascript:void(0)">积分规则维护</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>积分规则维护</span>
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
                <h4 class="page-header">积分规则维护</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${adObj.lx_jifen_id}" id="lx_jifen_id">


                    <div class="form-group">
                        <label class="col-sm-2 control-label">推广下线零钱充值金额的百分比</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_jifen_one" value="${adObj.lx_jifen_one}" class="form-control"
                                   placeholder="推广下线零钱充值金额的百分比 数字 例如10就是10%" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">推广下线消费金额的百分比</label>

                        <div class="col-sm-4">
                            <input type="text" id="lx_jifen_two" value="${adObj.lx_jifen_two}" class="form-control"
                                   placeholder="推广下线消费金额的百分比 数字 例如10就是10%" data-toggle="tooltip" data-placement="bottom"
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
        var lx_jifen_id = $("#lx_jifen_id").val();
        var lx_jifen_one = $("#lx_jifen_one").val();
        var lx_jifen_two = $("#lx_jifen_two").val();

//        if (lx_jifen_one.replace(/\s/g, '') == '') {
//            alert("推广下线充值金额的百分比");
//            return;
//        }
//        if (lx_jifen_two.replace(/\s/g, '') == '') {
//            alert("推广下线消费额的百分比");
//            return;
//        }

        var regInt = /^([0-9]\d*)$/;
        if (lx_jifen_one.replace(/\s/g, '') == '') {
            alert("推广下线充值金额的百分比不能为空");
            return;
        } else {
            if (!regInt.test(lx_jifen_one)) {
                alert("推广下线充值金额的百分比必须是正整数");
                return;
            }
        }

        if (lx_jifen_two.replace(/\s/g, '') == '') {
            alert("推广下线消费额的百分比不能为空");
            return;
        } else {
            if (!regInt.test(lx_jifen_two)) {
                alert("推广下线消费额的百分比必须是正整数");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/editJifenGuize.do",
            data: {
                "lx_jifen_id": lx_jifen_id,
                "lx_jifen_one": lx_jifen_one,
                "lx_jifen_two": lx_jifen_two
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                } else {
                    alert("修改失败，请检查")
                }
            }
        });
    }
</script>
