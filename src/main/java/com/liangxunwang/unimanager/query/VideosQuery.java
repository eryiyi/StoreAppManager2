package com.liangxunwang.unimanager.query;

/**
 * Created by zhl on 2015/2/3.
 */
public class VideosQuery {
    private int index;
    private int size;
    private String isdel;
    private String time_is;//0时间倒叙   1时间正序
    private String favour_is;//0默认排行  1最热排行

    public String getTime_is() {
        return time_is;
    }

    public void setTime_is(String time_is) {
        this.time_is = time_is;
    }

    public String getFavour_is() {
        return favour_is;
    }

    public void setFavour_is(String favour_is) {
        this.favour_is = favour_is;
    }

    public int getIndex() {
        return index;
    }

    public void setIndex(int index) {
        this.index = index;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }

    public String getIsdel() {
        return isdel;
    }

    public void setIsdel(String isdel) {
        this.isdel = isdel;
    }
}
