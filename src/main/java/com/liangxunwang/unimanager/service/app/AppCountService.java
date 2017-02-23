package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appCountService")
public class AppCountService implements FindService ,UpdateService{
    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;


    @Override
    public Object findById(Object object) throws ServiceException {
        return countDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        Object[] objects = (Object[]) object;
        String empId = (String) objects[0];
        String count = (String) objects[1];
        String type  = (String) objects[2];
        if(type.equals("1")){
            countDao.updateScoreDelete(empId, count);
        }
        if(type.equals("2")){
            countDao.updateScore(empId, count);
        }
        return 200;
    }
}
