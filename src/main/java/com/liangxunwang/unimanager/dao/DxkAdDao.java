package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.DxkAd;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("dxkAdDao")
public interface DxkAdDao {

    /**
     * 查询ad
     */
    List<DxkAd> lists(Map<String, Object> map);

    //保存
    void save(DxkAd dxkAd);

    //删除
    void delete(String dxk_ad_id);

    /**
     * 根据ID查找
     * @param dxk_ad_id
     * @return
     */
    public DxkAd findById(String dxk_ad_id);

    /**
     * 更新
     * @param dxkAd
     */
    public void update(DxkAd dxkAd);
}
