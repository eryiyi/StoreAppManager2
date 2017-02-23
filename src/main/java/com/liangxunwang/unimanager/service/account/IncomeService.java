package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.mvc.vo.ContractSchoolVO;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.IncomeQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzh on 2015/8/22.
 */
@Service("incomeService")
public class IncomeService implements ListService {

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Override
    public Object list(Object object) throws ServiceException {
        if (object instanceof IncomeQuery){
            IncomeQuery query = (IncomeQuery) object;
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();

            Map<String,Object> map = new HashMap<String, Object>();

            map.put("index", index);
            map.put("size", size);
            map.put("orderStatus", "5");
            if (!StringUtil.isNullOrEmpty(query.getStartTime())){
                map.put("startTime", DateUtil.getMs(query.getStartTime(), "MM/dd/yyyy"));
            }else {
                map.put("startTime", DateUtil.getStartDay());
            }
            if (!StringUtil.isNullOrEmpty(query.getEndTime())){
                map.put("endTime", DateUtil.getMs(query.getEndTime(), "MM/dd/yyyy")+ Constants.DAY_MILLISECOND);
            }else {
                map.put("endTime", DateUtil.getEndDay());
            }

            if(!StringUtil.isNullOrEmpty(query.getEmpId())){
                map.put("empId", query.getEmpId());
            }

            List<OrdersVO> list = orderDao.listOrders(map);
            Long count = orderDao.count(map);
            Float income = orderDao.income(map);

            return new Object[]{list, count, income};
        }
        return null;
    }


}
