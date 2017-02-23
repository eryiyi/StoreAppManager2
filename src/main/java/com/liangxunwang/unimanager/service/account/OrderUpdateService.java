package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.dao.MinePackageDao;
import com.liangxunwang.unimanager.model.MinePackage;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/8/19.
 * 根据订单号 更新订单
 */
@Service("orderUpdateService")
public class OrderUpdateService implements UpdateService {

    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeDao;


    @Override
    public Object update(Object object) {
        Order vo = (Order) object;
        appOrderMakeDao.updateOrderByOrderNo(vo);
        return null;
    }
}
