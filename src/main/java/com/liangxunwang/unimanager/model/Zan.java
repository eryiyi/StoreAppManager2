package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/3.
 */
public class Zan {
    private String id;
    private String recordId;
    private String empId;
    private String dateline;
    private String sendEmpId;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getRecordId() {
        return recordId;
    }

    public void setRecordId(String recordId) {
        this.recordId = recordId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getSendEmpId() {
        return sendEmpId;
    }

    public void setSendEmpId(String sendEmpId) {
        this.sendEmpId = sendEmpId;
    }
}
