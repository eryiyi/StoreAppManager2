package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.model.Settlement;
import com.liangxunwang.unimanager.mvc.vo.OrdersVO;
import com.liangxunwang.unimanager.query.SettlementQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by liuzh on 2015/8/24.
 * 与结算相关的接口
 */
@RequestMapping("/settlement")
public class SettlementController extends ControllerConstants {

    @Autowired
    @Qualifier("settlementService")
    private ListService settlementListService;

    @Autowired
    @Qualifier("orderService")
    private ListService orderListService;

    @Autowired
    @Qualifier("orderService")
    private ExecuteService orderExecuteService;

    @Autowired
    @Qualifier("settlementService")
    private ExecuteService settlementExecuteService;

    @RequestMapping("list")
    public String list(SettlementQuery query, HttpSession session , ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(admin != null){
            if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
                query.setEmpId(admin.getEmp_id());
            }
        }
        List<Settlement> list = (List<Settlement>) settlementListService.list(query);
        map.put("list", list);
        map.put("query", query);
        return "/settlement/list";
    }

    /**
     * 结算管理时查看所有的订单详情，已完成的
     * @param query
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("orders")
    public String orders(SettlementQuery query, HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(admin != null){
            if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
                query.setEmpId(admin.getEmp_id());
            }
        }
        List<OrdersVO> list = (List<OrdersVO>) orderListService.list(query);
        map.put("list", list);
        map.put("query", query);
        return "/settlement/orders";
    }

    /**
     * 查看商家的结算账号信息并进行结算
     * @param query
     * @param map
     * @return
     */
    @RequestMapping("endOrder")
    public String endOrder(SettlementQuery query, ModelMap map) throws Exception {
        Object[] results = (Object[]) orderExecuteService.execute(query);
        Settlement settlement = (Settlement) results[0];
        ManagerInfo info = (ManagerInfo) results[1];

        map.put("settlement", settlement);
        map.put("info", info);

        float orderIncome = settlement.getIncome()-settlement.getIncome()*settlement.getRate();
        map.put("income", orderIncome);
        map.put("query", query);

        return "/settlement/endorder";
    }



    @RequestMapping("sellerlist")
    public String sellerlist(SettlementQuery query, ModelMap map, Page page) throws Exception {
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) settlementExecuteService.execute(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));

        map.addAttribute("page", page);
        map.addAttribute("query", query);

        return "/settlement/sellers";
    }

    @RequestMapping("settlementEnd")
    @ResponseBody
    public String settlement(SettlementQuery query) throws Exception {
        Object[] params = new Object[]{query.getDate(), query.getEmpId()};
        orderExecuteService.execute(params);
        return toJSONString(SUCCESS);
    }
}
