package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.query.ManagerLogQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by zhl on 2015/3/3.
 */
@Controller
public class ManagerLogController extends ControllerConstants{

    @Autowired
    @Qualifier("managerLogService")
    private ListService managerLogListService;
    @RequestMapping("/listLog")
    public String listLog(ManagerLogQuery query, Page page, ModelMap map){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] result = (Object[]) managerLogListService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/log/listManagerLog";
    }
}
