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
            <li><a href="javascript:void(0)">版本管理</a></li>
            <li><a href="javascript:void(0)">修改版本信息</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改版本信息</span>
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
                <h4 class="page-header">修改版本信息</h4>

                    <input type="hidden" value="${versonCodeObj.mm_version_id}" id="mm_version_id">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">版本</label>

                        <div class="col-sm-4">
                            <input type="text" id="mm_version_code" value="${versonCodeObj.mm_version_code}" class="form-control"
                                   placeholder="版本 数字" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveP()">确定</button>
                            <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回
                            </button>
                        </div>
                    </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function saveP() {
        var mm_version_code = $("#mm_version_code").val();
        var mm_version_id = $("#mm_version_id").val();

        if (mm_version_code.replace(/\s/g, '') == '') {
            alert("请输入版本信息");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/versionCodeController/edit.do",
            data: {
                "mm_version_id": mm_version_id,
                "mm_version_code": mm_version_code
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
//                    window.location.href = "#module=versionCodeController/toEdit" +""+ "&_t=" + new Date().getTime();
                } else {
                    alert("执行失败，请检查")
                }
            }
        });
    }
</script>
