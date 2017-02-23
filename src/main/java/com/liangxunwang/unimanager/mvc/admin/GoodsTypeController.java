package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.DateUtil;
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
 * 商城分类
 */
@Controller
public class GoodsTypeController extends ControllerConstants{

    @Autowired
    @Qualifier("goodsTypeService")
    private SaveService saveGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private ListService listGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private FindService findGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeService")
    private UpdateService updateGoodsTypeService;

    @Autowired
    @Qualifier("goodsTypeSmallService")
    private UpdateService goodsTypeSmallServiceUpdate;


    @RequestMapping("/toAddGoodsType")
    public String toAddType(){
        return "/goodsType/addType";
    }

    @RequestMapping("/addGoodsType")
    @ResponseBody
    public String addType(GoodsType type){
        if (StringUtil.isNullOrEmpty(type.getTypeName())){
            return toJSONString(ERROR_1);//名称不能为空
        }
//        if (StringUtil.isNullOrEmpty(type.getTypeCover())){
//            return toJSONString(ERROR_3);//图片不能为空
//        }
        try {
            saveGoodsTypeService.save(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_4);
        }
    }


    @RequestMapping("/listType")
    public String listType(ModelMap map, GoodsTypeThreeQuery query, HttpSession session){
        query.setIs_type("0");
        List<GoodsType> list = (List<GoodsType>) listGoodsTypeService.list(query);
        map.put("list", list);
        return "/goodsType/listType";
    }

    @RequestMapping("/toUpdateType")
    public String toUpdateType(ModelMap map, String typeId){
        GoodsType type = (GoodsType) findGoodsTypeService.findById(typeId);
        map.put("type", type);
        return "/goodsType/updateType";
    }

    /**
     * 更新分类
     * @param type
     * @return
     */
    @RequestMapping("/updateGoodsType")
    @ResponseBody
    public String updateGoodsType(GoodsType type){
        try {
            updateGoodsTypeService.update(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("goodsTypeSmallService")
    private ListService goodsTypeSmallService;
    //------------------小分类-----------
    @RequestMapping("/toListSmallType")
    public String toListSmallType(GoodsTypeThreeQuery query, ModelMap map){
        //查看大分类
        GoodsType type = (GoodsType) findGoodsTypeService.findById(query.getType_id()==null?"":query.getType_id());
        map.put("type", type);
        //查询小分类
        List<GoodsType> list = (List<GoodsType>) goodsTypeSmallService.list(query);
        map.put("list", list);
        return "/goodsType/listSmallType";
    }

    @RequestMapping("/toAddGoodsSmallType")
    public String toAddGoodsSmallType(ModelMap map,String typeId){
        //查看大分类
        GoodsType type = (GoodsType) findGoodsTypeService.findById(typeId);
        map.put("type", type);
        map.put("newDateTime", System.currentTimeMillis()+"");
        return "/goodsType/addSmallType";
    }

    //--------------listHotGoodsType 商城热门分类----------------
    @RequestMapping("/listHotGoodsType")
    public String listHotGoodsType(GoodsTypeThreeQuery query, ModelMap map){
        query.setIs_hot("1");
        //查询小分类
        List<GoodsType> list = (List<GoodsType>) goodsTypeSmallService.list(query);
        map.put("list", list);
        return "/goodsType/listSmallHotType";
    }

    /**
     * 更新商城热门分类
     * @param type
     * @return
     */
    @RequestMapping("/updateGoodsHoTType")
    @ResponseBody
    public String updateGoodsHoTType(GoodsType type){
        try {
            goodsTypeSmallServiceUpdate.update(type);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @RequestMapping("/getAllGoodsTypes")
    @ResponseBody
    public String getAllGoodsTypes(String father,HttpServletRequest request, HttpServletResponse response) throws IOException {
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
        query.setIs_type(father);
        //查询小分类
        List<GoodsType> list = (List<GoodsType>) goodsTypeSmallService.list(query);
        try {
            DataTip tip = new DataTip();
            tip.setData(list);
            return reBack(toJSONString(tip), request, response);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }




    @Autowired
    @Qualifier("goodsTypeService")
    private DeleteService goodsTypeServiceDelete;

    /**
     * 删除分类
     * @param id
     * @return
     */
    @RequestMapping("/deleteGoodsTypeById")
    @ResponseBody
    public String deleteGoodsTypeById(String id){
        try {
            goodsTypeServiceDelete.delete(id);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


}
