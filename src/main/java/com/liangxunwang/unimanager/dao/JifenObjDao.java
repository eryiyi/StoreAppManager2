package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.JifenObj;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("jifenObjDao")
public interface JifenObjDao {


    /**
     * 根据ID查找
     * @param lx_jifen_id
     * @return
     */
    public JifenObj findById(String lx_jifen_id);
    public List<JifenObj> list();

    /**
     * 更新
     * @param jifenObj
     */
    public void update(JifenObj jifenObj);
}
