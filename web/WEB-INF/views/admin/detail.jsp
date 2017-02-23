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
            <li><a href="javascript:void (0);">管理员</a></li>
            <li><a href="javascript:void (0);">管理员详情</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>管理员详情</span>
                </div>
            </div>
            <div class="box-content">
                <h4 class="page-header">管理员</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户名</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" id="username" name="username"
                                   class="form-control" value="${admin.username}" data-toggle="tooltip"
                                   data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户手机号</label>

                        <div class="col-sm-4">
                            <input type="text" readonly="true" id="empMobile" name="mm_manager_mobile"
                                   class="form-control" value="${admin.empMobile}" data-toggle="tooltip"
                                   data-placement="bottom" title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">用户密码</label>

                        <div class="col-sm-4">
                            <input type="text" id="password" name="password" class="form-control"
                                   placeholder="新密码" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <%--<div class="form-group">--%>
                        <%--<label class="col-sm-2 control-label">角色</label>--%>

                        <%--<div class="col-lg-8">--%>

                            <%--<c:if test="${roleRname != null}">--%>
                                <%--<div id="rname">${roleRname}</div>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${role != null}">--%>
                                <%--<div id="rname">${role.name}</div>--%>
                            <%--</c:if>--%>

                        <%--</div>--%>
                    <%--</div>--%>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">状态</label>

                        <div class="col-lg-8">
                            <c:if test="${admin.isUse=='0'}">
                                <div id="status">启用</div>
                            </c:if>
                            <c:if test="${admin.isUse=='1'}">
                                <div id="status">禁用</div>
                            </c:if>
                            <c:if test="${admin.isUse=='2'}">
                                <div id="status">删除</div>
                            </c:if>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="updatePwr('${admin.id}')">
                                确定修改
                            </button>
                            <button type="button" class="btn btn-primary"
                                    onclick="manageEmp('${admin.id}','0')">启用
                            </button>
                            <button type="button" class="btn btn-primary"
                                    onclick="manageEmp('${admin.id}','1')">禁用
                            </button>
                            <button type="button" class="btn btn-primary"
                                    onclick="manageEmp('${admin.id}','2')">删除
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
    function updatePwr(id) {
        var password = $("#password").val();
        if (password.replace(/\s/g, '') == '') {
            alert("密码不能为空");
            return;
        }
        if (password.length < 6 || password.length > 18) {
            alert("密码长度至少6位,最多18位");
            return;
        }
        password = hex_md5(password);
        $.ajax({
            cache: true,
            type: "POST",
            url: "/changePass.do",
            data: {
                "password": password,
                "id": id
            },
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=admin/list&page=1"+ "&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

    function manageEmp(_id, _type) {
        $.ajax({
            cache: true,
            type: "POST",
            url: "/admin/updateType.do",
            data: {"id": _id, "is_use": _type},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("操作成功");
                    window.location.href = "#module=admin/list&page=1"+ "&_t=" + new Date().getTime();
                } else {
                    var _case = {1: "操作失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
    ;

    //
    function checkChild(_node) {
        var id = $(_node).val();//这个是点击的那个checkbox的id值，就是最前面那个checkbox
        if ($(_node).attr("checked")) {//要是这个地方选中的话，点击的时候把他后面的那些都置为不选中
            //这个选择是以【id】为开始的那些id的属性checked修改   明白不啊？不啊 擦擦擦
            $("input[id^='" + id + "']").attr("checked", false);
        } else {
            $("input[id^='" + id + "']").attr("checked", true);
        }
    }
    ;
</script>


