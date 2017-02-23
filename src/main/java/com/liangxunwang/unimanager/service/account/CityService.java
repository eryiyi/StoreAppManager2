package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AppCityDao;
import com.liangxunwang.unimanager.model.City;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("cityService")
public class CityService implements ListService {
    @Autowired
    @Qualifier("appCityDao")
    private AppCityDao appCityDao;

    @Override
    public Object list(Object object) throws ServiceException {
        String provinceid = (String) object;
        City city = new City();
        if(!StringUtil.isNullOrEmpty(provinceid)){
            city.setAreaid(provinceid);
        }
        return appCityDao.list(city);
    }
}
