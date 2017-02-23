package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/8/24.
 * 结算相关
 */
public class Settlement {
    private String date;//日期
    private float rate;//结算费率
    private float income;//总的收入
    private String isAccount;//是否结算

    public String getIsAccount() {
        return isAccount;
    }

    public void setIsAccount(String isAccount) {
        this.isAccount = isAccount;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public float getRate() {
        return rate;
    }

    public void setRate(float rate) {
        this.rate = rate;
    }

    public float getIncome() {
        return income;
    }

    public void setIncome(float income) {
        this.income = income;
    }
}
