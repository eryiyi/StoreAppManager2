package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.query.LxClassQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by zhl on 2015/2/2.
 * 分类
 */
@Controller
public class LxClassController extends ControllerConstants{

    @Autowired
    @Qualifier("goodsClassService")
    private SaveService goodsClassServiceSave;

    @Autowired
    @Qualifier("goodsClassService")
    private ListService goodsClassServiceList;

    @Autowired
    @Qualifier("goodsClassService")
    private FindService goodsClassServiceFind;

    @Autowired
    @Qualifier("goodsClassService")
    private UpdateService goodsClassServiceUpdate;


    @RequestMapping("/toAddLxClass")
    public String toAddType(){
        return "/lxClass/addType";
    }

    @RequestMapping("/addLxClass")
    @ResponseBody
    public String addType(LxClass type){
        if (StringUtil.isNullOrEmpty(type.getLx_class_name())){
            return toJSONString(ERROR_1);//名称不能为空
        }
        try {
            goodsClassServiceSave.save(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_4);
        }
    }


    @RequestMapping("/listLxClassType")
    public String listType(ModelMap map, LxClassQuery query, HttpSession session){
        query.setF_lx_class_id("0");
        List<LxClass> list = (List<LxClass>) goodsClassServiceList.list(query);
        map.put("list", list);
        return "/lxClass/listType";
    }

    @RequestMapping("/toUpdateLxClassType")
    public String toUpdateType(ModelMap map, String lx_class_id){
        LxClass lxClass = (LxClass) goodsClassServiceFind.findById(lx_class_id);
        map.put("type", lxClass);
        return "/lxClass/updateType";
    }

    /**
     * 更新分类
     * @param lxClass
     * @return
     */
    @RequestMapping("/updateLxClass")
    @ResponseBody
    public String updateGoodsType(LxClass lxClass){
        try {
            goodsClassServiceUpdate.update(lxClass);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    //------------------小分类-----------
    @RequestMapping("/toListSmallLxClass")
    public String toListSmallType(LxClassQuery query, ModelMap map){
        //查看大分类
        LxClass type = (LxClass) goodsClassServiceFind.findById(query.getF_lx_class_id()==null?"":query.getF_lx_class_id());
        map.put("type", type);
        //查询小分类
        List<LxClass> list = (List<LxClass>) goodsClassServiceList.list(query);
        map.put("list", list);
        return "/lxClass/listSmallType";
    }

    @RequestMapping("/toAddGoodsSmallLxClass")
    public String toAddGoodsSmallLxClass(ModelMap map,String f_lx_class_id){
        //查看大分类
        LxClass type = (LxClass) goodsClassServiceFind.findById(f_lx_class_id);
        map.put("type", type);
        map.put("newDateTime", System.currentTimeMillis()+"");
        return "/lxClass/addSmallType";
    }


    @RequestMapping("/getAllLxClass")
    @ResponseBody
    public String getAllLxClass(String f_lx_class_id,HttpServletRequest request, HttpServletResponse response) throws IOException {
        LxClassQuery query = new LxClassQuery();
        query.setF_lx_class_id(f_lx_class_id);
        //查询小分类
        List<LxClass> list = (List<LxClass>) goodsClassServiceList.list(query);
        try {
            DataTip tip = new DataTip();
            tip.setData(list);
            return reBack(toJSONString(tip), request, response);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @Autowired
    @Qualifier("goodsClassService")
    private DeleteService goodsClassServiceDele;

    /**
     * 更新分类
     * @param id
     * @return
     */
    @RequestMapping("/deleteClassId")
    @ResponseBody
    public String deleteClassId(String id){
        try {
            goodsClassServiceDele.delete(id);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

}
