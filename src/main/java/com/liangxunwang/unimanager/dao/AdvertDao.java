package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Advert;
import com.liangxunwang.unimanager.mvc.vo.AdvertVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/31.
 */
@Repository("advertDao")
public interface AdvertDao {
    /**
     * 保存广告
     * @param advert
     */
    public void save(Advert advert);

    /**
     * 查找广告集合  分页查询
     * @param map
     * @return
     */
    public List<AdvertVO> list(Map<String,Object> map);

    /**
     * 获得广告数量
     * @param map
     * @return
     */
    public long count(Map<String, Object> map);

    /**
     * 根据ID查找广告
     * @param adId
     * @return
     */
    public Advert findById(String adId);

    /**
     * 更新广告
     * @param advert
     */
    public void update(Advert advert);

    /**
     * 根据ID删除广告
     * @param adId
     */
    public void deleteById(String adId);

    /**
     * 获得大广告位
     * @return
     */
    public List<Advert> getBig(String typeId);

    /**
     * 根据学校ID获得所有的小广告位
     * @param schoolId
     * @return
     */
    public List<Advert> getSmall(String schoolId);

    /**
     * 根据过期时间去禁用
     * @param nowTime
     */
    public void updateOverTime(String nowTime);
}
