package com.liangxunwang.unimanager.baidu.channel.client;


import com.liangxunwang.unimanager.baidu.callback.YunLogHttpCallBack;
import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.auth.signature.ChannelSignatureDigest;
import com.liangxunwang.unimanager.baidu.channel.constants.BaiduChannelConstants;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.*;
import com.liangxunwang.unimanager.baidu.channel.transform.ChannelRestRequestChecker;
import com.liangxunwang.unimanager.baidu.channel.transform.ChannelRestRequestMapper;
import com.liangxunwang.unimanager.baidu.channel.transform.ChannelRestResponseJsonUnmapper;
import com.liangxunwang.unimanager.baidu.channel.transform.utils.TransformUtilitiy;
import com.liangxunwang.unimanager.baidu.exception.YunHttpClientException;
import com.liangxunwang.unimanager.baidu.httpclient.YunHttpClient;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.baidu.model.HttpRestResponse;

import java.util.Map;

public class BaiduChannelClient implements BaiduChannel {

    private String apiKey = null;

    private String secretKey = null;

    private String host = null;

    private YunLogHttpCallBack logHttpCallback = new YunLogHttpCallBack();

    private ChannelRestResponseJsonUnmapper responseJsonUnmapper = new ChannelRestResponseJsonUnmapper();

    public BaiduChannelClient(ChannelKeyPair pair) {
        this(pair, BaiduChannelConstants.CHANNEL_REST_URL);
    }

    private BaiduChannelClient(ChannelKeyPair pair, String host) {
        this.apiKey = pair.getApiKey();
        this.secretKey = pair.getSecretKey();
        this.host = host;
    }

    @Override
    public QueryBindListResponse queryBindList(QueryBindListRequest request)
            throws ChannelClientException, ChannelServerException {
        // TODO Auto-generated method stub
        HttpRestResponse resp = process("query_bindlist", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryBindListResponse());
    }

    @Override
    public PushUnicastMessageResponse pushUnicastMessage(
            PushUnicastMessageRequest request) throws ChannelClientException,
            ChannelServerException {

        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushUnicastMessageResponse());

    }

    @Override
    public PushTagMessageResponse pushTagMessage(PushTagMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        // TODO Auto-generated method stub
        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushTagMessageResponse());
    }

    @Override
    public PushBroadcastMessageResponse pushBroadcastMessage(
            PushBroadcastMessageRequest request) throws ChannelClientException,
            ChannelServerException {
        // TODO Auto-generated method stub
        HttpRestResponse resp = process("push_msg", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new PushBroadcastMessageResponse());
    }

    @Override
    public void verifyBind(VerifyBindRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("verify_bind", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public FetchMessageResponse fetchMessage(FetchMessageRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("fetch_msg", request);
        return new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse(),
                new FetchMessageResponse());
    }

    @Override
    public void setTag(SetTagRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("set_tag", request);
        new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse());
    }

    @Override
    public void deleteTag(DeleteTagRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("delete_tag", request);
        new ChannelRestResponseJsonUnmapper().unmarshall(
                resp.getHttpStatusCode(), resp.getJsonResponse());
    }

    @Override
    public FetchTagResponse fetchTag(FetchTagRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("fetch_tag", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new FetchTagResponse());
    }

    @Override
    public QueryUserTagsResponse queryUserTags(QueryUserTagsRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("query_user_tags", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryUserTagsResponse());
    }

    @Override
    public void initAppIoscert(InitAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("init_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public void updateAppIoscert(UpdateAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("update_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public void deleteAppIoscert(DeleteAppIoscertRequest request)
            throws ChannelClientException, ChannelServerException {
        HttpRestResponse resp = process("delete_app_ioscert", request);
        responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse());
    }

    @Override
    public QueryAppIoscertResponse queryAppIoscert(
            QueryAppIoscertRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("query_app_ioscert", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryAppIoscertResponse());
    }

    @Override
    public QueryDeviceTypeResponse queryDeviceType(
            QueryDeviceTypeRequest request) throws ChannelClientException,
            ChannelServerException {
        HttpRestResponse resp = process("query_device_type", request);
        return responseJsonUnmapper.unmarshall(resp.getHttpStatusCode(),
                resp.getJsonResponse(), new QueryDeviceTypeResponse());
    }

    public void setChannelLogHandler(YunLogHandler logHandler) {
        logHttpCallback.setHandler(logHandler);
    }

    // -----------------------------------------------------------

    private HttpRestResponse process(String method, ChannelRequest request)
            throws ChannelClientException, ChannelServerException {

        ChannelRestRequestChecker checker = new ChannelRestRequestChecker();
        checker.validate(request);

        ChannelRestRequestMapper mapper = new ChannelRestRequestMapper();
        Map<String, String> params = mapper.marshall(request);
        params.put("method", method);
        params.put("apikey", apiKey);

        String surl = obtainIntegrityUrl(request);

        ChannelSignatureDigest digest = new ChannelSignatureDigest();
        String sign = digest.digest("POST", surl, secretKey, params);
        params.put("sign", sign);

        YunHttpClient client = new YunHttpClient();
        client.addHttpCallback(logHttpCallback);

        try {
            return client.doExecutePostRequestResponse(surl, params);
        } catch (YunHttpClientException e) {
            throw new ChannelClientException(e.getMessage());
        }
    }

    private String obtainIntegrityUrl(ChannelRequest request) {
        String resurl = host;
        if (host.startsWith("http://") || host.startsWith("https://")) {
        } else {
            resurl = "http://" + host;
        }
        if (resurl.endsWith("/")) {
            resurl = resurl + "rest/2.0/channel/";
        } else {
            resurl = resurl + "/rest/2.0/channel/";
        }
        String resourceId = TransformUtilitiy.extractResourceId(request,
                BaiduChannelConstants.CHANNEL_DEFAULT_RESOURCE_ID);
        return resurl + resourceId;
    }

}
