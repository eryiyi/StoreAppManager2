package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.BankObj;
import com.liangxunwang.unimanager.model.lxBankApply;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.mvc.vo.lxBankApplyVo;
import com.liangxunwang.unimanager.query.BankCardQuery;
import com.liangxunwang.unimanager.query.LxBankApplyQuery;
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

@Controller
@RequestMapping("/lxBankApplyController")
public class LxBankApplyController extends ControllerConstants {

    @Autowired
    @Qualifier("lxBankApplyService")
    private ListService lxBankApplyServiceList;

    @Autowired
    @Qualifier("lxBankApplyService")
    private ExecuteService lxBankApplyServiceExe;

    @Autowired
    @Qualifier("lxBankApplyService")
    private UpdateService lxBankApplyServiceEUpdate;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LxBankApplyQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        Object[] result = (Object[]) lxBankApplyServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/lxBankApply/list";
    }

    @RequestMapping("listMine")
    public String listMine(HttpSession session,ModelMap map, LxBankApplyQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            if(!StringUtil.isNullOrEmpty(manager.getEmp_id())){
                query.setEmp_id(manager.getEmp_id());
            }
        }
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        Object[] result = (Object[]) lxBankApplyServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/lxBankApply/listMine";
    }


    @RequestMapping("/toDetail")
    public String toDetail(HttpSession session,ModelMap map, String lx_bank_apply_id) throws Exception {
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        lxBankApplyVo BankApplyVo = (lxBankApplyVo) lxBankApplyServiceExe.execute(lx_bank_apply_id);
        map.put("lxBankApplyVo", BankApplyVo);
        return "/lxBankApply/detail";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(HttpSession session,lxBankApply lxBankApply){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            lxBankApplyServiceEUpdate.update(lxBankApply);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            if (e.getMessage().equals("has_check")){
                return toJSONString(ERROR_2);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }



    @Autowired
    @Qualifier("bankObjService")
    private ListService bankObjServiceList;

    @Autowired
    @Qualifier("countService")
    private ExecuteService countServiceExe;

    //申请提现
    @RequestMapping("/toAdd")
    public String toAdd(HttpSession session,ModelMap map) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(admin != null){
            String emp_id = admin.getEmp_id();
            if(!StringUtil.isNullOrEmpty(emp_id)){
                //查询该用户的银行卡列表
                BankCardQuery query = new BankCardQuery();
                query.setEmp_id(emp_id);
                List<BankObj> listBanks = (List<BankObj>) bankObjServiceList.list(query);
                map.put("listBanks", listBanks);
                //查询该用户的积分多少
                CountVo countVo = (CountVo) countServiceExe.execute(emp_id);
                map.put("countVo", countVo);
            }

        }
        return "/lxBankApply/add";
    }

    @Autowired
    @Qualifier("lxBankApplyService")
    private SaveService lxBankApplyServiceSave;
    /**
     * 保存
     */
    @RequestMapping("/save")
    @ResponseBody
    public String save(HttpSession session,lxBankApply apply,ModelMap map) throws Exception {
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(StringUtil.isNullOrEmpty(apply.getBank_id())){
            return toJSONString(ERROR_3);//银行卡不存在
        }
        if(StringUtil.isNullOrEmpty(apply.getLx_bank_apply_count())){
            return toJSONString(ERROR_4);//提现金额不能为空
        }
        if(admin != null){
            if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
                //查询该用户的积分多少
                CountVo countVo = (CountVo) countServiceExe.execute(admin.getEmp_id());
                if(countVo != null){
                    String countNumber = countVo.getCount();
                    if(!StringUtil.isNullOrEmpty(countNumber)){
                        Double dNumber = Double.valueOf(countNumber);//用户积分数
                        Double dCount = Double.valueOf(apply.getLx_bank_apply_count());//提现金额
                        if(dNumber < dCount){
                            return toJSONString(ERROR_6);//提现金额大于用户剩余积分
                        }
                        if(dCount < 100){
                            return toJSONString(ERROR_7);//提现金额必须是100的整数倍
                        }
                    }
                }else {
                    return toJSONString(ERROR_5);//用户积分不存在
                }
                try {
                    apply.setEmp_id(admin.getEmp_id());
                    lxBankApplyServiceSave.save(apply);
                    return toJSONString(SUCCESS);
                }catch (ServiceException e){
                    return toJSONString(ERROR_1);
                }
            }else {
                return toJSONString(ERROR_2);//用户ID不存在
            }
        }else {
            return toJSONString(ERROR_2);//用户ID不存在
        }
    }


}
