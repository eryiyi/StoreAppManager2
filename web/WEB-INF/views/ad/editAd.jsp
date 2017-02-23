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
            <li><a href="javascript:void(0)">商家店铺广告管理</a></li>
            <li><a href="javascript:void(0)">修改广告</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改广告</span>
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
                <h4 class="page-header">修改广告</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${adObj.mm_ad_id}" id="mm_ad_id">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">广告图片</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath" id="imageDiv" src="${adObj.mm_ad_pic}"
                                 style="cursor: pointer"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">链接</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="mm_ad_url" value="${adObj.mm_ad_url}" class="form-control"--%>
                                   <%--placeholder="链接" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">广告语</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="mm_ad_title" value="${adObj.mm_ad_title}" class="form-control"--%>
                                   <%--placeholder="广告语" data-toggle="tooltip" data-placement="bottom"--%>
                                   <%--title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="mm_ad_num" value="${adObj.mm_ad_num}" class="form-control"
                                   placeholder="排序 数字" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveP()">添加</button>
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
    function saveP() {

        var mm_ad_id = $("#mm_ad_id").val();
//        var mm_ad_title = $("#mm_ad_title").val();

//        var mm_ad_url = $("#mm_ad_url").val();
        var mm_ad_num = $("#mm_ad_num").val();

//        if (mm_ad_url.replace(/\s/g, '') == '') {
//            alert("请输入链接");
//            return;
//        }

        var imagePath = $("img[name='imagePath']").attr("src");

        if (imagePath.replace(/\s/g, '') == '') {
            alert("请选择图片文件");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/adObj/editAd.do",
            data: {
                "mm_ad_pic": imagePath,
                "mm_ad_url": "",
                "mm_ad_num": mm_ad_num,
                "mm_ad_id": mm_ad_id,
                "mm_ad_title": ""
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=adObj/list"+ "&_t="+ new Date().getTime();
                } else {
                    alert("执行失败，请检查")
                }
            }
        });
    }
</script>
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
                            document.getElementById('imageDiv').src = data.data;
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


</script>

