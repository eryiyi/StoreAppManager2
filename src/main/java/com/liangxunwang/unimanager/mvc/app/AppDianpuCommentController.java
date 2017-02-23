package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.DianPuFavour;
import com.liangxunwang.unimanager.model.DianpuComment;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import com.liangxunwang.unimanager.query.DianpuCommentQuery;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
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
public class AppDianpuCommentController extends ControllerConstants {
    @Autowired
    @Qualifier("appDianpuCommentService")
    private ListService dianpuCommentServiceList;

    @Autowired
    @Qualifier("dianpuCommentService")
    private SaveService dianpuCommentServiceSave;

    @Autowired
    @Qualifier("dianpuCommentService")
    private DeleteService dianpuCommentServiceDelete;

    @RequestMapping(value = "/appGetDianpuComment", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetDianpuComment(DianpuCommentQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            List<DianpuComment> list  = (List<DianpuComment>) dianpuCommentServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //保存店铺评论
    @RequestMapping("/saveDianpuComment")
    @ResponseBody
    public String saveDianpuComment(DianpuComment dianpuComment){
        if(StringUtil.isNullOrEmpty(dianpuComment.getEmp_id())){
            return toJSONString(ERROR_2);//用户ID为空
        }
        if(StringUtil.isNullOrEmpty(dianpuComment.getEmp_id_seller())){
            return toJSONString(ERROR_3);//卖家用户ID为空
        }
        if(StringUtil.isNullOrEmpty(dianpuComment.getDianpu_comment_cont())){
            return toJSONString(ERROR_4);//评论内容为空
        }
        try {
            dianpuCommentServiceSave.save(dianpuComment);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
        return toJSONString(SUCCESS);//保存成功
    }

    @RequestMapping(value = "/deleteDianpuComment", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteDianpuComment(String dianpu_comment_id){
        dianpuCommentServiceDelete.delete(dianpu_comment_id);
        return toJSONString(SUCCESS);
    }

}
