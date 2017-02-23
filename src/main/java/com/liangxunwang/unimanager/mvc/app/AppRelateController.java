package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppRelateController extends ControllerConstants {

    @Autowired
    @Qualifier("relateService")
    private UpdateService relateService;

    //更新与我相关为已读
    @RequestMapping(value = "/updateRelateById", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String updateRelateById(String id){
        try {
            relateService.update(id);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("accessTokenNull")){
                return toJSONString(ERROR_9);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

}
