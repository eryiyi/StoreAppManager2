package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/2/2.
 */
public class RecordQuery {
    private int index;
    private int size;

    private String empId;
    private String schoolId;
    private String schoolIdEmp;
    private String school_record_mood_id;

    public String getSchool_record_mood_id() {
        return school_record_mood_id;
    }

    public void setSchool_record_mood_id(String school_record_mood_id) {
        this.school_record_mood_id = school_record_mood_id;
    }

    public String getSchoolIdEmp() {
        return schoolIdEmp;
    }

    public void setSchoolIdEmp(String schoolIdEmp) {
        this.schoolIdEmp = schoolIdEmp;
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
}
