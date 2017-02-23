package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.SellerGoods;

/**
 * Created by zhl on 2015/3/27.
 */
public class SellerGoodsVO extends SellerGoods {
    private String empName;//商家名称
    private String empCover;//商家头像
    private String schoolName;//圈子名称
    private String mobile;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
