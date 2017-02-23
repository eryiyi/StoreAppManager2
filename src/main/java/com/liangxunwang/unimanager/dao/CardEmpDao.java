package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.CardEmp;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("cardEmpDao")
public interface CardEmpDao {
    /**
     * 查询CardEmp
     */
    List<CardEmp> lists(Map<String, Object> map);

    //保存
    void save(CardEmp cardEmp);

    /**
     * 根据ID查找
     * @param emp_id
     * @return
     */
    public CardEmp findById(String emp_id);

    /**
     * 更新
     * @param cardEmp
     */
    public void update(CardEmp cardEmp);

    Long count(Map<String, Object> map);

    /**
     * 根据定向卡到期时间执行
     * @param nowTime
     */
    public void updateOverTime(String nowTime);
    public void updateOverTime2(String nowTime);
}
