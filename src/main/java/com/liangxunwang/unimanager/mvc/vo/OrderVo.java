package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Order;

/**
 * Created by Administrator on 2015/8/15.
 */
public class OrderVo extends Order {
    private String empCover;//卖家头像
    private String empName;//卖家昵称
    private String empMobile;//卖家手机号
    private String goodsCover;//商品图片
    private String goodsTitle;//商品标题
    private String goodsPrice;//商品单价

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getGoodsCover() {
        return goodsCover;
    }

    public void setGoodsCover(String goodsCover) {
        this.goodsCover = goodsCover;
    }

    public String getGoodsTitle() {
        return goodsTitle;
    }

    public void setGoodsTitle(String goodsTitle) {
        this.goodsTitle = goodsTitle;
    }

    public String getGoodsPrice() {
        return goodsPrice;
    }

    public void setGoodsPrice(String goodsPrice) {
        this.goodsPrice = goodsPrice;
    }
}
