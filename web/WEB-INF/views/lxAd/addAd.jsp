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
            <li><a href="javascript:void(0)">广告管理</a></li>
            <li><a href="javascript:void(0)">添加广告</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>添加广告</span>
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
                <h4 class="page-header">添加广告</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">广告图片</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath" src="" id="imageDiv" style="cursor: pointer"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">请选择广告类型</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="ad_type">
                                <option value="">--请选择广告类型--</option>
                                <option value="1" >推荐顶部轮播图 </option>
                                <option value="2" >推荐中部广告（大）</option>
                                <option value="3" >推荐中部广告（小）</option>
                                <option value="4" >商城顶部轮播图</option>
                                <option value="5" >商城首发新品</option>
                                <option value="6" >商城特惠专区</option>
                                <option value="7" >定向卡商家广告</option>


                                <option value="8" >闪购商品</option>
                                <option value="9" >首页闪购下方的广告</option>
                                <option value="10" >主页主题活动（大）</option>
                                <option value="11" >主页主题活动（小）</option>
                                <option value="12" >主页特色市场（大）</option>
                                <option value="13" >主页特色市场（小）</option>
                                <option value="14" >附近中间三个广告</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">选择商品</label>

                        <div class="col-sm-4">
                            <select class="form-control" name="ad_msg_id" id="ad_msg_id">
                                <option value="">-- 选择商品 --</option>
                                <c:forEach items="${listGoods}" var="s">
                                    <option value="${s.goods_id}">${s.goods_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">请选择广告要跳转的位置</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="ad_url_type">--%>
                                <%--<option value="">--请选择广告要跳转的位置--</option>--%>
                                <%--<option value="1" >商品详情</option>--%>
                                <%--<option value="2" >店铺详情</option>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">广告语</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="mm_ad_title" class="form-control" placeholder="广告语"--%>
                                   <%--data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<div class="col-sm-10 col-sm-offset-3">--%>
                            <%--<font color="red">*为了实现最佳的展现效果，图片最佳尺寸：1000（宽）*360（高）</font>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">链接</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<input type="text" id="mm_ad_url" class="form-control" placeholder="链接"--%>
                                   <%--data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">--%>
                        <%--</div>--%>
                    <%--</div>--%>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">备注</label>

                        <div class="col-sm-4">
                            <input type="text" id="ad_content"  class="form-control" placeholder="备注"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="mm_ad_num" value="0" class="form-control" placeholder="排序 数字"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
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
        var mm_ad_num = $("#mm_ad_num").val();
        var ad_type = $("#ad_type").val();
        var ad_msg_id = $("#ad_msg_id").val();
        var ad_content = $("#ad_content").val();
//        var ad_url_type = $("#ad_url_type").val();

        var imagePath = $("img[name='imagePath']").attr("src");

        if (imagePath.replace(/\s/g, '') == '') {
            alert("请选择图片文件");
            return;
        }
        if (ad_type.replace(/\s/g, '') == '') {
            alert("请选择广告类型");
            return;
        }
        if (ad_msg_id.replace(/\s/g, '') == '') {
            alert("请选择商品");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/lxAdController/addAd.do",
            data: {
                "ad_pic": imagePath,
                "ad_type": ad_type,
                "ad_url_type": "1",
                "ad_msg_id": ad_msg_id,
                "ad_content": ad_content,
                "top_num": mm_ad_num
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("执行成功");
                    window.location.href = "#module=lxAdController/list"+ "&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "添加失败，请检查", 2: "广告最多5条，请删除之后再添加！"};
                    alert(_case[data.code])
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

