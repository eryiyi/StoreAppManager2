package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/8/6.
 * ��ɫ
 */
public class Role {
    private String id;
    private String name;
    private String permissions;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPermissions() {
        return permissions;
    }

    public void setPermissions(String permissions) {
        this.permissions = permissions;
    }
}
