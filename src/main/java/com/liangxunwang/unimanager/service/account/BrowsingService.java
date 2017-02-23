package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.BrowsingDao;
import com.liangxunwang.unimanager.model.BrowsingObj;
import com.liangxunwang.unimanager.mvc.vo.BrowsingVo;
import com.liangxunwang.unimanager.query.BrowsingQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
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
@Service("browsingService")
public class BrowsingService implements ListService,SaveService {
    @Autowired
    @Qualifier("browsingDao")
    private BrowsingDao browsingDao;

    @Override
    public Object list(Object object) throws ServiceException {
        BrowsingQuery query = (BrowsingQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmpid())){
            map.put("empid", query.getEmpid());
        }
        if(!StringUtil.isNullOrEmpty(query.getGoodsid())){
            map.put("goodsid", query.getGoodsid());
        }
        List<BrowsingVo> lists = browsingDao.lists(map);
        if(lists != null){
            for(BrowsingVo vo : lists){
                if (!StringUtil.isNullOrEmpty(vo.getGoods_cover())) {
                    if (vo.getGoods_cover().startsWith("upload")) {
                        vo.setGoods_cover(Constants.URL + vo.getGoods_cover());
                    }else {
                        vo.setGoods_cover(Constants.QINIU_URL + vo.getGoods_cover());
                    }
                }
            }
        }
        long count = browsingDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        BrowsingQuery query = (BrowsingQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmpid())){
            map.put("empid", query.getEmpid());
        }
        if(!StringUtil.isNullOrEmpty(query.getGoodsid())){
            map.put("goodsid", query.getGoodsid());
        }
        List<BrowsingVo> lists = browsingDao.lists(map);
        BrowsingObj browsingObj = new BrowsingObj();
        if(lists != null && lists.size()>0){
            //说明已经存在 更新记录
            browsingObj.setBrowsingid(lists.get(0).getBrowsingid());
            browsingObj.setDateline(System.currentTimeMillis()+"");
            browsingDao.update(browsingObj);
        }else {
            browsingObj.setBrowsingid(UUIDFactory.random());
            browsingObj.setEmpid(query.getEmpid());
            browsingObj.setGoodsid(query.getGoodsid());
            browsingObj.setDateline(System.currentTimeMillis()+"");
            browsingDao.save(browsingObj);
        }
        return null;
    }

}
