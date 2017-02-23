package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.JSonPath;

public class PushTagMessageResponse extends ChannelResponse {

    @JSonPath(path = "response_params\\success_amount")
    private int successAmount = 0;

    public int getSuccessAmount() {
        return successAmount;
    }

    public void setSuccessAmount(int successAmount) {
        this.successAmount = successAmount;
    }

}
