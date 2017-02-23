package com.liangxunwang.unimanager.model;

/**
 * Created by Administrator on 2015/8/14.
 * ������  ---��֧�������й�����
 */
public class ShoppingTrade {
    private String out_trade_no;
    private String trade_prices;
    private String pay_status;
    private String dateline;
    private String schoolId;

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }

    public String getTrade_prices() {
        return trade_prices;
    }

    public void setTrade_prices(String trade_prices) {
        this.trade_prices = trade_prices;
    }

    public String getPay_status() {
        return pay_status;
    }

    public void setPay_status(String pay_status) {
        this.pay_status = pay_status;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
