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
            <li><a href="javascript:void (0);">商城分类</a></li>
            <li><a href="javascript:void (0);">修改商城分类</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改商城分类</span>
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
                <h4 class="page-header">商城分类详情</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" id="type_id" name="type_id" value="${type.typeId}">
                    <input type="hidden" id="is_type" name="is_type" value="${type.is_type}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商城类别名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="type_name" value="${type.typeName}" class="form-control"
                                   placeholder="商城类别名称" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商城类别介绍</label>

                        <div class="col-sm-6">
                            <input type="text" id="type_content" value="${type.typeContent}" class="form-control"
                                   placeholder="商城类别介绍" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否禁用</label>

                        <div class="col-sm-2">
                            <div class="toggle-switch toggle-switch-success">
                                <label>
                                    <input id="isUse" name="isUse"
                                           type="checkbox" ${type.typeIsUse=='1'?'checked':''}  >

                                    <div class="toggle-switch-inner"></div>
                                    <div class="toggle-switch-switch"><i class="fa fa-check"></i></div>
                                </label>
                            </div>
                        </div>
                    </div>



                    <div class="form-group">
                        <label class="col-sm-2 control-label">首页/附近</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="is_index" name="is_index">
                                <option value="0" ${type.is_index=='0'?'selected':''}>首页 </option>
                                <option value="1" ${type.is_index=='1'?'selected':''}>附近</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">类别图片</label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>

                            <div id="imageDiv" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${type.typeCover}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
//                    var imageDivHtml = $("#imageDiv").html() + html;
                                        $("#imageDiv").html(html);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-6">
                            <input type="text" id="type_num" value="${type.type_num}" class="form-control" placeholder="只能填写数字 越大越靠前"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="updateType()">提交</button>
                            <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回
                            </button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--<div class="col-xs-4 col-sm-4">--%>
<script type="text/javascript">

    function uploadImage() {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: 'fileUpload',                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
//                  var imageDivHtml = $("#imageDiv").html() + html;
                            $("#imageDiv").html(html);
                        } else {
                            if (data.code == 1) {
                                alert("上传图片失败");
                            } else if (data.code == 2) {
                                alert("上传图片格式只能为：jpg、png、gif、bmp、jpeg");
                            } else if (data.code == 3) {
                                alert("请选择上传图片");
                            } else {
                                alert("上传失败");
                            }
                        }
                    }
                }
        );
    }

    function deleteImage(e, node) {
        if (e.button == 2) {
            if (confirm("确定移除该图片吗？")) {
                $(node).remove();
            }
        }
    }
    ;

    function updateType() {
        var type_id = $("#type_id").val();
        var is_index = $("#is_index").val();
        var is_type = $("#is_type").val();

        var typeName = $("#type_name").val();
        if (typeName.replace(/\s/g, '') == '') {
            alert("类别名称不能为空");
            return;
        }
        var typeContent = $("#type_content").val()
        var type_num = $("#type_num").val();

        var regInt = /^([0-9]\d*)$/;
        if (type_num.replace(/\s/g, '') == '') {
            alert("排序不能为空");
            return;
        } else {
            if (!regInt.test(type_num)) {
                alert("排序必须是整数！");
                return;
            }
        }
        var isUse = '';
        if ($('#isUse').is(':checked')) {
            isUse = '1';
        } else {
            isUse = '0';
        }

        var imagePath = $("img[name='imagePath']").attr("src");
        if(is_type == '0'){
            if (imagePath == "") {
                alert("请上传图片");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/updateGoodsType.do",
            data: {
                "typeId": type_id,
                "typeName": typeName,
                "typeIsUse": isUse,
                "typeContent": typeContent,
                "type_num": type_num,
                "typeCover": imagePath,
                "is_index": is_index,
                "is_type": is_type
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("更新成功");
                    if(is_type == '0'){
                        window.location.href = "#module=listType&_t=" + new Date().getTime();
                    }else{
                        window.location.href = "#module=toListSmallType&_t=" + new Date().getTime()+"&type_id="+ is_type;
                    }

                } else {
                    var _case = {1: "类别名称不能为空", 2: "类别介绍不能为空", 3: "类别图片不能为空", 4: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });


    }
    ;

</script>


