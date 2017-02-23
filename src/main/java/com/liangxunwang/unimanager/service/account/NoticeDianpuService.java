package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.DianpuFavourDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.NoticeDao;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Service("noticeDianpuService")
public class NoticeDianpuService implements SaveService{
    @Autowired
    @Qualifier("noticeDao")
    private NoticeDao noticeDao;

    @Autowired
    @Qualifier("dianpuFavourDao")
    private DianpuFavourDao dianpuFavourDao;

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;


    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Notice notice = (Notice) object;
        if(!StringUtil.isNullOrEmpty(notice.getManager_id()) && !"0".equals(notice.getManager_id())){
            //先查询今天是否已经发过通知了
            Map<String,Object> map1 = new HashMap<String, Object>();
            map1.put("manager_id", notice.getManager_id());
            map1.put("dateline", DateUtil.getDate(System.currentTimeMillis() + "", "yyyy-MM-dd"));
            List<Notice> lists = noticeDao.findExist(map1);
            if(lists != null && lists.size()>0){
                //说明今天发不过了
                return 1;
            }else {
                notice.setId(UUIDFactory.random());
                notice.setDateline(DateUtil.getDate(System.currentTimeMillis() + "", "yyyy-MM-dd"));
                noticeDao.save(notice);

                //说明需要发送通知给粉丝
                AdminVO adminVO = adminDao.findById(notice.getManager_id());
                if(adminVO != null){
                    //查询我的粉丝
                    Map<String, Object> map = new HashMap<String, Object>();
                    map.put("param1" , adminVO.getEmp_id());
//                    List<DianpuFavourVO> listFensi = dianpuFavourDao.listsFensiAll(map);

                    List<MemberVO> list = memberDao.listAllFensi(map);

                    if(list != null){
                        for(MemberVO memberVO:list) {
                            if(memberVO != null && !StringUtil.isNullOrEmpty(memberVO.getPushId())&& !StringUtil.isNullOrEmpty(memberVO.getChannelId())){
                                pushMsg(memberVO.getPushId(), memberVO.getDeviceType(), notice.getTitle() , notice.getId());
                            }
                        }
                    }

                }
            }
        }
        return 200;
    }

    private void pushMsg(final String pushId, final String type, final String content, final String noticeId){
        CommonUtil.getThreadPool().execute(new Runnable() {
            @Override
            public void run() {
                ChannelKeyPair pair = null;
                if (type.equals("3")) {
                    pair = new ChannelKeyPair(Constants.API_KEY, Constants.SECRET_KEY);
                } else {
                    pair = new ChannelKeyPair(Constants.IOS_API_KEY, Constants.IOS_SECRET_KEY);
                }

                // 2. 创建BaiduChannelClient对象实例
                BaiduChannelClient channelClient = new BaiduChannelClient(pair);
                // 3. 若要了解交互细节，请注册YunLogHandler类
                channelClient.setChannelLogHandler(new YunLogHandler() {
                    @Override
                    public void onHandle(YunLogEvent event) {
                    }
                });
                try {
                    // 4. 创建请求类对象
                    // 手机端的ChannelId， 手机端的UserId， 先用1111111111111代替，用户需替换为自己的
                    PushUnicastMessageRequest request = new PushUnicastMessageRequest();
                    request.setDeviceType(Integer.parseInt(type));
                    if (type.equals("4")) {//如果是苹果手机端要设置这个
                        request.setDeployStatus(Constants.IOS_TYPE);
                    }
//            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android  4:ios 5:wp
//            request.setChannelId(Long.parseLong(pushId));
//            request.setChannelId(5100663888284228047L);
                    request.setUserId(pushId);

                    request.setMessageType(1);
                    request.setMessage("{\"title\":\"公告\",\"description\":\"" + content + "\",\"custom_content\": {\"type\":\"1\" , \"noticeId\":\""+noticeId+"\"}}");

//                    logger.info("开始调用百度推送接口");
                    // 5. 调用pushMessage接口
                    PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

//                    logger.info("推送成功----"+response.getSuccessAmount());
                    // 6. 认证推送成功
//                    System.out.println("push amount : " + response.getSuccessAmount());

                } catch (ChannelClientException e) {
                    // 处理客户端错误异常
                    e.printStackTrace();
//                    logger.info("处理客户端异常"+e.getMessage());
                } catch (ChannelServerException e) {
                    // 处理服务端错误异常
//                    System.out.println(String.format(
//                            "request_id: %d, error_code: %d, error_message: %s",
//                            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
                }
            }
        });
    }


}
