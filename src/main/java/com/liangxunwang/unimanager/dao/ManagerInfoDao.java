package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ManagerInfo;
import com.liangxunwang.unimanager.mvc.vo.ManagerInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/8/30.
 */
@Repository("managerInfoDao")
public interface ManagerInfoDao {
    /**
     * 保存后台登陆者的信息
     * @param info
     */
    void save(ManagerInfo info);

    /**
     * 更新信息
     * @param info
     */
    void update(ManagerInfo info);
    //审核
    void updateCheck(ManagerInfo info);

    //更新定向卡商家
    void updateDxkCard(ManagerInfo info);

    /**
     * @return根据用户id查看用户信息
     */
    ManagerInfo findById(String emp_id);


    //根据店铺申请ID
    ManagerInfo findByInfoId(String info_id);

    //根据用户id
    ManagerInfoVo getEmpMsg(String emp_id);

    //根据店铺申请ID
    ManagerInfoVo findByIdInfo(String info_id);

    List<ManagerInfoVo> lists(Map<String, Object> map);

    //查询附近的商家
    List<ManagerInfoVo> listsLocation(Map<String, Object> map);

    Long count(Map<String, Object> map);

    void deleteById(String info_id);
}
