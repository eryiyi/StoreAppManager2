package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class NearbyDianpuQuery {
    private int index;
    private int size;

    private String cont;
    private String lat_company;
    private String lng_company;
    private String lx_class_id;
    private String is_dxk;
    private String is_time;
    private String is_count;

    public String getIs_time() {
        return is_time;
    }

    public void setIs_time(String is_time) {
        this.is_time = is_time;
    }

    public String getIs_count() {
        return is_count;
    }

    public void setIs_count(String is_count) {
        this.is_count = is_count;
    }

    public String getIs_dxk() {
        return is_dxk;
    }

    public void setIs_dxk(String is_dxk) {
        this.is_dxk = is_dxk;
    }

    public String getLx_class_id() {
        return lx_class_id;
    }

    public void setLx_class_id(String lx_class_id) {
        this.lx_class_id = lx_class_id;
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

    public String getCont() {
        return cont;
    }

    public void setCont(String cont) {
        this.cont = cont;
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
}


