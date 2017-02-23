package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.HttpParamKeyName;
import com.liangxunwang.unimanager.baidu.annotation.R;
import com.liangxunwang.unimanager.baidu.channel.constants.BaiduChannelConstants;

public class QueryUserTagsRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.REQUIRE)
    private String userId = null;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
