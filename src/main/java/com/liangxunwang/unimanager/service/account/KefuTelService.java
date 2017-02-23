package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.KefuTelDao;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("kefuTelService")
public class KefuTelService implements ListService,SaveService, UpdateService, ExecuteService, DeleteService {

    @Autowired
    @Qualifier("kefuTelDao")
    private KefuTelDao fuwuDao;

    @Override
    public Object list(Object object) throws ServiceException {
        KefuQuery query = (KefuQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_tel_type())){
            map.put("mm_tel_type", query.getMm_tel_type());
        }
        List<KefuTel> lists = fuwuDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        KefuTel level = (KefuTel) object;
        level.setMm_tel_id(UUIDFactory.random());
        fuwuDao.save(level);
        return null;
    }

    @Override
    public Object update(Object object) {
        KefuTel level = (KefuTel) object;
        fuwuDao.update(level);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return fuwuDao.findById((String) object);
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        fuwuDao.delete((String) object);
        return null;
    }
}
