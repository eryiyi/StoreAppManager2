package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.RoleDao;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("adminLoginService")
public class AdminLoginService implements ExecuteService {

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Override
    public Object execute(Object object) {
        Object[] params = (Object[]) object;
        String username = (String) params[0];
        String password = (String) params[1];

        AdminVO admin = adminDao.findByUsername(username);
        if (admin == null){
            throw new ServiceException("USERNAME_ERROR");
        }
        if (!admin.getPassword().equals(password)){
            throw new ServiceException("PASSWORD_ERROR");
        }
        String permission = null;
        if ("all".equals(admin.getPermissions())){
            permission = "all";
        } else {
            if (!StringUtil.isNullOrEmpty(admin.getPermissions())){
                Role role = roleDao.find(admin.getPermissions());
                permission = role.getPermissions();
            }
        }
        return new Object[]{admin, permission};
    }
}
