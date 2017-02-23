package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppAreaDao;
import com.liangxunwang.unimanager.model.Area;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appAreaService")
public class AppAreaService implements ListService {
    @Autowired
    @Qualifier("appAreaDao")
    private AppAreaDao appAreaDao;

    @Override
    public Object list(Object object) throws ServiceException {
        String cityid = (String) object;
        Area area = new Area();
        area.setCityid(cityid);
        return appAreaDao.list(area);
    }
}
