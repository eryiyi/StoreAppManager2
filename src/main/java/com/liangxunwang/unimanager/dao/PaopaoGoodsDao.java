package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.PaopaoGoods;
import com.liangxunwang.unimanager.mvc.vo.PaopaoGoodsVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/16.
 */
@Repository("paopaoGoodsDao")
public interface PaopaoGoodsDao {
    /**
     * 根据商家会员ID和学校ID查询该学校已经添加了几个商品
     */
    List<PaopaoGoods> listByEmpSchool(String empId);

    //查询猜你喜欢
    List<PaopaoGoodsVO> listLikes(Map<String,Object> map);

    void save(PaopaoGoods goods);

    List<PaopaoGoodsVO> listGoods(Map<String,Object> map);
    //查询所有商品
    List<PaopaoGoodsVO> listGoodsAll(Map<String,Object> map);

    Long count(Map<String, Object> map);
    Long countZero(Map<String, Object> map);

    /**
     * 根据ID删除我的商品
     * @param id
     */
    void deleteById(String id);

    /**
     * 根据ID查找商品
     */
//    PaopaoGoods findById(String id);

    void update(PaopaoGoods goods);
    void updatePosition(PaopaoGoods goods);

    PaopaoGoodsVO findGoodsVO(String id);
    /**
     * 根据商品id，减去商品数量
     * */
    void updateCountById(@Param(value = "id")String id, @Param(value = "goodsCount") String goodsCount, @Param(value = "goods_count_sale") String goods_count_sale);

    /**
     * 删除商家下的商品，至为删除状态
     */
    void deleteGoodsById(Map<String,Object> map);

    void deleteGoodsByEmp(@Param(value = "empId")String empId);

    void updatePostionById(@Param(value = "id")String id, @Param(value = "goods_is_use")String goods_is_use);

    void updateJiaById(@Param(value = "id")String id, @Param(value = "status")String status);
}
