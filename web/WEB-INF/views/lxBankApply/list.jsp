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
            <li><a href="javaScript:void(0)">提现管理</a></li>
            <li><a href="javaScript:void(0)">提现申请</a></li>
        </ol>

    </div>
</div>

<div class="row">
    <div class="col-xs-12 col-sm-12">
        <div class="box ui-draggable ui-droppable">
            <div class="box-header">
                <div class="box-name ui-draggable-handle">
                    <i class="fa fa-table"></i>
                    <span>提现申请</span>
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
                <!-- style -->
                <style>
                    .w12 {
                        max-width: 12rem;
                    }
                </style>
                <!-- style -->

                <form class="form-inline">
                    <div class="col-sm-4 col-xs-3">
                        <div class="col-sm-6">
                            <input class="form-control" id="phone_number" value="${query.phoneNumber}" type="text"
                                   placeholder="手机号">
                        </div>
                        <div class="col-sm-6">
                            <input class="form-control" id="keyWords" value="${query.keyWords}" type="text"
                                   placeholder="昵称">
                        </div>
                    </div>

                    <div class="col-sm-4 col-xs-3">
                        <div class="col-sm-6">
                            <select class="form-control w12" id="is_check">
                                <option value="">--是否审核--</option>
                                <option value="0" ${query.is_check=='0'?'selected':''}>未处理</option>
                                <option value="1" ${query.is_check=='1'?'selected':''}>已处理</option>
                                <option value="2" ${query.is_check=='2'?'selected':''}>不通过</option>
                            </select>
                        </div>
                    </div>

                    <div class="col-xs-3 col-sm-2">
                        <i class="fa fa-search"></i>
                        <a href="javascript:void (0);" onclick="nextPage('1')">搜索</a>
                    </div>
                </form>
                <form action="" class="form">
                    <div class="form-group">
                        <div class="col-md-2 col-lg-2">
                            <button type="button" onclick="Daochu_Select()"
                                    class="btn w12 form-control btn-block btn-danger btn-sm">批量导出Excel
                            </button>
                        </div>
                    </div>
                </form>

                <table class="table">
                    <thead>
                    <tr>
                        <th><input type="checkbox" name="allmails" onclick="checkAll()"></th>
                        <th>#</th>
                        <th>会员账号</th>
                        <th>会员姓名</th>
                        <th>会员手机号</th>
                        <th>提现金额</th>
                        <th>银行卡号</th>
                        <th>手机号</th>
                        <th>姓名</th>
                        <th>申请时间</th>
                        <th>审核时间</th>
                        <th>是否审核</th>
                        <th>操作</th>
                        <th></th>
                    </tr>
                    </thead>
                    <tbody>
                    <c:forEach items="${list}" var="e" varStatus="st">
                        <tr>
                            <td><input type="checkbox" id="${e.lx_bank_apply_id}" name="checkbox_one"></td>
                            <td>${st.index+1}</td>
                            <td>${e.emp_number}</td>
                            <td>${e.emp_name}</td>
                            <td>${e.emp_mobile}</td>
                            <td>${e.lx_bank_apply_count}</td>
                            <td>${e.bank_card}</td>
                            <td>${e.bank_mobile}</td>
                            <td>${e.bank_emp_name}</td>
                            <td>${um:format(e.dateline_apply, 'yyyy-MM-dd HH:mm:ss')}</td>
                            <td>${um:format(e.dateline_done, 'yyyy-MM-dd HH:mm:ss')}</td>
                            <td>
                                <c:if test="${e.is_check == '0'}">未处理</c:if>
                                <c:if test="${e.is_check == '1'}">已处理</c:if>
                                <c:if test="${e.is_check == '2'}">不通过</c:if>
                            </td>
                            <td>
                                <button class="btn btn-primary" type="button" onclick="detailEmp('${e.lx_bank_apply_id}','${ e.is_check}')">审核
                                </button>
                            </td>
                        </tr>
                    </c:forEach>
                    </tbody>
                </table>

                <div style="margin-top: 20px;border-top: 1px solid #dedede;padding-bottom:15px; height: 50px">
                    <span style="line-height:28px;margin-top:25px;padding-left:10px; float: left">共${page.count}条/${page.pageCount}页</span>
                    <ul class="pagination" style="padding-left:100px; float: right">
                        <li>
                            <a style="margin-right:20px">每页显示&nbsp;<select name="size" id="size"
                                                                           onchange="nextPage('1')">
                                <option value="10" ${query.size==10?'selected':''}>10</option>
                                <option value="20" ${query.size==20?'selected':''}>20</option>
                                <option value="30" ${query.size==30?'selected':''}>30</option>
                                <option value="100" ${query.size==100?'selected':''}>100</option>
                                <option value="1000" ${query.size==1000?'selected':''}>1000</option>
                            </select>&nbsp;条</a>
                        </li>
                        <c:choose>
                            <c:when test="${page.page == 1}">
                                <li><a href="javascript:void(0)">首页</a></li>
                                <li><a href="javascript:void(0)"><span class="left">《</span></a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('1')">首页</a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page-1}')"><span
                                        class="left">《</span></a></li>
                            </c:otherwise>
                        </c:choose>
                        <li><a style="height: 30px; width: 100px">第<input style="width: 40px;height:20px;" type="text"
                                                                          id="index" name="index"
                                                                          onkeyup="searchIndex(event)"
                                                                          value="${page.page}"/> 页</a></li>

                        <c:choose>
                            <c:when test="${page.page == page.pageCount}">
                                <li><a href="javascript:void(0)"><span class="right">》</span></a></li>
                                <li><a href="javascript:void(0)">末页</a></li>
                            </c:when>
                            <c:otherwise>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.page+1}')"><span
                                        class="right">》</span></a></li>
                                <li><a href="javascript:void(0);" onclick="nextPage('${page.pageCount}')">末页</a></li>
                            </c:otherwise>
                        </c:choose>
                    </ul>
                </div>
            </div>
        </div>
    </div>
