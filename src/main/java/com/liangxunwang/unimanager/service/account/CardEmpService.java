package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CardEmpDao;
import com.liangxunwang.unimanager.model.CardEmp;
import com.liangxunwang.unimanager.query.CardEmpQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("cardEmpService")
public class CardEmpService implements ListService,SaveService,ExecuteService, UpdateService {
    @Autowired
    @Qualifier("cardEmpDao")
    private CardEmpDao cardEmpDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CardEmpQuery query = (CardEmpQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("empid", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_use())){
            map.put("is_use", query.getIs_use());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }if(!StringUtil.isNullOrEmpty(query.getEmp_mobile())){
            map.put("emp_mobile", query.getEmp_mobile());
        }if(!StringUtil.isNullOrEmpty(query.getEmp_number())){
            map.put("emp_number", query.getEmp_number());
        }
        List<CardEmp> lists = cardEmpDao.lists(map);
        long count = cardEmpDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CardEmp cardEmp = (CardEmp) object;
        cardEmp.setLx_card_emp_start_time(System.currentTimeMillis()+"");
        cardEmp.setIs_use("0");
        cardEmp.setLx_card_emp_year("1");
        cardEmpDao.save(cardEmp);
        return null;
    }


    @Override
    public Object execute(Object object) throws ServiceException {
        return cardEmpDao.findById((String) object);
    }

    @Override
    public Object update(Object object) {
        CardEmp cardEmp = (CardEmp) object;
        cardEmpDao.update(cardEmp);
        return null;
    }

}
