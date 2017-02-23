package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Province;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhl on 2015/2/25.
 */
@Repository("provinceDao")
public interface ProvinceDao {
    public List<Province> list();
}
