package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.ManagerEmp;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.ManagerEmpVO;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/4.
 */
@Controller("managerEmpController")
public class ManagerEmpController extends ControllerConstants {

    @Autowired
    @Qualifier("managerEmpService")
    private SaveService managerEmpSaveService;

    @Autowired
    @Qualifier("managerEmpService")
    private ListService listCloseMemberService;

    @Autowired
    @Qualifier("managerEmpService")
    private UpdateService managerEmpUpdateService;

    /**
     * 关禁闭
     * @param managerEmp
     * @param reportId
     * @return
     */
    @RequestMapping("/saveManagerEmp")
    @ResponseBody
    public String saveManagerEmp(ManagerEmp managerEmp, String reportId){
        try {
            Object[] params = new Object[]{managerEmp, reportId};
            managerEmpSaveService.save(params);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            if (e.getMessage().equals("HasClose")){
                return toJSONString(ERROR_2);
            }
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 查找该管理员下的被关禁闭会员
     * @param adminId
     * @return
     */
    @RequestMapping(value = "/listCloseMember", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String listCloseMember(String adminId){
        try {
            List<ManagerEmpVO> list = (List<ManagerEmpVO>) listCloseMemberService.list(adminId);
            DataTip dataTip = new DataTip();
            dataTip.setData(list);
            return toJSONString(dataTip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 解除禁闭
     * @param empId  会员ID
     * @return
     */
    @RequestMapping("/openManagerEmp")
    @ResponseBody
    public String openManagerEmp(String empId){
        try {
            managerEmpUpdateService.update(empId);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    public String systemOpenEmp(){
        openManagerEmp("update");
        return null;
    }

}
