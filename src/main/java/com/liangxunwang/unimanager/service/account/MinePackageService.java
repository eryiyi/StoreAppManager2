package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.MinePackageDao;
import com.liangxunwang.unimanager.model.MinePackage;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/3/3.
 * 我的钱包管理
 */
@Service("minePackageService")
public class MinePackageService implements SaveService,DeleteService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("minePackageDao")
    private MinePackageDao minePackageDao;

    //注册用户的时候 插入钱包
    @Override
    public Object save(Object object) throws ServiceException {
        MinePackage adObj = (MinePackage) object;
        adObj.setPackage_id(UUIDFactory.random());
        minePackageDao.save(adObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String emp_id = (String) object;
        minePackageDao.delete(emp_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return minePackageDao.findById((String) object);
    }

    //后台充值金额增加
    @Override
    public Object update(Object object) {
        MinePackage adObj = (MinePackage) object;
        minePackageDao.update(adObj);
        return null;
    }
}
