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
            <li><a href="javascript:void (0);">信息维护</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>信息维护</span>
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
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*真实姓名*</label>

                        <div class="col-sm-4">
                            <input type="text" id="real_name" value="${info.realName}" class="form-control"
                                   placeholder="真实姓名" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*身份证号*</label>

                        <div class="col-sm-4">
                            <input type="text" id="idcard" value="${info.idcard}" class="form-control"
                                   placeholder="身份证号" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*身份证正面图*</label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage()" style="float: left;"/><br/><br/>

                            <div id="imageDiv" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${info.idcardUrl}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
                                        $("#imageDiv").html(html);
                                    }</script>
                            </div>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*营业执照*</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="company_yyzz" id="company_yyzz" style="cursor: pointer"
                                 src="${info.company_yyzz}"/>
                            <script type="text/javascript">
                                var imagePath = '${info.company_yyzz}';
                                if (imagePath != null && imagePath != "") {
                                    var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
                                    $("#imageDiv").html(html);
                                }</script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload2" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImageT('fileUpload2','company_yyzz')"
                                   style="float: left;"/><br/><br/>
                        </div>
                    </div>

                    <div class="form-group" >
                    <label class="col-sm-2 control-label">*支付宝账号*</label>
                    <div class="col-sm-4">
                    <input type="text" id="pay_number" value="${info.payNumber}" class="form-control" placeholder="支付宝账号" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">*支付宝校验姓名*</label>
                    <div class="col-sm-4">
                    <input type="text" id="check_name"value="${info.checkName}" class="form-control" placeholder="支付宝校验姓名" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div><div class="form-group">
                    <label class="col-sm-2 control-label">*银行卡号*</label>
                    <div class="col-sm-4">
                    <input type="text" id="bank_card" value="${info.bankCard}" class="form-control" placeholder="银行卡号" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div><div class="form-group">
                    <label class="col-sm-2 control-label">*开户行*</label>
                    <div class="col-sm-4">
                    <input type="text" id="bank_type" value="${info.bankType}" class="form-control" placeholder="开户银行" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div><div class="form-group">
                    <label class="col-sm-2 control-label">*开户行地址*</label>
                    <div class="col-sm-4">
                    <input type="text" id="bank_address" value="${info.bankAddress}" class="form-control" placeholder="开户行的详细地址" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">*开户人姓名*</label>
                    <div class="col-sm-4">
                    <input type="text" id="bank_name" value="${info.bankName}" class="form-control" placeholder="银行卡持有者姓名" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div>
                    <div class="form-group">
                    <label class="col-sm-2 control-label">*银行预留电话*</label>
                    <div class="col-sm-4">
                    <input type="text" id="mobile" value="${info.mobile}" class="form-control" placeholder="银行预留电话" data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                    </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺名称*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_name" placeholder="店铺名称" class="form-control"
                                   value="${info.company_name}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺联系人*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_person" placeholder="店铺联系人" class="form-control"
                                   value="${info.company_person}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺电话*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_tel" placeholder="店铺电话" class="form-control"
                                   value="${info.company_tel}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺地址*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_address" placeholder="店铺地址" class="form-control"
                                   value="${info.company_address}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺介绍*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_detail" placeholder="店铺介绍" class="form-control"
                                   value="${info.company_detail}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <input type="hidden" id="company_pic" name="company_pic" value="${info.company_pic}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺主图*</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath1" id="imageDiv1" style="cursor: pointer"
                                 src="${info.company_pic}"/>
                            <script type="text/javascript">
                                var imagePath = '${info.company_pic}';
                                if (imagePath != null && imagePath != "") {
                                    var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title="点击右键删除"/>';
                                    $("#imageDiv").html(html);
                                }</script>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload1" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImageT('fileUpload1','imageDiv1')"
                                   style="float: left;"/><br/><br/>
                        </div>
                    </div>

                    <input type="hidden" id="info_id" value="${info.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">*入驻时间*</label>

                        <div class="col-sm-4">
                            <input type="text" id="dateline" placeholder="入驻时间" class="form-control" readonly="true"
                                   value="${info.dateline}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">*店铺星级*</label>

                        <div class="col-sm-4">
                            <input type="text" id="company_star" placeholder="店铺星级" class="form-control" readonly="true"
                                   value="${info.company_star}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <%--<input type="hidden" id="type_id" name="type_id" value="${info.type_id}">--%>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">选择店铺分类</label>--%>
                        <%--<div class="col-sm-4">--%>
                            <%--<select class="form-control" id="type_id">--%>
                                <%--<option value="">--选择店铺分类--</option>--%>
                                <%--<c:forEach items="${listType}" var="e" varStatus="st">--%>
                                    <%--<option value="${e.typeId}" ${e.typeId==info.type_id?'selected':''}>${e.typeName}</option>--%>
                                <%--</c:forEach>--%>
                            <%--</select>--%>
                        <%--</div>--%>
                    <%--</div>--%>

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
                                    <option value="${e.lx_class_id}" ${info.type_id==e.lx_class_id?'selected':''}>${e.lx_class_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">店铺经纬度（*重要*关系到您的店铺在手机app端地图上显示的位置）</label>

                        <div class="col-sm-4">
                            <input type="text" id="lat_company" class="form-control" value="${info.lat_company}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                            <input type="text" id="lng_company" class="form-control" value="${info.lng_company}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveManagerInfo()">更新</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
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
            <div class="box-content" style="height: 500px">
                <h4 class="page-header">地图</h4>

                <div id="mapContainer"></div>
            </div>
        </div>
    </div>

</div>
<script type="text/javascript">

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

    function saveManagerInfo() {
        var infoId = $("#info_id").val();
        var realName = $("#real_name").val();
        if (realName.replace(/\s/g, '') == '') {
            alert("请填写真实姓名");
            return;
        }
        if (realName.length > 20) {
            alert("请填写真实姓名");
            return;
        }
        var idcard = $("#idcard").val();
        if (idcard.replace(/\s/g, '') == '') {
            alert("请填写身份证号");
            return;
        }
        if (idcard.length != 18) {
            alert("请填写身份证号!18位");
            return;
        }
        var imagePath = $("img[name='imagePath']").attr("src");
        if (imagePath.replace(/\s/g, '') == '') {
            alert("请上传身份证图片");
            return;
        }

        var pay_number = $("#pay_number").val();
        var check_name = $("#check_name").val();
        var bank_card = $("#bank_card").val();
        var bank_type = $("#bank_type").val();
        var bank_address = $("#bank_address").val();
        var bank_name = $("#bank_name").val();
        var mobile = $("#mobile").val();

        if (pay_number.replace(/\s/g, '') == '') {
            alert("请填写支付宝账号！");
            return;
        }
        if (check_name.replace(/\s/g, '') == '') {
            alert("请填写支付宝校验名！");
            return;
        }
        if (bank_card.replace(/\s/g, '') == '') {
            alert("请填写银行卡号！");
            return;
        }
        if (bank_type.replace(/\s/g, '') == '') {
            alert("请填写开户行！");
            return;
        }
        if (bank_address.replace(/\s/g, '') == '') {
            alert("请填写开户行地址！");
            return;
        }
        if (bank_name.replace(/\s/g, '') == '') {
            alert("请填写开户人姓名！");
            return;
        }
        if (mobile.replace(/\s/g, '') == '') {
            alert("请填写银行预留手机号！");
            return;
        }


        var company_name = $("#company_name").val();
        if (company_name.replace(/\s/g, '') == '') {
            alert("请填写店铺名称");
            return;
        }
        if (company_name.length > 100) {
            alert("店铺名称超出字段限制");
            return;
        }
        var company_person = $("#company_person").val();
        if (company_person.replace(/\s/g, '') == '') {
            alert("请填写店铺联系人");
            return;
        }
        if (company_person.length > 20) {
            alert("联系人超出字段限制");
            return;
        }
        var company_tel = $("#company_tel").val();
        if (company_tel.replace(/\s/g, '') == '') {
            alert("请填写店铺电话");
            return;
        }
        if (company_tel.length > 20) {
            alert("店铺电话超出字段限制");
            return;
        }

        var company_address = $("#company_address").val();
        if (company_address.replace(/\s/g, '') == '') {
            alert("请填写店铺地址");
            return;
        }
        if (company_address.length > 100) {
            alert("店铺地址超出字段限制");
            return;
        }

        var company_detail = $("#company_detail").val();
        var lx_class_id = $("#lx_class_id").val();

        if (company_detail.replace(/\s/g, '') == '') {
            alert("请填写店铺介绍");
            return;
        }

        if (company_detail.length > 1000) {
            alert("店铺介绍超出字段限制");
            return;
        }

        if (lx_class_id.replace(/\s/g, '') == '') {
            alert("请选择店铺分类！");
            return;
        }

        var regInt = /^([1-9]\d*)$/;

        var imagePath1 = $("img[name='imagePath1']").attr("src");
        var company_yyzz = $("img[name='company_yyzz']").attr("src");

        if (imagePath1 == "" || imagePath1 == null) {
            imagePath1 = $("#company_pic").val();
        }
        if (company_yyzz == "" || company_yyzz == null) {
            company_yyzz = $("#company_yyzz").val();
        }

        if (company_yyzz.replace(/\s/g, '') == '') {
            alert("请上传营业执照");
            return;
        }

        var lat_company = $("#lat_company").val();
        var lng_company = $("#lng_company").val();

        $.ajax({
            cache: true,
            type: "POST",
            url: "/managerinfo/save.do",
            data: {
                "id": infoId, "realName": realName, "idcard": idcard,
                "idcardUrl": imagePath, "company_name": company_name,
                "company_person": company_person, "company_tel": company_tel,
                "company_address": company_address, "company_detail": company_detail,
                "company_pic": imagePath1,
                "checkName": check_name,
                "payNumber": pay_number,
                "bankCard": bank_card,
                "bankType": bank_type,
                "bankAddress": bank_address,
                "bankName": bank_name,
                "type_id": lx_class_id,
                "lat_company": lat_company,
                "lng_company": lng_company,
                "company_yyzz": company_yyzz,
                "mobile": mobile
            },

            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("信息维护成功");
                    window.location.href = "#module=/managerinfo/toEdit"+ "&_t="+ new Date().getTime();
                } else {
                    var _case = {1: "类别名称不能为空", 2: "保存失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

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

    function uploadImageT(_fileUpload, _imageDiv) {
        $.ajaxFileUpload(
                {
                    url: "/uploadUnCompressImage.do?_t=" + new Date().getTime(),            //需要链接到服务器地址
                    secureuri: false,//是否启用安全提交，默认为false
                    fileElementId: _fileUpload,                        //文件选择框的id属性
                    dataType: 'json',                                     //服务器返回的格式，可以是json, xml
                    success: function (data, status)  //服务器成功响应处理函数
                    {
                        if (data.success) {
                            document.getElementById(_imageDiv).src = data.data;
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
</script>

<script type="text/javascript">
    //初始化地图对象，加载地图
    ////初始化加载地图时，若center及level属性缺省，地图默认显示用户当前城市范围
    var map = new AMap.Map('mapContainer', {
        resizeEnable: true
    });
    //地图中添加地图操作ToolBar插件
    map.plugin(['AMap.ToolBar'], function () {
        //设置地位标记为自定义标记
        var toolBar = new AMap.ToolBar();
        map.addControl(toolBar);
    });
    var lat_company = $("#lat_company").val();
    var lng_company = $("#lng_company").val();

    var mapObj = new AMap.Map("mapContainer", {
        rotateEnable: true,
        dragEnable: true,
        zoomEnable: true,
        zooms: [3, 18],
        resizeEnable: true,
        zoom:11,
        center: [lng_company, lat_company]
        //二维地图显示视口
//    view: new AMap.View2D({
//      center:new AMap.LngLat(118.783897, 32.058875),//地图中心点
//      zoom:15 //地图显示的缩放级别
//    })
    });
    //  mapObj.plugin(["AMap.ToolBar"],function(){
    //    toolBar = new AMap.ToolBar();
    //    mapObj.addControl(toolBar);
    //  });
    var marker = new AMap.Marker({
        position: mapObj.getCenter()
    });
    marker.setMap(mapObj);

    //为地图注册click事件获取鼠标点击出的经纬度坐标
    var clickEventListener = AMap.event.addListener(mapObj, 'click', function (e) {
        document.getElementById("lat_company").value = e.lnglat.getLat();
        document.getElementById("lng_company").value = e.lnglat.getLng();

        marker.setMap(null);
        mapObj.setCenter(new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat()));
        marker = new AMap.Marker({
            position: new AMap.LngLat(e.lnglat.getLng(), e.lnglat.getLat())
        });
        marker.setMap(mapObj);
    });
</script>
