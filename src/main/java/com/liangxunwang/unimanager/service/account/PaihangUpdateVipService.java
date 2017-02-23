package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 商品上榜周期到期
 */
@Service("paihangUpdateVipService")
public class PaihangUpdateVipService implements UpdateService {
    @Autowired
    @Qualifier("paihangObjDao")
    private PaihangObjDao paihangObjDao;

    @Override
    public Object update(Object object) {
        paihangObjDao.updateOverTime(System.currentTimeMillis()+"");
        return null;
    }

}
