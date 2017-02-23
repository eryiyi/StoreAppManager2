package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("adDao")
public interface AdDao {

    /**
     * 查询ad
     */
    List<AdObj> lists(Map<String, Object> map);

    //保存
    void save(AdObj adObj);

    //删除
    void delete(String mm_ad_id);

    /**
     * 根据ID查找
     * @param mm_ad_id
     * @return
     */
    public AdObj findById(String mm_ad_id);

    /**
     * 更新
     * @param adObj
     */
    public void update(AdObj adObj);
}
