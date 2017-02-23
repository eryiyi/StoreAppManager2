package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/3/24.
 * 商家承包学校表
 */
public class ContractSchool {
    private String id;
    private String empId;
    private String schoolId;
    private String endTime;//到期时间
    private String dateline;//保存时间

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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
