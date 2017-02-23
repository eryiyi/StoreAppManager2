package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.BankObj;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.BankCardQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 * 银行卡操作
 */
@Controller
public class AppBankCardController extends ControllerConstants {

    @Autowired
    @Qualifier("bankObjService")
    private ListService bankObjServiceList;

    /**
     * 获得用户银行卡列表
     * @return
     */
    @RequestMapping(value = "/appGetBankCards", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetBankCards(BankCardQuery query){
        try {
            List<BankObj> list = (List<BankObj>) bankObjServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("bankObjService")
    private SaveService bankObjServiceSave;

    /**
     * 保存银行卡
     * @return
     */
    @RequestMapping(value = "/appSaveBankCards", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveBankCards(BankObj bankObj){
        try {
            bankObjServiceSave.save(bankObj);
        }catch (ServiceException e){
            if (e.getMessage().equals("adIsTooMuch")){
                return toJSONString(ERROR_2);//最多5个
            }else{
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);//保存成功
    }

    @Autowired
    @Qualifier("bankObjService")
    private DeleteService bankObjServiceDelete;

    @RequestMapping(value = "/deleteBankCard", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteBankCard(String bank_id){
        bankObjServiceDelete.delete(bank_id);
        return toJSONString(SUCCESS);
    }

}
