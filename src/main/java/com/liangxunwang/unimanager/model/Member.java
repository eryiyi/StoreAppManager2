package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/1/29.
 */
public class Member {
    private String empId;
    private String emp_number;
    private String empMobile;
    private String empPass;
    private String empType;//0 普通会员  1商家
    private String empName;
    private String empCover;
    private String empSex;
    private String isUse;
    private String dateline;
    private String emp_birthday;
    private String pushId;
    private String hxUsername;
    private String isInGroup;
    private String deviceType;
    private String channelId;

    private String lat;
    private String lng;
    private String level_id;//等级
    private String emp_erweima;//二维码
    private String emp_up;//上级

    private String emp_up_mobile;
    private String emp_pay_pass;
    private String lx_attribute_id;//分销等级ID，返利用； 默认0是普通等级，1是普通返利会员  2是店长
    private String is_card_emp;//是否是定向卡会员  默认0否  1是
    private String package_money;//零钱
    private String jfcount;//积分
    private String lx_dxk_level_id;//定向卡等级ID--后台设置

    public String getLx_dxk_level_id() {
        return lx_dxk_level_id;
    }

    public void setLx_dxk_level_id(String lx_dxk_level_id) {
        this.lx_dxk_level_id = lx_dxk_level_id;
    }

    public String getJfcount() {
        return jfcount;
    }

    public void setJfcount(String jfcount) {
        this.jfcount = jfcount;
    }

    public String getPackage_money() {
        return package_money;
    }

    public void setPackage_money(String package_money) {
        this.package_money = package_money;
    }

    public String getIs_card_emp() {
        return is_card_emp;
    }

    public void setIs_card_emp(String is_card_emp) {
        this.is_card_emp = is_card_emp;
    }

    public String getLx_attribute_id() {
        return lx_attribute_id;
    }

    public void setLx_attribute_id(String lx_attribute_id) {
        this.lx_attribute_id = lx_attribute_id;
    }

    public String getChannelId() {
        return channelId;
    }

    public void setChannelId(String channelId) {
        this.channelId = channelId;
    }

    public String getEmp_pay_pass() {
        return emp_pay_pass;
    }

    public void setEmp_pay_pass(String emp_pay_pass) {
        this.emp_pay_pass = emp_pay_pass;
    }

    public String getEmpType() {
        return empType;
    }

    public void setEmpType(String empType) {
        this.empType = empType;
    }

    public String getEmp_number() {
        return emp_number;
    }

    public void setEmp_number(String emp_number) {
        this.emp_number = emp_number;
    }

    public String getEmp_up_mobile() {
        return emp_up_mobile;
    }

    public void setEmp_up_mobile(String emp_up_mobile) {
        this.emp_up_mobile = emp_up_mobile;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getEmpMobile() {
        return empMobile;
    }

    public void setEmpMobile(String empMobile) {
        this.empMobile = empMobile;
    }

    public String getEmpPass() {
        return empPass;
    }

    public void setEmpPass(String empPass) {
        this.empPass = empPass;
    }

    public String getEmpName() {
        return empName;
    }

    public void setEmpName(String empName) {
        this.empName = empName;
    }

    public String getEmpCover() {
        return empCover;
    }

    public void setEmpCover(String empCover) {
        this.empCover = empCover;
    }

    public String getEmpSex() {
        return empSex;
    }

    public void setEmpSex(String empSex) {
        this.empSex = empSex;
    }

    public String getIsUse() {
        return isUse;
    }

    public void setIsUse(String isUse) {
        this.isUse = isUse;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }

    public String getEmp_birthday() {
        return emp_birthday;
    }

    public void setEmp_birthday(String emp_birthday) {
        this.emp_birthday = emp_birthday;
    }

    public String getPushId() {
        return pushId;
    }

    public void setPushId(String pushId) {
        this.pushId = pushId;
    }

    public String getHxUsername() {
        return hxUsername;
    }

    public void setHxUsername(String hxUsername) {
        this.hxUsername = hxUsername;
    }

    public String getIsInGroup() {
        return isInGroup;
    }

    public void setIsInGroup(String isInGroup) {
        this.isInGroup = isInGroup;
    }

    public String getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(String deviceType) {
        this.deviceType = deviceType;
    }

    public String getLat() {
        return lat;
    }

    public void setLat(String lat) {
        this.lat = lat;
    }

    public String getLng() {
        return lng;
    }

    public void setLng(String lng) {
        this.lng = lng;
    }

    public String getLevel_id() {
        return level_id;
    }

    public void setLevel_id(String level_id) {
        this.level_id = level_id;
    }

    public String getEmp_erweima() {
        return emp_erweima;
    }

    public void setEmp_erweima(String emp_erweima) {
        this.emp_erweima = emp_erweima;
    }

    public String getEmp_up() {
        return emp_up;
    }

    public void setEmp_up(String emp_up) {
        this.emp_up = emp_up;
    }
}
