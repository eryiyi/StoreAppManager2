package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/4/10.
 */
public class ChampionQuery {
    private int index;
    private int size;

    private String schoolId;
    private String type;// 0ȫ��   1 ѧУ

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

    public String getSchoolId() {
        return schoolId;
    }

    public void setSchoolId(String schoolId) {
        this.schoolId = schoolId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
