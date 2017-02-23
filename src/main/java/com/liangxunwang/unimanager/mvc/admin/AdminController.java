package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.model.Role;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.query.AdminQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpSession;
import java.util.List;

/**
 */
@Controller
public class AdminController extends ControllerConstants {

    @Autowired
    @Qualifier("adminService")
    private UpdateService adminUpdateService;

    @Autowired
    @Qualifier("adminService")
    private ListService adminUpdateServiceList;

    @Autowired
    @Qualifier("adminService")
    private ExecuteService adminExecuteService;

    @Autowired
    @Qualifier("adminRolesService")
    private ExecuteService adminRolesServiceExe;

    @Autowired
    @Qualifier("adminService")
    private FindService adminServiceFind;


    @Autowired
    @Qualifier("adminListSjService")
    private ListService adminListSjServiceList;

    @Autowired
    @Qualifier("adminListDlService")
    private ListService adminListDlServiceList;


    @Autowired
    @Qualifier("adminService")
    private SaveService adminSave;

    @Autowired
    @Qualifier("adminListDlService")
    private SaveService adminListDlServiceSave;


    @RequestMapping("/updateRole")
    @ResponseBody
    public String updateRole(String empId, String roleId){
        if (StringUtil.isNullOrEmpty(empId) || StringUtil.isNullOrEmpty(roleId)){
            return toJSONString(ERROR_1);
        }
        Object[] params = new Object[]{empId, roleId};
        adminUpdateService.update(params);
        return toJSONString(SUCCESS);
    }


    @RequestMapping("/changePass")
    @ResponseBody
    public String changePass(HttpSession session,String ePass, String pass){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            try {
                Object[] params = new Object[]{pass, ePass, manager.getId()};
                adminExecuteService.execute(params);
                return toJSONString(SUCCESS);
            }catch (Exception e){
                String msg = e.getMessage();
                if (msg.equals("no_user")){
                    return toJSONString(ERROR_2);//用户不存在
                }
                if (msg.equals("pass_error")){
                    return toJSONString(ERROR_3);//原先密码不正确
                }
            }
        }
        return toJSONString(ERROR_1);
    }

    //赋权限
    @RequestMapping("/changroles")
    @ResponseBody
    public String changroles(String permissions, String id){
        try {
            Object[] params = new Object[]{id, permissions};
            adminRolesServiceExe.execute(params);
            return toJSONString(SUCCESS);
        }catch (Exception e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/toChangePass")
    public String toChanagePass(){
        return "/admin/pass";
    }

    @RequestMapping("/admin/list")
    public String list(HttpSession session,ModelMap map, AdminQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) adminUpdateServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/admin/list";
    }

    @RequestMapping("/admin/listSj")
    public String listSj(HttpSession session,ModelMap map, AdminQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) adminListSjServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/admin/list_sj";
    }


    @RequestMapping("/admin/listDl")
    public String listDl(HttpSession session,ModelMap map, AdminQuery query, Page page){
        query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
        query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
        Object[] results = (Object[]) adminListDlServiceList.list(query);
        map.put("list", results[0]);
        long count = (Long) results[1];
        page.setCount(count);
        page.setPageCount(calculatePageCount(query.getSize(), count));
        map.addAttribute("page", page);
        map.addAttribute("query", query);
        return "/admin/list_dl";
    }


    @RequestMapping("/admin/detail")
    public String adminDetail(ModelMap map, String id) throws Exception {
        Object[] results = (Object[]) adminServiceFind.findById(id);
        if(results != null){
            AdminVO admin = (AdminVO) results[0];
            String permissions = (String) results[1];
            Role role  = (Role) results[2];
            map.put("admin", admin);
            if(role != null){
                map.put("role", role);
            }else {
                map.put("roleRname", "最高管理员");
            }
            map.put("permissions_admin", permissions);
        }
        return "/admin/detail";
    }

    @Autowired
    @Qualifier("roleService")
    private ListService roleService;

    @RequestMapping("/admin/role")
    public String adminRole(ModelMap map, String id) throws Exception {
        Object[] results = (Object[]) adminServiceFind.findById(id);
        if(results != null){
            AdminVO admin = (AdminVO) results[0];
            String permissions = (String) results[1];
            Role role  = (Role) results[2];
            map.put("admin", admin);
            if(role != null){
                map.put("role", role);
            }else {
                map.put("roleRname", "最高管理员");
            }
            map.put("permissions_admin", permissions);
        }
        //角色
        List<Role> roles = (List<Role>) roleService.list("");
        map.put("roles", roles);
        return "/admin/adminRole";
    }

    @RequestMapping("/admin/updateType")
    @ResponseBody
    public String updateType(HttpSession session, String id, String is_use ){
        try {
            Object[] params = new Object[]{id, is_use};
            //is_use :0 1 是启动和禁止管理员 2是删除管理员
            //更改管理员状态
            adminUpdateService.update(params);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }


    @RequestMapping("/toAddManager")
    public String toAddManager(HttpSession session){
        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        if(manager != null){
            if(manager.getIs_pingtai().equals("1")){
                return "/admin/addManager";
            }
        }
        return null;
    }


    @RequestMapping("/addManager")
    @ResponseBody
    public String addManager(HttpSession session, Admin admin){
        if(StringUtil.isNullOrEmpty(admin.getUsername())){
            return toJSONString(ERROR_1);
        }
        if(StringUtil.isNullOrEmpty(admin.getPassword())){
            return toJSONString(ERROR_2);
        }
        if(StringUtil.isNullOrEmpty(admin.getManager_cover())){
            return toJSONString(ERROR_3);
        }
        try {
            adminSave.save(admin);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("has_exist")){
                return toJSONString(ERROR_5);
            }else{
                return toJSONString(ERROR_4);
            }
        }
    }


    @RequestMapping("/admin/adminSaveDaili")
    @ResponseBody
    public String adminSaveDaili(HttpSession session, Admin admin){
        if(StringUtil.isNullOrEmpty(admin.getUsername())){
            return toJSONString(ERROR_1);
        }
        if(StringUtil.isNullOrEmpty(admin.getPassword())){
            return toJSONString(ERROR_2);
        }
        if(StringUtil.isNullOrEmpty(admin.getManager_cover())){
            return toJSONString(ERROR_3);
        }
        try {
            adminListDlServiceSave.save(admin);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            String msg = e.getMessage();
            if (msg.equals("has_exist")){
                return toJSONString(ERROR_5);
            }else{
                return toJSONString(ERROR_4);
            }
        }
    }

    @RequestMapping("/admin/toLogin")
    public String toLogin(HttpSession session){
//        Admin manager = (Admin) session.getAttribute(ACCOUNT_KEY);
        session.setAttribute(ACCOUNT_KEY , null);
        return "/adminLogin";
    }

}
