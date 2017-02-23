package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CancelOrder;
import org.springframework.stereotype.Repository;

/**
 * Created by Administrator on 2015/8/18.
 */
@Repository("appOrderCancelDao")
public interface AppOrderCancelDao {
    /**
     * 取消订单--记录保存
     * */
    void save(CancelOrder cancelOrder);
 }
