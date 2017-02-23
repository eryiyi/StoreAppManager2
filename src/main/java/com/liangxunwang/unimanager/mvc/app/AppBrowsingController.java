package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.BrowsingQuery;
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
public class AppBrowsingController extends ControllerConstants {

    @Autowired
    @Qualifier("browsingService")
    private ListService browsingServiceList;


    @Autowired
    @Qualifier("browsingService")
    private SaveService browsingServiceSave;

    /**
     * 获得浏览记录
     * @return
     */
    @RequestMapping(value = "/appGetBrowsing", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetBrowsing(BrowsingQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        try {
            Object[] results = (Object[]) browsingServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    /**
     * 保存浏览记录
     * @return
     */
    @RequestMapping(value = "/appSaveBrowsing", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appSaveBrowsing(BrowsingQuery query){
        try {
            browsingServiceSave.save(query);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
}
