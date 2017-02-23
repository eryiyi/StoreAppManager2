<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="/js/Util.js"></script>
<style>
    form {
        margin: 0;
    }

    textarea {
        display: block;
    }
</style>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javascript:void(0)" onclick="toPage('mainPage','')">主页</a></li>
            <li><a href="javaScript:void(0)">费率管理</a></li>
            <li><a href="javaScript:void(0)">修改费率</a></li>
        </ol>
    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改费率</span>
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
                <h4 class="page-header">详情</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">
                    <input id="rate_id" type="hidden" value="${rate.rid}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">费率</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="news_type" id="select_rate">
                                <option value="">-- 选择费率 --</option>
                                <option value="0.02" ${rate.rate=='0.02'?'selected':''}>%2</option>
                                <option value="0.03" ${rate.rate=='0.03'?'selected':''}>%3</option>
                                <option value="0.04" ${rate.rate=='0.04'?'selected':''}>%4</option>
                                <option value="0.05" ${rate.rate=='0.05'?'selected':''}>%5</option>
                                <option value="0.06" ${rate.rate=='0.06'?'selected':''}>%6</option>
                                <option value="0.07" ${rate.rate=='0.07'?'selected':''}>%7</option>
                                <option value="0.08" ${rate.rate=='0.08'?'selected':''}>%8</option>
                                <option value="0.09" ${rate.rate=='0.09'?'selected':''}>%9</option>
                                <option value="0.1"  ${rate.rate=='0.1'?'selected':''}>%10</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="upRate();">
                                <span><i class="fa fa-clock-o"></i></span>
                                提交
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function upRate() {
        var id = $("#rate_id").val();
        var rate = $("#select_rate").val();

        if (rate.replace(/\s/g, '') == '') {
            alert("请选择费率");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/rate/update.do",
            data: {"rid": id, "rate": rate},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=/rate/edit&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
</script>


