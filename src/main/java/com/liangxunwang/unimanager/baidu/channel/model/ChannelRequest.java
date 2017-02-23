package com.liangxunwang.unimanager.baidu.channel.model;


import com.liangxunwang.unimanager.baidu.annotation.HttpParamKeyName;
import com.liangxunwang.unimanager.baidu.annotation.R;
import com.liangxunwang.unimanager.baidu.channel.constants.BaiduChannelConstants;

public abstract class ChannelRequest {

    @HttpParamKeyName(name = BaiduChannelConstants.VERSION, param = R.OPTIONAL)
    protected String v = null;

    @HttpParamKeyName(name = BaiduChannelConstants.TIMESTAMP, param = R.REQUIRE)
    protected Long timestamp = System.currentTimeMillis() / 1000L;

    @HttpParamKeyName(name = BaiduChannelConstants.EXPIRES, param = R.OPTIONAL)
    protected Long expires = null;

}
