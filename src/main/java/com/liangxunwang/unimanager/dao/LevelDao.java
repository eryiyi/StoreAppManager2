package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Level;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhl on 2015/2/1.
 */
@Repository("levelDao")
public interface LevelDao {
    public void save(Level level);

    public List<Level> list();

    public void delete(String levelId);

    Level findById(String level_id);

    void  update(Level level);
}
