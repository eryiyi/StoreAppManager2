<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
  <title>注册微微商城</title>
  <meta http-equiv="X-UA-Compatible" content="IE=Edge,chrome=1">
  <meta name="viewport"
        content="width=device-width, user-scalable=no, initial-scale=1.0, maximum-scale=1.0, minimum-scale=1.0">
  <link rel="stylesheet" href="/css/goods_detail.css">
  <link rel="stylesheet" href="/css/reset.css">
  <link href="/plugins/bootstrap/bootstrap.css" rel="stylesheet">
  <link href="/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">
  <link href="http://maxcdn.bootstrapcdn.com/font-awesome/4.2.0/css/font-awesome.min.css" rel="stylesheet">
  <link href="/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
  <link href="/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
  <link href="/plugins/xcharts/xcharts.min.css" rel="stylesheet">
  <link href="/plugins/select2/select2.css" rel="stylesheet">
  <link href="/plugins/justified-gallery/justifiedGallery.css" rel="stylesheet">
  <link href="/css/style_v2.css" rel="stylesheet">
  <link href="/plugins/chartist/chartist.min.css" rel="stylesheet">

  <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
  <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
  <link rel="stylesheet" href="/ueditor/themes/default/css/ueditor.css" type="text/css">


  <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>

  <script src="/plugins/jquery/jquery.min.js"></script>
  <script src="/plugins/jquery-ui/jquery-ui.min.js"></script>
  <script src="/plugins/bootstrap/bootstrap.min.js"></script>
  <script src="/plugins/justified-gallery/jquery.justifiedGallery.min.js"></script>
  <script src="/plugins/tinymce/tinymce.min.js"></script>
  <script src="/plugins/tinymce/jquery.tinymce.min.js"></script>
  <script src="/js/devoops.js"></script>
  <script src="/js/china2.js"></script>
  <script type="text/javascript" src="/js/md5.js"></script>
  <script type="text/javascript" src="/js/cookie.js"></script>
  <script type="text/javascript" src="/js/ajaxfileupload.js"></script>
  <script type="text/javascript" src="/js/Util.js"></script>

</head>
<body>
<div class="container">
  <div class="good-info">
    <h2 class="good-name">欢迎注册微微商城</h2>
    <form class="form-horizontal" role="form">
      <input type="hidden" id="emp_up" name="emp_up" value="${member.empMobile}">
      <div class="person clearfix">

        <div class="form-group">
          <label class="col-sm-2 control-label">手机号</label>

          <div class="col-sm-4">
            <input type="text" id="username"  class="form-control" placeholder="请输入手机号"
                    data-toggle="tooltip" data-placement="bottom"
                   title="Tooltip for name">
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 control-label">密码</label>

          <div class="col-sm-4">
            <input type="text" id="pwr" class="form-control" placeholder="请输入密码"
                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 control-label">确认密码</label>

          <div class="col-sm-4">
            <input type="text" id="pwrsure" class="form-control" placeholder="请输入密码"
                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
          </div>
        </div>

        <div class="form-group">
          <label class="col-sm-2 control-label">推荐人手机号</label>

          <div class="col-sm-4">
            <input type="text" readonly="true" class="form-control"  value="${member.empMobile}"
                   data-toggle="tooltip" data-placement="bottom" title="Tooltip for name">
          </div>
        </div>

        <div class="form-group">
          <div class="col-sm-9 col-sm-offset-3">
            <button type="button" class="btn btn-primary" onclick="save()">提交</button>
          </div>
        </div>

      </div>
   </form>

  </div>
  <%--<div class="goods-detail">${vo.cont}</div>--%>
  <%--<div class="blank" style="height: 100px"><br></div>--%>
</div>
<%--<a href="http://a.app.qq.com/o/simple.jsp?pkgname=com.lbins.myapp">--%>
  <%--<img class="download-button" src="/img/share_download.png" alt="下载微微商城手机客户端">--%>
<%--</a>--%>
</body>
</html>
<script type="text/javascript">

  function save() {
    var emp_up = $("#emp_up").val();
    var username = $("#username").val();
    var pwr = $("#pwr").val();
    var pwrsure = $("#pwrsure").val();
    if (emp_up.replace(/\s/g, '') == '') {
      emp_up = '10000000000';
    }
    if (username.replace(/\s/g, '') == '') {
      alert("请输入您的手机号！");
      return;
    }
    if (pwr.replace(/\s/g, '') == '') {
      alert("请输入您的密码！");
      return;
    }
    if (pwr.length > 18 || pwr.length < 6) {
      alert("密码长度在6到18位之间！");
      return;
    }
    if (pwrsure.replace(/\s/g, '') == '') {
      alert("请输入您的确认密码！");
      return;
    }

    if(pwr != pwrsure){
      alert("两次输入密码不一致！");
      return;
    }

    $.ajax({
      cache: true,
      type: "POST",
      url: "/saveEmpShare.do",
      data: {
        "emp_up_mobile": emp_up,
        "empMobile": username,
        "empPass": pwr
      },
      async: false,
      success: function (_data) {
        var data = $.parseJSON(_data);
        if (data.success) {
          alert("注册成功");
          window.location.href = "http://a.app.qq.com/o/simple.jsp?pkgname=com.lbins.myapp";
        } else {
          var _case = {1: "手机号已经注册了，换个试试", 2: "注册失败，请稍后重试"};
          alert(_case[data.code])
        }
      }
    });
  };

</script>