package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/4.
 * 会员禁用记录
 */
public class ManagerEmp {
    private String id;
    private String empId;
    private String admin;
    private String start;
    private String end;
    private String dateline;
    private String empIdTwo;
    private String countJF;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getAdmin() {
        return admin;
    }

    public void setAdmin(String admin) {
        this.admin = admin;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getEnd() {
        return end;
    }

    public void setEnd(String end) {
        this.end = end;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getEmpIdTwo() {
        return empIdTwo;
    }

    public void setEmpIdTwo(String empIdTwo) {
        this.empIdTwo = empIdTwo;
    }

    public String getCountJF() {
        return countJF;
    }

    public void setCountJF(String countJF) {
        this.countJF = countJF;
    }
}
