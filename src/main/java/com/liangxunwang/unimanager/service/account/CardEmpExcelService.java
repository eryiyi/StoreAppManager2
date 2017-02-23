package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CardEmpDao;
import com.liangxunwang.unimanager.model.CardEmp;
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
 */
@Service("cardEmpExcelService")
public class CardEmpExcelService implements ExecuteService {
    @Autowired
    @Qualifier("cardEmpDao")
    private CardEmpDao cardEmpDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<CardEmp> empVOs = new ArrayList<CardEmp>();
            for(int i=0;i<arrs.length;i++){
                CardEmp empVO = cardEmpDao.findById(arrs[i]);
                empVO.setLx_card_emp_end_time(DateUtil.getDate(empVO.getLx_card_emp_end_time(), "yyyy-MM-dd HH:mm"));
                empVO.setLx_card_emp_start_time(DateUtil.getDate(empVO.getLx_card_emp_start_time(), "yyyy-MM-dd HH:mm"));
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelCardEmp(empVOs, request);
            return fileName;
        }
        return null;
    }


}
