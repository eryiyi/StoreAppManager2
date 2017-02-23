package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.GoodsCommentDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.RelateDao;
import com.liangxunwang.unimanager.model.GoodsComment;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.Relate;
import com.liangxunwang.unimanager.query.GoodsCommentQuery;
import com.liangxunwang.unimanager.service.DeleteService;
import com.liangxunwang.unimanager.service.ListService;
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
@Service("goodsCommentService")
public class GoodsCommentService implements SaveService, ListService,DeleteService{

    @Autowired
    @Qualifier("goodsCommentDao")
    private GoodsCommentDao goodsCommentDao;

    @Autowired
    @Qualifier("relateDao")
    private RelateDao relateDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object save(Object object) throws ServiceException {
        GoodsComment comment = (GoodsComment) object;
        comment.setId(UUIDFactory.random());
        comment.setDateline(System.currentTimeMillis()+"");
        if(StringUtil.isNullOrEmpty(comment.getGoodsId())){
            comment.setGoodsId("0");
        }
        goodsCommentDao.save(comment);

        Member member = memberDao.findById(comment.getEmpId());

        if(member != null){
            if (!StringUtil.isNullOrEmpty(comment.getFplid()) && !"".equals(comment.getFplid()) && !"0".equals(comment.getFplid())){
                //有父评论
                if (!comment.getEmpId().equals(comment.getGoodsEmpId())){
                    //给商品所有者
                    Relate relate = new Relate();
                    relate.setId(UUIDFactory.random());
                    relate.setDateline(System.currentTimeMillis()+"");
                    relate.setGoodsId(comment.getGoodsId());
                    relate.setTypeId("1");
                    relate.setEmpId(comment.getEmpId());
                    relate.setEmpTwoId(comment.getGoodsEmpId());
                    relate.setCont(member.getEmpName()+" 评论了你的商品");
                    relateDao.save(relate);
                }
                if (!comment.getFempId().equals(comment.getGoodsEmpId()) && !comment.getFempId().equals(comment.getEmpId())){
                    //给父评论人的
                    Relate relate = new Relate();
                    relate.setId(UUIDFactory.random());
                    relate.setDateline(System.currentTimeMillis()+"");
                    relate.setGoodsId(comment.getGoodsId());
                    relate.setTypeId("1");
                    relate.setEmpId(comment.getEmpId());
                    relate.setEmpTwoId(comment.getFempId());
                    relate.setCont(member.getEmpName()+" 回复了你的商品评论");
                    relateDao.save(relate);
                }

            }else {
                //没有父评论
                if (!comment.getEmpId().equals(comment.getGoodsEmpId())){
                    Relate relate = new Relate();
                    relate.setId(UUIDFactory.random());
                    relate.setDateline(System.currentTimeMillis()+"");
                    relate.setGoodsId(comment.getGoodsId());
                    relate.setTypeId("1");
                    relate.setEmpId(comment.getEmpId());
                    relate.setEmpTwoId(comment.getGoodsEmpId());
                    relate.setCont(member.getEmpName()+" 评论了你的商品");
                    relateDao.save(relate);

                }

            }
            Member pushMember =  memberDao.findById(comment.getGoodsEmpId());
            if(pushMember != null){
//                String pushId =pushMember.getPushId();
//                String type = pushMember.getDeviceType();
//
//                if (!StringUtil.isNullOrEmpty(comment.getFplid()) && !"".equals(comment.getFplid()) && !"0".equals(comment.getFplid())){
//                    //有父评论
//                    if (!comment.getGoodsEmpId().equals(comment.getEmpId())) {
//                        //动态所有者发通知
////                    pushZan(pushId,type, member.getEmpName() + " 评论了你的商品");
//                    }
//
//                    if (!comment.getFempId().equals(member.getEmpId()) && !comment.getFempId().equals(comment.getGoodsEmpId())) {
//                        //向父评论者发通知
//                        Member pushMember1 =  memberDao.findById(comment.getFempId());
//                        String pushId1 =pushMember1.getPushId();
//                        String type1 = pushMember1.getDeviceType();
//
////                    pushZan(pushId1, type1, member.getEmpName() + " 回复了你的商品评论");
//                    }
//                }else {
//                    //没有父评论
//                    if (!comment.getGoodsEmpId().equals(comment.getEmpId())) {
////                    pushZan(pushId,type, member.getEmpName() + " 评论了你的商品");
//                    }
//                }
            }

        }

//        relateDao.save(relate);
//
//        String pushId = memberDao.findById(comment.getGoodsEmpId()).getPushId();
//
//        if (!comment.getEmpId().equals(comment.getGoodsEmpId())) {
//            pushZan(pushId, member.getEmpName() + " 评论了你的商品");
//        }

        return comment;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        GoodsCommentQuery query = (GoodsCommentQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        if(!StringUtil.isNullOrEmpty(query.getGoodsId())){
            map.put("goods_id", query.getGoodsId());
        }
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        List<GoodsComment> list = goodsCommentDao.list(map);
        for (GoodsComment vo : list){
            if(!StringUtil.isNullOrEmpty(vo.getCover())){
                if (vo.getCover().startsWith("upload")) {
                    vo.setCover(Constants.URL + vo.getCover());
                }else {
                    vo.setCover(Constants.QINIU_URL + vo.getCover());
                }
            }

            if(!StringUtil.isNullOrEmpty(vo.getComment_pic())){
                //处理图片URL链接
                StringBuffer buffer = new StringBuffer();
                String[] pics = new String[]{};
                if(vo!=null && vo.getComment_pic()!=null){
                    pics = vo.getComment_pic().split(",");
                }
                for (int i=0; i<pics.length; i++){
                    if (pics[i].startsWith("upload")) {
                        buffer.append(Constants.URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }else {
                        buffer.append(Constants.QINIU_URL + pics[i]);
                        if (i < pics.length - 1) {
                            buffer.append(",");
                        }
                    }
                }
                vo.setComment_pic(buffer.toString());
            }

            if(!StringUtil.isNullOrEmpty(vo.getDateline())){
                vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
            }

        }
        return list;
    }

    private void pushZan( final String pushId, final String type, final String content){
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
//                        System.out.println(event.getMessage());
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
//            request.setChannelId(3671408368535076013L);
//            request.setUserId("792078116439786170");
                    request.setUserId(pushId);

                    request.setMessageType(1);
                    request.setMessage("{\"title\":\"提醒\",\"description\":\"" + content + "\",\"custom_content\": {\"type\":\"2\"}}");


                    // 5. 调用pushMessage接口
                    PushUnicastMessageResponse response = channelClient.pushUnicastMessage(request);

                    // 6. 认证推送成功

                } catch (ChannelClientException e) {
                    // 处理客户端错误异常
                    e.printStackTrace();
                } catch (ChannelServerException e) {
                    // 处理服务端错误异常
                    System.out.println(String.format(
                            "request_id: %d, error_code: %d, error_message: %s",
                            e.getRequestId(), e.getErrorCode(), e.getErrorMsg()));
                }
            }
        });
    }


    @Override
    public Object delete(Object object) throws ServiceException {
        goodsCommentDao.deleteById((String) object);
        return 200;
    }
}
