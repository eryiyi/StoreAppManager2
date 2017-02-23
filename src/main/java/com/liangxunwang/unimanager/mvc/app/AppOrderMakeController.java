package com.liangxunwang.unimanager.mvc.app;

import com.google.gson.Gson;
import com.liangxunwang.unimanager.data.OrdersDATA;
import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.mvc.vo.OrderVo;
import com.liangxunwang.unimanager.query.OrdersQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/13.
 */
@Controller
public class AppOrderMakeController extends ControllerConstants{

    @Autowired
    @Qualifier("appOrderMakeService")
    private SaveService appOrderMakeService;
    @Autowired
    @Qualifier("appOrderMakeWxService")
    private SaveService appOrderMakeWxService;

    @Autowired
    @Qualifier("appOrderWxService")
    private SaveService appOrderWxServiceSave;

    @Autowired
    @Qualifier("appOrderMakeService")
    private UpdateService appOrderUpdateService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private ListService appOrderListService;

    @Autowired
    @Qualifier("appOrderMakeService")
    private FindService appOrderFindService;

    @Autowired
    @Qualifier("appOrderService")
    private UpdateService UpateappOrderService;

    @Autowired
    @Qualifier("appOrderService")
    private SaveService SaveappOrderService;
    @Autowired
    @Qualifier("appOrderMakeLqService")
    private SaveService appOrderMakeLqService;

    @Autowired
    @Qualifier("appOrderService")
    private ExecuteService executeOrderService;


