package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.BrowsingObj;
import com.liangxunwang.unimanager.mvc.vo.BrowsingVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("browsingDao")
public interface BrowsingDao {

    /**
     * 分页查询
     */
    List<BrowsingVo> lists(Map<String, Object> map);

    long count(Map<String, Object> map);

    //保存
    void save(BrowsingObj browsingObj);

    /**
     * 更新
     * @param browsingObj
     */
    public void update(BrowsingObj browsingObj);
}
