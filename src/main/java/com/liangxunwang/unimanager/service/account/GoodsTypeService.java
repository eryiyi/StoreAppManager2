package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.GoodsTypeDao;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
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
 * Created by zhl on 2015/2/2.
 */
@Service("goodsTypeService")
public class GoodsTypeService implements SaveService, ListService, FindService , UpdateService, DeleteService{

    @Autowired
    @Qualifier("goodsTypeDao")
    private GoodsTypeDao goodsTypeDao;

    @Override
    public Object save(Object object) throws ServiceException {
        GoodsType type = (GoodsType) object;
        type.setTypeId(UUIDFactory.random());
        goodsTypeDao.save(type);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        GoodsTypeThreeQuery query = (GoodsTypeThreeQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(query.getType_isuse())){
            map.put("isUse", query.getType_isuse());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getType_id())){
            map.put("type_id", query.getType_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_type())){
            map.put("is_type", query.getIs_type());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_index())){
            map.put("is_index", query.getIs_index());
        }
        List<GoodsType> list = goodsTypeDao.list(map);
        for(GoodsType goodsType:list){
            if(!StringUtil.isNullOrEmpty(goodsType.getTypeCover())){
                if (goodsType.getTypeCover().startsWith("upload")) {
                    goodsType.setTypeCover(Constants.URL + goodsType.getTypeCover());
                }else {
                    goodsType.setTypeCover(Constants.QINIU_URL + goodsType.getTypeCover());
                }
            }
        }
        return list;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        return goodsTypeDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        GoodsType type = (GoodsType) object;
        goodsTypeDao.update(type);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String typeId = (String) object;
        goodsTypeDao.deleteById(typeId);
        return null;
    }
}
