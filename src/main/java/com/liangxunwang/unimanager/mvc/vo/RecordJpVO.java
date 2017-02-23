package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.RecordJp;

/**
 * Created by zhl on 2015/2/1.
 */
public class RecordJpVO extends RecordJp {
    public String empName;
    public String empNameJp;
    public String empCover;
    public String empCoverJp;
    public String recordTitle;
    public String recordMoney;

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpNameJp() {
        return empNameJp;
    }

    public void setEmpNameJp(String empNameJp) {
        this.empNameJp = empNameJp;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getEmpCoverJp() {
        return empCoverJp;
    }

    public void setEmpCoverJp(String empCoverJp) {
        this.empCoverJp = empCoverJp;
    }

    public String getRecordTitle() {
        return recordTitle;
    }

    public void setRecordTitle(String recordTitle) {
        this.recordTitle = recordTitle;
    }

    public String getRecordMoney() {
        return recordMoney;
    }

    public void setRecordMoney(String recordMoney) {
        this.recordMoney = recordMoney;
    }
}
