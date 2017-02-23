package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.BrowsingObj;

/**
 * Created by zhl on 2016/9/24.
 */
public class BrowsingVo extends BrowsingObj {
    private String goods_name;
    private String goods_cover;

    public String getGoods_name() {
        return goods_name;
    }

    public void setGoods_name(String goods_name) {
        this.goods_name = goods_name;
    }

    public String getGoods_cover() {
        return goods_cover;
    }

    public void setGoods_cover(String goods_cover) {
        this.goods_cover = goods_cover;
    }
}
