package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.RateDao;
import com.liangxunwang.unimanager.model.Rate;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;

/**
 * Created by zhl on 2015/9/7.
 */
public class RateService implements FindService, UpdateService {

    @Autowired
    @Qualifier("rateDao")
    private RateDao rateDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        return rateDao.findById("1");
    }

    @Override
    public Object update(Object object) {
        if (object instanceof Rate){
            Rate rate = (Rate) object;
            rateDao.updateRate(rate);
        }
        return null;
    }
}
