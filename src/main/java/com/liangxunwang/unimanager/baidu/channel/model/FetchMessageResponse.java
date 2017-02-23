package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.JSonPath;

import java.util.LinkedList;
import java.util.List;

public class FetchMessageResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\total_num")
    private int totalNum = 0;

    @JSonPath(path = "response_params\\messages")
    private List<ChannelMessage> messages = new LinkedList<ChannelMessage>();

    public int getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(int totalNum) {
        this.totalNum = totalNum;
    }

    public List<ChannelMessage> getMessages() {
        return messages;
    }

    public void setMessages(List<ChannelMessage> messages) {
        this.messages = messages;
    }

}
