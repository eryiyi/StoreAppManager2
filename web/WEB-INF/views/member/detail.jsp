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
            <li><a href="javascript:void (0);">会员管理</a></li>
            <li><a href="javascript:void (0);">会员详情</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>会员详情</span>
                </div>
            </div>
            <div class="box-content">
                <%--<h4 class="page-header">会员详情</h4>--%>
                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${empVO.empId}" id="emp_id">
                    <input type="hidden" value="${empVO.empCover}" id="emp_cover">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">账号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_number" readonly="true"  class="form-control" value="${empVO.emp_number}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">手机号</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_mobile" readonly="true" class="form-control"
                                   value="${empVO.empMobile}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">昵称</label>

                        <div class="col-sm-4">
                            <input type="text" id="emp_name" class="form-control" value="${empVO.empName}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">头像</label>

                        <div class="col-sm-10 col-md-2">
                            <img class="img-thumbnail" name="imagePath" id="imageDiv" style="cursor: pointer" src="${empVO.empCover}"/>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label"></label>

                        <div class="col-sm-10">
                            <input type="file" name="file" id="fileUpload" style="float: left;"/>
                            <input type="button" value="上传" onclick="uploadImage('fileUpload','imageDiv')"
                                   style="float: left;"/><br/><br/>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">性别</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="emp_sex">
                                <option value="">--选择--</option>
                                <option value="0" ${empVO.empSex=='0'?'selected':''}>男</option>
                                <option value="1" ${empVO.empSex=='1'?'selected':''}>女</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员类型</label>

                        <div class="col-sm-4">
                            <select class="form-control" id="empType" disabled="true">
                                <option value="0" ${empVO.empType=='0'?'selected':''}>会员</option>
                                <option value="1" ${empVO.empType=='1'?'selected':''}>商家</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">购买等级/打折</label>

                        <div class="col-sm-4">
                            <select class="form-control" name="level_id" id="level_id">
                                <option value="">-- 选择等级 --</option>
                                <c:forEach items="${listLevels}" var="s">
                                    <option value="${s.levelId}" ${s.levelId==empVO.level_id?'selected':''}>${s.levelName}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">


                        <label class="col-sm-2 control-label">分销等级/返利</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="lx_attribute_id">
                                <option value="0" ${empVO.lx_attribute_id=='0'?'selected':''}>普通会员</option>
                                <option value="1" ${empVO.lx_attribute_id=='1'?'selected':''}>分销返利会员</option>
                                <option value="2" ${empVO.lx_attribute_id=='2'?'selected':''}>店长</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">定向卡等级</label>

                        <div class="col-sm-4">
                            <select class="form-control" name="lx_dxk_level_id" id="lx_dxk_level_id">
                                <option value="">-- 选择等级 --</option>
                                <c:forEach items="${listDxk}" var="s">
                                    <option value="${s.lx_dxk_level_id}" ${s.lx_dxk_level_id==empVO.lx_dxk_level_id?'selected':''}>${s.lx_dxk_name}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>


                    <div class="form-group">
                        <label class="col-sm-2 control-label">出生日期</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" class="form-control"
                                   value="${empVO.emp_birthday}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">注册日期</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" class="form-control"
                                   value="${um:format(empVO.dateline, "yyyy-MM-dd HH:mm:ss")}">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">是否禁用</label>
                        <div class="col-sm-4">
                            <select class="form-control" id="is_use">
                                <option value="">--请选择--</option>
                                <option value="0" ${empVO.isUse=='0'?'selected':''}>否</option>
                                <option value="1" ${empVO.isUse=='1'?'selected':''}>是</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">会员经纬度</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" placeholder="经度" id="lat" class="form-control"
                                   value="${empVO.lat}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                            <input type="text" readonly="true" placeholder="纬度" id="lng" class="form-control"
                                   value="${empVO.lng}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">积分</label>

                        <div class="col-sm-4">
                            <input type="text"  readonly="true" class="form-control"
                                   value="${empVO.jfcount}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级手机号</label>

                        <div class="col-sm-4">
                            <input type="text"  readonly="true" class="form-control"
                                   value="${memberVOUpOne.empMobile}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上级昵称</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" class="form-control" value="${memberVOUpOne.empName}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">上上级手机号</label>

                        <div class="col-sm-4">
                            <input type="text"  readonly="true" class="form-control"
                                   value="${memberVOUpTwo.empMobile}" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">上上级昵称</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true"  class="form-control" value="${memberVOUpTwo.empName}"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>



                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveRole('${empVO.empId}')">确定修改
                            </button>
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

    function saveRole(mm_emp_id) {
        var emp_id = $("#emp_id").val();
        var emp_name = $("#emp_name").val();
        var emp_sex = $("#emp_sex").val();
        var level_id = $("#level_id").val();//购买等级  打折用
        var lx_attribute_id = $("#lx_attribute_id").val();//分销等级 返利用
        var lx_dxk_level_id = $("#lx_dxk_level_id").val();
        var is_use = $("#is_use").val();

        if (emp_name.replace(/\s/g, '') == '') {
            alert("名称不能为空");
            return;
        }
        if (emp_sex.replace(/\s/g, '') == '') {
            alert("请选择性别");
            return;
        }

        if (is_use.replace(/\s/g, '') == '') {
            alert("请选择是否禁用");
            return;
        }

        if (lx_attribute_id.replace(/\s/g, '') == '') {
            lx_attribute_id = '0';
        }

        var imagePath = $("img[name='imagePath']").attr("src");
        if (imagePath == "" || imagePath == null) {
            imagePath = $("#emp_cover").val();
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/updateEmpById.do",
            data: {
                "empId": emp_id,
                "empName": emp_name,
                "empSex": emp_sex,
                "empCover": imagePath,
                "level_id": level_id,
                "lx_attribute_id": lx_attribute_id,
                "lx_dxk_level_id": lx_dxk_level_id,
                "isUse": is_use
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    history.go(-1);
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

</script>

<script type="text/javascript">
    function uploadImage(_fileUpload, _imageDiv) {
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

    function deleteImage(e, node) {
        if (e.button == 2) {
            if (confirm("确定移除该图片吗？")) {
                $(node).remove();
            }
        }
    }
    ;


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

    var mapObj = new AMap.Map("mapContainer", {
        rotateEnable: true,
        dragEnable: true,
        zoomEnable: true,
        zooms: [3, 18]
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