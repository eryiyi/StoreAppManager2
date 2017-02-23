package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.RoleDao;
import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

/**
 * Created by zhl on 2015/8/12.
 */
@Service("adminService")
public class AdminService implements UpdateService , ExecuteService ,ListService, FindService, SaveService{

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;
    @Override
    public Object update(Object object) {
        if (object instanceof Object[]){
            Object[] params = (Object[]) object;
            String id = (String) params[0];
            String is_use = (String) params[1];
            adminDao.updateType(id, is_use);
        }
        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        String pass = (String) params[0];
        String ePass = (String) params[1];
        String id = (String) params[2];
        //先查询是否存在
        AdminVO adminVO = adminDao.findById(id);
        if(adminVO != null){
            if(ePass.equals(adminVO.getPassword())){
                adminDao.updatePass(id, pass);
            }else {
                throw new ServiceException("pass_error");
            }
        }else {
            throw new ServiceException("no_user");
        }
        return null;
    }

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

        List<AdminVO> lists = adminDao.lists(map);
        long count = adminDao.count(map);

        return new Object[]{lists, count};
    }


    @Autowired
    @Qualifier("roleDao")
    private RoleDao roleDao;

    @Override
    public Object findById(Object object) throws ServiceException {
        AdminVO admin = adminDao.findById((String) object);
        if(admin != null){
            String permission = null;
            //查询权限
            if ("all".equals(admin.getPermissions())){
                permission = "all";
            }
            else {
                if (!StringUtil.isNullOrEmpty(admin.getPermissions())){
                    Role role = roleDao.find(admin.getPermissions());
                    permission = role.getPermissions();
                }
            }
            //查询角色
            Role role = null;
            if("all".equals(admin.getPermissions())){
                //是全部权限
            }else {
                role = roleDao.find(admin.getPermissions());
            }
            return new Object[]{admin, permission, role};
        }else {
            return null;
        }
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Admin admin = (Admin) object;
        admin.setId(UUIDFactory.random());//设置ID
        if(!StringUtil.isNullOrEmpty(admin.getPassword())){
            admin.setPassword(new MD5Util().getMD5ofStr(admin.getPassword()));//密码加密
        }
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
