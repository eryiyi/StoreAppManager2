package com.liangxunwang.unimanager.service.member;

import com.fasterxml.jackson.databind.node.JsonNodeFactory;
import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.dao.MemberDao;
import com.liangxunwang.unimanager.dao.MinePackageDao;
import com.liangxunwang.unimanager.huanxin.comm.HxConstants;
import com.liangxunwang.unimanager.huanxin.comm.Roles;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.utils.ChatUtils;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.vo.ClientSecretCredential;
import com.liangxunwang.unimanager.huanxin.comm.httpclient.vo.Credential;
import com.liangxunwang.unimanager.model.Count;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.MinePackage;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.service.SaveService;
import com.liangxunwang.unimanager.service.ServiceException;
import com.liangxunwang.unimanager.util.Constants;
import com.liangxunwang.unimanager.util.MD5Util;
import com.liangxunwang.unimanager.util.StringUtil;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Created by zhl on 2015/1/29.
 */
@Service("memberRegisterService")
public class MemberRegisterService implements SaveService, ExecuteService {
    private static Logger LOGGER = LoggerFactory.getLogger(MemberRegisterService.class);
    private static JsonNodeFactory factory = new JsonNodeFactory(false);
    private static Credential credential = new ClientSecretCredential(HxConstants.APP_CLIENT_ID,
            HxConstants.APP_CLIENT_SECRET, Roles.USER_ROLE_APPADMIN);

    @Autowired
    @Qualifier("memberDao")
    private MemberDao memberDao;

    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Autowired
    @Qualifier("minePackageDao")
    private MinePackageDao minePackageDao;

    @Override
    public Object save(Object object) {
        Member member = (Member) object;
        member.setEmpId(UUIDFactory.random());//设置ID
        member.setDateline(System.currentTimeMillis() + "");//时间戳
        member.setEmpCover(Constants.PHOTOURLS[new Random().nextInt(61)]);//头像
        if(!StringUtil.isNullOrEmpty(member.getEmpPass())){
            member.setEmpPass(new MD5Util().getMD5ofStr(member.getEmpPass()));//密码加密
        }
        member.setIsUse("0");//默认没有禁用
        member.setEmpSex("0");//默认女
        member.setHxUsername(member.getDateline() + member.getEmpMobile());
        member.setLevel_id(Constants.DEFAULT_LEVEL);//默认青铜会员
        if(StringUtil.isNullOrEmpty(member.getEmp_up_mobile())){
            member.setEmp_up_mobile("10000000000");
        }
        String emp_number = "";
        try {
            Member checkMember = memberDao.findByMobile(member.getEmpMobile());
            if (checkMember != null){
                throw new ServiceException("MobileIsUse");
            }else {
                //生成账号相关
                emp_number = StringUtil.getStringRandom();
                Member member1 = memberDao.findByNumber(emp_number);
                if(member1 != null){
                    //说明改账号存在了
                    emp_number = StringUtil.getStringRandom();
                    Member member2 = memberDao.findByNumber(emp_number);
                    if(member2 != null){
                        //说明该账号存在了
                        //todo
                    }else {
                        member.setEmp_number(emp_number);
                    }
                }else{
                    member.setEmp_number(emp_number);
                }
                //查询上级
                Member memberUp = memberDao.findByMobile(member.getEmp_up_mobile());
                if(memberUp != null){
                    //说明有上级
                    member.setEmp_up(memberUp.getEmpId());
                }else{
                    member.setEmp_up(Constants.MOBILE_UP_DEFAULT_id);
                }

                memberDao.save(member);

                //钱包表
                MinePackage minePackage = new MinePackage();
                minePackage.setPackage_id(UUIDFactory.random());
                minePackage.setEmp_id(member.getEmpId());
                minePackage.setPackage_money("0");
                minePackageDao.save(minePackage);

            }
            //积分表
            Count count = new Count();
            count.setId(UUIDFactory.random());
            count.setEmpId(member.getEmpId());
            count.setCount(String.valueOf(0));
            countDao.save(count);
        }catch (Exception e){
            String msg = e.getMessage();
            if (msg.equals("MobileIsUse")){
                throw new ServiceException(Constants.HAS_EXISTS);
            }
            throw new ServiceException(Constants.SAVE_ERROR);
        }
        Boolean suc= ChatUtils.register(member.getEmpId(), "111111");
//        if(suc) {
//            College college=collegeDao.getGroupId(member.getSchoolId());
//            GroupUtils.addGroup(college.getGroupId(), member.getDateline() + member.getEmpMobile());
//        }else {
//            throw new ServiceException(Constants.HX_ERROR);
//        }
        return member;
    }

    /**
     * 校验昵称的唯一性
     * @param object
     * @return
     * @throws ServiceException
     */
    @Override
    public Object execute(Object object) throws ServiceException {
        Object[] params = (Object[]) object;
        String nickName = (String) params[0];
        String empId = (String) params[1];
        if (!StringUtil.isNullOrEmpty(nickName)) {
            Map<String,Object> map = new HashMap<String, Object>();
            if (!StringUtil.isNullOrEmpty(empId)) {
                map.put("empId", empId);
            }
            map.put("nickName", nickName);
            Member member = memberDao.findMemberByNickName(map);
            if (!StringUtil.isNullOrEmpty(empId)){
                if (member != null){
                    throw new ServiceException(Constants.HAS_EXISTS);
                }
            }
            if (member != null) {
                throw new ServiceException(Constants.HAS_EXISTS);
            }
        }
        return null;
    }



}
