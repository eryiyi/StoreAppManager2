package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.*;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.model.LxAttribute;
import com.liangxunwang.unimanager.model.Rate;
import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.query.SettlementQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/19.
 */
@Service("orderService")
public class OrderService implements ListService, DeleteService, FindService, UpdateService, ExecuteService {

    @Autowired
    @Qualifier("orderDao")
    private OrderDao orderDao;

    @Autowired
    @Qualifier("rateDao")
    private RateDao rateDao;

    @Override
    public Object delete(Object object) throws ServiceException {
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        if (object instanceof OrdersQuery){
            OrdersQuery query = (OrdersQuery) object;
            String empId = query.getEmpId();
            int index = (query.getIndex() - 1) * query.getSize();
            int size =  query.getSize();

            Map<String, Object> map = new HashMap<String, Object>();
            map.put("index", index);
            map.put("size", size);
            if (!StringUtil.isNullOrEmpty(empId)) {
                map.put("empId", empId);
            }
            if (!StringUtil.isNullOrEmpty(query.getEmpName())){
                map.put("empName", query.getEmpName());
            }
            if (!StringUtil.isNullOrEmpty(query.getEmpPhone())){
                map.put("empPhone", query.getEmpPhone());
            }
            if (!StringUtil.isNullOrEmpty(query.getOrderStatus())){
                map.put("orderStatus", query.getOrderStatus());
            }
            if (!StringUtil.isNullOrEmpty(query.getPayStatus())){
                map.put("payStatus", query.getPayStatus());
            }
            if (!StringUtil.isNullOrEmpty(query.getDistribStatus())){
                map.put("distribStatus", query.getDistribStatus());
            }
            if (!StringUtil.isNullOrEmpty(query.getIs_dxk_order())){
                map.put("is_dxk_order", query.getIs_dxk_order());
            }

            if (!StringUtil.isNullOrEmpty(query.getIs_zhiying())){
                map.put("is_zhiying", query.getIs_zhiying());
            }
            if (!StringUtil.isNullOrEmpty(query.getTrade_type())){
                map.put("trade_type", query.getTrade_type());
            }

            List<OrdersVO> list = orderDao.listOrders(map);
            long count = orderDao.count(map);

            return new Object[]{list, count};
        }else if (object instanceof SettlementQuery ){
            SettlementQuery query = (SettlementQuery) object;
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("index", 1);
            map.put("size", Integer.MAX_VALUE);
            map.put("orderStatus", "5");//订单完成状态
            if (!StringUtil.isNullOrEmpty(query.getEmpId())) {
                map.put("empId", query.getEmpId());
            }
            if (!StringUtil.isNullOrEmpty(query.getDate())) {
                Object[] times = DateUtil.getDayInterval(DateUtil.getMs(query.getDate(), "MM/dd/yyyy"), 0);
                map.put("startTime", times[0]);
                map.put("endTime", times[1]);
            }

            List<OrdersVO> list = orderDao.listOrders(map);
            return list;

        }

        return null;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        if (object instanceof String){
            String orderNo = (String) object;
            return orderDao.findById(orderNo);
        }
        return null;
    }


    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("lxAttributeDao")
    private LxAttributeDao lxAttributeDao;

    static int i = 0;
    List<MemberVO> lists = new ArrayList<MemberVO>();
    //根据会员ID查询他的上级所有关系-直到找到店长
    List<MemberVO> getListMemberVo(String emp_id){
        //emp_id： A充值的话，emp_id就是A的上级B
        MemberVO memberVO = (MemberVO) memberDao.findInfoById(emp_id);
        lists.add(memberVO);
        if(!memberVO.getLx_attribute_id().equals("2")){
            getListMemberVo(memberVO.getEmp_up());
        }else {
            return lists;
        }
        return lists;
    }

    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Autowired
    @Qualifier("countRecordDao")
    private CountRecordDao countRecordDao;

