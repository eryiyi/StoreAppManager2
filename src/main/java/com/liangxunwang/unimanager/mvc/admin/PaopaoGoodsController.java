package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.LxClass;
import com.liangxunwang.unimanager.model.PaopaoGoods;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import com.liangxunwang.unimanager.query.FindGoodsByIdQuery;
import com.liangxunwang.unimanager.query.GoodsTypeThreeQuery;
import com.liangxunwang.unimanager.query.LxClassQuery;
import com.liangxunwang.unimanager.query.PaopaoGoodsQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/16.
 */
@Controller
@RequestMapping("/paopaogoods")
public class PaopaoGoodsController extends ControllerConstants {
    @Autowired
    @Qualifier("goodsTypeService")
    private ListService goodsTypeListService;

    @Autowired
    @Qualifier("goodsClassService")
    private ListService goodsClassServiceList;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private SaveService paopaoGoodsSaveService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private ListService paopaoGoodsListService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private DeleteService paopaoGoodsDeleteService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private FindService paopaoGoodsFindService;

    @Autowired
    @Qualifier("paopaoGoodsService")
    private UpdateService paopaoGoodsUpdateService;

    @Autowired
    @Qualifier("paopaoGoodsFindService")
    private UpdateService paopaoGoodsFindServiceUpdate;

    @Autowired
    @Qualifier("appPaopaoGoodsService")
    private UpdateService appPaopaoGoodsUpdateService;

    @Autowired
    @Qualifier("appPaopaoGoodsService")
    private ListService appPaopaoGoodsServiceList;
    
    @RequestMapping("toAdd")
    public String toAdd(GoodsTypeThreeQuery query,ModelMap map, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(admin != null){
            if("0".equals(admin.getIs_pingtai())){
                //不是平台账号
                map.put("is_pingtai", "0");
                //查询推荐的分类
                LxClassQuery query1 = new LxClassQuery();
                query1.setF_lx_class_id("0");
                query1.setIs_del("0");
                List<LxClass> listClassbig = (List<LxClass>) goodsClassServiceList.list(query1);
                map.put("listClassbig", listClassbig);
            }else if("1".equals(admin.getIs_pingtai())){
                //是平台账号
                map.put("is_pingtai", "1");
                query.setType_isuse("0");
                List<GoodsType> listAll = (List<GoodsType>) goodsTypeListService.list(query);
                query.setIs_type("0");
                List<GoodsType> listBig = (List<GoodsType>) goodsTypeListService.list(query);
                map.put("listBig", listBig);
                map.put("listAll", listAll);
            }
        }
        return "/paopaogoods/add";
    }


    @RequestMapping("toAddDxk")
    public String toAddDxk(GoodsTypeThreeQuery query,ModelMap map, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
//        query.setType_isuse("0");
//        List<GoodsType> listAll = (List<GoodsType>) goodsTypeListService.list(query);
//        query.setIs_type("0");
//        List<GoodsType> listBig = (List<GoodsType>) goodsTypeListService.list(query);
//        map.put("listBig", listBig);
//        map.put("listAll", listAll);
        if(admin != null){
            if("0".equals(admin.getIs_pingtai())){
                //不是平台账号
                map.put("is_pingtai", "0");
                //查询推荐的分类
                LxClassQuery query1 = new LxClassQuery();
                query1.setF_lx_class_id("0");
                query1.setIs_del("0");
                List<LxClass> listClassbig = (List<LxClass>) goodsClassServiceList.list(query1);
                map.put("listClassbig", listClassbig);
            }else if("1".equals(admin.getIs_pingtai())){
                //是平台账号
                map.put("is_pingtai", "1");
                query.setType_isuse("0");
                List<GoodsType> listAll = (List<GoodsType>) goodsTypeListService.list(query);
                query.setIs_type("0");
                List<GoodsType> listBig = (List<GoodsType>) goodsTypeListService.list(query);
                map.put("listBig", listBig);
                map.put("listAll", listAll);
            }
        }
        return "/paopaogoods/addDxk";
    }

