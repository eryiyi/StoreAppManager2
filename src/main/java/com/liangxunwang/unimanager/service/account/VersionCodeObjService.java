package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.VersionCodeObjDao;
import com.liangxunwang.unimanager.model.VersonCodeObj;
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
@Service("versionCodeObjService")
public class VersionCodeObjService implements ListService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("versionCodeObjDao")
    private VersionCodeObjDao versionCodeObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
//        VersionCodeQuery query = (VersionCodeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        List<VersonCodeObj> lists = versionCodeObjDao.lists(map);
        return lists;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return versionCodeObjDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        VersonCodeObj versonCodeObj = (VersonCodeObj) object;
        versionCodeObjDao.update(versonCodeObj);
        return null;
    }
}
