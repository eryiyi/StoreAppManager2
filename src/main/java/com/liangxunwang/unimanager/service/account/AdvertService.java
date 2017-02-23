package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdvertDao;
import com.liangxunwang.unimanager.model.Advert;
import com.liangxunwang.unimanager.mvc.vo.AdvertVO;
import com.liangxunwang.unimanager.query.AdvertQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/31.
 */
@Service("advertService")
public class AdvertService implements SaveService, ListService , FindService, UpdateService, DeleteService{
    @Autowired
    @Qualifier("advertDao")
    private AdvertDao advertDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Advert advert = (Advert) object;
        advert.setAdId(UUIDFactory.random());
        advert.setDateline(System.currentTimeMillis()+"");
        advert.setEndTime(DateUtil.getMs(advert.getEndTime(), "MM/dd/yyyy") + "");
        if (StringUtil.isNullOrEmpty(advert.getAdSchoolId())){
            advert.setAdSchoolId("99999999999");
            if ("1".equals(advert.getAdTypeId())){
                advert.setAdTypeId("3");
            }else {
                advert.setAdTypeId("4");
            }
        }
        try {
            advertDao.save(advert);
        }catch (ServiceException e){
            throw new ServiceException(Constants.SAVE_ERROR);
        }
        return advert;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        AdvertQuery query = (AdvertQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }

        List<AdvertVO> list = advertDao.list(map);
        long count = advertDao.count(map);
        return new Object[] {list, count};
    }


    @Override
    public Object findById(Object object) throws ServiceException {
        String adId = (String) object;
        return advertDao.findById(adId);
    }

    @Override
    public Object update(Object object) {
        Advert advert = (Advert) object;
        advert.setDateline(System.currentTimeMillis()+"");
        advert.setEndTime(DateUtil.getMs(advert.getEndTime(), "MM/dd/yyyy") +"");
        advertDao.update(advert);
        return null;
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String adId = (String) object;
        if (adId.equals("update")){
            advertDao.updateOverTime(System.currentTimeMillis()+"");
        }else {
            advertDao.deleteById(adId);
        }
        return null;
    }
}
