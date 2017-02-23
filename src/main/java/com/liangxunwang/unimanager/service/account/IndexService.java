package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("indexService")
public class IndexService implements ListService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("dianpuCommentDao")
    private DianpuCommentDao dianpuCommentDao;


   @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;


    @Override
    public Object list(Object object) throws ServiceException {
        //----------------会员统计-------------------
        //总共会员数量
        long memberCount = memberDao.memberCount();
        //今天注册的会员
        Map<String, Object> map1 = new HashMap<String, Object>();
        map1.put("startTime",  DateUtil.getStartDay());
        map1.put("endTime",  DateUtil.getEndDay());
        long memberCountNoDay = memberDao.countDay(map1);
        //入驻的商家
        Map<String, Object> map2 = new HashMap<String, Object>();
        long countSj = adminDao.countSj(map2);
        //----------------商品统计----------------
        //全部商品
        Map<String, Object> map3 = new HashMap<String, Object>();
        long countGoodsAll = paopaoGoodsDao.count(map3);
        //商家的商品
        map3.put("is_zhiying","0");
        long countGoods1 = paopaoGoodsDao.count(map3);
        //商城的商品
        map3.put("is_zhiying","1");
        long countGoods2 = paopaoGoodsDao.count(map3);
        //--------------订单统计------------------------
        //全部订单
        Map<String, Object> map4 = new HashMap<String, Object>();
        long countOrderAll = orderDao.count(map4);
        //已完成订单
        map4.put("status", "5");
        long countOrderDone = orderDao.count(map4);
        //今日订单统计
        map4.put("startTime",  DateUtil.getStartDay());
        map4.put("endTime",  DateUtil.getEndDay());
        long countOrderDay = orderDao.countDay(map4);
        //---------------定向卡会员--------------------
        //总共定向卡会员数量
        long memberCountDxk = memberDao.memberCountDxk();
        //今天注册的定向卡会员
        long memberCountNoDayDxk = memberDao.countDayDxk(map1);

        //-----------商城的评论统计--------------------
        //店铺评论
        Map<String, Object> map5 = new HashMap<String, Object>();
        map5.put("emp_id_seller", Constants.MOBILE_UP_DEFAULT_id);
        map5.put("is_del", "0");
        long dianpuCommentCount = dianpuCommentDao.count(map5);
        //商品评论
        Map<String, Object> map6 = new HashMap<String, Object>();
        map6.put("goods_emp_id",Constants.MOBILE_UP_DEFAULT_id);
        long goodsCommentCount = goodsCommentDao.count(map6);

        List<Long> list = new ArrayList<Long>();
        list.add(memberCount);
        list.add(memberCountNoDay);
        list.add(countSj);

        list.add(countGoodsAll);
        list.add(countGoods1);
        list.add(countGoods2);

        list.add(countOrderAll);
        list.add(countOrderDone);
        list.add(countOrderDay);

        list.add(memberCountDxk);
        list.add(memberCountNoDayDxk);

        list.add(dianpuCommentCount);
        list.add(goodsCommentCount);
        return list;
    }
}
