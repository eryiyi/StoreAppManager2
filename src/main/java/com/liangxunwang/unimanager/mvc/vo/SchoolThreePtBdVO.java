package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.SchoolThreeTingtaiBd;

/**
 * Created by zhl on 2016/5/23.
 */
public class SchoolThreePtBdVO extends SchoolThreeTingtaiBd {
    private String school_three_pingtai_name;
    private String school_three_pingtai_pic;

    public String getSchool_three_pingtai_name() {
        return school_three_pingtai_name;
    }

    public void setSchool_three_pingtai_name(String school_three_pingtai_name) {
        this.school_three_pingtai_name = school_three_pingtai_name;
    }

    public String getSchool_three_pingtai_pic() {
        return school_three_pingtai_pic;
    }

    public void setSchool_three_pingtai_pic(String school_three_pingtai_pic) {
        this.school_three_pingtai_pic = school_three_pingtai_pic;
    }
}
