package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.LoadPic;
import com.liangxunwang.unimanager.query.LoadPicQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@RequestMapping("/loadPicController")
public class LoadPicController extends ControllerConstants {

    @Autowired
    @Qualifier("loadPicService")
    private ListService levelService;

    @Autowired
    @Qualifier("loadPicService")
    private SaveService levelServiceSave;

    @Autowired
    @Qualifier("loadPicService")
    private ExecuteService levelServiceSaveExe;

    @Autowired
    @Qualifier("loadPicService")
    private UpdateService levelServiceSaveUpdate;

    @Autowired
    @Qualifier("loadPicService")
    private DeleteService levelServiceSaveDel;


    @RequestMapping("list")
    public String list(HttpSession session,ModelMap map, LoadPicQuery query){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        List<LoadPic> list = (List<LoadPic>) levelService.list(query);
        map.put("list", list);
        return "/loadPic/list";
    }

    @RequestMapping("toAdd")
    public String toAdd(){
        return "/loadPic/add";
    }

    @RequestMapping("add")
    @ResponseBody
    public String add(LoadPic loadPic){
        try {
            levelServiceSave.save(loadPic);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("adIsTooMuch")){
                return toJSONString(ERROR_2);
            }else{
                return toJSONString(ERROR_1);
            }
        }
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String load_pic_id){
        levelServiceSaveDel.delete(load_pic_id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("/toEdit")
    public String toEdit(ModelMap map, String load_pic_id) throws Exception {
        LoadPic loadPic = (LoadPic) levelServiceSaveExe.execute(load_pic_id);
        map.put("loadPic", loadPic);
        return "/loadPic/edit";
    }

    /**
     * 更新
     */
    @RequestMapping("/edit")
    @ResponseBody
    public String edit(LoadPic loadPic){
        try {
            levelServiceSaveUpdate.update(loadPic);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



}
