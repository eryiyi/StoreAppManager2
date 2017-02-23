package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DxkAdDao;
import com.liangxunwang.unimanager.model.DxkAd;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("dxkAdService")
public class DxkAdService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("dxkAdDao")
    private DxkAdDao dxkAdDao;

    @Override
    public Object list(Object object) throws ServiceException {
        AdQuery query = (AdQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        List<DxkAd> lists = dxkAdDao.lists(map);

        if(lists != null){
            for(DxkAd adObj:lists){
                if(!StringUtil.isNullOrEmpty(adObj.getDxk_ad_pic())){
                    if(adObj.getDxk_ad_pic().startsWith("upload")){
                        adObj.setDxk_ad_pic(Constants.URL + adObj.getDxk_ad_pic());
                    }else {
                        adObj.setDxk_ad_pic(Constants.QINIU_URL + adObj.getDxk_ad_pic());
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        DxkAd adObj = (DxkAd) object;
        adObj.setDxk_ad_id(UUIDFactory.random());
        dxkAdDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_ad_id = (String) object;
        dxkAdDao.delete(mm_ad_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return dxkAdDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        DxkAd adObj = (DxkAd) object;
        dxkAdDao.update(adObj);
        return null;
    }
}
