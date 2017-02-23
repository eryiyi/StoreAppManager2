package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.LxAttribute;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("lxAttributeDao")
public interface LxAttributeDao {

    /**
     * 查询ad
     */
    List<LxAttribute> lists(Map<String, Object> map);

    //保存
    void save(LxAttribute lxAttribute);
    /**
     * 根据ID查找
     * @param lx_attribute_id
     * @return
     */
    public LxAttribute findById(String lx_attribute_id);

    /**
     * 更新
     * @param lxAttribute
     */
    public void update(LxAttribute lxAttribute);

    /**
     * 根据NICK 数字 查找
     * @param lx_attribute_nick
     * @return
     */
    public LxAttribute findByNick(String lx_attribute_nick);
}
