package com.liangxunwang.unimanager.mvc;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;
import java.util.Enumeration;
import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class IndexController extends ControllerConstants {
    @Autowired
    @Qualifier("indexService")
    private ListService indexListService;

    @Autowired
    @Qualifier("indexSjService")
    private ListService indexSjServiceList;

    @RequestMapping("/index")
    public String index(HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if (admin != null){
            map.put("admin", admin);
            return "/index";
        }
        return "adminLogin";
    }

    @RequestMapping("/main")
    public String left(HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if (admin != null){
            map.put("admin", admin);
        }
        return "/index";
    }

    @RequestMapping("/mainPage")
    public String mainPage(HttpSession session,ModelMap map){
        Admin admin = (Admin) session.getAttribute(ControllerConstants.ACCOUNT_KEY);
        if(admin != null){
            if("0".equals(admin.getEmp_id())){
                map.put("is_admin", "1");//是
                //说明是后台管理员用户
                List<Object> list = (List<Object>) indexListService.list(null);
                map.put("memberCount", list.get(0));
                map.put("memberCountNoDay", list.get(1));
                map.put("countSj", list.get(2));

                map.put("countGoodsAll", list.get(3));
                map.put("countGoods1", list.get(4));
                map.put("countGoods2", list.get(5));

                map.put("countOrderAll", list.get(6));
                map.put("countOrderDone", list.get(7));
                map.put("countOrderDay", list.get(8));

                map.put("memberCountDxk", list.get(9));
                map.put("memberCountNoDayDxk", list.get(10));

                map.put("dianpuCommentCount", list.get(11));
                map.put("goodsCommentCount", list.get(12));
                return "/main";
            }else{
                //说明是商家登录了  展示商品 订单 营业额
                map.put("is_admin", "0");//否
                List<Object> list = (List<Object>)indexSjServiceList.list(admin.getEmp_id());
                map.put("incomeAll", list.get(0));
                map.put("incomeDay", list.get(1));

                map.put("countOrderAll", list.get(2));
                map.put("countOrderDone", list.get(3));
                map.put("countOrderDay", list.get(4));

                map.put("countGoodsUse", list.get(5));
                map.put("countGoodsUnUse", list.get(6));
                map.put("countGoodsZero", list.get(7));
                map.put("countFensi", list.get(8));
                return "/main_sj";
            }
        }
        return "/main";

    }

    /**
     *
     * @return
     */
    @RequestMapping("/logout")
    public String logout(HttpSession session){
        Enumeration en = session.getAttributeNames();
        while (en.hasMoreElements()) {
            session.removeAttribute((String)en.nextElement());
        }
        return "redirect:/";
    }

}
