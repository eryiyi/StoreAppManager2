package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Goods;

/**
 * Created by zhl on 2015/2/6.
 */
public class GoodsVO extends Goods {
    private String nickName;
    private String empCover;
    private String mobile;
    private String schoolName;

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }

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

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
