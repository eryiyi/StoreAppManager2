package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Relate;

/**
 * Created by zhl on 2015/2/8.
 */
public class RelateVO extends Relate {
    private String empName;
    private String empCover;

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
}
