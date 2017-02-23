package com.liangxunwang.unimanager.mvc.vo;

import com.liangxunwang.unimanager.model.PKComment;

/**
 * Created by zhl on 2015/4/9.
 */
public class PkCommentVO extends PKComment {
    private String nickName;//�������ǳ�
    private String cover;//������ͷ��
    private String fNickName;//���������ǳ�

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getCover() {
        return cover;
    }

    public void setCover(String cover) {
        this.cover = cover;
    }

    public String getfNickName() {
        return fNickName;
    }

    public void setfNickName(String fNickName) {
        this.fNickName = fNickName;
    }
}
