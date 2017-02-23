package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PKWork;

/**
 * Created by zhl on 15-4-4.
 */
public class PkWorkVO extends PKWork {
    private String empName;
    private String empCover;
    private String zanNum;
    private String plNum;
    private String schoolName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getZanNum() {
        return zanNum;
    }

    public void setZanNum(String zanNum) {
        this.zanNum = zanNum;
    }

    public String getPlNum() {
        return plNum;
    }

    public void setPlNum(String plNum) {
        this.plNum = plNum;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
