package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.Record;

/**
 * Created by zhl on 2015/2/2.
 */
public class RecordVO extends Record {
    private String empName;//会员昵称
    private String empCover;//会员头像
    private String levelName;//会员等级
    private String zanNum;//赞数量
    private String plNum;//评论数量
    private int levelCount;//积分
    private String schoolName;//圈子名称
    private String school_record_mood_name;

    public String getSchool_record_mood_name() {
        return school_record_mood_name;
    }

    public void setSchool_record_mood_name(String school_record_mood_name) {
        this.school_record_mood_name = school_record_mood_name;
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

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getZanNum() {
        return zanNum;
    }

    public void setZanNum(String zanNum) {
        this.zanNum = zanNum;
    }

    public String getPlNum() {
        return plNum;
    }

    public void setPlNum(String plNum) {
        this.plNum = plNum;
    }

    public int getLevelCount() {
        return levelCount;
    }

    public void setLevelCount(int levelCount) {
        this.levelCount = levelCount;
    }

    public String getSchoolName() {
        return schoolName;
    }

    public void setSchoolName(String schoolName) {
        this.schoolName = schoolName;
    }
}
