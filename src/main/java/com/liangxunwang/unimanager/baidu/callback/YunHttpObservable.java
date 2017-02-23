package com.liangxunwang.unimanager.baidu.callback;


import com.liangxunwang.unimanager.baidu.event.YunHttpEvent;

import java.util.List;

public interface YunHttpObservable {

    public void addHttpCallback(YunHttpObserver callback);

    public void addBatchHttpCallBack(List<YunHttpObserver> callbacks);

    public void removeCallBack(YunHttpObserver callback);

    public void notifyAndCallback(YunHttpEvent event);

}
