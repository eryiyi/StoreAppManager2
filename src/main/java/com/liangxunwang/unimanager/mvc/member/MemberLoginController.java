package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/1/29.
 */
@Controller
public class MemberLoginController extends ControllerConstants{
    /**
     * @see com.liangxunwang.unimanager.service.member.MemberLoginService
     */
    @Autowired
    @Qualifier("memberLoginService")
    private ExecuteService memberLoginService;

    /**
     * 会员登录
     * @param username 用户名
     * @param password 密码
     * @return
     */
    @RequestMapping(value = "/memberLogin" ,produces="text/plain;charset=UTF-8")
    @ResponseBody
    public String memberLogin(@RequestParam String username, @RequestParam String password){
        if (StringUtil.isNullOrEmpty(username)){
            return toJSONString(ERROR_4);//请输入用户名
        }
        if (StringUtil.isNullOrEmpty(password)){
            return toJSONString(ERROR_5);//请输入密码
        }
        Object[] params = new Object[]{username, password};
        MemberVO member = null;
        try {
            member = (MemberVO) memberLoginService.execute(params);

        }catch (Exception e){
            String emsg = e.getMessage();
            if (emsg.equals("NotFound")){
                return toJSONString(ERROR_1);//暂无此用户，请检查用户名！
            }
            if (emsg.equals("PassError")){
                return toJSONString(ERROR_2);//用户密码错误！
            }
            if (emsg.equals("NotUse")){
                return toJSONString(ERROR_3);//此用户暂不可用！
            }
            if (emsg.equals("PassNull")){
                return toJSONString(ERROR_6);//数据库密码为空！请联系客服
            }
        }
        DataTip tip = new DataTip();
        tip.setData(member);
        return toJSONString(tip);
    }
}
