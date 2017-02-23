package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.ManagerLogDao;
import com.liangxunwang.unimanager.mvc.vo.ManagerLogVO;
import com.liangxunwang.unimanager.query.ManagerLogQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Service("managerLogService")
public class ManagerLogService implements ListService{
    @Autowired
    @Qualifier("managerLogDao")
    private ManagerLogDao managerLogDao;

    @Override
    public Object list(Object object) throws ServiceException {
        ManagerLogQuery query = (ManagerLogQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        Map<String, Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        List<ManagerLogVO> list = managerLogDao.list(map);
        long count = managerLogDao.count();
        return new Object[] {list, count};
    }
}
