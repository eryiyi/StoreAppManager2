package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.DianPuFavour;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.service.DeleteService;
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

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppDianpuFavourController extends ControllerConstants {
    @Autowired
    @Qualifier("dianpuFavourService")
    private ListService dianpuFavourServiceList;

    @Autowired
    @Qualifier("dianpuFavourService")
    private SaveService dianpuFavourServiceSave;

    @Autowired
    @Qualifier("dianpuFavourService")
    private DeleteService dianpuFavourServiceDel;


    @RequestMapping(value = "/appGetDianpuFavour", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetDianpuFavour(DianpuFavourQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            List<DianpuFavourVO> list = (List<DianpuFavourVO>) dianpuFavourServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //保存店铺收藏
    @RequestMapping("/saveDianpuFavour")
    @ResponseBody
    public String saveDianpuFavour(DianPuFavour dianPuFavour){
        //查询该用户是否已经收藏
        try {
            dianpuFavourServiceSave.save(dianPuFavour);
        }catch (ServiceException e){
            if (e.getMessage().equals("ISFAVOUR")){//已经收藏过了
                return toJSONString(ERROR_2);
            }
        }
        return toJSONString(SUCCESS);//保存成功
    }

    @RequestMapping(value = "/deleteDianpuFavour", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteDianpuFavour(String favour_no){
        dianpuFavourServiceDel.delete(favour_no);
        return toJSONString(SUCCESS);
    }

}
