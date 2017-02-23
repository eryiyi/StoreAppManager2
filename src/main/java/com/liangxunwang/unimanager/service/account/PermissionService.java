package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.model.Permission;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.springframework.stereotype.Service;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 2015/8/6.
 */
@Service("permissionService")
public class PermissionService implements ListService {

    @Override
    public Object list(Object object) throws ServiceException {
        String filePath = (String) object;
        File file = new File(filePath);
        try{
            SAXReader reader = new SAXReader();
            Document document = reader.read(file);
            Element root = document.getRootElement();
            List<Element> childElements = root.elements("group");
            List<Permission> allPermissions = new ArrayList<Permission>();
            for(Element child : childElements){
                allPermissions.add(convertFromGroup(child));
            }
            return allPermissions;
        }catch (Exception e){
            return null;
        }
    }

    private Permission convertFromGroup(Element e) {
        Permission permission = convertPermission(e);
        List<Element> children = e.elements("permission");
        List<Permission> permissionList = new ArrayList<Permission>();
        for(Element c : children) {
            permissionList.add(convertPermission(c));
        }
        permission.setChild(permissionList);
        return permission;
    }

    private Permission convertPermission(Element e){
        String id = e.attributeValue("id");
        String name = e.attributeValue("name");
        Permission permission = new Permission();
        permission.setId(id);
        permission.setName(name);
        return permission;
    }
}
