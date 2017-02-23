package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/8/30.
 * 结算时的商家列表
 */
public class SettlementSellers {
    private String empName;//商家名称
    private String empMobile;//商家电话
    private String empId;//商家会员ID
    private String contractId;//商家所属承包商的会员ID
    private float totalMoney;//总收入金额，某一天要结算的金额
    private String isAccount;//是否结算  0 是未结算  大于0就是结算

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getContractId() {
        return contractId;
    }

    public void setContractId(String contractId) {
        this.contractId = contractId;
    }

    public float getTotalMoney() {
        return totalMoney;
    }

    public void setTotalMoney(float totalMoney) {
        this.totalMoney = totalMoney;
    }

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }
}
