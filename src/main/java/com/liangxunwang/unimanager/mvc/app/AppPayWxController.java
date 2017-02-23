package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import net.sf.json.JSONObject;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppPayWxController extends ControllerConstants {

    /**
     * 微信支付回调
     * @return
     */
    @RequestMapping(value = "/payWxNotifyAction",   produces = "text/html;charset=UTF-8", method={RequestMethod.POST})
    @ResponseBody
    public String payWxNotifyAction(HttpServletRequest request, HttpServletResponse response) throws IOException, DocumentException {

            // 解析结果存储在HashMap
            Map<String, String> map = new HashMap<String, String>();
            InputStream inputStream = request.getInputStream();

            // 读取输入流
            SAXReader reader = new SAXReader();
            Document document = reader.read(inputStream);
            // 得到xml根元素
            Element root = document.getRootElement();
            // 得到根元素的所有子节点
            List<Element> elementList = root.elements();

            // 遍历所有子节点
            for (Element e : elementList) {
                map.put(e.getName(), e.getText());
            }

//            JSONObject  = JSONObject.fromObject(map);
            String json = ControllerConstants.toJSONString(map);

            System.out.println("===消息通知的结果：" + json.toString() + "==========================");
            System.out.println("===return_code===" + map.get("return_code"));
            System.out.println("===return_msg===" + map.get("return_msg"));
            System.out.println("===out_trade_no===" + map.get("out_trade_no"));

            //验证签名的过程

            //判断是否支付成功
            if(map.get("return_code").equals("SUCCESS")) {

                /**
                 *支付成功之后的业务处理
                 */
                String out_trade_no = map.get("out_trade_no") ;
                if(!StringUtil.isNullOrEmpty(out_trade_no)){
                    synchronized (this){
                        updateOrder(out_trade_no);
                    }
                }

                // 释放资源
                inputStream.close();
                inputStream = null;


                //bis.close();
                return "SUCCESS";
            }

            if (map.get("return_code").equals("FAIL")) {

                /**
                 *支付失败后的业务处理
                 */

                // 释放资源
                inputStream.close();

                inputStream = null;

                return "SUCCESS";
            }

            // 释放资源
            inputStream.close();
            inputStream = null;

            return "SUCCESS";

    }


    /**
     * 微信支付回调定向卡的
     * @return
     */
    @RequestMapping(value = "/payWxNotifyActionDxk",   produces = "text/html;charset=UTF-8", method={RequestMethod.POST})
    @ResponseBody
    public String payWxNotifyActionDxk(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

//            JSONObject  = JSONObject.fromObject(map);
        String json = ControllerConstants.toJSONString(map);

        System.out.println("===消息通知的结果：" + json.toString() + "==========================");
        System.out.println("===return_code===" + map.get("return_code"));
        System.out.println("===return_msg===" + map.get("return_msg"));
        System.out.println("===out_trade_no===" + map.get("out_trade_no"));

        //验证签名的过程

        //判断是否支付成功
        if(map.get("return_code").equals("SUCCESS")) {

            /**
             *支付成功之后的业务处理
             * 定向卡
             */
            String out_trade_no = map.get("out_trade_no") ;
            if(!StringUtil.isNullOrEmpty(out_trade_no)){
                synchronized (this){
                    updateDxk(out_trade_no);
                }

            }

            // 释放资源
            inputStream.close();
            inputStream = null;


            //bis.close();
            return "SUCCESS";
        }

        if (map.get("return_code").equals("FAIL")) {

            /**
             *支付失败后的业务处理
             */

            // 释放资源
            inputStream.close();

            inputStream = null;

            return "SUCCESS";
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return "SUCCESS";

    }


    /**
     * 微信支付回调零钱
     * @return
     */
    @RequestMapping(value = "/payWxNotifyActionLq",   produces = "text/html;charset=UTF-8", method={RequestMethod.POST})
    @ResponseBody
    public String payWxNotifyActionLq(HttpServletRequest request, HttpServletResponse response) throws Exception {

        // 解析结果存储在HashMap
        Map<String, String> map = new HashMap<String, String>();
        InputStream inputStream = request.getInputStream();

        // 读取输入流
        SAXReader reader = new SAXReader();
        Document document = reader.read(inputStream);
        // 得到xml根元素
        Element root = document.getRootElement();
        // 得到根元素的所有子节点
        List<Element> elementList = root.elements();

        // 遍历所有子节点
        for (Element e : elementList) {
            map.put(e.getName(), e.getText());
        }

//            JSONObject  = JSONObject.fromObject(map);
        String json = ControllerConstants.toJSONString(map);

        System.out.println("===消息通知的结果：" + json.toString() + "==========================");
        System.out.println("===return_code===" + map.get("return_code"));
        System.out.println("===return_msg===" + map.get("return_msg"));
        System.out.println("===out_trade_no===" + map.get("out_trade_no"));

        //验证签名的过程

        //判断是否支付成功
        if(map.get("return_code").equals("SUCCESS")) {

            /**
             *支付成功之后的业务处理
             */
            String out_trade_no = map.get("out_trade_no") ;
            if(!StringUtil.isNullOrEmpty(out_trade_no)){
                synchronized (this){
                    UpdateLq(out_trade_no);
                }

            }

            // 释放资源
            inputStream.close();
            inputStream = null;


            //bis.close();
            return "SUCCESS";
        }

        if (map.get("return_code").equals("FAIL")) {

            /**
             *支付失败后的业务处理
             */

            // 释放资源
            inputStream.close();

            inputStream = null;

            return "SUCCESS";
        }

        // 释放资源
        inputStream.close();
        inputStream = null;

        return "SUCCESS";

    }



    @Autowired
    @Qualifier("appOrderDxkConsumptionService")
    private UpdateService appOrderDxkConsumptionService;

    @Autowired
    @Qualifier("appOrderFindByTradeNoService")
    private ExecuteService appOrderFindByTradeNoService;

    @Autowired
    @Qualifier("dxkChongzhiReturnCountService")
    private ExecuteService dxkChongzhiReturnCountService;

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpServiceExe;

    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdService;


    @Autowired
    @Qualifier("cardEmpService")
    private SaveService cardEmpServiceSave;

    @Autowired
    @Qualifier("cardEmpService")
    private UpdateService cardEmpServiceUpdate;

    @Autowired
    @Qualifier("memberUpdateDxkByIdService")
    private UpdateService memberUpdateDxkByIdService;

    @Autowired
    @Qualifier("lxConsumptionService")
    private SaveService lxConsumptionServiceSave;

    int updateDxk(String out_trade_no) throws Exception {
        //根据out_trade_no 查询订单详情
        List<Order> listOrders = (List<Order>) appOrderFindByTradeNoService.execute(out_trade_no);
        if(listOrders != null && listOrders.size() > 0){
            //说明有订单
        }else{
            return 1;
        }
        Order order = listOrders.get(0);//定向卡订单
        if(order != null){
            if("5".equals(order.getStatus())){
                //说明已经处理过了
                return 1;
            }
            //更新订单状态
            appOrderDxkConsumptionService.update(out_trade_no);

//            listOrders.get(0).setStatus("5");
            LxConsumption lxConsumption = new LxConsumption();
            lxConsumption.setLx_consumption_type("1");
            lxConsumption.setOrder_no(order.getOrder_no());
            lxConsumption.setEmp_id(order.getEmp_id());
            lxConsumption.setLx_consumption_count(String.valueOf(order.getPayable_amount()));
            //充值
            if(StringUtil.isNullOrEmpty(lxConsumption.getLx_consumption_count()) || "0".equals(lxConsumption.getLx_consumption_count())){
                return 1;//金额为空或0
            }
            if(StringUtil.isNullOrEmpty(lxConsumption.getEmp_id())){
                return 2;//会员不存在，请检查会员！
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
                cardEmp.setLx_card_emp_end_time(DateUtil.getMs(lx_card_emp_end_time, "yyyy-MM-dd HH:mm:ss.SSS") + "");
                cardEmpServiceUpdate.update(cardEmp);
            }else{
                //插入定向卡信息
                cardEmp = new CardEmp();
                cardEmp.setEmp_id(lxConsumption.getEmp_id());
                String endDate = DateUtil.getCurrentDateTime();
                String year = endDate.substring(0,4);
                int yearInt = Integer.parseInt(year)+1;//获得加一年的年份
                String lx_card_emp_end_time = endDate.replace(year, String.valueOf(yearInt));
                cardEmp.setLx_card_emp_end_time(DateUtil.getMs(lx_card_emp_end_time, "yyyy-MM-dd HH:mm:ss") + "");
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
        }

        return 200;

    }

    @Autowired
    @Qualifier("appOrderMakeService")
    private UpdateService appOrderUpdateService;
    //普通订单支付成功的处理
    void updateOrder(String out_trade_no){
        Order order = new Order();
        order.setOut_trade_no(out_trade_no);
        appOrderUpdateService.update(order);
    }


    @Autowired
    @Qualifier("appLingqianChongzhiService")
    private ListService appLingqianChongzhiServiceList;

    @Autowired
    @Qualifier("orderUpdateService")
    private UpdateService orderUpdateServiceUpdate;

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


    int UpdateLq(String out_trade_no) throws Exception {
            List<Order> orders = (List<Order>) appLingqianChongzhiServiceList.list(out_trade_no);
            if(orders != null && orders.size() > 0){
//            for(Order order:orders){
                Order order = orders.get(0);
                if(order != null){
                    if("5".equals(order.getStatus())){
                        return 1;
                    }
                    //更新这个订单
                    order.setStatus("5");
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
       return 200;
    }
}
