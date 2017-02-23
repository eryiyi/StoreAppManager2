package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.DxkAd;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
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
@RequestMapping("/dxkAdController")
public class DxkAdController extends ControllerConstants {

    @Autowired
    @Qualifier("dxkAdService")
    private ListService levelService;

    @Autowired
    @Qualifier("dxkAdService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("dxkAdService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("dxkAdService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("dxkAdService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            query.setEmp_id(manager.getEmp_id());
        }
        List<DxkAd> list = (List<DxkAd>) levelService.list(query);
        map.put("list", list);
        //日志记录
        return "/dxkAd/list";
    }

    @RequestMapping("add")
    public String add(){
        return "/dxkAd/addAd";
    }

    @RequestMapping("addAd")
    @ResponseBody
    public String addPiao(HttpSession session,DxkAd dxkAd){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSave.save(dxkAd);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("adIsTooMuch")){
                return toJSONString(ERROR_2);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String dxk_ad_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(dxk_ad_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String dxk_ad_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        DxkAd adObj = (DxkAd) levelServiceSaveExe.execute(dxk_ad_id);
        map.put("adObj", adObj);
        return "/dxkAd/editAd";
    }

    /**
     * 更新
     */
    @RequestMapping("/editAd")
    @ResponseBody
    public String updateGoodsType(HttpSession session,DxkAd dxkAd){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(dxkAd);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
