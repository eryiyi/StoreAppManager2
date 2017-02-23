package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Notice;

/**
 * Created by zhl on 2015/2/5.
 */
public class NoticeVO extends Notice {
    private String empName;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }
}
