package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushBroadcastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushBroadcastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.NoticeDao;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.query.NoticeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Service("noticeService")
public class NoticeService implements SaveService,ListService, FindService, UpdateService, DeleteService{
    @Autowired
    @Qualifier("noticeDao")
    private NoticeDao noticeDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NoticeQuery query = (NoticeQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getManager_id())){
            map.put("manager_id", query.getManager_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
//            map.put("emp_id", query.getEmp_id());
        }
        List<Notice> lists = noticeDao.list(map);
        long count = noticeDao.count(map);
        return new Object[]{lists, count};
    }

    @Override
    public Object save(Object object) throws ServiceException {
        Notice notice = (Notice) object;
        notice.setId(UUIDFactory.random());

        notice.setDateline(DateUtil.getDate(System.currentTimeMillis()+"","yyyy-MM-dd"));
        noticeDao.save(notice);

        pushNotice(notice, "3");
        pushNotice(notice, "4");
        return null;
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        String noticeId = (String) object;

        return noticeDao.findById(noticeId);
    }

    @Override
    public Object update(Object object) {
        noticeDao.update((Notice) object);
        return null;
    }

    @Override
    public Object delete(Object object) throws ServiceException {
        String noticeId = (String) object;
        noticeDao.delete(noticeId);
        return null;
    }

    private void pushNotice(Notice notice, String type){
        ChannelKeyPair pair = null;
        if (type.equals("3")) {
            pair = new ChannelKeyPair(Constants.API_KEY, Constants.SECRET_KEY);
        }else {
            pair = new ChannelKeyPair(Constants.IOS_API_KEY, Constants.IOS_SECRET_KEY);
        }

        // 2. 创建BaiduChannelClient对象实例
        BaiduChannelClient channelClient = new BaiduChannelClient(pair);

        // 3. 若要了解交互细节，请注册YunLogHandler类
        channelClient.setChannelLogHandler(new YunLogHandler() {
            @Override
            public void onHandle(YunLogEvent event) {
//                System.out.println(event.getMessage());
            }
        });

        try {
            // 4. 创建请求类对象
            PushBroadcastMessageRequest request = new PushBroadcastMessageRequest();
            request.setDeviceType(Integer.parseInt(type)); // device_type => 1: web 2: pc 3:android
            // 4:ios 5:wp

            if (type.equals("4")) {//如果是苹果手机端要设置这个
                request.setDeployStatus(Constants.IOS_TYPE);
            }
//            request.setMessage("Hello 百度推送");
            // 若要通知，
            request.setMessageType(1);
//            request.setMessage("{\"title\":\"公告\",\"description\":\"" + notice.getTitle() + "\",\"custom_content\":{\"type\":\"1\"}}");
            request.setMessage("{\"title\":\"公告\",\"description\":\"" + notice.getTitle() + "\",\"custom_content\": {\"type\":\"1\" , \"noticeId\":\""+notice.getId()+"\"}}");

            // 5. 调用pushMessage接口
            PushBroadcastMessageResponse response = channelClient
                    .pushBroadcastMessage(request);

            // 6. 认证推送成功
//            System.out.println("push amount : " + response.getSuccessAmount());

        } catch (ChannelClientException e) {
            // 处理客户端错误异常
            e.printStackTrace();
        } catch (ChannelServerException e) {
            // 处理服务端错误异常
//            System.out.println(String.format(
//                    "request_id: %d, error_code: %d, error_message: %s",
//                    e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
        }
    }
}
