package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/2/5.
 */
@Controller
public class ModifyMemberController extends ControllerConstants{

    @Autowired
    @Qualifier("memberService")
    private UpdateService updateMemberService;

    @Autowired
    @Qualifier("modifyMemberService")
    private ExecuteService modifyMemberExecuteService;

    @Autowired
    @Qualifier("modifyMemberService")
    private UpdateService modifyMemberUpdateService;
    @Autowired
    @Qualifier("appMemberService")
    private UpdateService appMemberServiceUpdate;

    @Autowired
    @Qualifier("appMemberbirthService")
    private UpdateService appMemberbirthServiceUpdate;

    @Autowired
    @Qualifier("appMemberPwrFindService")
    private UpdateService appMemberPwrFindServiceUpdate;


    @Autowired
    @Qualifier("appMemberPwrFindService")
    private ExecuteService appMemberPwrFindServiceExe;

    //name  cover sex
    @RequestMapping("/modifyMember")
    @ResponseBody
    public String modifyMember(Member member){
        try {
            updateMemberService.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/modifyMemberSex")
    @ResponseBody
    public String modifyMemberSex(Member member){
        try {
            appMemberServiceUpdate.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/modifyMemberBirth")
    @ResponseBody
    public String modifyMemberBirth(Member member){
        try {
            appMemberbirthServiceUpdate.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 重设密码
     * @param empId  用户ID
     * @param rePass  新密码
     * @return
     */
    @RequestMapping("/resetPass")
    @ResponseBody
    public String resetPassword(String empId, String rePass){
        Object[] params = new Object[]{empId, rePass};
        try {
            modifyMemberExecuteService.execute(params);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("PassError")){
                return toJSONString(ERROR_1);
            }
            if (msg.equals("NoPeople")){
                return toJSONString(ERROR_2);
            }
            if(msg.equals(Constants.HX_ERROR)){
                return toJSONString(ERROR_4);
            }
            return toJSONString(ERROR_3);
        }
        return toJSONString(SUCCESS);
    }

    /**
     * 修改手机号
     * @return
     */
    @RequestMapping("/resetMobile")
    @ResponseBody
    public String resetMobile(String mobile, String reMobile,String empId){
        Object[] params = new Object[]{mobile, reMobile, empId};
        try {
            modifyMemberUpdateService.update(params);

        }catch (Exception e){
            String msg=  e.getMessage();
            if (msg.equals("NoThisMobile")){
                return toJSONString(ERROR_1);//原手机号没有注册
            }
            if (msg.equals("NoOwnAccount")){
                return toJSONString(ERROR_2);//手机号和当前登陆账号不匹配
            }
            if (msg.equals(Constants.NO_SEND_CODE)){
                return toJSONString(ERROR_3);//没有发送验证码
            }
            if (msg.equals(Constants.HAS_EXISTS)){
                return toJSONString(ERROR_4);//重设手机号已经是注册用户
            }
            if (msg.equals(Constants.CODE_NOT_EQUAL)){
                return toJSONString(ERROR_5);// 验证码不匹配
            }else {
                return toJSONString(ERROR_6);//修改失败
            }

        }
        return toJSONString(SUCCESS);
    }

    @Autowired
    @Qualifier("appMemberPayPassService")
    private UpdateService appMemberPayPassServiceUpdate;

    @RequestMapping("/modifyMemberPayPass")
    @ResponseBody
    public String modifyMemberPayPass(Member member){
        try {
            appMemberPayPassServiceUpdate.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("appMemberLocationService")
    private UpdateService appMemberLocationServiceUpdate;

    @RequestMapping("/sendLocation")
    @ResponseBody
    public String sendLocation(Member member){
        try {
            appMemberLocationServiceUpdate.update(member);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

//    @RequestMapping("/updateEmpName")
//    @ResponseBody
//    public String updateEmpName(Member member){
//        try {
//            appMemberLocationServiceUpdate.update(member);
//            return toJSONString(SUCCESS);
//        }catch (ServiceException e){
//            return toJSONString(ERROR_1);
//        }
//    }\

    @RequestMapping("/findPwrByMobile")
      @ResponseBody
      public String findPwrByMobile(Member member){
        if(StringUtil.isNullOrEmpty(member.getEmpMobile())){
            return  toJSONString(ERROR_2);//手机号为空
        }
        if(StringUtil.isNullOrEmpty(member.getEmpPass())){
            return  toJSONString(ERROR_3);//密码为空
        }
        try {
            appMemberPwrFindServiceUpdate.update(member);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("no_emp")){
                return toJSONString(ERROR_4);//该手机号不存在注册用户
            }else{
                return toJSONString(ERROR_1);
            }

        }
    }

    @RequestMapping("/findPwrPayByMobile")
    @ResponseBody
    public String findPwrPayByMobile(Member member){
        if(StringUtil.isNullOrEmpty(member.getEmpMobile())){
            return  toJSONString(ERROR_2);//手机号为空
        }
        if(StringUtil.isNullOrEmpty(member.getEmp_pay_pass())){
            return  toJSONString(ERROR_3);//密码为空
        }
        try {
            appMemberPwrFindServiceExe.execute(member);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("no_emp")){
                return toJSONString(ERROR_4);//该手机号不存在注册用户
            }else{
                return toJSONString(ERROR_1);
            }

        }
    }

}
