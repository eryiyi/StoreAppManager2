package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.JifenObjDao;
import com.liangxunwang.unimanager.model.JifenObj;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("jifenObjService")
public class JifenObjService implements ExecuteService, UpdateService {

    @Autowired
    @Qualifier("jifenObjDao")
    private JifenObjDao jifenObjDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        return jifenObjDao.list();
    }

    @Override
    public Object update(Object object) {
        JifenObj adObj = (JifenObj) object;
        jifenObjDao.update(adObj);
        return null;
    }
}
