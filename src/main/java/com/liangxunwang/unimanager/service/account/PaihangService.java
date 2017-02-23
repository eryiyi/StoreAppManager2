package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import com.liangxunwang.unimanager.query.PaihangQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
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
@Service("paihangService")
public class PaihangService implements ListService,DeleteService,ExecuteService,UpdateService,SaveService {
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getIs_del())) {
            map.put("is_del", query.getIs_del());
        }

        if (!StringUtil.isNullOrEmpty(query.getIs_type())) {
            map.put("is_type", query.getIs_type());
        }

        if (!StringUtil.isNullOrEmpty(query.getKeyword())) {
            map.put("keyword", query.getKeyword());
        }
        if (!StringUtil.isNullOrEmpty(query.getGoods_count())) {
            map.put("goods_count", query.getGoods_count());
        }

        List<PaihangObjVO> lists = paihangObjDao.listRecordVo(map);
        for (PaihangObjVO record : lists){
            if (!StringUtil.isNullOrEmpty(record.getGoods_cover())){
                if (record.getGoods_cover().startsWith("upload")){
                    record.setGoods_cover(Constants.URL + record.getGoods_cover());
                }else {
                    record.setGoods_cover(Constants.QINIU_URL + record.getGoods_cover());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getEnd_time())){
                record.setEnd_time(DateUtil.getDate(record.getEnd_time(), "yyyy-MM-dd"));
            }
        }
        long count = paihangObjDao.count(map);
        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_paihang_id = (String) object;
        paihangObjDao.deleteById(mm_paihang_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_paihang_id())){
            map.put("mm_paihang_id",query.getMm_paihang_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getGoods_id())){
            map.put("goods_id",query.getGoods_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del",query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_type())){
            map.put("is_type",query.getIs_type());
        }
        PaihangObjVO paihangObj = paihangObjDao.findById(map);
        if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
            paihangObj.setEnd_time(DateUtil.getDate(paihangObj.getEnd_time(), "yyyy-MM-dd"));
        }
        return paihangObj;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof PaihangObj){
            PaihangObj paihangObj = (PaihangObj) object;
            if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
                paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
            }
            paihangObjDao.updateTop(paihangObj);
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        PaihangObj paihangObj = (PaihangObj) object;
        //先查询是否存在该商品了

        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(paihangObj.getGoods_id())){
            map.put("goods_id", paihangObj.getGoods_id());
        }
        if(!StringUtil.isNullOrEmpty(paihangObj.getIs_type())){
            String schools = paihangObj.getIs_type();
            if(!StringUtil.isNullOrEmpty(schools)){
                String[] arras = schools.split("\\|");
                if(arras != null){
                    for(String str:arras){
                        map.put("is_type", str);
                        PaihangObjVO paihangObjVO = paihangObjDao.findByGoodsId(map);
                        if(paihangObjVO != null && !StringUtil.isNullOrEmpty(paihangObjVO.getMm_paihang_id())){
                            //该商品已经添加到推荐首页不能重复添加
//                            throw new ServiceException("Has_exist");
                            continue;
                        }else {
                            try {
                                paihangObj.setMm_paihang_id(UUIDFactory.random());
                                if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
                                    paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
                                }
                                paihangObj.setIs_type(str);
                                paihangObjDao.save(paihangObj);
                            }catch (Exception e){
                                throw new ServiceException(Constants.SAVE_ERROR);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }
}
