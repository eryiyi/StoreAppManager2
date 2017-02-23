package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.LoadPic;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.LoadPicQuery;
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
public class AppLoadPicController extends ControllerConstants {

    @Autowired
    @Qualifier("loadPicService")
    private ListService loadPicService;

    /**
     * 获得欢迎页广告列表
     * @return
     */
    @RequestMapping(value = "/appGetLoadPics", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetLoadPics(LoadPicQuery query){
        try {
            List<LoadPic> list = (List<LoadPic>) loadPicService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
