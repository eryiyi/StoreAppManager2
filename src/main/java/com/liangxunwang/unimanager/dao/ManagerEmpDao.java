package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ManagerEmp;
import com.liangxunwang.unimanager.mvc.vo.ManagerEmpVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by zhl on 2015/2/4.
 */
@Repository("managerEmpDao")
public interface ManagerEmpDao {
    public void save(ManagerEmp managerEmp);

    public List<ManagerEmpVO> list(String adminId);

    public ManagerEmp findByEmpId(String empId);

    public void delete(String empId);

    /**
     * 自动查找即将解除禁闭的会员
     * @return
     */
    public List<ManagerEmp> listOpen(String nowTime);

    /**
     * 将到期的人员自动解除禁闭
     * @param nowTime
     */
    public void systemOpenEmp(String nowTime);
}
