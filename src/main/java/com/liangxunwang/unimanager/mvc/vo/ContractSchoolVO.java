package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.ContractSchool;

/**
 * Created by zhl on 2015/3/24.
 */
public class ContractSchoolVO extends ContractSchool {
    private String empName;
    private String schoolName;
    private String empCover;
    private String empMobile;

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
