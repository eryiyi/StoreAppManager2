package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihanDianpuDao;
import com.liangxunwang.unimanager.model.PaihangDianpu;
import com.liangxunwang.unimanager.mvc.vo.PaihangDianpuVo;
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
@Service("paihangDianpuService")
public class PaihangDianpuService implements ListService,DeleteService,ExecuteService,UpdateService,SaveService {
    @Autowired
    @Qualifier("paihanDianpuDao")
    private PaihanDianpuDao paihanDianpuDao;

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

        List<PaihangDianpuVo> lists = paihanDianpuDao.listRecordVo(map);
        for (PaihangDianpuVo record : lists){
            if (!StringUtil.isNullOrEmpty(record.getCompany_pic())){
                if (record.getCompany_pic().startsWith("upload")){
                    record.setCompany_pic(Constants.URL + record.getCompany_pic());
                }else {
                    record.setCompany_pic(Constants.QINIU_URL + record.getCompany_pic());
                }
            }
            if(!StringUtil.isNullOrEmpty(record.getEnd_time())){
                record.setEnd_time(DateUtil.getDate(record.getEnd_time(), "yyyy-MM-dd"));
            }
        }
        long count = paihanDianpuDao.count(map);
        return new Object[]{lists, count};
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        String mm_paihang_id = (String) object;
        paihanDianpuDao.deleteById(mm_paihang_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        PaihangQuery query = (PaihangQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_paihang_id())){
            map.put("mm_paihang_id",query.getMm_paihang_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id",query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_del())){
            map.put("is_del",query.getIs_del());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_type())){
            map.put("is_type",query.getIs_type());
        }
        PaihangDianpuVo paihangObj = paihanDianpuDao.findById(map);
        if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
            paihangObj.setEnd_time(DateUtil.getDate(paihangObj.getEnd_time(), "yyyy-MM-dd"));
        }
        return paihangObj;
    }

    @Override
    public Object update(Object object) {
        if (object instanceof PaihangDianpu){
            PaihangDianpu paihangObj = (PaihangDianpu) object;
            if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
                paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
            }
            paihanDianpuDao.updateTop(paihangObj);
        }
        return null;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        PaihangDianpu paihangObj = (PaihangDianpu) object;
        //先查询是否存在该商品了

        Map<String, Object> map = new HashMap<String, Object>();

        if(!StringUtil.isNullOrEmpty(paihangObj.getEmp_id())){
            map.put("emp_id", paihangObj.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(paihangObj.getIs_type())){
            map.put("is_type", paihangObj.getIs_type());
        }

        PaihangDianpuVo paihangObjVO = paihanDianpuDao.findByGoodsId(map);
        if(paihangObjVO != null && !StringUtil.isNullOrEmpty(paihangObjVO.getMm_paihang_id())){
            //该店铺已经添加到推荐首页不能重复添加
            throw new ServiceException("Has_exist");
        }
        try {
            paihangObj.setMm_paihang_id(UUIDFactory.random());
            if(!StringUtil.isNullOrEmpty(paihangObj.getEnd_time())){
                paihangObj.setEnd_time(DateUtil.getMs(paihangObj.getEnd_time(), "yyyy-MM-dd") + "");
            }
            paihanDianpuDao.save(paihangObj);
        }catch (Exception e){
            throw new ServiceException(Constants.SAVE_ERROR);
        }
        return null;
    }
}
