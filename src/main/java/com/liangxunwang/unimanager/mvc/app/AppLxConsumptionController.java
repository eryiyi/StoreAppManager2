package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.*;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppLxConsumptionController extends ControllerConstants {
    @Autowired
    @Qualifier("lxConsumptionService")
    private ListService lxConsumptionServiceList;

    /**
     * 获得会员自己的消费记录
     * @return
     */
    @RequestMapping(value = "/appGetConsumption", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetConsumption(LxConsumptionQuery query ,Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            Object[] result = (Object[]) lxConsumptionServiceList.list(query);
            long count = (Long) result[1];

            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("lxCardObjService")
    private ListService lxCardObjServicelist;

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

    /**
     * 前台定向充值卡充值 返回该会员应该充值金额
     * @return
     */
    @RequestMapping(value = "/appToChongzhiDxk", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appToChongzhiDxk(String emp_id){
        String money = "";
        try {
            //查询会员详情
//            MemberVO memberVO = (MemberVO) memberFindByIdService.findById(emp_id);
//            map.put("memberVO", memberVO);
            //查看该会员是第几次定向卡充值
            CardEmp cardEmp = (CardEmp) cardEmpServiceExe.execute(emp_id);
            //2.查询定向卡规则
            List<LxCardObj> listCard = (List<LxCardObj>) lxCardObjServicelist.list("");
            LxCardObj lxCardObj = null;
            if(listCard != null && listCard.size()>0){
                lxCardObj = listCard.get(0);
            }
            if(cardEmp != null){
                //说明有这个数据  已经是定向卡会员了
                String countMoney = lxCardObj.getLx_card_count();//规定总金额
                String dijianMoney = lxCardObj.getLx_card_dijian();//规定递减
                String lowMoney = lxCardObj.getLx_card_low();//规定最低金额
                String year= cardEmp.getLx_card_emp_year();//第几年
                Double m = Double.valueOf(countMoney) - Double.valueOf(dijianMoney)*Integer.parseInt(year);//总金额减去 递减的
                if(m<Double.valueOf(lowMoney)){
                    m = Double.valueOf(lowMoney);
                }
                money = String.valueOf(m);
            }else{
                //说明不是定向卡会员 第一次充值定向卡 充值金额为满额
                money = lxCardObj.getLx_card_count();
            }
            DataTip tip = new DataTip();
            tip.setData(new DxkMoneyObj(money));
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            return toJSONString(ERROR_1);
        }
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

    /**
     * 前台定向充值卡充值之后  更新订单和充值记录
     * @return
     */
    @RequestMapping(value = "/appChongzhiDxk", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appChongzhiDxk(String out_trade_no) throws Exception {
        //根据out_trade_no 查询订单详情
        List<Order> listOrders = (List<Order>) appOrderFindByTradeNoService.execute(out_trade_no);
        if(listOrders != null && listOrders.size() > 0){
            //说明有订单
        }else{
            return toJSONString(ERROR_1);
        }
        Order order = listOrders.get(0);
        try {
            if(order != null ){
                if("5".equals(order.getStatus())){
                    //说明已经处理过了
                    return toJSONString(ERROR_1);
                }
                //更新订单状态
                appOrderDxkConsumptionService.update(out_trade_no);

                LxConsumption lxConsumption = new LxConsumption();
                lxConsumption.setLx_consumption_type("1");
                lxConsumption.setOrder_no(order.getOrder_no());
                lxConsumption.setEmp_id(order.getEmp_id());
                lxConsumption.setLx_consumption_count(String.valueOf(order.getPayable_amount()));
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

            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            return toJSONString(ERROR_1);
        }

    }

}
