package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PaihangObj;

/**
 * Created by Administrator on 2016/2/14.
 */
public class PaihangObjVO extends PaihangObj {
    private String goods_type_id;
    private String goods_name;
    private String goods_cover;
    private String goods_cont;
    private String sell_price;//销售价格
    private String market_price;//市场价格
    private String goods_address;
    private String goods_person;//联系人
    private String goods_tel;
    private String goods_qq;

    private String goods_is_use;
    private String goods_is_del;
    private String up_time;//上架时间
    private String goods_count;//商品数量
    private String goods_count_sale;//已卖商品数量
    private String goods_position;
    private String is_zhiying;//0 商家发布 1自营

    private String goods_emp_id;//商家商品发布者
    private String manager_id;//自营商品发布者

    public String getGoods_type_id() {
        return goods_type_id;
    }

    public void setGoods_type_id(String goods_type_id) {
        this.goods_type_id = goods_type_id;
    }

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

    public String getGoods_cont() {
        return goods_cont;
    }

    public void setGoods_cont(String goods_cont) {
        this.goods_cont = goods_cont;
    }

    public String getSell_price() {
        return sell_price;
    }

    public void setSell_price(String sell_price) {
        this.sell_price = sell_price;
    }

    public String getMarket_price() {
        return market_price;
    }

    public void setMarket_price(String market_price) {
        this.market_price = market_price;
    }

    public String getGoods_address() {
        return goods_address;
    }

    public void setGoods_address(String goods_address) {
        this.goods_address = goods_address;
    }

    public String getGoods_person() {
        return goods_person;
    }

    public void setGoods_person(String goods_person) {
        this.goods_person = goods_person;
    }

    public String getGoods_tel() {
        return goods_tel;
    }

    public void setGoods_tel(String goods_tel) {
        this.goods_tel = goods_tel;
    }

    public String getGoods_qq() {
        return goods_qq;
    }

    public void setGoods_qq(String goods_qq) {
        this.goods_qq = goods_qq;
    }

    public String getGoods_is_use() {
        return goods_is_use;
    }

    public void setGoods_is_use(String goods_is_use) {
        this.goods_is_use = goods_is_use;
    }

    public String getGoods_is_del() {
        return goods_is_del;
    }

    public void setGoods_is_del(String goods_is_del) {
        this.goods_is_del = goods_is_del;
    }

    public String getUp_time() {
        return up_time;
    }

    public void setUp_time(String up_time) {
        this.up_time = up_time;
    }

    public String getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(String goods_count) {
        this.goods_count = goods_count;
    }

    public String getGoods_count_sale() {
        return goods_count_sale;
    }

    public void setGoods_count_sale(String goods_count_sale) {
        this.goods_count_sale = goods_count_sale;
    }

    public String getGoods_position() {
        return goods_position;
    }

    public void setGoods_position(String goods_position) {
        this.goods_position = goods_position;
    }

    public String getIs_zhiying() {
        return is_zhiying;
    }

    public void setIs_zhiying(String is_zhiying) {
        this.is_zhiying = is_zhiying;
    }

    public String getGoods_emp_id() {
        return goods_emp_id;
    }

    public void setGoods_emp_id(String goods_emp_id) {
        this.goods_emp_id = goods_emp_id;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }
}
