package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.DianpuFavourDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.DianpuFavourVO;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.DianpuFavourQuery;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ListService;
import com.liangxunwang.unimanager.service.ServiceException;
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
 * Created by zhl on 2015/3/3.
 */
@Service("fensiService")
public class FensiService implements ListService,ExecuteService {

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Override
    public Object list(Object object) throws ServiceException {
        DianpuFavourQuery query = (DianpuFavourQuery) object;
        Map<String, Object> map1 = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map1.put("param1", query.getEmp_id());
        }
        memberDao.listAllFensi(map1);
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();


        String contion = " tmpLst,lx_emp where tmpLst.emp_id=lx_emp.emp_id";
        map.put("param1", query.getIndex() );
        map.put("param2", size);
        map.put("param3", "tmpLst.*,lx_emp.*");
        map.put("param4", contion);
        map.put("param5", "");
        map.put("param6", "@RECODE");

        List<MemberVO> lists = memberDao.findAll(map);
        if(lists != null){
            for(MemberVO member:lists){
                if (member.getEmpCover().startsWith("upload")) {
                    member.setEmpCover(Constants.URL + member.getEmpCover());
                }else {
                    member.setEmpCover(Constants.QINIU_URL + member.getEmpCover());
                }
            }
        }
//        long count = memberDao.countFensi(map);

        Map<String,Object> mapFensi = new HashMap<String, Object>();
        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            mapFensi.put("param1", query.getEmp_id());
        }
        List<MemberVO> listFensi =  memberDao.listAllFensi(mapFensi);
        long count = 0;
        if(listFensi != null){
            count = listFensi.size();
        }
        return new Object[]{lists, count};
    }


    @Override
    public Object execute(Object object) throws Exception {
        String emp_id = (String) object;
        Map<String, Object> map1 = new HashMap<String, Object>();
        Member member = memberDao.findById(emp_id);
        List<MemberVO>  list = new ArrayList<MemberVO>();
        if("0".equals(member.getIs_card_emp())){
            //不是定向卡会员
            if(!StringUtil.isNullOrEmpty(emp_id)){
                map1.put("emp_up", emp_id);
            }
            list =  memberDao.listAllFensiEmp(map1);
        }else{
            //定向卡会员
            if(!StringUtil.isNullOrEmpty(emp_id)){
                map1.put("param1", emp_id);
            }
            list =  memberDao.listAllFensi(map1);
        }

        return list;
    }
}
