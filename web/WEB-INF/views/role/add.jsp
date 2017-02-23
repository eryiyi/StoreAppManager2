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
            <li><a href="javascript:void (0);">添加角色</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>添加角色</span>
                </div>
            </div>
            <div class="box-content">
                <h4 class="page-header">角色</h4>

                <form class="form-horizontal" role="form">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">角色名称</label>

                        <div class="col-sm-4">
                            <input type="text" id="role_name" class="form-control" placeholder="角色名称"
                                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
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
                            <button type="button" class="btn btn-primary" onclick="saveRole()">提交</button>
                        </div>
                    </div>
                </form>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    $.ajax({
        url: '/permission/list.do',
        type: 'post',
        success: function (_data) {
            var data = $.parseJSON(_data);
            if (data) {
                var ownPermission = "${sessionScope.powers}";
                var newNode = "";
                for (var i = 0; i < data.length; i++) {
                    var per = data[i];
                    var childNode = "";
                    if (per.child) {
                        for (var j = 0; j < per.child.length; j++) {
                            if (ownPermission.indexOf(per.child[j].id) != -1 || ownPermission.replace("/\s/g", "") == "all") {
                                childNode += "<input type='checkbox' name='permissions' id='" + per.id + j + "' value='" + per.child[j].id + "'>" + per.child[j].name;
                            }
                        }
                    }
                    if (childNode != "") {
                        newNode += "<span><input name='permission' type='checkbox' id='" + per.id + "' onclick=\"checkChild(this)\" value='" + per.id + "'>" + per.name;
                        newNode += childNode;
                        newNode += "</span><br/>";
                    }
                }
                $("#permissions").append(newNode);
            }
        }
    });
    function saveRole() {
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
        $.ajax({
            cache: true,
            type: "POST",
            url: "/role/save.do",
            data: {"name": roleName, "permissions": permissions},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("添加成功");
                    window.location.href = "#module=role/list&page=1";
                } else {
                    var _case = {1: "角色名称不能为空", 2: "保存失败"};
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


