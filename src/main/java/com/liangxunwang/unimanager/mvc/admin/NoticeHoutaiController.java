package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.model.NoticeHoutai;
import com.liangxunwang.unimanager.query.NoticeHoutaiQuery;
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

/**
 * Created by zhl on 2015/2/5.
 */
@Controller
public class NoticeHoutaiController extends ControllerConstants {
    @Autowired
    @Qualifier("noticeHoutaiService")
    private SaveService saveNoticeService;


    @Autowired
    @Qualifier("noticeHoutaiService")
    private ListService listNoticeService;

    @Autowired
    @Qualifier("noticeHoutaiService")
    private FindService findNoticeService;

    @Autowired
    @Qualifier("noticeHoutaiService")
    private UpdateService updateNoticeService;

    @Autowired
    @Qualifier("noticeHoutaiService")
    private DeleteService deleteNoticeService;

    @RequestMapping("/toAddNoticeHoutai")
    public String toAddNoticeHoutai(){
        return "/noticeHoutai/add";
    }

    @RequestMapping("/saveNoticeHoutai")
    @ResponseBody
    public String saveNoticeHoutai(NoticeHoutai noticeHoutai){
        if (StringUtil.isNullOrEmpty(noticeHoutai.getNotice_title())){
            return toJSONString(ERROR_1);
        }
        if (StringUtil.isNullOrEmpty(noticeHoutai.getNotice_content())){
            return toJSONString(ERROR_2);
        }
        saveNoticeService.save(noticeHoutai);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/listNoticeHoutai")
    public String listNoticeHoutai(HttpSession session, ModelMap map, NoticeHoutaiQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        Object[] results = (Object[]) listNoticeService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        //判断是否能删除和更改
        if(admin.getIs_pingtai().equals("1") && "0".equals(admin.getEmp_id())){
            //说明可以删除
            map.put("is_edit", "0");
        }else{
            map.put("is_edit", "1");
        }
        return "/noticeHoutai/list";
    }

    @RequestMapping("/toUpdateNoticeHoutai")
    public String toUpdateNoticeHoutai(String notice_id, ModelMap map){
        NoticeHoutai notice = (NoticeHoutai) findNoticeService.findById(notice_id);
        map.put("notice", notice);
        return "/noticeHoutai/updateNotice";
    }

    @RequestMapping("/updateNoticeHoutai")
    @ResponseBody
    public String updateNoticeHoutai(NoticeHoutai notice){
        if (StringUtil.isNullOrEmpty(notice.getNotice_id())){
            return toJSONString(ERROR_1);
        } if (StringUtil.isNullOrEmpty(notice.getNotice_title())){
            return toJSONString(ERROR_2);
        }
        if (StringUtil.isNullOrEmpty(notice.getNotice_content())){
            return toJSONString(ERROR_3);
        }
        updateNoticeService.update(notice);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/deleteNoticeHoutai")
    @ResponseBody
    public String deleteNoticeHoutai(String notice_id){
        try {
            deleteNoticeService.delete(notice_id);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 公告详情
     * @param notice_id  公告ID
     * @param map
     * @return
     */
    @RequestMapping("/viewNoticeHoutai")
    public String viewNoticeHoutai(String notice_id, ModelMap map){
        NoticeHoutai notice = (NoticeHoutai) findNoticeService.findById(notice_id);
        map.put("notice", notice);
        return "/noticeHoutai/viewNotice";
    }

}
