package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.AdObj;
import com.liangxunwang.unimanager.model.NoticeHoutai;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("noticeHoutaiDao")
public interface NoticeHoutaiDao {

    /**
     * 查询
     */
    List<NoticeHoutai> lists(Map<String, Object> map);

    //保存
    void save(NoticeHoutai noticeHoutai);

    //删除
    void delete(String notice_id);

    Long count(Map<String, Object> map);

    /**
     * 根据ID查找
     * @param notice_id
     * @return
     */
    public NoticeHoutai findById(String notice_id);

    /**
     * 更新
     * @param noticeHoutai
     */
    public void update(NoticeHoutai noticeHoutai);
}
