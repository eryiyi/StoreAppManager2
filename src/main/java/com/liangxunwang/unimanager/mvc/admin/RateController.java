package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Rate;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * 费率有关的接口操作方法
 * Created by zhl on 2015/9/7.
 */
@Controller
@RequestMapping("/rate")
public class RateController extends ControllerConstants {

    @Autowired
    @Qualifier("rateService")
    private FindService rateFindService;

    @Autowired
    @Qualifier("rateService")
    private UpdateService rateUpdateService;

    @RequestMapping("edit")
    public String edit(ModelMap map){
        Rate rate = (Rate) rateFindService.findById("1");
        map.put("rate", rate);
        return "/rate/edit";
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(Rate rate){
        rateUpdateService.update(rate);
        return toJSONString(SUCCESS);
    }

}
