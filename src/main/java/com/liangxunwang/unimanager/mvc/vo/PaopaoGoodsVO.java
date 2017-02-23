package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PaopaoGoods;

/**
 * Created by zhl on 2015/8/18.
 */
public class PaopaoGoodsVO extends PaopaoGoods {
    private String nickName;
    private String empCover;

    private String managerName;
    private String managerCover;
    private String type_name;

    private String is_tuijian;
    private String mm_paihang_id;

    private String lx_class_name;

    public String getLx_class_name() {
        return lx_class_name;
    }

    public void setLx_class_name(String lx_class_name) {
        this.lx_class_name = lx_class_name;
    }

    public String getIs_tuijian() {
        return is_tuijian;
    }

    public void setIs_tuijian(String is_tuijian) {
        this.is_tuijian = is_tuijian;
    }

    public String getMm_paihang_id() {
        return mm_paihang_id;
    }

    public void setMm_paihang_id(String mm_paihang_id) {
        this.mm_paihang_id = mm_paihang_id;
    }

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }

    public String getManagerName() {
        return managerName;
    }

    public void setManagerName(String managerName) {
        this.managerName = managerName;
    }

    public String getManagerCover() {
        return managerCover;
    }

    public void setManagerCover(String managerCover) {
        this.managerCover = managerCover;
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


}
