package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GoodsType;
import com.liangxunwang.unimanager.model.LxClass;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Repository("lxClassDao")
public interface LxClassDao {
    /**
     * 保存分类
     */
    public void save(LxClass lxClass);

    public List<LxClass> list(Map<String, Object> map);

    /**
     * 根据ID查找分类
     * @param typeId
     * @return
     */
    public LxClass findById(String typeId);

    /**
     * 更新分类
     */
    public void update(LxClass lxClass);

    /**
     * 根据ID删除分类
     * @param lx_class_id
     */
    public void deleteById(String lx_class_id);


}
