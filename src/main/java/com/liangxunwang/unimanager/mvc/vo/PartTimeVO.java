package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PartTime;

/**
 * Created by zhl on 2015/2/7.
 */
public class PartTimeVO extends PartTime {
    private String empName;
    private String empCover;
    private String typeName;
    private String typeCover;
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getTypeCover() {
        return typeCover;
    }

    public void setTypeCover(String typeCover) {
        this.typeCover = typeCover;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }
}
