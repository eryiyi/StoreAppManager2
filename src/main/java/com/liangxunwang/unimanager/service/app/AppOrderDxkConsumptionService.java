package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/14.
 */
@Service("appOrderDxkConsumptionService")
public class AppOrderDxkConsumptionService implements UpdateService{
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    //更新订单状态 充值定向卡的支付
    @Override
    public Object update(Object object) {
            //跟新主订单和分订单状态
            String out_trade_no = (String) object;
            Order order = new Order();
            order.setOut_trade_no(out_trade_no);

            appOrderMakeSaveDao.updateTradeById(order.getOut_trade_no());
            order.setPay_time(System.currentTimeMillis() + "");

            //完成状态
            appOrderMakeSaveDao.updateOrderByTradeId(order);
            //根据交易号查询订单列表
//            List<Order> orders = appOrderMakeSaveDao.findOrderByTradeNo(order.getOut_trade_no());
        return null;
    }
}
