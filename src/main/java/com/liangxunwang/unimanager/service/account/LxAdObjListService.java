package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxAdDao;
import com.liangxunwang.unimanager.model.LxAd;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("lxAdObjListService")
public class LxAdObjListService implements ListService {
    @Autowired
    @Qualifier("lxAdDao")
    private LxAdDao lxAdDao;

    @Override
    public Object list(Object object) throws ServiceException {
        AdQuery query = (AdQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(query.getAd_type())){
            map.put("ad_type", query.getAd_type());
        }
        List<LxAd> lists = lxAdDao.listsAll(map);
        if(lists != null){
            for(LxAd adObj:lists){
                if(!StringUtil.isNullOrEmpty(adObj.getAd_pic())){
                    if(adObj.getAd_pic().startsWith("upload")){
                        adObj.setAd_pic(Constants.URL + adObj.getAd_pic());
                    }else {
                        adObj.setAd_pic(Constants.QINIU_URL + adObj.getAd_pic());
                    }
                }
            }
        }
        return lists;
    }

}
