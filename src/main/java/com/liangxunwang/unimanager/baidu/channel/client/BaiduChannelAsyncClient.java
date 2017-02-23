package com.liangxunwang.unimanager.baidu.channel.client;


import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.*;

import java.util.concurrent.Callable;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;

public class BaiduChannelAsyncClient extends BaiduChannelClient implements
        BaiduChannelAsync {

    private static ExecutorService execurotService = Executors
            .newFixedThreadPool(2);

    public BaiduChannelAsyncClient(ChannelKeyPair pair) {
        super(pair);
    }

    @SuppressWarnings("static-access")
    public BaiduChannelAsyncClient(ChannelKeyPair pair,
            ExecutorService execurotService) {
        super(pair);
        this.execurotService = execurotService;
    }

    @Override
    public Future<PushUnicastMessageResponse> pushUnicastMessageAsync(
            final PushUnicastMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService
                .submit(new Callable<PushUnicastMessageResponse>() {
                    @Override
                    public PushUnicastMessageResponse call() throws Exception {
                        return pushUnicastMessage(request);
                    }
                });
    }

    @Override
    public Future<Void> pushTagMessageAsync(final PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                pushTagMessage(request);
                return null;
            }
        });
    }

    @Override
    public Future<Void> pushBroadcastMessageAsync(
            final PushBroadcastMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                pushBroadcastMessage(request);
                return null;
            }
        });
    }

    @Override
    public Future<QueryBindListResponse> queryBindListAsync(
            final QueryBindListRequest request) throws ChannelClientException,
            ChannelServerException {
        return execurotService.submit(new Callable<QueryBindListResponse>() {
            @Override
            public QueryBindListResponse call() throws Exception {
                return queryBindList(request);
            }
        });
    }

    @Override
    public Future<Void> verifyBindAsync(final VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException {
        return execurotService.submit(new Callable<Void>() {
            @Override
            public Void call() throws Exception {
                verifyBind(request);
                return null;
            }
        });
    }

    @Override
    public Future<QueryUserTagsResponse> queryUserTagsAsync(
            final QueryUserTagsRequest request) throws ChannelClientException,
            ChannelServerException {
        return execurotService.submit(new Callable<QueryUserTagsResponse>() {
            @Override
            public QueryUserTagsResponse call() throws Exception {
                return queryUserTags(request);
            }
        });
    }

}
