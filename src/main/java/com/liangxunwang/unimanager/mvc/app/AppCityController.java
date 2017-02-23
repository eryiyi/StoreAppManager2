package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.City;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppCityController extends ControllerConstants {
    @Autowired
    @Qualifier("appCityService")
    private ListService appCityListService;

    @Autowired
    @Qualifier("cityService")
    private ListService cityService;

    /**
     * 获得所有的城市，根据省份id
     * @return
     */
    @RequestMapping(value = "/appGetCity", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCity(String provinceid){
        try {
            List<City> list = (List<City>) cityService.list(provinceid);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //后台获得城市列表
    @RequestMapping("listCitys")
    public String listCitys(HttpSession session,ModelMap map, String id){
        List<City> list = (List<City>) appCityListService.list(id);
        map.put("list", list);
        //日志记录
        return "/province/listcity";
    }



}
