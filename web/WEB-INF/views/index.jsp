<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" trimDirectiveWhitespaces="true" %>
<%@ taglib prefix="um" uri="/unimanager-tags" %>
<meta http-equiv="Content-Type" content="text/html; charset=utf-8">
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="utf-8">
    <title>微微商城后台管理系统</title>
    <meta name="description" content="description">
    <meta name="author" content="DevOOPS">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link href="/plugins/bootstrap/bootstrap.css" rel="stylesheet">
    <link href="/plugins/jquery-ui/jquery-ui.min.css" rel="stylesheet">

    <link href="/plugins/fancybox/jquery.fancybox.css" rel="stylesheet">
    <link href="/plugins/fullcalendar/fullcalendar.css" rel="stylesheet">
    <link href="/plugins/xcharts/xcharts.min.css" rel="stylesheet">
    <link href="/plugins/select2/select2.css" rel="stylesheet">
    <link href="/plugins/justified-gallery/justifiedGallery.css" rel="stylesheet">
    <link href="/css/style_v2.css" rel="stylesheet">
    <link href="/plugins/chartist/chartist.min.css" rel="stylesheet">


    <link href="/plugins/icon/css/style.css" rel="stylesheet">

    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.config.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/ueditor.all.min.js"></script>
    <script type="text/javascript" charset="utf-8" src="/ueditor/lang/zh-cn/zh-cn.js"></script>
    <%--CHART-JS--%>
    <script type="text/javascript" charset="utf-8" src="/js/Chart.bundle.min.js"></script>


    <link rel="stylesheet" href="/ueditor/themes/default/css/ueditor.css" type="text/css">

    <%--<script src="http://getbootstrap.com/docs-assets/js/html5shiv.js"></script>--%>
    <%--<script src="http://getbootstrap.com/docs-assets/js/respond.min.js"></script>--%>

    <link rel="stylesheet" href="http://cache.amap.com/lbs/static/main.css?v=1.0"/>
    <script type="text/javascript"
            src="http://webapi.amap.com/maps?v=1.3&key=7a3a7ab54b57b6bb91263d6959622b91"></script>

</head>
<body>
<!--Start Header-->
<div id="screensaver">
    <canvas id="canvas"></canvas>
    <i class="fa fa-lock" id="screen_unlock"></i>
</div>
<div id="modalbox">
    <div class="devoops-modal">
        <div class="devoops-modal-header">
            <div class="modal-header-name">
                <span>Basic table</span>
            </div>
            <div class="box-icons">
                <a class="close-link">
                    <i class="fa fa-times"></i>
                </a>
            </div>
        </div>
        <div class="devoops-modal-inner">
        </div>
        <div class="devoops-modal-bottom">
        </div>
    </div>
</div>
<header class="navbar">
    <div class="container-fluid expanded-panel">
        <div class="row">
            <div id="logo" class="col-xs-12 col-sm-2">
                <a href="javascript:void(0);"><img src="/img/logo.png" alt="微微商城"/></a>
            </div>
            <div id="top-panel" class="col-xs-12 col-sm-10">
                <div class="row">
                    <div class="col-xs-8 col-sm-4">
                        <%--<div id="search">--%>
                        <%--<input type="text" placeholder="search"/>--%>
                        <%--<i class="fa fa-search"></i>--%>
                        <%--</div>--%>
                    </div>
                    <div class="col-xs-8 col-sm-8 top-panel-right">
                        <%--<a href="javascript:void(0);" class="about">about</a>--%>
                        <%--<a href="javascript:void(0);" class="style1"></a>--%>
                        <ul class="nav navbar-nav pull-right panel-menu">
                            <li class="dropdown">
                                <a href="javascript:void(0);" class="dropdown-toggle account" data-toggle="dropdown">
                                    <div class="avatar">
                                        <img src="/img/avatar.jpg" class="img-circle" alt="avatar"/>
                                    </div>
                                    <i class="fa fa-angle-down pull-right"></i>

                                    <div class="user-mini pull-right">
                                        <span class="welcome">Welcome,</span>
                                        <span>${sessionScope.account.username}</span>
                                    </div>
                                </a>
                                <ul class="dropdown-menu">
                                    <li>
                                        <a href="/logout.do">
                                            <i class="fa fa-power-off"></i>
                                            <span>Logout</span>
                                        </a>
                                    </li>
                                </ul>
                            </li>
                        </ul>
                    </div>
                </div>
            </div>
        </div>
    </div>
