package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.LxConsumption;
import com.liangxunwang.unimanager.model.MinePackage;
import com.liangxunwang.unimanager.model.OrderInfoAndSign;
import com.liangxunwang.unimanager.mvc.vo.OrderVo;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.Map;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appOrderSingleLqService")
public class AppOrderSingleLqService implements SaveService {
//    private static Logger logger = LogManager.getLogger(AppOrderService.class);
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("relateDao")
    private RelateDao relateDao;

    @Autowired
    @Qualifier("minePackageDao")
    private MinePackageDao minePackageDao;

    @Autowired
    @Qualifier("lxConsumptionDao")
    private LxConsumptionDao lxConsumptionDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Map<String,String> map = (Map<String, String>) object;
        String order_no = map.get("order_no");
        String doublePricess = map.get("doublePrices");
        Double doublePrices = Double.valueOf(doublePricess);
        //根据订单号 查询订单详情
        OrderVo orderVo = appOrderMakeSaveDao.findOrderByOrderNo(order_no);
        //判断金额是否大于他的零钱--零钱是否足够支付
        MinePackage minePackage = minePackageDao.findById(orderVo.getEmp_id());
        if(minePackage != null){
            String package_money = minePackage.getPackage_money();//零钱
            if(!StringUtil.isNullOrEmpty(package_money)){
                Double lingqiang = Double.valueOf(package_money);//零钱的double格式
                if(lingqiang < doublePrices){
                    //如果零钱不够支付
                    throw new ServiceException("money_is_null");
                }
            }else {
                throw new ServiceException("money_is_null");
            }
        }else {
            throw new ServiceException("money_is_null");
        }

        //更新零钱 减去消费的这些
        MinePackage minePackage1 = new MinePackage();
        minePackage1.setEmp_id(orderVo.getEmp_id());
        minePackage1.setPackage_money(String.valueOf(doublePrices));
        minePackageDao.updateDel(minePackage1);

        //保存消费记录
        LxConsumption lxConsumption = new LxConsumption();
        lxConsumption.setLx_consumption_type("0");
        lxConsumption.setOrder_no(orderVo.getOrder_no());
        lxConsumption.setEmp_id(orderVo.getEmp_id());
        lxConsumption.setLx_consumption_cont("购物消费，零钱支付，金额" + orderVo.getPayable_amount());
        lxConsumption.setLx_consumption_count(String.valueOf(orderVo.getPayable_amount()));
        lxConsumption.setLx_consumption_id(UUIDFactory.random());
        lxConsumption.setDateline(System.currentTimeMillis()+"");
        lxConsumptionDao.save(lxConsumption);

        return new OrderInfoAndSign("", "", order_no);
    }

}
