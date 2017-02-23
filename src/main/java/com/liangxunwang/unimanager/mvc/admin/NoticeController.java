package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.NoticeQuery;
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

/**
 * Created by zhl on 2015/2/5.
 */
@Controller
public class NoticeController extends ControllerConstants {
    @Autowired
    @Qualifier("noticeService")
    private SaveService saveNoticeService;

    @Autowired
    @Qualifier("noticeDianpuService")
    private SaveService noticeDianpuService;

    @Autowired
    @Qualifier("noticeService")
    private ListService listNoticeService;
    @Autowired
    @Qualifier("appNoticeService")
    private ListService appNoticeServiceList;

    @Autowired
    @Qualifier("noticeService")
    private FindService findNoticeService;

    @Autowired
    @Qualifier("noticeService")
    private UpdateService updateNoticeService;

    @Autowired
    @Qualifier("noticeService")
    private DeleteService deleteNoticeService;

    @RequestMapping("/ajax/toAddNotice")
    public String toAddNotice(){

        return "/notice/addNotice";
    }

    @RequestMapping("/ajax/toAddNoticeDianpu")
    public String toAddNoticeDianpu(){

        return "/notice/addNoticeDianpu";
    }

    @RequestMapping("/saveNotice")
    @ResponseBody
    public String saveNotice(Notice notice){
        if (StringUtil.isNullOrEmpty(notice.getTitle())){
            return toJSONString(ERROR_1);
        }
        notice.setManager_id("0");
        saveNoticeService.save(notice);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/saveNoticeDianpu")
    @ResponseBody
    public String saveNoticeDianpu(HttpSession session, Notice notice){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if (StringUtil.isNullOrEmpty(notice.getTitle())){
            return toJSONString(ERROR_1);
        }
        if(admin != null){
            notice.setManager_id(admin.getId());
        }
        Integer code = (Integer)noticeDianpuService.save(notice);
        if(code == 1){
           //说明发不过了
            return toJSONString(ERROR_2);
        }else if(code == 200){
            return toJSONString(SUCCESS);
        }
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/listNotice")
    public String listNotice(HttpSession session, ModelMap map, NoticeQuery query, Page page){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());

        if(admin != null){
            if(!StringUtil.isNullOrEmpty(admin.getId()) && !"0".equals(admin.getId()) && "0".equals(admin.getIs_pingtai())){
                query.setManager_id(admin.getId());
            }else{
                query.setManager_id("");
            }
        }
        Object[] results = (Object[]) listNoticeService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/notice/listNotice";
    }

    @RequestMapping(value = "/appListNotice", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String appListNotice(NoticeQuery query, Page page){
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        List<Notice> lists = (List<Notice>) appNoticeServiceList.list(query);

        DataTip dataTip = new DataTip();
        dataTip.setData(lists);
        return toJSONString(dataTip);
    }



    @RequestMapping("/toUpdateNotice")
    public String toUpdateNotice(String noticeId, ModelMap map){
        Notice notice = (Notice) findNoticeService.findById(noticeId);
        map.put("notice", notice);
        return "/notice/updateNotice";
    }

    @RequestMapping("/updateNotice")
    @ResponseBody
    public String updateService(Notice notice){
        if (StringUtil.isNullOrEmpty(notice.getTitle())){
            return toJSONString(ERROR_1);
        }
        if (StringUtil.isNullOrEmpty(notice.getContent())){
            return toJSONString(ERROR_2);
        }
        updateNoticeService.update(notice);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/deleteNotice")
    @ResponseBody
    public String deleteNotice(String noticeId){
        try {
            deleteNoticeService.delete(noticeId);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 公告详情
     * @param noticeId  公告ID
     * @param map
     * @return
     */
    @RequestMapping("/viewNotice")
    public String viewNotice(String noticeId, ModelMap map){
        Notice notice = (Notice) findNoticeService.findById(noticeId);
        map.put("notice", notice);
        return "/notice/viewNotice";
    }

}
