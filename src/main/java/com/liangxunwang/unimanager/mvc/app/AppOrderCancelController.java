package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.CancelOrder;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/8/18.
 */
@Controller
public class AppOrderCancelController extends ControllerConstants {

    @Autowired
    @Qualifier("appOrderCancelService")
    private SaveService appOrderCancelSaveService;
    /**
     * 取消订单
     * @return
     */
    @RequestMapping("/cancelOrderSave")
    @ResponseBody
    public String cancelOrderSave(CancelOrder cancelOrder){
        //保存取消记录
        appOrderCancelSaveService.save(cancelOrder);
        return toJSONString(SUCCESS);
    }


}
