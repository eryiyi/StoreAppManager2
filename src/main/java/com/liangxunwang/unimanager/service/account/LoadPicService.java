package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LoadPicDao;
import com.liangxunwang.unimanager.model.LoadPic;
import com.liangxunwang.unimanager.query.LoadPicQuery;
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
@Service("loadPicService")
public class LoadPicService implements ListService,SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("loadPicDao")
    private LoadPicDao loadPicDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LoadPicQuery query = (LoadPicQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        List<LoadPic> lists = loadPicDao.lists(map);

        if(lists != null){
            for(LoadPic adObj:lists){
                if(!StringUtil.isNullOrEmpty(adObj.getLoad_pic())){
                    if(adObj.getLoad_pic().startsWith("upload")){
                        adObj.setLoad_pic(Constants.URL + adObj.getLoad_pic());
                    }else {
                        adObj.setLoad_pic(Constants.QINIU_URL + adObj.getLoad_pic());
                    }
                }
            }
        }
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        LoadPic adObj = (LoadPic) object;
        //先查询有多少个广告
        Map<String, Object> map = new HashMap<String, Object>();
        List<LoadPic> lists = loadPicDao.lists(map);
        if(lists != null && lists.size() > 4){
            throw new ServiceException("adIsTooMuch");
        }
        adObj.setLoad_pic_id(UUIDFactory.random());
        loadPicDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String load_pic_id = (String) object;
        loadPicDao.delete(load_pic_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return loadPicDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        LoadPic loadPic = (LoadPic) object;
        loadPicDao.update(loadPic);
        return null;
    }
}
