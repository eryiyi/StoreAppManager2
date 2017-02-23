package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

/**
 *
 */
public class ControllerInterceptor extends ControllerConstants implements HandlerInterceptor {
    @Override
    public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object o) throws Exception {
        String uri = request.getRequestURI();
        Object account = request.getSession().getAttribute(ACCOUNT_KEY);

        if(uri.contains(".json")){
                return true;
        }

        if(uri.matches("(^/$)|(^/index\\.do$)|(^/adminLogin\\.do$)|(^/logout\\.do$)" +
                "|(^/uploadImage\\.do$)" +
                "|(^/uploadUnCompressImage\\.do$)" +
                "|(^/recordList\\.do$)" +
                "|(^/goodsTypeList\\.do$)" +
                "|(^/goodsTypeList2\\.do$)"

                +"|(^/saveGoodsFavour\\.do$)"
                +"|(^/listFavour\\.do$)"
                +"|(^/deleteFavour\\.do$)"
                +"|(^/saveShoppingAddress\\.do$)"
                +"|(^/listShoppingAddress\\.do$)"
                +"|(^/deleteShoppingAddress\\.do$)"
                +"|(^/updateShoppingAddress\\.do$)"
                +"|(^/getSingleAddressByEmpId\\.do$)"
                +"|(^/orderSave\\.do$)"
                +"|(^/orderUpdate\\.do$)"
                +"|(^/listOrders\\.do$)"
                +"|(^/getSingleAddressByAddressId\\.do$)"
                +"|(^/updateOrder\\.do$)"
                +"|(^/orderSaveSingle\\.do$)"
                +"|(^/orderUpdateSingle\\.do$)"
                +"|(^/appGetProvince\\.do$)"
                +"|(^/appGetCity\\.do$)"
                +"|(^/appGetArea\\.do$)"
                +"|(^/paopaogoods/listGoods\\.do$)"
                +"|(^/paopaogoods/findById\\.do$)"
                +"|(^/paopaogoods/detail\\.do$)"
                +"|(^/viewpager/appList\\.do$)"
                +"|(^/selectOrderNum\\.do$)"
                +"|(^/listOrdersMng\\.do$)"
                +"|(^/cancelOrderSave\\.do$)"
                +"|(^/paopaogoods/updatePosition\\.do$)"
                +"|(^/paopaogoods/toGoodsContent\\.do$)"
                +"|(^/findOrderByOrderNo\\.do$)"
                +"|(^/paopaogoods/delete\\.do$)"


                +"|(^/appGetGoodsType\\.do$)"
                +"|(^/memberRegister\\.do$)"
                +"|(^/memberLogin\\.do$)"
                +"|(^/updatePushId\\.do$)"
                +"|(^/resetMobile\\.do$)"
                +"|(^/resetPass\\.do$)"
                +"|(^/modifyMember\\.do$)"
                +"|(^/getMemberByMobile\\.do$)"
                +"|(^/modifyMemberSex\\.do$)"
                +"|(^/modifyMemberBirth\\.do$)"
                +"|(^/modifyMemberPayPass\\.do$)"
                +"|(^/listProvince\\.do$)"
                +"|(^/sendLocation\\.do$)"
                +"|(^/saveManagerInfo\\.do$)"
                +"|(^/appGetNearbyDianpu\\.do$)"
                +"|(^/getIndexTuijian\\.do$)"
                +"|(^/appGetDianpuDetailByEmpId\\.do$)"
                +"|(^/appGetAdEmp\\.do$)"
                +"|(^/saveGoodsComment\\.do$)"
                +"|(^/listGoodsComment\\.do$)"
                +"|(^/appGetAdByType\\.do$)"
                +"|(^/appGetDianpuFavour\\.do$)"
                +"|(^/saveDianpuFavour\\.do$)"
                +"|(^/deleteDianpuFavour\\.do$)"
                +"|(^/appGetBankCards\\.do$)"
                +"|(^/appSaveBankCards\\.do$)"
                +"|(^/deleteBankCard\\.do$)"
                +"|(^/appGetPackage\\.do$)"
                +"|(^/getVersionCode\\.do$)"

                +"|(^/appSaveBrowsing\\.do$)"
                +"|(^/appGetBrowsing\\.do$)"
                +"|(^/orderSaveWx\\.do$)"
                +"|(^/getKefuTel\\.do$)"
                +"|(^/appShareReg\\.do$)"
                +"|(^/saveEmpShare\\.do$)"
                +"|(^/orderSaveSingleWx\\.do$)"
                +"|(^/scanOrder\\.do$)"
                +"|(^/appGetCountRecord\\.do$)"
                +"|(^/appGetCount\\.do$)"
                +"|(^/appGetConsumption\\.do$)"

                +"|(^/appSaveBankApply\\.do$)"
                +"|(^/appGetBankApply\\.do$)"
                +"|(^/appGetLikes\\.do$)"
                +"|(^/appGetCardEmp\\.do$)"
                +"|(^/appSaveDxkOrder\\.do$)"

                +"|(^/appGetCountComment\\.do$)"
                +"|(^/orderSaveLq\\.do$)"
                +"|(^/orderSaveSingleLq\\.do$)"
                +"|(^/appGetCountCommentDianpu\\.do$)"
                +"|(^/orderUpdateComment\\.do$)"
                +"|(^/listDianpuComment\\.do$)"
                +"|(^/appToChongzhiDxk\\.do$)"
                +"|(^/appChongzhiDxk\\.do$)"
                +"|(^/appListNotice\\.do$)"
                +"|(^/viewNotice\\.do$)"
                +"|(^/getPaihangDianpu\\.do$)"

                +"|(^/appLqPayZfb\\.do$)"
                +"|(^/appLqPayWx\\.do$)"
                +"|(^/appUpdateLqCz\\.do$)"
                +"|(^/findPwrByMobile\\.do$)"

                +"|(^/appGetGoodsTypeSmall\\.do$)"
                +"|(^/appGetLxClass\\.do$)"
                +"|(^/listMineEmps\\.do$)"
                +"|(^/MineFensiCount\\.do$)"
                +"|(^/findPwrPayByMobile\\.do$)"
                +"|(^/appGetDianpuComment\\.do$)"
                +"|(^/saveDianpuComment\\.do$)"
                +"|(^/deleteDianpuComment\\.do$)"


                +"|(^/payWxNotifyAction\\.do$)"
                +"|(^/payWxNotifyActionDxk\\.do$)"
                +"|(^/payWxNotifyActionLq\\.do$)"
                +"|(^/appGetLoadPics\\.do$)"
                +"|(^/appGetDxkAds\\.do$)"

                +"|(^/paopaogoods/updatePaopaoGoodsJia\\.do$)"
                +"|(^/paopaogoods/saveAppGoods\\.do$)"
                +"|(^/paopaogoods/shareGoodsUrl\\.do$)"
                +"|(^/getThemeApp\\.do$)"
        ) || account != null) {
            return true;
        }

        if("true".equals(request.getParameter("j"))) {
            PrintWriter out = response.getWriter();
            out.print(toJSONString(TIMEOUT));
            out.close();
        } else {
            request.getRequestDispatcher("/WEB-INF/session.jsp").forward(request, response);
        }
        return false;
    }
    @Override
    public void postHandle(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) throws Exception {

    }

    @Override
    public void afterCompletion(HttpServletRequest request, HttpServletResponse httpServletResponse, Object o, Exception e) throws Exception {

    }
}
