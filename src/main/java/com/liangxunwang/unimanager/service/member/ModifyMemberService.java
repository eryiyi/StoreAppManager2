package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/2/25.
 */
@Service("modifyMemberService")
public class ModifyMemberService implements ExecuteService , UpdateService{

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        String empId = (String) params[0];
        String rePass = (String) params[1];

        Member member = memberDao.findById(empId);
        if (member == null){
            throw new ServiceException("NoPeople");
        }
//        if (!member.getEmpPass().equals(new MD5Util().getMD5ofStr(pass))){
//            throw new ServiceException("PassError");
//        }
        memberDao.resetPass(empId, new MD5Util().getMD5ofStr(rePass));

//        String hxUsername = member.getHxUsername();
//        //修改环信用户密码
//        Boolean succ=ChatUtils.resetPW(hxUsername, new MD5Util().getMD5ofStr(rePass));
//        if(!succ){
//            throw new ServiceException(Constants.HX_ERROR);
//        }
        return null;
    }

    @Override
    public Object update(Object object) {
        Object[] params = (Object[]) object;
        String mobile = (String) params[0];
        String reMobile = (String) params[1];
        String empId = (String) params[2];

        Member member = memberDao.findByMobile(mobile);
        if (member == null){
            throw new ServiceException("NoThisMobile");//手机号还不是注册用户
        }
        if (!member.getEmpId().equals(empId)){
            throw new ServiceException("NoOwnAccount");
        }

        Member reMember = memberDao.findByMobile(reMobile);
        if (reMember != null){
            throw new ServiceException(Constants.HAS_EXISTS);
        }
        memberDao.resetMobile(empId, reMobile);

        return null;
    }
}
