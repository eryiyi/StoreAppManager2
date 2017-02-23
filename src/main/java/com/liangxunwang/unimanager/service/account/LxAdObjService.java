package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxAdDao;
import com.liangxunwang.unimanager.dao.PaopaoGoodsDao;
import com.liangxunwang.unimanager.model.LxAd;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
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

@Service("lxAdObjService")
public class LxAdObjService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("lxAdDao")
    private LxAdDao lxAdDao;

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;


    @Override
    public Object list(Object object) throws ServiceException {
        AdQuery query = (AdQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getAd_type())){
            map.put("ad_type", query.getAd_type());
        }
        List<LxAd> lists = lxAdDao.lists(map);
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
        long count = lxAdDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        LxAd adObj = (LxAd) object;
        adObj.setAd_id(UUIDFactory.random());
        adObj.setDateline(System.currentTimeMillis()+"");
        //根据商品id 查询empid
        if(!StringUtil.isNullOrEmpty(adObj.getAd_msg_id())){
            PaopaoGoodsVO paopaoGoodsVO = paopaoGoodsDao.findGoodsVO(adObj.getAd_msg_id());
            if(paopaoGoodsVO != null){
                //说明商品不是空
                if(!StringUtil.isNullOrEmpty(paopaoGoodsVO.getEmpId())){
                    adObj.setAd_emp_id(paopaoGoodsVO.getEmpId());
                }
            }
        }
        lxAdDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String ad_id = (String) object;
        lxAdDao.delete(ad_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return lxAdDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        LxAd lxAd = (LxAd) object;
        lxAdDao.update(lxAd);
        return null;
    }
}
