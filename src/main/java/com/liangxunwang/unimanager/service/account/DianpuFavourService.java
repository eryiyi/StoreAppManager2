package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DianpuFavourDao;
import com.liangxunwang.unimanager.model.DianPuFavour;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("dianpuFavourService")
public class DianpuFavourService implements ListService,SaveService,DeleteService,FindService {
    @Autowired
    @Qualifier("dianpuFavourDao")
    private DianpuFavourDao dianpuFavourDao;

    @Override
    public Object list(Object object) throws ServiceException {
        DianpuFavourQuery query = (DianpuFavourQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id_favour())){
            //查询我的收藏
            map.put("emp_id_favour", query.getEmp_id_favour());//会员ID
            List<DianpuFavourVO> lists = dianpuFavourDao.lists(map);

            if(lists != null){
                for(DianpuFavourVO dianPuFavour:lists){
                    if(!StringUtil.isNullOrEmpty(dianPuFavour.getEmp_cover())){
                        if(dianPuFavour.getEmp_cover().startsWith("upload")){
                            dianPuFavour.setEmp_cover(Constants.URL + dianPuFavour.getEmp_cover());
                        }else {
                            dianPuFavour.setEmp_cover(Constants.QINIU_URL + dianPuFavour.getEmp_cover());
                        }
                    }
                    if(!StringUtil.isNullOrEmpty(dianPuFavour.getCompany_pic())){
                        if(dianPuFavour.getCompany_pic().startsWith("upload")){
                            dianPuFavour.setCompany_pic(Constants.URL + dianPuFavour.getCompany_pic());
                        }else {
                            dianPuFavour.setCompany_pic(Constants.QINIU_URL + dianPuFavour.getCompany_pic());
                        }
                    }
                }
            }
            return lists;
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            //查询商家的粉丝
            map.put("emp_id", query.getEmp_id());//商家ID
            List<DianpuFavourVO> lists = dianpuFavourDao.listsFensi(map);
            if(lists != null){
                for(DianpuFavourVO dianPuFavour:lists){
                    if(!StringUtil.isNullOrEmpty(dianPuFavour.getEmp_cover())){
                        if(dianPuFavour.getEmp_cover().startsWith("upload")){
                            dianPuFavour.setEmp_cover(Constants.URL + dianPuFavour.getEmp_cover());
                        }else {
                            dianPuFavour.setEmp_cover(Constants.QINIU_URL + dianPuFavour.getEmp_cover());
                        }
                    }
                    if(!StringUtil.isNullOrEmpty(dianPuFavour.getCompany_pic())){
                        if(dianPuFavour.getCompany_pic().startsWith("upload")){
                            dianPuFavour.setCompany_pic(Constants.URL + dianPuFavour.getCompany_pic());
                        }else {
                            dianPuFavour.setCompany_pic(Constants.QINIU_URL + dianPuFavour.getCompany_pic());
                        }
                    }
                }
            }
            return lists;
        }
        return null;

    }

    @Override
    public Object save(Object object) throws ServiceException {
        DianPuFavour dianPuFavour = (DianPuFavour) object;
        //先查询是否存在了
        //查询是否已经收藏
        DianPuFavour goodsFavour1 = dianpuFavourDao.find(dianPuFavour);
        if(goodsFavour1 != null){
            throw new ServiceException("ISFAVOUR");
        }

        dianPuFavour.setFavour_no(UUIDFactory.random());
        dianPuFavour.setDateline(System.currentTimeMillis() + "");
        dianpuFavourDao.save(dianPuFavour);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String favour_no = (String) object;
        dianpuFavourDao.delete(favour_no);
        return null;
    }



    @Override
    public Object findById(Object object) throws ServiceException {
        return dianpuFavourDao.find((DianPuFavour) object);
    }
}
