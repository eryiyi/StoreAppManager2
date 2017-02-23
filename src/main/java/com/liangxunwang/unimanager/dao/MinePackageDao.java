package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.MinePackage;
import org.springframework.stereotype.Repository;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("minePackageDao")
public interface MinePackageDao {

    /**
     * 查询ad
     */
//    List<MinePackage> lists(Map<String, Object> map);

    //保存
    void save(MinePackage minePackage);

    //删除
    void delete(String emp_id);

    /**
     * 根据ID查找
     * @param emp_id
     * @return
     */
    public MinePackage findById(String emp_id);

    /**
     * 更新
     * @param minePackage
     */
    public void update(MinePackage minePackage);
    //减零钱
    public void updateDel(MinePackage minePackage);
}
