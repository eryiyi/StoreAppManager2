package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.Province;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class MemberRegisterController extends ControllerConstants {

    /**
     * @see com.liangxunwang.unimanager.service.member.MemberRegisterService
     */
    @Autowired
    @Qualifier("memberRegisterService")
    private SaveService memberRegisterService;

    @Autowired
    @Qualifier("memberRegisterService")
    private ExecuteService memberExeService;


    @Autowired
    @Qualifier("provinceService")
    private ListService provinceListService;

    /**
     * 注册功能
     * @param member  会员对象
     * @return
     */
    @RequestMapping("/memberRegister")
    @ResponseBody
    public String register(Member member){
        if(StringUtil.isNullOrEmpty(member.getEmpPass())){
            return toJSONString(ERROR_3);
        }
        if(StringUtil.isNullOrEmpty(member.getEmpMobile())){
            return toJSONString(ERROR_3);
        }
        try {
            Member member1 = (Member) memberRegisterService.save(member);
            if(member1 != null){
                DataTip tip = new DataTip();
                tip.setData(member1);
                return toJSONString(tip);
            }
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("has_exists")){
                return toJSONString(ERROR_1);//手机号已经注册了，换个试试
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_2);//注册失败，请稍后重试
            }
            if(msg.equals(Constants.HX_ERROR)){
                return toJSONString(ERROR_3);//环信注册失败
            }
        }
        return toJSONString(SUCCESS);
    }

    /**
     * 校验昵称唯一性
     * @param nickName
     * @return
     */
    @RequestMapping("/checkNickName")
    @ResponseBody
    public String checkNickName(@RequestParam String nickName, String empId){
        try {
            Object[] params = new Object[]{nickName, empId};
            memberExeService.execute(params);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals(Constants.HAS_EXISTS)) {
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }

    /**
     * 获得所有的省份
     * @return
     */
    @RequestMapping(value = "/getProvince", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getProvince(){
        try {
            List<Province> list = (List<Province>) provinceListService.list(null);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