    /**
     * 订单接收---形成订单
     * @param list
     * @return
     */
    @RequestMapping("/orderSave")
    @ResponseBody
    public String orderSave(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderMakeService.save(data.getList());
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(ERROR_2);
            }
            if (e.getMessage().equals("pay_zfb_error")){
                return toJSONString(ERROR_3);//支付宝错误
            }
        }
        return null;
    }

    /**
     * 订单接收--补款--单个订单 -支付宝
     * @return
     */
    @RequestMapping("/orderSaveSingle")
    @ResponseBody
    public String orderSaveSingle(String order_no, String doublePrices){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) SaveappOrderService.save(map);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
        }
        return null;
    }

    /**
     * 订单接收---形成订单
     * @param list
     * @return
     */
    @RequestMapping("/orderSaveWx")
    @ResponseBody
    public String orderSaveWx(String list){
        if(StringUtil.isNullOrEmpty(list)){
            return toJSONString(ERROR_1);
        }
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        if(data == null){
            return toJSONString(ERROR_1);
        }
        if(data.getList() == null){
            return toJSONString(ERROR_1);
        }
        try {
            Object[] strs = (Object[]) appOrderMakeWxService.save(data.getList());
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(ERROR_2);
            }
        }
        return null;
    }

    /**
     * 订单接收--补款--单个订单 微信
     * @return
     */
    @RequestMapping("/orderSaveSingleWx")
    @ResponseBody
    public String orderSaveSingleWx(String order_no, String doublePrices){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            Object[] strs = (Object[]) appOrderWxServiceSave.save(map);
            WxPayObj wxPayObj = new WxPayObj();
            wxPayObj.setXmlStr((String) strs[0]);
            wxPayObj.setOut_trade_no((String) strs[1]);
            DataTip tip = new DataTip();
            tip.setData(wxPayObj);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(ERROR_2);
            }
        }
        return null;
    }


    /**
     * 订单接收---形成订单--零钱支付
     * @param list
     * @return
     */
    @RequestMapping("/orderSaveLq")
    @ResponseBody
    public String orderSaveLq(String list){
        OrdersDATA data = new Gson().fromJson(list,OrdersDATA.class);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderMakeLqService.save(data.getList());
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }else
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(ERROR_2);
            }
            else
            if (e.getMessage().equals("money_is_null")){
                return toJSONString(ERROR_3);//零钱不够支付
            }
        }
        return null;
    }


    @Autowired
    @Qualifier("appOrderSingleLqService")
    private SaveService appOrderSingleLqServiceSave;

    /**
     * 订单接收--补款--单个订单 -零钱
     * @return
     */
    @RequestMapping("/orderSaveSingleLq")
    @ResponseBody
    public String orderSaveSingleLq(String order_no, String doublePrices){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no", order_no);
        map.put("doublePrices", doublePrices);
        try {
            OrderInfoAndSign orderInfoAndSign = (OrderInfoAndSign) appOrderSingleLqServiceSave.save(map);
            DataTip tip = new DataTip();
            tip.setData(orderInfoAndSign);
            return toJSONString(tip);
        }catch (Exception e){
            if (e.getMessage().equals("ISWRONG")){
                return toJSONString(ERROR_1);
            }else
            if (e.getMessage().equals("outOfNum")){
                return toJSONString(ERROR_2);
            }
            else
            if (e.getMessage().equals("money_is_null")){
                return toJSONString(ERROR_3);//零钱不够支付
            }
        }
        return null;
    }


    /**
     * 订单更新，支付订单成功
     * @param order
     * @return
     */
    @RequestMapping("/orderUpdate")
    @ResponseBody
    public String orderUpdate(Order order){
        appOrderUpdateService.update(order);
        return toJSONString(SUCCESS);
    }

    /**
     * 查询订单列表--会员查询
     * */
    @RequestMapping(value = "/listOrders", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listGoodsByType(OrdersQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            query.setEmptype("0");//普通会员查询订单
            List<OrderVo> list = (List<OrderVo>) appOrderListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //取消订单  确认收货  更改订单状态
    @RequestMapping("/updateOrder")
    @ResponseBody
    public String updateOrder(String order_no ,String status, String returnMsg, String returnOrder){
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no",order_no);
        map.put("status",status);
        if("7".equals(status)){
            map.put("returnMsg",returnMsg);
            map.put("returnOrder",returnOrder);
        }
        UpateappOrderService.update(map);
        return toJSONString(SUCCESS);
    }

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpServiceExe;
    @Autowired
    @Qualifier("cardEmpService")
    private UpdateService cardEmpServiceUpdate;

    @Autowired
    @Qualifier("memberUpdateDxkByIdService")
    private UpdateService memberUpdateDxkByIdService;

    @Autowired
    @Qualifier("lxConsumptionService")
    private SaveService lxConsumptionServiceSave;

    @Autowired
    @Qualifier("cardEmpService")
    private SaveService cardEmpServiceSave;

    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdService;

    @Autowired
    @Qualifier("dxkChongzhiReturnCountService")
    private ExecuteService dxkChongzhiReturnCountService;

    /**
     * 订单更新--更新单一订单
     * @param order_no  单一订单号  注意 不是主订单号  是order表自己的订单号
     * @return
     */
    @RequestMapping("/orderUpdateSingle")
    @ResponseBody
    public String orderUpdateSingle(String order_no){
        appOrderUpdateService.update(order_no);

        OrderVo order = (OrderVo) appOrderFindService.findById(order_no);
        if("2".equals(order.getIs_dxk_order())){
            //如果是充值定向卡，需要特别处理
            LxConsumption lxConsumption = new LxConsumption();
            lxConsumption.setLx_consumption_type("1");
            lxConsumption.setOrder_no(order.getOrder_no());
            lxConsumption.setEmp_id(order.getEmp_id());
            lxConsumption.setLx_consumption_count(String.valueOf(order.getPayable_amount()));
            try {
                //充值
                if(StringUtil.isNullOrEmpty(lxConsumption.getLx_consumption_count()) || "0".equals(lxConsumption.getLx_consumption_count())){
                    return toJSONString(ERROR_1);//金额为空或0
                }
                if(StringUtil.isNullOrEmpty(lxConsumption.getEmp_id())){
                    return toJSONString(ERROR_2);//会员不存在，请检查会员！
                }

                //增加充值记录
                lxConsumption.setLx_consumption_cont("app定向卡充值，金额" + lxConsumption.getLx_consumption_count());
                lxConsumptionServiceSave.save(lxConsumption);//增加记录

                //查看该会员是第几次定向卡充值
                CardEmp cardEmp = (CardEmp) cardEmpServiceExe.execute(lxConsumption.getEmp_id());
                if(cardEmp != null){
                    //更新定向卡信息
                    cardEmp.setLx_card_emp_year(String.valueOf(Integer.parseInt(cardEmp.getLx_card_emp_year()) + 1));//上一年的基础上加1年
                    String endDate = DateUtil.getDate(cardEmp.getLx_card_emp_end_time(), "yyyy-MM-dd HH:mm:ss.SSS");//根据毫秒值获得日期
                    String year = endDate.substring(0,4);
                    int yearInt = Integer.parseInt(year)+1;//获得加一年的年份
                    String lx_card_emp_end_time = endDate.replace(year, String.valueOf(yearInt));
//                cardEmp.setLx_card_emp_end_time(lx_card_emp_end_time);
                    cardEmp.setLx_card_emp_end_time(DateUtil.getMs(lx_card_emp_end_time, "yyyy-MM-dd HH:mm:ss.SSS") + "");
                    cardEmpServiceUpdate.update(cardEmp);
                }else{
                    //插入定向卡信息
                    cardEmp = new CardEmp();
                    cardEmp.setEmp_id(lxConsumption.getEmp_id());
                    cardEmp.setLx_card_emp_end_time(DateUtil.getMs(DateUtil.getCurrentDateTime(), "yyyy-MM-dd HH:mm:ss") + "");
                    cardEmpServiceSave.save(cardEmp);
                }
                //更新会员为定向卡会员
                Member member = new Member();
                member.setEmpId(lxConsumption.getEmp_id());
                member.setIs_card_emp("1");//定向卡会员 0否 1是  能购买零元商品
                memberUpdateDxkByIdService.update(member);

                //充值定向卡 给上级定向卡会员返利--返积分
                //1.查询上级
//            MemberVO memberVOUp = (MemberVO) memberFindByIdService.findById(lxConsumption.getEmp_id());
                MemberVO memberVO1 = (MemberVO) memberFindByIdService.findById(member.getEmpId());

                String[] arr = {lxConsumption.getEmp_id(), lxConsumption.getLx_consumption_count(), memberVO1.getLx_dxk_level_id(), memberVO1.getEmpName(), memberVO1.getEmp_up()};//会员ID 充值金额 定向卡等级 会员name, 会员的上级ID
                dxkChongzhiReturnCountService.execute(arr);

            }catch (ServiceException e){
                return toJSONString(ERROR_1);
            } catch (Exception e) {
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }

    /**
     * 查询订单数量
     * time_status 0 今天  1代表总的  2查询今天的收入
     * */
    @RequestMapping("/selectOrderNum")
    @ResponseBody
    public String selectOrderNum(String empId) throws Exception {
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("emp_id",empId);
        map.put("time_status", "0");
        //今天的数量
        String numberDay = (String) executeOrderService.execute(map);
        map.put("time_status", "1");
        //总的数量
        String numberAll = (String) executeOrderService.execute(map);
        //查询今天的收入
        map.put("time_status", "2");
        String pricesAllDay = (String) executeOrderService.execute(map);
        //查询总的收入
        map.put("time_status", "3");
        String pricesAll = (String) executeOrderService.execute(map);

        MineStore mineStore = new MineStore();
        mineStore.setNumberAll(numberAll);
        mineStore.setNumberDay(numberDay);
        mineStore.setPricesAllDay(Float.valueOf(pricesAllDay==null?"0.0":pricesAllDay));
        mineStore.setPricesAll(Float.valueOf(pricesAll==null?"0.0":pricesAll));
        DataTip tip = new DataTip();
        tip.setData(mineStore);
        return toJSONString(tip);
    }


    /**
     * 查询订单列表--商家查询自己的订单
     * */
    @RequestMapping(value = "/listOrdersMng", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listOrdersMng(OrdersQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            query.setEmptype("2");//商家查询自己的订单
            List<OrderVo> list = (List<OrderVo>) appOrderListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //根据订单号查询订单状态
    @RequestMapping(value = "/findOrderByOrderNo", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String findOrderByOrderNo(String orderNo){
        try {
            OrderVo order = (OrderVo) appOrderFindService.findById(orderNo);
            DataTip tip = new DataTip();
            tip.setData(order);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }




    @Autowired
    @Qualifier("appOrderCancelService")
    private ExecuteService appOrderCancelService;
    // 卖家扫一扫订单
    @RequestMapping("/scanOrder")
    public String scanOrder(String order_no ,String status, ModelMap map1) throws Exception {
        Map<String,String> map = new HashMap<String, String>();
        map.put("order_no",order_no);
        map.put("status",status);
        UpateappOrderService.update(map);
        //根据订单号，查询订单详情
        OrderVo orderVo = (OrderVo) appOrderCancelService.execute(order_no);
        map1.put("vo", orderVo);
        return "/order/detailOrder";
    }

    @Autowired
    @Qualifier("appOrderCancelService")
    private UpdateService appOrderCancelServiceUpdate;
    /**
     * 订单更新，已经评价了
     * @return
     */
    @RequestMapping("/orderUpdateComment")
    @ResponseBody
    public String orderUpdateComment(String order_no){
        appOrderCancelServiceUpdate.update(order_no);
        return toJSONString(SUCCESS);
    }

}
