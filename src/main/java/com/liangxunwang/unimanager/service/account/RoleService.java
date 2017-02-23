package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.RoleDao;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/8/8.
 */
@Service("roleService")
public class RoleService implements SaveService, ListService, DeleteService, FindService, UpdateService {

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;
    @Override
    public Object delete(Object object) throws ServiceException {
        if (object instanceof  String){
            String id = (String) object;
            roleDao.delete(id);
        }
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        return roleDao.list();
    }

    @Override
    public Object save(Object object) throws ServiceException {
        if (object instanceof Role){
            Role role = (Role) object;
            role.setId(UUIDFactory.random());
            roleDao.save(role);
        }
        return null;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        String id = (String) object;
        return roleDao.find(id);
    }

    @Override
    public Object update(Object object) {
        if (object instanceof Role){
            Role role = (Role) object;
            roleDao.update(role);
        }
        return null;
    }
}
