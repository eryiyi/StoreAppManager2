package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PaihangDianpu;

/**
 * Created by zhl on 2016/10/30.
 */
public class PaihangDianpuVo extends PaihangDianpu {
    private String company_name;
    private String company_person;
    private String company_tel;
    private String company_address;
    private String company_detail;
    private String company_pic;
    private String company_star;

    private String lat_company;
    private String lng_company;
    private String type_name;

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_person() {
        return company_person;
    }

    public void setCompany_person(String company_person) {
        this.company_person = company_person;
    }

    public String getCompany_tel() {
        return company_tel;
    }

    public void setCompany_tel(String company_tel) {
        this.company_tel = company_tel;
    }

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
    }

    public String getCompany_detail() {
        return company_detail;
    }

    public void setCompany_detail(String company_detail) {
        this.company_detail = company_detail;
    }

    public String getCompany_pic() {
        return company_pic;
    }

    public void setCompany_pic(String company_pic) {
        this.company_pic = company_pic;
    }

    public String getCompany_star() {
        return company_star;
    }

    public void setCompany_star(String company_star) {
        this.company_star = company_star;
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

    public String getType_name() {
        return type_name;
    }

    public void setType_name(String type_name) {
        this.type_name = type_name;
    }
}
