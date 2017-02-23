package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
//客服
@Controller
@RequestMapping("/kefu")
public class KefuController extends ControllerConstants {
    @Autowired
    @Qualifier("kefuTelService")
    private ListService levelService;

    @Autowired
    @Qualifier("kefuTelService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("kefuTelService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("kefuTelService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("kefuTelService")
    private DeleteService levelServiceDelete;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, KefuQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<KefuTel> list = (List<KefuTel>) levelService.list(query);
        map.put("list", list);
        return "/kefu/list";
    }

    //添加客服(总管理员)
    @RequestMapping("add")
    public String add(ModelMap map, KefuQuery query){

        return "/kefu/addkefu";
    }

    @RequestMapping("addKefu")
    @ResponseBody
    public String addKefu(HttpSession session,KefuTel level){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceSave.save(level);
        return toJSONString(SUCCESS);
    }


    @RequestMapping("/edit")
    public String toUpdateType(HttpSession session,ModelMap map, String typeId) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        KefuTel level = (KefuTel) levelServiceSaveExe.execute(typeId);
        map.put("levelObj", level);
        return "/kefu/editkefu";
    }

    /**
     * 更新
     * @param kefuTel
     * @return
     */
    @RequestMapping("/editKefu")
    @ResponseBody
    public String editKefu(HttpSession session,KefuTel kefuTel){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(kefuTel);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_tel_id, String mm_tel){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        levelServiceDelete.delete(mm_tel_id);
        return toJSONString(SUCCESS);
    }

}
