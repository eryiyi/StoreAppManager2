package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CardEmp;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.query.CardEmpQuery;
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

import javax.servlet.http.HttpSession;
//定向卡会员
@Controller
@RequestMapping("/cardEmpController")
public class CardEmpController extends ControllerConstants {

    @Autowired
    @Qualifier("cardEmpService")
    private ListService cardEmpServiceList;

    @Autowired
    @Qualifier("cardEmpService")
    private SaveService cardEmpServiceSave;

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpServiceExe;

    @Autowired
    @Qualifier("cardEmpService")
    private UpdateService cardEmpServiceUpdate;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, CardEmpQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) cardEmpServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/cardEmp/list";
    }

    @RequestMapping("/toDetail")
    public String toDetail(HttpSession session,ModelMap map, String emp_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        CardEmp cardEmp = (CardEmp) cardEmpServiceExe.execute(emp_id);
        cardEmp.setLx_card_emp_end_time(DateUtil.getDate(cardEmp.getLx_card_emp_end_time() , "MM/dd/yyyy"));
        map.put("cardEmp", cardEmp);
        return "/cardEmp/detail";
    }


    /**
     * 更新分类
     * @return
     */
    @RequestMapping("/updateCardEmp")
    @ResponseBody
    public String updateCardEmp(CardEmp cardEmp){
        if(StringUtil.isNullOrEmpty(cardEmp.getEmp_id())){
            return toJSONString(ERROR_2);
        }
        if(StringUtil.isNullOrEmpty(cardEmp.getLx_card_emp_end_time())){
            return toJSONString(ERROR_3);
        }
        try {
            cardEmp.setLx_card_emp_end_time(DateUtil.getMs(cardEmp.getLx_card_emp_end_time(), "MM/dd/yyyy") + "");
            cardEmpServiceUpdate.update(cardEmp);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
