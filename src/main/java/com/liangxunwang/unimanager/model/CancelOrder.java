package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2015/8/18.
 */
public class CancelOrder {
    private String order_cancel_no;
    private String order_no;
    private String emp_id;
    private String seller_emp_id;
    private String cont;
    private String dateline;

    public String getOrder_cancel_no() {
        return order_cancel_no;
    }

    public void setOrder_cancel_no(String order_cancel_no) {
        this.order_cancel_no = order_cancel_no;
    }

    public String getOrder_no() {
        return order_no;
    }

    public void setOrder_no(String order_no) {
        this.order_no = order_no;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getSeller_emp_id() {
        return seller_emp_id;
    }

    public void setSeller_emp_id(String seller_emp_id) {
        this.seller_emp_id = seller_emp_id;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