    @RequestMapping(value = "save", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String save(PaopaoGoods goods, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(admin != null){
            if(!StringUtil.isNullOrEmpty(admin.getId())){
                goods.setManager_id(admin.getId());
            }
            if(!StringUtil.isNullOrEmpty(admin.getEmp_id())){
                goods.setEmpId(admin.getEmp_id());
            }
            if("0".equals(admin.getIs_pingtai())){
                //不是平台账号
                goods.setIs_zhiying("0");
            }else if("1".equals(admin.getIs_pingtai())){
                //是平台账号
                goods.setIs_zhiying("1");
            }
        }
        paopaoGoodsSaveService.save(goods);
        return toJSONString(SUCCESS);
    }


    /**
     * App端取商品列表
     * @param query
     * @return
     */
    @RequestMapping(value = "listGoods", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String listGoods(PaopaoGoodsQuery query, Page page){
        try {
            query.setIndex(page.getIndex()==0?1:page.getIndex());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            query.setGoods_count("1");
            query.setDianpu_number("1");
            List<PaopaoGoodsVO> list = (List<PaopaoGoodsVO>) appPaopaoGoodsServiceList.list(query);
            List<PaopaoGoodsVO> list1 = new ArrayList<PaopaoGoodsVO>();
            if("1".equals(query.getIsMine())){
                //说明查询我的
                list1.addAll(list);
            }else{
                //说明不是查询我的
                for(PaopaoGoodsVO paopaoGoodsVO:list){
                    if("0".equals(paopaoGoodsVO.getIsUse())){
                        list1.add(paopaoGoodsVO);//只查询有用的
                    }
                }
            }
            DataTip tip = new DataTip();
            tip.setData(list1);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    /**
     * 后台查询商品
     * @param query
     * @param page
     * @param session
     * @param map
     * @return
     */
    @RequestMapping("list")
    public String list(PaopaoGoodsQuery query, Page page, HttpSession session, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        query.setIndex(page.getPage()==0?1:page.getPage());
        query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        if(!StringUtil.isNullOrEmpty(admin.getEmp_id()) && !"0".equals(admin.getEmp_id())){
            //商家登录
            map.put("is_admin", "1");//0是管理员登录  1的话是商家登录
            query.setEmpId(admin.getEmp_id());
        }else{
            map.put("is_admin", "0");
        }

        if("0".equals(admin.getIs_pingtai())){
            //如果是商家登陆的话 永远只允许查询商家产品
            query.setIs_zhiying("0");
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_zhiying())){
            map.put("is_zhiying", query.getIs_zhiying());
        }
        if(!StringUtil.isNullOrEmpty(query.getGoods_position())){
            map.put("goods_position", query.getGoods_position());
        }
        if(!StringUtil.isNullOrEmpty(query.getDianpu_number())){
            map.put("dianpu_number", query.getDianpu_number());
        }else {
            query.setIs_time("1");
        }
        Object[] result = (Object[]) paopaoGoodsListService.list(query);
        map.put("list", result[0]);
        long count = (Long) result[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/paopaogoods/list";
    }



    /**
     * 根据ID删除我的商品
     * @param id
     * @return
     */
    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id){
        paopaoGoodsDeleteService.delete(id);
        return toJSONString(SUCCESS);
    }


    @Autowired
    @Qualifier("paopaoGoodsFindService")
    private FindService paopaoGoodsFindServiceFind;

    @RequestMapping("edit")
    public String edit(HttpSession session , String id, ModelMap map){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();

        PaopaoGoods goods = (PaopaoGoods) paopaoGoodsFindServiceFind.findById(id);
//        List<GoodsType> listAll = (List<GoodsType>) goodsTypeListService.list(query);
//        query.setIs_type("0");
//        List<GoodsType> listBig = (List<GoodsType>) goodsTypeListService.list(query);
//        map.put("listBig", listBig);
//        map.put("listAll", listAll);
        map.put("goods", goods);

        if(goods != null){
            if("1".equals(goods.getIs_zhiying())){
                //商家的商品
                map.put("is_pingtai", "1");

                query.setType_isuse("0");
                List<GoodsType> listAll = (List<GoodsType>) goodsTypeListService.list(query);
                query.setIs_type("0");
                List<GoodsType> listBig = (List<GoodsType>) goodsTypeListService.list(query);
                map.put("listBig", listBig);
                map.put("listAll", listAll);
            }else if("0".equals(goods.getIs_zhiying())){
                map.put("is_pingtai", "0");
                LxClassQuery query1 = new LxClassQuery();
                query1.setIs_del("0");
                List<LxClass> listClassSmall = (List<LxClass>) goodsClassServiceList.list(query1);
                map.put("listClassSmall", listClassSmall);

                query1.setF_lx_class_id("0");
                List<LxClass> listClassbig = (List<LxClass>) goodsClassServiceList.list(query1);
                map.put("listClassbig", listClassbig);
            }
        }

        return "/paopaogoods/edit";
    }

    @RequestMapping("tuijian")
    public String tuijian(String id, ModelMap map){
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
        PaopaoGoods goods = (PaopaoGoods) paopaoGoodsFindServiceFind.findById(id);
        map.put("good", goods);
        return "/paihang/addpaihang";
    }

    @RequestMapping("paixu")
    public String paixu(String id, ModelMap map){
        GoodsTypeThreeQuery query = new GoodsTypeThreeQuery();
        PaopaoGoods goods = (PaopaoGoods) paopaoGoodsFindServiceFind.findById(id);
        map.put("goods", goods);
        return "/paopaogoods/addPaixu";
    }

    @RequestMapping("update")
    @ResponseBody
    public String update(PaopaoGoods goods){
        paopaoGoodsUpdateService.update(goods);
        return toJSONString(SUCCESS);
    }




    /**
     * App根据ID查询商品详情
     * @return
     */
    @RequestMapping(value = "findById", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String findByGoodsId(FindGoodsByIdQuery query){
        if (StringUtil.isNullOrEmpty(query.getId())){
            return toJSONString(ERROR_1);
        }
        Object[] params = new Object[]{query.getId(), (query.getEmpid()==null?"":query.getEmpid())};
        PaopaoGoodsVO vo = (PaopaoGoodsVO) paopaoGoodsFindService.findById(params);
        DataTip tip = new DataTip();
        tip.setData(vo);
        return toJSONString(tip);
    }

    /**
     * 根据商品ID查找商品详情
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("detail")
    public String detail(String id, ModelMap map){
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(ERROR_1);
        }
        PaopaoGoodsVO paopaoGoodsVO = (PaopaoGoodsVO) paopaoGoodsFindServiceFind.findById(id);
        map.put("vo", paopaoGoodsVO);
        return "/paopaogoods/detail";
    }

//    @RequestMapping("updatePosition")
//    @ResponseBody
//    public String updatePosition(String id, String position){
//        Map<String, String> map = new HashMap<String, String>();
//        map.put("id" , id);
//        map.put("goods_position" , position);
//        appPaopaoGoodsUpdateService.update(map);
//        return toJSONString(SUCCESS);
//    }

    @RequestMapping("updatePosition")
    @ResponseBody
    public String updatePosition(PaopaoGoods goods){
        paopaoGoodsFindServiceUpdate.update(goods);
        return toJSONString(SUCCESS);
    }


    @Autowired
    @Qualifier("appPaopaoGoodsJiaService")
    private UpdateService appPaopaoGoodsJiaService;

    /**
     * App上架 下架产品
     * @param id
     * @return
     */
    @RequestMapping(value = "updatePaopaoGoodsJia", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String updatePaopaoGoodsJia(String id,String status){
        Map<String, String> map = new HashMap<String, String>();
        map.put("id", id);
        map.put("status", status);
        appPaopaoGoodsJiaService.update(map);
        return toJSONString(SUCCESS);
    }


    /**
     * 分享商品
     * @param id
     * @param map
     * @return
     */
    @RequestMapping("shareGoodsUrl")
    public String shareGoodsUrl(String id, ModelMap map){
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(ERROR_1);
        }
        Object[] params = new Object[2];
        params[0] = id;
        params[1] = "";

        PaopaoGoodsVO paopaoGoodsVO = (PaopaoGoodsVO) paopaoGoodsFindService.findById(params);
        map.put("vo", paopaoGoodsVO);
        return "/paopaogoods/viewGoods";
    }



    @RequestMapping("toGoodsContent")
    public String toGoodsContent(String id,ModelMap map, HttpSession session){
        Admin admin = (Admin) session.getAttribute(ACCOUNT_KEY);
        if (StringUtil.isNullOrEmpty(id)){
            return toJSONString(ERROR_1);
        }
        PaopaoGoodsVO paopaoGoodsVO = (PaopaoGoodsVO) paopaoGoodsFindServiceFind.findById(id);
        map.put("vo", paopaoGoodsVO);
        return "/paopaogoods/goodsContent";
    }
}
