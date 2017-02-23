package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/2/5.
 */
public class GoodsCommentQuery {
    private int index;
    private int size;
    private String goodsId;
    private String emp_id;//评论人ID
    private String emp_id_dianpu;//店铺会员ID
    private String goods_emp_id;//店铺会员ID
    private String is_del;//

    public String getIs_del() {
        return is_del;
    }

    public void setIs_del(String is_del) {
        this.is_del = is_del;
    }

    public String getGoods_emp_id() {
        return goods_emp_id;
    }

    public void setGoods_emp_id(String goods_emp_id) {
        this.goods_emp_id = goods_emp_id;
    }

    public String getEmp_id_dianpu() {
        return emp_id_dianpu;
    }

    public void setEmp_id_dianpu(String emp_id_dianpu) {
        this.emp_id_dianpu = emp_id_dianpu;
    }

    public String getEmp_id() {
        return emp_id;
    }

    public void setEmp_id(String emp_id) {
        this.emp_id = emp_id;
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

    public String getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(String goodsId) {
        this.goodsId = goodsId;
    }
}
