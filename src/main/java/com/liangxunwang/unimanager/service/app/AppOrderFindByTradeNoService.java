package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.service.ExecuteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appOrderFindByTradeNoService")
public class AppOrderFindByTradeNoService implements ExecuteService {
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeDao;

    @Override
    public Object execute(Object object) {
        String out_trade_no = (String) object;
        List<Order> list = appOrderMakeDao.findOrderByTradeNo(out_trade_no);
        return list;
    }
}
