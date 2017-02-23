package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.lxBankApply;
import com.liangxunwang.unimanager.mvc.vo.lxBankApplyVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 * 提现申请
 */
@Repository("lxbankApplyDao")
public interface LxbankApplyDao {

    /**
     * 查询
     */
    List<lxBankApplyVo> lists(Map<String, Object> map);

    long count(Map<String, Object> map);

    //保存
    void save(lxBankApply lxBankApply);

    /**
     * 根据ID查找
     * @param lx_bank_apply_id
     * @return
     */
    public lxBankApplyVo findById(String lx_bank_apply_id);

    /**
     * 更新
     * @param lxBankApply
     */
    public void update(lxBankApply lxBankApply);
}
