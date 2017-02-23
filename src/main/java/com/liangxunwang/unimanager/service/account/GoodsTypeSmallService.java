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
@Service("goodsTypeSmallService")
public class GoodsTypeSmallService implements  ListService, UpdateService{

    @Autowired
    @Qualifier("goodsTypeDao")
    private GoodsTypeDao goodsTypeDao;


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
            map.put("is_type", query.getType_id());//父级分类
        }

        if(!StringUtil.isNullOrEmpty(query.getIs_hot())){
            map.put("is_hot", query.getIs_hot());
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
    public Object update(Object object) {
        GoodsType type = (GoodsType) object;
        goodsTypeDao.updateReMen(type);
        return null;
    }
}
