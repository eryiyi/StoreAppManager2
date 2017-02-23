package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushUnicastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.ManagerEmpDao;
import com.liangxunwang.unimanager.dao.ManagerLogDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.ManagerEmp;
import com.liangxunwang.unimanager.model.ManagerLog;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.ManagerEmpVO;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.service.UpdateService;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/4.
 * 禁闭service
 */
@Service("managerEmpService")
public class ManagerEmpService implements SaveService, ListService, UpdateService{
    @Autowired
    @Qualifier("managerEmpDao")
    private ManagerEmpDao managerEmpDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("managerLogDao")
    private ManagerLogDao managerLogDao;

    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Override
    public Object save(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        ManagerEmp managerEmp = (ManagerEmp) params[0];
        String reportId = (String) params[1];

        managerEmp.setId(UUIDFactory.random());
        managerEmp.setDateline(System.currentTimeMillis() + "");
        managerEmp.setStart(DateUtil.getMs(managerEmp.getStart(), "yyyy-MM-dd") + "");
        managerEmp.setEnd(DateUtil.getMs(managerEmp.getEnd(), "yyyy-MM-dd") + "");

        managerEmpDao.save(managerEmp);
        memberDao.closeMember(managerEmp.getEmpId());

        //更改分数
        countDao.updateScore(managerEmp.getEmpIdTwo(), managerEmp.getCountJF());

        Member member = memberDao.findById(managerEmp.getEmpId());
        pushManagerEmp(member.getPushId(), "您的账户已被禁用", (Long.parseLong(managerEmp.getEnd())-Long.parseLong(managerEmp.getStart())));

        //增加管理员操作记录
        ManagerLog managerLog = new ManagerLog();
        managerLog.setId(UUIDFactory.random());
        managerLog.setDateline(System.currentTimeMillis()+"");
        managerLog.setEmpId(managerEmp.getAdmin());
        managerLog.setReportId(reportId);
        managerLog.setContent("将账号为:"+member.getEmpMobile()+",昵称为:"+member.getEmpName()+"关禁闭");
        managerLogDao.save(managerLog);

        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        String adminId = (String) object;
        List<ManagerEmpVO> list = managerEmpDao.list(adminId);
        for (ManagerEmpVO vo : list){
            vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
            vo.setStart(DateUtil.getDate(vo.getStart(), "yyyy-MM-dd"));
            vo.setEnd(DateUtil.getDate(vo.getEnd(), "yyyy-MM-dd"));
        }
        return list ;
    }

    /**
     * 解除禁闭
     * @param object
     * @return
     */
    @Override
    public Object update(Object object) {
        String empId = (String) object;
        if (empId.equals("update")){
            List<ManagerEmp> managerEmps = managerEmpDao.listOpen(System.currentTimeMillis()+"");
            if (managerEmps.size()>0){
                managerEmpDao.systemOpenEmp(System.currentTimeMillis()+"");

                String[] emps = new String[managerEmps.size()];
                for (int i=0; i<managerEmps.size(); i++){
                    ManagerEmp emp = managerEmps.get(i);
                    emps[i] = emp.getEmpId();
                }
                Map<String, Object> map = new HashMap<String, Object>();
                map.put("emps", emps);
                memberDao.systemOpenEmp(map);
            }
        }else {
            managerEmpDao.delete(empId);
            memberDao.openMember(empId);
        }
        return null;
    }

    private void pushManagerEmp(String pushId, String content, Long time){
        ChannelKeyPair pair = new ChannelKeyPair(Constants.API_KEY, Constants.SECRET_KEY);

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
            request.setDeviceType(3); // device_type => 1: web 2: pc 3:android  4:ios 5:wp
//            request.setChannelId(3671408368535076013L);
//            request.setUserId("792078116439786170");
            request.setUserId(pushId);

            request.setMessageType(1);
            request.setMessage("{\"title\":\"提醒\",\"description\":\""+content+"\",\"custom_content\": {\"type\":\"3\",\"time\":\""+String.valueOf(time)+"\"}}");


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
}
