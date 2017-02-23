package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/6/12.
 */
public class RecordJp {
    private String school_record_jp_id;
    private String record_id;
    private String record_emp_id;
    private String emp_id;
    private String money;
    private String moneyY;//原始的价格
    private String dateline;

    public String getMoneyY() {
        return moneyY;
    }

    public void setMoneyY(String moneyY) {
        this.moneyY = moneyY;
    }

    public String getSchool_record_jp_id() {
        return school_record_jp_id;
    }

    public void setSchool_record_jp_id(String school_record_jp_id) {
        this.school_record_jp_id = school_record_jp_id;
    }

    public String getRecord_id() {
        return record_id;
    }

    public void setRecord_id(String record_id) {
        this.record_id = record_id;
    }

    public String getRecord_emp_id() {
        return record_emp_id;
    }

    public void setRecord_emp_id(String record_emp_id) {
        this.record_emp_id = record_emp_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getMoney() {
        return money;
    }

    public void setMoney(String money) {
        this.money = money;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
