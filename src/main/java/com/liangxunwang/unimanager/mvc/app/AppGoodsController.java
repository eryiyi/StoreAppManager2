package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.CommentNumber;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.LikeQuery;
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
 * 猜你喜欢
 */
@Controller
public class AppGoodsController extends ControllerConstants {

    @Autowired
    @Qualifier("appGoodsService")
    private ListService appGoodsServiceList;

    /**
     * 获得猜你喜欢
     * @return
     */
    @RequestMapping(value = "/appGetLikes", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetLikes(LikeQuery query, Page page){
        try {
            query.setIndex(page.getIndex()==0?1:page.getIndex());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            List<PaopaoGoodsVO> list = (List<PaopaoGoodsVO>) appGoodsServiceList.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("appGoodsCommentService")
    private FindService appGoodsCommentService;

    /**
     * 查询商品的好评率和评价数量
     * @return
     */
    @RequestMapping(value = "/appGetCountComment", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetCountComment(String id){
        try {
            Object[] result = (Object[]) appGoodsCommentService.findById(id);
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
