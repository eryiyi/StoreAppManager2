package com.liangxunwang.unimanager.query;

/**
 * 订单查询列表
 */
public class OrdersQuery {
    private int index;
    private int size;

    private String empId;
    private String status;
    private String empName;//买家昵称
    private String empPhone;//买家电话
    private String orderStatus;//订单状态
    private String payStatus;//付款状态
    private String distribStatus;//配送状态
    private String is_dxk_order;//是否0元订单
    private String is_zhiying;//是否直营  0否 1是
    private String trade_type;

    public String getTrade_type() {
        return trade_type;
    }

    public void setTrade_type(String trade_type) {
        this.trade_type = trade_type;
    }

    public String getIs_zhiying() {
        return is_zhiying;
    }

    public void setIs_zhiying(String is_zhiying) {
        this.is_zhiying = is_zhiying;
    }

    public String getIs_dxk_order() {
        return is_dxk_order;
    }

    public void setIs_dxk_order(String is_dxk_order) {
        this.is_dxk_order = is_dxk_order;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpPhone() {
        return empPhone;
    }

    public void setEmpPhone(String empPhone) {
        this.empPhone = empPhone;
    }

    public String getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(String orderStatus) {
        this.orderStatus = orderStatus;
    }

    public String getPayStatus() {
        return payStatus;
    }

    public void setPayStatus(String payStatus) {
        this.payStatus = payStatus;
    }

    public String getDistribStatus() {
        return distribStatus;
    }

    public void setDistribStatus(String distribStatus) {
        this.distribStatus = distribStatus;
    }

    private String emptype;

    public String getEmptype() {
        return emptype;
    }

    public void setEmptype(String emptype) {
        this.emptype = emptype;
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

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
