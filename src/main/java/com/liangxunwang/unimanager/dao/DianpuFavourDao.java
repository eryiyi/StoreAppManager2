package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.DianPuFavour;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("dianpuFavourDao")
public interface DianpuFavourDao {
    /**
     * 查询DianPuFavour
     */
    List<DianpuFavourVO> lists(Map<String, Object> map);
    //查询我的粉丝
    List<DianpuFavourVO> listsFensi(Map<String, Object> map);

    List<DianpuFavourVO> listsFensiAll(Map<String, Object> map);

    Long count(Map<String, Object> map);

    //保存
    void save(DianPuFavour dianPuFavour);

    //删除
    void delete(String favour_no);

    DianPuFavour find(DianPuFavour dianPuFavour);


}
