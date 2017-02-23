<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
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
            <li><a href="javaScript:void(0)">店铺评论管理</a></li>
            <li><a href="javaScript:void(0)">评论详情</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box">
            <div class="box-header">
                <div class="box-name">
                    <i class="fa fa-search"></i>
                    <span>评论详情</span>
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
                <h4 class="page-header">评论详情</h4>
                <dl class="dl-horizontal">
                    <dt>店铺名称</dt>
                    <dd>${vo.company_name}</dd>
                </dl>

                <dl class="dl-horizontal">
                    <dt>评论人手机号</dt>
                    <dd>${vo.emp_mobile}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>评论人昵称</dt>
                    <dd>${vo.emp_name}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>评论内容</dt>
                    <dd>${vo.dianpu_comment_cont}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>评论星级</dt>
                    <dd>${vo.starNumber}</dd>
                </dl>
                <dl class="dl-horizontal">
                    <dt>评论时间</dt>
                    <dd>${vo.comment_dateline}</dd>
                </dl>

                <c:forEach items="${arrs}" var="e" varStatus="st">
                    <div class="form-group">
                        <label class="col-sm-2 control-label">图片</label>
                        <div class="col-sm-10">
                            <div id="imageDiv" style="padding: 10px">
                                <script type="text/javascript">
                                    var imagePath = '${e}';
                                    if (imagePath != null && imagePath != "") {
                                        var html = '<img style="cursor: pointer" onmousedown="deleteImage(event, this)" src="' + imagePath + '" width="150" height="150" name="imagePath" title=""/>';
                                        $("#imageDiv").html(html);
                                    }
                                </script>
                            </div>
                        </div>
                    </div>
                </c:forEach>
                <div class="form-group">
                    <div class="col-sm-9 col-sm-offset-3">
                        <button type="button" class="btn btn-primary" onclick="javascript :history.back(-1)">返回
                        </button>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">

</script>

