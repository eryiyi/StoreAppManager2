package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.CountRecordDao;
import com.liangxunwang.unimanager.dao.LxbankApplyDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.model.lxBankApply;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.mvc.vo.lxBankApplyVo;
import com.liangxunwang.unimanager.query.LxBankApplyQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/3/3.
 * 提现申请
 */
@Service("lxBankApplyService")
public class LxBankApplyService implements ListService,SaveService,UpdateService,ExecuteService {
    @Autowired
    @Qualifier("lxbankApplyDao")
    private LxbankApplyDao lxbankApplyDao;

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Override
    public Object list(Object object) throws ServiceException {
        LxBankApplyQuery query = (LxBankApplyQuery) object;
        Map<String, Object> map = new HashMap<String, Object>();
        int index = (query.getIndex() - 1) * query.getSize();
        int size = query.getSize();

        map.put("index", index);
        map.put("size", size);

        if(!StringUtil.isNullOrEmpty(query.getEmp_id())){
            map.put("emp_id", query.getEmp_id());
        }
        if(!StringUtil.isNullOrEmpty(query.getIs_check())){
            map.put("is_check", query.getIs_check());
        }
        if(!StringUtil.isNullOrEmpty(query.getPhoneNumber())){
            map.put("phoneNumber", query.getPhoneNumber());
        }
        if(!StringUtil.isNullOrEmpty(query.getKeyWords())){
            map.put("keyWords", query.getKeyWords());
        }
        List<lxBankApplyVo> lists = lxbankApplyDao.lists(map);
        if(lists != null){
            for(lxBankApplyVo vo : lists){
                if (!StringUtil.isNullOrEmpty(vo.getEmp_cover())) {
                    if (vo.getEmp_cover().startsWith("upload")) {
                        vo.setEmp_cover(Constants.URL + vo.getEmp_cover());
                    }else {
                        vo.setEmp_cover(Constants.QINIU_URL + vo.getEmp_cover());
                    }
                }
            }
        }
        long count = lxbankApplyDao.count(map);
        return new Object[]{lists, count};
    }

    @Autowired
    @Qualifier("countRecordDao")
    private CountRecordDao countRecordDao;

    @Override
    public Object save(Object object) throws ServiceException {
        lxBankApply lxBankApply = (lxBankApply) object;
        if(lxBankApply == null){
            throw new ServiceException("no_result");
        }
        //先查询该用户是否存在
        MemberVO memberVO = memberDao.findInfoById(lxBankApply.getEmp_id());//提现人
        if(memberVO != null){
            lxBankApply.setLx_bank_apply_id(UUIDFactory.random());
            lxBankApply.setDateline_apply(System.currentTimeMillis() + "");
            lxbankApplyDao.save(lxBankApply);
            //提现申请成功
            //减去相对应的积分
            countDao.updateScoreDelete(lxBankApply.getEmp_id(), lxBankApply.getLx_bank_apply_count());
            //添加积分变动记录
            CountRecord countRecord = new CountRecord();
            countRecord.setLx_count_record_id(UUIDFactory.random());
            countRecord.setEmp_id(memberVO.getEmpId());
            countRecord.setLx_count_record_count("-" + lxBankApply.getLx_bank_apply_count());
            countRecord.setLx_count_record_cont(memberVO.getEmpName() + "(" + memberVO.getEmpMobile() + ")提现申请" + lxBankApply.getLx_bank_apply_count() + "分");
            countRecord.setDateline(System.currentTimeMillis()+"");
            countRecordDao.save(countRecord);
        }else {
            throw new ServiceException("no_emp");
        }

        return null;
    }

    @Override
    public Object update(Object object) {
        lxBankApply lxBankApply = (lxBankApply) object;
        //根据ID查询是否已经审核
        lxBankApplyVo lxBankApplyVo = lxbankApplyDao.findById(lxBankApply.getLx_bank_apply_id());
        if(lxBankApplyVo.getIs_check().equals("1") || lxBankApplyVo.getIs_check().equals("2")){
            throw new ServiceException("has_check");
        }else{
            if("1".equals(lxBankApply.getIs_check())){
                lxBankApply.setDateline_done(System.currentTimeMillis()+"");
                lxbankApplyDao.update(lxBankApply);
            }else if("2".equals(lxBankApply.getIs_check())){

                lxBankApply.setDateline_done(System.currentTimeMillis()+"");
                lxbankApplyDao.update(lxBankApply);

                //退还积分
                countDao.updateScore(lxBankApplyVo.getEmp_id(), lxBankApplyVo.getLx_bank_apply_count());
                //添加积分变动记录
                CountRecord countRecord = new CountRecord();
                countRecord.setLx_count_record_id(UUIDFactory.random());
                countRecord.setEmp_id(lxBankApplyVo.getEmp_id());
                countRecord.setLx_count_record_count("+" + lxBankApplyVo.getLx_bank_apply_count());
                countRecord.setLx_count_record_cont(lxBankApplyVo.getEmp_name() + "(" + lxBankApplyVo.getEmp_mobile() + ")提现申请被拒绝" + lxBankApplyVo.getLx_bank_apply_count() + "分");
                countRecord.setDateline(System.currentTimeMillis()+"");
                countRecordDao.save(countRecord);
            }

        }

        return null;
    }

    @Override
    public Object execute(Object object) throws ServiceException, Exception {
        return lxbankApplyDao.findById((String) object);
    }
}
