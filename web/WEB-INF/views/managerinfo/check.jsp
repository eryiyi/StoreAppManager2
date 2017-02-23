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
            <li><a href="javascript:void (0);">店铺管理</a></li>
            <li><a href="javascript:void (0);">店铺审核</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>店铺审核</span>
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
                <h4 class="page-header">信息详情</h4>

                <form class="form-horizontal" role="form">
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">真实姓名</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="real_name" value="${info.realName}" class="form-control"--%>
                                   <%--placeholder="真实姓名" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">身份证号</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="idcard" value="${info.idcard}" class="form-control"--%>
                                   <%--placeholder="身份证号" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">身份证正面图</label>--%>

                        <%--<div class="col-sm-10">--%>
                            <%--<input type="file" name="file" id="fileUpload" style="float: left;"/>--%>
                            <%--<input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>--%>

                            <%--<div id="imageDiv" style="padding: 10px">--%>
                                <%--<script type="text/javascript">--%>
                                    <%--var imagePath = '${info.idcardUrl}';--%>
                                    <%--if (imagePath != null && imagePath != "") {--%>
                                        <%--var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';--%>
                                        <%--$("#imageDiv").html(html);--%>
                                    <%--}</script>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group" >--%>
                    <%--<label class="col-sm-2 control-label">支付宝账号</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="pay_number" value="${info.payNumber}" class="form-control" placeholder="支付宝账号" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">支付宝校验姓名</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="check_name"value="${info.checkName}" class="form-control" placeholder="支付宝校验姓名" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div><div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">银行卡号</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="bank_card" value="${info.bankCard}" class="form-control" placeholder="银行卡号" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div><div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">开户行</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="bank_type" value="${info.bankType}" class="form-control" placeholder="如:建设银行" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div><div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">开户行地址</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="bank_address" value="${info.bankAddress}" class="form-control" placeholder="开户行的详细地址" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">开户人姓名</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="bank_name" value="${info.bankName}" class="form-control" placeholder="银行卡持有者姓名" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                    <%--<label class="col-sm-2 control-label">联系电话</label>--%>
                    <%--<div class="col-sm-4">--%>
                    <%--<input type="text" id="mobile" value="${info.mobile}" class="form-control" placeholder="联系电话" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                    <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺名称*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_name" placeholder="店铺名称" class="form-control" readonly="true"
                                   value="${info.company_name}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">*店铺联系人*</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="company_person" placeholder="店铺联系人" class="form-control"--%>
                                   <%--value="${info.company_person}" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">*店铺电话*</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="company_tel" placeholder="店铺电话" class="form-control"--%>
                                   <%--value="${info.company_tel}" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">*店铺地址*</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="company_address" placeholder="店铺地址" class="form-control"--%>
                                   <%--value="${info.company_address}" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">*店铺介绍*</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="company_detail" placeholder="店铺介绍" class="form-control"--%>
                                   <%--value="${info.company_detail}" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<input type="hidden" id="company_pic" name="company_pic" value="${info.company_pic}">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">店铺主图</label>--%>

                        <%--<div class="col-sm-10 col-md-2">--%>
                            <%--<img class="img-thumbnail" name="imagePath1" id="imageDiv1" style="cursor: pointer"--%>
                                 <%--src="${info.company_pic}"/>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label"></label>--%>

                        <%--<div class="col-sm-10">--%>
                            <%--<input type="file" name="file" id="fileUpload1" style="float: left;"/>--%>
                            <%--<input type="button" value="上传" onclick="uploadImageT('fileUpload1','imageDiv1')"--%>
                                   <%--style="float: left;"/><br/><br/>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <input type="hidden" id="info_id" value="${info.id}">
                    <input type="hidden" id="emp_id" value="${info.emp_id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*入驻时间*</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline" placeholder="入驻时间" class="form-control" readonly="true"
                                   value="${info.dateline}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">*店铺星级*</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="company_star" placeholder="店铺星级" class="form-control" readonly="true"--%>
                                   <%--value="${info.company_star}" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">是否审核</label>
                            <div class="col-sm-4">
                                <select class="form-control" id="is_check">
                                    <option value="">--请选择--</option>
                                    <option value="0" ${info.is_check=='0'?'selected':''}>未审核</option>
                                    <option value="1" ${info.is_check=='1'?'selected':''}>已审核</option>
                                    <option value="2" ${info.is_check=='2'?'selected':''}>未通过</option>
                                </select>
                            </div>
                        </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveManagerInfo()">审核</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <%--<div class="col-xs-12 col-sm-12">--%>
        <%--<div class="box">--%>
            <%--<div class="box-header">--%>
                <%--<div class="box-icons">--%>
                    <%--<a class="collapse-link">--%>
                        <%--<i class="fa fa-chevron-up"></i>--%>
                    <%--</a>--%>
                    <%--<a class="expand-link">--%>
                        <%--<i class="fa fa-expand"></i>--%>
                    <%--</a>--%>
                    <%--<a class="close-link">--%>
                        <%--<i class="fa fa-times"></i>--%>
                    <%--</a>--%>
                <%--</div>--%>
                <%--<div class="no-move"></div>--%>
            <%--</div>--%>
            <%--<div class="box-content" style="height: 500px">--%>
                <%--<h4 class="page-header">地图</h4>--%>

                <%--<div id="mapContainer"></div>--%>
            <%--</div>--%>
        <%--</div>--%>
    <%--</div>--%>

</div>
<script type="text/javascript">
    function saveManagerInfo() {
        var infoId = $("#info_id").val();
        var emp_id= $("#emp_id").val();

        var  is_check= $("#is_check").val();
        if(is_check.replace(/\s/g, '') == ''){
            alert("请选择是否审核");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/managerinfo/updateCheck.do",
            data: {
                "id": infoId, "is_check": is_check, "emp_id": emp_id
            },

            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("审核成功");
                    window.location.href = "#module=/managerinfo/list"+ "&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "审核失败", 2: "审核失败"};
                    alert(_case[data.code])
                }
            }
        });
    };

</script>

