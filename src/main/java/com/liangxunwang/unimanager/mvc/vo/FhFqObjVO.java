package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.FhFqObj;

/**
 * Created by zhl on 2016/7/26.
 */
public class FhFqObjVO extends FhFqObj {
    private String emp_name;
    private String emp_cover;
    private String emp_mobile;
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_cover() {
        return emp_cover;
    }

    public void setEmp_cover(String emp_cover) {
        this.emp_cover = emp_cover;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }
}
