package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.News;

/**
 * Created by zhl on 2015/2/3.
 */
public class NewsVO extends News {
    private String nickName;//发布者昵称
    private String typeName;
    private String pic;

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    public String getPic() {
        return pic;
    }

    public void setPic(String pic) {
        this.pic = pic;
    }
}
