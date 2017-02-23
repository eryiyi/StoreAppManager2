package com.liangxunwang.unimanager.service.member;

import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
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
@Service("memberInfoService2")
public class MemberInfoService2 implements ExecuteService {
    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao ;

    @Override
    public Object execute(Object object) throws ServiceException {
            MemberQuery query = (MemberQuery) object;
            int index = (query.getIndex() - 1) * query.getSize();
            int size = query.getSize();
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("index", index);
            map.put("size", size);
            if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
                map.put("emp_up", query.getEmp_id());
            }
            map.put("is_use", "0");

            List<MemberVO> list = memberDao.list(map);

            for (Member member : list) {
                if (member.getEmpCover().startsWith("upload")) {
                    member.setEmpCover(Constants.URL + member.getEmpCover());
                }else {
                    member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                }
            }
            return list;

    }

}
