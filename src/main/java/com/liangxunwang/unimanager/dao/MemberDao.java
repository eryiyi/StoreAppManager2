package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Member;
import com.liangxunwang.unimanager.mvc.vo.EmpDianpu;
import com.liangxunwang.unimanager.mvc.vo.MemberVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("memberDao")
public interface MemberDao {
    /**
     * 根据手机号查找会员
     * @param mobile
     * @return
     */
    MemberVO findByMobile(String mobile);

    Member findByPhone(String mobile);

    Member findById(String id);

    Member findEmpByManagerEmpId(String school_id);

    Member findByNumber(String emp_number);//根据账号查找

    Member findByCommentFplid(String fplId);

    MemberVO findInfoById(String empId);

    void save(Member member);

    /**
     * 根据查询条件查询  index:第几页 size：每页显示多少条数据
     * @param map
     * @return
     */
    List<MemberVO> list(Map<String,Object> map);

    List<MemberVO> listAllFensi(Map<String,Object> map);
    List<MemberVO> listAllFensiEmp(Map<String,Object> map);

    List<MemberVO> findAll(Map<String,Object> map);

    //根据关注的心情标签查询会员
    List<MemberVO> listByMoods(Map<String,Object> map);

    //查询店铺
    List<EmpDianpu> listDianPu(Map<String,Object> map);

    /**
     * 根据条件查询数量
     * @param map
     * @return
     */
    long count(Map<String,Object> map);

    long countFensi(Map<String,Object> map);

    /**
     * 根据会员ID，去更改是否为管理员
     * @param empId
     * @param flag
     */
    void updateAdmin(@Param(value = "empId")String empId, @Param(value = "flag")String flag);

    /**
     * 根据会员ID设为商家
     * @param empId
     * @param flag
     */
    void changeBusiness(@Param(value = "empId")String empId, @Param(value = "flag")String flag);

    /**
     * 根据昵称查找会员
     * @param map
     * @return
     */
    Member findMemberByNickName(Map<String, Object> map);

    /**
     * 关禁闭
     * @param empId
     */
    void closeMember(String empId);

    /**
     * 解除禁闭
     * @param empId
     */
    void openMember(String empId);

    /**
     * 修改资料
     * @param member
     */
    void modifyMember(Member member);

    //根据用户Id更新用户信息
    void updateMemberById(Member member);

    //根据用户Id更新定向卡
    void updateMemberDxkById(Member member);

    //姓名 昵称
    void modifyMemberNoCover(Member member);

    //修改支付密码
    void resetPayPass(Member member);
    //上传经纬度  个人的
    void resetEmpLocation(Member member);

    //性别
    void modifyMemberSex(Member member);
    //生日
    void modifyMemberBirth(Member member);

    void updatePassword(@Param(value = "mobile")String mobile, @Param(value = "password") String password);
    //更新支付密码
    void updatePasswordPay(@Param(value = "mobile")String mobile, @Param(value = "password") String password);

//    List<Member> listMemberInfo(Map<String,Object> map);

    List<Member> listMemberInfoByUsername(Map<String,Object> map);

    /**
     * 根据ID更新pushId
     * @param id
     * @param pushId
     */
    void updatePushId(@Param(value = "id") String id, @Param(value = "pushId") String pushId, @Param(value = "type")String type, @Param(value = "channelId")String channelId);

    //封号 解封号
    void updateFenghao(@Param(value = "is_fenghao") String is_fenghao, @Param(value = "emp_id") String emp_id);
    //封群
    void updateFengQun(@Param(value = "is_fengqun") String is_fengqun, @Param(value = "emp_id") String emp_id);

    /**
     * 修改密码
     * @param empId
     * @param rePass
     */
    void resetPass(@Param(value = "empId") String empId, @Param(value = "rePass") String rePass);

    /**
     * 设置手机号
     * @param empId
     * @param reMobile
     */
    void resetMobile(@Param(value = "empId") String empId, @Param(value = "reMobile") String reMobile);

    List<Member> searchMember(String keyWords);

    List<Member> searchMemberByPage(Map<String,Object> map);

    /**
     * 根据用户ID批量解除禁闭
     * @param map
     */
    void systemOpenEmp(Map<String,Object> map);

    /**
     * 注册会员数量
     * @return
     */
    long memberCount();

    //定向卡会员
    long memberCountDxk();

    //今日注册会员数量
    long countDay(Map<String, Object> map);

    long countDayDxk(Map<String, Object> map);

    /**
     * 被关禁闭会员数量
     * @return
     */
    long closeMemberCount();

    /**
     * 更改环信是否在组
     * @param empId
     */
    void updateHx(String empId);

    /**
     * 根据会员ID设置为承包商
     * @param empId
     */
    void setContractUser(@Param(value = "empId")String empId, @Param(value = "typeId")String typeId);

    /**
     * 根据学校ID查询所有的会员信息
     * @param schoolId
     * @return
     */
    List<Member> listMemberBySchool(String schoolId);

    //查询封号的
    List<MemberVO> getFenghaos();
    //查询封群的
    List<MemberVO> getFengquns();

    //更换学校
    void updateCollegeById(@Param(value = "emp_id") String is_fengqun, @Param(value = "school_id") String emp_id);

    /**
     * 注册会员数量
     * @return
     */
    long memberCountById(String schoolId);

    //更新会员类别
    void updateType(@Param(value = "empType") String is_fengqun, @Param(value = "emp_id") String emp_id);
    void updateEmpUp(@Param(value = "emp_id")String empId, @Param(value = "emp_up")String flag);
}
