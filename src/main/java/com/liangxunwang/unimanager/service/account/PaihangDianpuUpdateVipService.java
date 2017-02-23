package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.PaihanDianpuDao;
import com.liangxunwang.unimanager.dao.PaihangObjDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 店铺上榜周期到期
 */
@Service("paihangDianpuUpdateVipService")
public class PaihangDianpuUpdateVipService implements UpdateService {
    @Autowired
    @Qualifier("paihanDianpuDao")
    private PaihanDianpuDao paihanDianpuDao;

    @Override
    public Object update(Object object) {
        paihanDianpuDao.updateOverTime(System.currentTimeMillis()+"");
        return null;
    }

}
