package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.JSonPath;

import java.util.LinkedList;
import java.util.List;

public class FetchTagResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\total_num")
    private int totalNum = 0;

    @JSonPath(path = "response_params\\amount")
    private int amount = 0;

    @JSonPath(path = "response_params\\tags")
    private List<TagInfo> tags = new LinkedList<TagInfo>();

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(int amount) {
        this.amount = amount;
    }

    public List<TagInfo> getTags() {
        return tags;
    }

    public void setTags(List<TagInfo> tags) {
        this.tags = tags;
    }

}
