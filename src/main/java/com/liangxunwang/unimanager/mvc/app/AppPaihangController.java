package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by zhl on 2015/2/2.
 */
@Controller
public class AppPaihangController extends ControllerConstants {

    @Autowired
    @Qualifier("paihangService")
    private ListService paihangService;

    //查询推荐首页商品
    @RequestMapping(value = "/getIndexTuijian", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String getRecord(PaihangQuery query, Page page){
        query.setIndex(page.getIndex()==0?1:page.getIndex());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        query.setGoods_count("1");
        try {
            Object[] results = (Object[]) paihangService.list(query);
            DataTip tip = new DataTip();
            tip.setData(results[0]);
            return toJSONString(tip);
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
