package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
import com.liangxunwang.unimanager.query.LxBankApplyQuery;
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
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * Created by zhl on 2015/2/5.
 */
@Controller
public class GoodsCommentController extends ControllerConstants {

    @Autowired
    @Qualifier("goodsCommentService")
    private SaveService goodsCommentSaveService;

    @Autowired
    @Qualifier("goodsCommentService")
    private DeleteService goodsCommentServiceDel;

    @Autowired
    @Qualifier("goodsCommentService")
    private ListService goodsCommentListService;

    @Autowired
    @Qualifier("goodsCommentMineService")
    private ListService goodsCommentMineServiceList;

    @Autowired
    @Qualifier("appDianpuCommentService")
    private ListService appDianpuCommentService;

    @RequestMapping("/saveGoodsComment")
    @ResponseBody
    public String saveGoodsComment(GoodsComment comment){
        try {
            goodsCommentSaveService.save(comment);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(ERROR_1);
        }
    }


    @RequestMapping("listGoodsComments")
    public String listGoodsComments(HttpSession session,ModelMap map, GoodsCommentQuery query ,Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            if(!StringUtil.isNullOrEmpty(manager.getEmp_id())){
                query.setGoods_emp_id(manager.getEmp_id());
            }
        }
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        Object[] result = (Object[]) goodsCommentMineServiceList.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/goodsComment/list";
    }

    //查询商品评论列表
    @RequestMapping(value = "/listGoodsComment", produces = "text/plain; charset=UTF-8;")
    @ResponseBody
    public String listGoodsComment(GoodsCommentQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            List<GoodsComment> list = (List<GoodsComment>) goodsCommentListService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);

            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    //查询店铺评论列表
    @RequestMapping(value = "/listDianpuComment", produces = "text/plain; charset=UTF-8;")
    @ResponseBody
    public String listDianpuComment(GoodsCommentQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        try {
            List<GoodsComment> list = (List<GoodsComment>) appDianpuCommentService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);

            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("deleteGoodsCommentById")
    @ResponseBody
    public String deleteGoodsCommentById(HttpSession session,String comment_id){
        goodsCommentServiceDel.delete(comment_id);
        return toJSONString(SUCCESS);
    }


}
