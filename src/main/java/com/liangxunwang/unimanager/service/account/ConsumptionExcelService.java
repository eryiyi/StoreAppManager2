package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.LxConsumptionDao;
import com.liangxunwang.unimanager.model.LxConsumption;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.CreateSimpleExcelToDisk;
import com.liangxunwang.unimanager.util.DateUtil;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zhl on 2016/10/9.
 */
@Service("consumptionExcelService")
public class ConsumptionExcelService implements ExecuteService {
    @Autowired
    @Qualifier("lxConsumptionDao")
    private LxConsumptionDao lxConsumptionDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<LxConsumption> empVOs = new ArrayList<LxConsumption>();
            for(int i=0;i<arrs.length;i++){
                LxConsumption empVO = lxConsumptionDao.findById(arrs[i]);
                empVO.setDateline(DateUtil.getDate(empVO.getDateline(), "yyyy-MM-dd HH:mm"));
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelConsumption(empVOs, request);
            return fileName;
        }
        return null;
    }
}
