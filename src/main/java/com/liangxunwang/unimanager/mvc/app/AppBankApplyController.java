package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.lxBankApply;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.LxBankApplyQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppBankApplyController extends ControllerConstants {

    @Autowired
    @Qualifier("lxBankApplyService")
    private ListService lxBankApplyServiceList;

    @Autowired
    @Qualifier("lxBankApplyService")
    private SaveService lxBankApplyServiceSave;

    /**
     * 获得提现申请
     * @return
     */
    @RequestMapping(value = "/appGetBankApply", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetBankApply(LxBankApplyQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            Object[] results = (Object[])  lxBankApplyServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 保存提现申请
     * @return
     */
    @RequestMapping(value = "/appSaveBankApply", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveBankApply(lxBankApply apply){
        try {
             lxBankApplyServiceSave.save(apply);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
