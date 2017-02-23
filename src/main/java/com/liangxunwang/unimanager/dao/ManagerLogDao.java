package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ManagerLog;
import com.liangxunwang.unimanager.mvc.vo.ManagerLogVO;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 */
@Repository("managerLogDao")
public interface ManagerLogDao {

    public void save(ManagerLog log);

    public List<ManagerLogVO> list(Map<String, Object> map);

    public long count();
}
