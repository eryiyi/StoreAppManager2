package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/10/5.
 */
public class CountRecord {
    private String lx_count_record_id;
    private String lx_count_record_cont;
    private String lx_count_record_count;
    private String dateline;
    private String emp_id;

    private String emp_mobile;
    private String emp_number;
    private String emp_name;

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getLx_count_record_id() {
        return lx_count_record_id;
    }

    public void setLx_count_record_id(String lx_count_record_id) {
        this.lx_count_record_id = lx_count_record_id;
    }

    public String getLx_count_record_cont() {
        return lx_count_record_cont;
    }

    public void setLx_count_record_cont(String lx_count_record_cont) {
        this.lx_count_record_cont = lx_count_record_cont;
    }

    public String getLx_count_record_count() {
        return lx_count_record_count;
    }

    public void setLx_count_record_count(String lx_count_record_count) {
        this.lx_count_record_count = lx_count_record_count;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }
}
