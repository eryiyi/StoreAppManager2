package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.VersonCodeObj;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("versionCodeObjDao")
public interface VersionCodeObjDao {
    /**
     * 查询ad
     */
    List<VersonCodeObj> lists(Map<String, Object> map);

    /**
     * 根据ID查找
     * @param mm_version_id
     * @return
     */
    public VersonCodeObj findById(String mm_version_id);

    /**
     * 更新
     * @param versonCodeObj
     */
    public void update(VersonCodeObj versonCodeObj);
}
