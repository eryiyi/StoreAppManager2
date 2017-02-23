package com.liangxunwang.unimanager.baidu.callback;


import com.liangxunwang.unimanager.baidu.event.YunHttpEvent;

public interface YunHttpObserver {

    public void onHandle(YunHttpEvent event);

}
