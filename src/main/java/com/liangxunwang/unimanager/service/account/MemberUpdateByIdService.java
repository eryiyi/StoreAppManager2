package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service("memberUpdateByIdService")
public class MemberUpdateByIdService implements UpdateService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object update(Object object) {
        Member member = (Member) object;
        if(!"0".equals(member.getLx_dxk_level_id()) && !StringUtil.isNullOrEmpty(member.getLx_dxk_level_id())){
            //说明是定向卡等级了
            member.setIs_card_emp("1");
        }else {
            member.setIs_card_emp("0");
        }
        memberDao.updateMemberById(member);
        return null;
    }

}
