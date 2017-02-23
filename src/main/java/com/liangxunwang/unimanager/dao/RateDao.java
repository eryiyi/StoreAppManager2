package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Rate;
import org.springframework.stereotype.Repository;

/**
 * Created by zhl on 2015/8/24.
 */
@Repository("rateDao")
public interface RateDao {
    Rate findById(String id);

    void updateRate(Rate rate);
}
