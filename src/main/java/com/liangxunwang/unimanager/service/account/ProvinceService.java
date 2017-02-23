package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.ProvinceDao;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/2/25.
 */
@Service("provinceService")
public class ProvinceService implements ListService {

    @Autowired
    @Qualifier("provinceDao")
    private ProvinceDao provinceDao;

    @Override
    public Object list(Object object) throws ServiceException {

        return provinceDao.list();
    }
}
