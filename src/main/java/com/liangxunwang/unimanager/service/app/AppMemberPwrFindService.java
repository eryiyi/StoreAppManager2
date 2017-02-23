package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("appMemberPwrFindService")
public class AppMemberPwrFindService implements UpdateService,ExecuteService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object update(Object object) {
        Member member = (Member) object;
        //根据mobile查询会员是否存在
        MemberVO memberVO =  memberDao.findByMobile(member.getEmpMobile());
        if(memberVO != null){
            member.setEmpPass(new MD5Util().getMD5ofStr(member.getEmpPass()));//密码加密
            memberDao.updatePassword(member.getEmpMobile(), member.getEmpPass());
        }else {
            throw new ServiceException("no_emp");//会员不存在
        }

        return 200;
    }

    @Override
    public Object execute(Object object) throws ServiceException, Exception {
        Member member = (Member) object;
        //根据mobile查询会员是否存在
        MemberVO memberVO =  memberDao.findByMobile(member.getEmpMobile());
        if(memberVO != null){
            memberDao.updatePasswordPay(member.getEmpMobile(), member.getEmp_pay_pass());
        }else {
            throw new ServiceException("no_emp");//会员不存在
        }

        return 200;
    }
}
