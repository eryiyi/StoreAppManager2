<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
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
            <li><a href="javaScript:void(0)">添加定向卡商品</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>添加定向卡商品</span>
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
                    <input type="hidden" id="is_pingtai" name="is_pingtai" value="${is_pingtai}">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_title" class="form-control" placeholder="商品名字"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">商品类别</label>--%>

                        <%--<div class="col-sm-4">--%>
                            <%--<select class="populate placeholder" name="university" id="goods_type">--%>
                                <%--<option value="">-- 选择商品类别 --</option>--%>
                                <%--<c:forEach items="${listBig}" var="s">--%>
                                    <%--<option value="${s.typeId}">${s.typeName}</option>--%>
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
                                        <option value="${e.typeId}">${e.typeName}</option>
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
                                </select>
                            </div>
                        </div>
                    </c:if>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片1</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload','imageDiv','imagePath')" style="float: left;"/><br/><br/>

                            <div id="imageDiv" style="padding: 10px"><img name="imagePath" src=""></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片2</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload1" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload1','imageDiv1','imagePath1')" style="float: left;"/><br/><br/>

                            <div id="imageDiv1" style="padding: 10px"><img name="imagePath1" src=""></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品图片3</label>
                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload2" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload2','imageDiv2','imagePath2')" style="float: left;"/><br/><br/>

                            <div id="imageDiv2" style="padding: 10px"><img name="imagePath2" src=""></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">销售价格</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_seller_price" class="form-control" placeholder="销售价格" value="0" readonly="true"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">市场价格</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_market_price" class="form-control" placeholder="市场价格"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品地址</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_address" class="form-control" placeholder="商品地址"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">联系人</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_person" class="form-control" placeholder="联系人"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">电话</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_tel" class="form-control" placeholder="电话"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">QQ</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_qq" class="form-control" placeholder="qq" data-toggle="tooltip"
                                   data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品数量</label>

                        <div class="col-sm-4">
                            <input type="text" id="goods_count" class="form-control" placeholder="商品数量"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">商品介绍</label>

                        <div class="col-sm-8">
                            <script id="editor" name="editor" type="text/plain"
                                    style="width:100%;height:250px;"></script>
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
        var is_pingtai = $("#is_pingtai").val();
        var type = '';
        var lx_class_id = '';

        if(is_pingtai == '1'){
            type = $("#goods_type").val();
        }
        if(is_pingtai == '0'){
            lx_class_id = $("#lx_class_id").val();
        }


        var goodsTitle = $("#goods_title").val();
        var type = $("#goods_type").val();

        var goods_address = $("#goods_address").val();
        var goods_person = $("#goods_person").val();
        var goods_tel = $("#goods_tel").val();
        var goods_qq = $("#goods_qq").val();

        var sellerPrice = $("#goods_seller_price").val();
        var marketPrice = $("#goods_market_price").val();
        var count = $("#goods_count").val();
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
        if (imagePath == "" || imagePath1 == "" || imagePath2 == "") {
            alert("请上传图片");
            return;
        }
        var reg = /(^[-+]?[1-9]\d*(\.\d{1,2})?$)|(^[-+]?[0]{1}(\.\d{1,2})?$)/;
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

        var regInt = /^([0-9]\d*)$/;
        if (count.replace(/\s/g, '') == '') {
            alert("商品数量不能为空");
            return;
        } else {
            if (!regInt.test(count)) {
                alert("商品数量必须是整数！");
                return;
            }
        }

        if (goods_tel.replace(/\s/g, '') == '') {
            alert("电话不能为空");
            return;
        } else {
            if (!regInt.test(goods_tel)) {
                alert("电话必须是整数！");
                return;
            }
        }

        if(goods_tel.length > 50){
            alert("电话格式不正确！");
        }

        if (content.replace(/\s/g, '') == '') {
            alert("商品详细介绍不能为空");
            return;
        }

        $.ajax({
            cache: true,
            type: "POST",
            url: "/paopaogoods/save.do",
            data: {
                "name": goodsTitle,
                "typeId": type,
                "lx_class_id": lx_class_id,
                "cover": imagePath,
                "goods_cover1": imagePath1,
                "goods_cover2": imagePath2,
                "sellPrice": sellerPrice,
                "marketPrice": marketPrice,
                "address": goods_address,
                "person": goods_person,
                "tel": goods_tel,
                "qq": goods_qq,
                "count": count,
                "cont":content ,
                "is_zhiying": "1",
                "goods_position": "0",
                "goods_count_sale": "0",
                "is_dxk": "1",
                "is_zhekou": "0",
                "zhekou_number": "0",
                "dianpu_number": "0"

            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    var str = data.data;
                    if (str == null || str == '') {
                        alert("添加成功");
                        window.location.href = "#module=/paopaogoods/list&page=1"+ "&_t="+ new Date().getTime();
                    } else {
                        alert(str + " 商品数量已达上限，无法添加商品");
                    }
                } else {
                    var _case = {1: "添加失败"};
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


