package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxAttributeDao;
import com.liangxunwang.unimanager.model.LxAttribute;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("lxAttributeService")
public class LxAttributeService implements ListService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("lxAttributeDao")
    private LxAttributeDao lxAttributeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        Map<String, Object> map = new HashMap<String, Object>();
        List<LxAttribute> lists = lxAttributeDao.lists(map);
        return lists;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return lxAttributeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        LxAttribute lxAttribute = (LxAttribute) object;
        lxAttributeDao.update(lxAttribute);
        return null;
    }
}
