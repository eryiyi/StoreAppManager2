package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2016/10/4.
 * 定向卡等级
 */
public class LxDxkLevel {
    private String lx_dxk_level_id;//等级Id
    private String lx_dxk_name;//等级名称 不可改
    private String lx_dxk_nick;
    private String lx_dxk_rate;//返利百分比

    public String getLx_dxk_level_id() {
        return lx_dxk_level_id;
    }

    public void setLx_dxk_level_id(String lx_dxk_level_id) {
        this.lx_dxk_level_id = lx_dxk_level_id;
    }

    public String getLx_dxk_name() {
        return lx_dxk_name;
    }

    public void setLx_dxk_name(String lx_dxk_name) {
        this.lx_dxk_name = lx_dxk_name;
    }

    public String getLx_dxk_nick() {
        return lx_dxk_nick;
    }

    public void setLx_dxk_nick(String lx_dxk_nick) {
        this.lx_dxk_nick = lx_dxk_nick;
    }

    public String getLx_dxk_rate() {
        return lx_dxk_rate;
    }

    public void setLx_dxk_rate(String lx_dxk_rate) {
        this.lx_dxk_rate = lx_dxk_rate;
    }
}