    @Override
    public Object update(Object object) {
        //7天自动收货
        Long t = Constants.DAY_MILLISECOND*7;
        //先查询所有订单  处理返利
        List<OrdersVO>  listOrders = orderDao.listOrdersEnd(System.currentTimeMillis() + "", t + "");
        //更新订单为以完成状态
        orderDao.updateOrderStatus(System.currentTimeMillis()+"", t+"");

        //处理返利
        if(listOrders != null){
            for(OrdersVO ordersVO:listOrders){

                //--------------------给卖家返利------------------------------
                //销售总额
                Float payable_amount = ordersVO.getPayable_amount();
                //利润
                String pv_amount = ordersVO.getPv_amount()==null?"0":ordersVO.getPv_amount();

                Float jf = payable_amount - Float.valueOf(pv_amount);

                //把订单的钱给卖家
                //增加他的积分
                countDao.updateScore(ordersVO.getSeller_emp_id(), String.valueOf(jf));
                //添加积分变动记录
                CountRecord countRecordSeller = new CountRecord();
                countRecordSeller.setLx_count_record_id(UUIDFactory.random());
                countRecordSeller.setDateline(System.currentTimeMillis() + "");
                countRecordSeller.setEmp_id(ordersVO.getSeller_emp_id());
                countRecordSeller.setLx_count_record_count("+" + String.valueOf(jf));
                countRecordSeller.setLx_count_record_cont(ordersVO.getEmpName()+ "("+ordersVO.getPhone()+")消费订单" + jf + "元");
                countRecordDao.save(countRecordSeller);
                //-----------------------------------------------------

                //查询消费者ID
                String emp_id = ordersVO.getEmp_id();
//                String pv_amount = ordersVO.getPv_amount();//商品的pv在订单中的总和 这是返利用到的（商品pv,pv即利润）
                //根据会员ID查询会员详情
                MemberVO memberVO = (MemberVO) memberDao.findInfoById(emp_id);

                if(memberVO != null && !StringUtil.isNullOrEmpty(memberVO.getEmp_up())){
                    //根据emp_id查询他的分销返利上级路线
                    //1.查询消费者的--所有上级返利关系 直到店长
                    lists.clear();
                    List<MemberVO> lists = getListMemberVo(memberVO.getEmp_up());
                    if(lists != null && !StringUtil.isNullOrEmpty(pv_amount) && !"0".equals(pv_amount) && !"0.0".equals(pv_amount)){
                        List<MemberVO> listsArea = new ArrayList<MemberVO>();//县级会员 只能放一个
                        List<MemberVO> listsCity = new ArrayList<MemberVO>();//市级代理 只能放一个
                        List<MemberVO> listsProvince = new ArrayList<MemberVO>();//省级代理 只能放一个
                        List<MemberVO> listsDianzhang = new ArrayList<MemberVO>();//店长 只能放一个
                        for(MemberVO memberVO1:lists){
                            if(listsDianzhang.size()>0){
                                //说明已经返利到店长级别了 返回即可
                                break;
                            }else{
                                //分销等级ID，返利用； 默认0是普通等级，1是普通返利会员  2是店长
                                switch (Integer.parseInt(memberVO1.getLx_attribute_id())){
                                    case 0:
                                    {
                                        //返利会员：省 市 县
                                        if(listsProvince.size()>0){
                                            //说明省会员有了
                                            if(listsCity.size()>0){
                                                //说明有市级会员了
                                                if(listsArea.size()>0){
                                                    //说明有县级会员 不能继续返利了
                                                }else {
                                                    //没有县级会员 可以添加
                                                    LxAttribute lxAttribute = lxAttributeDao.findByNick("3");
                                                    if (!StringUtil.isNullOrEmpty(lxAttribute.getLx_attribute_rate())) {
                                                        Double jifenCount = Double.parseDouble(pv_amount) * (Double.parseDouble(lxAttribute.getLx_attribute_rate()) * 0.01);//增加的积分
                                                        //增加他的积分
                                                        countDao.updateScore(memberVO1.getEmpId(), String.valueOf(jifenCount));
                                                        //添加积分变动记录
                                                        CountRecord countRecord = new CountRecord();
                                                        countRecord.setLx_count_record_id(UUIDFactory.random());
                                                        countRecord.setDateline(System.currentTimeMillis()+"");
                                                        countRecord.setEmp_id(memberVO1.getEmpId());
                                                        countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                                        countRecord.setLx_count_record_cont(memberVO.getEmpName() + "购物消费利润pv" + pv_amount + "元");
                                                        countRecordDao.save(countRecord);
                                                    }
                                                    listsArea.add(memberVO1);
                                                }
                                            }else {
                                                //没有市级会员 可以添加
                                                LxAttribute lxAttribute = lxAttributeDao.findByNick("2");
                                                if (!StringUtil.isNullOrEmpty(lxAttribute.getLx_attribute_rate())) {
                                                    Double jifenCount = Double.parseDouble(pv_amount) * (Double.parseDouble(lxAttribute.getLx_attribute_rate()) * 0.01);//增加的积分
                                                    //增加他的积分
                                                    countDao.updateScore(memberVO1.getEmpId(), String.valueOf(jifenCount));
                                                    //添加积分变动记录
                                                    CountRecord countRecord = new CountRecord();
                                                    countRecord.setLx_count_record_id(UUIDFactory.random());
                                                    countRecord.setDateline(System.currentTimeMillis()+"");
                                                    countRecord.setEmp_id(memberVO1.getEmpId());
                                                    countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                                    countRecord.setLx_count_record_cont(memberVO.getEmpName() + "购物消费利润pv" + pv_amount + "元");
                                                    countRecordDao.save(countRecord);
                                                }
                                                listsCity.add(memberVO1);
                                            }
                                        }else{
                                            //说明没有省会员 可以添加
                                            LxAttribute lxAttribute = lxAttributeDao.findByNick("1");
                                            if (!StringUtil.isNullOrEmpty(lxAttribute.getLx_attribute_rate())) {
                                                Double jifenCount = Double.parseDouble(pv_amount) * (Double.parseDouble(lxAttribute.getLx_attribute_rate()) * 0.01);//增加的积分
                                                //增加他的积分
                                                countDao.updateScore(memberVO1.getEmpId(), String.valueOf(jifenCount));
                                                //添加积分变动记录
                                                CountRecord countRecord = new CountRecord();
                                                countRecord.setLx_count_record_id(UUIDFactory.random());
                                                countRecord.setDateline(System.currentTimeMillis()+"");
                                                countRecord.setEmp_id(memberVO1.getEmpId());
                                                countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                                countRecord.setLx_count_record_cont(memberVO.getEmpName() + "购物消费利润pv" + pv_amount + "元");
                                                countRecordDao.save(countRecord);
                                            }
                                            listsProvince.add(memberVO1);
                                        }
                                    }
                                    break;
                                    case 2:
                                    {
                                        //店长
                                        if(listsDianzhang.size() > 0){
                                            //说明返利到店长级别了
                                            break;
                                        }else {
                                            //说明还没有返利到店长 可以继续返利
                                            LxAttribute lxAttribute = lxAttributeDao.findByNick("4");
                                            if (!StringUtil.isNullOrEmpty(lxAttribute.getLx_attribute_rate())) {
                                                Double jifenCount = Double.parseDouble(pv_amount) * (Double.parseDouble(lxAttribute.getLx_attribute_rate()) * 0.01);//增加的积分
                                                //增加他的积分
                                                countDao.updateScore(memberVO1.getEmpId(), String.valueOf(jifenCount));
                                                //添加积分变动记录
                                                CountRecord countRecord = new CountRecord();
                                                countRecord.setLx_count_record_id(UUIDFactory.random());
                                                countRecord.setDateline(System.currentTimeMillis()+"");
                                                countRecord.setEmp_id(memberVO1.getEmpId());
                                                countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                                countRecord.setLx_count_record_cont(memberVO.getEmpName() + "购物消费利润pv" + pv_amount + "元");
                                                countRecordDao.save(countRecord);
                                            }
                                            listsDianzhang.add(memberVO1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                    }
                }


            }
        }

        return 200;
    }

    @Override
    public Object execute(Object object)  {
        if (object instanceof SettlementQuery){
            SettlementQuery query = (SettlementQuery) object;
            Map<String,Object> map = new HashMap<String, Object>();
            if (!StringUtil.isNullOrEmpty(query.getEmpId())){
                map.put("empId", query.getEmpId());
            }
            Rate rate = rateDao.findById("1");
            Object[] results = DateUtil.getDayInterval(DateUtil.getMs(query.getDate(), "MM/dd/yyyy"), 0);
            map.put("startTime", results[0]);
            map.put("endTime", results[1]);
            Settlement settlement = orderDao.settlement(map);
            if (settlement != null) {
                settlement.setDate((String) results[2]);
                settlement.setRate(rate.getRate());
            }

        }else if (object instanceof Object[]){
            Object[] params = (Object[]) object;
            String date = (String) params[0];
            String empId = (String) params[1];
            Object[] times = DateUtil.getDayInterval(DateUtil.getMs(date, "MM/dd/yyyy"), 0);
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("empId", empId);
            map.put("startTime", times[0]);
            map.put("endTime", times[1]);
            orderDao.updateOrderAccount(map);
        }
        return null;
    }
}
