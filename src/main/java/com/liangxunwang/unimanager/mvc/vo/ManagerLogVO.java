package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.ManagerLog;

/**
 * Created by zhl on 2015/3/3.
 */
public class ManagerLogVO extends ManagerLog {
    private String empName;
    private String mobile;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
