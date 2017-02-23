package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LxAd;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
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

@Controller
@RequestMapping("/lxAdController")
public class LxAdController extends ControllerConstants {

    @Autowired
    @Qualifier("lxAdObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("paihangAllService")
    private ListService paihangAllService;

    @Autowired
    @Qualifier("lxAdObjService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("lxAdObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("lxAdObjService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("lxAdObjService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] results = (Object[])  levelService.list(query);

        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/lxAd/list";
    }


    @Autowired
    @Qualifier("paopaoGoodsListAllService")
    private ListService paopaoGoodsListAllService;



    @RequestMapping("add")
    public String add(ModelMap map){
        //查询商品list
        PaopaoGoodsQuery query = new PaopaoGoodsQuery();
//        List<PaopaoGoodsVO> listGoods = (List<PaopaoGoodsVO>) paopaoGoodsListAllService.list(query);
        //查询推荐的产品列表
        PaihangQuery query1 = new PaihangQuery();
        query1.setIs_del("0");
        List<PaihangObjVO> lists  = (List<PaihangObjVO>) paihangAllService.list(query1);
        map.put("listGoods", lists);
        return "/lxAd/addAd";
    }

    @RequestMapping("addAd")
    @ResponseBody
    public String addPiao(HttpSession session,LxAd lxAd){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSave.save(lxAd);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String ad_id){
        levelServiceSaveDel.delete(ad_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String ad_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        LxAd lxAd = (LxAd) levelServiceSaveExe.execute(ad_id);
        map.put("lxAd", lxAd);
        //查询推荐的产品列表
        PaihangQuery query1 = new PaihangQuery();
        query1.setIs_del("0");
        List<PaihangObjVO> lists  = (List<PaihangObjVO>) paihangAllService.list(query1);
        map.put("listGoods", lists);
        return "/lxAd/editAd";
    }

    /**
     * 更新
     */
    @RequestMapping("/editAd")
    @ResponseBody
    public String updateGoodsType(HttpSession session,LxAd lxAd){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(lxAd.getAd_id())){
            return toJSONString(ERROR_2);
        }
        if(StringUtil.isNullOrEmpty(lxAd.getAd_msg_id())){
            return toJSONString(ERROR_3);
        }
        if(StringUtil.isNullOrEmpty(lxAd.getAd_type())){
            return toJSONString(ERROR_4);
        }

        try {
            levelServiceSaveUpdate.update(lxAd);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
}
