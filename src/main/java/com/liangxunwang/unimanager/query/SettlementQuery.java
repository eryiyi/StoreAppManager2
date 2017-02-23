package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/8/24.
 */
public class SettlementQuery {
    private int index;
    private int size;

    private String date;//要查询的日期
    private String empId;//商家的ID
    private String isAccount;//是否结算

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
