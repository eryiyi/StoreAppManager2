<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<script type="text/javascript" src="/js/Util.js"></script>
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
            <li><a href="javaScript:void(0)">公告管理</a></li>
            <li><a href="javaScript:void(0)">修改公告</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>修改公告表单</span>
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
                <h4 class="page-header">公告详情</h4>

                <form id="save_form" class="form-horizontal" method="post" role="form">
                    <input id="notice_id" type="hidden" value="${notice.id}">

                    <div class="form-group">
                        <label class="col-sm-2 control-label">标题</label>

                        <div class="col-sm-4">
                            <input type="text" id="notice_title" value="${notice.title}" class="form-control"
                                   placeholder="标题" data-toggle="tooltip" data-placement="bottom"
                                   title="Tooltip for name">
                        </div>
                    </div>

                    <div class="form-group">
                        <label class="col-sm-2 control-label">内容</label>

                        <div class="col-sm-8">
                            <script id="editor" name="content" type="text/plain"
                                    style="width:75%;height:150px;">${notice.content}</script>
                        </div>
                    </div>

                    <div class="form-group">
                        <div class="col-sm-2 col-sm-offset-2">
                            <button type="button" class="btn btn-primary" onclick="save();">
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

    function save() {
        var title = $("#notice_title").val();
        var content = UE.getEditor('editor').getContent();
        var noticeId = $("#notice_id").val();

        if (title.replace(/\s/g, '') == '') {
            alert("请输入公告标题");
            return;
        }
        if (content.replace(/\s/g, '') == '') {
            alert("请输入公告内容");
            return;
        }
        $.ajax({
            cache: true,
            type: "POST",
            url: "/updateNotice.do",
            data: {"title": title, "content": content, "id": noticeId},
            async: false,
            success: function (_data) {
                var data = $.parseJSON(_data);
                if (data.success) {
                    alert("修改成功");
                    window.location.href = "#module=listNotice&_t=" + new Date().getTime();
//                    $.ajax({
//                        type: "GET",
//                        url: "/listNotice.do",
//                        success: function(response){
//                            $("#content").html(response);
//                        }
//
//                    });
//                    history.pushState('', 'New URL: '+"/listNotice.do", "/listNotice.do");

                } else {
                    var _case = {1: "修改失败"};
                    alert(_case[data.code])
                }
            }
        });
    }
</script>


