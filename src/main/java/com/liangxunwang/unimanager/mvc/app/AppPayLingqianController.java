package com.liangxunwang.unimanager.mvc.app;

import com.google.gson.Gson;
import com.liangxunwang.unimanager.data.OrdersDATA;
import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 * 零钱充值 -- 支付宝
 */
@Controller
public class AppPayLingqianController extends ControllerConstants {

    @Autowired
    @Qualifier("appLingqianChongzhiService")
    private SaveService appLingqianChongzhiService;

    @Autowired
    @Qualifier("appLingqianChongzhiWxService")
    private SaveService appLingqianChongzhiWxService;


    @Autowired
    @Qualifier("appLingqianChongzhiService")
    private ListService appLingqianChongzhiServiceList;


    /**
     * 零钱充值--支付宝
     * @return
     */
    @RequestMapping(value = "/appLqPayZfb", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appLqPayZfb(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appLingqianChongzhiService.save(data.getList());
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("minePackageService")
    private UpdateService minePackageServiceUpdate;
    @Autowired
    @Qualifier("jifenObjService")
    private ExecuteService jifenObjServiceExe;

    @Autowired
    @Qualifier("countService")
    private UpdateService countServiceUpdate;

    @Autowired
    @Qualifier("countRecordService")
    private SaveService countRecordServiceSave;


    @Autowired
    @Qualifier("lxConsumptionService")
    private SaveService lxConsumptionServiceSave;

    /**
     * 零钱充值--微信
     * @return
     */
    @RequestMapping(value = "/appLqPayWx", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appLqPayWx(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            Object[] strs = (Object[]) appLingqianChongzhiWxService.save(data.getList());
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdService;


    @Autowired
    @Qualifier("orderUpdateService")
    private UpdateService orderUpdateServiceUpdate;

    //更新订单状态 支付成功，零钱增加额度
    @RequestMapping(value = "/appUpdateLqCz", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appUpdateLqCz(String out_trade_no){
        try {
        List<Order> orders = (List<Order>) appLingqianChongzhiServiceList.list(out_trade_no);
        if(orders != null && orders.size() > 0){
//            for(Order order:orders){
            Order order = orders.get(0);
                if(order != null ){
                    if("5".equals(order.getStatus())){
                        return toJSONString(ERROR_1);
                    }
                    order.setStatus("5");
                    //更新这个订单
                    orderUpdateServiceUpdate.update(order);
                    //充值
                    MinePackage minePackage = new MinePackage();
                    minePackage.setEmp_id(order.getEmp_id());//会员ID
                    minePackage.setPackage_money(String.valueOf(order.getPayable_amount()));//充值金额
                    minePackageServiceUpdate.update(minePackage);//零钱更新
                    //增加充值记录
                    LxConsumption lxConsumption = new LxConsumption();
                    lxConsumption.setEmp_id(order.getEmp_id());
                    lxConsumption.setOrder_no(order.getOrder_no());
                    lxConsumption.setLx_consumption_type("2");
                    lxConsumption.setLx_consumption_count(String.valueOf(order.getPayable_amount()));
                    lxConsumption.setLx_consumption_cont("app零钱充值，金额" + order.getPayable_amount());
                    lxConsumptionServiceSave.save(lxConsumption);//消费记录

                    //充值成功 ，给上级增加积分
                    //1.查询上级
                    MemberVO memberVO = (MemberVO) memberFindByIdService.findById(lxConsumption.getEmp_id());
                    if(memberVO != null && !StringUtil.isNullOrEmpty(memberVO.getEmp_up())){
                        //说明存在上级
                        //2.更新上级积分
                        List<JifenObj> listJifens = (List<JifenObj>) jifenObjServiceExe.execute("");//查询推广下线后台充值金额 给上级增加积分的百分率
                        if(listJifens != null && listJifens.size()>0){
                            JifenObj jifenObj = listJifens.get(0);//积分规则
                            if(jifenObj != null){
                                String lx_jifen_one = jifenObj.getLx_jifen_one();//推广下线充值金额的X%
                                if(!StringUtil.isNullOrEmpty(lx_jifen_one)){
                                    Double jifenCount = Double.parseDouble(lxConsumption.getLx_consumption_count())*(Double.parseDouble(lx_jifen_one)*0.01);//增加的积分
                                    //更新上级积分
                                    String[] arr = {memberVO.getEmp_up(), String.valueOf(jifenCount)};
                                    countServiceUpdate.update(arr);//更新上级积分
                                    //添加积分变动记录
                                    CountRecord countRecord = new CountRecord();
                                    countRecord.setEmp_id(memberVO.getEmp_up());
                                    countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                    countRecord.setLx_count_record_cont(memberVO.getEmpName()+"("+memberVO.getEmpMobile()+")app充值零钱" + lxConsumption.getLx_consumption_count()+"元");
                                    countRecordServiceSave.save(countRecord);
                                }
                            }
                        }
                    }
                }
//            }
        }
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            return toJSONString(ERROR_1);
        }
        return toJSONString(SUCCESS);
    }

}
