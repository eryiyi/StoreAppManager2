package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.DianpuComment;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("dianpuCommentDao")
public interface DianpuCommentDao {

    List<DianpuComment> lists(Map<String, Object> map);

    void save(DianpuComment dianpuComment);

    void delete(String dianpu_comment_id);

    public DianpuComment findById(String dianpu_comment_id);

    Long count(Map<String, Object> map);

    Long countStarNumber(Map<String,Object> map);

}
