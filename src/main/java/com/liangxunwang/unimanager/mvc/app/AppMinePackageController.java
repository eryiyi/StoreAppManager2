package com.liangxunwang.unimanager.mvc.app;

import com.liangxunwang.unimanager.model.MinePackage;
import com.liangxunwang.unimanager.model.tip.DataTip;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.ControllerConstants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2015/8/17.
 */
@Controller
public class AppMinePackageController extends ControllerConstants {

    @Autowired
    @Qualifier("minePackageService")
    private ExecuteService minePackageService;

    /**
     * 获得用户钱包主页
     * @return
     */
    @RequestMapping(value = "/appGetPackage", produces = "text/plain;charset=UTF-8")
    @ResponseBody
    public String appGetPackage(String emp_id){
        try {
            MinePackage minePackage = (MinePackage) minePackageService.execute(emp_id);//钱包信息
            DataTip tip = new DataTip();
            tip.setData(minePackage);
            return toJSONString(tip);
        }catch (Exception e){
            return toJSONString(ERROR_1);
        }
    }

}
