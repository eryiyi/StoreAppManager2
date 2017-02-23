package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LxCardObj;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/lxCardObjController")
public class LxCardObjController extends ControllerConstants {

    @Autowired
    @Qualifier("lxCardObjService")
    private ListService levelService;

    @Autowired
    @Qualifier("lxCardObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("lxCardObjService")
    private UpdateService levelServiceSaveUpdate;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<LxCardObj> list = (List<LxCardObj>) levelService.list(query);
        map.put("list", list);
        return "/card/list";
    }


    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String lx_card_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        LxCardObj lxCardObj = (LxCardObj) levelServiceSaveExe.execute(lx_card_id);
        map.put("adObj", lxCardObj);
        return "/card/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,LxCardObj lxCardObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(lxCardObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
