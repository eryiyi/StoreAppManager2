package com.liangxunwang.unimanager.service.account;

import com.liangxunwang.unimanager.dao.CountDao;
import com.liangxunwang.unimanager.mvc.vo.CountVo;
import com.liangxunwang.unimanager.service.ExecuteService;
import com.liangxunwang.unimanager.util.CreateSimpleExcelToDisk;
import com.liangxunwang.unimanager.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import javax.servlet.http.HttpServletRequest;
import java.util.ArrayList;
import java.util.List;

/**
 */
@Service("countExcelService")
public class CountExcelService implements ExecuteService {
    @Autowired
    @Qualifier("countDao")
    private CountDao countDao;

    @Override
    public Object execute(Object object) throws Exception {
        Object[] objects = (Object[]) object;
        String ids = (String) objects[0];
        HttpServletRequest request = (HttpServletRequest) objects[1];
        if(!StringUtil.isNullOrEmpty(ids)){
            String[] arrs = ids.split(",");
            List<CountVo> empVOs = new ArrayList<CountVo>();
            for(int i=0;i<arrs.length;i++){
                CountVo empVO = countDao.findById(arrs[i]);
                empVOs.add(empVO);
            }
            String fileName = CreateSimpleExcelToDisk.toExcelCount(empVOs, request);
            return fileName;
        }
        return null;
    }


}
