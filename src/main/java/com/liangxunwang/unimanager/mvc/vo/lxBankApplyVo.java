package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.lxBankApply;

/**
 * Created by zhl on 2016/10/8.
 */
public class lxBankApplyVo extends lxBankApply {
    private String emp_number;
    private String emp_mobile;
    private String emp_name;
    private String emp_cover;

    private String bank_emp_name;
    private String bank_mobile;
    private String bank_kaihu_name;
    private String bank_name;
    private String bank_card;

    public String getBank_emp_name() {
        return bank_emp_name;
    }

    public void setBank_emp_name(String bank_emp_name) {
        this.bank_emp_name = bank_emp_name;
    }

    public String getBank_mobile() {
        return bank_mobile;
    }

    public void setBank_mobile(String bank_mobile) {
        this.bank_mobile = bank_mobile;
    }

    public String getBank_kaihu_name() {
        return bank_kaihu_name;
    }

    public void setBank_kaihu_name(String bank_kaihu_name) {
        this.bank_kaihu_name = bank_kaihu_name;
    }

    public String getBank_name() {
        return bank_name;
    }

    public void setBank_name(String bank_name) {
        this.bank_name = bank_name;
    }

    public String getBank_card() {
        return bank_card;
    }

    public void setBank_card(String bank_card) {
        this.bank_card = bank_card;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getEmp_mobile() {
        return emp_mobile;
    }

    public void setEmp_mobile(String emp_mobile) {
        this.emp_mobile = emp_mobile;
    }

    public String getEmp_name() {
        return emp_name;
    }

    public void setEmp_name(String emp_name) {
        this.emp_name = emp_name;
    }

    public String getEmp_cover() {
        return emp_cover;
    }

    public void setEmp_cover(String emp_cover) {
        this.emp_cover = emp_cover;
    }
}
