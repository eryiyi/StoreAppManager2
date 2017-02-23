package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.LxAd;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.AdQuery;
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
public class AppLxAdController extends ControllerConstants {

    @Autowired
    @Qualifier("lxAdObjListService")
    private ListService lxAdObjListService;

    /**
     * 获得广告轮播
     * @return
     */
    @RequestMapping(value = "/appGetAdByType", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetAdByType(AdQuery query){
        try {
            List<LxAd> lists= (List<LxAd>) lxAdObjListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(lists);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