</div>

<script type="text/javascript">
    function searchIndex(e) {
        if (e.keyCode != 13) return;
        var _index = $("#index").val();
        var is_check = $("#is_check").val();
        var phone_number = $("#phone_number").val();
        var keyWords = $("#keyWords").val();
        var size = getCookie("contract_size");
        if (_index <= ${page.pageCount} && _index >= 1) {
            window.location.href = "#module=/lxBankApplyController/list&page=" + _index +
            "&size=" + size + "&_t="+ new Date().getTime()+"&is_check="+is_check +"&keyWords=" + keyWords  +"&phoneNumber="+phone_number;
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }
    function nextPage(_page) {
        var page = parseInt(_page);
        var is_check = $("#is_check").val();
        var size = $("#size").val();
        var phone_number = $("#phone_number").val();
        var keyWords = $("#keyWords").val();
        addCookie("contract_size", size, 36);
        if ((page <= ${page.pageCount} && page >= 1) ) {
            window.location.href = "#module=/lxBankApplyController/list&page=" + page + "&size=" + size + "&_t="+ new Date().getTime()
            +"&is_check="+is_check+"&keyWords=" + keyWords  +"&phoneNumber="+phone_number;
        } else {
            alert("请输入1-${page.pageCount}的页码数");
        }
    }


    function Daochu_Select() {
        var select_id = '';
        var select = document.getElementsByName("checkbox_one");
        for (var i = 0; i < select.length; i++) {
            if (select[i].checked == true) {
                select_id = select_id + select[i].id + ',';
            }
        }
        if (select_id == '') {
            alert('请选择数据！');
            return
        } else {
            if (confirm("确定要导出所选择的数据吗？")) {
                $.ajax({
                    url: "/bankApplyExportExcel.do",
                    data: {"ids": select_id},
                    type: "POST",
                    success: function (_data) {
                        var data = $.parseJSON(_data);
                        if (data.success) {
                            window.location.href = "/upload" + data.data;//这样就可以弹出下载对话框了
                        } else {
                            var _case = {1: "导出失败"};
                            alert(_case[data.code])
                        }
                    }
                });
            }
        }
    }

    function checkAll() {
        var all = document.getElementsByName("allmails")[0];
        var select = document.getElementsByName("checkbox_one");
        if (all.checked) {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = true;
            }
        } else {
            for (var i = 0; i < select.length; i++) {
                select[i].checked = false;
            }
        }
    }


    function detailEmp(lx_bank_apply_id,is_check) {
        if(is_check == '0'){
            window.location.href = "#module=/lxBankApplyController/toDetail&lx_bank_apply_id=" + lx_bank_apply_id+ "&_t="+ new Date().getTime();
        }else{
           alert("已处理，不能重复处理！");
        }
    }


</script>
