package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.query.CountRecordQuery;
import com.liangxunwang.unimanager.service.FindService;
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
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppCountRecordController extends ControllerConstants {
    @Autowired
    @Qualifier("countRecordService")
    private ListService countRecordService;

    /**
     * 获得会员积分记录
     * @return
     */
    @RequestMapping(value = "/appGetCountRecord", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCountRecord(CountRecordQuery query ,Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            Object[] result = (Object[]) countRecordService.list(query);
            long count = (Long) result[1];

            DataTip tip = new DataTip();
            tip.setData(result[0]);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("appCountService")
    private FindService appCountServiceFind;

    /**
     * 获得会员积分
     * @return
     */
    @RequestMapping(value = "/appGetCount", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCount(String emp_id){
        try {
            CountVo countVo = (CountVo) appCountServiceFind.findById(emp_id);
            DataTip tip = new DataTip();
            tip.setData(countVo);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
