package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Report;

/**
 * Created by zhl on 2015/2/4.
 */
public class ReportVO extends Report {
    private String empOneNickName;//举报者昵称
    private String empTwoNickName;//被举报者昵称

    public String getEmpOneNickName() {
        return empOneNickName;
    }

    public void setEmpOneNickName(String empOneNickName) {
        this.empOneNickName = empOneNickName;
    }

    public String getEmpTwoNickName() {
        return empTwoNickName;
    }

    public void setEmpTwoNickName(String empTwoNickName) {
        this.empTwoNickName = empTwoNickName;
    }
}
