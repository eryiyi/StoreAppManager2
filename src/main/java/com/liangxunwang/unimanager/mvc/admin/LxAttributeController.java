package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LxAttribute;
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
@RequestMapping("/lxAttributeController")
public class LxAttributeController extends ControllerConstants {

    @Autowired
    @Qualifier("lxAttributeService")
    private ListService levelService;

    @Autowired
    @Qualifier("lxAttributeService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("lxAttributeService")
    private UpdateService levelServiceSaveUpdate;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, AdQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<LxAttribute> list = (List<LxAttribute>) levelService.list(query);
        map.put("list", list);
        return "/lxAttribute/list";
    }

    @RequestMapping("/toEdit")
    public String toEdit(HttpSession session,ModelMap map, String lx_attribute_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        LxAttribute lxAttribute = (LxAttribute) levelServiceSaveExe.execute(lx_attribute_id);
        map.put("adObj", lxAttribute);
        return "/lxAttribute/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,LxAttribute lxAttribute){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(lxAttribute);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
