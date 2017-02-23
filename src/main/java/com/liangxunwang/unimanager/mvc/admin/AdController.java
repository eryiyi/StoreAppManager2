package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.Admin;
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
@RequestMapping("/adObj")
public class AdController extends ControllerConstants {

    @Autowired
    @Qualifier("adObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("adObjService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("adObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("adObjService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("adObjService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            query.setEmp_id(manager.getEmp_id());
        }
        List<AdObj> list = (List<AdObj>) levelService.list(query);
        map.put("list", list);
        //日志记录
        return "/ad/list";
    }

    @RequestMapping("add")
    public String add(){
        return "/ad/addAd";
    }

    @RequestMapping("addAd")
    @ResponseBody
    public String addPiao(HttpSession session,AdObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null && !StringUtil.isNullOrEmpty(manager.getEmp_id())){
            adObj.setEmp_id(manager.getEmp_id());
        }
        try {
            levelServiceSave.save(adObj);
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
    public String delete(HttpSession session,String mm_ad_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSaveDel.delete(mm_ad_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        AdObj adObj = (AdObj) levelServiceSaveExe.execute(typeId);
        map.put("adObj", adObj);
        return "/ad/editAd";
    }

    /**
     * 更新
     */
    @RequestMapping("/editAd")
    @ResponseBody
    public String updateGoodsType(HttpSession session,AdObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(adObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
