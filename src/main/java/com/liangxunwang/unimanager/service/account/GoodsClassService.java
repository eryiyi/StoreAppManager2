package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxClassDao;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.query.LxClassQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Service("goodsClassService")
public class GoodsClassService implements SaveService, ListService, FindService , UpdateService,DeleteService{

    @Autowired
    @Qualifier("lxClassDao")
    private LxClassDao lxClassDao;

    @Override
    public Object save(Object object) throws ServiceException {
        LxClass type = (LxClass) object;
        type.setLx_class_id(UUIDFactory.random());
        lxClassDao.save(type);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        LxClassQuery query = (LxClassQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(query.getLx_class_id())){
            map.put("lx_class_id", query.getLx_class_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getF_lx_class_id())){
            map.put("f_lx_class_id", query.getF_lx_class_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        List<LxClass> list = lxClassDao.list(map);
//        for(LxClass goodsType:list){
//            if(!StringUtil.isNullOrEmpty(goodsType.getLx_class_cover())){
//                if (goodsType.getLx_class_cover().startsWith("upload")) {
//                    goodsType.setLx_class_cover(Constants.URL + goodsType.getLx_class_cover());
//                }else {
//                    goodsType.setLx_class_cover(Constants.QINIU_URL + goodsType.getLx_class_cover());
//                }
//            }
//        }
        return list;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        return lxClassDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        LxClass type = (LxClass) object;
        lxClassDao.update(type);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String lx_class_id = (String) object;
        lxClassDao.deleteById(lx_class_id);
        return null;
    }
}
