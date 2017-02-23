package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Videos;

/**
 * Created by zhl on 2015/2/2.
 */
public class VideosVO extends Videos {
    private String zanNum;//赞数量
    private String plNum;//评论数量

    public String getZanNum() {
        return zanNum;
    }

    public void setZanNum(String zanNum) {
        this.zanNum = zanNum;
    }

    public String getPlNum() {
        return plNum;
    }

    public void setPlNum(String plNum) {
        this.plNum = plNum;
    }
}
