package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;

/**
 * Created by zhl on 2015/8/12.
 */
@Controller
@RequestMapping("/paihang")
public class PaihangController extends ControllerConstants {
    @Autowired
    @Qualifier("paihangService")
    private ListService recordService;

    @Autowired
    @Qualifier("paihangService")
    private DeleteService recordServiceDele;

    @Autowired
    @Qualifier("paihangService")
    private ExecuteService recordServiceExer;

    @Autowired
    @Qualifier("paihangService")
    private UpdateService recordServiceUpdate;

    @Autowired
    @Qualifier("paihangService")
    private SaveService paihangServiceSave;

    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, PaihangQuery query, Page page){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());

        Object[] results = (Object[]) recordService.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "paihang/list";
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(HttpSession session,String mm_paihang_id){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        recordServiceDele.delete(mm_paihang_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("toDetail")
    public String add(ModelMap map, PaihangQuery query) throws Exception {
        PaihangObjVO recordVO = (PaihangObjVO) recordServiceExer.execute(query);
        map.put("recordVO", recordVO);
        return "/paihang/detail";
    }

    //更改数据
    @RequestMapping("/update")
    @ResponseBody
    public String updateEmp( HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            recordServiceUpdate.update(paihangObj);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    /**
     * 添加排行榜
     */
    @RequestMapping("/add")
    @ResponseBody
    public String add(HttpSession session, PaihangObj paihangObj){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        try {
            paihangServiceSave.save(paihangObj);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("Has_exist")){
                //该商品已经添加到推荐首页不能重复添加
                return toJSONString(ERROR_2);
            }
            if (msg.equals(Constants.SAVE_ERROR)){
                return toJSONString(ERROR_1);
            }
        }
        return toJSONString(SUCCESS);
    }


    //-------------------每天凌晨执行，查询是否有商品推荐到期-------------------------
    public String updateTuijian(){
        updatePaihangVip();
        return null;
    }

    @Autowired
    @Qualifier("paihangUpdateVipService")
    private UpdateService paihangUpdateVipService;

    @RequestMapping("/updatePaihangVip")
    @ResponseBody
    public String updatePaihangVip(){
        try {
            paihangUpdateVipService.update("");
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }
    //-------------------------------------------------


}
