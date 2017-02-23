package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.CountRecordDao;
import com.liangxunwang.unimanager.dao.LxDxkLevelDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.model.CountRecord;
import com.liangxunwang.unimanager.model.LxDxkLevel;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
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
 * Created by zhl on 2015/3/3.
 */
@Service("dxkChongzhiReturnCountService")
public class DxkChongzhiReturnCountService implements ExecuteService {

    @Autowired
    @Qualifier("memberDao")
    private  MemberDao memberDao;

    @Autowired
    @Qualifier("lxDxkLevelDao")
    private LxDxkLevelDao lxDxkLevelDao;

    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Autowired
    @Qualifier("countRecordDao")
    private CountRecordDao countRecordDao;

    @Override
    public Object execute(Object object) throws ServiceException {
        String[] arr = (String[]) object;
        String emp_id = arr[0];//会员ID
        String lx_consumption_count = arr[1];//消费金额
        String lx_dxk_level_id = arr[2];//会员的定向卡等级
        String emp_name = arr[3];//会员昵称
        String emp_id_up = arr[4];//会员上级的ID
        Member member = memberDao.findById(emp_id);
//        Member memberUp = memberDao.findById(emp_id_up);
        //1.查询上级--一串所有的
        lists.clear();
        List<MemberVO> lists = getListMemberVo(emp_id_up);
        if(lists != null){
            List<MemberVO> listsPt = new ArrayList<MemberVO>();//放普通会员
            List<MemberVO> listsSD = new ArrayList<MemberVO>();//放市级代理
            List<MemberVO> listsDQ = new ArrayList<MemberVO>();//放大区代理
            List<MemberVO> listsZS = new ArrayList<MemberVO>();//放招商部

            for(MemberVO memberVO:lists){
                if(listsZS.size()>0){
                    //说明已经返到招商部了，停止
                    return 200;//成功
                }else {
                    if("1".equals(memberVO.getIs_card_emp())){
                        //说明是定向卡会员
                        //根据会员定向卡级别查询他的返积分利率
                        LxDxkLevel lxDxkLevel = lxDxkLevelDao.findById(memberVO.getLx_dxk_level_id()==null?"":memberVO.getLx_dxk_level_id());
                        if(lxDxkLevel != null){
                            switch (Integer.parseInt(lxDxkLevel.getLx_dxk_nick())){
                                case 1:
                                case 2:
                                case 3:
                                {
                                    //普通定向卡会员
                                    if(listsPt.size()<3){
                                        //普通定向卡会员少于3个 可以继续返
                                        String lx_dxk_rate = lxDxkLevel.getLx_dxk_rate();//利率
                                        if (!StringUtil.isNullOrEmpty(lx_dxk_rate)) {
                                            Double jifenCount = Double.parseDouble(lx_consumption_count) * (Double.parseDouble(lx_dxk_rate) * 0.01);//增加的积分
                                            //增加他的积分
                                            countDao.updateScore(memberVO.getEmpId(), String.valueOf(jifenCount));
                                            //添加积分变动记录
                                            CountRecord countRecord = new CountRecord();
                                            countRecord.setLx_count_record_id(UUIDFactory.random());
                                            countRecord.setDateline(System.currentTimeMillis()+"");
                                            countRecord.setEmp_id(memberVO.getEmpId());
                                            countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                            countRecord.setLx_count_record_cont(emp_name + "("+member.getEmpMobile()+")定向卡充值" + lx_consumption_count + "元");
                                            countRecordDao.save(countRecord);
                                        }
                                        //返利的加入集合
                                        listsPt.add(memberVO);
                                    }
                                }
                                break;
                                case 4:
                                {
                                    //市级定向卡会员
                                    if(listsSD.size()<1){
                                        //市级定向卡会员少于1个 可以继续返
                                        String lx_dxk_rate = lxDxkLevel.getLx_dxk_rate();//利率
                                        if (!StringUtil.isNullOrEmpty(lx_dxk_rate)) {
                                            Double jifenCount = Double.parseDouble(lx_consumption_count) * (Double.parseDouble(lx_dxk_rate) * 0.01);//增加的积分
                                            //增加他的积分
                                            countDao.updateScore(memberVO.getEmpId(), String.valueOf(jifenCount));
                                            //添加积分变动记录
                                            CountRecord countRecord = new CountRecord();
                                            countRecord.setLx_count_record_id(UUIDFactory.random());
                                            countRecord.setDateline(System.currentTimeMillis()+"");
                                            countRecord.setEmp_id(memberVO.getEmpId());
                                            countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                            countRecord.setLx_count_record_cont(emp_name + "("+member.getEmpMobile()+")定向卡充值" + lx_consumption_count + "元");
                                            countRecordDao.save(countRecord);
                                        }
                                        //返利的加入集合
                                        listsSD.add(memberVO);
                                    }
                                }
                                break;
                                case 5:
                                {
                                    if(listsDQ.size()<1){
                                        //大区经理少于1个 可以继续返
                                        String lx_dxk_rate = lxDxkLevel.getLx_dxk_rate();//利率
                                        if (!StringUtil.isNullOrEmpty(lx_dxk_rate)) {
                                            Double jifenCount = Double.parseDouble(lx_consumption_count) * (Double.parseDouble(lx_dxk_rate) * 0.01);//增加的积分
                                            //增加他的积分
                                            countDao.updateScore(memberVO.getEmpId(), String.valueOf(jifenCount));
                                            //添加积分变动记录
                                            CountRecord countRecord = new CountRecord();
                                            countRecord.setLx_count_record_id(UUIDFactory.random());
                                            countRecord.setDateline(System.currentTimeMillis()+"");
                                            countRecord.setEmp_id(memberVO.getEmpId());
                                            countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                            countRecord.setLx_count_record_cont(emp_name + "("+member.getEmpMobile()+")定向卡充值" + lx_consumption_count + "元");
                                            countRecordDao.save(countRecord);
                                        }
                                        //返利的加入集合
                                        listsDQ.add(memberVO);
                                    }
                                }
                                break;
                                case 6:
                                {
                                    if(listsZS.size()<1){
                                        //招商部少于1个 可以继续返
                                        String lx_dxk_rate = lxDxkLevel.getLx_dxk_rate();//利率
                                        if (!StringUtil.isNullOrEmpty(lx_dxk_rate)) {
                                            Double jifenCount = Double.parseDouble(lx_consumption_count) * (Double.parseDouble(lx_dxk_rate) * 0.01);//增加的积分
                                            //增加他的积分
                                            countDao.updateScore(memberVO.getEmpId(), String.valueOf(jifenCount));
                                            //添加积分变动记录
                                            CountRecord countRecord = new CountRecord();
                                            countRecord.setLx_count_record_id(UUIDFactory.random());
                                            countRecord.setDateline(System.currentTimeMillis()+"");
                                            countRecord.setEmp_id(memberVO.getEmpId());
                                            countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                            countRecord.setLx_count_record_cont(emp_name + "("+member.getEmpMobile()+")定向卡充值" + lx_consumption_count + "元");
                                            countRecordDao.save(countRecord);
                                        }
                                        //返利的加入集合
                                        listsZS.add(memberVO);
                                        return 200;
                                    }
                                }
                                break;
                            }
                        }else{
                            //说明是普通定向卡会员等级  最普通的  没有到期日
                            Map<String, Object> map = new HashMap<String, Object>();
                            List<LxDxkLevel> lxDxkLevels = lxDxkLevelDao.lists(map);
                            LxDxkLevel lxDxkLevelTmp = null;

                            //普通定向卡会员
                            if(listsPt.size()<3){
                                if(lxDxkLevels != null){
                                    for(LxDxkLevel lxDxkLevel1:lxDxkLevels){
                                        if(listsPt.size() == 0){
                                            //说明一个也没有
                                            if("1".equals(lxDxkLevel1.getLx_dxk_nick())){
                                                //最低级别的定向卡会员 找的就是它
                                                lxDxkLevelTmp = lxDxkLevel1;
                                                break;
                                            }
                                        }
                                        if(listsPt.size() == 1){
                                            //说明有一个了
                                            if("2".equals(lxDxkLevel1.getLx_dxk_nick())){
                                                lxDxkLevelTmp = lxDxkLevel1;
                                                break;
                                            }
                                        }
                                        if(listsPt.size() == 2){
                                            //说明有两个了
                                            if("3".equals(lxDxkLevel1.getLx_dxk_nick())){
                                                lxDxkLevelTmp = lxDxkLevel1;
                                                break;
                                            }
                                        }
                                    }
                                }

                                //普通定向卡会员少于3个 可以继续返
                                String lx_dxk_rate = lxDxkLevelTmp.getLx_dxk_rate();//利率
                                if (!StringUtil.isNullOrEmpty(lx_dxk_rate)) {
                                    Double jifenCount = Double.parseDouble(lx_consumption_count) * (Double.parseDouble(lx_dxk_rate) * 0.01);//增加的积分
                                    //增加他的积分
                                    countDao.updateScore(memberVO.getEmpId(), String.valueOf(jifenCount));
                                    //添加积分变动记录
                                    CountRecord countRecord = new CountRecord();
                                    countRecord.setLx_count_record_id(UUIDFactory.random());
                                    countRecord.setDateline(System.currentTimeMillis()+"");
                                    countRecord.setEmp_id(memberVO.getEmpId());
                                    countRecord.setLx_count_record_count("+" + String.valueOf(jifenCount));
                                    countRecord.setLx_count_record_cont(emp_name + "("+member.getEmpMobile()+")定向卡充值" + lx_consumption_count + "元");
                                    countRecordDao.save(countRecord);
                                }
                                //返利的加入集合
                                listsPt.add(memberVO);
                            }
                        }
                    }
                }
            }
        }
        return null;
    }

    static int i = 0;
    List<MemberVO> lists = new ArrayList<MemberVO>();
    //根据会员ID查询他的上级所有关系-知道找到招商部
     List<MemberVO> getListMemberVo(String emp_id){
        //emp_id： A充值的话，emp_id就是A的上级B
        MemberVO memberVO = (MemberVO) memberDao.findInfoById(emp_id);
        lists.add(memberVO);
        if(!memberVO.getLx_dxk_level_id().equals(Constants.TOP_DXK_LEVEL)){
            getListMemberVo(memberVO.getEmp_up());
        }else {
            return lists;
        }
        return lists;
    }


}
