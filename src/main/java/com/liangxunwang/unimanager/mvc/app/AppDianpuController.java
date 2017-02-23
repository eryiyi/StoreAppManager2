package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.CommentNumber;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import com.liangxunwang.unimanager.query.AdQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
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

import java.util.List;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppDianpuController extends ControllerConstants {
    @Autowired
    @Qualifier("appMemberService")
    private ListService appMemberServiceList;

    /**
     * 获得所有的店铺
     * @return
     */
    @RequestMapping(value = "/appGetDianPu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetDianPu(MemberQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

            List<EmpDianpu> list = (List<EmpDianpu>) appMemberServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("adObjService")
    private ListService adObjService;
    /**
     * 获得用户广告
     * @return
     */
    @RequestMapping(value = "/appGetAds", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetAds(AdQuery query){
        try {
            List<AdObj> list = (List<AdObj>) adObjService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("appManagerInfoService")
    private FindService appManagerInfoService;
    /**
     * 获得个人店铺信息
     * @return
     */
    @RequestMapping(value = "/appGetProfileMsg", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetProfileMsg(String emp_id){
        try {
            ManagerInfoVo managerInfoVo = (ManagerInfoVo) appManagerInfoService.findById(emp_id);
            DataTip tip = new DataTip();
            tip.setData(managerInfoVo);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("appDianpuCommentService")
    private FindService appDianpuCommentService;

    /**
     * 查询店铺的好评率和评价数量
     * @return
     */
    @RequestMapping(value = "/appGetCountCommentDianpu", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCountCommentDianpu(String id){//店铺人ID
        try {
            Object[] result = (Object[]) appDianpuCommentService.findById(id);
            Long commentCount = (Long) result[0];
            Double starDouble = (Double) result[1];
            CommentNumber commentNumber = new CommentNumber();
            commentNumber.setCommentCount(String.valueOf(commentCount));
            commentNumber.setStarDouble(String.valueOf(starDouble));
            DataTip tip = new DataTip();
            tip.setData(commentNumber);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
