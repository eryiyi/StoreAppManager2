package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.RoleDao;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/12.
 */
@Service("adminListSjService")
public class AdminListSjService implements ListService{

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;


    @Override
    public Object list(Object object) throws ServiceException {
        AdminQuery query = (AdminQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if (!StringUtil.isNullOrEmpty(query.getIsUse())) {
            map.put("is_use", query.getIsUse());
        }
        if (!StringUtil.isNullOrEmpty(query.getCont())) {
            map.put("cont", query.getCont());
        }

        List<AdminVO> lists = adminDao.listsSj(map);
        long count = adminDao.countSj(map);

        return new Object[]{lists, count};
    }


}
