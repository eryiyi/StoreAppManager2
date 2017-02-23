package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.LxCardObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("lxCardObjDao")
public interface LxCardObjDao {

    /**
     * 查询
     */
    List<LxCardObj> lists(Map<String, Object> map);

    /**
     * 根据ID查找
     * @param lx_card_id
     * @return
     */
    public LxCardObj findById(String lx_card_id);

    /**
     * 更新
     * @param lxCardObj
     */
    public void update(LxCardObj lxCardObj);
}
