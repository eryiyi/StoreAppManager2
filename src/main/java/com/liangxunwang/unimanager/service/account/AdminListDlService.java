package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/12.
 */
@Service("adminListDlService")
public class AdminListDlService implements ListService, SaveService{

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

        List<AdminVO> lists = adminDao.listsDl(map);
        long count = adminDao.countDl(map);

        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Admin admin = (Admin) object;
        admin.setId(UUIDFactory.random());//设置ID
        //先查询该用户账号是否存在
        AdminVO adminVO = adminDao.findByUsername(admin.getUsername());
        if(adminVO != null){
            //说明存在了
            throw new ServiceException("has_exist");
        }
        adminDao.add(admin);
        return 200;
    }

}
