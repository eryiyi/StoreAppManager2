package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Service("goodsCommentMineService")
public class GoodsCommentMineService implements  ListService{

    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Override
    public Object list(Object object) throws ServiceException {
        GoodsCommentQuery query = (GoodsCommentQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getGoodsId())){
            map.put("goods_id", query.getGoodsId());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getGoods_emp_id())){
            map.put("goods_emp_id", query.getGoods_emp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del", query.getIs_del());
        }
        List<GoodsComment> list = goodsCommentDao.list(map);
        long count = goodsCommentDao.count(map);
        return new Object[]{list, count};
    }

}
