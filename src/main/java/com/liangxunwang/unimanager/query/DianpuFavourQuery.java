package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/1/31.
 */
public class DianpuFavourQuery {
    private int index;
    private int size;

    private String emp_id_favour;
    private String emp_id;//查询我的粉丝用 给商家的

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


    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getEmp_id_favour() {
        return emp_id_favour;
    }

    public void setEmp_id_favour(String emp_id_favour) {
        this.emp_id_favour = emp_id_favour;
    }
}
