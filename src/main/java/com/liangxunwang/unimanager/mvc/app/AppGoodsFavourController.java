package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.GoodsFavour;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.GoodsFavourVO;
import com.liangxunwang.unimanager.query.FavoursQuery;
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
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
@Controller
public class AppGoodsFavourController extends ControllerConstants{

    @Autowired
    @Qualifier("appGoodsFavourService")
    private SaveService saveAppGoodsFavourService;

    @Autowired
    @Qualifier("appGoodsFavourService")
    private ListService listAppGoodsFavourService;

    @Autowired
    @Qualifier("appGoodsFavourService")
    private DeleteService deleteAppGoodsFavourService;

    //保存商品收藏
    @RequestMapping("/saveGoodsFavour")
    @ResponseBody
    public String saveGoodsFavour(GoodsFavour goodsFavour){
        if (StringUtil.isNullOrEmpty(goodsFavour.getGoods_id())){
            return toJSONString(ERROR_1);//没有选中商品
        }
        if(StringUtil.isNullOrEmpty(goodsFavour.getEmp_id_favour())){
            return toJSONString(ERROR_1);//收藏会员ID不能为空
        }

        //查询该用户是否已经收藏
        try {
            saveAppGoodsFavourService.save(goodsFavour);
        }catch (Exception e){
            if (e.getMessage().equals("ISFAVOUR")){//已经收藏过了
                return toJSONString(ERROR_2);
            }
        }
        return toJSONString(SUCCESS);//保存成功
    }

    @RequestMapping(value = "/listFavour", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listFavour(FavoursQuery query, Page page){
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            List<GoodsFavourVO> list = (List<GoodsFavourVO>) listAppGoodsFavourService.list(query);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping(value = "/deleteFavour", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteFavour(String favourId){
        deleteAppGoodsFavourService.delete(favourId);
        return toJSONString(SUCCESS);
    }

}
