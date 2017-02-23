package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Goods;
import com.liangxunwang.unimanager.mvc.vo.GoodsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/2.
 */
@Repository("goodsDao")
public interface GoodsDao {
    /**
     * 保存商品
     * @param goods
     */
    public void save(Goods goods);

    /**
     * 根据商品的类型ID和查询条件查找商品
     * @param map
     * @return
     */
    List<GoodsVO> findListByType(Map<String, Object> map);

    /**
     * 根据ID查找详情
     * @param id
     * @return
     */
    GoodsVO findByGoodsId(String id);

    /**
     * 根据ID下架商品
     * @param id
     */
    void downGoods(String id);

    /**
     * 删除商品
     * @param id
     */
    void deleteGoods(String id);

    void deleteGoodsByEmp(@Param(value = "empId")String empId, @Param(value = "schoolId")String schoolId);

    /**
     * 根据商家ID和学校ID删除该商家下的所有商品
     */
    void deleteGoodsById(Map<String, Object> map);

    /**
     * 根据商品ID置顶商品
     * @param goodsId  商品ID
     * @param position  0不置顶  1 置顶
     */
    void updatePosition(@Param(value = "goodsId")String goodsId, @Param(value = "position")String position);

    /**
     * 检查某个商家和会员是否有发商品
     * @param empId
     * @param schoolId
     * @return
     */
    Goods findGoodsByEmpIdAndSchoolId(@Param(value = "empId")String empId, @Param(value = "schoolId")String schoolId);
}
