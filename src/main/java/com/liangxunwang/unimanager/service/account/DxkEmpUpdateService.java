package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CardEmpDao;
import com.liangxunwang.unimanager.service.UpdateService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * 定向卡会员到期执行
 */
@Service("dxkEmpUpdateService")
public class DxkEmpUpdateService implements UpdateService {

    @Autowired
    @Qualifier("cardEmpDao")
    private CardEmpDao cardEmpDao;

    @Override
    public Object update(Object object) {
        cardEmpDao.updateOverTime(System.currentTimeMillis()+"");
        cardEmpDao.updateOverTime2(System.currentTimeMillis()+"");
        return null;
    }

}
