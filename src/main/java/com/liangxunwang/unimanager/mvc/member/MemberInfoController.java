package com.liangxunwang.unimanager.mvc.member;

import com.liangxunwang.unimanager.model.FensiCount;
import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import com.liangxunwang.unimanager.query.LxConsumptionQuery;
import com.liangxunwang.unimanager.query.MemberQuery;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.Page;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by zhl on 2015/2/8.
 */
@Controller
public class MemberInfoController extends ControllerConstants {
    @Autowired
    @Qualifier("memberInfoService")
    private ExecuteService listMemberInfoService;

    @Autowired
    @Qualifier("memberInfoService")
    private FindService findMemberService;

    @Autowired
    @Qualifier("memberInfoService")
    private UpdateService updateMemberService;

    @Autowired
    @Qualifier("memberInfoService")
    private ListService memberInfoListService;

    @RequestMapping(value = "/getMemberInfoById", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getMemberInfoById(String empId){
        try {
            MemberVO memberVO = (MemberVO) findMemberService.findById(empId);
            DataTip tip = new DataTip();
            tip.setData(memberVO);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping("/updatePushId")
    @ResponseBody
    public String updatePushId(@RequestParam String id, @RequestParam String pushId, @RequestParam String type, @RequestParam String channelId){
        if (StringUtil.isNullOrEmpty(id) || StringUtil.isNullOrEmpty(pushId) || StringUtil.isNullOrEmpty(type)){
            return toJSONString(ERROR_1);
        }
        Object[] params = new Object[]{id, pushId, type,channelId};
        try {
            updateMemberService.update(params);
            return toJSONString(SUCCESS);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);

        }
    }

    @RequestMapping(value = "/searchMember", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String searchMember(MemberQuery query, Page page){
        try {
            query.setIndex(page.getPage() == 0 ? 1 : page.getPage());
            query.setSize(query.getSize() == 0 ? page.getDefaultSize() : query.getSize());
            Object[] params = new Object[]{query, page.getPage()};
            List<Member> list = (List<Member>) memberInfoListService.list(params);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }

    @RequestMapping(value = "/listInviteMemberInfo", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getInviteMemberInfo(String hxUserNames){
        try {
            Object[] params = new Object[]{hxUserNames};
            List<Member> list = (List<Member>) listMemberInfoService.execute(params);
            DataTip tip = new DataTip();
            tip.setData(list);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            return toJSONString(ERROR_1);
        }
    }


    @Autowired
    @Qualifier("appEmpService")
    private ExecuteService appEmpService;


    @RequestMapping(value = "/getMemberByMobile", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String getMemberByMobile(String mm_emp_mobile) throws Exception {
        try {
            //查看该会员信息
            MemberVO empVO = (MemberVO) appEmpService.execute(mm_emp_mobile);
            if(empVO != null){
                //说明该手机号已经注册了
                DataTip tip = new DataTip();
                tip.setData(empVO);
                return toJSONString(tip);
            }else {
                return toJSONString(ERROR_1);
            }
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("memberFindByIdService")
    private FindService memberFindByIdServiceExe;

    @Autowired
    @Qualifier("memberInfoService2")
    private ExecuteService memberInfoService2Exe;

    //查询我的粉丝  我推荐的人列表
    @RequestMapping(value = "/listMineEmps", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String listMineEmps(MemberQuery query,Page page){
        if(StringUtil.isNullOrEmpty(query.getEmp_id())){
            return toJSONString(ERROR_2);
        }
        try {
            query.setIndex(page.getPage()==0?1:page.getPage());
            query.setSize(query.getSize()==0?page.getDefaultSize():query.getSize());
            //现根据用户EmpId查询用户信息
            MemberVO memberVO = (MemberVO) memberFindByIdServiceExe.findById(query.getEmp_id());
            if("0".equals(memberVO.getIs_card_emp())){
                //不是定向卡会员
                List<MemberVO> list = (List<MemberVO>) memberInfoService2Exe.execute(query);
                DataTip tip = new DataTip();
                tip.setData(list);
                return toJSONString(tip);
            }else{
                //定向卡会员
                List<Member> list = (List<Member>) listMemberInfoService.execute(query);
                DataTip tip = new DataTip();
                tip.setData(list);
                return toJSONString(tip);
            }

        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            e.printStackTrace();
            return toJSONString(ERROR_1);
        }
    }



    @Autowired
    @Qualifier("fensiService")
    private ExecuteService fensiServiceExe;


    @RequestMapping(value = "/MineFensiCount", produces = "text/plain;charset=UTF-8;")
    @ResponseBody
    public String MineFensiCount(String emp_id){
        if(StringUtil.isNullOrEmpty(emp_id)){
            return toJSONString(ERROR_2);
        }
        try {
            List<MemberVO> list = ( List<MemberVO> ) fensiServiceExe.execute(emp_id);
            FensiCount fensiCount = new FensiCount();
            fensiCount.setCountFensi(String.valueOf(list.size()));
            DataTip tip = new DataTip();
            tip.setData(fensiCount);
            return toJSONString(tip);
        }catch (ServiceException e){
            return toJSONString(ERROR_1);
        } catch (Exception e) {
            e.printStackTrace();
            return toJSONString(ERROR_1);
        }
    }


}
