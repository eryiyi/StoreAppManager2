package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/8/18.
 */
public class PaopaoGoodsQuery {
    private int index;
    private int size;
    private String cont;
    private String typeId;
    private String empId;
    private String type;//商品类别标志
    private String isMine;//是否查询我的

    private String manager_id;
    private String is_zhiying;//0否 1是
    private String lat_company;
    private String lng_company;
    private String is_time;//如果是1说明是最新排序
    private String is_count;//如果是1说明是按照销量排序
    private String isUse;
    private String is_dxk;
    private String goods_count;
    private String goods_position;
    private String is_admin;//0是管理员登录  1的话是商家登录
    private String dianpu_number;//

    public String getDianpu_number() {
        return dianpu_number;
    }

    public void setDianpu_number(String dianpu_number) {
        this.dianpu_number = dianpu_number;
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

    public String getIs_admin() {
        return is_admin;
    }

    public void setIs_admin(String is_admin) {
        this.is_admin = is_admin;
    }

    public String getGoods_count() {
        return goods_count;
    }

    public void setGoods_count(String goods_count) {
        this.goods_count = goods_count;
    }

    public String getIs_dxk() {
        return is_dxk;
    }

    public void setIs_dxk(String is_dxk) {
        this.is_dxk = is_dxk;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getIs_count() {
        return is_count;
    }

    public void setIs_count(String is_count) {
        this.is_count = is_count;
    }

    public String getIs_time() {
        return is_time;
    }

    public void setIs_time(String is_time) {
        this.is_time = is_time;
    }

    public String getLat_company() {
        return lat_company;
    }

    public void setLat_company(String lat_company) {
        this.lat_company = lat_company;
    }

    public String getLng_company() {
        return lng_company;
    }

    public void setLng_company(String lng_company) {
        this.lng_company = lng_company;
    }

    public String getManager_id() {
        return manager_id;
    }

    public void setManager_id(String manager_id) {
        this.manager_id = manager_id;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
    }

    public String getTypeId() {
        return typeId;
    }

    public void setTypeId(String typeId) {
        this.typeId = typeId;
    }
    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsMine() {
        return isMine;
    }

    public void setIsMine(String isMine) {
        this.isMine = isMine;
    }
}
