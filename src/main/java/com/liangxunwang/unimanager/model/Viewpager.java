package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/8/26.
 * 商城轮播对象
 */
public class Viewpager {
    private String id;
    private String picAddress;//图片地址
    private String picUrl;//图片链接
    private String desc;

    private String schoolId;//哪个学校的
    private String goodsId;//产品的ID
    private String type;//1 PK  2 商品详情  3我们的广告

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getDesc() {
        return desc;
    }

    public void setDesc(String desc) {
        this.desc = desc;
    }
}
