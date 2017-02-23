package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CountRecordDao;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.query.CountRecordQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
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
@Service("countRecordService")
public class CountRecordService implements ListService,SaveService,ExecuteService {

    @Autowired
    @Qualifier("countRecordDao")
    private CountRecordDao countRecordDao;

    @Override
    public Object list(Object object) throws ServiceException {
        CountRecordQuery query = (CountRecordQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getPhone_number())){
            map.put("phone_number", query.getPhone_number());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }
        List<CountRecord> lists = countRecordDao.lists(map);
        long count = countRecordDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        CountRecord countRecord = (CountRecord) object;
        countRecord.setLx_count_record_id(UUIDFactory.random());
        countRecord.setDateline(System.currentTimeMillis()+"");
        countRecordDao.save(countRecord);
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        return countRecordDao.findById((String) object);
    }

}
