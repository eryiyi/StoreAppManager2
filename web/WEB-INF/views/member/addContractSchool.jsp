<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<div class="row">
    <div id="breadcrumb" class="col-xs-12">
        <a href="#" class="show-sidebar">
            <i class="fa fa-bars"></i>
        </a>
        <ol class="breadcrumb pull-left">
            <li><a href="javaScript:void(0)">主页</a></li>
            <li><a href="#">会员管理</a></li>
            <li><a href="#">添加承包商</a></li>
        </ol>

    </div>
</div>

<div class="row">
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
            <div class="box-content">
                <h4 class="page-header">承包商设置</h4>

                <form class="form-horizontal">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">承包商设置</label>

                        <div class="col-sm-6">
                            <c:if test="${memberVo.empTypeId == '3'}"><a href="javascript:void(0);"
                                                                         onclick="setContractUser('${memberVo.empId}', '${memberVo.empTypeId}')">取消为承包商</a></c:if>
                            <c:if test="${memberVo.empTypeId == '0'}"><a href="javascript:void(0);"
                                                                         onclick="setContractUser('${memberVo.empId}', '${memberVo.empTypeId}')">设置为承包商</a></c:if>
                            <c:if test="${memberVo.empTypeId == '1'}"><a href="javascript:void(0);"
                                                                         onclick="setContractUser('${memberVo.empId}', '${memberVo.empTypeId}')">设置为承包商</a></c:if>
                            <c:if test="${memberVo.empTypeId == '2'}"><a href="javascript:void(0);"
                                                                         onclick="setContractUser('${memberVo.empId}', '${memberVo.empTypeId}')">设置为承包商</a></c:if>
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
            <div class="box-content">
                <h4 class="page-header">承包学校信息</h4>

                <table class="table">
                    <thead>
                    <tr>
                        <th>#</th>
                        <th>昵称</th>
                        <th>手机号</th>
                        <th>性别</th>
                        <th>承包学校</th>
                        <th>到期时间</th>
                        <th>管理</th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td>${st.index+1}</td>
                            <td>${memberVo.empName}</td>
                            <td>${memberVo.empMobile}</td>
                            <td>
                                <c:if test="${memberVo.empSex == '0'}">男</c:if>
                                <c:if test="${memberVo.empSex == '1'}">女</c:if>
                            </td>
                            <td>${e.schoolName}</td>
                            <td>${um:format(e.endTime, "yyyy-MM-dd HH:mm:ss")}</td>
                            <td><a href="javascript:void(0);" onclick="deleteContractSchool('${e.id}')">删除</a></td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>
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
            <div class="box-content">
                <h4 class="page-header">添加学校</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">省份名称</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="university" id="s2_province"
                                    onchange="selectColleges();">
                                <option value="">-- 选择省份 --</option>
                                <c:forEach items="${provinces}" var="s">
                                    <option value="${s.provinceId}" ${query.schoolId==s.provinceId?'selected':''} >${s.pname}</option>
                                </c:forEach>
                            </select>
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">学校名称</label>

                        <div class="col-sm-4">
                            <select class="populate placeholder" name="university" id="s2_country">
                                <option value="">-- 选择学校 --</option>
                            </select>
                        </div>
                    </div>
                    <div class="form-group has-feedback">
                        <label class="col-sm-2 control-label">过期时间</label>

                        <div class="col-sm-2">
                            <input type="text" id="input_date" class="form-control" placeholder="Date">
                            <span class="fa fa-calendar txt-danger form-control-feedback"></span>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="saveContractSchool()">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>
<%--<div class="col-xs-4 col-sm-4">--%>
<script type="text/javascript">
    $(document).ready(function () {
        // Initialize datepicker
        $('#input_date').datepicker({setDate: new Date()});
        WinMove();
    });

    function saveContractSchool() {
        var schoolId = $("#s2_country").val();
        if (schoolId == "") {
            alert("请选择学校");
            return;
        }
        var endTime = $("#input_date").val();
        if (endTime == "") {
            alert("请选择过期时间");
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/addContractSchool.do",
            data: {"schoolId": schoolId, "endTime": endTime, "empId": '${memberVo.empId}'},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("添加成功");
                    window.location.href = "#module=toAddContractSchool&empId=" + '${memberVo.empId}' + "&_time=" + new Date().getTime();
                } else {
                    var _case = {1: "添加失败", 2: "该学校已被承包"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;
    function selectColleges() {
        var colleges =${colleges};
        var province = $("#s2_province").val();
        var ret = '';
        for (var i = colleges.length - 1; i >= 0; i--) {
            if (colleges[i].provinceID == province) {
                ret += "<option value='" + colleges[i].coid + "'>" + colleges[i].name + "</option>";
            }
        }
        $("#s2_country").html(ret);
    }
    ;

    function deleteContractSchool(_id) {
        if (!confirm("确实要删除该学校么？")) {
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/deleteContractSchool.do",
            data: {"id": _id},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("删除成功");
                    window.location.href = "#module=toAddContractSchool&empId=" + '${memberVo.empId}' + "&_time=" + new Date().getTime();
                } else {
                    var _case = {1: "删除失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

    function setContractUser(_empId, _empTypeId) {
        if (_empTypeId == '3') {
            if (confirm("确定取消该承包商吗？")) {
                setContract(_empId, _empTypeId);
            }
        } else {
            if (confirm("确定将该用户设置为承包商吗？")) {
                setContract(_empId, _empTypeId);
            }
        }
    }
    ;
    function setContract(_empId, _empTypeId) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/setContractUser.do",
            data: {"empId": _empId, "empTypeId": _empTypeId},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("设置成功");
                    window.location.href = "#module=toAddContractSchool&empId=" + '${memberVo.empId}' + "&_time=" + new Date().getTime();
                } else {
                    var _case = {1: "设置失败"};
                    alert(_case[data.code])
                }
            }
        });
    }

</script>


