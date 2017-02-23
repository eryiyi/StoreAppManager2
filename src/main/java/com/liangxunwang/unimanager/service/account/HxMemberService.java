package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("hxMemberService")
public class HxMemberService implements UpdateService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;


//    环信添加用户到组
    @Override
    public Object update(Object object) {
//        Object[] params = new Object[]{hxUserName, coid, empId};
//        hxMemberUpdateService.update(params);
        Object[] params = (Object[]) object;
        String hxUserName = (String) params[0];
        String coid = (String)params[1];
        String empId = (String)params[2];
        return null;
    }

}
