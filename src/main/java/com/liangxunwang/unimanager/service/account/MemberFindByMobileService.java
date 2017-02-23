package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Objects;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("memberFindByMobileService")
public class MemberFindByMobileService implements FindService,UpdateService{
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;


    @Override
    public Object findById(Object object) throws ServiceException {
        String emp_id = (String) object;

        MemberVO memberVO =  memberDao.findByMobile(emp_id);

        return memberVO;
    }

    @Override
    public Object update(Object object) {
        Object[] objects = (Object[]) object;
        String emp_id = (String) objects[0];
        String emp_up = (String) objects[1];
        memberDao.updateEmpUp(emp_id, emp_up);
        return 200;
    }
}
