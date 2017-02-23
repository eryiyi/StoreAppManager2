package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.model.JifenObj;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.query.CountQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;
//积分操作
@Controller
public class EmpCountController extends ControllerConstants {

    @Autowired
    @Qualifier("countService")
    private ListService countServiceList;
    @Autowired
    @Qualifier("countService")
    private ExecuteService countServiceExe;

    @RequestMapping("/listCount")
    public String list(HttpSession session, ModelMap map, CountQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) countServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/count/list";
    }


    @Autowired
    @Qualifier("jifenObjService")
    private ExecuteService jifenObjServiceFind;


    @Autowired
    @Qualifier("jifenObjService")
    private UpdateService jifenObjServiceUpdate;

    //积分规则修改
    @RequestMapping("/toEditJifenGuize")
    public String toEditJifenGuize(HttpSession session, ModelMap map) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<JifenObj> jifenObjs = (List<JifenObj>) jifenObjServiceFind.execute("");
        if(jifenObjs != null && jifenObjs.size()>0){
            map.put("adObj", jifenObjs.get(0));
        }
        return "/count/editJfGuize";
    }

    /**
     * 更新
     */
    @RequestMapping("/editJifenGuize")
    @ResponseBody
    public String editJifenGuize(HttpSession session,JifenObj adObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            jifenObjServiceUpdate.update(adObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //积分扣除
    @RequestMapping("/toKouchuCount")
    public String toKouchuCount(HttpSession session, ModelMap map, String emp_id) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        CountVo countVo = (CountVo) countServiceExe.execute(emp_id);
        if(countVo != null){
            map.put("countVo", countVo);
        }
        return "/count/kouchuCount";
    }


    //积分增加
    @RequestMapping("/toAddCount")
    public String toAddCount(HttpSession session, ModelMap map, String emp_id) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        CountVo countVo = (CountVo) countServiceExe.execute(emp_id);
        if(countVo != null){
            map.put("countVo", countVo);
        }
        return "/count/AddCount";
    }


    @Autowired
    @Qualifier("appCountService")
    private UpdateService appCountServiceUpdate;

    @Autowired
    @Qualifier("countRecordService")
    private SaveService countRecordServiceSave;

    @RequestMapping("/kouchuCount")
    @ResponseBody
    public String kouchuCount(HttpSession session,String emp_id, String jifenCount, String countNumber){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(ERROR_2);//用户ID不存在，请检查用户！
        }
        if(StringUtil.isNullOrEmpty(jifenCount)){
            return toJSONString(ERROR_3);//会员剩余积分为空，请检查！
        }
        if(StringUtil.isNullOrEmpty(countNumber)){
            return toJSONString(ERROR_4);//请输入要扣除多少积分！
        }
        if(Double.valueOf(jifenCount) < Double.valueOf(countNumber)){
            return toJSONString(ERROR_5);//扣除的额度不能大于用户剩余积分！
        }
        try {
            Object[] objects = {emp_id,countNumber,"1"};//1是减 2是增
            appCountServiceUpdate.update(objects);
            //积分变动记录
            CountRecord countRecord = new CountRecord();
            countRecord.setEmp_id(emp_id);
            countRecord.setLx_count_record_count("-" + String.valueOf(countNumber));
            countRecord.setLx_count_record_cont("管理员" + manager.getUsername() + "后台扣除积分：" + countNumber);
            countRecordServiceSave.save(countRecord);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/addCountMng")
    @ResponseBody
    public String addCountMng(HttpSession session,String emp_id, String jifenCount, String countNumber){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(ERROR_2);//用户ID不存在，请检查用户！
        }

        if(StringUtil.isNullOrEmpty(countNumber)){
            return toJSONString(ERROR_4);//请输入要增加多少积分！
        }

        try {
            Object[] objects = {emp_id, countNumber,"2"};//1是减 2是增
            appCountServiceUpdate.update(objects);
            //积分变动记录
            CountRecord countRecord = new CountRecord();
            countRecord.setEmp_id(emp_id);
            countRecord.setLx_count_record_count("+"+String.valueOf(countNumber));
            countRecord.setLx_count_record_cont("管理员"+manager.getUsername()+"后台添加积分："+countNumber);
            countRecordServiceSave.save(countRecord);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
}
