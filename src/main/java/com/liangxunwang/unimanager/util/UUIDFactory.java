package com.liangxunwang.unimanager.util;

import java.util.UUID;

/**
 * Created by zhl on 2015/1/29.
 */
public class UUIDFactory {
    public static String random(){
        return UUID.randomUUID().toString().replaceAll("-", "");
    }
}
