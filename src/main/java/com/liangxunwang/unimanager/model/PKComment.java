package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 15-4-3.
 * PKComment
 */
public class PKComment {
    private String id;
    private String zpId;
    private String empId;
    private String commentCont;
    private String fPlid;
    private String dateline;
    private String fplempid;
    private String sendEmpId;

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

    public String getZpId() {
        return zpId;
    }

    public void setZpId(String zpId) {
        this.zpId = zpId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getCommentCont() {
        return commentCont;
    }

    public void setCommentCont(String commentCont) {
        this.commentCont = commentCont;
    }

    public String getfPlid() {
        return fPlid;
    }

    public void setfPlid(String fPlid) {
        this.fPlid = fPlid;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
