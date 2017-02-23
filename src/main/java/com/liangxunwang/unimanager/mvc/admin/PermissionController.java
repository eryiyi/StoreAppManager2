package com.liangxunwang.unimanager.mvc.admin;

import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by zhl on 2015/8/6.
 */
@Controller
@RequestMapping("/permission")
public class PermissionController extends ControllerConstants {

    @Autowired
    @Qualifier("permissionService")
    private ListService permissionListService;

    @RequestMapping(value = "list", produces = "text/html;charset=UTF-8")
    @ResponseBody
    public String list(HttpServletRequest request){
        String path = request.getSession().getServletContext().getRealPath("/WEB-INF/permissions.xml");
        List list = (List) permissionListService.list(path);
        return toJSONString(list);
    }
}
