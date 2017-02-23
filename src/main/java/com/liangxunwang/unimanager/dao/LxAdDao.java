package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.LxAd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("lxAdDao")
public interface LxAdDao {

    /**
     * 查询ad
     */
    List<LxAd> lists(Map<String, Object> map);
    //查找全部
    List<LxAd> listsAll(Map<String, Object> map);

    long count(Map<String, Object> map);

    //保存
    void save(LxAd lxAd);

    //删除
    void delete(String ad_id);

    /**
     * 根据ID查找
     * @param ad_id
     * @return
     */
    public LxAd findById(String ad_id);

    /**
     * 更新
     * @param ad
     */
    public void update(LxAd ad);
}
