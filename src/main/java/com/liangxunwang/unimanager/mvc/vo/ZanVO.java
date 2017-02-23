package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Zan;

/**
 * Created by zhl on 2015/2/3.
 */
public class ZanVO extends Zan {
    private String nickName;
    private String cover;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }
}
