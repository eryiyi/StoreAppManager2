package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Service("appGoodsCommentService")
public class AppGoodsCommentService implements FindService {
    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        String goods_id = (String) object;
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("goods_id", goods_id);
        DecimalFormat df=new DecimalFormat(".##");
        //全部星级
        Long countAll = goodsCommentDao.countStarNumber(map);
        //评论数量
        Long countNum = goodsCommentDao.count(map);


        Double aDouble =(double)(Math.round(Double.valueOf(countAll==null?0:countAll)/Double.valueOf(countNum))/100.0)*2*1000;//这样为保持2位

        return new Object[] {countNum, aDouble};
    }
}
