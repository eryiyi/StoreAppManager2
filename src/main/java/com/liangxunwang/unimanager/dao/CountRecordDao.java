package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.CountRecord;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("countRecordDao")
public interface CountRecordDao {

    /**
     * 查询ad
     */
    List<CountRecord> lists(Map<String, Object> map);

    //保存
    void save(CountRecord countRecord);

    /**
     * 根据ID查找
     * @param lx_count_record_id
     * @return
     */
    public AdObj findById(String lx_count_record_id);

    Long count(Map<String, Object> map);

}
