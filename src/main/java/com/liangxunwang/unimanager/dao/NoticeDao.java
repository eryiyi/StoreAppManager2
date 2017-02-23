package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Notice;
import org.springframework.stereotype.Repository;
import sun.rmi.runtime.Log;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Repository("noticeDao")
public interface NoticeDao {

    public void save(Notice notice);

    public List<Notice> list(Map<String,Object> map);

    public List<Notice> listUp(Map<String,Object> map);

    public List<Notice> findExist(Map<String,Object> map);

    public Notice findById(String noticeId);

    public void update(Notice notice);

    public void delete(String noticeId);

    Long count(Map<String,Object> map);
}
