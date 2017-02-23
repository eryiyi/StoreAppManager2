package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AppOrderMakeDao;
import com.liangxunwang.unimanager.model.Order;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 生成定向卡订单
 */
@Service("orderDxkService")
public class OrderDxkService implements  SaveService {

    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Order order = (Order) object;
        //判断该会员该店铺是否今天刷过单了 ，一天只能刷一次
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("startTime", DateUtil.getStartDay());
        map.put("endTime", DateUtil.getEndDay());

        map.put("emp_id", order.getEmp_id());
        map.put("seller_emp_id", order.getSeller_emp_id());
        Order order1 = appOrderMakeDao.findIsExist(map);
        if(order1 != null){
            //说明存在了
            throw new ServiceException("has_exist");
        }
        order.setOrder_no(UUIDFactory.random());
        order.setCreate_time(System.currentTimeMillis()+"");
        order.setPay_time(System.currentTimeMillis()+"");
        order.setStatus("2");
        order.setPay_status("1");
        order.setIs_dxk_order("1");
        appOrderMakeDao.saveDxkOrder(order);
        return null;
    }
}
