package com.liangxunwang.unimanager.model;

/**
 * Created by zhanghailong on 2016/3/23.
 */
public class PaihangDianpu {
    private String mm_paihang_id;
    private String emp_id;//被推荐店铺的会员ID
    private String top_num;
    private String is_del;
    private String end_time;//结束时间--退榜时间-设置之后自动退榜
    private String is_type;//0-推荐店铺

    public String getMm_paihang_id() {
        return mm_paihang_id;
    }

    public void setMm_paihang_id(String mm_paihang_id) {
        this.mm_paihang_id = mm_paihang_id;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
    }

    public String getTop_num() {
        return top_num;
    }

    public void setTop_num(String top_num) {
        this.top_num = top_num;
    }

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getEnd_time() {
        return end_time;
    }

    public void setEnd_time(String end_time) {
        this.end_time = end_time;
    }

    public String getIs_type() {
        return is_type;
    }

    public void setIs_type(String is_type) {
        this.is_type = is_type;
    }
}
