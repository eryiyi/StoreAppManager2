package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.ShoppingAddress;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.mvc.vo.ShoppingAddressVO;
import com.liangxunwang.unimanager.service.*;
import com.liangxunwang.unimanager.util.ControllerConstants;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

/**
 * Created by Administrator on 2015/8/10.
 */
@Controller
public class AppShoppingAddressController extends ControllerConstants{

    @Autowired
    @Qualifier("appShoppingAddressService")
    private SaveService saveAppShoppingAddressService;

    @Autowired
    @Qualifier("appShoppingAddressService")
    private ListService listAppShoppingAddressService;

    @Autowired
    @Qualifier("appShoppingAddressService")
    private DeleteService deleteAppShoppingAddressService;

    @Autowired
    @Qualifier("appShoppingAddressService")
    private UpdateService updateAppShoppingAddressService;

    @Autowired
    @Qualifier("appShoppingAddressService")
    private FindService findAppShoppingAddressService;

    @Autowired
    @Qualifier("appShoppingAddressSecondService")
    private FindService findappShoppingAddressSecondService;

    //保存收货地址
    @RequestMapping("/saveShoppingAddress")
    @ResponseBody
    public String saveShoppingAddress(ShoppingAddress shoppingAddress) {
        if(StringUtil.isNullOrEmpty(shoppingAddress.getEmp_id())){
            return toJSONString(ERROR_1);//empid为空
        }
        saveAppShoppingAddressService.save(shoppingAddress);
        return toJSONString(SUCCESS);//保存成功
    }

    //查询列表
    @RequestMapping(value = "/listShoppingAddress", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String listFavour(String empId){
        List<ShoppingAddressVO> list = (List<ShoppingAddressVO>) listAppShoppingAddressService.list(empId);
        DataTip tip = new DataTip();
        tip.setData(list);
        return toJSONString(tip);
    }

    //删除地址
    @RequestMapping(value = "/deleteShoppingAddress", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String deleteShoppingAddress(String address_id){
        deleteAppShoppingAddressService.delete(address_id);
        return toJSONString(SUCCESS);
    }

    //更新收货地址
    @RequestMapping("/updateShoppingAddress")
    @ResponseBody
    public String updateShoppingAddress(ShoppingAddress shoppingAddress){
        updateAppShoppingAddressService.update(shoppingAddress);
        return toJSONString(SUCCESS);//更新地址成功
    }


    //查询默认收货地址
    @RequestMapping(value = "/getSingleAddressByEmpId", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String getSingleAddressByEmpId(String empId){
        ShoppingAddressVO shoppingAddress = (ShoppingAddressVO) findAppShoppingAddressService.findById(empId);
        DataTip tip = new DataTip();
        tip.setData(shoppingAddress);
        return toJSONString(tip);
    }

    //查询收货地址，根据地址id
    @RequestMapping(value = "/getSingleAddressByAddressId", produces = "text/plain; charset=UTF-8")
    @ResponseBody
    public String getSingleAddressByAddressId(String address_id){
        ShoppingAddressVO shoppingAddress = (ShoppingAddressVO) findappShoppingAddressSecondService.findById(address_id);
        DataTip tip = new DataTip();
        tip.setData(shoppingAddress);
        return toJSONString(tip);
    }

}
