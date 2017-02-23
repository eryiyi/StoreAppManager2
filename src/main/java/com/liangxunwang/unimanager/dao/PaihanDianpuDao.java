package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.PaihangDianpu;
import com.liangxunwang.unimanager.model.PaihangObj;
import com.liangxunwang.unimanager.mvc.vo.PaihangDianpuVo;
import com.liangxunwang.unimanager.mvc.vo.PaihangObjVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("paihanDianpuDao")
public interface PaihanDianpuDao {

    /**
     * 查询排行信息
     */
    List<PaihangDianpuVo> listRecordVo(Map<String, Object> map);

    long count(Map<String, Object> map);

    /**
     * 保存一条信息
     */
    void save(PaihangDianpu paihangObj);

    //删除信息根据id
   void deleteById(String mm_paihang_id);

    //更新置顶状态
    void updateTop(PaihangDianpu paihangObj);

    //根据id查询
    PaihangDianpuVo findById(Map<String, Object> map);


    //根据商品iD查询是否已经存在了
    PaihangDianpuVo findByGoodsId(Map<String, Object> map);

    /**
     * 根据过期时间去禁用VIP
     * @param nowTime
     */
    public void updateOverTime(String nowTime);
}
