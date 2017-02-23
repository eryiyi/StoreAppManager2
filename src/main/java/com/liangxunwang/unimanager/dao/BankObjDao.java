package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.BankObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("bankObjDao")
public interface BankObjDao {
    /**
     * 查询ad
     */
    List<BankObj> lists(Map<String, Object> map);

    //保存
    void save(BankObj bankObj);

    //删除
    void delete(String bank_id);

    /**
     * 根据ID查找
     * @param bank_id
     * @return
     */
    public BankObj findById(String bank_id);

}
