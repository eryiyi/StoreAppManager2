package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/3/9.
 */
@Service("hXMemberService")
public class HXMemberService implements UpdateService {

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;
    @Override
    public Object update(Object object) {
        Object[] params = (Object[]) object;
        String hxUsername = (String) params[0];
        String coid = (String) params[1];
        String empId = (String) params[2];

        boolean flag =  true;
        if (flag){
            memberDao.updateHx(empId);
        }
        return null;
    }
}
