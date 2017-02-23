package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.LxConsumption;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 * 消费记录
 */
@Repository("lxConsumptionDao")
public interface LxConsumptionDao {

    /**
     * 查询
     */
    List<LxConsumption> lists(Map<String, Object> map);

    Long count(Map<String, Object> map);

    //保存
    void save(LxConsumption adObj);

    /**
     * 根据ID查找
     * @param lx_consumption_id
     * @return
     */
    public LxConsumption findById(String lx_consumption_id);

}
