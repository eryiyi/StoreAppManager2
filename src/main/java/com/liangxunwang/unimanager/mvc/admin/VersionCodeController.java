package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.VersonCodeObj;
import com.liangxunwang.unimanager.query.VersionCodeQuery;
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
@RequestMapping("/versionCodeController")
public class VersionCodeController extends ControllerConstants {

    @Autowired
    @Qualifier("versionCodeObjService")
    private ListService levelService;


    @Autowired
    @Qualifier("versionCodeObjService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("versionCodeObjService")
    private UpdateService levelServiceSaveUpdate;


    @RequestMapping("/toEdit")
    public String toEdit(ModelMap map, VersionCodeQuery query) throws Exception {
        List<VersonCodeObj> list = (List<VersonCodeObj>) levelService.list(query);
        if(list != null && list.size() >0 ) {
            map.put("versonCodeObj", list.get(0));
        }
        return "/versioncode/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,VersonCodeObj versonCodeObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            levelServiceSaveUpdate.update(versonCodeObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
