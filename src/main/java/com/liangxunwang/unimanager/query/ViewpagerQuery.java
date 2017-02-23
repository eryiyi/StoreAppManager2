package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/8/26.
 */
public class ViewpagerQuery {
    private String schoolId;
    private String type;//登录者的类型  1管理员  2承包商  3 商家
    private String empId;//后台登陆者的会员ID

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
