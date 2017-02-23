package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.LoadPic;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("loadPicDao")
public interface LoadPicDao {

    /**
     * 查询ad
     */
    List<LoadPic> lists(Map<String, Object> map);

    //保存
    void save(LoadPic loadPic);

    //删除
    void delete(String load_pic_id);

    /**
     * 根据ID查找
     * @param load_pic_id
     * @return
     */
    public LoadPic findById(String load_pic_id);

    /**
     * 更新
     * @param loadPic
     */
    public void update(LoadPic loadPic);
}
