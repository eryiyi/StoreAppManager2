package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/8/14.
 */
@Service("appOrderMakeLqService")
public class AppOrderMakeLqService implements SaveService{
    //    private static Logger logger = LogManager.getLogger(AppOrderMakeService.class);
    @Autowired
    @Qualifier("appOrderMakeDao")
    private AppOrderMakeDao appOrderMakeSaveDao;

    @Autowired
    @Qualifier("paopaoGoodsDao")
    private PaopaoGoodsDao paopaoGoodsDao;

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

    //保存订单
    @Override
    public Object save(Object object) throws ServiceException {
        String emp_id = "";
        List<Order> lists = (List<Order>) object;
        //先判断商品剩余数量，是否大于要购买的数量
        for(Order order:lists){
            if(!StringUtil.isNullOrEmpty(order.getEmp_id())){
                emp_id = order.getEmp_id();
            }
            order.getGoods_count();//订单数量
            //根据商品ID查询商品数量
            PaopaoGoodsVO vo = paopaoGoodsDao.findGoodsVO(order.getGoods_id());
            if(vo != null){
                if(!StringUtil.isNullOrEmpty(vo.getCount()) && !StringUtil.isNullOrEmpty(order.getGoods_count())){
                    if(Integer.parseInt(vo.getCount()) < Integer.parseInt(order.getGoods_count())){
                        throw new ServiceException("outOfNum");//超出数量限制
                    }
                }
            }

        }
        Double doublePrices = 0.0;
        if(lists!=null && lists.size() > 0){
            for(Order order:lists){
                doublePrices += order.getPayable_amount();
            }
        }
        doublePrices = Double.parseDouble(String.format("%.2f",doublePrices));
        //判断金额是否大于他的零钱--零钱是否足够支付
        MinePackage minePackage = minePackageDao.findById(emp_id);
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
        //商品总额，用于插入订单金额
        String out_trade_no = UUIDFactory.random();//订单总金额的id
        ShoppingTrade shoppingTrade = new ShoppingTrade();
        shoppingTrade.setOut_trade_no(out_trade_no);
        shoppingTrade.setPay_status("0");
        shoppingTrade.setDateline(System.currentTimeMillis() + "");
        shoppingTrade.setTrade_prices(String.valueOf(doublePrices));

        //保存总订单--和微信支付一致
        appOrderMakeSaveDao.saveTrade(shoppingTrade);

        //构造订单列表
        if(lists!=null && lists.size() > 0){
            for(Order order:lists){
                order.setOrder_no(UUIDFactory.random());
                order.setCreate_time(System.currentTimeMillis() + "");
                order.setStatus("1");//1生成订单
                order.setPay_status("0");//0未支付
                order.setOut_trade_no(out_trade_no);
                order.setIsAccount("0");
                //自动签收时间--accept_time
//                order.setAccept_time(DateUtil.beforLongDate(System.currentTimeMillis(),7));
            }
        }
        //保存订单
        for(Order order:lists){
            appOrderMakeSaveDao.saveList(order);

            //保存消费记录
            LxConsumption lxConsumption = new LxConsumption();
            lxConsumption.setLx_consumption_type("0");
            lxConsumption.setOrder_no(order.getOrder_no());
            lxConsumption.setEmp_id(emp_id);
            lxConsumption.setLx_consumption_cont("购物消费，零钱支付，金额" + order.getPayable_amount());
            lxConsumption.setLx_consumption_count(String.valueOf(order.getPayable_amount()));
            lxConsumption.setLx_consumption_id(UUIDFactory.random());
            lxConsumption.setDateline(System.currentTimeMillis()+"");
            lxConsumptionDao.save(lxConsumption);
        }

        //商品数量要减去已购买的数量
        for(Order order:lists){
            order.getGoods_count();//订单数量
            PaopaoGoodsVO paopaoGoods = paopaoGoodsDao.findGoodsVO(order.getGoods_id());
            paopaoGoodsDao.updateCountById(order.getGoods_id(), order.getGoods_count(), order.getGoods_count());
        }

        //更新零钱 减去消费的这些
        MinePackage minePackage1 = new MinePackage();
        minePackage1.setEmp_id(emp_id);
        minePackage1.setPackage_money(String.valueOf(doublePrices));
        minePackageDao.updateDel(minePackage1);
        return new OrderInfoAndSign("", "", out_trade_no);
    }

}
