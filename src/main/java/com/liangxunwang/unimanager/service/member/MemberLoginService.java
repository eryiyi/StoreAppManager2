package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("memberLoginService")
public class MemberLoginService implements ExecuteService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao dao;

    @Override
    public Object execute(Object object) {
        Object[] params = (Object[]) object;
        String username = (String) params[0];
        String password = (String) params[1];

        MemberVO member = dao.findByMobile(username);

        if (member == null){
            throw new ServiceException("NotFound");
        }
        if(StringUtil.isNullOrEmpty(member.getEmpPass())){
            //密码为空
            throw new ServiceException("PassNull");
        }
        if (!new MD5Util().getMD5ofStr(password).equals(member.getEmpPass())){
            throw new ServiceException("PassError");
        }
        if ("1".equals(member.getIsUse())){
            throw new ServiceException("NotUse");
        }
        if (member.getEmpCover().startsWith("upload")) {
            member.setEmpCover(Constants.URL + member.getEmpCover());
        }else {
            member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
        }
        return member;
    }
}
