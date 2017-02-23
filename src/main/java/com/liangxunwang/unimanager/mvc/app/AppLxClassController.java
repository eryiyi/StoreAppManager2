package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.LxAd;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.LxClassQuery;
import com.liangxunwang.unimanager.service.ListService;
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
 */
@Controller
public class AppLxClassController extends ControllerConstants {

    @Autowired
    @Qualifier("appLxClassService")
    private ListService appLxClassService;

    /**
     * 获得入驻分类
     * @return
     */
    @RequestMapping(value = "/appGetLxClass", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetLxClass(LxClassQuery query){
        try {
            List<LxClass> list = (List<LxClass>) appLxClassService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
