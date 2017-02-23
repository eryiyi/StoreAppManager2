package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.GoodsType;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Repository("goodsTypeDao")
public interface GoodsTypeDao {
    /**
     * 保存商品分类
     * @param goodsType
     */
    public void save(GoodsType goodsType);

    public List<GoodsType> list(Map<String, Object> map);

    /**
     * 根据ID查找分类
     * @param typeId
     * @return
     */
    public GoodsType findById(String typeId);

    /**
     * 更新分类
     * @param type
     */
    public void update(GoodsType type);
    //是否热门
    public void updateReMen(GoodsType type);

    /**
     * 根据ID删除分类
     * @param typeId
     */
    public void deleteById(String typeId);

}
