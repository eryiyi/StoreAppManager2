package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/3.
 *
 * 举报表
 */
public class Report {
    private String id;
    private String empOne;
    private String empTwo;
    private String typeId;
    private String cont;
    private String xxid;//被举报信息ID
    private String isDel;//
    private String dateline;
    private String schoolId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getEmpOne() {
        return empOne;
    }

    public void setEmpOne(String empOne) {
        this.empOne = empOne;
    }

    public String getEmpTwo() {
        return empTwo;
    }

    public void setEmpTwo(String empTwo) {
        this.empTwo = empTwo;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getXxid() {
        return xxid;
    }

    public void setXxid(String xxid) {
        this.xxid = xxid;
    }

    public String getIsDel() {
        return isDel;
    }

    public void setIsDel(String isDel) {
        this.isDel = isDel;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }
}
