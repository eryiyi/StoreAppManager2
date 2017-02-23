package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Count;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/4.
 */
@Repository("countDao")
public interface CountDao {

    public void save(Count count);

    public List<CountVo> list(Map<String, Object> map);

    //增加积分
    void updateScore(@Param(value = "empId")String empId, @Param(value = "count")String count);
    //减积分
    void updateScoreDelete(@Param(value = "empId")String empId, @Param(value = "count")String count);

    /**
     * 根据条件查询数量
     * @param map
     * @return
     */
    long count(Map<String,Object> map);

    CountVo findById(String emp_id);
}
