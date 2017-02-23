package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.LxClassDao;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.query.LxClassQuery;
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

/**
 * Created by zhl on 2015/2/2.
 */
@Service("appLxClassService")
public class AppLxClassService implements  ListService{

    @Autowired
    @Qualifier("lxClassDao")
    private LxClassDao lxClassDao;


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
        for(LxClass goodsType:list){
            if(!StringUtil.isNullOrEmpty(goodsType.getLx_class_cover())){
                if (goodsType.getLx_class_cover().startsWith("upload")) {
                    goodsType.setLx_class_cover(Constants.URL + goodsType.getLx_class_cover());
                }else {
                    goodsType.setLx_class_cover(Constants.QINIU_URL + goodsType.getLx_class_cover());
                }
            }
        }
        return list;
    }


}
