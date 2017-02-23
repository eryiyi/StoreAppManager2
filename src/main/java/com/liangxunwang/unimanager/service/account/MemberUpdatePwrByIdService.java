package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.MD5;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("memberUpdatePwrByIdService")
public class MemberUpdatePwrByIdService implements UpdateService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object update(Object object) {
        Member member = (Member) object;
        if(!StringUtil.isNullOrEmpty(member.getEmpPass())){
            memberDao.resetPass(member.getEmpId(), member.getEmpPass());
        }
        if(!StringUtil.isNullOrEmpty(member.getEmp_pay_pass())){
            memberDao.resetPayPass(member);
        }
        return null;
    }

}
