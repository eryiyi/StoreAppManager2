package com.liangxunwang.unimanager.huanxin.comm.httpclient.utils;

import com.liangxunwang.unimanager.huanxin.comm.HxConstants;
import com.liangxunwang.unimanager.huanxin.comm.Roles;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.vo.ClientSecretCredential;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.vo.Credential;

/**
 * Created by 刘先强 on 2015/2/6.
 */
public class MyToken {
    // 通过app的client_id和client_secret来获取app管理员token
    private static Credential credential;
    public static Credential getCredential(){
        if(credential==null||credential.getToken().isExpired()){
            credential = new ClientSecretCredential(HxConstants.APP_CLIENT_ID,
                    HxConstants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);
        }
        return credential;
    }
}
