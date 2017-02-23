package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.College;

/**
 * Created by zhl on 2016/5/7.
 */
public class SchoolVO extends College {
    private String provinceName;

    public String getProvinceName() {
        return provinceName;
    }

    public void setProvinceName(String provinceName) {
        this.provinceName = provinceName;
    }
}
