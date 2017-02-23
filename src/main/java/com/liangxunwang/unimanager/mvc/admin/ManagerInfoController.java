package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.query.LxClassQuery;
import com.liangxunwang.unimanager.query.ManagerInfoQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/8/30.
 */
@Controller
@RequestMapping("/managerinfo")
public class ManagerInfoController extends ControllerConstants {

    @Autowired
    @Qualifier("managerInfoService")
    private ListService managerInfoSaveServiceList;

    @Autowired
    @Qualifier("managerInfoService")
    private FindService managerInfoFindService;

    @Autowired
    @Qualifier("managerInfoFindService")
    private FindService managerInfoFindServiceFind;

    @Autowired
    @Qualifier("managerInfoService")
    private UpdateService managerInfoUpdateService;

    @Autowired
    @Qualifier("managerInfoFindService")
    private UpdateService managerInfoFindServiceUpdate;

    @Autowired
    @Qualifier("managerInfoService")
    private SaveService managerInfoSaveService;

    @RequestMapping("save")
    @ResponseBody
    private String save(ManagerInfo info, HttpSession session){
        managerInfoUpdateService.update(info);
        return toJSONString(SUCCESS);
    }


    @Autowired
    @Qualifier("goodsClassService")
    private ListService goodsClassServiceList;

    //商家信息维护
    @RequestMapping("toEdit")
    public String edit(HttpSession session, ModelMap map){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);//获得登录用户
//        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
//        List<GoodsType> listType = (List<GoodsType>) listGoodsTypeService.list(query);
//        map.put("listType", listType);

        LxClassQuery query1 = new LxClassQuery();
        query1.setIs_del("0");
        List<LxClass> listClassSmall = (List<LxClass>) goodsClassServiceList.list(query1);
        map.put("listClassSmall", listClassSmall);

        query1.setF_lx_class_id("0");
        List<LxClass> listClassbig = (List<LxClass>) goodsClassServiceList.list(query1);
        map.put("listClassbig", listClassbig);

        if(manager != null){
            if(!StringUtil.isNullOrEmpty(manager.getEmp_id()) && !"0".equals(manager.getEmp_id())){
                ManagerInfo info = (ManagerInfo) managerInfoFindService.findById(manager.getEmp_id());
                map.put("info", info);
                return "/managerinfo/add";
            }
        }
        return null;
    }

    //商家信息列表
    @RequestMapping("list")
    public String list( ManagerInfoQuery  query , Page page,ModelMap map){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) managerInfoSaveServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);

        return "/managerinfo/list";
    }

    //商家信息查看
    @RequestMapping("toDetail")
    public String toDetail( ModelMap map, String id){
        ManagerInfo info = (ManagerInfo) managerInfoFindServiceFind.findById(id);
        map.put("info", info);

        return "/managerinfo/check";
    }

    //后台管理员审核
    @RequestMapping("updateCheck")
    @ResponseBody
    private String updateCheck(ManagerInfo info){
        managerInfoFindServiceUpdate.update(info);
        return toJSONString(SUCCESS);
    }

    @Autowired
    @Qualifier("managerInfoService")
    private DeleteService managerInfoSaveServiceDelete;

    //后台删除店铺
    @RequestMapping("deleteById")
    @ResponseBody
    private String deleteById(String info_id){
        managerInfoSaveServiceDelete.delete(info_id);
        return toJSONString(SUCCESS);
    }


    //定向卡编辑
    @RequestMapping("toCardDetail")
    public String toCardDetail( ModelMap map, String id){
        ManagerInfo info = (ManagerInfo) managerInfoFindServiceFind.findById(id);
        map.put("info", info);
        return "/managerinfo/toCard";
    }


    @Autowired
    @Qualifier("managerInfoCardService")
    private UpdateService managerInfoCardService;


    //定向卡编辑
    @RequestMapping("updateCard")
    @ResponseBody
    private String updateCard(ManagerInfo info){
        managerInfoCardService.update(info);
        return toJSONString(SUCCESS);
    }


}
