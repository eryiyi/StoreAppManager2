package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppShoppingAddressDao;
import com.liangxunwang.unimanager.model.ShoppingAddress;
import com.liangxunwang.unimanager.mvc.vo.ShoppingAddressVO;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.RelativeDateFormat;
import com.liangxunwang.unimanager.util.UUIDFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
@Service("appShoppingAddressService")
public class AppShoppingAddressService implements SaveService ,ListService ,DeleteService,UpdateService,FindService{

    @Autowired
    @Qualifier("appShoppingAddressDao")
    private AppShoppingAddressDao appShoppingAddressDao;

    //保存收货地址
    public Object save(Object object) throws ServiceException {
        ShoppingAddress shoppingAddress = (ShoppingAddress) object;
        shoppingAddress.setAddress_id(UUIDFactory.random());

        shoppingAddress.setDateline(System.currentTimeMillis() + "");
        //先查询是否有收货地址
        List<ShoppingAddressVO> list = appShoppingAddressDao.findList(shoppingAddress.getEmp_id());
        if(list != null && list.size() > 0){
            //有收货地址
        }else {
            //没有收货地址
            shoppingAddress.setIs_default("1");//设置为默认地址
        }
        //先判断是否把该收货地址设为默认收货地址
        if("1".equals(shoppingAddress.getIs_default())){
            //要把现在的设置为默认收货地址,更新其它地址为普通地址
            appShoppingAddressDao.updateByEmpid(shoppingAddress.getEmp_id());
        }
        appShoppingAddressDao.save(shoppingAddress);
        return null;
    }

    //查询收货地址
    @Override
    public Object list(Object object) throws ServiceException {
        String empId = (String) object;
        List<ShoppingAddressVO> list = appShoppingAddressDao.findList(empId);
        for (ShoppingAddressVO vo : list){
            vo.setDateline(RelativeDateFormat.format(Long.parseLong(vo.getDateline())));
        }
        return list;
    }

    //删除收货地址根据ID
    @Override
    public Object delete(Object object) throws ServiceException {
        String address_id = (String) object;
        appShoppingAddressDao.deleteAddressById(address_id);
        return null;
    }


    //更新收货地址
    public Object update(Object object) throws ServiceException {
        ShoppingAddress shoppingAddress = (ShoppingAddress) object;
        //先判断是否把该收货地址设为默认收货地址
        if("1".equals(shoppingAddress.getIs_default())){
            //要把现在的设置为默认收货地址,更新其它地址为普通地址
            appShoppingAddressDao.updateByEmpid(shoppingAddress.getEmp_id());
        }
        appShoppingAddressDao.update(shoppingAddress);
        return null;
    }

    //查询默认收货地址
    @Override
    public Object findById(Object object) throws ServiceException {
        String empId = (String) object;
        ShoppingAddressVO shoppingAddress = appShoppingAddressDao.findAddressMoren(empId);
        if(shoppingAddress != null){
            return shoppingAddress;
        }else {
            List<ShoppingAddressVO> list = appShoppingAddressDao.findList(empId);
            if(list != null && list.size()>0){
                list.get(0).setDateline(RelativeDateFormat.format(Long.parseLong(list.get(0).getDateline())));
                return list.get(0);
            }else {
                return null;
            }
        }
    }


}