</header>
<!--End Header-->
<!--Start Container-->
<div id="main" class="container-fluid">
    <div class="row">
        <div id="sidebar-left" class="col-xs-2 col-sm-2">
            <ul class="nav main-menu">
                <li>
                    <a href="javascript:void(0);" class="active" onclick="toPage('mainPage','')">
                        <i class="fa fa-dashboard"></i>
                        <span class="hidden-xs">主页</span>
                    </a>
                </li>

                <c:if test="${um:permission('LIST_ROLE', sessionScope.powers)||um:permission('ADD_ROLE', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-table"></i>
                            <span class="hidden-xs">角色管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('ADD_ROLE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/role/add','')">添加角色</a></li>
                            </c:if>
                            <c:if test="${um:permission('LIST_ROLE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/role/list','')">角色列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LIST_MANAGER_ADMIN', sessionScope.powers) || um:permission('LIST_MANAGER_SJ', sessionScope.powers) || um:permission('LIST_MANAGER_DL', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-table"></i>
                            <span class="hidden-xs">管理员管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LIST_MANAGER_ADMIN', sessionScope.powers)}">
                                    <li><a href="javascript:void(0);" onclick="toPage('admin/list','1')">管理员</a></li>
                            </c:if>
                            <c:if test="${um:permission('LIST_MANAGER_SJ', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('admin/listSj','1')">商家列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('LIST_MANAGER_DL', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('admin/listDl','1')">代理列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LIST_EMPLOYEE', sessionScope.powers) || um:permission('LX_CARD_EMP_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-table"></i>
                            <span class="hidden-xs">会员管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LIST_EMPLOYEE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('ajax/listMember','1')">会员列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('LX_CARD_EMP_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/cardEmpController/list','1')">定向卡会员</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <%--<li class="dropdown">--%>
                    <%--<a href="#" class="dropdown-toggle">--%>
                        <%--<i class="fa fa-picture-o"></i>--%>
                        <%--<span class="hidden-xs">营业收入</span>--%>
                    <%--</a>--%>
                    <%--<ul class="dropdown-menu">--%>
                        <%--<li><a href="javascript:void(0);" onclick="toPage('/income/list','1')">收入查询</a></li>--%>
                        <%--<li><a href="javascript:void(0);" onclick="toPage('/settlement/list','1')">结算查询</a></li>--%>
                    <%--</ul>--%>
                <%--</li>--%>

                <c:if test="${um:permission('AREA_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">地区管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('AREA_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listProvince','1')">省份列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>


                <c:if test="${um:permission('LIST_CLASS', sessionScope.powers)
                ||um:permission('ADD_CLASS', sessionScope.powers)
                ||um:permission('GOODS_HOT_SMALL_TYPE', sessionScope.powers)
                ||um:permission('LIST_LX_CLASS', sessionScope.powers)
                ||um:permission('ADD_LX_CLASS', sessionScope.powers)
                }">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-list"></i>
                            <span class="hidden-xs">商城管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LIST_CLASS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listType','1')">商城分类列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('ADD_CLASS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('toAddGoodsType','')">添加商城分类</a></li>
                            </c:if>
                            <c:if test="${um:permission('GOODS_HOT_SMALL_TYPE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listHotGoodsType','1')">商城热门分类</a></li>
                            </c:if>
                            <%--<c:if test="${um:permission('GOODS_LIST_CLASS', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);" onclick="toPage('/paopaogoods/listsgoods','1')">商品列表</a></li>--%>
                            <%--</c:if>--%>
                            <c:if test="${um:permission('LIST_LX_CLASS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listLxClassType','1')">入驻分类列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('ADD_LX_CLASS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('toAddLxClass','')">添加入驻分类</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>


                <c:if test="${um:permission('MINE_GOODS_LIST_SHANGCHENG', sessionScope.powers)
                 || um:permission('MINE_GOODS_ADD_MANAGE', sessionScope.powers)
                 || um:permission('MINE_ORDER_LIST_SHANGCHENG', sessionScope.powers)
                 || um:permission('MINE_GOODS_LIST_SJ', sessionScope.powers)
                 || um:permission('MINE_GOODS_COMMENTS', sessionScope.powers)
                 || um:permission('MINE_ORDER_LIST_SJ', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">商品管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('MINE_GOODS_LIST_SHANGCHENG', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPageOne('/paopaogoods/list','1','1')">商城产品列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('MINE_GOODS_LIST_SJ', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPageOne('/paopaogoods/list','1','0')">商家产品列表</a></li>
                            </c:if>
                            <c:if test="${admin.emp_id != null && admin.emp_id != '0'}">
                                <c:if test="${um:permission('MINE_GOODS_ADD_MANAGE', sessionScope.powers)}">
                                    <li><a href="javascript:void(0);" onclick="toPage('/paopaogoods/toAdd','')">发布商品</a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${admin.emp_id != null && admin.emp_id != '0'}">
                                <c:if test="${um:permission('MINE_GOODS_ADD_DXK', sessionScope.powers)}">
                                    <li><a href="javascript:void(0);" onclick="toPage('/paopaogoods/toAddDxk','')">发布定向卡商品</a></li>
                                </c:if>
                            </c:if>
                            <c:if test="${um:permission('MINE_ORDER_LIST_SHANGCHENG', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPageOne('/order/list','1','1')">商城订单</a></li>
                            </c:if>
                            <c:if test="${um:permission('MINE_ORDER_LIST_SJ', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPageOne('/order/list','1','0')">商家订单</a></li>
                            </c:if>
                            <c:if test="${um:permission('MINE_GOODS_COMMENTS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPageOne('/listGoodsComments','1','0')">商品评论</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <%--<c:if test="${um:permission('NOTICE_LIST', sessionScope.powers)||um:permission('ADD_NOTICE_MANA_ADD', sessionScope.powers)}">--%>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle">--%>
                            <%--<i class="fa fa-picture-o"></i>--%>
                            <%--<span class="hidden-xs">公告管理</span>--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<c:if test="${um:permission('NOTICE_LIST', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);" onclick="toPage('listNotice','1')">公告列表</a></li>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${um:permission('ADD_NOTICE_MANA_ADD', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);" onclick="toPage('ajax/toAddNotice','')">添加公告</a></li>--%>
                            <%--</c:if>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</c:if>--%>

                <c:if test="${ um:permission('MANAGER_INFO_WH', sessionScope.powers)|| um:permission('MANAGER_INFO_LIST', sessionScope.powers)|| um:permission('MANAGER_DIANPU_AD', sessionScope.powers)|| um:permission('SHANGJIA_ORDERS', sessionScope.powers)|| um:permission('DIANPU_COMMENT', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">店铺管理</span>
                        </a>
                        <ul class="dropdown-menu">

                            <c:if test="${um:permission('MANAGER_INFO_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('managerinfo/list','1')">店铺列表</a></li>
                            </c:if>

                            <c:if test="${admin.emp_id != null && admin.emp_id != '0'}">
                                <c:if test="${um:permission('MANAGER_INFO_WH', sessionScope.powers)}">
                                    <li><a href="javascript:void(0);" onclick="toPage('managerinfo/toEdit','')">店铺信息维护</a></li>
                                </c:if>

                                <c:if test="${um:permission('MANAGER_DIANPU_AD', sessionScope.powers)}">
                                    <li><a href="javascript:void(0);" onclick="toPage('adObj/list','')">店铺广告列表</a></li>
                                </c:if>
                            </c:if>

                            <c:if test="${um:permission('SHANGJIA_ORDERS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/order/listMine','1')">我的订单</a></li>
                            </c:if>
                            <c:if test="${um:permission('DIANPU_COMMENT', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/listDianpuComments','1')">店铺评论</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('FENSI_LIST', sessionScope.powers)|| um:permission('ADD_NOTICE_TO_FENSI', sessionScope.powers)|| um:permission('NOTICE_Manager_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">我的粉丝</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('FENSI_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);"
                                       onclick="toPage('/mineFensiController/list','1')">粉丝管理</a></li>
                            </c:if>
                            <%--<c:if test="${um:permission('ADD_NOTICE_TO_FENSI', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);"--%>
                                       <%--onclick="toPage('ajax/toAddNoticeDianpu','1')">发公告</a></li>--%>
                            <%--</c:if>--%>
                            <%--<c:if test="${um:permission('NOTICE_Manager_LIST', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);" onclick="toPage('listNotice','1')">公告列表</a></li>--%>
                            <%--</c:if>--%>
                        </ul>
                    </li>
                </c:if>


                <%--<c:if test="${um:permission('REMIT_SELLERLIST', sessionScope.powers)|| um:permission('REMIT_CONTRACTLIST', sessionScope.powers)}">--%>
                    <%--<li class="dropdown">--%>
                        <%--<a href="#" class="dropdown-toggle">--%>
                            <%--<i class="fa fa-picture-o"></i>--%>
                            <%--<span class="hidden-xs">汇款结算</span>--%>
                        <%--</a>--%>
                        <%--<ul class="dropdown-menu">--%>
                            <%--<c:if test="${um:permission('REMIT_SELLERLIST', sessionScope.powers)}">--%>
                                <%--<li><a href="javascript:void(0);"--%>
                                       <%--onclick="toPage('/settlement/sellerlist','1')">商家结算</a></li>--%>
                            <%--</c:if>--%>
                        <%--</ul>--%>
                    <%--</li>--%>
                <%--</c:if>--%>

                <c:if test="${um:permission('PAIHANG_SHANGHU_MANAGE', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-thumbs-up "></i>
                            <span>商品推荐管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('PAIHANG_SHANGHU_MANAGE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/paihang/list','1')">商品推荐管理</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('PAIHANG_DIANPU_SHANGHU_MANAGE', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-thumbs-up "></i>
                            <span>店铺推荐管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('PAIHANG_DIANPU_SHANGHU_MANAGE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/paihangDianpuController/list','1')">店铺推荐管理</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('ADVERT_LIST', sessionScope.powers)||um:permission('ADD_ADVERT_MANAGER', sessionScope.powers)}">
                    <li class="dropdown">
                    <a href="#" class="dropdown-toggle">
                    <i class="fa fa-pencil-square-o"></i>
                    <span class="hidden-xs">广告管理</span>
                    </a>
                    <ul class="dropdown-menu">
                    <c:if test="${um:permission('ADVERT_LIST', sessionScope.powers)}">
                    <li><a href="javascript:void(0);" onclick="toPage('lxAdController/list','1')">广告列表</a></li>
                    </c:if>
                    <c:if test="${um:permission('ADD_ADVERT_MANAGER', sessionScope.powers)}">
                    <li><a href="javascript:void(0);" onclick="toPage('lxAdController/add','')">添加广告</a></li>
                    </c:if>
                    </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('UPDATE_VERSION_CODE_MAANGER_AD_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">版本管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('UPDATE_VERSION_CODE_MAANGER_AD_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/versionCodeController/toEdit','')">版本管理</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('TEL_KEFU_MANAGE', sessionScope.powers)||um:permission('ADD_TEL_KEFU_MANAGE', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="javascript:void (0);" class="dropdown-toggle">
                            <i class="fa fa-phone"></i>
                            <span>客服管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('ADD_TEL_KEFU_MANAGE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/kefu/add','')">添加客服</a></li>
                            </c:if>
                            <c:if test="${um:permission('TEL_KEFU_MANAGE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/kefu/list','1')">客服列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('POINT_LIST', sessionScope.powers)||um:permission('ADD_POINT', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">积分管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('JIFEN_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listCount','1')">积分列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('EDIT_JIFEN_GUIZE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('toEditJifenGuize','')">积分规则</a></li>
                            </c:if>
                            <c:if test="${um:permission('JIFEN_ADD_DELETE_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/countRecordController/listRecords','1')">积分变动记录</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LX_CARD_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span class="hidden-xs">定向卡维护</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LX_CARD_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/lxCardObjController/list','1')">定向卡维护</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LX_XIAOFEI_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span class="hidden-xs">消费记录</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LX_XIAOFEI_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/lxConsumptionController/list','1')">消费记录</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LX_BANK_APPLY_LIST', sessionScope.powers) || um:permission('LX_BANK_APPLY_ADD', sessionScope.powers) || um:permission('LX_BANK_APPLY_MINE', sessionScope.powers) }">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span class="hidden-xs">提现管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LX_BANK_APPLY_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/lxBankApplyController/list','1')">提现申请</a></li>
                            </c:if>
                            <c:if test="${um:permission('LX_BANK_APPLY_ADD', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/lxBankApplyController/toAdd','')">申请提现</a></li>
                            </c:if>
                            <c:if test="${um:permission('LX_BANK_APPLY_MINE', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/lxBankApplyController/listMine','1')">我的提现</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('LEVEL_LIST', sessionScope.powers)||um:permission('LX_ATTRIBUTE_LIST', sessionScope.powers)||um:permission('LX_DXK_LEVEL_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span class="hidden-xs">会员等级维护</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('LEVEL_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('listLevel','1')">会员打折等级列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('LX_ATTRIBUTE_LIST', sessionScope.powers)}">
                            <li><a href="javascript:void(0);" onclick="toPage('/lxAttributeController/list','1')">会员分销等级列表</a></li>
                            </c:if>
                            <c:if test="${um:permission('LX_DXK_LEVEL_LIST', sessionScope.powers)}">
                            <li><a href="javascript:void(0);" onclick="toPage('/lxDxkLevelController/list','1')">定向卡等级列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('WELCOME_AD', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-desktop"></i>
                            <span class="hidden-xs">欢迎页广告</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('WELCOME_AD', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/loadPicController/list','1')">广告列表</a></li>
                            </c:if>

                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('ADD_NOTICE_HOUTAI', sessionScope.powers) || um:permission('NOTICE_HOUTAI_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">后台帮助</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('ADD_NOTICE_HOUTAI', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/toAddNoticeHoutai','')">添加帮主</a></li>
                            </c:if>
                            <c:if test="${um:permission('NOTICE_HOUTAI_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/listNoticeHoutai','1')">帮助列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('DXK_AD_LIST', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">定向卡广告管理</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('DXK_AD_LIST', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('/dxkAdController/list','1')">广告列表</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

                <c:if test="${um:permission('MODIFY_PASS', sessionScope.powers)}">
                    <li class="dropdown">
                        <a href="#" class="dropdown-toggle">
                            <i class="fa fa-picture-o"></i>
                            <span class="hidden-xs">系统设置</span>
                        </a>
                        <ul class="dropdown-menu">
                            <c:if test="${um:permission('MODIFY_PASS', sessionScope.powers)}">
                                <li><a href="javascript:void(0);" onclick="toPage('toChangePass','')">修改密码</a></li>
                            </c:if>
                        </ul>
                    </li>
                </c:if>

            </ul>
        </div>
        <!--Start Content-->
        <div id="content" class="col-xs-12 col-sm-10">
            <div id="about">
                <div class="about-inner">
                    <h4 class="page-header">Open-source admin theme for you</h4>

                    <p>DevOOPS team</p>

                    <p>Homepage - <a href="http://devoops.me" target="_blank">http://devoops.me</a></p>

                    <p>Email - <a href="mailto:devoopsme@gmail.com">devoopsme@gmail.com</a></p>

                    <p>Twitter - <a href="http://twitter.com/devoopsme" target="_blank">http://twitter.com/devoopsme</a>
                    </p>

                    <p>Donate - BTC 123Ci1ZFK5V7gyLsyVU36yPNWSB5TDqKn3</p>
                </div>
            </div>
            <div class="preloader">
                <img src="/img/devoops_getdata.gif" class="devoops-getdata" alt="preloader"/>
            </div>
            <div id="ajax-content"></div>
        </div>
        <!--End Content-->
    </div>
</div>
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

</body>
<script type="text/javascript">
    (function (window, undefined) {
        var currentHash;

        function decodeChineseWords(params) {
            if (params["cn"] == undefined || params["cn"] == "") {
                return;
            }
            var cns = params["cn"].split(","), i;
            for (i = 0; i < cns.length; i++) {
                params[cns[i]] = decodeURIComponent(params[cns[i]]);
            }
        }

        function checkHash() {
            var newHash = window.location.hash;
            if (newHash == "") {
                window.location.hash = "#module=mainPage";
                return;
            }
            if (newHash == currentHash) return;
            currentHash = newHash;
            var paramsString = currentHash.substring(1);
            var paramsArray = paramsString.split("&");
            var params = {};
            for (var i = 0; i < paramsArray.length; i++) {
                var tempArray = paramsArray[i].split("=");
                params[tempArray[0]] = tempArray[1];
            }
            var _url = params["module"].replace(/\./g, "/") + ".do?_t=" + new Date().getTime();
            delete params["module"];
            decodeChineseWords(params);
            $.ajax({
                url: _url, type: "post", data: params, success: function (data) {
                    var editor;
                    while ((editor = Util.editors.shift()) != undefined) {
                        editor.destroy();
                    }
                    $("#content").html(data);
                }
            });
        }

        window.setInterval(checkHash, 100);
    })(window);
    function toPage(_url, _page) {
        if (_page != '') {
            window.location.href = "#module=" + _url + "&page=" + _page + "&_t="+ new Date().getTime();
        } else {
            window.location.href = "#module=" + _url+ "&_t="+ new Date().getTime();
        }
    }


    function toPageOne(_url, _page, is_zhiying) {
        window.location.href = "#module=" + _url + "&page=" + _page + "&is_zhiying=" + is_zhiying + "&_t="+ new Date().getTime();
    }

    function loadNotice() {
        window.location.href = "/#/ajax/toAddNotice.do"
        window.location.reload();
    }
</script>
</html>
