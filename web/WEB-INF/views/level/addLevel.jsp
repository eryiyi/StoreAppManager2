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
            <li><a href="#">等级管理</a></li>
            <li><a href="#">添加等级</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>添加等级</span>
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
                <h4 class="page-header">等级内容</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">等级名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="level_name" class="form-control" placeholder="等级名称"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">起始分数</label>

                        <div class="col-sm-4">
                            <input type="text" id="level_start" class="form-control" placeholder="起始分数"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">结束分数</label>

                        <div class="col-sm-4">
                            <input type="text" id="level_end" class="form-control" placeholder="结束分数"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveLevel()">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function saveLevel() {
        var levelName = $("#level_name").val();
        var levelStart = $("#level_start").val();
        var levelEnd = $("#level_end").val();

        if (levelName.replace(/\s/g, '') == '') {
            alert("等级名称不能为空");
            return;
        }
        if (!/^\d+$/.test(levelStart)) {
            alert("请输入正确的起始分数");
            return;
        }
        if (!/^\d+$/.test(levelEnd)) {
            alert("请输入正确的结束分数");
            return;
        }
        if (parseInt(levelStart) > parseInt(levelEnd)) {
            alert("起始分数不能大于结束分数");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/addLevel.do",
            data: {"levelName": levelName, "levelStart": levelStart, "levelEnd": levelEnd},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("添加成功");
                    window.location.href = "#module=listLevel"+ "&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "等级名称不能为空", 2: "请输入正确的起始分数", 3: "请输入正确的结束分数", 4: "起始分数不能大于结束分数", 5: "保存失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
</script>


