package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/2/8.
 */
@Service("memberInfoService")
public class MemberInfoService implements ExecuteService, FindService, UpdateService, ListService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao ;

    @Override
    public Object execute(Object object) throws ServiceException {
        if (object instanceof MemberQuery) {
            MemberQuery query = (MemberQuery) object;
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();
//            Map<String, Object> map = new HashMap<String, Object>();
//            map.put("index", index);
//            map.put("size", size);
//            if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
//                map.put("emp_up", query.getEmp_id());
//            }
            Map<String, Object> map1 = new HashMap<String, Object>();
            if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
                map1.put("param1", query.getEmp_id());
            }
            memberDao.listAllFensi(map1);

            Map<String, Object> map = new HashMap<String, Object>();
//            int index = (query.getIndex() - 1) * query.getSize();
//            int size = query.getSize();

            String contion = " tmpLst,lx_emp where tmpLst.emp_id=lx_emp.emp_id";
            map.put("param1", query.getIndex() );
            map.put("param2", size);
            map.put("param3", "tmpLst.*,lx_emp.*");
            map.put("param4", contion);
            map.put("param5", "");
            map.put("param6", "@RECODE");

            List<MemberVO> list = memberDao.findAll(map);

            for (Member member : list) {
                if (member.getEmpCover().startsWith("upload")) {
                    member.setEmpCover(Constants.URL + member.getEmpCover());
                }else {
                    member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                }
            }
            return list;
        }else {
            Object[] params = (Object[]) object;
            String hxUsername = (String) params[0];
            Map<String, Object> map = new HashMap<String, Object>();
            List<Member> list = new ArrayList<Member>();
            if(hxUsername!=null){
                String[] strs = hxUsername.split(",");
                List<String> phones = new ArrayList<String>();
                for (int i = 0; i < strs.length; i++) {
                    phones.add(strs[i]);
                }
                map.put("hxUsername", strs);
               list = memberDao.listMemberInfoByUsername(map);
            }
            if(list != null && list.size()>0){
                for (Member member : list) {
                    if (member.getEmpCover().startsWith("upload")) {
                        member.setEmpCover(Constants.URL + member.getEmpCover());
                    }else {
                        member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                    }
                }
            }
            return list;
        }
    }

    @Override
    public Object findById(Object object) throws ServiceException {
        String empId = (String) object;
        MemberVO memberVO = memberDao.findInfoById(empId);
        if (memberVO.getEmpCover().startsWith("upload")) {
            memberVO.setEmpCover(Constants.URL + memberVO.getEmpCover());
        }else {
            memberVO.setEmpCover(Constants.QINIU_URL + memberVO.getEmpCover());
        }
        return memberVO;
    }

    @Override
    public Object update(Object object) {
        Object[] params = (Object[]) object;
        String id = (String) params[0];
        String pushId = (String) params[1];
        String type = (String) params[2];
        String channelId = (String) params[3];
        memberDao.updatePushId(id, pushId, type,channelId);
        return null;
    }

    @Override
    public Object list(Object object) throws ServiceException {
        Object[] params = (Object[]) object;

        MemberQuery query = (MemberQuery) params[0];
        Integer page = (Integer) params[1];
        if (page == 0) {
            return memberDao.searchMember(query.getKeyWords());
        }else {
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();
            Map<String,Object> map = new HashMap<String, Object>();
            map.put("index", index);
            map.put("size", size);
            if (!StringUtil.isNullOrEmpty(query.getKeyWords())){
                map.put("keyWords", query.getKeyWords());
            }
            List<Member> list = memberDao.searchMemberByPage(map);
            for (Member member : list){
                if (member.getEmpCover().startsWith("upload")) {
                    member.setEmpCover(Constants.URL + member.getEmpCover());
                }else {
                    member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                }
            }
            return list;
        }
    }
}
