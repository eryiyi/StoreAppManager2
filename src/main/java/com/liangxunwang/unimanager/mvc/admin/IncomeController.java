package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.query.IncomeQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpSession;

/**
 * Created by liuzh on 2015/8/22.
 * 收入操作相关
 */
@RequestMapping("/income")
public class IncomeController extends ControllerConstants {

    @Autowired
    @Qualifier("incomeService")
    private ListService incomeListService;

    @RequestMapping("list")
    public String list(IncomeQuery query, Page page, HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if (!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
            query.setEmpId(admin.getEmp_id());
        }
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) incomeListService.list(query);

        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);

        Float income = (Float) results[2];
        map.put("income", income);

        return "/income/list";

    }
}
