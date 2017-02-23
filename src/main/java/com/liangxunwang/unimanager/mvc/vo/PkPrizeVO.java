package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PkPrize;

/**
 * Created by zhl on 2015/4/11.
 */
public class PkPrizeVO extends PkPrize {
    private String themeNumber;//�����ڴ�
    private String schoolName;//ѧУ����

    public String getThemeNumber() {
        return themeNumber;
    }

    public void setThemeNumber(String themeNumber) {
        this.themeNumber = themeNumber;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}

