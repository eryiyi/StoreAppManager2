package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/10/8.
 * 提现
 */
public class lxBankApply {
    private String lx_bank_apply_id;//提现ID
    private String lx_bank_apply_count;//提现金额
    private String emp_id;//会员DI
    private String bank_id;//银行卡ID
    private String dateline_apply;//申请时间
    private String dateline_done;//处理时间
    private String is_check;//是否处理  0 没处理 1以处理

    public String getLx_bank_apply_id() {
        return lx_bank_apply_id;
    }

    public void setLx_bank_apply_id(String lx_bank_apply_id) {
        this.lx_bank_apply_id = lx_bank_apply_id;
    }

    public String getLx_bank_apply_count() {
        return lx_bank_apply_count;
    }

    public void setLx_bank_apply_count(String lx_bank_apply_count) {
        this.lx_bank_apply_count = lx_bank_apply_count;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getBank_id() {
        return bank_id;
    }

    public void setBank_id(String bank_id) {
        this.bank_id = bank_id;
    }

    public String getDateline_apply() {
        return dateline_apply;
    }

    public void setDateline_apply(String dateline_apply) {
        this.dateline_apply = dateline_apply;
    }

    public String getDateline_done() {
        return dateline_done;
    }

    public void setDateline_done(String dateline_done) {
        this.dateline_done = dateline_done;
    }

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }
}
