package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/8/6.
 * ��ɫ���ƣ���ɾ�Ĳ�
 */
@Controller
@RequestMapping("/role")
public class RoleController extends ControllerConstants{

    @Autowired
    @Qualifier("roleService")
    private SaveService roleSaveService;
    @Autowired
    @Qualifier("roleService")
    private ListService roleListService;
    @Autowired
    @Qualifier("roleService")
    private DeleteService roleDeleteService;

    @Autowired
    @Qualifier("roleService")
    private FindService roleFindService;

    @Autowired
    @Qualifier("roleService")
    private UpdateService roleUpdateService;

    @RequestMapping("add")
    public String add(){
        return "/role/add";
    }

    @RequestMapping("list")
    public String list(ModelMap map){
        List<Role> list = (List<Role>) roleListService.list(null);
        map.put("list", list);
        return "/role/list";
    }

    @RequestMapping("save")
    @ResponseBody
    public String save(Role role){
        roleSaveService.save(role);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("delete")
    @ResponseBody
    public String delete(String id){
        roleDeleteService.delete(id);
        return toJSONString(SUCCESS);
    }

    @RequestMapping("edit")
    public String toEdit(@RequestParam String id, ModelMap map){
        Role role = (Role) roleFindService.findById(id);
        map.put("role", role);
        return "/role/edit";
    }

    /**
     * 修改角色权限
     * @param role
     * @return
     */
    @RequestMapping("update")
    @ResponseBody
    public String update(Role role){
        if (StringUtil.isNullOrEmpty(role.getPermissions()) || StringUtil.isNullOrEmpty(role.getName())){
            return toJSONString(ERROR_1);
        }
        roleUpdateService.update(role);
        return toJSONString(SUCCESS);
    }
}
