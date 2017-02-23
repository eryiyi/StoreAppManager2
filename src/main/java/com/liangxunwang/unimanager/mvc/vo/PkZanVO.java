package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PKZan;

/**
 * Created by zhl on 15-4-5.
 */
public class PkZanVO extends PKZan {
    private String nickName;
    private String empCover;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }
}
