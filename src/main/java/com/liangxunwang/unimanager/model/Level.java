package com.liangxunwang.unimanager.model;

/**
 * 等级维护
 * Created by zhl on 2015/2/1.
 */
public class Level {
    private String levelId;
    private String levelName;
    private String levelStart;
    private String levelEnd;
    private String level_zhe;

    public String getLevel_zhe() {
        return level_zhe;
    }

    public void setLevel_zhe(String level_zhe) {
        this.level_zhe = level_zhe;
    }

    public String getLevelId() {
        return levelId;
    }

    public void setLevelId(String levelId) {
        this.levelId = levelId;
    }

    public String getLevelName() {
        return levelName;
    }

    public void setLevelName(String levelName) {
        this.levelName = levelName;
    }

    public String getLevelStart() {
        return levelStart;
    }

    public void setLevelStart(String levelStart) {
        this.levelStart = levelStart;
    }

    public String getLevelEnd() {
        return levelEnd;
    }

    public void setLevelEnd(String levelEnd) {
        this.levelEnd = levelEnd;
    }
}
