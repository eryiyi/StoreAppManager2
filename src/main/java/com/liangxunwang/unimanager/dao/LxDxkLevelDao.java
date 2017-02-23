package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.LxDxkLevel;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("lxDxkLevelDao")
public interface LxDxkLevelDao {

    /**
     * 查询ad
     */
    List<LxDxkLevel> lists(Map<String, Object> map);

    //保存
    void save(LxDxkLevel lxAttribute);
    /**
     * 根据ID查找
     * @param lx_dxk_level_id
     * @return
     */
    public LxDxkLevel findById(String lx_dxk_level_id);

    /**
     * 更新
     * @param lxAttribute
     */
    public void update(LxDxkLevel lxAttribute);
}
