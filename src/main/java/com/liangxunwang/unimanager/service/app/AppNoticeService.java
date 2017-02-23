package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.baidu.channel.auth.ChannelKeyPair;
import com.liangxunwang.unimanager.baidu.channel.client.BaiduChannelClient;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelClientException;
import com.liangxunwang.unimanager.baidu.channel.exception.ChannelServerException;
import com.liangxunwang.unimanager.baidu.channel.model.PushBroadcastMessageRequest;
import com.liangxunwang.unimanager.baidu.channel.model.PushBroadcastMessageResponse;
import com.liangxunwang.unimanager.baidu.log.YunLogEvent;
import com.liangxunwang.unimanager.baidu.log.YunLogHandler;
import com.liangxunwang.unimanager.dao.AdminDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.NoticeDao;
import com.liangxunwang.unimanager.model.Notice;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.NoticeQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/5.
 */
@Service("appNoticeService")
public class AppNoticeService implements ListService{
    @Autowired
    @Qualifier("noticeDao")
    private NoticeDao noticeDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("adminDao")
    private AdminDao adminDao;

    @Override
    public Object list(Object object) throws ServiceException {
        NoticeQuery query = (NoticeQuery) object;
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();
        Map<String,Object> map = new HashMap<String, Object>();
        map.put("index", index);
        map.put("size", size);
        String[] schoolIds = null;
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            //根据用户ID查询他的上级列表
            if(lists != null){
                lists.clear();
            }
            List<MemberVO> listUps = getListUps(query.getEmp_id());
            Map<String, Object> mapEmps = new HashMap<String, Object>();
            if(listUps != null){
                String[] empids = new String[listUps.size()];//用户emp_id数组
                for(int i=0;i<listUps.size();i++){
                    empids[i] = listUps.get(i).getEmpId();
                }
                mapEmps.put("empids", empids);
            }
            List<AdminVO> adminVOs = adminDao.listsEmpIds(mapEmps);//管理员列表
            if(adminVOs != null){
                schoolIds = new String[adminVOs.size()+1];
                for(int i=0;i<adminVOs.size();i++){
                    schoolIds[i] = adminVOs.get(i).getId();
                }
                schoolIds[adminVOs.size()] = "0";
                map.put("schoolIds", schoolIds);
            }
        }else {
            schoolIds = new String[1];
            schoolIds[0] = "0";
        }

        List<Notice> lists = noticeDao.listUp(map);
        return lists;
    }

    List<MemberVO> lists = new ArrayList<MemberVO>();
    List<MemberVO> getListUps(String emp_id){
        MemberVO memberVOUp = memberDao.findInfoById(emp_id);
        lists.add(memberVOUp);
        if(memberVOUp.getEmpId().equals(Constants.MOBILE_UP_DEFAULT_id)){
            //说明是顶级会员了
            return lists;
        }else{
            getListUps(memberVOUp.getEmp_up());
        }
        return lists;
    }

}
