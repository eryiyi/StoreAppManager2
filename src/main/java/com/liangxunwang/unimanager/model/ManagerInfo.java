package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/8/30.
 */
public class ManagerInfo {
    private String id;
    private String realName;
    private String idcard;
    private String idcardUrl;

    private String payNumber;
    private String checkName;

    private String bankCard;
    private String bankType;
    private String bankAddress;
    private String mobile;
    private String bankName;

    private String type_id;
    private String company_name;
    private String company_person;
    private String company_tel;
    private String company_address;
    private String company_detail;
    private String company_pic;
    private String company_star;

    private String lat_company;
    private String lng_company;

    private String emp_id;

    private String yingye_time_start;
    private String yingye_time_end;
    private String shouhui;

    private String dateline;
    private String is_check;
    private String lx_class_id;//分类
    private String company_yyzz;//营业执照
    private String is_card;//是否是定向卡商家 默认0否  1是

    public String getIs_card() {
        return is_card;
    }

    public void setIs_card(String is_card) {
        this.is_card = is_card;
    }

    public String getCompany_yyzz() {
        return company_yyzz;
    }

    public void setCompany_yyzz(String company_yyzz) {
        this.company_yyzz = company_yyzz;
    }

    public String getLx_class_id() {
        return lx_class_id;
    }

    public void setLx_class_id(String lx_class_id) {
        this.lx_class_id = lx_class_id;
    }

    public String getType_id() {
        return type_id;
    }

    public void setType_id(String type_id) {
        this.type_id = type_id;
    }

    public String getPayNumber() {
        return payNumber;
    }

    public void setPayNumber(String payNumber) {
        this.payNumber = payNumber;
    }

    public String getCheckName() {
        return checkName;
    }

    public void setCheckName(String checkName) {
        this.checkName = checkName;
    }

    public String getIs_check() {
        return is_check;
    }

    public void setIs_check(String is_check) {
        this.is_check = is_check;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }


    public String getRealName() {
        return realName;
    }

    public void setRealName(String realName) {
        this.realName = realName;
    }

    public String getIdcard() {
        return idcard;
    }

    public void setIdcard(String idcard) {
        this.idcard = idcard;
    }

    public String getIdcardUrl() {
        return idcardUrl;
    }

    public void setIdcardUrl(String idcardUrl) {
        this.idcardUrl = idcardUrl;
    }

    public String getBankCard() {
        return bankCard;
    }

    public void setBankCard(String bankCard) {
        this.bankCard = bankCard;
    }

    public String getBankType() {
        return bankType;
    }

    public void setBankType(String bankType) {
        this.bankType = bankType;
    }

    public String getBankAddress() {
        return bankAddress;
    }

    public void setBankAddress(String bankAddress) {
        this.bankAddress = bankAddress;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
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

    public String getCompany_address() {
        return company_address;
    }

    public void setCompany_address(String company_address) {
        this.company_address = company_address;
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

    public String getCompany_detail() {
        return company_detail;
    }

    public void setCompany_detail(String company_detail) {
        this.company_detail = company_detail;
    }

    public String getCompany_name() {
        return company_name;
    }

    public void setCompany_name(String company_name) {
        this.company_name = company_name;
    }

    public String getCompany_pic() {
        return company_pic;
    }

    public void setCompany_pic(String company_pic) {
        this.company_pic = company_pic;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getCompany_star() {
        return company_star;
    }

    public void setCompany_star(String company_star) {
        this.company_star = company_star;
    }

    public String getYingye_time_start() {
        return yingye_time_start;
    }

    public void setYingye_time_start(String yingye_time_start) {
        this.yingye_time_start = yingye_time_start;
    }

    public String getYingye_time_end() {
        return yingye_time_end;
    }

    public void setYingye_time_end(String yingye_time_end) {
        this.yingye_time_end = yingye_time_end;
    }

    public String getShouhui() {
        return shouhui;
    }

    public void setShouhui(String shouhui) {
        this.shouhui = shouhui;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
