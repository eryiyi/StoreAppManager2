package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppGoodsFavourDao;
import com.liangxunwang.unimanager.model.GoodsFavour;
import com.liangxunwang.unimanager.mvc.vo.GoodsFavourVO;
import com.liangxunwang.unimanager.query.FavoursQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * Created by Administrator on 2015/8/10.
 */
@Service("appGoodsFavourService")
public class AppGoodsFavourService implements SaveService ,ListService ,DeleteService{

    @Autowired
    @Qualifier("appGoodsFavourDao")
    private AppGoodsFavourDao goodsFavourDao;


    public Object save(Object object) throws ServiceException {
        GoodsFavour goodsFavour = (GoodsFavour) object;
        goodsFavour.setFavour_id(UUIDFactory.random());
        goodsFavour.setDateline(System.currentTimeMillis() + "");
        Map<String, Object> maps = new HashMap<String, Object>();
        maps.put("goods_id",goodsFavour.getGoods_id());
        maps.put("emp_id_favour", goodsFavour.getEmp_id_favour());
        //查询是否已经收藏
        GoodsFavour goodsFavour1 = goodsFavourDao.isMineFavour(maps);
        if(goodsFavour1 != null){
            throw new ServiceException("ISFAVOUR");
        }
        goodsFavourDao.save(goodsFavour);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        FavoursQuery query = (FavoursQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        map.put("empId", query.getEmpId());
        List<GoodsFavourVO> list = goodsFavourDao.findList(map);
        for (GoodsFavourVO vo : list){
            if (!StringUtil.isNullOrEmpty(vo.getGoods_cover())){
                String[] pics = vo.getGoods_cover().split(",");
                StringBuffer buffer = new StringBuffer();
                for (int i=0; i<pics.length; i++){
                    buffer.append(Constants.URL+pics[i]);
                    if (i<pics.length-1){
                        buffer.append(",");
                    }
                }
                vo.setGoods_cover(buffer.toString());
            }
            vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
        }
        return list;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String favourId = (String) object;
        goodsFavourDao.deleteFavourById(favourId);
        return null;
    }
}
