package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.CardEmp;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/8/17.
 * 定向卡会员
 */
@Controller
public class AppCardEmpController extends ControllerConstants {

    @Autowired
    @Qualifier("cardEmpService")
    private ExecuteService cardEmpService;

    /**
     * 获得定向卡会员详情
     * @return
     */
    @RequestMapping(value = "/appGetCardEmp", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCardEmp(String emp_id) throws Exception {
        try {
            CardEmp cardEmp = (CardEmp) cardEmpService.execute(emp_id);
            DataTip tip = new DataTip();
            tip.setData(cardEmp);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
