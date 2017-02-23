package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Relate;
import com.liangxunwang.unimanager.mvc.vo.RelateVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/8.
 */
@Repository("relateDao")
public interface RelateDao {

    void save(Relate relate);

    List<RelateVO> list(Map<String,Object> map);

    void deleteByRecordId(String recordId);

    void deleteByGoodsId(String goodsId);

    void update(String id);
}
