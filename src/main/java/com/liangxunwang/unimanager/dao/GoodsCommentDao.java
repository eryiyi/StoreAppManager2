package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.DianPuFavour;
import com.liangxunwang.unimanager.model.GoodsComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Repository("goodsCommentDao")
public interface GoodsCommentDao {
    public void save(GoodsComment comment);

    //查询商品评论列表  //查询店铺评论列表
    public List<GoodsComment> list(Map<String,Object> map);

    List<GoodsComment> listAllComment(Map<String,Object> map);

    Long count(Map<String,Object> map);

    Long countStarNumber(Map<String,Object> map);

    void deleteById(String comment_id);

    GoodsComment find(String comment_id);
}
