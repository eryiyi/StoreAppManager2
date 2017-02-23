package com.liangxunwang.unimanager.service.app;

import com.liangxunwang.unimanager.dao.AppShoppingAddressDao;
import com.liangxunwang.unimanager.mvc.vo.ShoppingAddressVO;
import com.liangxunwang.unimanager.service.FindService;
import com.liangxunwang.unimanager.service.ServiceException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
 * Created by Administrator on 2015/8/15.
 */
@Service("appShoppingAddressSecondService")
public class AppShoppingAddressSecondService implements FindService  {
    @Autowired
    @Qualifier("appShoppingAddressDao")
    private AppShoppingAddressDao appShoppingAddressDao;

    //查询收货地址，根据地址ID
    @Override
    public Object findById(Object object) throws ServiceException {
        String address_id = (String) object;
        ShoppingAddressVO shoppingAddress = appShoppingAddressDao.findAddressByAddressId(address_id);
        return shoppingAddress;
    }

}
