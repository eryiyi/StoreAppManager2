package com.liangxunwang.unimanager.model;

/**
 * Created by zhl on 2015/4/9.
 * �ھ���
 */
public class Champion {
    private String id;
    private String themeNumber;//�ڴ�
    private String themeId;//����ID
    private String empId;//��ԱID
    private String schoolId;//ѧУID
    private String zpId;//��ƷID
    private String type;//���  0 ȫ��  1ѧУ
    private String dateline;
    private String isPic;//�Ƿ�ͼ  0δ��  1����
    private String uploadPic;//�ϴ���ͼƬ
    private String isSure;//�Ƿ�ȷ���յ���Ʒ 0  δ�콱  1���콱

    public String getIsPic() {
        return isPic;
    }

    public void setIsPic(String isPic) {
        this.isPic = isPic;
    }

    public String getUploadPic() {
        return uploadPic;
    }

    public void setUploadPic(String uploadPic) {
        this.uploadPic = uploadPic;
    }

    public String getIsSure() {
        return isSure;
    }

    public void setIsSure(String isSure) {
        this.isSure = isSure;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getThemeNumber() {
        return themeNumber;
    }

    public void setThemeNumber(String themeNumber) {
        this.themeNumber = themeNumber;
    }

    public String getThemeId() {
        return themeId;
    }

    public void setThemeId(String themeId) {
        this.themeId = themeId;
    }

    public String getEmpId() {
        return empId;
    }

    public void setEmpId(String empId) {
        this.empId = empId;
    }

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getZpId() {
        return zpId;
    }

    public void setZpId(String zpId) {
        this.zpId = zpId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDateline() {
        return dateline;
    }

    public void setDateline(String dateline) {
        this.dateline = dateline;
    }
}
