package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.Admin;
import com.liangxunwang.unimanager.mvc.vo.AdminVO;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * Created by zhl on 2015/1/29.
 */
@Repository("adminDao")
public interface AdminDao {

    AdminVO findByUsername(String username);

    void add(Admin admin);

    void delete(String id);

    /**
     * 根据ID修改密码
     * @param id
     * @param pass
     */
    void updatePass(@Param(value = "id")String id, @Param(value = "pass") String pass);

    //赋权限
    void updateRoles(@Param(value = "id")String id, @Param(value = "permissions") String pass);


    AdminVO findByEmpId(String emp_id);

    AdminVO findById(String id);

    /**
     * 查询所有的后台管理员信息
     */
    List<AdminVO> lists(Map<String, Object> map);

    List<AdminVO> listsEmpIds(Map<String, Object> map);

    long count(Map<String,Object> map);

    /**
     * 查询所有的后台管理员信息--商家的
     */
    List<AdminVO> listsSj(Map<String, Object> map);

    List<AdminVO> listsDl(Map<String, Object> map);

    long countSj(Map<String,Object> map);

    long countDl(Map<String,Object> map);

    /**
     * 根据ID 禁用 启用
     * @param id
     * @param pass
     */
    void updateType(@Param(value = "id")String id, @Param(value = "is_use") String pass);
}
