package com.liangxunwang.unimanager.dao;

import com.liangxunwang.unimanager.model.ShoppingAddress;
import com.liangxunwang.unimanager.mvc.vo.ShoppingAddressVO;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Created by Administrator on 2015/8/11.
 */
@Repository("appShoppingAddressDao")
public interface AppShoppingAddressDao {
    /**
     * 保存收货地址
     * */
    void save(ShoppingAddress shoppingAddress);
    /**
     * */
    List<ShoppingAddressVO> findList(String empId);

    /**
     * 删除收货地址根据id
     * */
    void deleteAddressById(String address_id);
    /**
     * 跟新收货地址
     * */
    void update(ShoppingAddress shoppingAddress);
    /**
     * 根据用户id，更改默认收货地址
     * */
    void updateByEmpid(String emp_id);
    /**
     * 发现默认收货地址
     * */
    ShoppingAddressVO findAddressMoren(String empId);
    /*
    * 查询收货地址，根据地址di
    * */
    ShoppingAddressVO findAddressByAddressId(String address_id);

}
