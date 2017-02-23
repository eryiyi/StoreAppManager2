package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.KefuTelDao;
import com.liangxunwang.unimanager.model.KefuTel;
import com.liangxunwang.unimanager.query.KefuQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service("appKefuTelService")
public class AppKefuTelService implements ListService {

    @Autowired
    @Qualifier("kefuTelDao")
    private KefuTelDao fuwuDao;

    @Override
    public Object list(Object object) throws ServiceException {
        KefuQuery query = (KefuQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getMm_tel_type())){
            map.put("mm_tel_type", query.getMm_tel_type());
        }
        List<KefuTel> lists = fuwuDao.listsAll(map);
        for(KefuTel kefuTel:lists){
            if(!StringUtil.isNullOrEmpty(kefuTel.getMm_cover())){
                if (kefuTel.getMm_cover().startsWith("upload")) {
                    kefuTel.setMm_cover(Constants.URL + kefuTel.getMm_cover());
                }else {
                    kefuTel.setMm_cover(Constants.QINIU_URL + kefuTel.getMm_cover());
                }
            }
        }
        return lists;
    }

}
