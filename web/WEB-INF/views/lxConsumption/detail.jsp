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
            <li><a href="javascript:void(0)">消费记录</a></li>
            <li><a href="javascript:void(0)">消费记录详情</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>消费记录详情</span>
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
                <h4 class="page-header">消费记录详情</h4>
                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户昵称</label>
                        <div class="col-sm-4">
                            <input type="text" id="emp_name" value="${lxConsumption.emp_name}" class="form-control"
                                   placeholder="用户昵称" data-toggle="tooltip" data-placement="bottom" readonly
                                   title="Tooltip for name">
                        </div>
                    </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">手机号</label>
                    <div class="col-sm-4">
                        <input type="text" id="emp_mobile" value="${lxConsumption.emp_mobile}" class="form-control"
                               placeholder="手机号" data-toggle="tooltip" data-placement="bottom" readonly
                               title="Tooltip for name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">消费金额</label>
                    <div class="col-sm-4">
                        <input type="text" id="lx_consumption_count" value="${lxConsumption.lx_consumption_count}" class="form-control"
                               placeholder="消费金额" data-toggle="tooltip" data-placement="bottom" readonly
                               title="Tooltip for name">
                    </div>
                </div>
                <div class="form-group">
                    <label class="col-sm-2 control-label">消费说明</label>
                    <div class="col-sm-4">
                        <input type="text" id="lx_consumption_cont" value="${lxConsumption.lx_consumption_cont}" class="form-control"
                               placeholder="消费说明" data-toggle="tooltip" data-placement="bottom" readonly
                               title="Tooltip for name">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">消费订单号</label>
                    <div class="col-sm-4">
                        <input type="text" id="order_no" value="${lxConsumption.order_no}" class="form-control"
                               placeholder="消费订单号" data-toggle="tooltip" data-placement="bottom" readonly
                               title="Tooltip for name">
                    </div>
                </div>

                <div class="form-group">
                    <label class="col-sm-2 control-label">消费类型</label>
                    <div class="col-sm-4">
                        <%--<select class="form-control" id="lx_consumption_type" >--%>
                            <%--<option value="">--消费类型--</option>--%>
                            <%--<option value="0" ${lxConsumption.lx_consumption_type=='0'?'selected':''}>购买商品</option>--%>
                            <%--<option value="1" ${lxConsumption.lx_consumption_type=='1'?'selected':''}>后台充值</option>--%>
                            <%--<option value="1" ${lxConsumption.lx_consumption_type=='2'?'selected':''}>手机端充值</option>--%>
                        <%--</select>--%>
                        <c:if test="${lxConsumption.lx_consumption_type == '0'}">购买商品</c:if>
                        <c:if test="${lxConsumption.lx_consumption_type == '1'}">零钱充值</c:if>
                        <c:if test="${lxConsumption.lx_consumption_type == '2'}">手机端充值</c:if>
                        <c:if test="${lxConsumption.lx_consumption_type == '3'}">定向卡充值</c:if>
                    </div>
                </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <%--<button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回--%>
                            <%--</button>--%>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>


