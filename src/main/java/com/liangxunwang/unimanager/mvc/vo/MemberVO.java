package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Member;

/**
 * Created by zhl on 2015/1/31.
 */
public class MemberVO  extends Member {
    private String levelName;
    private String level_zhe;

    private String emp_mobile_up;
    private String emp_name_up;

    private String lx_dxk_name;//定向卡等级
    private String depth;//深度

    public String getDepth() {
        return depth;
    }

    public void setDepth(String depth) {
        this.depth = depth;
    }

    public String getLevel_zhe() {
        return level_zhe;
    }

    public void setLevel_zhe(String level_zhe) {
        this.level_zhe = level_zhe;
    }

    public String getLx_dxk_name() {
        return lx_dxk_name;
    }

    public void setLx_dxk_name(String lx_dxk_name) {
        this.lx_dxk_name = lx_dxk_name;
    }


    public String getEmp_mobile_up() {
        return emp_mobile_up;
    }

    public void setEmp_mobile_up(String emp_mobile_up) {
        this.emp_mobile_up = emp_mobile_up;
    }

    public String getEmp_name_up() {
        return emp_name_up;
    }

    public void setEmp_name_up(String emp_name_up) {
        this.emp_name_up = emp_name_up;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }
}
