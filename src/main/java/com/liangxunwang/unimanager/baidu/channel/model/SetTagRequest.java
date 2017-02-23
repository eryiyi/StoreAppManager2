package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.HttpParamKeyName;
import com.liangxunwang.unimanager.baidu.annotation.R;
import com.liangxunwang.unimanager.baidu.annotation.RangeRestrict;
import com.liangxunwang.unimanager.baidu.channel.constants.BaiduChannelConstants;

public class SetTagRequest extends ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.TAG_NAME, param = R.REQUIRE)
    @RangeRestrict(minLength = 1, maxLength = 128)
    private String tag;

    @HttpParamKeyName(name = BaiduChannelConstants.USER_ID, param = R.OPTIONAL)
    @RangeRestrict(minLength = 1, maxLength = 256)
    private String userId;

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

}
