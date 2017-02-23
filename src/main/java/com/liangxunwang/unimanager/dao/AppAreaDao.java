package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Area;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhl on 2015/2/25.
 */
@Repository("appAreaDao")
public interface AppAreaDao {
    public List<Area> list(Area area);
}
