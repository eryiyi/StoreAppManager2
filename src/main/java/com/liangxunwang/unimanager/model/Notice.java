package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/5.
 * 公告表
 */
public class Notice {
    private String id;
    private String title;
    private String content;
    private String dateline;
    private String manager_id;
    private String manager_admin;
    private String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getManager_admin() {
        return manager_admin;
    }

    public void setManager_admin(String manager_admin) {
        this.manager_admin = manager_admin;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
