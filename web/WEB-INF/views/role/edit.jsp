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
            <li><a href="javascript:void (0);">角色管理</a></li>
            <li><a href="javascript:void (0);">修改角色</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改角色</span>
                    <span>修改角色</span>
                </div>
            </div>
            <div class="box-content">
                <h4 class="page-header">角色</h4>

                <form class="form-horizontal" role="form">
                    <input type="hidden" value="${role.id}" name="id" id="role_id"/>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色名称</label>

                        <div class="col-sm-4">
                            <input type="text" value="${role.name}" id="role_name" class="form-control"
                                   placeholder="角色名称" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>
                    <div class="form-group">
                        <label class="col-sm-2 control-label">拥有权限</label>

                        <div class="col-lg-8">
                            <div id="permissions"></div>
                        </div>
                    </div>
                    <div class="form-group">
                        <div class="col-sm-9 col-sm-offset-3">
                            <button type="button" class="btn btn-primary" onclick="updateRole()">确认修改</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    Window.Permission = '${role.permissions}';
    $.ajax({
        url: '/permission/list.do',
        type: 'post',
        success: function (_data) {
            var data = $.parseJSON(_data);
            if (data) {
                //console.info(data);
                var ownPermission = "${sessionScope.powers}";
                var permissions = Window.Permission;
                var newNode = "";
                for (var i = 0; i < data.length; i++) {
                    var per = data[i];
                    var childNode = "";
                    var has = "false";
                    var isAll = "true";
                    if (per.child) {
                        for (var j = 0; j < per.child.length; j++) {
                            if (permissions.indexOf(per.child[j].id) != -1 && (ownPermission.indexOf(per.child[j].id) != -1 || ownPermission.replace("/\s/g", "") == "all")) {
                                has = "true";
                                childNode += "<input type='checkbox' checked name='permissions' id='" + per.id + j + "' value='" + per.child[j].id + "'>" + per.child[j].name;
                            } else if (permissions.indexOf(per.child[j].id) != -1) {
                                childNode += "<input type='hidden' name='permissions' value='" + per.child[j].id + "'>";
                            } else if (ownPermission.indexOf(per.child[j].id) != -1 || ownPermission.replace("/\s/g", "") == "all") {
                                has = "true";
                                isAll = "false";
                                childNode += "<input type='checkbox'  name='permissions' id='" + per.id + j + "' value='" + per.child[j].id + "'>" + per.child[j].name;
                            }
                        }
                    }
                    if (childNode != "") {
                        newNode += "";
                        if (has == "true") {
                            newNode += "<span><input name='permission'  type='checkbox'";
                            if (isAll == "true") {
                                newNode += " checked";
                            }
                            newNode += " id='" + per.id + "' onclick=\"checkChild(this)\" value='" + per.id + "'>" + per.name;
                            newNode += childNode;
                            newNode += "</span><br/>";
                        } else {
                            newNode += childNode;
                        }
                    }
                }
                $("#permissions").append(newNode);
            }
        }
    });
    function checkChild(_node) {
        var id = $(_node).val();
        if ($(_node).attr("checked")) {
            $("input[id^='" + id + "']").attr("checked", false);
        } else {
            $("input[id^='" + id + "']").attr("checked", true);
        }
    }
    function updateRole() {
        $("#add_button").attr("disabled", "disabled");
        var roleName = $("#role_name").val();
        if (roleName.replace(/\s/g, '') == '') {
            alert("角色名称不能为空");
            return;
        }
        var permission_ary = new Array();
        $('input[name="permissions"]:checked').each(function () {
            permission_ary.push($(this).val());//向数组中添加元素
        });
        var permissions = permission_ary.join('|');//将数组元素连接起来以构建一个字符串
        if (permissions == null || permissions == '') {
            alert("请选择权限");
            return;
        }

        var roleId = $("#role_id").val();
        $.ajax({
            url: "/role/update.do?_t=" + new Date().getTime(),
            data: {"id": roleId, "name": roleName, "permissions": permissions},
            type: "post",
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改角色成功");
                    window.location.href = "#module=role/list&page=1";
                } else {
                    var _case = {1: "角色名称不能为空", 2: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
</script>