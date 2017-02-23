<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>--%>
<%--<script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>--%>
<%--<link rel="stylesheet" href="/ueditor/themes/default/css/ueditor.css" type="text/css">--%>
<script type="text/javascript" charset="utf-8">
    window.UEDITOR_HOME_URL = location.protocol + '//' + document.domain + (location.port ? (":" + location.port) : "") + "/ueditor/";
</script>
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
            <li><a href="javaScript:void(0)">商品管理</a></li>
            <li><a href="javaScript:void(0)">修改商品</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改商品</span>
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
                <h4 class="page-header">商品详情</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">
                    <input type="hidden" id="goods_id" value="${goods.id}">
                    <input type="hidden" id="is_dxk" value="${goods.is_dxk}">
                    <input type="hidden" id="is_pingtai" name="is_pingtai" value="${is_pingtai}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_title" class="form-control" value="${goods.name}"
                                   placeholder="商品名字" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">商品类别</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" name="university" id="goods_type">--%>
                                <%--<option value="">-- 选择商品类别 --</option>--%>
                                <%--<c:forEach items="${listBig}" var="s">--%>
                                    <%--<option value="${s.typeId}" ${goods.typeId==s.typeId?'selected':''}>${s.typeName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">商品类别</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="mm_emp_provinceId" onchange="selectCitys()">--%>
                                <%--<option value="">--选择商品类别--</option>--%>
                                <%--<c:forEach items="${listBig}" var="e" varStatus="st">--%>
                                    <%--<option value="${e.typeId}">${e.typeName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">小类别</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="goods_type">--%>
                                <%--<option value="">--选择小类别--</option>--%>
                                <%--<c:forEach items="${listAll}" var="e" varStatus="st">--%>
                                    <%--<option value="${e.typeId}" ${goods.typeId==e.typeId?'selected':''}>${e.typeName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <c:if test="${is_pingtai == '1'}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">平台商品类别</label>

                            <div class="col-sm-4">
                                <select class="form-control" id="mm_emp_provinceId" onchange="selectCitys()">
                                    <option value="">--选择平台商品类别--</option>
                                    <c:forEach items="${listBig}" var="e" varStatus="st">
                                        <option value="${e.typeId}">${e.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">小类别</label>

                            <div class="col-sm-4">
                                <select class="form-control" id="goods_type">
                                    <option value="">--选择小类别--</option>
                                    <c:forEach items="${listAll}" var="e" varStatus="st">
                                        <option value="${e.typeId}" ${goods.typeId==e.typeId?'selected':''}>${e.typeName}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>

                    <c:if test="${is_pingtai == '0'}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">店铺商品类别</label>

                            <div class="col-sm-4">
                                <select class="form-control" id="mm_emp_provinceId1" onchange="selectClass()">
                                    <option value="">--选择店铺商品类别--</option>
                                    <c:forEach items="${listClassbig}" var="e" varStatus="st">
                                        <option value="${e.lx_class_id}">${e.lx_class_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                        <div class="form-group">
                            <label class="col-sm-2 control-label">小类别</label>

                            <div class="col-sm-4">
                                <select class="form-control" id="lx_class_id">
                                    <option value="">--选择小类别--</option>
                                    <c:forEach items="${listClassSmall}" var="e" varStatus="st">
                                        <option value="${e.lx_class_id}" ${goods.lx_class_id==e.lx_class_id?'selected':''}>${e.lx_class_name}</option>
                                    </c:forEach>
                                </select>
                            </div>
                        </div>
                    </c:if>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">商品图片</label>--%>

                        <%--<div class="col-sm-10">--%>
                            <%--<input type="file" name="file" id="fileUpload" style="float: left;"/>--%>
                            <%--<input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>--%>

                            <%--<div id="imageDiv" style="padding: 10px">--%>
                                <%--<script type="text/javascript">--%>
                                    <%--var imagePath = '${goods.cover}';--%>
                                    <%--if (imagePath != null && imagePath != "") {--%>
                                        <%--var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';--%>
                                        <%--$("#imageDiv").html(html);--%>
                                    <%--}--%>
                                <%--</script>--%>
                            <%--</div>--%>
                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片1</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload','imageDiv','imagePath')" style="float: left;"/><br/><br/>

                            <div id="imageDiv" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${goods.cover}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
                                        $("#imageDiv").html(html);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片2</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload1" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload1','imageDiv1','imagePath1')" style="float: left;"/><br/><br/>

                            <div id="imageDiv1" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${goods.goods_cover1}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath1" title="点击右键删除"/>';
                                        $("#imageDiv1").html(html);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片3</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload2" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload2','imageDiv2','imagePath2')" style="float: left;"/><br/><br/>

                            <div id="imageDiv2" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${goods.goods_cover2}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath2" title="点击右键删除"/>';
                                        $("#imageDiv2").html(html);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">销售价格</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_seller_price" value="${goods.sellPrice}" class="form-control"
                                   placeholder="销售价格" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">市场价格</label>
                        <div class="col-sm-4">
                            <input type="text" id="goods_market_price" value="${goods.marketPrice}" class="form-control"
                                   placeholder="市场价格" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <c:if test="${is_pingtai == '1'}">
                        <div class="form-group">
                            <label class="col-sm-2 control-label">PV利润</label>
                            <div class="col-sm-4">
                                <input type="text" id="pv_prices" value="${goods.pv_prices}" class="form-control"
                                       placeholder="PV利润" data-toggle="tooltip" data-placement="bottom"
                                       title="Tooltip for name">
                            </div>
                        </div>

                    </c:if>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品数量</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_count" value="${goods.count}" class="form-control"
                                   placeholder="商品数量" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_person" value="${goods.person}" class="form-control"
                                   placeholder="联系人" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系电话</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_tel" value="${goods.tel}" class="form-control"
                                   placeholder="联系电话" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系QQ</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_qq" value="${goods.qq}" class="form-control"
                                   placeholder="联系QQ" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系地址</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_address" value="${goods.address}" class="form-control"
                                   placeholder="联系地址" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品介绍</label>

                        <div class="col-sm-8">
                            <script id="editor" name="editor" type="text/plain"
                                    style="width:100%;height:250px;">${goods.cont}</script>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">上架下架</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="isUse">
                                <option value="">--选择--</option>
                                <option value="0" ${goods.isUse=='0'?'selected':''}>上架</option>
                                <option value="1" ${goods.isUse=='1'?'selected':''}>下架</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否删除</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="isDel">
                                <option value="">--选择--</option>
                                <option value="0" ${goods.isDel=='0'?'selected':''}>否</option>
                                <option value="1" ${goods.isDel=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否支持美币支付折扣</label>

                        <div class="col-sm-2">
                            <div class="toggle-switch toggle-switch-success">
                                <label>
                                    <input id="is_zhekou" name="is_zhekou" type="checkbox" ${goods.is_zhekou=='1'?'checked':''}>

                                    <div class="toggle-switch-inner"></div>
                                    <div class="toggle-switch-switch"><i class="fa fa-check"></i></div>
                                </label>
                            </div>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">折扣</label>

                        <div class="col-sm-4">
                            <input type="text" id="zhekou_number" class="form-control" placeholder="只能是数字，例如8折就填写8，9.8折就填写9.8" value="${goods.zhekou_number}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">店铺内排序</label>

                        <div class="col-sm-4">
                            <input type="text" id="dianpu_number" class="form-control" placeholder="店铺内排序,数字越大越靠前" value="${goods.dianpu_number}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="savePaopaoGoods();">
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
    (function () {
        var editor = UE.getEditor('editor');
        Util.editors.push(editor);
    })();

    function savePaopaoGoods() {
        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;

        var is_pingtai = $("#is_pingtai").val();

        var type = '';
        var lx_class_id = '';
        var pv_prices = '';

        if(is_pingtai == '1'){
            type = $("#goods_type").val();
            pv_prices = $("#pv_prices").val();
        }
        if(is_pingtai == '0'){
            lx_class_id = $("#lx_class_id").val();
            pv_prices = '0';
        }

        var goodsId = $("#goods_id").val();
        var isUse = $("#isUse").val();
        var goodsTitle = $("#goods_title").val();

        var goods_address = $("#goods_address").val();
        var goods_person = $("#goods_person").val();
        var goods_tel = $("#goods_tel").val();
        var goods_qq = $("#goods_qq").val();

        var sellerPrice = $("#goods_seller_price").val();
        var marketPrice = $("#goods_market_price").val();
        var isDel = $("#isDel").val();
        var count = $("#goods_count").val();
        var is_dxk = $("#is_dxk").val();
        var dianpu_number = $("#dianpu_number").val();
        var content = UE.getEditor('editor').getContent();
        if (goodsTitle.replace(/\s/g, '') == '') {
            alert("商品名称不能为空");
            return;
        }

        if(is_pingtai == '1'){
            if (type.replace(/\s/g, '') == '') {
                alert("请选择商品类别");
                return;
            }
        }

        if(is_pingtai == '0'){
            if (lx_class_id.replace(/\s/g, '') == '') {
                alert("请选择商品类别");
                return;
            }
        }

        var imagePath = $("img[name='imagePath']").attr("src");
        var imagePath1 = $("img[name='imagePath1']").attr("src");
        var imagePath2 = $("img[name='imagePath2']").attr("src");

        if (imagePath.replace(/\s/g, '') == '' || imagePath2.replace(/\s/g, '') == '' || imagePath1.replace(/\s/g, '') == '') {
            alert("请选择商品封面图片");
            return;
        }


        if (sellerPrice.replace(/\s/g, '') == '') {
            alert("销售价格不能为空");
            return;
        } else {
            if (!reg.test(sellerPrice)) {
                alert("销售价格必须为合法数字(正数，最多两位小数)！");
                return;
            }
        }
        if (marketPrice.replace(/\s/g, '') == '') {
            alert("市场价格不能为空");
            return;
        } else {
            if (!reg.test(marketPrice)) {
                alert("市场价格必须为合法数字(正数，最多两位小数)！");
                return;
            }
        }
        if(is_pingtai == '1'){
            if (pv_prices.replace(/\s/g, '') == '') {
                alert("PV不能为空");
                return;
            } else {
                if (!reg.test(pv_prices)) {
                    alert("PV必须为合法数字(正数，最多两位小数)！");
                    return;
                }
            }
        }

        var regInt = /^([0-9]\d*)$/;
        if (count.replace(/\s/g, '') == '') {
            alert("商品数量不能为空");
            return;
        } else {
            if (!regInt.test(count)) {
                alert("商品数量必须是正整数，大于0！");
                return;
            }
        }
        if (content.replace(/\s/g, '') == '') {
            alert("商品详细介绍不能为空");
            return;
        }
        if (isUse.replace(/\s/g, '') == '') {
            alert("请选择是否上架");
            return;
        }
        if (isDel.replace(/\s/g, '') == '') {
            alert("请选择是否删除");
            return;
        }


        var zhekou_number = $("#zhekou_number").val();

        var is_zhekou = '';
        if ($('#is_zhekou').is(':checked')) {
            is_zhekou = '1';
        } else {
            is_zhekou = '0';
        }
        if(is_zhekou == '1'){
            if (zhekou_number.replace(/\s/g, '') == '') {
                alert("折扣不能为空");
                return;
            } else {
                if (!reg.test(zhekou_number)) {
                    alert("折扣必须为合法数字(正数，最多两位小数)！");
                    return;
                }
            }
        }


        var regInt = /^([0-9]\d*)$/;
        if (dianpu_number.replace(/\s/g, '') == '') {
            alert("店铺内排序不能为空");
            return;
        } else {
            if (!regInt.test(dianpu_number)) {
                alert("店铺内排序必须是整数！");
                return;
            }
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/paopaogoods/update.do",
            data: {
                "id": goodsId,
                "name": goodsTitle,
                "isUse": isUse,
                "isDel": isDel,
                "typeId": type,
                "cover": imagePath,
                "goods_cover1": imagePath1,
                "goods_cover2": imagePath2,
                "sellPrice": sellerPrice,
                "marketPrice": marketPrice,
                "pv_prices": pv_prices,
                "address": goods_address,
                "person": goods_person,
                "tel": goods_tel,
                "qq": goods_qq,
                "count": count,
                "cont": content,
                "lx_class_id": lx_class_id,
                "is_dxk": is_dxk,
                "is_zhekou": is_zhekou,
                "zhekou_number": zhekou_number,
                "dianpu_number": dianpu_number
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    var str = data.data;
                    if (str == null || str == '') {
                        alert("修改成功");
                        window.location.href = "#module=/paopaogoods/list&page=1"+
                        "&_t=" + new Date().getTime();
                    } else {
                        alert(str + " 商品数量已达上限，无法添加商品");
                    }
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

    function uploadImage(_fileUpload,_imageDiv,_imagePath) {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: _fileUpload,                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + data.data + '" width="150" height="150" name="'+_imagePath+'" title="点击右键删除"/>';
                            $("#"+_imageDiv).html(html);
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

    function selectCitys() {
        var province = $("#mm_emp_provinceId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: "/getAllGoodsTypes.do",
            data: {
                "father": province
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    <%--var citys = ${listCitysAll};--%>
                    var citys = data.data;
                    var ret = "<option value=''>" + '请选择分类' + "</option>";
                    for (var i = citys.length - 1; i >= 0; i--) {
                        if (citys[i].is_type == province) {
                            ret += "<option value='" + citys[i].typeId + "'>" + citys[i].typeName + "</option>";
                        }
                    }
                    $("#goods_type").html(ret);
                } else {
                    var _case = {1: "获取数据失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

    function selectCitys() {
        var province = $("#mm_emp_provinceId").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: "/getAllGoodsTypes.do",
            data: {
                "father": province
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    <%--var citys = ${listCitysAll};--%>
                    var citys = data.data;
                    var ret = "<option value=''>" + '请选择分类' + "</option>";
                    for (var i = citys.length - 1; i >= 0; i--) {
                        if (citys[i].is_type == province) {
                            ret += "<option value='" + citys[i].typeId + "'>" + citys[i].typeName + "</option>";
                        }
                    }
                    $("#goods_type").html(ret);
                } else {
                    var _case = {1: "获取数据失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;


    function selectClass() {
        var province = $("#mm_emp_provinceId1").val();
        $.ajax({
            cache: true,
            type: "POST",
            url: "/getAllLxClass.do",
            data: {
                "f_lx_class_id": province
            },
            async: false,
            contentType: "application/x-www-form-urlencoded; charset=utf-8",
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    <%--var citys = ${listCitysAll};--%>
                    var citys = data.data;
                    var ret = "<option value=''>" + '请选择分类' + "</option>";
                    for (var i = citys.length - 1; i >= 0; i--) {
                        if (citys[i].f_lx_class_id == province) {
                            ret += "<option value='" + citys[i].lx_class_id + "'>" + citys[i].lx_class_name + "</option>";
                        }
                    }
                    $("#lx_class_id").html(ret);
                } else {
                    var _case = {1: "获取数据失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>


