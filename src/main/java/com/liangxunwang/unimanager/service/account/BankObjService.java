package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.BankObjDao;
import com.liangxunwang.unimanager.model.BankObj;
import com.liangxunwang.unimanager.query.BankCardQuery;
import com.liangxunwang.unimanager.service.*;
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
@Service("bankObjService")
public class BankObjService implements ListService,SaveService,DeleteService,ExecuteService {
    @Autowired
    @Qualifier("bankObjDao")
    private BankObjDao bankObjDao;

    @Override
    public Object list(Object object) throws ServiceException {
        BankCardQuery query = (BankCardQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        List<BankObj> lists = bankObjDao.lists(map);
        return lists;
    }

    @Override
    public Object save(Object object) throws ServiceException {
        BankObj bankObj = (BankObj) object;
        //先查询有多少个银行卡
        Map<String, Object> map = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(bankObj.getEmp_id())){
            map.put("emp_id", bankObj.getEmp_id());
        }
        List<BankObj> lists = bankObjDao.lists(map);
        if(lists != null && lists.size() > 4){
            throw new ServiceException("adIsTooMuch");
        }
        bankObj.setBank_id(UUIDFactory.random());
        bankObjDao.save(bankObj);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String bank_id = (String) object;
        bankObjDao.delete(bank_id);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return bankObjDao.findById((String) object);
    }

}
