package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/2/3.
 * 评论
 */

public class Comment {
    private String id;
    private String recordId;
    private String fplid;
    private String empId;
    private String content;
    private String dateline;
    private String sendEmpId;
    private String fplempid;

    public String getFplempid() {
        return fplempid;
    }

    public void setFplempid(String fplempid) {
        this.fplempid = fplempid;
    }

    public String getSendEmpId() {
        return sendEmpId;
    }

    public void setSendEmpId(String sendEmpId) {
        this.sendEmpId = sendEmpId;
    }

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

    public String getFplid() {
        return fplid;
    }

    public void setFplid(String fplid) {
        this.fplid = fplid;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
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
