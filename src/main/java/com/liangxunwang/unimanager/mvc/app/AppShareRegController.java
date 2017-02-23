package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppShareRegController extends ControllerConstants {

    @Autowired
    @Qualifier("appMemberService")
    private FindService appMemberService;

    /**
     * 推广页面 推广注册 带推广者ID
     * @return
     */
    @RequestMapping(value = "/appShareReg", produces = "text/plain;charset=UTF-8")
    public String appGetAdEmp(HttpSession session,ModelMap map, String emp_id){
        try {
            Member member = (Member) appMemberService.findById(emp_id);
            map.put("member", member);
            return "/reg_app/reg_app";
        }catch (ServiceException e){
            return "/reg_app/reg_app";
        }
    }


    @Autowired
    @Qualifier("memberRegisterService")
    private SaveService memberRegisterService;

    @RequestMapping("saveEmpShare")
    @ResponseBody
    public String saveEmpShare(HttpSession session,Member member){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            Member member1 = (Member) memberRegisterService.save(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals(Constants.HAS_EXISTS)){
                return toJSONString(ERROR_1);//手机号已经注册了，换个试试
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_2);//注册失败，请稍后重试
            }
            if(msg.equals(Constants.HX_ERROR)){
                return toJSONString(ERROR_3);//环信注册失败
            }
        }
        return toJSONString(ERROR_2);//注册失败
    }



}
