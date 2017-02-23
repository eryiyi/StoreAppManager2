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
 */
@Service("orderReturnService")
public class OrderReturnService implements UpdateService {

    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeDao;

    @Autowired
    @Qualifier("minePackageDao")
    private MinePackageDao minePackageDao;

    @Override
    public Object update(Object object) {
        OrdersVO vo = (OrdersVO) object;
        //更新订单退款状态
        Order order = new Order();
        order.setOrder_no(vo.getOrder_no());
        order.setStatus("5");//标记完成
        appOrderMakeDao.updateOrderReturnDone(order);
        //退款给用户
        float pay_count = vo.getPayable_amount();//金额
        String emp_id = vo.getEmp_id();//要退给用户emp

        //构造钱包
        MinePackage minePackage = new MinePackage();
        minePackage.setPackage_money(String.valueOf(pay_count));
        minePackage.setEmp_id(emp_id);
        minePackageDao.update(minePackage);//更新钱包
        return null;
    }
}
