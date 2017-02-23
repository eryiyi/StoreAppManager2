package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.model.DianpuComment;
import com.liangxunwang.unimanager.model.JifenObj;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.CountQuery;
import com.liangxunwang.unimanager.query.CountRecordQuery;
import com.liangxunwang.unimanager.query.DianpuCommentQuery;
import com.liangxunwang.unimanager.service.*;
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

@Controller
public class DianpuCommentController extends ControllerConstants {
    @Autowired
    @Qualifier("dianpuCommentService")
    private ListService dianpuCommentServiceList;

    @Autowired
    @Qualifier("dianpuCommentService")
    private FindService dianpuCommentServiceExe;

    @Autowired
    @Qualifier("dianpuCommentService")
    private DeleteService dianpuCommentServiceDel;

    @RequestMapping("/listDianpuComments")
    public String list(HttpSession session, ModelMap map, DianpuCommentQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);

        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) dianpuCommentServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/dianpuComment/list";
    }

    @RequestMapping("toDetailDianpuComment")
    public String toDetailDianpuComment(HttpSession session,ModelMap map, String dianpu_comment_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(!StringUtil.isNullOrEmpty(dianpu_comment_id)){
            DianpuComment dianpuComment = (DianpuComment) dianpuCommentServiceExe.findById(dianpu_comment_id);
            map.put("vo", dianpuComment);
            if(!StringUtil.isNullOrEmpty(dianpuComment.getDianpu_comment_pic())){
                String[] arrs = dianpuComment.getDianpu_comment_pic().split(",");
                map.put("arrs", arrs);
            }
        }
        return "/dianpuComment/detail";
    }



    @RequestMapping("deleteDianpuCommentById")
    @ResponseBody
    public String deleteDianpuCommentById(String comment_id){
        dianpuCommentServiceDel.delete(comment_id);
        return toJSONString(SUCCESS);
    }



}
