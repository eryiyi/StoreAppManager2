package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.OrderDao;
import com.liangxunwang.unimanager.dao.RateDao;
import com.liangxunwang.unimanager.dao.SellerGoodsDao;
import com.liangxunwang.unimanager.model.Rate;
import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.model.SettlementSellers;
import com.liangxunwang.unimanager.query.SettlementQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by liuzh on 2015/8/24.
 */
@Service("settlementService")
public class SettlementService implements ListService, ExecuteService {

    @Autowired
    @Qualifier("rateDao")
    private RateDao rateDao;

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("sellerGoodsDao")
    private SellerGoodsDao sellerGoodsDao;

    @Override
    public Object list(Object object) throws ServiceException {
        if (object instanceof SettlementQuery){

            SettlementQuery query = (SettlementQuery) object;

            List<Settlement> list = new ArrayList<Settlement>();

            Map<String,Object> map = new HashMap<String, Object>();
            if (!StringUtil.isNullOrEmpty(query.getEmpId())){
                map.put("empId", query.getEmpId());
            }

            Rate rate = rateDao.findById("1");
            //如果是空的默认查10天的数据
            if (StringUtil.isNullOrEmpty(query.getDate())){
                for (int i=0; i<10; i++){
                    Object[] results = DateUtil.getDayInterval(System.currentTimeMillis(), i);
                    map.put("startTime", results[0]);
                    map.put("endTime", results[1]);
                    Settlement settlement = orderDao.settlement(map);
                    if (settlement != null) {
                        settlement.setDate((String) results[2]);
                        settlement.setRate(rate.getRate());
                        list.add(settlement);
                    }
                }
            }else {
                Object[] results = DateUtil.getDayInterval(DateUtil.getMs(query.getDate(), "MM/dd/yyyy"), 0);
                map.put("startTime", results[0]);
                map.put("endTime", results[1]);
                Settlement settlement = orderDao.settlement(map);
                if (settlement != null) {
                    settlement.setDate((String) results[2]);
                    settlement.setRate(rate.getRate());
                    list.add(settlement);
                }
            }

            return list;

        }
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        if (object instanceof SettlementQuery){
            SettlementQuery query = (SettlementQuery) object;

            Map<String,Object> map = new HashMap<String, Object>();
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();
            map.put("index", index);
            map.put("size", size);
            if (StringUtil.isNullOrEmpty(query.getDate())){//默认查7天前的是否结算的单子
                Object[] results = DateUtil.getDayInterval(System.currentTimeMillis(), 7);
                map.put("startTime", results[0]);
                map.put("endTime", results[1]);
            }else {//有查询条件的时候查某一天的
                Object[] results = null;
                results= DateUtil.getDayInterval(DateUtil.getMs(query.getDate(), "MM/dd/yyyy"), 0);
                map.put("startTime", results[0]);
                map.put("endTime", results[1]);
            }
            if (StringUtil.isNullOrEmpty(query.getIsAccount())){
                map.put("isAccount", "0");
            }
            List<SettlementSellers> list = sellerGoodsDao.settlementSellers(map);
            long count = sellerGoodsDao.settlementSellersCount(map);
            return new Object[]{list, count};
        }
        return null;
    }
}
